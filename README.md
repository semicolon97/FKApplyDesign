# FKApplyDesign
There are Three .java files in the project, with each file containing the corresponding class.
1. Board Class - This class simulates the game board. 
    Attributes - 
      1. rowSize : To store no of rows in the board
      2. colSize : To store no of columns in the board
      3. board[rowSize][colSize] : To store the actual board with all cell values
    Methods -
      1. Getters and Setters to access attributes
      2. getCell(int x, int y) to return the value stored in the board at location (x,y)
      3. initializeBoard() to initialize all cells in the board attribute with some initial value
      4. makeMove(int player, int x, int y) to make a move by Player player in the cell (x,y)
2. Player Class - This class simulates the players who can play the game. It is an Abstract class.
    Attributes - 
      1. playerId : To store the Id of each player
    Methods - 
      1. Getters and Setters to access attributes
      2. rowMove(rowSize) : Abstract method to simulate row selection by the player
      3. colMove(colSize) : Abstract method to simulate column selection by the player
3. Human Class - This class inherits from Player class.
    Attributes - There are no attributes except playerId inherited from Player.
    Methods - 
      1. rowMove(rowSize) : Method to implement the abstract method from the parent abstract class Player.
                            User input is used to select the row move.
      2. colMove(colSize) : Method to implement the abstract method from the parent abstract class Player.
                            User input is used to select the column move.
4. Machine Class - This class inherits from Player class.
    Attributes - There are no attributes except playerId inherited from Player.
    Methods - 
      1. rowMove(rowSize) : Method to implement the abstract method from the parent abstract class Player.
                            Random class is used to select the row move.
      2. colMove(colSize) : Method to implement the abstract method from the parent abstract class Player.
                            Random class is used to select the column move.
5. Game Class - This class is used to simulate the actual game by defining the players who play, the board
                on which the game is to be played and the rules of the game.
    Attributes - There are no attributes.
    Methods - 
      1. rowCrossed(gameBoard, rowSize, colSize) : Method to check if any player has moved on all cells of a row.
                                                   Returns true if it is so.
      2. colCrossed(gameBoard, rowSize, colSize) : Method to check if any player has moved on all cells of a column.
                                                   Returns true if it is so.
      3. diagonalCrossed(gameBoard, rowSize, colSize) : Method to check if any player has moved on all cells of a diagonal.
                                                   Returns true if it is so.
      4. gameOver(gameBoard, rowSize, colSize) : MEthod to check if the game is over, that is, some player has moved on all
                                                 cells of a particular row, column or diagonal.
      5. showBoard(gameBoard) : Method to display the board to the user when called
      6. main(String[] args) : This method implements the overall logic by creating the requested no of players, creating an
                               object of Board class with requested attributes, and implementing the game logic.
 Input Format :
 Enter Board Row Size
3
Enter Board Column Size
3
enter number of Players
2
For Each Player, Enter H for human and M for Machine
Invalid Choice, please enter again
H
H
Enter row number of your move within range 0 - 2
2
Enter column number of your move within range 0 - 2
0
-1 -1 -1 
-1 -1 -1 
0 -1 -1 
Enter row number of your move within range 0 - 2
0
Enter column number of your move within range 0 - 2
2
-1 -1 1 
-1 -1 -1 
0 -1 -1 
Enter row number of your move within range 0 - 2
1
Enter column number of your move within range 0 - 2
1
-1 -1 1 
-1 0 -1 
0 -1 -1 
Enter row number of your move within range 0 - 2
2
Enter column number of your move within range 0 - 2
2
-1 -1 1 
-1 0 -1 
0 -1 1 
Enter row number of your move within range 0 - 2
1
Enter column number of your move within range 0 - 2
0
-1 -1 1 
0 0 -1 
0 -1 1 
Enter row number of your move within range 0 - 2
1
Enter column number of your move within range 0 - 2
2
-1 -1 1 
0 0 1 
0 -1 1 

OutPut Format :
The Winner is Player 1


Design Flaws :
The Game class is too tightly coupled with the game logic of this case. Separate methods to implement the game logic would
reduce this coupling and make the code more resilient to requirement changes.
