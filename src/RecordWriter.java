import com.fasterxml.jackson.databind.ObjectMapper;

import java.awt.*;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Scanner;


public class RecordWriter {
    ObjectMapper objectMapper = new ObjectMapper();



        //TODO рекорд txt в json (ObjectMapper) 1.прочитать строку из файла 2.с ObjectMapper превратить строку в масисив
        // 3. Входит ли значение в топ 10, вставить в нужное место 4. с ObjectMapper превратить массив в строку
        // 5. записать строку в файл
        //TODO прочитать файл сравнить значения , записать по возрастанию




    public void changeRecords(Player player) throws IOException{
        Player[] players = readRecords();
        players = compare(players, player);
        writeToFile(players, objectMapper);
    }

    public Player[] readRecords() throws IOException {

        Player[] players;

        File file = new File("records.json");
        if (file.length() == 0) {
            players = new Player[0];

        } else {
            players = objectMapper.readValue(file, Player[].class);
        }
        return players;
    }

    private Player[] compare(Player[] players, Player player) {
        Arrays.sort(players, Comparator.comparingInt(Player::getMoveCount));


        int newSize = Math.min(players.length + 1, 10); // keep the array size to a maximum of 10 elements
        Player[] newPlayers = new Player[newSize];
        int insertIndex = -1;


// find the index where the new player should be inserted
        for (int i = 0; i < players.length; i++) {
            if (player.getMoveCount() <= players[i].getMoveCount()) {
                insertIndex = i;
                break;
            }
        }

// insert the new player at the correct index
        if (insertIndex >= 0) {
            // shift the existing players to make space for the new player
            System.arraycopy(players, 0, newPlayers, 0, insertIndex);
            System.arraycopy(players, insertIndex, newPlayers, insertIndex + 1, players.length - insertIndex);
            newPlayers[insertIndex] = player;
        } else {
            // no need to shift the existing players, just append the new player at the end
            System.arraycopy(players, 0, newPlayers, 0, players.length);
            newPlayers[players.length] = player;
        }

// update the players array with the new array
        players = newPlayers;
        return players;
    }

    private void writeToFile(Player[] players, ObjectMapper objectMapper) throws IOException {
        String jsonString = objectMapper.writeValueAsString(players);
        Files.write(Paths.get("records.json"), jsonString.getBytes());
    }


}
