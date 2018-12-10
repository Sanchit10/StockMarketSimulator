package controller;

import java.io.IOException;
import model.SMSimulatorModel;
import view.View;

/**
 * An interface for our controller implementation that allows us to run the application and take.
 * input from the user,display the results accordingly, in other words this function enables a user
 * to interact with our simulator
 */
public interface SMController {


  /**
   * The function that allows a user(having a user account) to interact with our simulator, the.
   * user can create custom portfolios, add stock purchases from the current day or even previous
   * dates in time to these portfolios, examine the composition of any portfolio at any date, get
   * stock info for any publicly trading company on any given day and much more
   *
   * @param model the stock market simulator model that the controller interacts with
   * @param myView the part of our program that solemnly handles the appendable objects for output
   * @throws IOException if the inputs by the user happen to be invalid, doesn't crash the program
   */
  void runStockMarketSimulation(SMSimulatorModel model, View myView) throws IOException;


}
