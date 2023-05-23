import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class StartFrame extends JFrame implements ActionListener {

    JButton exitButton;
    JButton recordsButton;
    JButton newGameButton = new JButton("New game");
    StartFrame(Fabric fabric){
        this.setTitle("Sliding Puzzle");
        this.setVisible(true);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(640,420);
        this.setResizable(false);
        this.setLayout(null);
 
//        TODO пернести все, ооставить только присваивание
//        TODO добавить конфигурационный файл который будет содеражжать настройку цвета
        JLabel backgroundColor = new JLabel();
        backgroundColor.setBackground(new Color(139, 126, 102));
        backgroundColor.setOpaque(true);
        this.add(backgroundColor).setBounds(0, 0, 640, 420);

        newGameButton.setBounds(150, 100, 100, 50);
        newGameButton.addActionListener(this);

        this.recordsButton = fabric.getRecordButton();
        this.recordsButton.addActionListener(this);

        this.exitButton = fabric.getExitButton();
        this.exitButton.addActionListener(this);

        JLabel gameTitle = new JLabel();
        gameTitle.setText("Sliding Puzzle");
        gameTitle.setForeground(Color.DARK_GRAY);
        gameTitle.setFont(new Font("Cursive", Font.ITALIC,30));
        gameTitle.setSize(200,50);
        gameTitle.setBounds(150, 0, 200, 50);

        JLayeredPane frameWidth = new JLayeredPane();
        frameWidth.setBounds(0, 0, 640, 420);
        frameWidth.add(backgroundColor, Integer.valueOf(0));
        frameWidth.add(gameTitle, Integer.valueOf(1));
        frameWidth.add(exitButton,Integer.valueOf(1));
        frameWidth.add(recordsButton,Integer.valueOf(1));
        frameWidth.add(newGameButton,Integer.valueOf(1));

        this.add(frameWidth);

        ImageIcon icon = new ImageIcon("icon.jpeg");
        this.setIconImage(icon.getImage());
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==recordsButton){
            this.dispose();
            new RecordsFrame();
        }
        if(e.getSource()==newGameButton){
            this.dispose();
            try {
                new GameOn();

            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        }
        if(e.getSource()==exitButton){
            this.dispose();
            System.exit(0);
        }
    }
}
