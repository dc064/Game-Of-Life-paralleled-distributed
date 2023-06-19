package com.pw.gameoflife.gameoflife.simulation;

public class SingleCellCalc implements Runnable 
    {
        int x, y, cellState, border, neighbourhood;
        int [][] grid;

        public SingleCellCalc(int x, int y, int cellState, int[][] grid, int border, int neighbourhood)
        {
            this.x = x;
            this.y = y;
            this.cellState = cellState;
            this.grid = grid;
            this.border = border;
            this.neighbourhood = neighbourhood;
        }

        @Override
        public void run() 
        {
            int aliveNeighbours = CellUpdater.countNeighbours(grid, x, y, neighbourhood, border);
            
            if (cellState == 1)
            {
                if (aliveNeighbours < 2 || aliveNeighbours > 3) 
                {
                    //grid[x][y] = 0;
                } 
                else 
                {
                    //status zostaje
                
                    synchronized(CellUpdater.toReturn)
                    {
                        CellUpdater.toReturn.add(new Cell(x, y));
                    }
                    //CellUpdater.toReturnAtom.add(new Cell(l, m));
                    //toReturn.add(new Cell(l, m));
                }
            }
            else if (cellState == 0) 
            {
                if (aliveNeighbours == 3) 
                {
                    synchronized(CellUpdater.toReturn)
                    {
                        CellUpdater.toReturn.add(new Cell(x, y));
                    }
                }
            }  
        }
    }
