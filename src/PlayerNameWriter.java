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
    JTextField textField;
    ObjectMapper objectMapper = new ObjectMapper();
    File nameFile = new File("players.json");

    public void writeField() {
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLayout(new FlowLayout());

        submitButton = new JButton("Submit");
        submitButton.addActionListener(this);

        textField = new JTextField(20);
        textField.setPreferredSize(new Dimension(250, 40));
        textField.setFont(new Font(null, Font.PLAIN, 25));
        textField.setForeground(Color.DARK_GRAY);
        textField.setBackground(Color.BLACK);
        textField.setCaretColor(Color.WHITE);
        textField.setText("Username");


//TODO textField не сохраняется введенное значение Done
        this.add(submitButton);
        this.add(textField);
        this.pack(); // size wil be comparable to components
        this.setVisible(true);
    }

    public String readFromFile() throws IOException {
        return this.objectMapper.readValue(nameFile, String.class);
    }

    public void writeToFile(String name, ObjectMapper objectMapper) throws IOException {
        String jsonString = objectMapper.writeValueAsString(name);
        Files.write(Paths.get("players.json"), jsonString.getBytes());
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == submitButton) {
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

