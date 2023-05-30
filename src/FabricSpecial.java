import javax.swing.*;
import java.awt.*;

public class FabricSpecial implements Fabric {
    /**
     * @return
     */
    @Override
    public ImageIcon getIcon() {
        ImageIcon icon = new ImageIcon("icon.jpeg");
        return icon;
    }

    /**
     * @return
     */
    @Override
    public JLabel getBackgroundColor() {
        JLabel backgroundColor = new JLabel();
        backgroundColor.setBackground(new Color(139, 126, 102));
        backgroundColor.setOpaque(true);
        backgroundColor.setBounds(0, 0, 640, 420);
        return backgroundColor;
    }

    /**
     * @return
     */
    @Override
    public JLayeredPane getFrameWidth() {
        JLayeredPane frameWidth = new JLayeredPane();
        frameWidth.setBounds(0, 0, 640, 420);
        return frameWidth;
    }

    /**
     * @return
     */
    @Override
    public JButton getNewGameButton() {
        JButton newGameButton = new JButton("New game");
        newGameButton.setBounds(260, 100, 100, 50);
        newGameButton.setFont(new Font("Brush Script MT", Font.PLAIN, 30));
        newGameButton.setBackground(new Color(248, 152, 111));
        newGameButton.setBorder(BorderFactory.createEtchedBorder());
        return newGameButton;
    }

    /**
     * @return
     */
    @Override
    public JButton getRecordButton() {
        JButton recordsButton = new JButton("Records");
        recordsButton.setBounds(260, 200, 100, 50);
        recordsButton.setFont(new Font("Brush Script MT", Font.PLAIN, 30));
        recordsButton.setBackground(new Color(248, 152, 111));
        recordsButton.setBorder(BorderFactory.createEtchedBorder());
        return recordsButton;
    }

    /**
     * @return
     */
    @Override
    public JButton getExitButton() {
        JButton exitButton = new JButton("EXIT");
        exitButton.setBounds(260, 300, 100, 50);
        exitButton.setFont(new Font("Brush Script MT", Font.PLAIN, 30));
        exitButton.setBackground(new Color(248, 152, 111));
        exitButton.setBorder(BorderFactory.createEtchedBorder());
        return exitButton;
    }


    /**
     * @return
     */
    @Override
    public JLabel getGameTitle() {
        JLabel gameTitle = new JLabel("Sliding Puzzle");
        gameTitle.setForeground(Color.DARK_GRAY);
        gameTitle.setFont(new Font("Cursive", Font.ITALIC, 50));
        gameTitle.setSize(400, 50);
        gameTitle.setBounds(150, 0, 400, 60);
        return gameTitle;
    }


    /**
     * @return
     */
    @Override
    public JButton getBackButton() {
        JButton backButton = new JButton("Back");
        backButton.setBounds(300, 400, 100, 50);
        backButton.setFont(new Font("Brush Script MT", Font.PLAIN, 30));
        backButton.setBackground(new Color(248, 152, 111));
        backButton.setBorder(BorderFactory.createEtchedBorder());
        return backButton;
    }

    /**
     * @return
     */
    @Override
    public JLabel grtRecordsTitle() {
        JLabel recordsTitle = new JLabel("Record Board");
        recordsTitle.setForeground(new Color(248, 152, 111));
        recordsTitle.setFont(new Font("Cursive", Font.ITALIC, 30));
        recordsTitle.setSize(200, 50);
        recordsTitle.setBounds(150, 0, 200, 50);
        return recordsTitle;
    }

    /**
     * @return
     */
    @Override
    public JFrame getGameFrame() {
        JFrame gameFrame = new JFrame("Sliding Puzzle");
        gameFrame.setLayout(null);
        gameFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        gameFrame.setSize(640, 420);
        gameFrame.setResizable(false);
        return gameFrame;
    }

    /**
     * @return
     */
    @Override
    public JButton getCancelButton() {
        JButton cancelButton = new JButton("Cancel");
        cancelButton.setBounds(450, 270, 80, 71);
        cancelButton.setFont(new Font("Brush Script MT", Font.PLAIN, 30));
        cancelButton.setBackground(new Color(248, 152, 111));
        cancelButton.setBorder(BorderFactory.createEtchedBorder());

        return cancelButton;
    }

    /**
     * @return
     */
    @Override
    public JPanel getMainPanel() {
        JPanel mainPanel = new JPanel();
        mainPanel.setBackground(new Color(139, 126, 102));
        mainPanel.setBounds(40, 40, 300, 300);
        mainPanel.setEnabled(true);
        return mainPanel;
    }

    /**
     * @return
     */
    @Override
    public JLabel getWinLabel() {
        JLabel winLabel = new JLabel("Game ends. Well done!");
        winLabel.setBounds(30, 50, 600, 100);
        winLabel.setFont(new Font(null, Font.PLAIN, 40));
        winLabel.setForeground(new Color(248, 152, 111));
        winLabel.setVisible(false);
        return winLabel;
    }
}
