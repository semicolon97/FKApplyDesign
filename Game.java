package FKApplyDesign;

import java.util.Scanner;

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
    }
}