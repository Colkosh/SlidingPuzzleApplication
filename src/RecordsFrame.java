import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class RecordsFrame extends JFrame implements ActionListener {
    JButton backButton;

    RecordsFrame() {
        backButton = new JButton();
        backButton.setBounds(300, 400, 100, 50);
        backButton.setText("Back");
        backButton.addActionListener(this);

        JLabel label = new JLabel();
        label.setText("Records");
        label.setForeground(Color.DARK_GRAY);
        label.setFont(new Font("Cursive", Font.ITALIC, 30));
        label.setSize(200, 50);
        label.setBounds(150, 0, 200, 50);

        this.setTitle("Sliding Puzzle");
        this.setVisible(true);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(500, 500);
        this.setResizable(false);
        this.setLayout(null);
        this.add(label);
        this.add(backButton);

        ImageIcon icon = new ImageIcon("icon.jpeg");
        this.setIconImage(icon.getImage());

    }


    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == backButton) {
            this.dispose();
//            new StartFrame();
        }
    }
}
