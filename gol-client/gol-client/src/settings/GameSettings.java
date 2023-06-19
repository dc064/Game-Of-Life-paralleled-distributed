package settings;

import java.util.List;

public class GameSettings {
    public static final int windowW = 1200;
    public static final int windowH = 800;
    
    public static final int boardW = 700;
    public static final int boardH = 700;

    public static final int settingsListLength = 3;

    public static int delay = 50;

    public static int cellW;
    public static int cellH;

    public static int cellDimensionX;
    public static int cellDimensionY;

    public static int iterations;

    public static void readSettings(List<Integer> settings)
    {
        if(settings.size() != GameSettings.settingsListLength)
        {
            throw new IllegalArgumentException("Incorrect settings list: expected " + GameSettings.settingsListLength
            + " but received " + settings.size());
        }
        GameSettings.cellDimensionX = settings.get(0);
        GameSettings.cellDimensionY = settings.get(1);

        GameSettings.cellW = GameSettings.boardW / GameSettings.cellDimensionX;
        GameSettings.cellH = GameSettings.boardW / GameSettings.cellDimensionY;

        GameSettings.iterations = settings.get(2);
    }
}
