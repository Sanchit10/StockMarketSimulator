package view;

import java.awt.event.ActionListener;


/**
 * An interface for the view of our stock market simulator, this view interacts with the model in a.
 * principled way i.e. through the controller and takes care of all the appendable objects that are
 * used for displaying the results of user inputs out in the console as a text based view
 */
public interface View {


  /**
   * A method that displays the welcome message for our simulator.
   */
  void welcomeMessage();

  /**
   * A method that asks the user to enter his/her username.
   */
  void userNamePrompt();

  /**
   * A method that asks the user to enter his/her password to login into the simulator.
   */
  void passwordPrompt();

  /**
   * A method that appends the login success message to be displayed as text message in the view.
   *
   * @param out the appendable object from the model through the controller
   * @return the appendable object
   */
  Appendable userLoginSuccess(Appendable out);

  /**
   * A method that appends the login failure message to be displayed as text message in the view.
   *
   * @param out the appendable object from the model through the controller
   * @return the appendable object
   */
  Appendable loginFailure(Appendable out);

  /**
   * A method that displays the main menu of our stock market simulator, the main menu shows a list.
   * of all the functionalities available to a user of our stock market simulator
   */
  void mainMenu();

  /**
   * A method that appends the quit message to be displayed as text message in the view.
   *
   * @param out the appendable object from the model through the controller
   * @return the appendable object
   */
  Appendable quitMessage(Appendable out);

  /**
   * A method that appends the quit message to be displayed as text message in the view.
   *
   * @param out the appendable object from the model through the controller
   * @return the appendable object
   */
  Appendable invalidCommand(Appendable out);

  /**
   * A method that displays the confirmation message upon the successful creation of a new.
   * portfolio by the user
   */
  void createPortfolioMsg();

  /**
   * A method that creates a new portfolio for the user and appends the result of the method as a.
   * text message in the view
   *
   * @param out the appendable object from the model through the controller
   * @return the appendable object
   */
  Appendable successfulPortfolioCreation(Appendable out);

  /**
   * A method that displays a text message before fetching the existing list of custom portfolios.
   * by the user
   */
  void listMsg();

  /**
   * A method that gets the existing list of custom portfolios for a specific user.
   *
   * @param out the appendable object from the model through the controller
   * @return the appendable object
   */
  Appendable getPortfolioList(Appendable out);

  /**
   * A method that displays the instruction before getting information about a particular stock.
   */
  void getStockInfo();

  /**
   * A method that asks the user to enter the date for which he/she wishes to get the stock info.
   */
  void enterDate();

  /**
   * A method that interacts with the model and appends the stock info as text info in the view.
   *
   * @param out the appendable object from the model through the controller
   * @return the appendable object
   */
  Appendable getStockInfo1(Appendable out);

  /**
   * A method that prints a message informing the user about an invalid input.
   */
  void invalidInfo();

  /**
   * A method that asks user to enter the ticker symbol of the stock whose info they wish to get.
   */
  void addSymbol();

  /**
   * A method that asks the user to enter the date for which he wishes to get the stock info.
   */
  void addDate();

  /**
   * A method that asks the user to enter the time when the wish to purchase shares of a particular.
   * stock
   */
  void enterTime();

  /**
   * A method that asks the user to enter the name of the portfolio wherein he/she wishes to add.
   * the current investment
   */
  void enterPortfolioName();

  /**
   * A method that asks the user to specify the amount he/she wishes to invest.
   */
  void enterAmount();

  /**
   * A method that asks the user the name of the portfolio whose composition is to be examined.
   */
  void enterName();

  /**
   * A method that displays an error message when an invalid or incorrect portfolio name is.
   * entered
   */
  void errorMsg();

  /**
   * A method that gets the info of a stock at a particular day, current or in the past and the.
   * time of the day and returns the value of a particular share of stock for the user
   *
   * @param out the appendable object from the model through the controller
   * @return the appendable object
   */
  Appendable getInfoOnAday(Appendable out);

  /**
   * A method that returns an error message if any of the input to get the stock info at a.
   * particular day on a particular time is incorrect or invalid
   *
   * @param out the appendable object from the model through the controller
   * @return the appendable object
   */
  Appendable errMsg1(Appendable out);

  /**
   * A method that reads the contents of a custom portfolio and appends those contents as text info.
   * in the view
   *
   * @param out the appendable object from the model through the controller
   * @return the appendable object
   */
  Appendable readPortfolio(Appendable out);

  /**
   * A method that asks the user about the name of the portfolio whose cost per basis they wish to.
   * evaluate
   */
  void costPerBasisPortfolio();

  /**
   * A method that gets the cost per basis of a custom portfolio created by the user and appends.
   * the value as text information in the view
   *
   * @param out the appendable object from the model through the controller
   * @return the appendable object
   */
  Appendable getCostPerBasisPortfolio(Appendable out);

  /**
   * A method that displays an error message when an invalid or incorrect portfolio name is.
   * entered
   */
  void errMsg2();


  /**
   * A method that asks the user to enter in a ticker symbol.
   */
  void addSymbol1();

  /**
   * A method that asks the user to enter in the portfolio name.
   */
  void addPortfolioName2();

  /**
   * A method that is used to get the cost per basis for a stock.
   *
   * @param out appendable object containing information about a particular stock in the portfolio
   * @return the appendable object
   */
  Appendable getStockCostBasis(Appendable out);

  /**
   * An error message.
   */
  void errMsg3();

  /**
   * A method that asks for the portfolio name yet again.
   */
  void enterName2();

  /**
   * A method that gets the net worth of a portfolio at a given date.
   *
   * @param out appendable object consisting of information about the net worth of a portfolio
   * @return the appendable object
   */
  Appendable getNetworth(Appendable out);

  /**
   * A method that asks the user to enter in the date in yyyy-mm-dd format.
   */
  void addDate1();

  /**
   * An error message for an invalid input by the user.
   */
  void errMsg4();

  /**
   * A method that asks the user to specify the commission fee for a transaction.
   */
  void commissionPercentage();

  /**
   * A method that asks the user to specify custom weights for an existing portfolio.
   */
  void customWeights();

  /**
   * A method that allows the user to specify a custom weight distribution for an existing.
   * portfolio
   *
   * @param out the appendable object that is used by the controller to interact with the model
   * @return the appendable object
   */
  Appendable addCustomWeights(Appendable out);

  /**
   * A method that asks the for the portfolio name for a functionality.
   */
  void enterName3();

  /**
   * A method that asks for the start date for the fixed investment strategy.
   */
  void enterStartDate();

  /**
   * A method that asks for the end date for the fixed investment strategy.
   */
  void enterEndDate();

  /**
   * A method that asks for the duration after which the fixed investment should repeat.
   */
  void enterDuration();

  /**
   * A method that asks the user to specify the fixed investment amount.
   */
  void enterFixedInvestment();

  /**
   * A method that displays an error message.
   */
  void errMsg5();

  /**
   * A method that offers the user to revert back to the main menu of the simulator if one or more.
   * of the inputs happen to be invalid
   */
  void revertToMainMenu();

  /**
   * A method that offers the user to enter the custom weight distribution of stocks for an.
   * existing portfolio
   */
  void enterWeightDistribution();


  /**
   * A method that sets action listeners for various button click events for the GUI.
   *
   * @param listner the listener for a particular action i.e. button click
   */
  void setListener(ActionListener listner);

  /**
   * A GUI frame that shows the main menu of our stock market simulator.
   */
  void mainMenuFrame();

  /**
   * A GUI frame that gives users to create a new custom portfolio.
   */
  void createNewPortfolioFrame();

  /**
   * A GUI frame that returns the status of creating a new portfolio as a String.
   *
   * @param s status confirmation message for portfolio creation
   */
  void portfolioCreationFrame(String s);


  /**
   * A GUI frame that deals with the list of existing portfolios for a user.
   *
   * @param s status confirmation message for list of portfolios
   */
  void listFrame(String s);

  /**
   * A GUI frame to purchase stock.
   */
  void stockPurchaseForm();

  /**
   * A GUI frame that deals with the purchase of new stock.
   *
   * @param s status confirmation message of a purchase
   */
  void showPurchaseFrame(String s);

  /**
   * A GUI frame to deal with the setting weights for a fixed investment strategy.
   */
  void weightDistributionConfirmation();

  /**
   * A GUI frame to deal with the setting weights for a fixed investment strategy.
   */
  void showWeightPurchaseForm();

  /**
   * A GUI frame to deal with getting the name of a portfolio for a specific investment.
   */
  void getPfNameFrame();

  /**
   * A GUI frame that gives the status of setting weights for a specific portfolio.
   *
   * @param s status confirmation of setting weights
   */
  void setWeightsConfirmationFrame(String s);

  /**
   * A GUI frame for when the weights are not set.
   */
  void weightsNotSetFrame();

  /**
   * A GUI frame for dealing with the fixed investment strategy for a portfolio.
   */
  void getFixedInvestmentForm();

  /**
   * A GUI frame to calculate the cost basis of a portfolio.
   */
  void costBasisFrame();

  /**
   * A GUI that gets the cost basis for a given portfolio.
   *
   * @param s returns the status as the cost basis of a portfolio
   */
  void showCostBasisFrame(String s);

  /**
   * A GUI frame that reads the portfolio contents of a given portfolio.
   */
  void portfolioReadframe();

  /**
   * A GUI frame that gets the contents of a portfolio.
   *
   * @param s the contents as a string
   */
  void getPortfolioContents(String s);

  /**
   * A GUI frame that for confirmation of a fixed investment strategy.
   *
   * @param s confirms whether the fixed investment was successful or not
   */
  void confirmFixedInvestment(String s);

  /**
   * A GUI frame that gets the value of a portfolio.
   */
  void getPortfolioVal();

  /**
   * A GUI frame that gets the value of a portfolio.
   *
   * @param s value of the portfolio as a string
   */
  void getPortfolioValFrame1(String s);




}
