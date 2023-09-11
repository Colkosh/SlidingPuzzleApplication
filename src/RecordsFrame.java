import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;


public class RecordsFrame extends JFrame implements ActionListener {
    // GUI components
    JButton backButton;
    JLabel recordsTitle;
    JLabel backgroundColor;
    ImageIcon icon;
    JLayeredPane frameWidth;

    RecordsFrame(Fabric fabric) throws IOException {
        // Initialize GUI components using the "fabric" object
        this.backButton = fabric.getBackButton();
        this.backButton.addActionListener(this);

        this.backgroundColor = fabric.getBackgroundColor();
        this.backgroundColor.setSize(500, 500);

        this.recordsTitle = fabric.getRecordsTitle();

        this.frameWidth = fabric.getFrameWidth();
        this.frameWidth.setSize(500, 500);

        // Add GUI components to the layered pane
        this.frameWidth.add(backgroundColor, Integer.valueOf(0));
        this.frameWidth.add(recordsTitle, Integer.valueOf(1));
        this.frameWidth.add(backButton, Integer.valueOf(1));

        // Display the records
        showRecords();

        // Set JFrame properties
        this.setTitle("Sliding Puzzle");
        this.setVisible(true);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(500, 500);
        this.setResizable(false);
        this.setLayout(null);

        this.add(frameWidth);

        this.icon = fabric.getIcon();
        this.setIconImage(icon.getImage());
    }

    // Read records from a file using class RecordWriter method readRecords
    public Player[] readRecords() throws IOException {
        RecordWriter recordWriter = new RecordWriter();
        return recordWriter.readRecords();
    }

    // Display the records on the GUI
    public void showRecords() throws IOException {
        Player[] players = readRecords();
        int count = 1;

        for (Player player : players) {
            JLabel record = new JLabel(count + ")" + player);
            record.setForeground(Color.DARK_GRAY);
            record.setFont(new Font("Cursive", Font.ITALIC, 30));
            record.setBounds(30, count * 30, 400, 50);
            this.frameWidth.add(record, Integer.valueOf(1));
            count++;
        }
    }

    // Handle button click events
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == backButton) {
            this.dispose(); // Close the current window
            new StartFrame(); // Open a new StartFrame window
        }
    }
}

