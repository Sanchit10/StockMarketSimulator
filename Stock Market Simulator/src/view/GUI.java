package view;


import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionListener;
import java.util.HashMap;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

/**
 * A class that implements the view interface and acts as the graphical user interface for our.
 * stock market simulator, the graphical user interface provides all the functionalities as our old
 * text based view
 */
public class GUI extends JFrame implements View {

  HashMap<String, Integer> myMap = new HashMap<>();
  private JButton login;
  private JButton createPortfolio;
  private JButton portfolioCreationSuccess;
  private JButton portfolioCreationExit;
  private JButton portfolioLists;
  private JButton listCreate;
  private JButton purchase;
  private JButton makePurchase;
  private JButton backToMain;
  private JButton fixedInvestment;
  private JButton customWeightDecision;
  private JButton decision1;
  private JButton setWeights;
  private JButton fixedInvestmentForm;
  private JButton setWeightsFailed;
  private JButton getCostPerBasis;
  private JButton calculateCostBasis;
  private JButton backtoMainMenu2;
  private JButton readPortfolio;
  private JButton readPortfolio1;
  private JButton backtoMainMenu4;
  private JButton completeFixedInvestment;
  private JButton backToMainMenu5;
  private JButton portfolioValue;
  private JButton getPortfolioValue;
  private JButton backToMainMenu6;
  private JButton exit;


  private ActionListener myListner;
  public static JTextField USER_NAME = new JTextField(10);
  public static JTextField PORTFOLIO_N = new JTextField(10);
  public static JTextField TICKER_SYMBOL = new JTextField(10);
  public static JTextField TIME_OF_THE_DAY = new JTextField(10);
  public static JTextField STRING_DATE = new JTextField(10);
  public static JTextField PORTFOLIO_NAME = new JTextField(10);
  public static JTextField DOLLAR_AMOUNT = new JTextField(10);
  public static JTextField COMMISSION_FEE = new JTextField(10);
  public static JTextField NEED_WEIGHT_DISTRIBUTION = new JTextField(10);
  public static JTextField GET_PORTFOLIO_NAME = new JTextField(10);
  public static JTextField GET_STOCK_NAMES = new JTextField(20);
  public static JTextField GET_STOCK_WEIGHTS = new JTextField(20);
  public static JTextField ENTER_PORTFOLIO_NAME = new JTextField(10);
  public static JTextField ENTER_START_DATE = new JTextField(10);
  public static JTextField ENTER_END_DATE = new JTextField(10);
  public static JTextField ENTER_INVESTMENT_DURATION = new JTextField(10);
  public static JTextField GET_PASSWORD = new JTextField(10);

  /**
   * The constructor for our graphical user interface, it created a login interface that allows.
   * users to login and purchase stocks to portfolios, add portfolios to high level strategies, save
   * and retrieve investment information and much more
   */
  public GUI() {

    JFrame display = new JFrame("Stock Market Simulator");
    JPanel mainPanel = new JPanel();
    display.getContentPane().add(mainPanel);
    JPanel panelForm = new JPanel(new GridBagLayout());
    panelForm.setBackground(Color.black);
    mainPanel.add(panelForm);

    GridBagConstraints c = new GridBagConstraints();
    c.gridx = 0;
    c.gridy = 0;
    c.anchor = GridBagConstraints.LINE_START;

    JLabel welcomeMessage = new JLabel("Welcome to our stock market simulator!");
    welcomeMessage.setForeground(Color.GREEN);
    panelForm.add(welcomeMessage, c);
    c.gridy++;
    JLabel enterUsername = new JLabel("Please enter your username:");
    enterUsername.setForeground(Color.GREEN);
    panelForm.add(enterUsername, c);
    c.gridy++;
    JLabel enterPassword = new JLabel("Please enter your password:");
    enterPassword.setForeground(Color.GREEN);
    panelForm.add(enterPassword, c);
    c.gridy++;
    login = new JButton("Login");
    login.setActionCommand("Login!");
    panelForm.add(login, c);

    c.gridx = 1;
    c.gridy = 1;
    c.anchor = GridBagConstraints.LINE_START;
    panelForm.add(USER_NAME, c);
    c.gridy++;
    GET_PASSWORD = new JPasswordField(10);
    panelForm.add(GET_PASSWORD, c);
    c.gridy++;

    mainPanel.setBackground(Color.black);
    display.setVisible(true);
    display.setSize(400, 200);
    display.setResizable(false);
    display.setLocationRelativeTo(null);
    display.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    display.pack();


  }

  @Override
  public void mainMenuFrame() {

    JFrame display = new JFrame("The Main Menu");
    JPanel mainPanel = new JPanel();
    display.getContentPane().add(mainPanel);
    JPanel menu = new JPanel(new GridBagLayout());
    menu.setBackground(Color.black);
    mainPanel.add(menu);
    GridBagConstraints c = new GridBagConstraints();
    helper1(c, menu);
    helper2(c, menu);

    mainPanel.setBackground(Color.black);
    display.setSize(700, 400);
    display.setLocationRelativeTo(null);
    display.setVisible(true);
    display.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);


  }


  @Override
  public void setListener(ActionListener listener) {
    login.addActionListener(listener);
    helper7(listener);
    helper8(listener);
    helper9(listener);
  }


  @Override
  public void createNewPortfolioFrame() {
    JFrame display = new JFrame("Create a new portfolio");
    JPanel mainPanel = new JPanel();
    display.getContentPane().add(mainPanel);
    JPanel form = new JPanel(new GridBagLayout());
    form.setBackground(Color.black);
    mainPanel.add(form);
    GridBagConstraints c = new GridBagConstraints();
    c.gridx = 0;
    c.gridy = 0;
    c.anchor = GridBagConstraints.LINE_END;
    JLabel msg = new JLabel("Enter the name of the portfolio that you wish to create:=>");
    msg.setForeground(Color.green);
    form.add(msg, c);

    c.gridx = 1;
    c.gridy = 0;
    c.anchor = GridBagConstraints.LINE_END;
    PORTFOLIO_N = new JTextField(10);
    form.add(PORTFOLIO_N, c);
    c.gridy++;
    portfolioCreationSuccess = new JButton("Create");
    form.add(portfolioCreationSuccess, c);
    portfolioCreationSuccess.setActionCommand("Successfully created new portfolio!");
    myMap.put("Successfully created new portfolio!", 2);
    this.setListener(myListner);

    mainPanel.setBackground(Color.black);
    display.setSize(700, 400);
    display.setLocationRelativeTo(display);
    display.setVisible(true);
    display.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    display.pack();


  }

  @Override
  public void portfolioCreationFrame(String s) {
    JFrame display = new JFrame("Create a new PortfolioOperations");
    JPanel mainPanel = new JPanel();
    display.getContentPane().add(mainPanel);
    mainPanel.setBackground(Color.black);

    JLabel label = new JLabel(s);
    label.setForeground(Color.green);
    mainPanel.add(label);
    portfolioCreationExit = new JButton("Ok");
    mainPanel.add(portfolioCreationExit);
    portfolioCreationExit.setActionCommand("PortfolioOperations Created");
    this.myMap.put("PortfolioOperations Created", 3);
    this.setListener(myListner);

    mainPanel.setBackground(Color.black);
    display.setSize(700, 400);
    display.setLocationRelativeTo(display);
    display.setVisible(true);
    display.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);


  }


  @Override
  public void listFrame(String s) {
    JFrame display = new JFrame("List of portfolios");
    JPanel mainPanel = new JPanel();
    display.getContentPane().add(mainPanel);
    mainPanel.setBackground(Color.black);

    JLabel label = new JLabel(s);
    label.setForeground(Color.green);
    mainPanel.add(label);

    listCreate = new JButton("Ok");
    listCreate.setActionCommand("Back to main menu1");
    myMap.put("Back to main menu1", 5);
    this.setListener(myListner);
    mainPanel.add(listCreate);

    mainPanel.setBackground(Color.black);
    display.setSize(700, 400);
    display.setLocationRelativeTo(display);
    display.setVisible(true);
    display.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);


  }


  @Override
  public void stockPurchaseForm() {
    JFrame display = new JFrame("Purchase Stock");
    JPanel mainPanel = new JPanel();
    display.getContentPane().add(mainPanel);
    JPanel menu = new JPanel(new GridBagLayout());
    menu.setBackground(Color.black);
    mainPanel.add(menu);
    GridBagConstraints c = new GridBagConstraints();
    helper3(c, menu);
    helper4(c, menu);
    mainPanel.setBackground(Color.black);
    display.setSize(700, 400);
    display.setLocationRelativeTo(null);
    display.setVisible(true);
    display.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    display.pack();


  }

  @Override
  public void showPurchaseFrame(String s) {
    JFrame display = new JFrame("Purchase Stock Status");
    JPanel mainPanel = new JPanel();
    display.getContentPane().add(mainPanel);
    JPanel menu = new JPanel(new GridBagLayout());
    menu.setBackground(Color.black);
    mainPanel.add(menu);
    JLabel label = new JLabel(s);
    label.setForeground(Color.green);
    menu.add(label);

    backToMain = new JButton("Ok");
    backToMain.setActionCommand("Back to main menu2");
    myMap.put("Back to main menu2", 8);
    this.setListener(myListner);
    menu.add(backToMain);

    mainPanel.setBackground(Color.black);
    display.setSize(700, 400);
    display.setLocationRelativeTo(null);
    display.setVisible(true);
    display.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

  }

  @Override
  public void weightDistributionConfirmation() {

    JFrame display = new JFrame("Weight distribution confirmation");
    JPanel mainPanel = new JPanel();
    display.getContentPane().add(mainPanel);
    mainPanel.setBackground(Color.black);
    JLabel label = new JLabel("Do you want a custom weight distribution? Enter yes/no :=>");
    label.setForeground(Color.green);
    mainPanel.add(label);
    mainPanel.add(NEED_WEIGHT_DISTRIBUTION);
    customWeightDecision = new JButton("Ok");
    customWeightDecision.setActionCommand("Decision");
    myMap.put("Decision", 10);
    this.setListener(myListner);
    mainPanel.add(customWeightDecision);

    mainPanel.setBackground(Color.black);
    display.setSize(700, 400);
    display.setLocationRelativeTo(null);
    display.setVisible(true);
    display.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

  }

  @Override
  public void showWeightPurchaseForm() {
    JFrame display = new JFrame("Set Weights");
    JPanel mainPanel = new JPanel();
    display.getContentPane().add(mainPanel);
    JPanel menu = new JPanel(new GridBagLayout());
    menu.setBackground(Color.black);
    mainPanel.add(menu);
    GridBagConstraints c = new GridBagConstraints();
    c.gridx = 0;
    c.gridy = 0;
    c.anchor = GridBagConstraints.LINE_START;
    JLabel label = new JLabel(
        "Enter the name of the stocks in your porfolio, seperated by a ',':=> ");
    label.setForeground(Color.green);
    menu.add(label, c);
    c.gridy++;
    JLabel label1 = new JLabel("Enter the custom weights of the stocks, seperated by a ',' :=> ");
    label1.setForeground(Color.green);
    menu.add(label1, c);
    c.gridy = 0;
    c.gridx = 1;

    c.anchor = GridBagConstraints.LINE_END;
    menu.add(GET_STOCK_NAMES, c);
    c.gridy++;
    menu.add(GET_STOCK_WEIGHTS, c);
    c.gridy++;
    setWeights = new JButton("Ok");
    setWeights.setActionCommand("Set Weights");
    myMap.put("Set Weights", 12);
    this.setListener(myListner);
    menu.add(setWeights, c);

    mainPanel.setBackground(Color.black);
    display.setSize(700, 400);
    display.setLocationRelativeTo(null);
    display.setVisible(true);
    display.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
  }

  @Override
  public void getPfNameFrame() {
    JFrame display = new JFrame("Weight distribution confirmation");
    JPanel mainPanel = new JPanel();
    display.getContentPane().add(mainPanel);
    JPanel menu = new JPanel(new GridBagLayout());
    menu.setBackground(Color.black);
    menu.setBackground(Color.black);
    mainPanel.add(menu);
    GridBagConstraints c = new GridBagConstraints();
    c.gridy = 0;
    c.gridx = 0;
    c.anchor = GridBagConstraints.LINE_START;
    JLabel myLabel = new JLabel("Enter the name of the portfolio that you wish to use:=>");
    myLabel.setForeground(Color.green);
    menu.add(myLabel, c);
    c.gridy++;
    c.anchor = GridBagConstraints.LINE_START;
    c.gridy = 0;
    c.gridx = 1;
    menu.add(GET_PORTFOLIO_NAME, c);
    c.gridy++;
    decision1 = new JButton("Ok");
    decision1.setActionCommand("Decision1");
    myMap.put("Decision1", 11);
    this.setListener(myListner);
    menu.add(decision1, c);
    c.gridy++;
    mainPanel.setBackground(Color.black);
    display.setSize(700, 400);
    display.setLocationRelativeTo(null);
    display.setVisible(true);
    display.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

  }

  @Override
  public void setWeightsConfirmationFrame(String s) {
    JFrame display = new JFrame("Weight distribution confirmation");
    JPanel mainPanel = new JPanel();
    display.getContentPane().add(mainPanel);
    mainPanel.setBackground(Color.black);
    JLabel myLabel = new JLabel(s);
    myLabel.setForeground(Color.green);
    mainPanel.add(myLabel);
    fixedInvestmentForm = new JButton("Ok");
    fixedInvestmentForm.setActionCommand("Fixed Investment Form1");
    myMap.put("Fixed Investment Form1", 16);
    this.setListener(myListner);
    mainPanel.add(fixedInvestmentForm);
    mainPanel.setBackground(Color.black);
    display.setSize(700, 400);
    display.setLocationRelativeTo(null);
    display.setVisible(true);
    display.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
  }

  @Override
  public void weightsNotSetFrame() {
    JFrame display = new JFrame("Weights not set");
    JPanel mainPanel = new JPanel();
    display.getContentPane().add(mainPanel);
    JPanel menu = new JPanel(new GridBagLayout());
    menu.setBackground(Color.black);
    mainPanel.add(menu);
    GridBagConstraints c = new GridBagConstraints();
    c.gridx = 0;
    c.gridy = 0;
    c.anchor = GridBagConstraints.LINE_START;
    JLabel myLabel = new JLabel("Weights not set.Please try again");
    myLabel.setForeground(Color.green);
    menu.add(myLabel, c);
    c.gridy++;
    c.gridx = 1;
    c.gridy = 0;
    c.anchor = GridBagConstraints.LINE_END;
    setWeightsFailed = new JButton("Ok");
    setWeightsFailed.setActionCommand("Weights Failed");
    myMap.put("Weights Failed", 14);
    this.setListener(myListner);
    menu.add(setWeightsFailed, c);
    mainPanel.setBackground(Color.black);
    display.setSize(700, 400);
    display.setLocationRelativeTo(null);
    display.setVisible(true);
    display.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
  }


  @Override
  public void getFixedInvestmentForm() {
    JFrame display = new JFrame("Purchase Stock in Fixed Amount");
    JPanel mainPanel = new JPanel();
    display.getContentPane().add(mainPanel);
    JPanel menu = new JPanel(new GridBagLayout());
    menu.setBackground(Color.black);
    mainPanel.add(menu);
    GridBagConstraints c = new GridBagConstraints();
    helper5(c, menu);
    helper6(c, menu);
    completeFixedInvestment = new JButton("Buy stock");
    completeFixedInvestment.setActionCommand("Complete");
    myMap.put("Complete", 7);
    this.setListener(myListner);
    menu.add(completeFixedInvestment, c);
    mainPanel.setBackground(Color.black);
    display.setSize(700, 400);
    display.setLocationRelativeTo(null);
    display.setVisible(true);
    display.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    display.pack();
  }

  @Override
  public void costBasisFrame() {
    JFrame display = new JFrame("Cost Basis Info");
    JPanel mainPanel = new JPanel();
    display.getContentPane().add(mainPanel);
    JPanel menu = new JPanel(new GridBagLayout());
    menu.setBackground(Color.black);
    mainPanel.add(menu);
    GridBagConstraints c = new GridBagConstraints();
    c.gridx = 0;
    c.gridy = 0;
    c.anchor = GridBagConstraints.LINE_START;
    JLabel label1 = new JLabel("Enter the name of the portfolio for cost basis calculation =>");
    label1.setForeground(Color.green);
    menu.add(label1, c);
    c.gridy = 0;
    c.gridx = 1;
    menu.add(GET_PORTFOLIO_NAME);
    c.gridy++;
    calculateCostBasis = new JButton("Ok");
    calculateCostBasis.setActionCommand("Calculate1");
    myMap.put("Calculate1", 18);
    this.setListener(myListner);
    menu.add(calculateCostBasis);
    mainPanel.setBackground(Color.black);
    display.setSize(700, 400);
    display.setLocationRelativeTo(null);
    display.setVisible(true);
    display.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);


  }

  @Override
  public void showCostBasisFrame(String s) {

    JFrame display = new JFrame("Cost Basis");
    JPanel mainPanel = new JPanel();
    display.getContentPane().add(mainPanel);
    mainPanel.setBackground(Color.black);
    String dollar = "$";
    JLabel myLabel = new JLabel(dollar + s);
    myLabel.setForeground(Color.green);
    mainPanel.add(myLabel);
    backtoMainMenu2 = new JButton("Ok");
    backtoMainMenu2.setActionCommand("Back to main menu 3");
    myMap.put("Back to main menu 3", 25);
    this.setListener(myListner);
    mainPanel.add(backtoMainMenu2);
    mainPanel.setBackground(Color.black);
    display.setSize(700, 400);
    display.setLocationRelativeTo(null);
    display.setVisible(true);
    display.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);


  }

  @Override
  public void portfolioReadframe() {
    JFrame display = new JFrame("Cost Basis Info");
    JPanel mainPanel = new JPanel();
    display.getContentPane().add(mainPanel);
    JPanel menu = new JPanel(new GridBagLayout());
    menu.setBackground(Color.black);
    mainPanel.add(menu);
    GridBagConstraints c = new GridBagConstraints();
    c.gridx = 0;
    c.gridy = 0;
    c.anchor = GridBagConstraints.LINE_START;
    JLabel label1 = new JLabel("Enter the name of the portfolio that you wish to read =>");
    label1.setForeground(Color.green);
    menu.add(label1, c);
    c.gridy = 0;
    c.gridx = 1;
    menu.add(GET_PORTFOLIO_NAME);
    c.gridy++;
    readPortfolio1 = new JButton("Ok");
    readPortfolio1.setActionCommand("Read1");
    myMap.put("Read1", 18);
    this.setListener(myListner);
    menu.add(readPortfolio1);
    mainPanel.setBackground(Color.black);
    display.setSize(700, 400);
    display.setLocationRelativeTo(null);
    display.setVisible(true);
    display.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
  }

  @Override
  public void getPortfolioContents(String s) {

    JFrame display = new JFrame("Get PortfolioOperations Contents");
    JPanel mainPanel = new JPanel();
    display.getContentPane().add(mainPanel);
    mainPanel.setBackground(Color.black);
    JLabel myLabel = new JLabel(s);
    myLabel.setForeground(Color.green);
    mainPanel.add(myLabel);
    backtoMainMenu4 = new JButton("Ok");
    backtoMainMenu4.setActionCommand("Back to main menu 4");
    myMap.put("Back to main menu 4", 35);
    mainPanel.add(backtoMainMenu4);
    this.setListener(myListner);
    mainPanel.setBackground(Color.black);
    display.setSize(700, 400);
    display.setLocationRelativeTo(null);
    display.setVisible(true);
    display.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
  }

  @Override
  public void confirmFixedInvestment(String s) {

    JFrame display = new JFrame("Confirm fixed investment");
    JPanel mainPanel = new JPanel();
    display.getContentPane().add(mainPanel);
    mainPanel.setBackground(Color.black);
    JLabel myLabel = new JLabel(s);
    myLabel.setForeground(Color.green);
    mainPanel.add(myLabel);
    backToMainMenu5 = new JButton("Ok");
    backToMainMenu5.setActionCommand("Back to main menu 5");
    myMap.put("Back to main menu 5", 55);
    this.setListener(myListner);
    mainPanel.add(backToMainMenu5);
    mainPanel.setBackground(Color.black);
    display.setSize(700, 400);
    display.setLocationRelativeTo(null);
    display.setVisible(true);
    display.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);


  }

  @Override
  public void getPortfolioVal() {
    JFrame display = new JFrame("Get portfolio value");
    JPanel mainPanel = new JPanel();
    display.getContentPane().add(mainPanel);
    JPanel menu = new JPanel(new GridBagLayout());
    menu.setBackground(Color.black);
    mainPanel.add(menu);
    GridBagConstraints c = new GridBagConstraints();
    c.gridx = 0;
    c.gridy = 0;
    c.anchor = GridBagConstraints.LINE_START;
    JLabel label1 = new JLabel("Enter the name of the portfolio whose value you wish to know =>");
    label1.setForeground(Color.green);
    menu.add(label1, c);
    c.gridy++;
    JLabel label2 = new JLabel("Enter the date on which you wish to know the value =>");
    label2.setForeground(Color.green);
    menu.add(label2, c);
    c.gridx = 1;
    c.gridy = 0;
    menu.add(PORTFOLIO_NAME, c);
    c.gridy++;
    menu.add(ENTER_START_DATE, c);
    c.gridy++;
    portfolioValue = new JButton("Ok");
    portfolioValue.setActionCommand("Get the value");
    myMap.put("Get the value", 72);
    this.setListener(myListner);
    menu.add(portfolioValue, c);

    mainPanel.setBackground(Color.black);
    display.setSize(700, 400);
    display.setLocationRelativeTo(null);
    display.setVisible(true);
    display.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

  }

  @Override
  public void getPortfolioValFrame1(String s) {

    JFrame display = new JFrame("Get portfolio value");
    JPanel mainPanel = new JPanel();
    display.getContentPane().add(mainPanel);
    mainPanel.setBackground(Color.black);

    JLabel label1 = new JLabel("$" + s);
    label1.setForeground(Color.green);
    mainPanel.add(label1);
    backToMainMenu6 = new JButton("Ok");
    backToMainMenu6.setActionCommand("Back to main menu 6");
    myMap.put("Back to main menu 6", 71);
    this.setListener(myListner);
    mainPanel.add(backToMainMenu6);

    mainPanel.setBackground(Color.black);
    display.setSize(700, 400);
    display.setLocationRelativeTo(null);
    display.setVisible(true);
    display.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);


  }


  @Override
  public void welcomeMessage() {
    //method for text based view
  }

  @Override
  public void userNamePrompt() {
    //method for text based view
  }

  @Override
  public void passwordPrompt() {
    //method for text based view
  }

  @Override
  public Appendable userLoginSuccess(Appendable out) {
    return null;
  }

  @Override
  public Appendable loginFailure(Appendable out) {
    return null;
  }

  @Override
  public void mainMenu() {
    //method for text based view
  }

  @Override
  public Appendable quitMessage(Appendable out) {
    return null;
  }

  @Override
  public Appendable invalidCommand(Appendable out) {
    return null;
  }

  @Override
  public void createPortfolioMsg() {
    //method for text based view
  }

  @Override
  public Appendable successfulPortfolioCreation(Appendable out) {
    return null;
  }

  @Override
  public void listMsg() {
    //method for text based view
  }

  @Override
  public Appendable getPortfolioList(Appendable out) {
    return null;
  }

  @Override
  public void getStockInfo() {
    //method for text based view
  }

  @Override
  public void enterDate() {
    //method for text based view
  }

  @Override
  public Appendable getStockInfo1(Appendable out) {
    return null;
  }

  @Override
  public void invalidInfo() {
    //method for text based view
  }

  @Override
  public void addSymbol() {
    //method for text based view
  }

  @Override
  public void addDate() {
    //method for text based view
  }

  @Override
  public void enterTime() {
    //method for text based view
  }

  @Override
  public void enterPortfolioName() {
    //method for text based view
  }

  @Override
  public void enterAmount() {
    //method for text based view
  }

  @Override
  public void enterName() {
    //method for text based view
  }

  @Override
  public void errorMsg() {
    //method for text based view
  }

  @Override
  public Appendable getInfoOnAday(Appendable out) {
    return null;
  }

  @Override
  public Appendable errMsg1(Appendable out) {
    return null;
  }

  @Override
  public Appendable readPortfolio(Appendable out) {
    return null;
  }

  @Override
  public void costPerBasisPortfolio() {
    //method for text based view
  }

  @Override
  public Appendable getCostPerBasisPortfolio(Appendable out) {
    return null;
  }

  @Override
  public void errMsg2() {
    //method for text based view
  }

  @Override
  public void addSymbol1() {
    //method for text based view
  }

  @Override
  public void addPortfolioName2() {
    //method for text based view
  }

  @Override
  public Appendable getStockCostBasis(Appendable out) {
    return null;
  }

  @Override
  public void errMsg3() {
    //method for text based view
  }

  @Override
  public void enterName2() {
    //method for text based view
  }

  @Override
  public Appendable getNetworth(Appendable out) {
    return null;
  }

  @Override
  public void addDate1() {
    //method for text based view
  }

  @Override
  public void errMsg4() {
    //method for text based view
  }

  @Override
  public void commissionPercentage() {
    //method for text based view
  }

  @Override
  public void customWeights() {
    //method for text based view
  }

  @Override
  public Appendable addCustomWeights(Appendable out) {
    return null;
  }

  @Override
  public void enterName3() {
    //method for text based view
  }

  @Override
  public void enterStartDate() {
    //method for text based view
  }

  @Override
  public void enterEndDate() {
    //method for text based view
  }

  @Override
  public void enterDuration() {
    //method for text based view
  }

  @Override
  public void enterFixedInvestment() {
    //method for text based view
  }

  @Override
  public void errMsg5() {
    //method for text based view
  }

  @Override
  public void revertToMainMenu() {
    //method for text based view
  }

  @Override
  public void enterWeightDistribution() {
    //method for text based view
  }

  /**
   * A helper function for our graphical user interface.
   *
   * @param c gridbag constrains layout for our form in the GUI
   * @param menu menu panel to add elements to the form
   */
  private void helper1(GridBagConstraints c, JPanel menu) {
    c.gridx = 0;
    c.gridy = 0;
    c.anchor = GridBagConstraints.LINE_START;
    String s = "Welcome back ";
    String s1 = USER_NAME.getText();
    JLabel welcomeMsg = new JLabel();
    welcomeMsg.setText(s + s1);
    welcomeMsg.setForeground(Color.green);
    menu.add(welcomeMsg, c);
    c.gridy++;
    JLabel createPfMsg = new JLabel(
        "Create a new portfolio for your investments: =>");
    createPfMsg.setForeground(Color.green);
    menu.add(createPfMsg, c);
    c.gridy++;
    JLabel msg = new JLabel(
        "See the list of all your existing portfolios: =>");
    msg.setForeground(Color.green);
    menu.add(msg, c);
    c.gridy++;
    JLabel msg1 = new JLabel(
        "Purchase stock without setting up custom weight distribution: =>");
    msg1.setForeground(Color.green);
    menu.add(msg1, c);
    c.gridy++;
    JLabel msg2 = new JLabel("Set up a fixed investment plan for one of your portfolios: =>");
    msg2.setForeground(Color.green);
    menu.add(msg2, c);
    c.gridy++;
    JLabel msg3 = new JLabel(
        "Click the button on the right to get the costbasis of a porfolio: =>");
    msg3.setForeground(Color.GREEN);
    menu.add(msg3, c);
    c.gridy++;
    JLabel msg4 = new JLabel("Read the contents of an existing portfolio: =>");
    msg4.setForeground(Color.green);
    menu.add(msg4, c);
    c.gridy++;
    JLabel msg5 = new JLabel("Get the total value of a portfolio on any given day: =>");
    msg5.setForeground(Color.green);
    menu.add(msg5, c);
    c.gridy++;
    JLabel msg6 = new JLabel("Exit the simulator: =>");
    msg6.setForeground(Color.green);
    menu.add(msg6, c);


  }

  /**
   * A helper function for our graphical user interface.
   *
   * @param c gridbag constrains layout for our form in the GUI
   * @param menu menu panel to add elements to the form
   */
  private void helper2(GridBagConstraints c, JPanel menu) {
    c.gridy = 1;
    c.gridx = 1;
    c.anchor = GridBagConstraints.LINE_END;
    createPortfolio = new JButton("Create Portfolio");
    createPortfolio.setActionCommand("Create new portfolio");
    menu.add(createPortfolio, c);
    myMap.put("Create new portfolio", 1);
    this.setListener(myListner);
    c.gridy++;
    portfolioLists = new JButton("Portfolios List");
    portfolioLists.setActionCommand("View List");
    menu.add(portfolioLists, c);
    myMap.put("View List", 4);
    this.setListener(myListner);
    c.gridy++;
    purchase = new JButton("Purchase");
    purchase.setActionCommand("Purchase");
    menu.add(purchase, c);
    myMap.put("Purchase", 5);
    this.setListener(myListner);
    c.gridy++;
    fixedInvestment = new JButton("Inv. Strategy");
    fixedInvestment.setActionCommand("Fixed Investment");
    menu.add(fixedInvestment, c);
    myMap.put("Fixed Investment", 8);
    this.setListener(myListner);
    c.gridy++;
    getCostPerBasis = new JButton("Get cost basis");
    getCostPerBasis.setActionCommand("Cost basis");
    menu.add(getCostPerBasis, c);
    myMap.put("Cost basis", 100);
    this.setListener(myListner);
    c.gridy++;
    readPortfolio = new JButton("Read portfolio");
    readPortfolio.setActionCommand("Read portfolio");
    menu.add(readPortfolio, c);
    myMap.put("Read portfolio", 40);
    this.setListener(myListner);
    c.gridy++;
    getPortfolioValue = new JButton("Get portfolio val");
    getPortfolioValue.setActionCommand("Get val");
    myMap.put("Get val", 75);
    menu.add(getPortfolioValue, c);
    this.setListener(myListner);
    c.gridy++;
    exit = new JButton("Exit");
    exit.setActionCommand("Exit");
    myMap.put("Exit", 100);
    menu.add(exit, c);
    this.setListener(myListner);
  }


  /**
   * A helper function for our GUI.
   *
   * @param listener Action listener for button click events
   */
  private void helper7(ActionListener listener) {

    if (myMap.containsKey("Create new portfolio")) {
      createPortfolio.addActionListener(listener);
    }
    if (myMap.containsKey("Successfully created new portfolio!")) {
      portfolioCreationSuccess.addActionListener(listener);
    }
    if (myMap.containsKey("PortfolioOperations Created")) {
      portfolioCreationExit.addActionListener(listener);
    }

    if (myMap.containsKey("View List")) {
      portfolioLists.addActionListener(listener);
    }

    if (myMap.containsKey("Back to main menu1")) {
      listCreate.addActionListener(listener);
    }

    if (myMap.containsKey("Purchase")) {
      purchase.addActionListener(listener);
    }

    if (myMap.containsKey("Buy Stock")) {
      makePurchase.addActionListener(listener);
    }

    if (myMap.containsKey("Back to main menu2")) {
      backToMain.addActionListener(listener);
    }
    if (myMap.containsKey("Fixed Investment")) {
      fixedInvestment.addActionListener(listener);
    }
  }

  /**
   * A helper function for our GUI.
   *
   * @param listener Action listener for button click events
   */
  private void helper8(ActionListener listener) {

    if (myMap.containsKey("Decision")) {
      customWeightDecision.addActionListener(listener);
    }

    if (myMap.containsKey("Decision1")) {
      decision1.addActionListener(listener);
    }

    if (myMap.containsKey("Set Weights")) {
      setWeights.addActionListener(listener);
    }
    if (myMap.containsKey("Fixed Investment Form")) {
      fixedInvestmentForm.addActionListener(listener);
    }

    if (myMap.containsKey("Weights Failed")) {
      setWeightsFailed.addActionListener(listener);
    }

    if (myMap.containsKey("Fixed Investment Form1")) {
      fixedInvestmentForm.addActionListener(listener);
    }

    if (myMap.containsKey("Cost basis")) {
      getCostPerBasis.addActionListener(listener);
    }

    if (myMap.containsKey("Calculate1")) {
      calculateCostBasis.addActionListener(listener);
    }

    if (myMap.containsKey("Back to main menu 3")) {
      backtoMainMenu2.addActionListener(listener);
    }
  }

  /**
   * A helper function for our GUI.
   *
   * @param listener Action listener for button click events
   */
  private void helper9(ActionListener listener) {
    if (myMap.containsKey("Read portfolio")) {
      readPortfolio.addActionListener(listener);
    }
    if (myMap.containsKey("Read1")) {
      readPortfolio1.addActionListener(listener);
    }
    if (myMap.containsKey("Back to main menu 4")) {
      backtoMainMenu4.addActionListener(listener);
    }

    if (myMap.containsKey("Complete")) {
      completeFixedInvestment.addActionListener(listener);
    }

    if (myMap.containsKey("Back to main menu 5")) {
      backToMainMenu5.addActionListener(listener);
    }

    if (myMap.containsKey("Get val")) {
      getPortfolioValue.addActionListener(listener);
    }
    if (myMap.containsKey("Get the value")) {
      portfolioValue.addActionListener(listener);
    }

    if (myMap.containsKey("Back to main menu 6")) {
      backToMainMenu6.addActionListener(listener);
    }
    if (myMap.containsKey("Exit")) {
      exit.addActionListener(listener);
    }

  }

  /**
   * A helper function for our graphical user interface.
   *
   * @param c gridbag constrains layout for our form in the GUI
   * @param menu menu panel to add elements to the form
   */
  private void helper3(GridBagConstraints c, JPanel menu) {

    c.gridx = 0;
    c.gridy = 0;
    c.anchor = GridBagConstraints.LINE_START;
    JLabel label1 = new JLabel("Enter the ticker symbol of the stock you wish to purchase =>");
    label1.setForeground(Color.green);
    menu.add(label1, c);
    c.gridy++;
    JLabel label2 = new JLabel("Enter the time of the day in 24 hour format =>");
    label2.setForeground(Color.green);
    menu.add(label2, c);
    c.gridy++;
    JLabel label3 = new JLabel("Enter the date in yyyy-mm-dd format =>");
    label3.setForeground(Color.green);
    menu.add(label3, c);
    c.gridy++;
    JLabel label4 = new JLabel("Enter the portfolio name wherein to add the purchase =>");
    label4.setForeground(Color.green);
    menu.add(label4, c);
    c.gridy++;
    JLabel label5 = new JLabel("Enter the amount in dollars you wish to invest =>");
    label5.setForeground(Color.green);
    menu.add(label5, c);
    c.gridy++;
    JLabel label6 = new JLabel("Enter the commission fee(%) for this transaction =>");
    label6.setForeground(Color.green);
    menu.add(label6, c);


  }


  /**
   * A helper function for our graphical user interface.
   *
   * @param c gridbag constrains layout for our form in the GUI
   * @param menu menu panel to add elements to the form
   */
  private void helper4(GridBagConstraints c, JPanel menu) {
    c.gridx = 1;
    c.gridy = 0;

    c.anchor = GridBagConstraints.LINE_END;
    menu.add(TICKER_SYMBOL, c);
    c.gridy++;
    menu.add(TIME_OF_THE_DAY, c);
    c.gridy++;
    menu.add(STRING_DATE, c);
    c.gridy++;
    menu.add(PORTFOLIO_NAME, c);
    c.gridy++;
    menu.add(DOLLAR_AMOUNT, c);
    c.gridy++;
    menu.add(COMMISSION_FEE, c);
    c.gridy++;
    makePurchase = new JButton("Buy stock");
    makePurchase.setActionCommand("Buy Stock");
    myMap.put("Buy Stock", 7);
    this.setListener(myListner);
    menu.add(makePurchase, c);

  }

  /**
   * A helper function for our graphical user interface.
   *
   * @param c gridbag constrains layout for our form in the GUI
   * @param menu menu panel to add elements to the form
   */
  private void helper5(GridBagConstraints c, JPanel menu) {
    c.gridx = 0;
    c.gridy = 0;
    c.anchor = GridBagConstraints.LINE_START;
    JLabel label1 = new JLabel("Enter the name of the portfolio for the fixed investment =>");
    label1.setForeground(Color.green);
    menu.add(label1, c);
    c.gridy++;
    JLabel label2 = new JLabel("Enter the start date as yyyy-mm-dd for your investment plan =>");
    label2.setForeground(Color.green);
    menu.add(label2, c);
    c.gridy++;
    JLabel label3 = new JLabel("Enter the date end date. Enter 'NA' for no fixed end date =>");
    label3.setForeground(Color.green);
    menu.add(label3, c);
    c.gridy++;
    JLabel label4 = new JLabel("Enter the interval for this periodic investment =>");
    label4.setForeground(Color.green);
    menu.add(label4, c);
    c.gridy++;
    JLabel label5 = new JLabel("Enter the amount in dollars you wish to invest =>");
    label5.setForeground(Color.green);
    menu.add(label5, c);
    c.gridy++;
    JLabel label6 = new JLabel("Enter the commission fee(%) for this transaction =>");
    label6.setForeground(Color.green);
    menu.add(label6, c);
  }

  /**
   * A helper function for our graphical user interface.
   *
   * @param c gridbag constrains layout for our form in the GUI
   * @param menu menu panel to add elements to the form
   */
  private void helper6(GridBagConstraints c, JPanel menu) {

    c.gridx = 1;
    c.gridy = 0;

    c.anchor = GridBagConstraints.LINE_END;
    menu.add(ENTER_PORTFOLIO_NAME, c);
    c.gridy++;
    menu.add(ENTER_START_DATE, c);
    c.gridy++;
    menu.add(ENTER_END_DATE, c);
    c.gridy++;
    menu.add(ENTER_INVESTMENT_DURATION, c);
    c.gridy++;
    menu.add(DOLLAR_AMOUNT, c);
    c.gridy++;
    menu.add(COMMISSION_FEE, c);
    c.gridy++;
  }


}
