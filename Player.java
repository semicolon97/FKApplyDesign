package FKApplyDesign;

import java.util.Scanner;
import java.util.Random;

abstract class Player {
    private int playerId;

    int getPlayerId() {
        return playerId;
    }

    void setPlayerId(int id) {
        playerId=id;
    }

    abstract int rowMove(int rowSize);
    abstract int colMove(int colSize);
    abstract boolean wantUndo();
}



class Human extends Player {
    int rowMove(int rowSize) {
        System.out.println("Enter row number of your move within range 0 - "+(rowSize-1));
        Scanner sc = new Scanner(System.in);
        int rowNo = sc.nextInt();
        return rowNo;
    }

    int colMove(int colSize) {
        System.out.println("Enter column number of your move within range 0 - "+(colSize-1));
        Scanner sc = new Scanner(System.in);
        int colNo = sc.nextInt();
        return colNo;
    }


    boolean wantUndo() {
        System.out.println("Enter Y if you want to undo your previous move, else enter N");
        String choice;
        Scanner sc = new Scanner(System.in);
        choice = sc.nextLine();
        if (choice.equals("Y")) {
            return true;
        }
        else if (choice.equals("N")) {
            return false;
        }
        else {
            System.out.println("Invalid choice, assuming no undo");
            return false;
        }
    }
}



class Machine extends Player {
    int rowMove(int rowSize) {
        Random rd = new Random();
        int rowNo = rd.nextInt(rowSize);
        return rowNo;
    }

    int colMove(int colSize) {
        Random rd = new Random();
        int colNo = rd.nextInt(colSize);
        return colNo;
    }

    boolean wantUndo() {
        return false;
    }
}