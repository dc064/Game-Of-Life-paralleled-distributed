package display;

import java.awt.*;
import javax.swing.JPanel;

import cell.Cell;
import cell.CellRepository;
import settings.GameSettings;

public class GOLPanel extends JPanel {

    public GOLPanel() {
        setBounds(10, 10, GameSettings.boardW, GameSettings.boardH);
        setVisible(true);

        repaint();
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        Graphics2D g2D = (Graphics2D) g;

        g2D.setColor(Color.red);
        
        if (CellRepository.cells != null) {
            for (Cell iterator : CellRepository.cells) {
                g2D.fillRect(iterator.x * GameSettings.cellH, iterator.y * GameSettings.cellH, GameSettings.cellH, GameSettings.cellW);
            }
        }
    }

    @Override
    public void update(Graphics g) {
        super.paint(g);

        Graphics2D g2D = (Graphics2D) g;

        g2D.setColor(Color.red);

        if (CellRepository.cells != null) {
            for (Cell iterator : CellRepository.cells) {
                g2D.fillRect(iterator.x * GameSettings.cellH, iterator.y * GameSettings.cellH, GameSettings.cellH, GameSettings.cellW);
            }
        }
    }
}
