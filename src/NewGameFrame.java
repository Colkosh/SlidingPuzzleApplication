import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class NewGameFrame extends JFrame implements ActionListener {
    JButton fourOnFourButton;
    JButton threeOnThreeButton;
    JButton backButton;
    JButton createGameButton;
    NewGameFrame(){

        backButton = new JButton();
        backButton.setBounds(150, 300, 100, 50);
        backButton.setText("Back");
        backButton.addActionListener(this);

        createGameButton = new JButton();
        createGameButton.setBounds(150, 200, 200, 50);
        createGameButton.setText("Create game");
        createGameButton.addActionListener(this);

        threeOnThreeButton = new JButton();
        threeOnThreeButton.setBounds(150, 100, 100, 50);
        threeOnThreeButton.setText("3 x 3");
        threeOnThreeButton.addActionListener(this);

        fourOnFourButton = new JButton();
        fourOnFourButton.setBounds(300, 100, 100, 50);
        fourOnFourButton.setText("4 x 4");
        fourOnFourButton.addActionListener(this);

        JLabel label = new JLabel();
        label.setText("New game");
        label.setForeground(Color.DARK_GRAY);
        label.setFont(new Font("Cursive", Font.ITALIC,30));
        label.setSize(200,50);
        label.setBounds(150, 0, 200, 50);

        this.setTitle("Sliding Puzzle");
        this.setVisible(true);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(500,500);
        this.setResizable(false);
        this.setLayout(null);
        this.add(label);
        this.add(backButton);
        this.add(threeOnThreeButton);
        this.add(fourOnFourButton);
        this.add(createGameButton);

        ImageIcon icon = new ImageIcon("icon.jpeg");
        this.setIconImage(icon.getImage());
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==threeOnThreeButton){
            //threeOnThreeBoard.setVisible(true)
            System.out.println("You choose 3 x 3 board");
        }
        if(e.getSource()==fourOnFourButton){
            //fourOnFourBoard.setVisible(true)
            System.out.println("You choose 4 x 4 board");
        }
        if(e.getSource()==backButton){
            this.dispose();
            //new StartFrame();
        }
        if(e.getSource()==createGameButton){
            this.dispose();
            try {
                new GameOn();
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        }
    }
}
