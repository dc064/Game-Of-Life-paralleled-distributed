package display;

import java.awt.*;
import javax.swing.JPanel;

import cell.Cell;
import cell.CellRepository;
import settings.GlobalSettings;

public class GOLPanel extends JPanel {

    public GOLPanel() {
        setBounds(10, 10, GlobalSettings.board_w, GlobalSettings.board_h);
        setVisible(true);

        repaint();
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        Graphics2D g2D = (Graphics2D) g;

        g2D.setPaint(Color.BLACK);

        g2D.setColor(Color.red);
        
        if (CellRepository.cells != null) {
            for (Cell iterator : CellRepository.cells) {
                g2D.fillRect(iterator.x * GlobalSettings.h_cell, iterator.y * GlobalSettings.h_cell, GlobalSettings.h_cell, GlobalSettings.w_cell);
            }
        }
    }

    @Override
    public void update(Graphics g) {
        super.paint(g);

        Graphics2D g2D = (Graphics2D) g;

        g2D.setPaint(Color.BLACK);

        super.paint(g);

        g2D.setColor(Color.red);

        if (CellRepository.cells != null) {
            for (Cell iterator : CellRepository.cells) {
                g2D.fillRect(iterator.x * GlobalSettings.h_cell, iterator.y * GlobalSettings.h_cell, GlobalSettings.h_cell, GlobalSettings.w_cell);
            }
                //g2D.fillRect(iterator.x, iterator.y, GlobalSettings.h_cell, GlobalSettings.w_cell);
        }
    }
        // g.fillRect(x,y,GlobalSettings.h_cell, GlobalSettings.w_cell);
}



// package display;
// import javax.swing.JPanel;

// import settings.GlobalSettings;

// public class GOLPanel extends JPanel {

//     public GOLPanel()
//     {
//         setBounds(10, 10, GlobalSettings.board_w, GlobalSettings.board_h);
//         setVisible(true);
//     }
    
// }
