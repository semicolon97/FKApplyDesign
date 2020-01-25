package FKApplyDesign;

import java.util.Scanner;
import java.util.ArrayList;

public class Game {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

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

        ArrayList<Player> listOfPlayers = new ArrayList<>(noOfPlayers);

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
                playerId++;
                listOfPlayers.add(tempHuman);
            }
            else if (choice.equals("M")) {
                tempMachine = new Machine();
                tempMachine.setPlayerId(playerId);
                playerId++;
                listOfPlayers.add(tempMachine);
            }
            else {
                System.out.println("Invalid Choice, please enter again");
                iter--;
            }
        }
    }
}