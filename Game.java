package FKApplyDesign;

import java.util.Scanner;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;

public class Game {

    Map<Integer, ArrayList<Board> > gameState = new HashMap<>();

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

    boolean newRowCrossed(int [][] winners, int rowSize, int colSize) {
        int initialLoc, flag = 1;
        for (int i = 0 ; i < rowSize ; i++) {
            flag = 1;
            initialLoc = winners[i][0];
            if (initialLoc == -1) {
                flag = 0;
                continue;
            }
            for (int j = 0 ; j < colSize ; j++) {
                if (winners[i][j] != initialLoc) {
                    flag = 0;
                    break;
                }
            }
            if (flag==1) {
                break;
            }
        }

        if (flag == 1) {
            return true;
        }
        else {
            return false;
        }
    }

    boolean newColCrossed(int[][] winners, int rowSize, int colSize) {
        int initialLoc, flag = 1;
        for (int i = 0 ; i < colSize ; i++) {
            flag = 1;
            initialLoc = winners[0][i];
            if (initialLoc == -1) {
                flag = 0;
                continue;
            }
            for (int j = 0 ; j < rowSize ; j++) {
                if (winners[j][i] != initialLoc) {
                    flag = 0;
                    break;
                }
            }
            if (flag==1) {
                break;
            }
        }

        if (flag == 1) {
            return true;
        }
        else {
            return false;
        }
    }

    boolean newDiagonalCrossed(int[][] winners, int rowSize, int colSize) {
        int initialLoc, flag = 1;
        initialLoc = winners[0][0];

        if (initialLoc != -1) {
            for (int i=0;i<rowSize;i++) {
                if (winners[i][i]!=initialLoc) {
                    flag = 0;
                    break;
                }
            }
        }

        if (initialLoc != -1 && flag == 1) {
            return true;
        }

        flag = 1;
        initialLoc = winners[rowSize-1][0];
        if (initialLoc != -1) {
            for (int i=0;i<rowSize;i++) {
                if (winners[rowSize-i-1][i]!=initialLoc) {
                    flag = 0;
                    break;
                }
            }
        }

        if (initialLoc != -1 && flag == 1) {
            return true;
        }

        return false;
    }

    boolean rowCrossed(Board gameBoard, int rowSize, int colSize) {
        int initialLoc, flag = 1;
        for (int i = 0 ; i < rowSize ; i++) {
            flag = 1;
            initialLoc = gameBoard.getCell(i,0);
            if (initialLoc == -1) {
                flag = 0;
                continue;
            }
            for (int j = 0 ; j < colSize ; j++) {
                if (gameBoard.getCell(i,j) != initialLoc) {
                    flag = 0;
                    break;
                }
            }
            if (flag==1) {
                break;
            }
        }

        if (flag == 1) {
            return true;
        }
        else {
            return false;
        }
    }

    boolean colCrossed(Board gameBoard, int rowSize, int colSize) {
        int initialLoc, flag = 1;
        for (int i = 0 ; i < colSize ; i++) {
            flag = 1;
            initialLoc = gameBoard.getCell(0,i);
            if (initialLoc == -1) {
                flag = 0;
                continue;
            }
            for (int j = 0 ; j < rowSize ; j++) {
                if (gameBoard.getCell(j,i) != initialLoc) {
                    flag = 0;
                    break;
                }
            }
            if (flag==1) {
                break;
            }
        }

        if (flag == 1) {
            return true;
        }
        else {
            return false;
        }
    }

    boolean diagonalCrossed(Board gameBoard, int rowSize, int colSize) {
        int initialLoc, flag = 1;
        initialLoc = gameBoard.getCell(0,0);
        if (initialLoc != -1) {
            for (int i=0;i<rowSize;i++) {
                if (gameBoard.getCell(i,i)!=initialLoc) {
                    flag = 0;
                    break;
                }
            }
        }

        if (initialLoc != -1 && flag == 1) {
            return true;
        }

        flag = 1;
        initialLoc = gameBoard.getCell(rowSize-1,0);
        if (initialLoc != -1) {
            for (int i=0;i<rowSize;i++) {
                if (gameBoard.getCell(rowSize-i-1,i)!=initialLoc) {
                    flag = 0;
                    break;
                }
            }
        }

        if (initialLoc != -1 && flag == 1) {
            return true;
        }

        return false;
    }

    boolean gameOver(Board gameBoard, int rowSize, int colSize) {
        return (rowCrossed(gameBoard, rowSize, colSize) || colCrossed(gameBoard,rowSize,colSize) || diagonalCrossed(gameBoard,rowSize,colSize));
    }

    void showBoard(Board gameBoard) {
        int rows = gameBoard.getRow_size();
        int cols = gameBoard.getCol_size();

        for (int i=0;i<rows;i++) {
            for (int j=0;j<cols;j++) {
                System.out.print(gameBoard.getCell(i,j)+" ");
            }
            System.out.println();
        }

        System.out.println("____");
    }

    int playGame(int gameNo, Board gameBoard, Player[] listOfPlayers, int rowSize, int colSize) {
        int noOfValidMoves = rowSize * colSize;
        int noOfMovesdone = 0;
        int whoseTurn = 0;
        int moveX;
        int moveY;
        int noOfPlayers = listOfPlayers.length;
        Player whoMoves = listOfPlayers[whoseTurn];

        while(gameOver(gameBoard,rowSize,colSize) == false && noOfMovesdone < noOfValidMoves) {
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

            //showBoard(gameBoard);
        }

        if (gameOver(gameBoard,rowSize,colSize) == false && noOfMovesdone == noOfValidMoves) {
            System.out.println("Game is a draw");
            showBoard(gameBoard);
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
            showBoard(gameBoard);
            return whoseTurn;
        }

    }

    int playEnhancedGame(int gameNo, Player[] listOfPlayers, int rowSize, int colSize, int boardSize) {
        int tempWinner;

        if (boardSize == rowSize) {

            Board gameBoard = new Board();
            gameBoard.setRow_size(rowSize);
            gameBoard.setCol_size(colSize);

            gameBoard.initializeBoard();

            tempWinner = playGame(gameNo, gameBoard, listOfPlayers, rowSize, colSize);

            return tempWinner;
        }
        else {
            int [][] winners = new int[rowSize][colSize];

            for (int i=0;i<rowSize;i++) {
                for (int j=0;j<colSize;j++) {
                    winners[i][j]=-1;
                }
            }
            for (int i=0;i<rowSize;i++) {
                for (int j=0;j<colSize;j++) {
                    winners[i][j] = playEnhancedGame(gameNo, listOfPlayers, rowSize, colSize, boardSize/rowSize);
                    if (newRowCrossed(winners, rowSize, colSize) || newColCrossed(winners, rowSize, colSize) || newDiagonalCrossed(winners, rowSize, colSize)) {
                        return winners[i][j];
                    }
                }
            }

            return -1;
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

        System.out.println("enter number of Players");
        int noOfPlayers = sc.nextInt();

        Player[] listOfPlayers = new Player[noOfPlayers];

        System.out.println("For Each Player, Enter H for human and M for Machine");
        int playerId = 0;
        String choice;
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
            System.out.println("Enter Y to play enhanced Tictactoe");
            choice = sc.nextLine();

            int winner;

            if (choice.equals("Y")) {
                System.out.println("Enter the board size in terms of power of row or column size");
                int boardSize = sc.nextInt();

                winner = newGame.playEnhancedGame(gameNo, listOfPlayers, rowSize, colSize, boardSize);
                if (winner == -1) {
                    System.out.println("The Game is a draw");
                }
                else {
                    System.out.println("The Winner of the Enhanced TicTacToe is " +winner);
                }
                choice = sc.nextLine();
            }
            else {
                winner = newGame.playGame(gameNo, gameBoard, listOfPlayers, rowSize, colSize );
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
            //if(newGame.gameState.containsKey(cho)) {
                ArrayList<Board> states = newGame.gameState.get(cho);
                for (Board iter : states) {
                    newGame.showBoard(iter);
                }
           // }
        }
    }
}
