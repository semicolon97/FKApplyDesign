package FKApplyDesign;

public class Board {
    private int row_size;
    private int col_size;
    private int[][] board = new int[row_size][col_size];

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

    void initializeBoard() {
        for (int i=0;i<row_size;i++) {
            for (int j=0;j<col_size;j++) {
                board[i][j]=-1;
            }
        }
    }

    int makeMove(int player, int x, int y) {
        if (x<0||x>=row_size||y<0||y>=col_size) {
            return -1;
        }
        else if (board[x][y] != -1) {
            return 0;
        }
        else {
            board[x][y]=player;
            return 1;
        }
    }
}