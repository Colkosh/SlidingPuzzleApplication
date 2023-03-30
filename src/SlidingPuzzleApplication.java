import javax.swing.*;
import java.util.*;

import static java.lang.StrictMath.floor;
import static java.lang.StrictMath.random;


public class SlidingPuzzleApplication {
    public static void main(String[] args) {

        gameOn(createDesk(4, randomFromArray(4)));
        //createAndShowGUI();

    }

    // todo 0-15 massiv i разделить на 4(00-0, 01) if i j везде учитывать, строка на вход /swing
    //    static int[][] createDesk(int colums, int strokes){
//

    static int[] randomFromArray(int dimensions) {
        Random rand = new Random();
        int[] numbers = new int[dimensions * dimensions];
        for (int i = 0; i < (dimensions * dimensions); i++) {
            numbers[i] = i + 1;
        }
        numbers[15] = 0;
        for (int i = 0; i < (dimensions * dimensions); i++) {
            int index = rand.nextInt(dimensions * dimensions);
            int num = numbers[i];
            numbers[i] = numbers[index];
            numbers[index] = num;

        }
        return numbers;
    }

    static int[][] createDesk(int dimensions, int[] numbers) {
        int count = 0;
        int[][] board = new int[dimensions][dimensions];
        for (int i = 0; i < dimensions; i++) {
            for (int j = 0; j < dimensions; j++) {
                board[i][j] = numbers[count];
                count++;
            }
        }
        return board;
    }


    static void showDesk(int[][] gameDesk) {
        for (int i = 0; i < 4; i++) {
            System.out.println(Arrays.toString(gameDesk[i]));
        }
    }

    static void nextTurn(int[][] gameDesk) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Write i: ");

        int i = Integer.parseInt(scanner.nextLine().trim());

        System.out.println("Write j: ");
        int j = Integer.parseInt(scanner.nextLine().trim());

        takeTurn(gameDesk, i, j);
    }

    static void takeTurn(int[][] gameDesk, int i, int j) {

        if ((j + 1 < 4) && (i < 4) && (i >= 0) && (j >= 0) && (gameDesk[i][j + 1] == 0)) {
            gameDesk[i][j + 1] = gameDesk[i][j];
            gameDesk[i][j] = 0;
        }
        if ((j - 1 >= 0) && (i < 4) && (i >= 0) && (j < 4) && (gameDesk[i][j - 1] == 0)) {
            gameDesk[i][j - 1] = gameDesk[i][j];
            gameDesk[i][j] = 0;
        }
        if ((i + 1 < 4) && (j < 4) && (i >= 0) && (j >= 0) && (gameDesk[i + 1][j] == 0)) {
            gameDesk[i + 1][j] = gameDesk[i][j];
            gameDesk[i][j] = 0;
        }
        if ((i - 1 >= 0) && (j < 4) && (i < 4) && (j >= 0) && (gameDesk[i - 1][j] == 0)) {
            gameDesk[i - 1][j] = gameDesk[i][j];
            gameDesk[i][j] = 0;
        }
    }

    static void gameOn(int[][] gameDesk) {
        int[][] gameEnd = {{1, 2, 3, 4}, {5, 6, 7, 8}, {9, 10, 11, 12}, {13, 14, 15, 0}};
        while (true) {
            showDesk(gameDesk);
            if (isGameFinished(gameDesk, gameEnd)) {
                System.out.println("Game ends. Well done!");
                break;
            }
            nextTurn(gameDesk);
        }
    }

    static Boolean isGameFinished(int[][] gameDesk, int[][] gameEnd) {
        for (int i = 0; i < 4; i++) {
            if (!Arrays.equals(gameDesk[i], gameEnd[i])) {
                return false;
            }
        }
        return true;
    }

    private static void createAndShowGUI() {
        //Create and set up the window.
        JFrame frame = new JFrame("HelloWorldSwing");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //Add the ubiquitous "Hello World" label.
        JLabel label = new JLabel("Hello World");
        frame.getContentPane().add(label);

        //Display the window.
        frame.pack();
        frame.setVisible(true);
    }


}



