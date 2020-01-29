package FKApplyDesign;

import java.util.Random;

class Board {
    private int row_size;
    private int col_size;
    private int[][] board;
    public static final int empty = -1;
    public static final int destroyed = -2;

    void setRow_size(int n) {
        row_size = n;
    }
    void setCol_size(int n) {
        col_size = n;
    }

    int getRow_size() {
        return row_size;
    }
    int getCol_size() {
        return col_size;
    }

    int getCell(int x, int y) {
        if (x<0||x>=row_size||y<0||y>=col_size) {
            return empty;
        }
        else if (board[x][y] == destroyed) {
            return destroyed;
        }
        else {
            return board[x][y];
        }
    }

    void initializeBoard() {
        board = new int[row_size][col_size];
        for (int i=0;i<row_size;i++) {
            for (int j=0;j<col_size;j++) {
                board[i][j]=empty;
            }
        }
    }

    int makeMove(int player, int x, int y) {
        if (x<0||x>=row_size||y<0||y>=col_size) {
            System.out.println("Invalid Move");
            return -1;
        }
        else if (board[x][y] != empty) {
            System.out.println("Cell already occupied");
            return 0;
        }
        else if (board[x][y] == destroyed) {
            System.out.println("No Cell exists at this position");
            return destroyed;
        }
        else {
            board[x][y]=player;
            return 1;
        }
    }

    boolean rowCrossed(int noOfConnectionsToWin) {
        int initialLoc, flag = 1, count = 0;

        for (int i = 0 ; i < row_size ; i++) {
            flag = 1;
            count = 0;
            initialLoc = getCell(i,0);
            if (initialLoc == empty || initialLoc == destroyed) {
                flag = 0;
                continue;
            }
            //count = 1;
            for (int j = 0 ; j < col_size ; j++) {
                if (getCell(i,j) != initialLoc) {
                    flag = 0;
                    break;
                }
                else {
                    count++;
                    if (count == noOfConnectionsToWin) {
                        flag = 1;
                        break;
                    }
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

    boolean colCrossed() {
        int initialLoc, flag = 1;
        for (int i = 0 ; i < col_size ; i++) {
            flag = 1;
            initialLoc = getCell(0,i);
            if (initialLoc == empty || initialLoc == destroyed) {
                flag = 0;
                continue;
            }
            for (int j = 0 ; j < row_size; j++) {
                if (getCell(j,i) != initialLoc) {
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

    boolean diagonalCrossed(int noOfConnectionsToWin) {
        int initialLoc, flag = 1, count = 0;
        initialLoc = getCell(0,0);
        if (initialLoc != empty && initialLoc != destroyed) {
            for (int i=0;i<row_size;i++) {
                if (getCell(i,i)!=initialLoc) {
                    flag = 0;
                    break;
                }
                else {
                    count++;
                    if (count == noOfConnectionsToWin) {
                        flag = 1;
                        break;
                    }
                }
            }
        }

        if (initialLoc != -1 && flag == 1) {
            return true;
        }

        flag = 1;
        count = 0;
        initialLoc = getCell(row_size-1,0);
        if (initialLoc != empty && initialLoc != destroyed) {
            for (int i=0;i<row_size;i++) {
                if (getCell(row_size-i-1,i)!=initialLoc) {
                    flag = 0;
                    break;
                }
                else {
                    count++;
                    if (count == noOfConnectionsToWin) {
                        flag = 1;
                        break;
                    }
                }
            }
        }

        if (initialLoc != -1 && flag == 1) {
            return true;
        }

        return false;
    }

    void showBoard() {
        for (int i=0;i<row_size;i++) {
            for (int j=0;j<col_size;j++) {
                if (getCell(i,j) != destroyed) {
                    System.out.print(getCell(i,j)+" ");
                }
            }
            System.out.println();
        }

        System.out.println("____");
    }


    void makeBoardIrregular(int noOfBlocksToBeDestroyed) {
        Random random = new Random();

        int noOfBlocksDestroyed = 0;
        int rowDestroy, colDestroy;

        while (noOfBlocksDestroyed < noOfBlocksToBeDestroyed) {
            rowDestroy = random.nextInt(row_size);
            colDestroy = random.nextInt(col_size);
            while (board[rowDestroy][colDestroy] == destroyed) {
                rowDestroy = random.nextInt(row_size);
                colDestroy = random.nextInt(col_size);
            }

            board[rowDestroy][colDestroy] = destroyed;
            noOfBlocksDestroyed = noOfBlocksDestroyed + 1;
        }
    }
}