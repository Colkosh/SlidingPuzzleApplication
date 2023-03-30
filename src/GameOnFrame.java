import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GameOnFrame extends JFrame implements ActionListener {
    JButton pauseButton;
    JButton cancelButton;
    GameOnFrame(){
        cancelButton = new JButton();
        cancelButton.setBounds(500, 200, 100, 50);
        cancelButton.setText("Cancel");
        cancelButton.addActionListener(this);

        pauseButton = new JButton();
        pauseButton.setBounds(500, 100, 100, 50);
        pauseButton.setText("Pause");
        pauseButton.addActionListener(this);



        this.setTitle("Sliding Puzzle");
        this.setVisible(true);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(500,500);
        this.setResizable(false);
        this.setLayout(null);
        this.add(cancelButton);
        this.add(pauseButton);

        ImageIcon icon = new ImageIcon("icon.jpeg");
        this.setIconImage(icon.getImage());
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()== cancelButton){
            this.dispose();
            new StartFrame();
        }
        if(e.getSource()== pauseButton){
            // timer stops
//            this.setEnabled(false);
            new PauseFrame();
        }
    }
}
