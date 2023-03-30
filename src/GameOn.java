import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;
import java.util.Random;


public class GameOn implements ActionListener {
    int moveCount = 0;
    JFrame fr;
    JPanel mainPanel;
    JLabel winLabel = new JLabel("Game ends. Well done!");
    JLabel[][] label;
    JButton pauseButton;
    JButton cancelButton;
    JButton[][] button;
    int rows;
    int cols;
    int[][] board;
    // TODO Нормально структурировать код, а то все где попало написано!!!

    public GameOn() {
        rows = 4;
        cols = 4;
        board = new int[rows][cols];
        initGUI();
    }

    public void initGUI() {

        fr = new JFrame("Sliding Puzzle");

//        JLayeredPane layeredPane = new JLayeredPane();
//        layeredPane.setBounds(0, 0, 500, 500);
//        layeredPane.add(label1,Integer.valueOf(0));
//        layeredPane.add(label2,Integer.valueOf(2));
////        layeredPane.add(label2,JLayeredPane.DEFAULT_LAYER);
//        layeredPane.add(label3,Integer.valueOf(1));

        JFrame frame = new JFrame();
        frame.add(layeredPane);

        ImageIcon icon1 = new ImageIcon("img.png");
        JLabel label2 = new JLabel(icon1);
        fr.add(label2).setBounds(0,0,640,420);

        cancelButton = new JButton();
        cancelButton.setBounds(430, 260, 100, 50);
        cancelButton.setText("Cancel");
        cancelButton.addActionListener(this);
        fr.add(cancelButton);


        pauseButton = new JButton();
        pauseButton.setBounds(430, 200, 100, 50);
        pauseButton.setText("Pause");
        pauseButton.addActionListener(this);
        fr.add(pauseButton);


        mainPanel = new JPanel();
        mainPanel.setBackground(Color.white);
        mainPanel.setBounds(40, 40, 300, 300);
        mainPanel.setLayout(new GridLayout(rows, cols, 5, 5));
        mainPanel.setEnabled(true);

        button = new JButton[rows][cols];
        label = new JLabel[rows][cols];

        this.randomFromArray(rows);

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                button[i][j] = new JButton();
                label[i][j] = new JLabel();
                //TODO поместить label на центр кнопки
                String text = i + "," + j;
                button[i][j].setText(text);
                button[i][j].setFont(new Font("TimesRoman", Font.PLAIN, 0));
                button[i][j].setLayout(new FlowLayout());


                label[i][j].setFont(new Font("TimesRoman", Font.BOLD, 30));
                label[i][j].setForeground(Color.LIGHT_GRAY);
                label[i][j].setVerticalAlignment(JLabel.CENTER);
                label[i][j].setHorizontalAlignment(JLabel.CENTER);


                if (board[i][j] != 0) {
                    label[i][j].setText(String.valueOf(board[i][j]));
                } else {

                    label[i][j].setText(" ");
                }

                button[i][j].addActionListener(this);
                button[i][j].add(label[i][j]);
                button[i][j].setBorder(BorderFactory.createLineBorder(Color.black, 2));
                button[i][j].setBackground(new Color(84, 32, 16));

                mainPanel.add(button[i][j]);
            }
        }

        winLabel.setBounds(380, 10, 240, 100);
        winLabel.setFont(new Font(null, Font.PLAIN, 20));
        winLabel.setForeground(new Color(68, 213, 87));
        winLabel.setVisible(false);

        fr.setLayout(null);
        fr.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        fr.setSize(640, 420);
        fr.setBackground(Color.LIGHT_GRAY);
        fr.setResizable(false);
        fr.add(mainPanel);
        fr.add(winLabel);


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
        numbers[15] = 0;
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

    public Boolean gameOn(int[][] gameDesk) {
        int[][] gameEnd = {{1, 2, 3, 4}, {5, 6, 7, 8}, {9, 10, 11, 12}, {13, 14, 15, 0}};
        for (int i = 0; i < 4; i++) {
            if (!Arrays.equals(gameDesk[i], gameEnd[i])) {
                return false;
            }
        }
        System.out.println(moveCount);
        winLabel.setVisible(true);
        mainPanel.setEnabled(false);
        return true;
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
        int i = Integer.parseInt(s.split(",")[0]);
        int j = Integer.parseInt(s.split(",")[1]);
        if ((j + 1 < 4) && (i < 4) && (i >= 0) && (j >= 0) && (board[i][j + 1] == 0)) {
            label[i][j + 1].setText(String.valueOf(board[i][j]));
            board[i][j + 1] = board[i][j];
            label[i][j].setText("");
            board[i][j] = 0;
            moveCount++;
        }
        if ((j - 1 >= 0) && (i < 4) && (i >= 0) && (j < 4) && (board[i][j - 1] == 0)) {
            label[i][j - 1].setText(String.valueOf(board[i][j]));
            board[i][j - 1] = board[i][j];
            label[i][j].setText("");
            board[i][j] = 0;
            moveCount++;
        }
        if ((i + 1 < 4) && (j < 4) && (i >= 0) && (j >= 0) && (board[i + 1][j] == 0)) {
            label[i + 1][j].setText(String.valueOf(board[i][j]));
            board[i + 1][j] = board[i][j];
            label[i][j].setText("");
            board[i][j] = 0;
            moveCount++;
        }
        if ((i - 1 >= 0) && (j < 4) && (i < 4) && (j >= 0) && (board[i - 1][j] == 0)) {
            label[i - 1][j].setText(String.valueOf(board[i][j]));
            board[i - 1][j] = board[i][j];
            label[i][j].setText("");
            board[i][j] = 0;
            moveCount++;
        }
        gameOn(board);


    }

}
//TODO не всегда можно выйграть, как это можно решить?