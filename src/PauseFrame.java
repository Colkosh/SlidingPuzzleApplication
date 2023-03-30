import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PauseFrame extends JFrame implements ActionListener {
    JButton backButton = new JButton("Back");
    PauseFrame(){
        backButton.setBounds(0, 100, 100, 50);
        backButton.addActionListener(this);

        JLabel label = new JLabel();
        label.setText("Game paused");
        label.setForeground(Color.DARK_GRAY);
        label.setFont(new Font("Cursive", Font.ITALIC,20));
        label.setSize(150,50);
        label.setBounds(0, 0, 200, 50);

        this.setTitle("Sliding Puzzle");
        this.setVisible(true);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setSize(200,200);
        this.setResizable(false);
        this.setLayout(null);
        this.add(label);
        this.add(backButton);

        ImageIcon icon = new ImageIcon("icon.jpeg");
        this.setIconImage(icon.getImage());
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==backButton){
            this.dispose();
        }
    }
}
