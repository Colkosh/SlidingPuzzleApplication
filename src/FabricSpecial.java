import javax.swing.*;
import java.awt.*;

public class FabricSpecial implements Fabric {
    /**
     * Returns an ImageIcon for the game icon.
     *
     * @return The ImageIcon for the game icon.
     */
    @Override
    public ImageIcon getIcon() {
        ImageIcon icon = new ImageIcon("icon.jpeg");
        return icon;
    }

    /**
     * Returns a JLabel for the background color.
     *
     * @return The JLabel for the background color.
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
     * Returns a JLayeredPane for the frame width.
     *
     * @return The JLayeredPane for the frame width.
     */
    @Override
    public JLayeredPane getFrameWidth() {
        JLayeredPane frameWidth = new JLayeredPane();
        frameWidth.setBounds(0, 0, 640, 420);
        return frameWidth;
    }

    /**
     * Returns a JButton for the new game button.
     *
     * @return The JButton for the new game button.
     */
    @Override
    public JButton getNewGameButton() {
        JButton newGameButton = new JButton("New game");
        newGameButton.setBounds(220, 100, 200, 50);
        newGameButton.setFont(new Font("Brush Script MT", Font.PLAIN, 30));
        newGameButton.setBackground(new Color(248, 152, 111));
        newGameButton.setBorder(BorderFactory.createEtchedBorder());
        return newGameButton;
    }

    /**
     * Returns a JButton for the records button.
     *
     * @return The JButton for the records button.
     */
    @Override
    public JButton getRecordButton() {
        JButton recordsButton = new JButton("Records");
        recordsButton.setBounds(220, 200, 200, 50);
        recordsButton.setFont(new Font("Brush Script MT", Font.PLAIN, 30));
        recordsButton.setBackground(new Color(248, 152, 111));
        recordsButton.setBorder(BorderFactory.createEtchedBorder());
        return recordsButton;
    }

    /**
     * Returns a JButton for the exit button.
     *
     * @return The JButton for the exit button.
     */
    @Override
    public JButton getExitButton() {
        JButton exitButton = new JButton("EXIT");
        exitButton.setBounds(220, 300, 200, 50);
        exitButton.setFont(new Font("Brush Script MT", Font.PLAIN, 30));
        exitButton.setBackground(new Color(248, 152, 111));
        exitButton.setBorder(BorderFactory.createEtchedBorder());
        return exitButton;
    }


    /**
     * Returns a JLabel for the game title.
     *
     * @return The JLabel for the game title.
     */
    @Override
    public JLabel getGameTitle() {
        JLabel gameTitle = new JLabel("Sliding Puzzle");
        gameTitle.setForeground(Color.DARK_GRAY);
        gameTitle.setFont(new Font("Cursive", Font.ITALIC, 50));
        gameTitle.setBounds(165, 0, 310, 60);
        return gameTitle;
    }

    /**
     * Returns a JButton for the back button.
     *
     * @return The JButton for the back button.
     */
    @Override
    public JButton getBackButton() {
        JButton backButton = new JButton("Back");
        backButton.setBounds(40, 400, 400, 50);
        backButton.setFont(new Font("Brush Script MT", Font.PLAIN, 30));
        backButton.setBackground(new Color(248, 152, 111));
        backButton.setBorder(BorderFactory.createEtchedBorder());
        return backButton;
    }

    /**
     * Returns a JLabel for the records title.
     *
     * @return The JLabel for the records title.
     */
    @Override
    public JLabel getRecordsTitle() {
        JLabel recordsTitle = new JLabel("Record Board");
        recordsTitle.setForeground(new Color(248, 152, 111));
        recordsTitle.setFont(new Font("Cursive", Font.ITALIC, 30));
        recordsTitle.setSize(200, 50);
        recordsTitle.setBounds(150, 0, 200, 50);
        return recordsTitle;
    }

    /**
     * Returns a JFrame for the game frame.
     *
     * @return The JFrame for the game frame.
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
     * Returns a JButton for the cancel button.
     *
     * @return The JButton for the cancel button.
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
     * Returns a JPanel for the main panel.
     *
     * @return The JPanel for the main panel.
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
     * Returns a JLabel for the win label.
     *
     * @return The JLabel for the win label.
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

    /**
     * Returns a JButton for the submit button.
     *
     * @return The JButton for the submit button.
     */
    @Override
    public JButton getSubmitButton() {
        JButton submitButton = new JButton("Submit");
        submitButton.setFont(new Font("Brush Script MT", Font.PLAIN, 30));
        submitButton.setBackground(new Color(248, 152, 111));
        submitButton.setBorder(BorderFactory.createEtchedBorder());
        return submitButton;
    }

    /**
     * Returns a JTextField for the text field.
     *
     * @return The JTextField for the text field.
     */
    @Override
    public JTextField getTextField() {
        JTextField textField = new JTextField(20);
        textField.setPreferredSize(new Dimension(300, 50));
        textField.setFont(new Font(null, Font.PLAIN, 25));
        textField.setForeground(Color.LIGHT_GRAY);
        textField.setBackground(new Color(139, 126, 102));
        textField.setCaretColor(Color.LIGHT_GRAY);
        textField.setText("Username");
        return textField;
    }
}
