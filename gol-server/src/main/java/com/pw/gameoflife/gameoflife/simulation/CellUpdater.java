package com.pw.gameoflife.gameoflife.simulation;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;

import com.pw.gameoflife.gameoflife.repositories.CellRepository;
import com.pw.gameoflife.gameoflife.settings.GameSettings;

public class CellUpdater {

    static int[][] grid = new int[GameSettings.boardX][GameSettings.boardY];
    static List<Cell> toReturn = new ArrayList<>();
    static final AtomicReference<List<Cell>> toReturnAtom = new AtomicReference<List<Cell>>(toReturn);
    static ThreadPoolExecutor executor;

    public static void nextStep(List<Cell> cells, int M, int N, int iteration) throws Exception {

        grid = new int[M][N];

        toReturn = new ArrayList<Cell>();
        
        for (Cell c : cells) {
            grid[c.x][c.y] = 1;
        }

        int currentBorder = GameSettings.borderType;
        int currentNeighbourhood = GameSettings.neighbourhood;
        executor = (ThreadPoolExecutor) Executors.newFixedThreadPool(GameSettings.threads);

        for (int y = 0; y < GameSettings.boardY; y++) {
            for (int x = 0; x < GameSettings.boardX; x++) 
            {
                Runnable runnable = new SingleCellCalc(x, y, grid[x][y], grid, currentBorder, currentNeighbourhood);
				executor.execute(runnable);
            }
        }
        executor.shutdown();
        boolean isDone = executor.awaitTermination(10, TimeUnit.MINUTES);
        if(!isDone)
        {
            executor.shutdownNow();
            throw new InterruptedException();
        }

        CellRepository.put(toReturn, iteration);
    }

    public static int countNeighbours(int[][] grid, int x, int y, int neighbourhood, int borderType) {
        if (neighbourhood == 0) {
            return countNeighboursMoore(grid, x, y, borderType);
        } else if (neighbourhood == 1) {
            return countNeighboursNeumann(grid, x, y, borderType);
        } else {
            return -1;
        }
    }

    private static int countNeighboursNeumann(int[][] grid, int x, int y, int borderType) {
        int type = checkCellType(x, y);

        int counter = 0;

        if (borderType == 0) // normalnie
        {
            if (type != 1 && type != 2 && type != 3) {
                counter += grid[x][y + 1];
            }
            if (type != 1 && type != 4 && type != 7) {
                counter += grid[x - 1][y];
            }
            if (type != 3 && type != 6 && type != 9) {
                counter += grid[x + 1][y];
            }
            if (type != 7 && type != 8 && type != 9) {
                counter += grid[x][y - 1];
            }
        }
        else if (borderType == 1) // wrapper
        {
            //gora
            if (type != 1 && type != 2 && type != 3) {
                counter += grid[x][y + 1];
            }
            else {
                int currY = y + 1;
                if (currY >= GameSettings.boardY) {
                    currY -= GameSettings.boardY;
                }
                counter += grid[x][currY];
            }

            //lewo
            if (type != 1 && type != 4 && type != 7) {
                counter += grid[x - 1][y];
            }
            else {
                int currX = x - 1;
                if (currX < 0) {
                    currX += GameSettings.boardX;
                }
                counter += grid[currX][y];
            }

            //prawo
            if (type != 3 && type != 6 && type != 9) {
                counter += grid[x + 1][y];
            }
            else {
                int currX = x + 1;
                if (currX >= GameSettings.boardX) {
                    currX -= GameSettings.boardX;
                }
                counter += grid[currX][y];
            }

            //dol
            if (type != 7 && type != 8 && type != 9) {
                counter += grid[x][y - 1];
            }
            else {
                int currY = y - 1;
                if (currY < 0) {
                    currY += GameSettings.boardY;
                }
                counter += grid[x][currY];
            }
        }

        return counter;
    }

    static int countNeighboursMoore(int[][] grid, int x, int y, int borderType) {
        int counter = 0;

        int type = checkCellType(x, y);

        if (borderType == 0) // normalnie
        {
            if (type == 2 || type == 3 || type == 5 || type == 6) {
                counter += grid[x - 1][y - 1];
            }

            if (type < 7) {
                counter += grid[x][y - 1];
            }

            if (type == 1 || type == 2 || type == 4 || type == 5) {
                counter += grid[x + 1][y - 1];
            }

            if (type != 1 && type != 4 && type != 7) {
                counter += grid[x - 1][y];
            }

            if (type != 3 && type != 6 && type != 9) {
                counter += grid[x + 1][y];
            }

            if (type == 5 || type == 6 || type == 8 || type == 9) {
                counter += grid[x - 1][y + 1];
            }

            if (type > 3) {
                counter += grid[x][y + 1];
            }

            if (type == 4 || type == 5 || type == 7 || type == 8) {
                counter += grid[x + 1][y + 1];
            }
        }

        else if (borderType == 1) // wrapper
        {
            // lewy dol
            if (type == 2 || type == 3 || type == 5 || type == 6) {
                counter += grid[x - 1][y - 1];
            } else {
                int currX = x - 1;
                int currY = y - 1;
                if (currX < 0) {
                    currX += GameSettings.boardX;
                }
                if (currY < 0) {
                    currY += GameSettings.boardY;
                }
                counter += grid[currX][currY];
            }

            // dol
            if (type < 7) {
                counter += grid[x][y - 1];
            } else {
                int currY = y - 1;
                if (currY < 0) {
                    currY += GameSettings.boardY;
                }
                counter += grid[x][currY];
            }

            // prawy dol
            if (type == 1 || type == 2 || type == 4 || type == 5) {
                counter += grid[x + 1][y - 1];
            } else {
                int currX = x + 1;
                int currY = y - 1;
                if (currX >= GameSettings.boardX) {
                    currX -= GameSettings.boardX;
                }
                if (currY < 0) {
                    currY += GameSettings.boardY;
                }
                counter += grid[currX][currY];
            }

            // lewo
            if (type != 1 && type != 4 && type != 7) {
                counter += grid[x - 1][y];
            } else {
                int currX = x - 1;
                if (currX < 0) {
                    currX += GameSettings.boardX;
                }
                counter += grid[currX][y];
            }

            // prawo
            if (type != 3 && type != 6 && type != 9) {
                counter += grid[x + 1][y];
            } else {
                int currX = x + 1;
                if (currX >= GameSettings.boardX) {
                    currX -= GameSettings.boardX;
                }
                counter += grid[currX][y];
            }

            // lewo gora
            if (type == 5 || type == 6 || type == 8 || type == 9) {
                counter += grid[x - 1][y + 1];
            } else {
                int currX = x - 1;
                int currY = y + 1;
                if (currX < 0) {
                    currX += GameSettings.boardX;
                }
                if (currY >= GameSettings.boardY) {
                    currY -= GameSettings.boardY;
                }
                counter += grid[currX][currY];
            }

            // gora
            if (type > 3) {
                counter += grid[x][y + 1];
            } else {
                int currY = y + 1;
                if (currY >= GameSettings.boardY) {
                    currY -= GameSettings.boardY;
                }
                counter += grid[x][currY];
            }

            // gora prawo
            if (type == 4 || type == 5 || type == 7 || type == 8) {
                counter += grid[x + 1][y + 1];
            }
            else {
                int currX = x + 1;
                int currY = y + 1;
                if (currX >= GameSettings.boardX) {
                    currX -= GameSettings.boardX;
                }
                if (currY >= GameSettings.boardY) {
                    currY -= GameSettings.boardY;
                }
                counter += grid[currX][currY];
            }
        }
        return counter;
    }

    public static int checkCellType(int x, int y) {
        boolean up = y + 1 >= GameSettings.boardY;
        boolean down = y - 1 < 0;
        boolean right = x + 1 >= GameSettings.boardX;
        boolean left = x - 1 < 0;

        if (up) {
            if (left) {
                return 1;
            }
            if (right) {
                return 3;
            }
            return 2;
        }
        if (down) {
            if (left) {
                return 7;
            }
            if (right) {
                return 9;
            }
            return 8;
        }
        if (left) {
            return 4;
        }
        if (right) {
            return 6;
        }
        return 5;
    }
}