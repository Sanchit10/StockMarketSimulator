package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.HashMap;
import model.SMSimulatorModel;
import view.GUI;
import view.View;

/**
 * A class for the controller that interacts with the GUI(view) and model of our simulator in a.
 * principled way
 */
public class SMControllerGUI implements SMController, ActionListener {

  private SMSimulatorModel myModel;
  private View myView;

  /**
   * A constructor that and takes in the model and view as the arguments and sets listener to deal.
   * with button clicks in the GUI
   *
   * @param m the model of our simulator
   * @param v the view of our simulator
   */
  public SMControllerGUI(SMSimulatorModel m, View v) {
    myModel = m;
    myView = v;
    myView.setListener(this);
  }


  @Override
  public void runStockMarketSimulation(SMSimulatorModel model, View myView) throws IOException {
    // default method for running the text based view of our simulator
  }

  /**
   * A method that fetches the respective button click action from the view, and tells the model. to
   * respond accordingly, retrieves information from the model and instructs the view to display the
   * same
   *
   * @param e the event of a button click in the GUI
   */
  @Override
  public void actionPerformed(ActionEvent e) {
    switch (e.getActionCommand()) {
      case "Login!":

        helper1();
        break;

      case "Create new portfolio":
        helper2();
        break;

      case "Successfully created new portfolio!":
        helper3();
        break;

      case "PortfolioOperations Created":
        helper4();
        break;

      case "View List":
        helper5();
        break;

      case "Back to main menu1":
        helper6();
        break;

      case "Purchase":
        helper7();
        break;

      case "Buy Stock":
        helper8();
        break;

      case "Back to main menu2":
        helper9();
        break;

      case "Fixed Investment":
        helper10();
        break;

      case "Decision":
        helper11();
        break;

      case "Decision1":
        helper12();
        break;

      case "Set Weights":
        helper13();
        break;

      case "Weights Failed":
        helper14();
        break;

      case "Fixed Investment Form1":
        helper15();
        break;
      case "Cost basis":
        helper16();
        break;

      case "Calculate1":
        helper17();
        break;

      case "Back to main menu 3":
        helper18();
        break;

      case "Read portfolio":
        helper19();
        break;

      case "Read1":
        helper20();
        break;

      case "Back to main menu 4":
        helper21();
        break;

      case "Complete":
        helper22();
        break;

      case "Back to main menu 5":
        helper23();
        break;

      case "Get val":
        helper23();
        break;

      case "Get the value":
        helper24();
        break;

      case "Back to main menu 6":
        helper25();
        break;

      case "Exit":
        System.exit(0);
        break;

      default:
        myView.mainMenuFrame();


    }

  }

  /**
   * A helper function for the controller of our program.
   */
  private void helper1() {
    String s = GUI.USER_NAME.getText();
    String password = GUI.GET_PASSWORD.getText();
    Boolean checkLogin = myModel.login(s, password);
    if (checkLogin) {
      myView.mainMenuFrame();
      myView.setListener(this::actionPerformed);
    }
  }

  /**
   * A helper function for the controller of our program.
   */
  private void helper2() {
    myView.createNewPortfolioFrame();
    myView.setListener(this::actionPerformed);
  }

  /**
   * A helper function for the controller of our program.
   */
  private void helper3() {
    String s1 = GUI.PORTFOLIO_N.getText();
    String result = myModel.createNewPortfolio(s1);
    myView.portfolioCreationFrame(result);
    myView.setListener(this::actionPerformed);
  }

  /**
   * A helper function for the controller of our program.
   */
  private void helper4() {
    myView.mainMenuFrame();
    myView.setListener(this::actionPerformed);
  }

  /**
   * A helper function for the controller of our program.
   */
  private void helper5() {
    String myString = myModel.getPortfolioNames();
    myView.listFrame(myString);
    myView.setListener(this::actionPerformed);
  }

  /**
   * A helper function for the controller of our program.
   */
  private void helper6() {
    myView.mainMenuFrame();
    myView.setListener(this::actionPerformed);
  }

  /**
   * A helper function for the controller of our program.
   */
  private void helper7() {
    myView.stockPurchaseForm();
    myView.setListener(this::actionPerformed);
  }

  /**
   * A helper function for the controller of our program.
   */
  private void helper8() {
    String tickerSymbol = GUI.TICKER_SYMBOL.getText();
    String time = GUI.TIME_OF_THE_DAY.getText();
    String date = GUI.STRING_DATE.getText();
    String name = GUI.PORTFOLIO_NAME.getText();
    GUI.PORTFOLIO_NAME.setText("");
    String amount = GUI.DOLLAR_AMOUNT.getText();
    Double realAmount = Double.parseDouble(amount);
    String fee = GUI.COMMISSION_FEE.getText();
    Double realFee = Double.parseDouble(fee);
    String myString1 = myModel.purchase(tickerSymbol, time, date, name, realAmount, realFee);
    myView.showPurchaseFrame(myString1);
    myView.setListener(this::actionPerformed);
  }

  /**
   * A helper function for the controller of our program.
   */
  private void helper9() {
    myView.mainMenuFrame();
    myView.setListener(this::actionPerformed);
  }

  /**
   * A helper function for the controller of our program.
   */
  private void helper10() {
    myView.weightDistributionConfirmation();
    myView.setListener(this::actionPerformed);
  }

  /**
   * A helper function for the controller of our program.
   */
  private void helper11() {
    String s3 = GUI.NEED_WEIGHT_DISTRIBUTION.getText();
    if (s3.toLowerCase().equals("yes")) {
      myView.getPfNameFrame();
      myView.setListener(this::actionPerformed);
    } else {
      myView.getFixedInvestmentForm();
      myView.setListener(this::actionPerformed);
    }

  }

  /**
   * A helper function for the controller of our program.
   */
  private void helper12() {
    myView.showWeightPurchaseForm();
    myView.setListener(this::actionPerformed);
  }

  /**
   * A helper function for the controller of our program.
   */
  private void helper13() {
    String s4 = GUI.GET_PORTFOLIO_NAME.getText();
    GUI.GET_PORTFOLIO_NAME.setText("");
    HashMap<String, Integer> myMap = new HashMap<>();
    String stocks = GUI.GET_STOCK_NAMES.getText();
    String weights = GUI.GET_STOCK_WEIGHTS.getText();
    int i = 0;
    int j = 0;
    int startIndexStock = 0;
    int startIndexWeight = 0;
    String stock;
    String weight;
    while (i < stocks.length() && j < weights.length()) {
      while (stocks.charAt(i) != ',' && i != stocks.length() - 1) {
        i++;
      }
      if (i == stocks.length() - 1) {
        stock = stocks.substring(startIndexStock, i + 1);
      } else {
        stock = stocks.substring(startIndexStock, i);
        startIndexStock = i + 1;
      }
      while (weights.charAt(j) != ',' && j != weights.length() - 1) {
        j++;
      }
      if (j == weights.length() - 1) {
        weight = weights.substring(startIndexWeight, j + 1);
      } else {

        weight = weights.substring(startIndexWeight, j);
        startIndexWeight = j + 1;
      }
      int realWeight = Integer.parseInt(weight);
      myMap.put(stock, realWeight);
      if (i == stocks.length() - 1 && j == weights.length() - 1) {
        break;
      }
      i++;
      j++;
    }
    try {
      String result1 = myModel.setWeights(s4, myMap);
      myView.setWeightsConfirmationFrame(result1);
      myView.setListener(this::actionPerformed);

    } catch (Exception e1) {
      myView.weightsNotSetFrame();
      myView.setListener(this::actionPerformed);

    }
  }

  /**
   * A helper function for the controller of our program.
   */
  private void helper14() {
    myView.mainMenuFrame();
    myView.setListener(this::actionPerformed);
  }

  /**
   * A helper function for the controller of our program.
   */
  private void helper15() {
    myView.getFixedInvestmentForm();
    myView.setListener(this::actionPerformed);
  }

  /**
   * A helper function for the controller of our program.
   */
  private void helper16() {
    myView.costBasisFrame();
    myView.setListener(this::actionPerformed);
  }

  /**
   * A helper function for the controller of our program.
   */
  private void helper17() {
    String s5 = GUI.GET_PORTFOLIO_NAME.getText();
    GUI.GET_PORTFOLIO_NAME.setText("");
    String result2 = myModel.getPortfolioCostBasis(s5);
    myView.showCostBasisFrame(result2);
    myView.setListener(this::actionPerformed);
  }

  /**
   * A helper function for the controller of our program.
   */
  private void helper18() {
    myView.mainMenuFrame();
    myView.setListener(this::actionPerformed);
  }

  /**
   * A helper function for the controller of our program.
   */
  private void helper19() {
    myView.portfolioReadframe();
    myView.setListener(this::actionPerformed);
  }

  /**
   * A helper function for the controller of our program.
   */
  private void helper20() {
    String s6 = GUI.GET_PORTFOLIO_NAME.getText();
    String result3 = myModel.readPortfolio(s6);
    myView.getPortfolioContents(result3);
    myView.setListener(this::actionPerformed);
  }

  /**
   * A helper function for the controller of our program.
   */
  private void helper21() {
    myView.mainMenuFrame();
    myView.setListener(this::actionPerformed);

  }

  /**
   * A helper function for the controller of our program.
   */
  private void helper22() {
    String name2 = GUI.ENTER_PORTFOLIO_NAME.getText();
    GUI.ENTER_PORTFOLIO_NAME.setText("");
    String startDate = GUI.ENTER_START_DATE.getText();
    GUI.ENTER_START_DATE.setText("");
    String endDate = GUI.ENTER_END_DATE.getText();
    GUI.ENTER_END_DATE.setText("");
    String duration = GUI.ENTER_INVESTMENT_DURATION.getText();
    GUI.ENTER_INVESTMENT_DURATION.setText("");
    int realDuration = Integer.parseInt(duration);
    String amount1 = GUI.DOLLAR_AMOUNT.getText();
    GUI.DOLLAR_AMOUNT.setText("");
    Double amount2 = Double.parseDouble(amount1);
    String commissionFee = GUI.COMMISSION_FEE.getText();
    GUI.COMMISSION_FEE.setText("");
    Double commissionFee1 = Double.parseDouble(commissionFee);
    try {
      String investmentStatus = myModel
          .investFixedAmount(name2, startDate, endDate, realDuration, amount2, commissionFee1);
      myView.confirmFixedInvestment(investmentStatus);
      myView.setListener(this::actionPerformed);


    } catch (Exception e1) {
      e1.printStackTrace();
    }
  }

  /**
   * A helper function for the controller of our program.
   */
  private void helper23() {
    myView.mainMenuFrame();
    myView.setListener(this::actionPerformed);
  }

  /**
   * A helper function for the controller of our program.
   */
  private void helper24() {
    String s7 = GUI.PORTFOLIO_NAME.getText();
    GUI.PORTFOLIO_NAME.setText("");
    String s8 = GUI.ENTER_START_DATE.getText();
    GUI.ENTER_END_DATE.setText("");
    String result4 = myModel.getValue(s7, s8);
    myView.getPortfolioValFrame1(result4);
    myView.setListener(this::actionPerformed);
  }

  /**
   * A helper function for the controller of our program.
   */
  private void helper25() {
    myView.mainMenuFrame();
    myView.setListener(this::actionPerformed);
  }


}
