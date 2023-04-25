package display;
import javax.swing.JFrame;

import cell.Cell;
import settings.GlobalSettings;  
    
public class GOLFrame extends JFrame {    
  
  public GOLPanel panel;  
   public GOLFrame() {
      setSize(GlobalSettings.window_w, GlobalSettings.window_h);
      setLayout(null);
      panel = new GOLPanel();

      this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      this.add(panel);
      this.pack();
      this.setLocationRelativeTo(null);
      this.setVisible(true);
  }
  
  GOLFrame(Cell[][] cells) {
      setSize(GlobalSettings.window_w, GlobalSettings.window_h);
      setLayout(null);
      panel = new GOLPanel();

      this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      this.add(panel);
      this.pack();
      this.setLocationRelativeTo(null);
      this.setExtendedState(MAXIMIZED_BOTH);
      this.setVisible(true);
  }
}