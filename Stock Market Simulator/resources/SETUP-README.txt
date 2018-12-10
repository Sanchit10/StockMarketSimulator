This file contains the setup instructions for running Assignment8_5010.jar,
by Sanchit Saini and James Borzillieri.

---------------------------------------------------------------------------------------


Our implementation allows for multiple views to co-exist. Both text based and graphical based views can be run from the command line using the jar file which we are submitting.

In order to do that you need to 
1) Navigate to folder containing Assignment10_5010.jar
2) In order to run the program with a text based view in the console run the below mentioned command 
'java -jar Assignment10_5010.jar console'


3)In order to run the program with a graphical user interface run the below mentioned command  
'java -jar Assignment10_5010.jar gui'

---------------------------------------------------------------------------------------

Our implementation can store data from multiple users, though there is
currently no functionality which allows users to switch between profiles while
running the program.

The human user will be prompted to enter a username/password combination upon
execution of the .jar file, which requires only the following to run:

  1) Navigate to folder containing Assignment8_5010.jar
  2) Run the command java -jar Assignment8_5010.jar

---------------------------------------------------------------------------------------

Users are setup in the driver method (Main.class). Accessible users are
indicated by the following username/password combinations:

  user1/123
  user2/123
  user3/123
  user4/123
  user5/123

---------------------------------------------------------------------------------------

After login, user will be greeted and prompted with the following options:

  Enter 1 to create a new portfolio
  Enter 2 to view all your portfolios
  Enter 3 to get the stock info of your choice for any given date
  Enter 4 to buy shares of a stock and add it to any portfolio of your choice
  Enter 5 to read one of your existing portfolios
  Enter 6 to get the cost per basis of any of your portfolios
  Enter 7 to get the cost per basis of any stock in any of your existing
          portfolios
  Enter 8 to get the net worth of your portfolio for any given day in the
          stock market.If you wish to know the current value, please enter
          today's date
  Enter 9 to setup an existing portfolio in accordance with a high-level investment strategy 
  (Dollar-cost averaging)
  Enter q/Q to quit the simulator

---------------------------------------------------------------------------------------

TESTING
Due to limited allowable calls by AlphaVantage, it is necessary in many cases to run tests
individually rather than the entire batch at once.

---------------------------------------------------------------------------------------

This concludes the setup instructions for Assignment8_5010.jar