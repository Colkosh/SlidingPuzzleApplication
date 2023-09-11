import com.fasterxml.jackson.databind.ObjectMapper;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class PlayerNameWriter extends JFrame implements ActionListener {

    JButton submitButton;
    JLabel backgroundColor;
    JTextField textField;
    ObjectMapper objectMapper = new ObjectMapper();
    File nameFile = new File("players.json");

    public void writeField(Fabric fabric) {
        // Set up the player name input frame
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLayout(new FlowLayout());

        this.setIconImage(fabric.getIcon().getImage());

        backgroundColor = fabric.getBackgroundColor();

        submitButton = fabric.getSubmitButton();
        submitButton.addActionListener(this);

        textField = fabric.getTextField();

        this.add(backgroundColor);
        this.add(submitButton);
        this.add(textField);
        this.pack(); // Set the size of the frame to fit the components
        this.setVisible(true);
    }

    public String readFromFile() throws IOException {
        // Read the player name from the file
        return this.objectMapper.readValue(nameFile, String.class);
    }

    public void writeToFile(String name, ObjectMapper objectMapper) throws IOException {
        // Write the player name to the file
        String jsonString = objectMapper.writeValueAsString(name);
        Files.write(Paths.get("players.json"), jsonString.getBytes());
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == submitButton) {
            // Handle the submit button click
            String text = textField.getText();
            try {
                writeToFile(text, objectMapper);
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
            submitButton.setEnabled(false);
            textField.setEditable(false);
            this.dispose();
        }
    }
}