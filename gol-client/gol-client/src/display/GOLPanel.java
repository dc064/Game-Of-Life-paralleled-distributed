package display;
import javax.swing.JPanel;

import settings.GlobalSettings;

public class GOLPanel extends JPanel {

    public GOLPanel()
    {
        setBounds(10, 10, GlobalSettings.board_w, GlobalSettings.board_h);
        setVisible(true);
    }
    
}
