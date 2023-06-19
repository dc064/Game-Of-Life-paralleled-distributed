package display;

import java.awt.Canvas;
import java.awt.Color;

import settings.GlobalSettings;

public class CellDisplay extends Canvas    
{    
    public CellDisplay(int x, int y) { 
        setBackground (Color.GREEN);  
        setBounds(x * GlobalSettings.w_cell, y * GlobalSettings.h_cell, GlobalSettings.w_cell, GlobalSettings.h_cell);
        setVisible(true);
    }    
}
