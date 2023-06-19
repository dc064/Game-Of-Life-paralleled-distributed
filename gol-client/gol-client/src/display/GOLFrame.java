package display;

import javax.swing.JFrame;

import cell.Cell;
import settings.GameSettings;

public class GOLFrame extends JFrame {

  private GOLPanel panel;

  public GOLFrame() {
    this.setSize(GameSettings.windowW, GameSettings.windowH);
    setLayout(null);
    panel = new GOLPanel();

    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    this.add(panel);
    this.panel.setVisible(true);
    this.setLocationRelativeTo(null);
    this.setVisible(true);
  }

  public GOLFrame(Cell[][] cells) {
    this.setSize(GameSettings.windowW, GameSettings.windowH);
    setLayout(null);
    panel = new GOLPanel();
    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    this.add(panel);
    this.panel.setVisible(true);
    this.setLocationRelativeTo(null);
    this.setExtendedState(MAXIMIZED_BOTH);
    this.setVisible(true);
  }

  public void updateCellDisplay()
  {
    this.panel.repaint();
  }
}