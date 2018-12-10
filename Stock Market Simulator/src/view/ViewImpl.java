package view;


import java.awt.event.ActionListener;


/**
 * A class that implements the view interface. This class is responsible for dealing with all the.
 * appendable objects as well as the instruction statements for the user in the console by
 * interacting with the model only via the controller in a principled way
 */
public class ViewImpl implements View {


  @Override
  public void welcomeMessage() {
    System.out.println("\nWelcome to our stock market "
        + "simulator!\n");
  }

  @Override
  public void userNamePrompt() {
    System.out.println("Please enter your Username to continue:");
  }

  @Override
  public void passwordPrompt() {
    System.out.println("Please enter your password:");
  }

  @Override
  public Appendable userLoginSuccess(Appendable out) {
    return out;
  }

  @Override
  public Appendable loginFailure(Appendable out) {
    return out;
  }

  @Override
  public void mainMenu() {
    System.out.print("\n\nWhat would you like to do today?\n");
    System.out.print("Enter 1 to create a new portfolio\n");
    System.out.print("Enter 2 to view all your portfolios\n");
    System.out.print("Enter 3 to get the stock info of your choice for any given date\n");
    System.out
        .print("Enter 4 to buy shares of a stock and add it to any portfolio of your choice\n");
    System.out.print("Enter 5 to read one of your existing portfolios\n");
    System.out.print("Enter 6 to get the cost per basis of any of your portfolios\n");
    System.out.print(
        "Enter 7 to get the cost per basis of any stock in any of your existing portfolios\n");
    System.out.print(
        "Enter 8 to get the net worth of your portfolio for any given day in the stock market."
            + "If you wish to know the current value, please enter today's date\n");
    System.out.println("Enter 9 to setup an existing portfolio in accordance with a high-level "
        + "investment strategy (Dollar-cost averaging)");
    System.out.print("Enter q/Q to quit the simulator\n");
  }

  @Override
  public Appendable quitMessage(Appendable out) {
    return out;
  }

  @Override
  public Appendable invalidCommand(Appendable out) {
    return out;
  }

  @Override
  public void createPortfolioMsg() {
    System.out.println("Enter the name of your new portfolio");
  }

  @Override
  public Appendable successfulPortfolioCreation(Appendable out) {
    return out;
  }

  @Override
  public void listMsg() {
    System.out.println("Your list of portfolios is as follows:");
  }

  @Override
  public Appendable getPortfolioList(Appendable out) {
    return out;
  }

  @Override
  public void getStockInfo() {
    System.out.println("Enter the name of the stock you wish to view(ticker symbol)");
  }

  @Override
  public void enterDate() {
    System.out.println(
        "Enter the date (in yyyy-mm-dd format) for which you wish to obtain this stock info."
            + "Enter today's date if you wish to know the current value of stock");
  }

  @Override
  public Appendable getStockInfo1(Appendable out) {
    return out;
  }

  @Override
  public void invalidInfo() {
    System.out.println("Invalid TickerSymbol/date/date-format.Please try again");
  }

  @Override
  public void addSymbol() {
    System.out.println("Please enter the ticker symbol of the stock you wish to purchase");
  }

  @Override
  public void addDate() {
    System.out.println("Enter the date (in yyyy-mm-dd format) for when you wish to purchase shares"
        + " of this stock. Enter today's date if you wish to buy it today!");
  }

  @Override
  public void enterTime() {
    System.out.println("Please enter the time of the day when you wish to purchase the stock"
        + "(24 hour format,PST)");
  }

  @Override
  public void enterPortfolioName() {
    System.out.println("Enter the name of the portfolio wherein you wish to "
        + "add in this investment");
  }


  @Override
  public void enterAmount() {
    System.out.println("Enter the amount(dollars and cents) that you wish to "
        + "spend.You need not add the '$' "
        + "to the amount you wish to spend");
  }

  @Override
  public void enterName() {
    System.out.println("Enter the name of the portfolio you wish to examine");
  }

  @Override
  public void errorMsg() {
    System.out.println("The portfolio name does not exist or it is invalid");
  }

  @Override
  public Appendable getInfoOnAday(Appendable out) {
    return out;
  }

  @Override
  public Appendable errMsg1(Appendable out) {
    return out;
  }

  @Override
  public Appendable readPortfolio(Appendable out) {
    return out;
  }

  @Override
  public void costPerBasisPortfolio() {
    System.out.println("Enter the name of the portfolio whose cost per"
        + " basis you wish to get evaluated");
  }

  @Override
  public Appendable getCostPerBasisPortfolio(Appendable out) {
    return out;
  }

  @Override
  public void errMsg2() {
    System.out.println("The portfolio name does not exist or it is invalid");
  }

  @Override
  public void addSymbol1() {
    System.out.println("Enter the ticker symbol of the stock whose "
        + "cost per basis you wish to examine");
  }

  @Override
  public void addPortfolioName2() {
    System.out.println("Enter the name of the portfolio wherein you wish to examine the"
        + " stock you just mentioned");
  }

  @Override
  public Appendable getStockCostBasis(Appendable out) {
    return out;
  }

  @Override
  public void errMsg3() {
    System.out.println("The ticker symbol/portfolio names are invalid");
  }

  @Override
  public void enterName2() {
    System.out.println("Enter the name of the portfolio whose net worth you wish to know");
  }

  @Override
  public Appendable getNetworth(Appendable out) {
    return out;
  }

  @Override
  public void errMsg4() {
    System.out.println("Invalid portfolio name or invalid date");
  }

  @Override
  public void addDate1() {
    System.out.println("Enter the date (in yyyy-mm-dd format) for when you wish "
        + "to know the market value "
        + "for your portfolio. Enter today's date for the current market value");
  }

  @Override
  public void commissionPercentage() {
    System.out.println("Enter the commision percentage for this transaction. "
        + "You are not required to enter the value with '%' symbol");
  }

  @Override
  public void customWeights() {
    System.out.println("Do you wish to enter a custom weight distribution for different "
        + "stocks in your portfolio. Please answer as a yes or no");
  }

  @Override
  public Appendable addCustomWeights(Appendable out) {
    return out;
  }

  @Override
  public void enterName3() {
    System.out.println("Enter the name of the portfolio you wish to use "
        + "for this high-level investment strategy");

  }

  @Override
  public void enterStartDate() {
    System.out.println("Enter the start date for your fixed investment strategy"
        + " in yyyy-mm-dd format");
  }

  @Override
  public void enterEndDate() {
    System.out.println("Enter the end date for your fixed investment strategy "
        + "in yyyy-mm-dd format. If you "
        + "do not wish to specify an end date , please type in 'NA'");
  }

  @Override
  public void enterDuration() {
    System.out.println("Please specify the number of days as the interval for "
        + "this periodic investment");
  }

  @Override
  public void enterFixedInvestment() {
    System.out.println("Please enter the amount you wish to invest periodically");
  }

  @Override
  public void errMsg5() {
    System.out.println("One or more of the values inputted by you happens to be invalid");
  }

  @Override
  public void revertToMainMenu() {
    System.out.println("Enter M for main menu");
  }

  @Override
  public void enterWeightDistribution() {
    System.out.println("Enter the weight for this stock");
  }

  @Override
  public void setListener(ActionListener listner) {
    //default value for a GUI method
  }

  @Override
  public void mainMenuFrame() {
    //default value for a GUI method
  }

  @Override
  public void createNewPortfolioFrame() {
    //default value for a GUI method
  }

  @Override
  public void portfolioCreationFrame(String s) {
    //default value for a GUI method
  }


  @Override
  public void listFrame(String s) {
    //default value for a GUI method
  }

  @Override
  public void stockPurchaseForm() {
    //default value for a GUI method
  }

  @Override
  public void showPurchaseFrame(String s) {
    //default value for a GUI method
  }

  @Override
  public void weightDistributionConfirmation() {
    //default value for a GUI method
  }

  @Override
  public void showWeightPurchaseForm() {
    //default value for a GUI method
  }

  @Override
  public void getPfNameFrame() {
    //default value for a GUI method
  }

  @Override
  public void setWeightsConfirmationFrame(String s) {
    //default value for a GUI method
  }

  @Override
  public void weightsNotSetFrame() {
    //default value for a GUI method
  }

  @Override
  public void getFixedInvestmentForm() {
    //default value for a GUI method
  }

  @Override
  public void costBasisFrame() {
    //default value for a GUI method
  }

  @Override
  public void showCostBasisFrame(String s) {
    //default value for a GUI method
  }

  @Override
  public void portfolioReadframe() {
    //default value for a GUI method
  }

  @Override
  public void getPortfolioContents(String s) {
    //default value for a GUI method
  }

  @Override
  public void confirmFixedInvestment(String s) {
    //default value for a GUI method
  }

  @Override
  public void getPortfolioVal() {
    //default value for a GUI method
  }

  @Override
  public void getPortfolioValFrame1(String s) {
    //default value for a GUI method
  }


}
