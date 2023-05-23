import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Arrays;
import java.util.Random;


public class GameOn implements ActionListener {
    int moveCount = 0;
    JFrame fr;
    JPanel mainPanel;
    JLabel winLabel = new JLabel("Game ends. Well done!");
    private JLabel[][] numbersOnBoard;
    private JButton pauseButton;
    JButton cancelButton;
    private JButton[][] button;
    int rows;
    int cols;
    int[][] board;
    String name;
    // TODO Нормально структурировать код, а то все где попало написано!!!
    // TODO сделать чтобы можно были ииграть 3х3(Любое число)
    //     точка на доске

    public GameOn() throws IOException {
        rows = 4;
        cols = 4;
        board = new int[rows][cols];
        initGUI();
    }

    /**
     * ctrl  + space
     */
    public void initGUI() throws IOException {



        fr = new JFrame("Sliding Puzzle");
        fr.setLayout(null);
        fr.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        fr.setSize(640, 420);
        fr.setResizable(false);

        JLabel label2 = new JLabel();
        label2.setBackground(new Color(139, 126, 102));
        label2.setOpaque(true);
        fr.add(label2).setBounds(0, 0, 640, 420);


        cancelButton = new JButton();
        cancelButton.setBounds(450, 270, 80, 71);
        cancelButton.setText("Cancel");
        cancelButton.setFont(new Font("Brush Script MT", Font.PLAIN, 30));
        cancelButton.setBackground(new Color(248, 152, 111));
        cancelButton.setBorder(BorderFactory.createEtchedBorder());
        cancelButton.addActionListener(this);
        fr.add(cancelButton);


        pauseButton = new JButton();
        pauseButton.setBounds(450, 195, 80, 71);
        pauseButton.setText("Pause");
        pauseButton.setFont(new Font("Brush Script MT", Font.PLAIN, 30));
        pauseButton.setBackground(new Color(248, 152, 111));
        pauseButton.setBorder(BorderFactory.createEtchedBorder());
        pauseButton.addActionListener(this);
        fr.add(pauseButton);

        mainPanel = new JPanel();
        mainPanel.setBackground(new Color(139, 126, 102));
        mainPanel.setBounds(40, 40, 300, 300);
        mainPanel.setLayout(new GridLayout(rows, cols, 5, 5));
        mainPanel.setEnabled(true);

        button = new JButton[rows][cols];
        numbersOnBoard = new JLabel[rows][cols];


        this.randomFromArray(rows);

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                button[i][j] = new JButton();
                numbersOnBoard[i][j] = new JLabel();
                //TODO поместить label на центр кнопки
                String text = i + "," + j;
                button[i][j].setText(text);
                button[i][j].setFont(new Font("TimesRoman", Font.PLAIN, 0));
                button[i][j].setLayout(new FlowLayout());

                numbersOnBoard[i][j].setFont(new Font("Clement Numbers", Font.PLAIN, 30));
                numbersOnBoard[i][j].setForeground(new Color(248, 202, 182));
                numbersOnBoard[i][j].setVerticalAlignment(JLabel.CENTER);
                numbersOnBoard[i][j].setHorizontalAlignment(JLabel.CENTER);


                if (board[i][j] != 0) {
                    numbersOnBoard[i][j].setText(String.valueOf(board[i][j]));
                } else {

                    numbersOnBoard[i][j].setText(" ");
                }

                button[i][j].addActionListener(this);
                button[i][j].add(numbersOnBoard[i][j]);
                button[i][j].setBorder(BorderFactory.createLineBorder(Color.black, 2));
                button[i][j].setBackground(new Color(92, 28, 1));

                mainPanel.add(button[i][j]); //TODO одстраивался в количество ячеек, изменять размер окна, если менять
                //TODO размер доска становилась больше
            }
        }

        winLabel.setBounds(380, 10, 240, 100);
        winLabel.setFont(new Font(null, Font.PLAIN, 20));
        winLabel.setForeground(new Color(225, 81, 20));
        winLabel.setVisible(false);

        JLayeredPane layeredPane = new JLayeredPane();
        layeredPane.setBounds(0, 0, 640, 420);
        layeredPane.add(label2, Integer.valueOf(0));
        layeredPane.add(mainPanel, Integer.valueOf(1));
        layeredPane.add(winLabel, Integer.valueOf(1));

        fr.add(layeredPane);

        ImageIcon icon = new ImageIcon("icon.jpeg");
        fr.setIconImage(icon.getImage());
        fr.setVisible(true);

    }

    public void randomFromArray(int dimensions) {
        Random rand = new Random();
        int[] numbers = new int[dimensions * dimensions];
        for (int i = 0; i < (dimensions * dimensions); i++) {
            numbers[i] = i + 1;
        }
        numbers[dimensions * dimensions-1] = 0;
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

        new RecordWriter(new Player(getPlayerName(),moveCount));
        //TODO запрос на имя игрока, если пропускает - player - DONE
        winLabel.setVisible(true);
        mainPanel.setVisible(false);//кликабл фрлс
        //new RecordWriter(moveCount);
        //TODO сохранять турнирную таблицу, игрок должен ввести имя
        return true;
    }
    public String getPlayerName() throws IOException {
       GetPlayerName getPlayerName =  new GetPlayerName();
        return getPlayerName.readFromFile();
    }


    @Override
    public void actionPerformed(ActionEvent e) {
//        if (e.getSource() == cancelButton) {
//            fr.dispose();
//            new StartFrame();
//        }
//        if (e.getSource() == pauseButton) {
//            //TODO timer stops
//            new PauseFrame();
//        }

        String s = e.getActionCommand();
        System.out.println(s);
        //TODO Выходит ошибка из--за parseInt, если кнопки убрать ошибки не будет
        //TODO прорефакторить условия при изменении размера матрицы
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

}
//TODO не всегда можно выйграть, как это можно решить?