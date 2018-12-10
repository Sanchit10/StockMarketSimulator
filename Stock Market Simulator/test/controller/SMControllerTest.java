package controller;

import static org.junit.Assert.assertEquals;

import java.io.File;
import java.io.IOException;
import java.io.Reader;
import java.io.StringReader;
import model.SMSimulatorModel;
import model.SMSimulatorModelImpl;
import org.junit.Before;
import org.junit.Test;
import view.View;
import view.ViewImpl;

/**
 * Test class for the controller.
 */
public class SMControllerTest {

  private SMSimulatorModel myModel;
  private View myView;
  private static final String userDirectory =
      System.getProperty("user.dir") + File.separator + "Investor_Profile";

  /**
   * Setting up a model instance.
   */
  @Before
  public void setup1() {
    myModel = new SMSimulatorModelImpl();
    myView = new ViewImpl();
  }

  /**
   * A controller test.
   */
  @Test
  public void ValidUsername() throws IOException {
    String expectedControllerState = "Hi James Bond welcome back!!\n"
        + "Quitting simulator.Goodbye!\n";

    StringBuffer out = new StringBuffer();
    Reader in = new StringReader("James Bond\nBond@007\nq");
    SMController myController = new SMControllerImpl(in, out);
    myController.runStockMarketSimulation(myModel, myView);
    assertEquals(out.toString(), expectedControllerState);
  }


  /**
   * A controller test.
   */
  @Test
  public void createNewPortFolio() throws IOException {
    String s = "Hi James Bond welcome back!!\n"
        + "PortfolioOperations myPortfolio successfully createdQuitting simulator.Goodbye!\n";

    StringBuffer out = new StringBuffer();
    Reader in = new StringReader("James Bond\nBond@007\n1\nmyPortfolio\nq");
    SMController myController = new SMControllerImpl(in, out);
    myController.runStockMarketSimulation(myModel, myView);
    assertEquals(out.toString(), s);

  }


  /**
   * A controller test.
   */
  @Test
  public void getStockInfoAtDate() throws IOException {
    String s = "Hi James Bond welcome back!!\n"
        + "TimeStamp: 2018-11-13\n"
        + "Opening price: 191.6300\n"
        + "Day's high: 197.1800\n"
        + "Day's low: 191.4501\n"
        + "Closing price: 192.2300\n"
        + "Volume: 46882936Quitting simulator.Goodbye!\n";
    StringBuffer out = new StringBuffer();
    Reader in = new StringReader("James Bond\nBond@007\n3\nAAPL\n2018-11-13\nq");
    SMController myController = new SMControllerImpl(in, out);
    myController.runStockMarketSimulation(myModel, myView);
    assertEquals(out.toString(), s);
  }


  /**
   * A controller test.
   */
  @Test
  public void getStcokInfoAtAPrevDate() throws IOException {
    String s = "Hi James Bond welcome back!!\n"
        + "TimeStamp: 2018-08-31\n"
        + "Opening price: 226.5100\n"
        + "Day's high: 228.8700\n"
        + "Day's low: 226.0000\n"
        + "Closing price: 227.6300\n"
        + "Volume: 43340134Quitting simulator.Goodbye!\n";
    StringBuffer out = new StringBuffer();
    Reader in = new StringReader("James Bond\nBond@007\n3\nAAPL\n2018-08-31\nq");
    SMController myController = new SMControllerImpl(in, out);
    myController.runStockMarketSimulation(myModel, myView);
    assertEquals(out.toString(), s);
  }


  /**
   * A controller test.
   */
  @Test
  public void listOfPortfolios() throws IOException {
    String s = "Hi James Bond welcome back!!\n"
        + "PortfolioOperations Portfolio1 successfully createdPortfolio Portfolio2 "
        + "successfully createdPortfolio1\n"
        + "Portfolio2Quitting simulator.Goodbye!\n";
    StringBuffer out = new StringBuffer();
    Reader in = new StringReader("James Bond\nBond@007\n1\nPortfolio1\n1\nPortfolio2\n2\nq");
    SMController myController = new SMControllerImpl(in, out);
    myController.runStockMarketSimulation(myModel, myView);
    assertEquals(out.toString(), s);
  }


  /**
   * A controller test.
   */
  @Test
  public void purchaseStock() throws IOException {
    String s = "Hi James Bond welcome back!!\n"
        + "PortfolioOperations Portfolio1 successfully createdSuccessfully "
        + "purchased 4 shares of AAPL "
        + "at 227.63 per share.Quitting simulator.Goodbye!\n";
    StringBuffer out = new StringBuffer();
    Reader in = new StringReader(
        "James Bond\nBond@007\n1\nPortfolio1\n4\nAAPL\n0921\n2018-08-31"
            + "\nPortfolio1\n1000.54\n1\nq");
    SMController myController = new SMControllerImpl(in, out);
    myController.runStockMarketSimulation(myModel, myView);
    assertEquals(out.toString(), s);

  }


  /**
   * A controller test.
   */
  @Test
  public void purchaseMultipleStocks() throws IOException {
    String s = "Hi James Bond welcome back!!\n"
        + "PortfolioOperations Portfolio1 successfully createdSuccessfully "
        + "purchased 4 shares of AAPL"
        + " at 227.63 per share.Successfully purchased 18 shares of MSFT at 106.94 per share."
        + "Quitting simulator.Goodbye!\n";
    StringBuffer out = new StringBuffer();
    Reader in = new StringReader(
        "James Bond\nBond@007\n1\nPortfolio1\n4\nAAPL\n0921\n2018-08-31\nPortfolio1\n1000.54"
            + "\n1\n4\nMSFT\n0954\n2018-11-13\nPortfolio1\n2050.98\n1\nq");
    SMController myController = new SMControllerImpl(in, out);
    myController.runStockMarketSimulation(myModel, myView);
    assertEquals(out.toString(), s);

  }


  /**
   * A controller test.
   */
  @Test
  public void purchaseMultipleReadPortfoliosCheckVal() throws IOException {
    String s = "Hi James Bond welcome back!!\n"
        + "PortfolioOperations Portfolio1 successfully createdSuccessfully purchased 4 shares of "
        + "AAPL at 227.63 per share.Successfully purchased 18 shares of MSFT at "
        + "106.94 per share.PortfolioOperations name: Portfolio1\n"
        + "Total cost basis: 2863.7943999999998\n"
        + "MSFT: 18 shares owned\n"
        + "AAPL: 4 shares ownedQuitting simulator.Goodbye!\n";
    StringBuffer out = new StringBuffer();
    Reader in = new StringReader(
        "James Bond\nBond@007\n1\nPortfolio1\n4\nAAPL\n0921\n2018-08-31"
            + "\nPortfolio1\n1000.54\n1\n4\nMSFT\n0954\n2018-11-13\nPortfolio1"
            + "\n2050.98\n1\n5\nPortfolio1\nq");
    SMController myController = new SMControllerImpl(in, out);
    myController.runStockMarketSimulation(myModel, myView);
    assertEquals(out.toString(), s);

  }


  /**
   * A controller test.
   */
  @Test
  public void InvalidTickerSymbol() throws IOException {
    String s = "Hi James Bond welcome back!!\n"
        + "Quitting simulator.Goodbye!\n";
    StringBuffer out = new StringBuffer();
    Reader in = new StringReader("James Bond\nBond@007\n3\nOOEI332L\n2018-11-13\nq");
    SMController myController = new SMControllerImpl(in, out);
    myController.runStockMarketSimulation(myModel, myView);
    assertEquals(out.toString(), s);
  }


  /**
   * A controller test.
   */
  @Test
  public void InvalidTime() throws IOException {
    String s = "Hi James Bond welcome back!!\n"
        + "PortfolioOperations Portfolio1 successfully createdSuccessfully purchased 4 shares of "
        + "AAPL at 227.63 per share.The market is closed. Please select "
        + "a valid business dayQuitting simulator.Goodbye!\n";
    StringBuffer out = new StringBuffer();
    Reader in = new StringReader(
        "James Bond\nBond@007\n1\nPortfolio1\n4\nAAPL\n0921\n2018-08-31\nPortfolio1"
            + "\n1000.54\n1\n4\nMSFT\n0123\n2018-11-13\nPortfolio1\n2050.98\n1\nq");
    SMController myController = new SMControllerImpl(in, out);
    myController.runStockMarketSimulation(myModel, myView);
    assertEquals(out.toString(), s);
  }


  /**
   * A controller test.
   */
  @Test
  public void invalidPortfolioName() throws IOException {
    String s = "Hi James Bond welcome back!!\n"
        + "PortfolioOperations Portfolio1 successfully createdNo such portfolio exists. "
        + "Please check your spelling.Quitting simulator.Goodbye!\n";
    StringBuffer out = new StringBuffer();
    Reader in = new StringReader("James Bond\nBond@007\n1\nPortfolio1\n5\nPortfolio2\nq");
    SMController myController = new SMControllerImpl(in, out);
    myController.runStockMarketSimulation(myModel, myView);
    assertEquals(out.toString(), s);
  }


  /**
   * A controller test.
   */
  @Test
  public void allFunctionalities() throws IOException {
    String s = "Hi James Bond welcome back!!\n"
        + "PortfolioOperations Portfolio1 successfully createdPortfolio Portfolio2 successfully "
        + "createdSuccessfully purchased 4 shares of AAPL at 227.63 per share.Successfully "
        + "purchased 14 shares of MSFT at 106.94 per share.Successfully purchased 5 shares "
        + "of FB at 175.73 per share.Enter the name of the"
        + " portfolio whose net worth you wish to know\n"
        + "Enter the date (in yyyy-mm-dd format) for when you wish to know the market "
        + "value for your portfolio. Enter today's date for the current market value\n"
        + " 2246.2799999999997Quitting simulator.Goodbye!\n";
    StringBuffer out = new StringBuffer();
    Reader in = new StringReader(
        "James Bond\nBond@007\n1\nPortfolio1\n1\nPortfolio2\n4\nAAPL\n0921"
            + "\n2018-08-31\nPortfolio1\n1000.54\n4\nMSFT\n0954\n2018-11-13"
            + "\nPortfolio1\n1567.75\n4\nFB\n0944\n2018-08-31\nPortfolio2\n999\n8"
            + "\nPortfolio1\n2018-07-31\nq");
    SMController myController = new SMControllerImpl(in, out);
    myController.runStockMarketSimulation(myModel, myView);
    assertEquals(out.toString(), s);

  }


  /**
   * A controller test.
   */
  @Test
  public void testCostBasisPortfolio() throws IOException {
    String s = "Hi James Bond welcome back!!\n"
        + "PortfolioOperations Portfolio1 successfully createdPortfolio Portfolio2 successfully "
        + "createdSuccessfully purchased 4 shares of AAPL at 227.63 "
        + "per share.910.52Quitting simulator.Goodbye!\n";
    StringBuffer out = new StringBuffer();
    Reader in = new StringReader(
        "James Bond\nBond@007\n1\nPortfolio1\n1\nPortfolio2\n4\nAAPL\n0921\n2018-08-31"
            + "\nPortfolio1\n1000.54\n6\nPortfolio1\nq");
    SMController myController = new SMControllerImpl(in, out);
    myController.runStockMarketSimulation(myModel, myView);
    assertEquals(out.toString(), s);

  }

  /**
   * A controller test.
   */
  @Test
  public void testForCostperbasisinAnyPortfolio() throws IOException {
    String s = "Hi James Bond welcome back!!\n"
        + "PortfolioOperations Portfolio1 successfully createdPortfolio Portfolio2 successfully"
        + " createdSuccessfully purchased 4 shares of AAPL at 227.63 per share.910.52Enter the "
        + "ticker symbol of the stock whose cost per basis you wish to examine\n"
        + "Enter the name of the portfolio wherein you wish to examine the stock "
        + "you just mentioned\n"
        + "910.5200Quitting simulator.Goodbye!\n";
    StringBuffer out = new StringBuffer();
    Reader in = new StringReader(
        "James Bond\nBond@007\n1\nPortfolio1\n1\nPortfolio2\n4\nAAPL\n0921\n2018-08-31"
            + "\nPortfolio1\n1000.54\n6\nPortfolio1\n7\nAAPL\nPortfolio1\nQ");
    SMController myController = new SMControllerImpl(in, out);
    myController.runStockMarketSimulation(myModel, myView);
    assertEquals(out.toString(), s);
  }

  /**
   * A controller test.
   */
  @Test
  public void testForUnequalWeightDistribution() throws IOException {
    String s = "Hi James Bond welcome back!!\n"
        + "PortfolioOperations Portfolio1 successfully createdPortfolio Portfolio2 successfully "
        + "createdSuccessfully purchased 4 shares of "
        + "AAPL at 227.63 per share.919.6252919.6252Quitting simulator.Goodbye!\n";
    StringBuffer out = new StringBuffer();
    Reader in = new StringReader(
        "James Bond\nBond@007\n1\nPortfolio1\n1\nPortfolio2\n4\nAAPL\n0921\n2018-08-31"
            + "\nPortfolio1\n1000.54\n1\n6\nPortfolio1\n7\nAAPL\nPortfolio1\n9\nyes\n"
            + "Portfolio1\nAAPL\n43\nM\nq");
    SMController myController = new SMControllerImpl(in, out);
    myController.runStockMarketSimulation(myModel, myView);
    assertEquals(out.toString(), s);
  }

  /**
   * A controller test.
   */
  @Test
  public void testForMultipleStocksEqualDistribution() throws IOException {
    String s = "Hi James Bond welcome back!!\n"
        + "PortfolioOperations Portfolio1 successfully createdPortfolio Portfolio2 successfully "
        + "createdSuccessfully purchased 4 shares of AAPL at 227.63 per share.919.6252919.6252"
        + "Fixed amount investment successful to portfolio:"
        + "Portfolio1Quitting simulator.Goodbye!\n";
    StringBuffer out = new StringBuffer();
    Reader in = new StringReader(
        "James Bond\nBond@007\n1\nPortfolio1\n1\nPortfolio2\n4\nAAPL\n0921\n2018-08-31"
            + "\nPortfolio1\n1000.54\n1\n6\nPortfolio1\n7\nAAPL\nPortfolio1\n9\nno\n"
            + "Portfolio1\n2018-08-31\n2018-10-20\n10\n600\n0.1\nq");
    SMController myController = new SMControllerImpl(in, out);
    myController.runStockMarketSimulation(myModel, myView);
    assertEquals(out.toString(), s);
  }

  /**
   * A controller test.
   */
  @Test
  public void testCustomWeightsMultipleStocks() throws IOException {
    String s = "Hi James Bond welcome back!!\n"
        + "PortfolioOperations pf1 successfully createdSuccessfully purchased 4 shares of AAPL "
        + "at 227.63 per share.Successfully purchased 7 "
        + "shares of MSFT at 112.33 per share.Weights setFixed amount "
        + "investment successful to portfolio:pf1Quitting simulator.Goodbye!\n";
    StringBuffer out = new StringBuffer();
    Reader in = new StringReader(
        "James Bond\nBond@007\n1\npf1\n4\nAAPL\n0921\n2018-08-31\npf1\n999\n1\n4\nMSFT\n0921"
            + "\n2018-08-31\npf1\n899\n0.1\n9\nyes\npf1\nAAPL\n91\nMSFT\n9\npf1"
            + "\n2018-09-06\n2018-10-20\n21\n850\n0.1\nq");
    SMController myController = new SMControllerImpl(in, out);
    myController.runStockMarketSimulation(myModel, myView);
    assertEquals(out.toString(), s);
  }

  /**
   * A controller test.
   */
  @Test
  public void testForInvalidDateMultiple() throws IOException {
    String s = "Hi James Bond welcome back!!\n"
        + "PortfolioOperations pf1 successfully createdSuccessfully purchased 4 shares of AAPL "
        + "at 227.63 per share.Successfully purchased 7 "
        + "shares of MSFT at 112.33 per share."
        + "invalid dateQuitting simulator.Goodbye!\n";
    StringBuffer out = new StringBuffer();
    Reader in = new StringReader(
        "James Bond\nBond@007\n1\npf1\n4\nAAPL\n0921\n2018-08-31\npf1\n999\n1\n4\nMSFT\n0921"
            + "\n2018-08-31\npf1\n899\n0.1\n9\nyes\npf1\nAAPL\n91\nMSFT\n9\npf1"
            + "\n2018-09-06\n2018-10-32\n19\n850\n0.1\nM\nq");
    SMController myController = new SMControllerImpl(in, out);
    myController.runStockMarketSimulation(myModel, myView);
    assertEquals(out.toString(), s);
  }

  /**
   * A controller test.
   */
  @Test
  public void endDateHoliday() throws IOException {

    String s = "Hi James Bond welcome back!!\n"
        + "PortfolioOperations pf1 successfully createdSuccessfully "
        + "purchased 4 shares of AAPL at 227.63 "
        + "per share.Successfully purchased 7 shares of MSFT at 112.33 per share.Weights "
        + "setFixed amount investment successful to portfolio:pf1Portfolio name: pf1\n"
        + "Total cost basis: 2454.66871\n"
        + "MSFT: 7 shares owned\n"
        + "AAPL: 8 shares ownedQuitting simulator.Goodbye!\n";
    StringBuffer out = new StringBuffer();
    Reader in = new StringReader(
        "James Bond\nBond@007\n1\npf1\n4\nAAPL\n0921\n2018-08-31\npf1\n999\n1\n4\nMSFT\n0921"
            + "\n2018-08-31\npf1\n899\n0.1\n9\nyes\npf1\nAAPL\n91\nMSFT\n9\npf1"
            + "\n2018-11-14\n2018-11-22\n19\n850\n0.1\n5\npf1\nq");
    SMController myController = new SMControllerImpl(in, out);
    myController.runStockMarketSimulation(myModel, myView);
    assertEquals(out.toString(), s);
  }

  /**
   * A controller test.
   */
  @Test
  public void testNegativeCommission() throws IOException {
    String s = "Hi James Bond welcome back!!\n"
        + "PortfolioOperations pf1 successfully createdinvalid commission fee percentage. "
        + "Please input a positive numberQuitting simulator.Goodbye!\n";
    StringBuffer out = new StringBuffer();
    Reader in = new StringReader(
        "James Bond\nBond@007\n1\npf1\n4\nAAPL\n0921\n2018-08-31\npf1\n999\n-1\nq");
    SMController myController = new SMControllerImpl(in, out);
    myController.runStockMarketSimulation(myModel, myView);
    assertEquals(out.toString(), s);
  }

  /**
   * A controller test.
   */
  @Test
  public void checkPortfolioCostPerBasisBetweenInvestment() throws IOException {
    String s = "Hi James Bond welcome back!!\n"
        + "PortfolioOperations pf1 successfully createdSuccessfully purchased 4 shares of "
        + "AAPL at 227.63 per share.Successfully purchased 7 shares of MSFT "
        + "at 112.33 per share.Weights set\n"
        + "Fixed amount investment successful to portfolio:pf13700.92372Quitting "
        + "simulator.Goodbye!\n";
    StringBuffer out = new StringBuffer();
    Reader in = new StringReader(
        "James Bond\nBond@007\n1\npf1\n4\nAAPL\n0921\n2018-08-31\npf1\n999\n1\n4\nMSFT\n0921"
            + "\n2018-08-31\npf1\n899\n0.1\n9\nyes\npf1\nAAPL\n91\nMSFT\n9\npf1"
            + "\n2018-09-06\n2018-10-20\n21\n850\n0.1\n6\npf1\nq");
    SMController myController = new SMControllerImpl(in, out);
    myController.runStockMarketSimulation(myModel, myView);
    assertEquals(out.toString(), s);

  }

  /**
   * A controller test.
   */
  @Test
  public void checkValueOfPortfolioBetweenInvestmentPeriod() throws IOException {
    String s = "Hi James Bond welcome back!!\n"
        + "PortfolioOperations pf1 successfully createdSuccessfully purchased 4 shares of "
        + "AAPL at 227.63 per share.Successfully purchased 7 shares of MSFT at 112.33 "
        + "per share.Weights set\n"
        + "Fixed amount investment successful to portfolio:pf13786.693611.65"
        + "Quitting simulator.Goodbye!\n";
    StringBuffer out = new StringBuffer();
    Reader in = new StringReader(
        "James Bond\nBond@007\n1\npf1\n4\nAAPL\n0921\n2018-08-31\npf1\n999\n1\n4\nMSFT\n0921"
            + "\n2018-08-31\npf1\n899\n0.1\n9\nyes\npf1\nAAPL\n91\nMSFT\n9\npf1"
            + "\n2018-09-06\n2018-10-20\n21\n850\n0.1\n8\npf1\n2018-10-02\n8"
            + "\npf1\n2018-10-21\nq");
    SMController myController = new SMControllerImpl(in, out);
    myController.runStockMarketSimulation(myModel, myView);
    assertEquals(out.toString(), s);

  }

  /**
   * A controller test.
   */
  @Test
  public void fixedInvestmentWithoutEnddate() throws IOException {
    String s = "Hi James Bond welcome back!!\n"
        + "PortfolioOperations pf1 successfully createdSuccessfully purchased 4 shares of "
        + "AAPL at 227.63 per share.Fixed amount investment "
        + "successful to portfolio:pf1Quitting simulator.Goodbye!\n";
    StringBuffer out = new StringBuffer();
    Reader in = new StringReader(
        "James Bond\nBond@007\n1\npf1\n4\nAAPL\n0921\n2018-08-31\npf1"
            + "\n999\n1\n9\nno\npf1\n2018-09-06\nNA\n30\n453\n1\nq");
    SMController myController = new SMControllerImpl(in, out);
    myController.runStockMarketSimulation(myModel, myView);
    assertEquals(out.toString(), s);
  }

}





