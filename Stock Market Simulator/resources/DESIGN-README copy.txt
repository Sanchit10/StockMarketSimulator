


									Stock Market Simulator (Most Recent Project for one of the courses I was enrolled in [Programming design Paradigms])


---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
Update for assignment 10:


=> A new class, solemnly for the purpose of implementing the controller for the GUI was created. We could have implemented the graphical user interface with the same controller implementation class as before but choose not to in order to keep everything cleaner, manageable and more readable.

=> New methods for the graphical user interface were added in the view implementation. The previous view implementation file that was used to implement the view as a text based console contained default values for the newly added methods.The new 'GUI' class that was used for implementing our graphical user interface implemented the new methods and also consisted of the previous methods from the text based view but with their default values. No other design changes were required for the controller and the view to incorporate these new features.

=> Also as explained previously, before implementing persistence, we were using various hash maps and arrays to store information while the program was run and portfolios were created, purchases made and investment strategies configured. Since all of those features now save this information in text files in the project directory itself, I no longer needed any of the above mentioned data structures. Also I needed to add code for fetching this information from the text files. To achieve all of this, I ended up making removing the holding class(which was used to store information for a single instance while the program was run) and did most of the refactoring in the java class named as 'Portfolio' before this change and ended up renaming it as 'PortfolioOperations' since I believed it was a more appropriate name for what that class was achieving. No other changes to the existing classes in the model were made.

---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
Update for assignment 9:

Changes to SMSimulatorModel:
-A holding class was added due to the fact that the portfolio class was becoming cluttered with hashmaps, particularly with the addition of weights to user holdings.
-The following methods were added to SMSimulatorModelImpl:
 	investFixedAmount(): this method is called to execute a scheduled (or number of scheduled) investments at certain intervals, over a period of time. The User class handles
											 behavior associated with different weights.

	setWeights():        this method sets custom weights to be defined by the user.	These weights are assigned and then reference when making fixed investments with the prior method.

	setEvenWeights():    this method reverts the desired portfolio back to its default configuration, handling each portfolio as though its holdings have even weights.

-A number of helper functions were also defined and invoked to help with time and date management (i.e. rewindDate() and fastForwardDate()) for handling scheduled investments on
 weekends and holidays. All remaining holidays for 2018 (from the current date) are covered by our implementation.

 Changes to View:
 -A view was added to this implementation that handles appendable objects by interacting with the model through the controller in a principled manner. the view cannot interact with the model
 without the controller. The view cannot mutate the model.


---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------

1) The program allows multiple users to login, if the user records have a valid username and password.The user can create new portfolios, get current stock info, get stock info at a previous date by simply entering the ticker symbol and date. No bad inputs will crash the program. The user is not expected to type in any long complex inputs to get the program working. Also, the user can buy stock for respective portfolios at ant given date (when the market is open for business and only during working hours), calculate the cost per basis of any stock in any portfolio or any entire portfolio in general. Also, the user can get to know the value of his portfolios for any given day (current/past) by simply entering the date and the portfolio name.Simplicity of use for the end user(client) is at the heart of the design for our stock market simulator.Summing it up in a nutshell, our program offers the following functionalities-

a) Allow users to login with their own usernames and password. Invalid usernames and passwords cannot use our stock market simulator.
b) Allow users to create custom portfolios.
c) Allow users to get a list of these custom portfolios.
d) Allow users to get the any stock info for any date. For the present day, the user is expected to enter the current date.
e) Allow users to buy shares of any stock on any given day(except holidays, weekends, market closing hours) and add the purchase to any of the existing portfolios.
f) Allow users to examine the composition of any of their portfolios.
g) Allow users to get the total cost per basis of any of their portfolios.
h) Allow users to get the individual cost per basis of any stock in any of the existing portfolios.
i) Allow users to get the current value of any of their existing portfolios i.e. the current money the user would get if he wishes to sell all the stocks in any existing portfolio on any given date(in the past or current).

The stock market simulator also handles all the bad inputs, invalid inputs that the user might enter.It also does not expect the user to enter long complex inputs.

2)  Create an interface for the model that I wished to implement.This model implementation implements all the desired functionalities of our simulator application. I have a separate user file, because different users will have different amounts of money they'll be willing to spend, have unique number of portfolios and have unique compositions in those portfolios as well(everything is subjective!). For this very reason, I have a portfolio class that takes care of our logic to create new portfolios for any user and add purchased shares of any stock to one of their existing portfolios.

3) A controller interface that consists of a function named as runStockMarketSimulation that is implemented in the controller class. The controller class has a constructor that takes in readable and appendable objects which are further used to take in input from the user, use that input(the readable object) to interact with the model through the controller without mutating the model and use the result of that interaction to output it to the console through the appendable object. The entire implementation has been throughly tested by writing extensive Junit tests.The user is not able to mutate the model through any of the entered inputs whatsoever.


