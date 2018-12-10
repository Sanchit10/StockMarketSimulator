package model;

import java.text.Format;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.concurrent.TimeUnit;

import static java.lang.Thread.sleep;

/**
 * A class that implements the model interface, it implements all the required functionalitites.
 * desired by the stock market simulator
 */
public class SMSimulatorModelImpl implements SMSimulatorModel {

  private User currentUser = new User();

  @Override
  public boolean login(String userName, String password) {
    return true;
  }

  @Override
  public int getPortfolioSize(String portfolioName) {
    return this.currentUser.getPortfolioSize(portfolioName);
  }

  @Override
  public String createNewPortfolio(String portfolioName) {
    if (this.loggedIn()) {
      return this.currentUser.createNewPortfolio(portfolioName);
    }
    return "Cannot create portfolio " + portfolioName + "; user not logged in";
  }

  @Override
  public String getStockInfoAtDate(String tickerSymbol, String date) throws Exception {
    if (this.loggedIn()) {
      return this.currentUser.getStockInfoAtDate(tickerSymbol, date);
    }
    return "Cannot retrieve info. Please log in first";
  }

  @Override
  public String readPortfolio(String portfolioName) {
    if (this.loggedIn()) {
      return this.currentUser.readPortfolio(portfolioName);
    }
    return "Cannot read portfolio. Please log in first.";
  }

  @Override
  public String getPortfolioNames() {
    if (this.loggedIn()) {
      return this.currentUser.getPortfolioNames();
    }
    return "Cannot retrieve portfolios. Please log in first";
  }

  @Override
  public String getPortfolioCostBasis(String portfolioName) {
    if (this.loggedIn()) {
      return this.currentUser.getPortfolioCostBasis(portfolioName);
    }
    return "Cannot retrieve cost basis. Please log in first";
  }

  @Override
  public String getStockCostBasis(String tickerSymbol, String portfolioName) {
    if (this.loggedIn()) {
      return this.currentUser.getIndividualStockCostBasis(tickerSymbol, portfolioName);
    }
    return "Cannot retrieve stock cost basis. Please log in first.";
  }

  @Override
  public String getValue(String portfolioName, String date) {
    if (this.loggedIn()) {
      return this.currentUser.getPortfolioValue(portfolioName, date);
    }
    return "Cannot retrieve portfolio value. Please log in first.";
  }

  @Override
  public String purchase(String tickerSymbol, String time, String date, String portfolioName,
      double dollarAmount, double commissionFeePercentage) {
    if (this.loggedIn()) {
      //double commissionFee = dollarAmount / 100 * commissionFeePercentage;
      return this.currentUser.purchase(tickerSymbol, time, date, portfolioName, dollarAmount,
          commissionFeePercentage);
    }
    return "Cannot make purchase; user not logged in";
  }

  @Override
  public String investFixedAmount(String portfolioName, String startDate, String endDate,
      int interval, double dollars, double commissionFeePercentage)
      throws Exception {
    if (dollars < 0 || commissionFeePercentage < 0) {
      return "Error: cannot pass negative dollar amounts or commission fee percentages";
    }
    String actualEndDate;
    if (endDate.matches("NA")) {
      ZonedDateTime nycTime = ZonedDateTime.now(ZoneId.of("America/New_York"));
      actualEndDate = nycTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
    } else {
      actualEndDate = endDate;
    }
    //get the difference in dates
    int dateDiff = 0;
    dateDiff = this.dateDiff(startDate, actualEndDate);
    if (dateDiff < 0) {
      throw new Exception("invalid start and end dates");
    }
    int numberOfInvestments = dateDiff / interval + 1;
    String date = startDate;
    for (int i = 0; i < numberOfInvestments; i++) {
      this.currentUser.investFixedAmount(portfolioName, date, dollars, commissionFeePercentage);
      date = addDaysToDate(date, interval);
      sleep(2000);
    }
    return "Fixed amount investment successful to portfolio:" + portfolioName;
  }

  @Override
  public String setWeights(String portfolioName, HashMap<String, Integer> weights)
      throws Exception {
    return this.currentUser.setWeights(portfolioName, weights);
  }

  /**
   * Returns true if user is logged in, otherwise false.
   *
   * @return boolean indicating logged in status
   */
  private boolean loggedIn() {
    return (this.currentUser != null);
  }

  /**
   * Returns the difference in days between dates.
   *
   * @return difference in days between dates
   */
  private int dateDiff(String startDate, String endDate) throws ParseException {
    //get todays date
    ZonedDateTime nycTime = ZonedDateTime.now(ZoneId.of("America/New_York"));
    nycTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));

    //format start, end, and today's dates
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);
    Date beginDate = sdf.parse(startDate);
    Date finishDate = sdf.parse(endDate);
    Date todaysDate = sdf.parse(nycTime.toString());

    //get difference between start and end
    long startToEndMillies = finishDate.getTime() - beginDate.getTime();
    long diffStartToEnd = TimeUnit.DAYS.convert(startToEndMillies, TimeUnit.MILLISECONDS);

    //make sure end date is not after today's date
    long endToTodayMillis = todaysDate.getTime() - finishDate.getTime();
    long diffEndToToday = TimeUnit.DAYS.convert(endToTodayMillis, TimeUnit.MILLISECONDS);

    //return appropriate value
    if (diffStartToEnd < 0 ||
        diffEndToToday < 0) {
      return -1;
    } else {
      return (int) diffStartToEnd;
    }
  }

  /**
   * Increments date in accordance with investment strategy interval.
   *
   * @return the updated date
   */
  private String addDaysToDate(String date, int days) throws ParseException {
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);
    Date oldDate = sdf.parse(date);
    Calendar cal = Calendar.getInstance();
    cal.setTime(oldDate);
    cal.add(Calendar.DATE, days);
    Date newDate = cal.getTime();
    Format formatter = new SimpleDateFormat("yyyy-MM-dd");
    return formatter.format(newDate);
  }
}