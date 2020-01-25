package FKApplyDesign;

public class Board {
    private int row_size;
    private int col_size;

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
}