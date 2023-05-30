import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class StartFrame extends JFrame implements ActionListener {

    JButton exitButton;
    JButton recordsButton;
    JButton newGameButton;
    JLabel gameTitle;
    JLabel backgroundColor;
    JLayeredPane frameWidth;
    ImageIcon icon;
    Fabric fabric = new FabricSpecial();

    StartFrame() {
        this.setTitle("Sliding Puzzle");
        this.setVisible(true);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(640, 420);
        this.setResizable(false);
        this.setLayout(new GridLayout());

//        TODO пернести все, ооставить только присваивание
//        TODO добавить конфигурационный файл который будет содеражжать настройку цвета
        this.backgroundColor = this.fabric.getBackgroundColor();

        this.newGameButton = this.fabric.getNewGameButton();
        this.newGameButton.addActionListener(this);

        this.recordsButton = this.fabric.getRecordButton();
        this.recordsButton.addActionListener(this);

        this.exitButton = this.fabric.getExitButton();
        this.exitButton.addActionListener(this);

        this.gameTitle = this.fabric.getGameTitle();

        this.frameWidth = this.fabric.getFrameWidth();
        this.frameWidth.add(backgroundColor, Integer.valueOf(0));
        this.frameWidth.add(gameTitle, Integer.valueOf(1));
        this.frameWidth.add(exitButton, Integer.valueOf(1));
        this.frameWidth.add(recordsButton, Integer.valueOf(1));
        this.frameWidth.add(newGameButton, Integer.valueOf(1));

        this.add(frameWidth);

        this.icon = this.fabric.getIcon();
        this.setIconImage(icon.getImage());
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == recordsButton) {
            this.dispose();

            try {
                new RecordsFrame(fabric);
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }

        }
        if (e.getSource() == newGameButton) {
            this.dispose();
            try {
                new GameOn(fabric);

            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        }
        if (e.getSource() == exitButton) {
            this.dispose();
            System.exit(0);
        }
    }
}
