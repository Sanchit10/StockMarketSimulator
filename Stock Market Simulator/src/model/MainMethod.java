package model;

import controller.SMController;
import controller.SMControllerGUI;
import controller.SMControllerImpl;
import java.io.IOException;
import java.io.InputStreamReader;
import view.GUI;
import view.View;
import view.ViewImpl;


/**
 * A class that consists of the 'main' to run our simulator, we create a user object to enable the.
 * login functionality for the program
 */
public class MainMethod {

  /**
   * A class to run our stock market simulator, we created a user object whose username and.
   * password can be used to login and run the simulator
   *
   * @param args default value
   */
  public static void main(String[] args) {

    if (args.length == 1) {
      if (args[0].toLowerCase().matches("gui")) {
        SMSimulatorModel myModel = new SMSimulatorModelImpl();
        View realView = new GUI();
        SMController myController = new SMControllerGUI(myModel, realView);
      } else if (args[0].toLowerCase().matches("console")) {
        SMSimulatorModel myModel = new SMSimulatorModelImpl();
        View myView = new ViewImpl();
        try {
          new SMControllerImpl(new InputStreamReader(System.in), System.out)
              .runStockMarketSimulation(myModel, myView);
        } catch (IOException e) {
          e.printStackTrace();
        }
      } else {
        System.out
            .println("Invalid argument. Please run the file with arguments \"console\" or \"gui\"");
      }
    } else {
      System.out
          .println(
              "Invalid argument(s). Please run the file with arguments \"console\" or \"gui\"");
    }
  }
}