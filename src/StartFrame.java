import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class StartFrame extends JFrame implements ActionListener {

    // Buttons
    JButton exitButton;
    JButton recordsButton;
    JButton newGameButton;

    // Labels
    JLabel gameTitle;
    JLabel backgroundColor;

    // Layered pane
    JLayeredPane frameWidth;

    // Image icon
    ImageIcon icon;

    // Fabric instance
    Fabric fabric = new FabricSpecial();

    StartFrame() {
        // Set frame properties
        this.setTitle("Sliding Puzzle");
        this.setVisible(true);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(640, 420);
        this.setResizable(false);
        this.setLayout(new GridLayout());

        // Get background color from the fabric
        this.backgroundColor = this.fabric.getBackgroundColor();

        // Get buttons from the fabric and attach action listeners
        this.newGameButton = this.fabric.getNewGameButton();
        this.newGameButton.addActionListener(this);

        this.recordsButton = this.fabric.getRecordButton();
        this.recordsButton.addActionListener(this);

        this.exitButton = this.fabric.getExitButton();
        this.exitButton.addActionListener(this);

        // Get game title label from the fabric
        this.gameTitle = this.fabric.getGameTitle();

        // Get layered pane from the fabric
        this.frameWidth = this.fabric.getFrameWidth();

        // Add components to the layered pane
        this.frameWidth.add(backgroundColor, Integer.valueOf(0));
        this.frameWidth.add(gameTitle, Integer.valueOf(1));
        this.frameWidth.add(exitButton, Integer.valueOf(1));
        this.frameWidth.add(recordsButton, Integer.valueOf(1));
        this.frameWidth.add(newGameButton, Integer.valueOf(1));

        // Add layered pane to the frame
        this.add(frameWidth);

        // Get and set the icon for the frame
        this.icon = this.fabric.getIcon();
        this.setIconImage(icon.getImage());
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        // Handle button clicks
        if (e.getSource() == recordsButton) {
            // Close the current frame
            this.dispose();

            try {
                // Create a new RecordsFrame and pass the fabric instance
                new RecordsFrame(fabric);
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }

        }
        if (e.getSource() == newGameButton) {
            // Close the current frame
            this.dispose();

            try {
                // Create a new GameOn instance and pass the fabric instance
                new GameOnFrame(fabric);
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        }
        if (e.getSource() == exitButton) {
            // Close the current frame
            this.dispose();

            // Exit the application
            System.exit(0);
        }
    }
}