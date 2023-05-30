import javax.swing.*;

public interface Fabric {
    ImageIcon getIcon();

    JLabel getBackgroundColor();

    JLayeredPane getFrameWidth();

    //----------StartFrame---------
    JButton getNewGameButton();

    JButton getRecordButton();

    JButton getExitButton();

    JLabel getGameTitle();


    //------------------------------

    //--------RecordsFrame----------
    JButton getBackButton();

    JLabel grtRecordsTitle();
    //-----------------------------

    //---------GameOn--------------
    JFrame getGameFrame();

    JButton getCancelButton();

    JPanel getMainPanel();

    JLabel getWinLabel();


}
