import java.io.Serializable;

public class Player implements Serializable {
    private String playerName;
    private int moveCount;

    Player() {
    }

    Player(String playerName, int moveCount) {
        this.playerName = playerName;
        this.moveCount = moveCount;

    }

    public int getMoveCount() {
        return moveCount;
    }

    public String getPlayerName() {
        return playerName;
    }

    @Override
    public String toString() {
        return moveCount + " moves" + " - " + playerName;
    }
}