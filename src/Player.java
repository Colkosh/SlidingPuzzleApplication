import java.io.Serializable;

public class Player implements Serializable {
    private String playerName;
    private int moveCount;

    // Parameterized constructor
    Player(String playerName, int moveCount) {
        this.playerName = playerName;
        this.moveCount = moveCount;
    }

    // Getter for moveCount
    public int getMoveCount() {
        return moveCount;
    }

    // String representation of the player object
    @Override
    public String toString() {
        return moveCount + " moves" + " - " + playerName;
    }
}
