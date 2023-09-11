import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.Arrays;
import java.util.Random;

public class GameOnFrame implements ActionListener {
    // Game variables
    int moveCount = 0;
    int rows;
    int cols;
    int[][] board;
    JLabel[][] numbersOnBoard;
    JButton[][] buttonsOnBoard;

    // Swing components
    JFrame gameFrame;
    JPanel mainPanel;
    JLabel winLabel;
    JLayeredPane frameWidth;
    ImageIcon icon;
    JButton cancelButton;
    JLabel backgroundColor;

    public GameOnFrame(Fabric fabric) throws IOException {
        // Set the dimensions of the game board
        rows = 4;
        cols = 4;
        board = new int[rows][cols];

        // Initialize the GUI
        initGUI(fabric);
    }

    public void initGUI(Fabric fabric) throws IOException {
        // Create the game frame
        this.gameFrame = fabric.getGameFrame();

        // Get background color from the fabric
        this.backgroundColor = fabric.getBackgroundColor();

        // Get the cancel button from the fabric and attach an action listener
        this.cancelButton = fabric.getCancelButton();
        this.cancelButton.addActionListener(this);

        // Create the main panel and set the layout
        this.mainPanel = fabric.getMainPanel();
        this.mainPanel.setLayout(new GridLayout(rows, cols, 5, 5));

        this.icon = fabric.getIcon();

        this.frameWidth = fabric.getFrameWidth();

        this.winLabel = fabric.getWinLabel();

        buttonsOnBoard = new JButton[rows][cols];
        numbersOnBoard = new JLabel[rows][cols];

        // Generate a random board configuration
        this.randomFromArray(rows);

        // Create buttons and labels for each position on the board
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                buttonsOnBoard[i][j] = new JButton();
                numbersOnBoard[i][j] = new JLabel();

                String text = i + "," + j;
                buttonsOnBoard[i][j].setText(text);
                buttonsOnBoard[i][j].setFont(new Font("TimesRoman", Font.PLAIN, 0));
                buttonsOnBoard[i][j].setLayout(new FlowLayout());

                numbersOnBoard[i][j].setFont(new Font("Clement Numbers", Font.PLAIN, 30));
                numbersOnBoard[i][j].setForeground(new Color(248, 202, 182));
                numbersOnBoard[i][j].setVerticalAlignment(JLabel.CENTER);
                numbersOnBoard[i][j].setHorizontalAlignment(JLabel.CENTER);

                // Set the text of the label based on the value in the board array
                if (board[i][j] != 0) {
                    numbersOnBoard[i][j].setText(String.valueOf(board[i][j]));
                } else {
                    numbersOnBoard[i][j].setText("");
                }

                buttonsOnBoard[i][j].addActionListener(this);
                buttonsOnBoard[i][j].add(numbersOnBoard[i][j]);
                buttonsOnBoard[i][j].setBorder(BorderFactory.createLineBorder(Color.black, 2));
                buttonsOnBoard[i][j].setBackground(new Color(92, 28, 1));

                mainPanel.add(buttonsOnBoard[i][j]);
            }
        }
        // Add components to the frame's layered pane
        this.frameWidth.add(backgroundColor, Integer.valueOf(0));
        this.frameWidth.add(mainPanel, Integer.valueOf(1));
        this.frameWidth.add(winLabel, Integer.valueOf(1));
        this.frameWidth.add(cancelButton, Integer.valueOf(1));

        // Add the frameWidth to the game frame
        gameFrame.add(frameWidth);

        // Set the icon, make the frame visible
        gameFrame.setIconImage(icon.getImage());
        gameFrame.setVisible(true);

        // Set the player name
        this.setPlayerName(fabric);
    }

    public void randomFromArray(int dimensions) {
        // Generate a random board configuration
        Random rand = new Random();
        int[] numbers = new int[dimensions * dimensions];

        // Fill the array with numbers from 1 to dimensions * dimensions
        for (int i = 0; i < (dimensions * dimensions); i++) {
            numbers[i] = i + 1;
        }

        // Set the last element to 0 to represent the empty space
        numbers[dimensions * dimensions - 1] = 0;

        // Shuffle the numbers randomly
        for (int i = 0; i < (dimensions); i++) {
            int index = rand.nextInt(dimensions * dimensions);
            int num = numbers[i];
            numbers[i] = numbers[index];
            numbers[index] = num;
        }

        // Assign the shuffled numbers to the game board
        int count = 0;
        for (int i = 0; i < dimensions; i++) {
            for (int j = 0; j < dimensions; j++) {
                board[i][j] = numbers[count];
                count++;
            }
        }
    }

    public Boolean gameOn(int[][] gameDesk) throws IOException {
        // Check if the game board matches the winning configuration
        int[][] gameEnd = {{1, 2, 3, 4}, {5, 6, 7, 8}, {9, 10, 11, 12}, {13, 14, 15, 0}};

        for (int i = 0; i < 4; i++) {
            if (!Arrays.equals(gameDesk[i], gameEnd[i])) {
                return false;
            }
        }

        // If the game is won, update the records and display the win label
        changeRecords(moveCount);
        winLabel.setVisible(true);
        mainPanel.setVisible(false);

        return true;
    }

    public void changeRecords(int moveCount) throws IOException {
        // Update the records with the new player's move count
        RecordWriter recordWriter = new RecordWriter();
        recordWriter.changeRecords(new Player(getPlayerName(), moveCount));
    }

    public String getPlayerName() throws IOException {
        // Get the player name from the stored file
        PlayerNameWriter playerNameWriter = new PlayerNameWriter();
        return playerNameWriter.readFromFile();
    }

    public void setPlayerName(Fabric fabric) {
        // Set the player name in the file
        PlayerNameWriter playerNameWriter = new PlayerNameWriter();
        playerNameWriter.writeField(fabric);
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == cancelButton) {
            // Close the current frame
            gameFrame.dispose();
            // Create a new StartFrame
            new StartFrame();
        }
        String s = e.getActionCommand();
        if (!s.equals("Cancel")) {
            // Get values from GameBoard
            int i = Integer.parseInt(s.split(",")[0]);
            int j = Integer.parseInt(s.split(",")[1]);
            // Check if "0" can be moved to the right
            if ((j + 1 < board[i].length) && (i < board[i].length) && (i >= 0) && (j >= 0) && (board[i][j + 1] == 0)) {
                numbersOnBoard[i][j + 1].setText(String.valueOf(board[i][j]));
                board[i][j + 1] = board[i][j];
                numbersOnBoard[i][j].setText("");
                board[i][j] = 0;
                moveCount++;
            }
            // Check if "0" can be moved to the left
            if ((j - 1 >= 0) && (i < board[i].length) && (i >= 0) && (j < board[i].length) && (board[i][j - 1] == 0)) {
                numbersOnBoard[i][j - 1].setText(String.valueOf(board[i][j]));
                board[i][j - 1] = board[i][j];
                numbersOnBoard[i][j].setText("");
                board[i][j] = 0;
                moveCount++;
            }
            // Check if "0" can be moved down
            if ((i + 1 < board[i].length) && (j < board[i].length) && (i >= 0) && (j >= 0) && (board[i + 1][j] == 0)) {
                numbersOnBoard[i + 1][j].setText(String.valueOf(board[i][j]));
                board[i + 1][j] = board[i][j];
                numbersOnBoard[i][j].setText("");
                board[i][j] = 0;
                moveCount++;
            }
            // Check if "0" can be moved up
            if ((i - 1 >= 0) && (j < board[i].length) && (i < board[i].length) && (j >= 0) && (board[i - 1][j] == 0)) {
                numbersOnBoard[i - 1][j].setText(String.valueOf(board[i][j]));
                board[i - 1][j] = board[i][j];
                numbersOnBoard[i][j].setText("");
                board[i][j] = 0;
                moveCount++;
            }
            try {
                gameOn(board);
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        }
    }
}
