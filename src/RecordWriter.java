import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Comparator;

public class RecordWriter {
    ObjectMapper objectMapper = new ObjectMapper();

    // Update the player records with a new player
    public void changeRecords(Player player) throws IOException {
        Player[] players = readRecords(); // Read existing records
        players = compare(players, player); // Compare and insert the new player
        writeToFile(players, objectMapper); // Write updated records to the file
    }

    // Read player records from a JSON file
    public Player[] readRecords() throws IOException {
        Player[] players;
        File file = new File("records.json");

        // Check if the file is empty
        if (file.length() == 0) {
            players = new Player[0]; // Empty array if file is empty
        } else {
            players = objectMapper.readValue(file, Player[].class); // Read the file into an array of Player objects
        }
        return players;
    }

    // Compare and insert the new player in the correct position based on move count
    private Player[] compare(Player[] players, Player player) {
        Arrays.sort(players, Comparator.comparingInt(Player::getMoveCount)); // Sort players based on move count

        int newSize = Math.min(players.length + 1, 10); // Keep the array size to a maximum of 10 elements
        Player[] newPlayers = new Player[newSize];
        int insertIndex = -1;

        // Find the index where the new player should be inserted
        for (int i = 0; i < players.length; i++) {
            if (player.getMoveCount() <= players[i].getMoveCount()) {
                insertIndex = i;
                break;
            }
        }

        // Insert the new player at the correct index
        if (insertIndex >= 0) {
            // Shift the existing players to make space for the new player
            System.arraycopy(players, 0, newPlayers, 0, insertIndex);
            System.arraycopy(players, insertIndex, newPlayers, insertIndex + 1, players.length - insertIndex);
            newPlayers[insertIndex] = player;
        } else {
            // No need to shift the existing players, just append the new player at the end
            System.arraycopy(players, 0, newPlayers, 0, players.length);
            newPlayers[players.length] = player;
        }

        // Update the players array with the new array
        players = newPlayers;
        return players;
    }

    // Write the player records to the JSON file
    private void writeToFile(Player[] players, ObjectMapper objectMapper) throws IOException {
        String jsonString = objectMapper.writeValueAsString(players); // Convert players array to JSON string
        Files.write(Paths.get("records.json"), jsonString.getBytes()); // Write JSON string to file
    }
}