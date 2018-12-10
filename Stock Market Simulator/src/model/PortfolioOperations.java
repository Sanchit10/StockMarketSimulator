package model;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;
import java.util.stream.Stream;

/**
 * A class that created custom portfolios for users on our stock market simulator.
 */
public class PortfolioOperations {

  // root directory containing all user portfolios and descriptives
  private static final String userDirectory =
      System.getProperty("user.dir") + File.separator + "Investor_Profile";

  /**
   * Returns the size of this portfolio.
   *
   * @return number of holdings in this portfolio
   */
  public int getPortfolioSize(String portfolioName) {
    return this.readPortfolioLines(portfolioName).size();
  }

  /**
   * Initializes a new portfolio entry with fields.
   *
   * @return true if db was created, false if it did not
   */
  public boolean createNewPortfolio(String portfolioName) {
    initNewPortfolioDir(portfolioName);
    //initial entries represents column headers in db files
    String initPortfolioHeader = String.format("TickerSymbol,SharesOwned,CostBasis,Weight\n");
    String initPortfolioDescriptivesHeader =
        String.format("PortfolioName,Scheduled\n" +
            portfolioName + ",false");
    //create the file paths which will be written to
    Path portfolioPath =
        Paths.get(userDirectory + File.separator + portfolioName, "Holdings.txt");
    Path portfolioDescriptivesPath =
        Paths.get(userDirectory + File.separator + portfolioName,
            "Portfolio_Descriptives.txt");
    try {
      //create and write to file; does not create file if file exists
      Files.write(portfolioPath, initPortfolioHeader.getBytes(), StandardOpenOption.CREATE_NEW);
      Files.write(portfolioDescriptivesPath, initPortfolioDescriptivesHeader.getBytes(),
          StandardOpenOption.CREATE_NEW);
    } catch (IOException e) {
      return false;
    }
    return true;
  }

  /**
   * Gets all portfolio names present in the directory.
   *
   * @return a string representing the names of the portfolios
   */
  public String getPortfolioNames() {
    File[] directoryArray = Objects
        .requireNonNull(new File(userDirectory).listFiles(File::isDirectory));
    ArrayList<File> directories = new ArrayList<>(Arrays.asList(directoryArray));
    if (directories.size() > 0) {
      StringBuilder sb = new StringBuilder();
      for (int i = directories.size() - 1; i >= 0; i--) {
        sb.append(this.getPortfolioName(directories.get(i)) + "\n");
      }
      return sb.toString().trim();
    } else {
      return "You currently have no active portfolios";
    }
  }

  /**
   * Gets a single portfolio name given a specified filePath.
   *
   * @param file the filepath
   * @return the name of the portfolio
   */
  private String getPortfolioName(File file) {
    Scanner scan = new Scanner(file.toString());
    scan.useDelimiter("[/]");
    String portfolioName = "";
    while (scan.hasNext()) {
      portfolioName = scan.next();
    }
    return portfolioName;
  }

  /**
   * Get the contents of desired portfolio.
   *
   * @param portfolioName name of the portfolio
   * @return a string representing the contents/existence of the portfolio
   */
  public String readPortfolio(String portfolioName) {
    if (new File(userDirectory + File.separator + portfolioName).exists()) {
      //get lines from portfolio file
      List<String> lines = this.readPortfolioLines(portfolioName);
      StringBuilder sb = new StringBuilder();
      sb.append("Portfolio name: " + portfolioName + "\n");
      sb.append("Total cost basis: " + this.getPortfolioCostBasis(portfolioName) + "\n");
      if (lines.size() > 0) {
        for (String line : lines) {
          Scanner scan = new Scanner(line);
          scan.useDelimiter(",");
          String tick = scan.next();
          String sharesOwned = scan.next();
          String tickCostBasis = scan.next();
          sb.append(tick + ": " + sharesOwned + " shares owned\n");
          sb.append(tick + " cost basis: " + tickCostBasis + "\n");
          int weightInt = scan.nextInt();
          String weightString = (weightInt == -1) ?
              "Evenly weighted" : Integer.toString(weightInt) + "%";
          sb.append("Weight: " + weightString + "\n");
        }
        return sb.toString().trim();
      } else {
        return "This portfolio is active, but contains no holdings.";
      }
    } else {
      return "No such portfolio exists. Please check your spelling.";
    }
  }

  /**
   * Returns true if this portfolio has been marked for scheduled investments.
   *
   * @return true if scheduled, otherwise false
   */
  public boolean isScheduled(String portfolioName) {
    String descriptives = this.getPortfolioDescriptives(portfolioName);
    Scanner scan = new Scanner(descriptives);
    scan.useDelimiter("[,]");
    String scheduled = "";
    while (scan.hasNext()) {
      scheduled = scan.next();
    }
    return scheduled.matches("true");
  }

  /**
   * Sets the scheduler for the desired portfolio.
   *
   * @param portfolioName the desired portfolio to be manipulated
   * @param trueOrFalse status of the schedule
   */
  public void setSchedule(String portfolioName, boolean trueOrFalse) {
    //fixme if file exists
    String descriptives = this.getPortfolioDescriptives(portfolioName);
    StringBuilder sb = new StringBuilder();
    Scanner scan = new Scanner(descriptives);
    scan.useDelimiter("[,]");
    sb.append(scan.next() + "," + trueOrFalse);
    this.resetDescriptives(portfolioName, sb.toString());
  }

  /**
   * Gets individual cost basis for given holding.
   *
   * @param tickerSymbol ticker symbol
   * @return stock cost basis
   */
  public String getIndividualStockCostBasis(String tickerSymbol, String portfolioName) {
    //get lines from portfolio file
    List<String> lines =
        this.readPortfolioLines(
            userDirectory + File.separator + portfolioName + "Holdings.txt");
    //scan through lines to find tickerSybmol match
    if (lines.size() > 0) {
      for (String line : lines) {
        Scanner scan = new Scanner(line);
        scan.useDelimiter(",");
        String tickerToken = scan.next();
        //iterate to cost basis column
        if (tickerToken.matches(tickerSymbol)) {
          scan.next();
          return String.format("%.4f", scan.next());
        }
      }
    }
    return "No cost basis information for \"" + tickerSymbol +
        "\" in portfolio \"" + portfolioName + "\"";
  }

  /**
   * Retrieves the cost basis for the desired portfolio.
   *
   * @param portfolioName the desired portfolio
   * @return the cost basis of the portfolio
   */
  public String getPortfolioCostBasis(String portfolioName) {
    List<String> lines = this.readPortfolioLines(portfolioName);
    if (lines.size() > 0) {
      double totalCostBasis = 0;
      for (String line : lines) {
        Scanner scan = new Scanner(line);
        scan.useDelimiter(",");
        scan.next();
        scan.next();
        totalCostBasis += scan.nextDouble();
      }
      return Double.toString(totalCostBasis);
    }
    return "0.00";
  }

  /**
   * Returns whether or not this portfolio is evenly weighted.
   *
   * @return true if evenly weighted, otherwise false
   */
  public boolean isEvenlyWeighted(String portfolioName) {
    //fixme
    HashMap<String, Integer> weights = this.getWeights(portfolioName);
    ArrayList<Integer> values = new ArrayList<>(weights.values());
    if (values.size() > 0) {
      int testValue = values.remove(0);
      for (String key : weights.keySet()) {
        if (testValue != weights.get(key)) {
          return false;
        }
      }
    }
    return true;
  }

  /**
   * Determines whether or not the desired portfolio contains the given holding.
   *
   * @param portfolioName desired portfolio
   * @param tickerSymbol the given holding
   * @return true if contains, otherwise false
   */
  public boolean portfolioContains(String portfolioName, String tickerSymbol) {
    ArrayList<String> lines = (ArrayList<String>) this.readPortfolioLines(portfolioName);
    if (lines.size() > 0) {
      for (String line : lines) {
        Scanner scan = new Scanner(line);
        scan.useDelimiter("[,]");
        String portfolioTicker = scan.next();
        if (portfolioTicker.matches(tickerSymbol)) {
          return true;
        }
      }
    }
    return false;
  }

  /**
   * Sets the investment weightage for the given portfolio.
   *
   * @param weights a hashmap of tickerSymbol/weight pairs
   */
  public String setWeights(HashMap<String, Integer> weights, String portfolioName) {
    //get lines to file
    ArrayList<String> fileLines = (ArrayList<String>) readPortfolioLines(portfolioName);
    if (fileLines.size() > 0) {
      ArrayList<String> newLines = new ArrayList<>();
      int i = 0;
      //update the entries
      for (String line : fileLines) {
        //scan current line
        Scanner scan = new Scanner(line);
        scan.useDelimiter("[,]");
        String tickerSymbol = scan.next();
        //remove line to update
        String removedLine = fileLines.get(i);
        String replacementString = removedLine.substring(
            0, removedLine.length() - 2) +
            String.format("%02d", weights.get(tickerSymbol));
        newLines.add(i, replacementString);
        i++;
      }
      //write updated entries to portfolio
      try {
        this.overwriteListToPortfolio(portfolioName, newLines);
        return "Successfully set weights for " + portfolioName;
      } catch (IOException e) {
        return "Failed to update weights for " + portfolioName;
      }
    }
    return "Error setting weights for " + portfolioName;
  }

  /**
   * Returns a hashmap of tickerSymbol/weight pairs for this portfolio.
   *
   * @param portfolioName the desired portfolio
   * @return a hashmap of tickerSymbol/weight pairs
   */
  public HashMap<String, Integer> getWeights(String portfolioName) {
    ArrayList<String> fileLines = (ArrayList<String>) readPortfolioLines(portfolioName);
    HashMap<String, Integer> portfolioWeights = new HashMap<>();
    if (fileLines.size() > 0) {
      for (String line : fileLines) {
        Scanner scan = new Scanner(line);
        scan.useDelimiter("[,]");
        String tickerSymbol = scan.next();
        scan.next();
        scan.next();
        portfolioWeights.put(tickerSymbol, scan.nextInt());
      }
      return portfolioWeights;
    }
    return new HashMap<>();
  }

  /**
   * Gets all ticker symbols for a desired portfolio.
   *
   * @param portfolioName the desired portfolio
   */
  protected HashMap<String, Integer> getTickerSymbolsAndSharesOwned(String portfolioName) {
    HashMap<String, Integer> returnMap = new HashMap<>();
    ArrayList<String> lines = (ArrayList<String>) this.readPortfolioLines(portfolioName);
    if (lines.size() > 0) {
      for (String line : lines) {
        Scanner scan = new Scanner(line);
        scan.useDelimiter("[,]");
        returnMap.put(scan.next(), scan.nextInt());
      }
    }
    return returnMap;
  }

  /**
   * Gets the weight for the given company holding in a given portfolio.
   *
   * @param portfolioName the "given portfolio"
   * @param tickerSymbol the "given company
   * @return an integer weight value representing the percent weight distribution attributed
   */
  private int getWeight(String portfolioName, String tickerSymbol) {
    return this.getWeights(portfolioName).get(tickerSymbol);

  }

  /**
   * Updates the cost basis for the given portfolio investment.
   *
   * @param portfolioName the portfolio to update
   * @param tickerSymbol the entry to update
   * @param dollarsToAdd money to purchase stocks + commission fee
   * @return status message indicating the success/failure of update
   */
  public String updateStockEntry(String portfolioName, String tickerSymbol,
      int stocksToAdd, double dollarsToAdd) {
    List<String> fileLines = readPortfolioLines(portfolioName);

    if (fileLines.size() > 0 &&
        this.portfolioContains(portfolioName, tickerSymbol)) {
      int i = 0;
      for (String line : fileLines) {
        Scanner scan = new Scanner(line);
        scan.useDelimiter("[,]");
        if (scan.next().matches(tickerSymbol)) {
          //remove line to update
          fileLines.remove(i);
          int shareUpdate = Integer.parseInt(scan.next()) + stocksToAdd;
          double costBasisUpdate = Double.parseDouble(scan.next()) + dollarsToAdd;
          String replacementString =
              tickerSymbol + "," + shareUpdate + "," + costBasisUpdate + "," + scan.next();
          fileLines.add(i, replacementString);
          String filePath = userDirectory + File.separator + portfolioName +
              File.separator + "Holdings.txt";
          // create BufferedWriter(FileWriter) used to append input to .txt file
          // 'false' indicates overwrite
          try {
            this.overwriteListToPortfolio(portfolioName, fileLines);
          } catch (IOException e) {
            return "Failed to write to file" + filePath +
                "internal error PortfolioOperations.updateCostBasis(" + portfolioName +
                tickerSymbol + "," + stocksToAdd + "," + dollarsToAdd + ")";
          }
          return "Successfully updated " + tickerSymbol +
              "entry for portfolio" + "\"" + portfolioName + "\"";
        } else {
          i++;
        }
      }
    } else {
      this.appendToPortfolio(portfolioName, tickerSymbol, stocksToAdd, dollarsToAdd);
      return "Successfully updated " + tickerSymbol +
          "entry for portfolio" + "\"" + portfolioName + "\"";
    }
    return "No current shares of " + tickerSymbol + "for portfolio \"" + portfolioName + "\"";
  }

  /**
   * Resets the descriptive file for the given portfolio with new input.
   *
   * @param replacementLine the info line to replace the old one
   * @return status message indicating success/failure
   */
  protected String resetDescriptives(String portfolioName, String replacementLine) {
    String filePath = userDirectory + File.separator + portfolioName +
        File.separator + "Portfolio_Descriptives.txt";
    // create BufferedWriter(FileWriter) used to append input to .txt file
    // 'false' indicates overwrite
    try (BufferedWriter buffWriter = new BufferedWriter(
        new FileWriter(filePath, false))) {
      buffWriter.write("PortfolioName,Scheduled");
      buffWriter.newLine();
      buffWriter.write(replacementLine);
      return "Successfully update " + portfolioName + " descriptives";
    } catch (IOException e) {
      return "Failed to update " + portfolioName + " descriptives";
    }
  }

  /**
   * OverWrites content of given portfolio with given list.
   *
   * @param portfolioName desired portfolio
   * @param fileLines the lines to write into the portfolio file
   * @return true if successful, otherwise false
   */
  private boolean overwriteListToPortfolio(String portfolioName, List<String> fileLines)
      throws IOException {
    String filePath = userDirectory + File.separator +
        portfolioName + File.separator + "Holdings.txt";
    // create BufferedWriter(FileWriter) used to append input to .txt file
    // 'false' indicates overwrite
    try (BufferedWriter buffWriter = new BufferedWriter(
        new FileWriter(filePath, false))) {
      buffWriter.write("TickerSymbol,SharesOwned,CostBasis,Weight");
      buffWriter.newLine();
      for (String fileLine : fileLines) {
        buffWriter.write(fileLine);
        buffWriter.newLine();
      }
      return true;
    } catch (IOException e) {
      return false;
    }
  }

  /**
   * Appends new stock entry to portfolio.
   *
   * @param portfolioName desired portfolio
   * @param tickerSymbol company to append
   * @param stocksToAdd number of stocks purchased
   * @param initialCostBasis money spent on this purchase
   * @return status message
   */
  public String appendToPortfolio(String portfolioName, String tickerSymbol,
      int stocksToAdd, double initialCostBasis) {
    String userEntry = String.format(
        "%s,%s,%s,%s", tickerSymbol, stocksToAdd, initialCostBasis, -1);
    String filePath =
        userDirectory + File.separator + portfolioName + File.separator + "Holdings.txt";
    //create BufferedWriter(FileWriter) used to append input to .txt file
    //'true' indicates append rather than overwrite to file
    try (BufferedWriter buffWriter = new BufferedWriter(
        new FileWriter(filePath, true))) {
      //append user data to database file
      buffWriter.append(userEntry);
      buffWriter.newLine();
      return "Successfully appended data to portfolio" + portfolioName;
    } catch (IOException e) {
      // System.err.println("Failed to write to file");
      return "Failed to write to file" + filePath +
          "internal error PortfolioOperations.updateCostBasis(" + portfolioName + "," +
          tickerSymbol + "," + stocksToAdd + "," + initialCostBasis + ")";
    }
  }

  /**
   * Determines whether or not the desired portfolio exists.
   *
   * @param portfolioName thr desired portfolio
   * @return true if it exists, otherwise false
   */
  protected boolean portfolioExists(String portfolioName) {
    return new File(userDirectory + File.separator + portfolioName).exists();
  }

  /**
   * Reads file and returns each line as an element of a list.
   *
   * @param path the filepath
   * @return a list of pertinent lines in the file
   */
  private List<String> readFile(Path path) {
    try {
      //create list to hold lines
      List<String> list = Files.readAllLines(path);
      list.remove(0);
      if (list.size() > 0) {
        return list;
      }
    } catch (IOException e) {
      e.printStackTrace();
    }
    return new ArrayList<>();
  }

  /**
   * Reads portfolio and returns its lines in list format (1 element == 1 line).
   *
   * @return a list representing the file contents.
   */
  private List<String> readPortfolioLines(String portfolioName) {
    Path path = Paths.get(userDirectory + File.separator + portfolioName,
        "Holdings.txt");
    ArrayList<String> returnList = (ArrayList<String>) this.readFile(path);
    //remove column labels
    if (returnList.size() > 0) {
      return returnList;
    }
    return new ArrayList<>();
  }

  /**
   * Reads portfolio descriptives file and returns a string.
   */
  private String getPortfolioDescriptives(String portfolioName) {
    Path path = Paths.get(userDirectory + File.separator + portfolioName,
        "Portfolio_Descriptives.txt");
    return this.readFile(path).get(0);
  }

  /**
   * Initializes investor database directory if not present.
   *
   * @return return true if db was created, false if it did not
   */
  private boolean initInvestorDB() {
    if (!new File(userDirectory).exists()) {
      new File(userDirectory).mkdirs();
      return true;
    }
    return false;
  }

  /**
   * Initializes investor database directory if not present.
   *
   * @return return true if db was created, false if it did not
   */
  private boolean initNewPortfolioDir(String portfolioName) {
    initInvestorDB();
    String directoryPath = userDirectory + File.separator + portfolioName;
    if (!new File(directoryPath).exists()) {
      new File(directoryPath).mkdirs();
      return true;
    }
    return false;
  }

  /**
   * Helper function used in recursive delete functions.
   *
   * @param filePath the path and contents to be deleted
   */
  boolean recursiveDelete(String filePath) {
    Path path = Paths.get(filePath);
    try (Stream<Path> stream = Files.walk(path)) {
      stream.sorted(Comparator.reverseOrder()).map(Path::toFile).forEach(File::delete);
    } catch (IOException e) {
      return false;
    }
    return true;
  }
}