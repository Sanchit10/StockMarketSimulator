package model;

import java.util.HashMap;

/**
 * The model for our stock market simulator, it allows users to login and make their custom.
 * portfolios and get stock info,purchase stock back in time or in current time and add it to their
 * custom portfolio, examine the portfolio's composition among other things
 */
public interface SMSimulatorModel {

  /**
   * Returns the size of the desired portfolio belonging to current user.
   *
   * @return size of desired portfolio
   */
  int getPortfolioSize(String portfolioName);

  /**
   * Returns true if username and password are valid, otherwise false.
   *
   * @param userName user name
   * @param password password
   * @return true if successful
   */
  boolean login(String userName, String password);


  /**
   * Creates new user portfolio.
   *
   * @param portfolioName name of portfolio
   * @return status message
   */
  String createNewPortfolio(String portfolioName);

  /**
   * Purchases stock for given ticker and dollar amount.
   *
   * @param tickerSymbol the symbol of the desired stock
   * @param time the time of the purchase
   * @param date date of the purchase
   * @param portfolioName the portfolio to add to
   * @param dollarAmount the amount of stock to purchase (USD)
   * @param commissionFeePercentage the amount in USD to factor in for sales costs
   * @return true if transaction was successful, otherwise false
   */
  String purchase(String tickerSymbol, String time, String date,
      String portfolioName, double dollarAmount, double commissionFeePercentage);

  /**
   * Gets stock info for a desired date in company history.
   *
   * @param tickerSymbol desired company
   * @param date yyyy-mm-dd
   * @return stock info
   * @throws Exception if invalid
   */
  String getStockInfoAtDate(String tickerSymbol, String date) throws Exception;

  /**
   * Get the contents of desired portfolio.
   *
   * @param portfolioName name of portfolio
   * @return contents of portfolio
   */
  String readPortfolio(String portfolioName);

  /**
   * Gets the names of user's portfolios.
   *
   * @return String representations of user portfolio names, seperated by newlines.
   */
  String getPortfolioNames();

  /**
   * Get cost basis for desired portfolio.
   *
   * @param portfolioName name of portfolio
   * @return portfolio cost basis
   */
  String getPortfolioCostBasis(String portfolioName);

  /**
   * Get cost basis for desired stock.
   *
   * @param portfolioName name of portfolio
   * @param tickerSymbol ticker symbol
   * @return stock cost basis
   */
  String getStockCostBasis(String tickerSymbol, String portfolioName);

  /**
   * Gets the total value for specified portfolio at a given date.
   *
   * @param portfolioName name of portfolio
   * @param date date of value
   * @return total value of portfolio
   */
  String getValue(String portfolioName, String date);

  /**
   * Invests a fixed amount to desired portfolio. If weights have not been specified by user, then
   * the dollar amount (USD) will be distributed equally. In the case where decimals are
   * non-terminating (i.e. 1/3 split among holdings), the function will return an error message.
   *
   * @param portfolioName name of portfolio to invest in
   * @param startDate date to start interval investment
   * @param endDate date to end interval investment
   * @param interval number of days between investments
   * @param dollars amount (USD) to spend on stocks
   * @param commissionFeePercentage amount (USD) charged for commission
   * @return string indicating the success/failure of the transaction
   */
  String investFixedAmount(String portfolioName, String startDate, String endDate, int interval,
      double dollars, double commissionFeePercentage) throws Exception;

  /**
   * Sets the desired weights for the given portfolio.
   *
   * @param portfolioName name of the portfolio to distribute weights to
   * @param weights a Hashmap of the following key/value pair: K: String representing the ticker
   * @throws Exception if invalid weights are given
   */
  String setWeights(String portfolioName, HashMap<String, Integer> weights) throws Exception;


}