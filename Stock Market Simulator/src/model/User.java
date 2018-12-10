package model;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.Format;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Locale;
import java.util.Scanner;

import static java.lang.Thread.sleep;

/**
 * A class that creates a user object with username and password attributes.
 */
public class User {

  private double purse;
  private PortfolioOperations portfolios;

  /**
   * A constructor for the user object, only a valid user (with a username and password) can login
   * to run the simulator.
   */
  public User() {
    this.purse = 1000000000;
    this.portfolios = new PortfolioOperations();
  }

  public int getPortfolioSize(String portfolioName) {
    return this.portfolios.getPortfolioSize(portfolioName);
  }

  /**
   * Creates a new portfolio with specified name if it does not already exist.
   *
   * @param portfolioName name of portfolio
   * @return status message
   */
  public String createNewPortfolio(String portfolioName) {
    if (this.portfolioExists(portfolioName)) {
      return "Failed to create portfolio; PortfolioOperations already exists";
    } else {
      this.portfolios.createNewPortfolio(portfolioName);
      return "PortfolioOperations " + portfolioName + " successfully created";
    }
  }

  /**
   * Purchases stock for given ticker and dollar amount.
   *
   * @param tickerSymbol the symbol of the desired stock
   * @param time the time of the purchase
   * @param date date of the purchase
   * @param portfolioName the portfolio to add to
   * @param dollarAmount the amount of stock to purchase (USD)
   * @return true if transaction was successful, otherwise false
   */
  public String purchase(String tickerSymbol, String time, String date,
      String portfolioName, double dollarAmount, double commissionFeePercentage) {
    String slidingDate = date;

    if (!portfolioExists(portfolioName)) {
      return "That portfolio does not exist.";
    }
    if (dollarAmount < 0) {
      return "Cannot invest negative dollar amounts.";
    }
    //fixme
    //if portfolio is on scheduled investment, need to ff date and buy anyway
    if (this.portfolios.isScheduled(portfolioName)) {
      if (!marketOpen(slidingDate, "1000")) {
        while (!marketOpen(slidingDate, "1000")) {
          try {
            slidingDate = fastForwardDate(slidingDate);
          } catch (ParseException e) {
            return "Failed to parse date for scheduled investment";
          }
        }
      }
    }
    //elif portfolio is not on schedule, return "failed to buy"
    if (marketOpen(slidingDate, time)) {
      String stockLine = this.getStockInfoLine(tickerSymbol, slidingDate);
      int volume = this.getVolume(stockLine);

      //get the closing price, commissionFee, shares to buy, and money spent for this stock
      double closingPrice = this.getClosingPrice(stockLine);

      int sharesToBuy = (int) (dollarAmount / closingPrice);
      double approxCommissionFee = (sharesToBuy * closingPrice) / 100 * commissionFeePercentage;
      double moneySpent = sharesToBuy * closingPrice;

      if ((moneySpent + approxCommissionFee) > dollarAmount) {
        sharesToBuy--;
        moneySpent = sharesToBuy * closingPrice;
      }
      double commissionFee = (moneySpent / 100) * commissionFeePercentage;

      //if we don't have enough money
      if (moneySpent + commissionFee > this.purse) {
        return "You don't have enough money to make this purchase";
      }

      //if desired shares do not exceed total volume
      if (sharesToBuy <= volume) {
        //add shares to portfolio
        this.portfolios.updateStockEntry(
            portfolioName, tickerSymbol, sharesToBuy, commissionFee + moneySpent);
        //deduct money from purse
        this.purse -= moneySpent + commissionFee;
        return "Successfully purchased " + sharesToBuy + " shares of " + tickerSymbol + " at " +
            closingPrice + " per share.";
      }
    }
    return "The market is closed. Please select a valid business day";
  }

  /**
   * Gets stock info for a desired date in company history; returns string with readable values .
   *
   * @param tickerSymbol desired company
   * @param date yyyy-mm-dd
   * @return status message
   * @throws Exception if invalid
   */
  public String getStockInfoAtDate(String tickerSymbol, String date) throws Exception {
    String[] headings = {"TimeStamp: ", "Opening price: ", "Day's high: ", "Day's low: ",
        "Closing price: ", "Volume: "};
    //get the stock info for the given date
    String infoForDate = this.getStockInfoLine(tickerSymbol, date);
    if (!infoForDate.matches("AlphaVantage has no info for" + tickerSymbol + " on " + date)) {
      Scanner lineScanner = new Scanner(infoForDate);
      lineScanner.useDelimiter("[,]");
      String lineDate = lineScanner.next();
      StringBuilder sb = new StringBuilder();
      sb.append(headings[0] + lineDate + "\n");
      for (int i = 1; i < 6; i++) {
        sb.append(headings[i] + lineScanner.next() + "\n");
      }
      return sb.toString().trim();
    }
    throw new Exception("Invalid input. Please check your date format and ticker symbol.");
  }

  /**
   * Get the contents of desired portfolio.
   *
   * @param portfolioName name of the portfolio
   * @return a string representing the contents/existence of the portfolio
   */
  public String readPortfolio(String portfolioName) {
    return this.portfolios.readPortfolio(portfolioName);
  }

  /**
   * Gets the names of all active portfolios.
   *
   * @return portfolio names
   */
  public String getPortfolioNames() {
    return this.portfolios.getPortfolioNames();
  }

  /**
   * Gets the value of a particular portfolio on any date, for the current value, enter today's
   * date.
   *
   * @param portfolioName the portfolio name whose value we wish to know
   * @param date the date
   * @return the cumulative value of the portfolio as a string
   */
  public String getPortfolioValue(String portfolioName, String date) {
    String valueDate = date;
    if (!marketOpen(date, "1000")) {
      //rewind to get last open date
      while (!marketOpen(valueDate, "1000")) {
        try {
          valueDate = rewindDate(valueDate);
        } catch (ParseException e) {
          return "Parsing error while rewinding date getPortfolioValue()";
        }
      }
    }
    if (getPortfolioSize(portfolioName) == 0) {
      return "PortfolioOperations" + "\"" + portfolioName + "\"" + " contains no holdings";
    }
    if (portfolioExists(portfolioName)) {
      HashMap<String, Integer> holdings =
          this.portfolios.getTickerSymbolsAndSharesOwned(portfolioName);
      double totalValue = 0;
      for (String key : holdings.keySet()) {
        totalValue +=
            holdings.get(key) * this.getClosingPrice(this.getStockInfoLine(key, valueDate));
        try {
          sleep(2500);
        } catch (InterruptedException e) {
          e.printStackTrace();
        }
      }
      return Double.toString(totalValue);
    }
    return "PortfolioOperations " + portfolioName + " does not exist";
  }

  /**
   * Gets the total cost basis for the given portfolio.
   *
   * @param portfolioName name of th portfolio
   * @return cost basis in string format
   */
  public String getPortfolioCostBasis(String portfolioName) {
    if (this.portfolioExists(portfolioName)) {
      return this.portfolios.getPortfolioCostBasis(portfolioName);
    } else {
      return "That portfolio does not exist. Please check your spelling";
    }
  }

  /**
   * Gets the individual cost basis for a particular holding.
   *
   * @param portfolioName name of portfolio
   * @param tickerSymbol ticker symbol
   * @return cost basis for individual stock
   */
  public String getIndividualStockCostBasis(String tickerSymbol, String portfolioName) {
    return this.portfolios.getIndividualStockCostBasis(tickerSymbol, portfolioName);
  }

  /**
   * Invests a fixed amount of money to all holdings in this portfolio according to specified
   * weights.
   */
  public String investFixedAmount(String portfolioName, String date, double dollars,
      double commissionFeePercentage) {
    String time = "1000";
    this.portfolios.setSchedule(portfolioName, true);
    // if desired portfolio is evenly weighted
    if (this.portfolios.isEvenlyWeighted(portfolioName)) {
      //get number of holdings
      double portfolioSize = this.portfolios.getPortfolioSize(portfolioName);
      double moneyToSpendPerHolding = dollars / portfolioSize;
      for (String key : this.portfolios.getTickerSymbolsAndSharesOwned(portfolioName).keySet()) {
        this.purchase(
            key, time, date, portfolioName, moneyToSpendPerHolding, commissionFeePercentage);
        try {
          sleep(2500);
        } catch (InterruptedException e) {
          e.printStackTrace();
        }
      }
      return "Successfully invested evenly-weighted fixed amount in " + portfolioName;
    }
    // if desired portfolio has been custom weighted
    else {
      HashMap<String, Integer> weights = this.portfolios.getWeights(portfolioName);
      for (String key : weights.keySet()) {
        double moneyToSpendPerHolding = (dollars / 100) * weights.get(key);
        this.purchase(
            key, time, date, portfolioName, moneyToSpendPerHolding, commissionFeePercentage);
        try {
          sleep(2500);
        } catch (InterruptedException e) {
          e.printStackTrace();
        }
      }
      return "Successfully invested custom-weighted fixed amount in " + portfolioName;
    }
  }

  /**
   * Sets the desired weights for the holdings within this portfolio.
   */
  public String setWeights(String portfolioName, HashMap<String, Integer> weights)
      throws Exception {
    if (weights.keySet().size() == this.portfolios.getPortfolioSize(portfolioName)) {
      //weightage must == 100
      int weightage = 0;
      //check for presence of all keys (stock tickers) in portfolio
      for (String key : weights.keySet()) {
        if (!this.portfolios.portfolioContains(portfolioName, key)) {
          throw new Exception(
              "Invalid weight map: PortfolioOperations does not contain key " + "\"" + key + "\"");
        }
        weightage += weights.get(key);
      }
      if (weightage != 100) {
        throw new Exception("Weight map does not equal 100. Please try again.");
      }
      //now set weights for portfolio. go into portfolio for this.
      return this.portfolios.setWeights(weights, portfolioName);
    }
    return "Not enough stock ticker/weight pairs. Please try again";
  }


  /**
   * Helper function finds info for a stock given a specific date.
   *
   * @param tickerSymbol ticker symbol
   * @param date date of desired info
   * @return status of stock
   */
  private String getStockInfoLine(String tickerSymbol, String date) {
    String stockInfo;
    try {
      stockInfo = this.alphaVantagAPI(tickerSymbol);
    } catch (RuntimeException e) {
      //failed to scrape data
      throw e;
    }
    Scanner stockScanner = new Scanner(stockInfo);
    stockScanner.useDelimiter("\\n");
    while (stockScanner.hasNext()) {
      String lineInQuestion = stockScanner.next();
      Scanner lineScanner = new Scanner(lineInQuestion);
      lineScanner.useDelimiter("[,]");
      String lineDate = lineScanner.next();
      if (lineDate.matches(date)) {
        return lineInQuestion;
      }
    }
    return "AlphaVantage has no info for" + tickerSymbol + " on " + date;
  }

  /**
   * Gets the closing price for the provided line according to the AlphaVantage output.
   *
   * @param stockLine line of infor to parse
   * @return closing price if successful, otherwise return -1
   */
  private double getClosingPrice(String stockLine) {
    Scanner scan = new Scanner(stockLine);
    scan.useDelimiter("[,]");
    for (int i = 0; i < 4; i++) {
      scan.next();
    }
    double d = -1;
    if (scan.hasNextDouble()) {
      d = scan.nextDouble();
    }
    return d;
  }

  /**
   * Returns the volume traded for the given stock line.
   *
   * @return volume
   */
  private int getVolume(String stockLine) {
    Scanner scan = new Scanner(stockLine);
    scan.useDelimiter("[,]");
    for (int i = 0; i < 5; i++) {
      scan.next();
    }
    String volume = "";
    if (scan.hasNext()) {
      volume = scan.next();
    }
    volume = volume.replaceAll("(\\r)", "");
    return Integer.parseInt(volume);
  }

  /**
   * Helper function to determine whether or not the market is open This function accounts for.
   * remaining holidays is 2018, weekends, and assumes purchases are being made in U.S. Pacific
   * Standard Time.
   *
   * @return true if market is open
   */
  private boolean marketOpen(String date, String time) {
    String[] earlyClosingDates2018 = {"2018-11-21", "2018-12-24"};
    //return false if holiday
    if (isHoliday(date)) {
      return false;
    }
    int userTime = Integer.parseInt(time);
    //return false if outside early-close hours
    for (String earlyClose : earlyClosingDates2018) {
      if (date.matches(earlyClose)) {
        if (userTime < 600 || userTime >= 1000) {
          return false;
        }
      }
    }
    //invalid regular-season time
    if (userTime < 600 || userTime >= 1300) {
      return false;
    }
    return isWeekday(date);
  }

  /**
   * Helper function determines whether or not it is a market holiday for year 2018.
   *
   * @param date the desired date for transaction.
   * @return true if it is a holiday
   */
  private boolean isHoliday(String date) {
    String[] marketHolidays2018 = {"2018-09-03", "2018-11-22", "2018-12-25"};
    //return false if holiday
    for (String holiday : marketHolidays2018) {
      if (date.matches(holiday)) {
        return true;
      }
    }
    return false;
  }

  /**
   * Helper function returns true if the given date is a weekday.
   *
   * @return true if weekday
   */
  private boolean isWeekday(String date) {
    int year = Integer.parseInt(date.substring(0, 4));
    int month = Integer.parseInt(date.substring(5, 7));
    int day = Integer.parseInt(date.substring(8, 10));
    Calendar calendar = new GregorianCalendar(year, month - 1, day);
    int dayOfWeek = calendar.get(Calendar.DAY_OF_WEEK);
    //return false if given date is weekend
    return (Calendar.SUNDAY != dayOfWeek && Calendar.SATURDAY != dayOfWeek);
  }

  /**
   * Returns true if portfolio exists, otherwise false.
   *
   * @param portfolioName name of the portfolio
   * @return true if exists
   */
  private boolean portfolioExists(String portfolioName) {
    return this.portfolios.portfolioExists(portfolioName);
  }

  /**
   * Returns today's date in the following format: "yyyy-mm-dd".
   *
   * @return date in nyc
   */
  private String getNYCdate() {
    ZonedDateTime nycTime = ZonedDateTime.now(ZoneId.of("America/New_York"));
    return nycTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
  }

  /**
   * Returns current time in NYC with the following format: "HHmm".
   *
   * @return time in nyc (24 hr)
   */
  private int getNYCTime() {
    ZonedDateTime nycTime = ZonedDateTime.now(ZoneId.of("America/New_York"));
    return nycTime.getHour() * 100 + nycTime.getMinute();
  }

  /**
   * Rewinds the date by 1 calendar day.
   */
  private String rewindDate(String date) throws ParseException {
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);
    Date oldDate = sdf.parse(date);
    Calendar cal = Calendar.getInstance();
    cal.setTime(oldDate);
    cal.add(Calendar.DATE, -1);
    Date newDate = cal.getTime();
    Format formatter = new SimpleDateFormat("yyyy-MM-dd");
    return formatter.format(newDate);
  }

  /**
   * Fast forwards day by one calendar day.
   */
  private String fastForwardDate(String date) throws ParseException {
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);
    Date oldDate = sdf.parse(date);
    Calendar cal = Calendar.getInstance();
    cal.setTime(oldDate);
    cal.add(Calendar.DATE, 1);
    Date newDate = cal.getTime();
    Format formatter = new SimpleDateFormat("yyyy-MM-dd");
    return formatter.format(newDate);
  }


  /**
   * Logs in to AlphaVantage; retrieves stock info for given parameter.
   *
   * @param tickerSymbol ticker symbol
   * @return String displaying stock info
   */
  private String alphaVantagAPI(String tickerSymbol) throws RuntimeException {
    String apiKey = "AD7JAZ51CFHTA74D";
    String stockSymbol = tickerSymbol; //ticker symbol
    URL url = null;

    try {
      /*
      create the URL. This is the query to the web service. The query string
      includes the type of query (DAILY stock prices), stock symbol to be
      looked up, the API key and the format of the returned
      data (comma-separated values:csv). This service also supports JSON
      which you are welcome to use.
       */
      url = new URL("https://www.alphavantage"
          + ".co/query?function=TIME_SERIES_DAILY"
          + "&outputsize=full"
          + "&symbol"
          + "=" + stockSymbol + "&apikey=" + apiKey + "&datatype=csv");
    } catch (MalformedURLException e) {
      throw new RuntimeException("the alphavantage API has either changed or "
          + "no longer works\n");
    }

    InputStream in = null;
    StringBuilder output = new StringBuilder();

    try {
      /*
      Execute this query. This returns an InputStream object.
      In the csv format, it returns several lines, each line being separated
      by commas. Each line contains the date, price at opening time, highest
      price for that date, lowest price for that date, price at closing time
      and the volume of trade (no. of shares bought/sold) on that date.
       */

      in = url.openStream();
      int b;

      while ((b = in.read()) != -1) {
        output.append((char) b);
      }
    } catch (IOException e) {
      throw new IllegalArgumentException("No price data found for " + stockSymbol + "\n");
    }
    return output.toString();
  }
}