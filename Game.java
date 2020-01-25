package FKApplyDesign;

import java.util.Scanner;
import java.util.ArrayList;

public class Game {

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
                listOfPlayers[playerId] = tempMachine ;
                playerId++;
            }
            else {
                System.out.println("Invalid Choice, please enter again");
                iter--;
            }
        }


        int noOfValidMoves = rowSize * colSize;
        int noOfMovesdone = 0;
        int whoseTurn = 0;
        int moveX;
        int moveY;
        Player whoMoves = listOfPlayers[whoseTurn];

        while(newGame.gameOver(gameBoard,rowSize,colSize) == false && noOfMovesdone < noOfValidMoves) {
            moveX = whoMoves.rowMove(rowSize);
            moveY = whoMoves.colMove(colSize);

            while(gameBoard.makeMove(whoseTurn, moveX, moveY) != 1) {
                moveX = whoMoves.rowMove(rowSize);
                moveY = whoMoves.colMove(colSize);
            }
            whoseTurn = (whoseTurn+1)%noOfPlayers;
        }

        if (newGame.gameOver(gameBoard,rowSize,colSize) == false && noOfMovesdone == noOfValidMoves) {
            System.out.println("Game is a draw");
        }
        else {
            whoseTurn = (whoseTurn - 1)%noOfPlayers;
            System.out.println("The Winner is Player "+whoseTurn);
        }
    }
}