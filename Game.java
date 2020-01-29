package FKApplyDesign;

import java.util.Scanner;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;

public class Game {

    Map<Integer, ArrayList<Board> > gameState = new HashMap<>();
    public static final int noWinner = -1;

    void setGameState(int gameN, Board game)  {

        Integer gameNo = new Integer(gameN);
        if (gameState.containsKey(gameNo)) {
            gameState.get(gameNo).add(game);
        }
        else {
            ArrayList<Board> temp = new ArrayList<>();
            temp.add(game);
            gameState.put(gameNo, temp);
        }
    }

    boolean gameOver(String boardChoice, Board gameBoard, int noOfConnectionsToWin) {
        if (boardChoice.equals("H")) {
            return  (gameBoard.rowCrossed(noOfConnectionsToWin) || gameBoard.diagonalCrossed(noOfConnectionsToWin));
        }
        else {
            return (gameBoard.rowCrossed(noOfConnectionsToWin) || gameBoard.colCrossed() || gameBoard.diagonalCrossed(noOfConnectionsToWin));
        }
    }

    int playGame(String boardChoice, int noOfConnectionsToWin, int gameNo, Board gameBoard, Player[] listOfPlayers, int rowSize, int colSize) {
        int noOfValidMoves = rowSize * colSize;
        int noOfMovesdone = 0;
        int whoseTurn = 0;
        int moveX;
        int moveY;
        int noOfPlayers = listOfPlayers.length;
        Player whoMoves = listOfPlayers[whoseTurn];

        while(gameOver(boardChoice, gameBoard, noOfConnectionsToWin) == false && noOfMovesdone < noOfValidMoves) {
            moveX = whoMoves.rowMove(rowSize);
            moveY = whoMoves.colMove(colSize);

            while(whoMoves.wantUndo()) {
                System.out.println("Replay your move");
                moveX = whoMoves.rowMove(rowSize);
                moveY = whoMoves.colMove(colSize);
            }

            while(gameBoard.makeMove(whoseTurn, moveX, moveY) != 1) {
                moveX = whoMoves.rowMove(rowSize);
                moveY = whoMoves.colMove(colSize);
            }

            setGameState(gameNo, gameBoard);

            whoseTurn = (whoseTurn+1)%noOfPlayers;
            whoMoves = listOfPlayers[whoseTurn];
            noOfMovesdone++;
        }

        if (gameOver(boardChoice, gameBoard, noOfConnectionsToWin) == false && noOfMovesdone == noOfValidMoves) {
            System.out.println("Game is a draw");
            gameBoard.showBoard();
            return -1;
        }
        else {
            if (whoseTurn == 0) {
                whoseTurn = noOfPlayers - 1;
            }
            else {
                whoseTurn = (whoseTurn - 1) % noOfPlayers;
            }
            System.out.println("The Winner is Player "+whoseTurn);
            gameBoard.showBoard();
            return whoseTurn;
        }

    }

    int playEnhancedGame(String boardChoice, String irregularChoice, int noOfCellsToBeDestroyed, int noOfConnectionstoWin, int gameNo, Player[] listOfPlayers, int rowSize, int colSize, int boardSize) {
        int tempWinner;

        if (boardSize == rowSize) {

            Board gameBoard = new Board();
            gameBoard.setRow_size(rowSize);
            gameBoard.setCol_size(colSize);

            gameBoard.initializeBoard();

            if (irregularChoice.equals("I")) {
                gameBoard.makeBoardIrregular(noOfCellsToBeDestroyed);
            }

            tempWinner = playGame(boardChoice, noOfConnectionstoWin, gameNo, gameBoard, listOfPlayers, rowSize, colSize);

            return tempWinner;
        }
        else {
            Board enhancedGameBoard = new Board();
            enhancedGameBoard.setRow_size(rowSize);
            enhancedGameBoard.setCol_size(colSize);
            enhancedGameBoard.initializeBoard();

            for (int row=0;row<rowSize;row++) {
                for (int col=0;col<colSize;col++) {
                    enhancedGameBoard.makeMove(playEnhancedGame(boardChoice, irregularChoice, noOfCellsToBeDestroyed, noOfConnectionstoWin, gameNo, listOfPlayers, rowSize, colSize, boardSize/rowSize), row, col);
                    if (gameOver(boardChoice, enhancedGameBoard, noOfConnectionstoWin)) {
                        return enhancedGameBoard.getCell(row, col);
                    }
                }
            }

            return noWinner;
        }
    }

    void showLeaderBoard(ArrayList<Integer> leaderboard, int gameNo) {
        System.out.println("The Leaderboard is : ");
        for (int i=0;i<gameNo;i++) {
            System.out.println("The Game No. "+(i+1)+" won by : "+leaderboard.get(i));
        }
    }

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        Game newGame = new Game();

        System.out.println("Enter Board Row Size");
        int rowSize = sc.nextInt();
        System.out.println("Enter Board Column Size");
        int colSize = sc.nextInt();

        Board gameBoard = new Board();

        gameBoard.setRow_size(rowSize);
        gameBoard.setCol_size(colSize);

        gameBoard.initializeBoard();

        System.out.println("Enter I to make the Board irregular");
        String extra = sc.nextLine();
        String irregularChoice = sc.nextLine();

        int noOfCellsToBeDestroyed = 0;
        int noOfConnectionsToWin = rowSize;

        if (irregularChoice.equals("I")) {
            System.out.println("Enter no of cells to be destroyed");
            noOfCellsToBeDestroyed = sc.nextInt();
            gameBoard.makeBoardIrregular(noOfCellsToBeDestroyed);

            System.out.println("Enter winning criterion in terms of no on connections");
            noOfConnectionsToWin = sc.nextInt();
        }

        System.out.println("enter number of Players");
        int noOfPlayers = sc.nextInt();

        Player[] listOfPlayers = new Player[noOfPlayers];

        System.out.println("For Each Player, Enter H for human and M for Machine");
        int playerId = 0;
        String gameChoice, boardChoice, choice ;
        Human tempHuman;
        Machine tempMachine;

        for (int iter=0;iter<noOfPlayers;iter++) {
            choice = sc.nextLine();
            if (choice.equals("H")) {
                tempHuman = new Human();
                tempHuman.setPlayerId(playerId);
                listOfPlayers[playerId] = tempHuman;
                playerId++;
            }
            else if (choice.equals("M")) {
                tempMachine = new Machine();
                tempMachine.setPlayerId(playerId);
                listOfPlayers[playerId] = tempMachine;
                playerId++;
            }
            else {
                System.out.println("Invalid Choice, please enter again");
                iter--;
            }
        }

        ArrayList<Integer> leaderboard = new ArrayList<>();
        int gameNo = 0;

        String continuePlaying = "Y";

        while(continuePlaying.equals("Y")) {

            System.out.println("Enter H to play Hexagonal TicTacToe");
            boardChoice = sc.nextLine();

            System.out.println("Enter Y to play enhanced Tictactoe");
            gameChoice = sc.nextLine();

            int winner;

            if (gameChoice.equals("Y")) {
                System.out.println("Enter the board size in terms of power of row or column size");
                int boardSize = sc.nextInt();

                winner = newGame.playEnhancedGame(boardChoice, irregularChoice, noOfCellsToBeDestroyed, noOfConnectionsToWin, gameNo, listOfPlayers, rowSize, colSize, boardSize);
                if (winner == -1) {
                    System.out.println("The Game is a draw");
                }
                else {
                    System.out.println("The Winner of the Enhanced TicTacToe is " +winner);
                }
                choice = sc.nextLine();
            }
            else {
                winner = newGame.playGame(boardChoice, noOfConnectionsToWin, gameNo, gameBoard, listOfPlayers, rowSize, colSize );
            }

            leaderboard.add(winner);
            gameNo = gameNo + 1;

            System.out.println("To Continue Playing Type Y");
            continuePlaying = sc.nextLine();
            System.out.println("Enter L to see leadeboard");
            choice = sc.nextLine();
            if (choice.equals("L")) {
                newGame.showLeaderBoard(leaderboard, gameNo);
            }
        }

        System.out.println("Enter Y to see saved game states");
        choice = sc.nextLine();

        if (choice.equals("Y")) {
            System.out.println("Enter Game No. to view its states");
            int ch = sc.nextInt();
            System.out.println(ch);
            Integer cho = new Integer(ch);
                ArrayList<Board> states = newGame.gameState.get(cho);
                for (Board iter : states) {
                    iter.showBoard();
                }
        }
    }
}
