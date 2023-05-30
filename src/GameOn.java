import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.Arrays;
import java.util.Random;


public class GameOn implements ActionListener {
    int moveCount = 0;
    JFrame gameFrame;
    JPanel mainPanel;
    JLabel winLabel;
    JLayeredPane frameWidth;
    ImageIcon icon;
    private JLabel[][] numbersOnBoard;
    JButton cancelButton;
    private JButton[][] buttonsOnBoard;
    int rows;
    int cols;
    int[][] board;
    JLabel backgroundColor;
    // TODO Нормально структурировать код, а то все где попало написано!!!
    // TODO сделать чтобы можно были ииграть 3х3(Любое число)
    //     точка на доске

    public GameOn(Fabric fabric) throws IOException {
        rows = 4;
        cols = 4;
        board = new int[rows][cols];
        initGUI(fabric);

    }

    /**
     * ctrl  + space
     */
    public void initGUI(Fabric fabric) throws IOException {


        this.gameFrame = fabric.getGameFrame();

        this.backgroundColor = fabric.getBackgroundColor();

        this.cancelButton = fabric.getCancelButton();
        this.cancelButton.addActionListener(this);


        this.mainPanel = fabric.getMainPanel();
        this.mainPanel.setLayout(new GridLayout(rows, cols, 5, 5));

        this.icon = fabric.getIcon();

        this.frameWidth = fabric.getFrameWidth();

        this.winLabel = fabric.getWinLabel();


        buttonsOnBoard = new JButton[rows][cols];
        numbersOnBoard = new JLabel[rows][cols];



        this.randomFromArray(rows);

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                buttonsOnBoard[i][j] = new JButton();
                numbersOnBoard[i][j] = new JLabel();
                //TODO поместить label на центр кнопки
                String text = i + "," + j;
                buttonsOnBoard[i][j].setText(text);
                buttonsOnBoard[i][j].setFont(new Font("TimesRoman", Font.PLAIN, 0));
                buttonsOnBoard[i][j].setLayout(new FlowLayout());

                numbersOnBoard[i][j].setFont(new Font("Clement Numbers", Font.PLAIN, 30));
                numbersOnBoard[i][j].setForeground(new Color(248, 202, 182));
                numbersOnBoard[i][j].setVerticalAlignment(JLabel.CENTER);
                numbersOnBoard[i][j].setHorizontalAlignment(JLabel.CENTER);


                if (board[i][j] != 0) {
                    numbersOnBoard[i][j].setText(String.valueOf(board[i][j]));
                } else {
                    numbersOnBoard[i][j].setText("");
                }

                buttonsOnBoard[i][j].addActionListener(this);
                buttonsOnBoard[i][j].add(numbersOnBoard[i][j]);
                buttonsOnBoard[i][j].setBorder(BorderFactory.createLineBorder(Color.black, 2));
                buttonsOnBoard[i][j].setBackground(new Color(92, 28, 1));

                mainPanel.add(buttonsOnBoard[i][j]); //TODO одстраивался в количество ячеек, изменять размер окна, если менять
                //TODO размер доска становилась больше
            }
        }


        this.frameWidth.add(backgroundColor, Integer.valueOf(0));
        this.frameWidth.add(mainPanel, Integer.valueOf(1));
        this.frameWidth.add(winLabel, Integer.valueOf(1));
        this.frameWidth.add(cancelButton, Integer.valueOf(1));

        gameFrame.add(frameWidth);


        gameFrame.setIconImage(icon.getImage());
        gameFrame.setVisible(true);

        this.setPlayerName();

    }

    public void randomFromArray(int dimensions) {
        Random rand = new Random();
        int[] numbers = new int[dimensions * dimensions];
        for (int i = 0; i < (dimensions * dimensions); i++) {
            numbers[i] = i + 1;
        }
        numbers[dimensions * dimensions - 1] = 0;
        for (int i = 0; i < (dimensions); i++) {
            int index = rand.nextInt(dimensions * dimensions);
            int num = numbers[i];
            numbers[i] = numbers[index];
            numbers[index] = num;
// TODO когда игра заканчивается выключать кнопки
        }
        int count = 0;
        for (int i = 0; i < dimensions; i++) {
            for (int j = 0; j < dimensions; j++) {
                board[i][j] = numbers[count];
                count++;
            }
        }
    }

    public Boolean gameOn(int[][] gameDesk) throws IOException {
        int[][] gameEnd = {{1, 2, 3, 4}, {5, 6, 7, 8}, {9, 10, 11, 12}, {13, 14, 15, 0}};
        for (int i = 0; i < 4; i++) {
            if (!Arrays.equals(gameDesk[i], gameEnd[i])) {
                return false;
            }
        }

        RecordWriter recordWriter = new RecordWriter();
        recordWriter.changeRecords(new Player(getPlayerName(), moveCount));
        //TODO запрос на имя игрока, если пропускает - player - DONE
        winLabel.setVisible(true);
        mainPanel.setVisible(false);//кликабл фрлс
        //TODO сохранять турнирную таблицу, игрок должен ввести имя
        return true;
    }

    public void setPlayerName(){
        PlayerNameWriter playerNameWriter = new PlayerNameWriter();
        playerNameWriter.writeField();
    }
    public String getPlayerName() throws IOException {
        PlayerNameWriter playerNameWriter = new PlayerNameWriter();
        return playerNameWriter.readFromFile();
    }



    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == cancelButton) {
            gameFrame.dispose();

            new StartFrame();
        }
        String s = e.getActionCommand();
        if (!s.equals("Cancel")) {
            int i = Integer.parseInt(s.split(",")[0]);
            int j = Integer.parseInt(s.split(",")[1]);
            if ((j + 1 < board[i].length) && (i < board[i].length) && (i >= 0) && (j >= 0) && (board[i][j + 1] == 0)) {
                numbersOnBoard[i][j + 1].setText(String.valueOf(board[i][j]));
                board[i][j + 1] = board[i][j];
                numbersOnBoard[i][j].setText("");
                board[i][j] = 0;
                moveCount++;
            }
            if ((j - 1 >= 0) && (i < board[i].length) && (i >= 0) && (j < board[i].length) && (board[i][j - 1] == 0)) {
                numbersOnBoard[i][j - 1].setText(String.valueOf(board[i][j]));
                board[i][j - 1] = board[i][j];
                numbersOnBoard[i][j].setText("");
                board[i][j] = 0;
                moveCount++;
            }
            if ((i + 1 < board[i].length) && (j < board[i].length) && (i >= 0) && (j >= 0) && (board[i + 1][j] == 0)) {
                numbersOnBoard[i + 1][j].setText(String.valueOf(board[i][j]));
                board[i + 1][j] = board[i][j];
                numbersOnBoard[i][j].setText("");
                board[i][j] = 0;
                moveCount++;
            }
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


        //TODO Выходит ошибка из--за parseInt, если кнопки убрать ошибки не будет DONE
        //TODO прорефакторить условия при изменении размера матрицы NOT NOW


    }

}
//TODO не всегда можно выйграть, как это можно решить?