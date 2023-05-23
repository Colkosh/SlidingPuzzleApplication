import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.yaml.snakeyaml.scanner.Scanner;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;

public class GetPlayerName extends JFrame implements ActionListener {

    JButton button;
    JTextField textField;
    ObjectMapper objectMapper = new ObjectMapper();
    String[] players;
    File file = new File("players.json");

    GetPlayerName() {
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLayout(new FlowLayout());

        button = new JButton("Submit");
        button.addActionListener(this);

        textField = new JTextField(20);
        textField.setPreferredSize(new Dimension(250, 40));
        textField.setFont(new Font(null, Font.PLAIN, 25));
        textField.setForeground(Color.DARK_GRAY);
        textField.setBackground(Color.BLACK);
        textField.setCaretColor(Color.WHITE);
        textField.setText("Username");//can chancge it
        //textField.addActionListener();

//TODO textField не сохраняется введенное значение
        this.add(button);
        this.add(textField);
        this.pack(); // size wil be comparable to components
        this.setVisible(true);
    }
//    public void setText(String text){
//        this.text = text;
//    }
//    public String getText(){
//        return this.text;
//    }

    public String readFromFile() throws IOException {
        String player = this.objectMapper.readValue(file, String.class);
        return player;
    }
    public void writeToFile(String name, ObjectMapper objectMapper) throws IOException {
//        players = objectMapper.readValue(file, String[].class);

        String jsonString = objectMapper.writeValueAsString(name);
//        ArrayList<> arrayList = new ArrayList<String>();
//        arrayList.add(players);
//        arrayList.add(name);

        Files.write(Paths.get("players.json"), jsonString.getBytes());

    }


    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == button) {
            String text = textField.getText();
            System.out.println(text);
            try {
                writeToFile(text,objectMapper);
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
            button.setEnabled(false);
            textField.setEditable(false);
            this.dispose();
        }

    }

}

class Test1 {
    public static void main(String[] args) throws IOException {

        new GetPlayerName();
    }
}

