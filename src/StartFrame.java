import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class StartFrame extends JFrame implements ActionListener {

    JButton exitButton;
    JButton recordsButton;
    JButton newGameButton = new JButton("New game");
    StartFrame(){


        newGameButton.setBounds(150, 100, 100, 50);
        newGameButton.addActionListener(this);

        recordsButton = new JButton();
        recordsButton.setBounds(150, 200, 100, 50);
        recordsButton.setText("Records");
        recordsButton.addActionListener(this);

        exitButton = new JButton();
        exitButton.setBounds(150, 300, 100, 50);
        exitButton.setText("EXIT");
        exitButton.addActionListener(this);

        JLabel label = new JLabel();
        label.setText("Sliding Puzzle");
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
        this.add(newGameButton);
        this.add(recordsButton);
        this.add(exitButton);

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
            new GameOn();
        }
        if(e.getSource()==exitButton){
            this.dispose();
            System.exit(0);
        }
    }
}
