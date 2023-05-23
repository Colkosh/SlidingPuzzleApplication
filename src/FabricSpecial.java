import javax.swing.*;

public class FabricSpecial implements Fabric {


    @Override
    public JButton getRecordButton() {
        JButton recordsButton = new JButton();
        recordsButton.setBounds(150, 200, 100, 50);
        recordsButton.setText("Records");
        return recordsButton;
    }

    @Override
    public JButton getExitButton() {
        JButton exitButton = new JButton();
        exitButton.setBounds(150, 300, 100, 50);
        exitButton.setText("EXIT");
        return exitButton;
    }
}
