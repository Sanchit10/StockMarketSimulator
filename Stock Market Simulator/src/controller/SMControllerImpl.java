package controller;


import java.io.IOException;
import java.util.HashMap;
import java.util.Scanner;
import model.SMSimulatorModel;
import view.View;

/**
 * A class that implements the controller interface, it consists of a constructor that takes in a.
 * readable and appendable object that helps in the unit testing of the controller that acts as the
 * mediator between the user inputs and the model for our simulator
 */
public class SMControllerImpl implements SMController {


  final Readable in;

  final Appendable out;

  /**
   * A constructor that takes in the input stream,output stream for the inputs and outputs.
   * respectively
   *
   * @param rd the input stream
   * @param ap the output stream
   */
  public SMControllerImpl(Readable rd, Appendable ap) {
    if (rd == null) {
      throw new IllegalArgumentException("Readable object is null");
    }
    if (ap == null) {
      throw new IllegalArgumentException("Appendable object is null");
    }
    this.in = rd;
    this.out = ap;
  }

  @Override
  public void runStockMarketSimulation(SMSimulatorModel model, View myView) throws IOException {

    myView.welcomeMessage();
    Scanner myScan = new Scanner(this.in);

    while (true) {

      myView.userNamePrompt();

      String userName = myScan.nextLine();

      myView.passwordPrompt();
      String password = myScan.nextLine();

      try {
        boolean login = model.login(userName, password);
        if (login) {
          myView.userLoginSuccess(this.out.append("Hi " + userName + " welcome back!!\n"));
        }
        break;
      } catch (IllegalArgumentException e) {
        myView.loginFailure(this.out.append(e.getMessage()));
      }


    }
    helper(model, myScan, myView);


  }


  /**
   * A helper function for the runStockMarketSimulation function.
   *
   * @param model the Stock Market Simulation model
   * @param myScan the scanner object required to take in input from the user
   * @param myView handles all the appendable objects for text output in the console
   * @throws IOException for an invalid input which is appropriately handled by the program
   */
  private void helper(SMSimulatorModel model, Scanner myScan, View myView) throws IOException {
    while (true) {

      myView.mainMenu();

      String input = myScan.nextLine();

      switch (input) {
        case "1":
          helper1(model, myScan, myView);
          break;
        case "2":
          helper2(model, myView);
          break;

        case "3":
          helper3(model, myScan, myView);
          break;

        case "4":
          helper4(model, myScan, myView);
          break;

        case "5":
          helper5(model, myScan, myView);
          break;

        case "6":
          helper6(model, myScan, myView);
          break;

        case "7":
          helper7(model, myScan, myView);
          break;

        case "8":
          helper8(model, myScan, myView);
          break;

        case "9":
          helper9(model, myScan, myView);
          break;

        case "q":

          myView.quitMessage(this.out.append("Quitting simulator.Goodbye!\n"));
          return;

        case "Q":
          myView.quitMessage(this.out.append("Quitting simulator.Goodbye!\n"));
          return;

        default:
          myView.invalidCommand(this.out.append("Please enter a valid command\n"));


      }


    }
  }

  /**
   * A helper function for the runStockMarketSimulation function.
   *
   * @param model the Stock Market Simulation model
   * @param myScan the scanner object required to take in input from the user
   * @param myView handles all the appendable objects for text output in the console
   * @throws IOException for an invalid input which is appropriately handled by the program
   */
  private void helper1(SMSimulatorModel model, Scanner myScan, View myView) throws IOException {

    myView.createPortfolioMsg();
    String portfolioName = myScan.nextLine();
    myView.successfulPortfolioCreation(this.out.append(model.createNewPortfolio(portfolioName)));

  }

  /**
   * A helper function for the runStockMarketSimulation function.
   *
   * @param model the Stock Market Simulation model
   * @param myView handles all the appendable objects for text output in the console
   * @throws IOException for an invalid input which is appropriately handled by the program
   */
  private void helper2(SMSimulatorModel model, View myView) throws IOException {

    myView.listMsg();
    myView.getPortfolioList(
        this.out.append(model.getPortfolioNames()));
  }

  /**
   * A helper function for the runStockMarketSimulation function.
   *
   * @param model the Stock Market Simulation model
   * @param myScan the scanner object required to take in input from the user
   * @param myView handles all the appendable objects for text output in the console
   * @throws IOException for an invalid input which is appropriately handled by the program
   */
  private void helper3(SMSimulatorModel model, Scanner myScan, View myView) throws IOException {

    myView.getStockInfo();
    String tickerSymbol = myScan.nextLine();

    myView.enterDate();
    String date = myScan.nextLine();
    try {
      myView.getStockInfo1(this.out.append(model.getStockInfoAtDate(tickerSymbol, date)));

    } catch (Exception e) {
      myView.invalidInfo();

    }

  }

  /**
   * A helper function for the runStockMarketSimulation function.
   *
   * @param model the Stock Market Simulation model
   * @param myScan the scanner object required to take in input from the user
   * @param myView handles all the appendable objects for text output in the console
   * @throws IOException for an invalid input which is appropriately handled by the program
   */

  private void helper4(SMSimulatorModel model, Scanner myScan, View myView) throws IOException {

    myView.addSymbol();
    String symbol = myScan.nextLine();

    myView.enterTime();
    String time = myScan.nextLine();

    myView.addDate();
    String date1 = myScan.nextLine();

    myView.enterPortfolioName();
    String name = myScan.nextLine();

    myView.enterAmount();
    String amount = myScan.nextLine();
    Double amount1 = Double.parseDouble(amount);

    myView.commissionPercentage();
    String amount2 = myScan.nextLine();
    Double amount3 = Double.parseDouble(amount2);
    try {
      myView.getInfoOnAday(
          this.out.append(model.purchase(symbol, time, date1, name, amount1, amount3)));

    } catch (Exception e) {
      myView.errMsg1(this.out.append(e.getMessage()));

    }
  }

  /**
   * A helper function for the runStockMarketSimulation function.
   *
   * @param model the Stock Market Simulation model
   * @param myScan the scanner object required to take in input from the user
   * @param myView handles all the appendable objects for text output in the console
   * @throws IOException for an invalid input which is appropriately handled by the program
   */

  private void helper5(SMSimulatorModel model, Scanner myScan, View myView) throws IOException {

    myView.enterName();
    String name1 = myScan.nextLine();
    try {
      myView.readPortfolio(this.out.append(model.readPortfolio(name1)));

    } catch (Exception e) {

      myView.errorMsg();
    }
  }


  /**
   * A helper function for the runStockMarketSimulation function.
   *
   * @param model the Stock Market Simulation model
   * @param myScan the scanner object required to take in input from the user
   * @param myView handles all the appendable objects for text output in the console
   * @throws IOException for an invalid input which is appropriately handled by the program
   */

  private void helper6(SMSimulatorModel model, Scanner myScan, View myView) throws IOException {

    myView.costPerBasisPortfolio();
    String name2 = myScan.nextLine();
    try {
      myView.getCostPerBasisPortfolio(this.out.append(model.getPortfolioCostBasis(name2)));

    } catch (Exception e) {

      myView.errMsg2();
    }
  }


  /**
   * A helper function for the runStockMarketSimulation function.
   *
   * @param model the Stock Market Simulation model
   * @param myScan the scanner object required to take in input from the user
   * @throws IOException for an invalid input which is appropriately handled by the program
   */
  private void helper7(SMSimulatorModel model, Scanner myScan, View myView) throws IOException {

    myView.addSymbol1();
    String symbol1 = myScan.nextLine();

    myView.addPortfolioName2();
    String name3 = myScan.nextLine();
    try {
      myView.getStockCostBasis(this.out.append(model.getStockCostBasis(symbol1, name3)));

    } catch (Exception e) {

      myView.errMsg3();
    }
  }

  /**
   * A helper function for the runStockMarketSimulation function.
   *
   * @param model the Stock Market Simulation model
   * @param myScan the scanner object required to take in input from the user
   * @throws IOException for an invalid input which is appropriately handled by the program
   */
  private void helper8(SMSimulatorModel model, Scanner myScan, View myView) throws IOException {
    myView.enterName2();

    String name4 = myScan.nextLine();

    myView.addDate1();
    String date2 = myScan.nextLine();
    try {
      myView.getNetworth(this.out.append(model.getValue(name4, date2)));

    } catch (Exception e) {

      myView.errMsg4();
    }

  }

  /**
   * A helper function for the runStockMarketSimulation function.
   *
   * @param model the Stock Market Simulation model
   * @param myScan the scanner object required to take in input from the user
   * @throws IOException for an invalid input which is appropriately handled by the program
   */
  private void helper9(SMSimulatorModel model, Scanner myScan, View myView) {
    HashMap<String, Integer> myMap = new HashMap<>();
    myView.customWeights();
    String decision1 = myScan.nextLine().toLowerCase();
    if (decision1.equals("yes")) {

      myView.enterName();
      String name = myScan.nextLine();
      int portfolioSize = model.getPortfolioSize(name);

      for (int i = 1; i <= portfolioSize; i++) {
        System.out
            .println("Enter ticker symbol for the " + i + " stock in your existing portfolio");
        String symbol = myScan.nextLine();
        myView.enterWeightDistribution();
        String weight = myScan.nextLine();
        int finalWeight = Integer.parseInt(weight);
        myMap.put(symbol, finalWeight);
      }

      try {
        myView.addCustomWeights(this.out.append(model.setWeights(name, myMap)));

      } catch (Exception e) {
        myView.errMsg5();
        myView.revertToMainMenu();
        myMap.clear();
        String input = myScan.nextLine().toUpperCase();
        if (input.equals("M")) {
          return;


        }
      }

    }
    helper10(model, myScan, myView);
  }

  /**
   * A helper function for the runStockMarketSimulation function.
   *
   * @param model the Stock Market Simulation model
   * @param myScan the scanner object required to take in input from the user
   * @throws IOException for an invalid input which is appropriately handled by the program
   */
  private void helper10(SMSimulatorModel model, Scanner myScan, View myView) {

    myView.enterName3();
    String name = myScan.nextLine();
    myView.enterStartDate();
    String startDate = myScan.nextLine();
    myView.enterEndDate();
    String endDate = myScan.nextLine();
    myView.enterDuration();
    String days1 = myScan.nextLine();
    int finalDays = Integer.parseInt(days1);
    myView.enterFixedInvestment();
    String amount = myScan.nextLine();
    Double finalAmount = Double.parseDouble(amount);
    myView.commissionPercentage();
    String commissionFee = myScan.nextLine();
    Double finalCommissionFee = Double.parseDouble(commissionFee);

    try {
      this.out.append(model.investFixedAmount(name, startDate, endDate, finalDays,
          finalAmount, finalCommissionFee));
    } catch (Exception e) {
      myView.errMsg5();
      myView.revertToMainMenu();
      String input = myScan.nextLine().toUpperCase();
      if (input.equals("M")) {
        return;
      }
    }
  }


}


