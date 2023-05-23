import java.io.Serializable;

public class Player implements Serializable {
    private String playerName;
    private int moveCount;

    Player() {}

    Player(String playerName, int moveCount) {
        this.playerName = playerName;
        this.moveCount = moveCount;

    }

    Player(int moveCount) {
        this.playerName = "NoNamePlayer";
        this.moveCount = moveCount;

    }

    public String getPlayerName() {
        return playerName;
    }

    public void setPlayerName(String playerName) {
        this.playerName = playerName;
    }

    public int getMoveCount() {
        return moveCount;
    }

    public void setMoveCount(int moveCount) {
        this.moveCount = moveCount;
    }
}