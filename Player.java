package FKApplyDesign;

import java.util.Scanner;

public abstract class Player {
    private int playerId;

    int getPlayerId() {
        return playerId;
    }

    void setPlayerId(int id) {
        playerId=id;
    }

    abstract int rowMove();
    abstract int colMove();
}



class Humans extends Player {
    int rowMove() {
        System.out.println("Enter row number of your move");
        Scanner sc = new Scanner(System.in);
        int rowNo = sc.nextInt();
        return rowNo;
    }

    int colMove() {
        System.out.println("Enter column number of your move");
        Scanner sc = new Scanner(System.in);
        int colNo = sc.nextInt();
        return colNo;
    }
}