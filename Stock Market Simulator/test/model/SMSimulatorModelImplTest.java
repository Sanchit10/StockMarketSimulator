package model;

import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.util.HashMap;

import static java.lang.Thread.sleep;
import static org.junit.Assert.assertEquals;

/**
 * A test class to test the model implementation.
 */
public class SMSimulatorModelImplTest {

  private SMSimulatorModel myModel;
  private static final String userDirectory =
      System.getProperty("user.dir") + File.separator + "Investor_Profile";

  @Before
  public void setup() {
    myModel = new SMSimulatorModelImpl();
    PortfolioOperations pos = new PortfolioOperations();
    pos.recursiveDelete(userDirectory);
  }


  /**
   * Tests expected exception throw for invalid ticker symbol.
   */
  @Test(expected = Exception.class)
  public void invalidTicker() throws Exception {
    myModel.getStockInfoAtDate("apL", "2018-10-02");

  }

  /**
   * Tests expected exception throw for invalid date input.
   */
  @Test(expected = Exception.class)
  public void invalidDate() throws Exception {
    myModel.getStockInfoAtDate("AAPL", "2019-10-04");
  }

  /**
   * Tests expected exception throw for another invalid date.
   */
  @Test(expected = Exception.class)
  public void invalidDate1() throws Exception {
    myModel.getStockInfoAtDate("AAPL", "-2019-10-04");
  }

  /**
   * Tests expected exception throw for illegal custom weight input.
   */
  @Test(expected = Exception.class)
  public void invalidWeights() throws Exception {
    myModel.createNewPortfolio("p1");
    myModel.purchase("AAPL", "1200", "2018-11-14", "p1", 10000, 1);
    myModel.purchase("AMZN", "1200", "2018-11-14", "p1", 10000, 1);
    HashMap<String, Integer> weights = new HashMap<>();
    weights.put("AAPL", 24);
    weights.put("AMZN", 75);
    myModel.setWeights("p1", weights);
  }

  /**
   * Tests expected exception throw for another invalid date.
   */
  @Test
  public void testStockInfoDate() {
    String s = "TimeStamp: 2018-10-02\n"
        + "Opening price: 227.2500\n"
        + "Day's high: 230.0000\n"
        + "Day's low: 226.6300\n"
        + "Closing price: 229.2800\n"
        + "Volume: 24788170";
    try {
      assertEquals(myModel.getStockInfoAtDate("AAPL", "2018-10-02"), s);
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  /**
   * Tests for valid portfolio creation.
   */
  @Test
  public void testPortfolioCreation() {
    String s1 = "Portfolio myPortfolio successfully created";
    assertEquals(myModel.createNewPortfolio("myPortfolio"), s1);
  }

  @Test
  public void testReadPortfolio() {
    myModel.createNewPortfolio("p1");
    myModel.createNewPortfolio("p2");
    myModel.createNewPortfolio("p3");
    myModel.createNewPortfolio("p4");
    assertEquals("p1\np2\np3\np4", myModel.getPortfolioNames());
  }


  /**
   * Tests purchase and total value functionality. This test fails if run with the entire batch of
   * tests due to overbearing calls to AlphaVantage. It must be run on its own. This also tests the
   * fixed investment functionality among even distributions.
   */
  @Test
  public void testPurchaseAndTotalValue() {
    myModel.createNewPortfolio("p1");
    myModel.purchase("AAPL", "1200", "2018-11-14", "p1", 10000, 1);
    myModel.purchase("AMZN", "1200", "2018-11-14", "p1", 10000, 1);
    String expectedOutput = "Portfolio name: p1\n" +
        "Total cost basis: 19689.4046\n" +
        "AAPL: 53 shares owned\n" +
        "AMZN: 6 shares owned";
    assertEquals("19494.46", myModel.getValue("p1", "2018-11-14"));
    assertEquals(expectedOutput, myModel.readPortfolio("p1"));
    assertEquals("19689.4046", myModel.getPortfolioCostBasis("p1"));
    try {
      sleep(5000);
      myModel.investFixedAmount("p1", "2018-11-14", "2018-11-15", 10, 20000, 1);
    } catch (Exception e) {
      e.printStackTrace();
    }
    expectedOutput = "Portfolio name: p1\n" +
        "Total cost basis: 39378.8092\n" +
        "AAPL: 106 shares owned\n" +
        "AMZN: 12 shares owned";
    System.out.println(myModel.readPortfolio("p1"));
    assertEquals(expectedOutput, myModel.readPortfolio("p1"));
  }

  /**
   * Tests even distribution fixed investment.
   */
  @Test
  public void testSingleHoldingFixedInv() {
    myModel.createNewPortfolio("p1");
    myModel.purchase("AAPL", "1200", "2018-11-14", "p1", 10000, 1);
    try {
      myModel.investFixedAmount("p1", "2018-11-14", "2018-11-15", 10, 10000, 1);
    } catch (Exception e) {
      e.printStackTrace();
    }
    String expectedOutput = "Portfolio name: p1\n" +
        "Total cost basis: 19998.808000000005\n" +
        "AAPL: 106 shares owned";
    assertEquals(expectedOutput, myModel.readPortfolio("p1"));
  }

  /**
   * Tests for proper distribution of purchases for custom weights.
   */
  @Test
  public void testCustomHolding() {
    HashMap<String, Integer> weights = new HashMap<>();
    weights.put("AAPL", 25);
    weights.put("AMZN", 75);
    myModel.createNewPortfolio("p1");
    myModel.purchase("AAPL", "1200", "2018-11-14", "p1", 10000, 1);
    myModel.purchase("AMZN", "1200", "2018-11-14", "p1", 10000, 1);
    try {
      myModel.setWeights("p1", weights);//fixme
    } catch (Exception e) {
      e.printStackTrace();
    }
    try {
      myModel.investFixedAmount("p1", "2018-11-14", "2018-11-15", 10, 10000, 1);
    } catch (Exception e) {
      e.printStackTrace();
    }
    String expectedOutput = "Portfolio name: p1\n" +
        "Total cost basis: 28602.089000000004\n" +
        "AAPL: 66 shares owned\n" +
        "AMZN: 10 shares owned";
    assertEquals(expectedOutput, myModel.readPortfolio("p1"));
  }

  /**
   * Tests fixed interval investment with even distributions and no end date.
   */
  @Test
  public void testNoEndDateInvestFixedInterval() {
    myModel.createNewPortfolio("p1");
    myModel.purchase("AAPL", "1200", "2018-11-14", "p1", 10000, 1);
    myModel.purchase("AMZN", "1200", "2018-11-14", "p1", 10000, 1);
    try {
      myModel.investFixedAmount("p1", "2018-11-14", "NA", 20, 20000, 1);
    } catch (Exception e) {
      e.printStackTrace();
    }
    String expectedOutput = "Portfolio name: p1\n" +
        "Total cost basis: 57797.8156\n" +
        "AAPL: 162 shares owned\n" +
        "AMZN: 17 shares owned";
    assertEquals(expectedOutput, myModel.readPortfolio("p1"));
  }

  /**
   * Tests for no portfolio condition.
   */
  @Test
  public void checkNullPortfolio() {
    assertEquals("No such portfolio exists. Please check your spelling.",
        myModel.readPortfolio("Goldfinger"));
    myModel.createNewPortfolio("Goldfinger");
    assertEquals("This portfolio is active, but contains no holdings.",
        myModel.readPortfolio("Goldfinger"));
  }
}
