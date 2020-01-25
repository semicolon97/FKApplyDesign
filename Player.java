package FKApplyDesign;

public abstract class Player {
    private int playerId;

    int getPlayerId() {
        return playerId;
    }

    void setPlayerId(int id) {
        playerId=id;
    }

    public abstract int rowMove();
    public abstract int colMove();
}
