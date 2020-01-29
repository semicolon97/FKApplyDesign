package FKApplyDesign;

class Board {
    private int row_size;
    private int col_size;
    private int[][] board;
    public static final int empty = -1;

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
        else {
            board[x][y]=player;
            return 1;
        }
    }

    boolean rowCrossed() {
        int initialLoc, flag = 1;
        for (int i = 0 ; i < row_size ; i++) {
            flag = 1;
            initialLoc = getCell(i,0);
            if (initialLoc == empty) {
                flag = 0;
                continue;
            }
            for (int j = 0 ; j < col_size ; j++) {
                if (getCell(i,j) != initialLoc) {
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

    boolean colCrossed() {
        int initialLoc, flag = 1;
        for (int i = 0 ; i < col_size ; i++) {
            flag = 1;
            initialLoc = getCell(0,i);
            if (initialLoc == empty) {
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

    boolean diagonalCrossed() {
        int initialLoc, flag = 1;
        initialLoc = getCell(0,0);
        if (initialLoc != empty) {
            for (int i=0;i<row_size;i++) {
                if (getCell(i,i)!=initialLoc) {
                    flag = 0;
                    break;
                }
            }
        }

        if (initialLoc != -1 && flag == 1) {
            return true;
        }

        flag = 1;
        initialLoc = getCell(row_size-1,0);
        if (initialLoc != empty) {
            for (int i=0;i<row_size;i++) {
                if (getCell(row_size-i-1,i)!=initialLoc) {
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

    void showBoard() {
        for (int i=0;i<row_size;i++) {
            for (int j=0;j<col_size;j++) {
                System.out.print(getCell(i,j)+" ");
            }
            System.out.println();
        }

        System.out.println("____");
    }
}