package com.pw.gameoflife.gameoflife;

import java.util.ArrayList;
import java.util.List;

public class CellUpdater {
    
    static void nextStep(List<Cell> cells, int M, int N)
    {
        List<Cell> toReturn = new ArrayList<>();

        int[][] future = new int[M][N];
 
        for (Cell c : cells) {
            future[c.x][c.y]++;
        }
        // Loop through every cell
        for (int l = 0; l < M; l++)
        {
            for (int m = 0; m < N; m++)
            {
                // finding no Of Neighbours that are alive
                int aliveNeighbours = 0;
                for (int i = -1; i <= 1; i++)
                    for (int j = -1; j <= 1; j++)
                      if ((l+i>=0 && l+i<M) && (m+j>=0 && m+j<N))
                        aliveNeighbours += future[l + i][m + j];
 
                // The cell needs to be subtracted from
                // its neighbours as it was counted before
                aliveNeighbours -= future[l][m];
 
                // Implementing the Rules of Life
 
                // Cell is lonely and dies
                if ((future[l][m] == 1) && (aliveNeighbours < 2))
                {
                    future[l][m] = 0;
                }
 
                // Cell dies due to over population
                else if ((future[l][m] == 1) && (aliveNeighbours > 3))
                {
                    future[l][m] = 0;
                }
                // A new cell is born
                else if ((future[l][m] == 0) && (aliveNeighbours == 3))
                {
                    future[l][m] = 1;
                    toReturn.add(new Cell(l, m));
                }
 
                // Remains the same
                else
                {
                    if(future[l][m] > 0)
                    {
                        toReturn.add(new Cell(l, m));
                    }
                }
            }
        }

        //return toReturn;
        CellRepository.put(toReturn);
    }

    private void countNeighbours()
    {
        if(SettingsInitializer.neighbourhood == 0)
        {
            neumannNeighbourhood();
        }
        else if (SettingsInitializer.neighbourhood == 1)
        {
            mooreNeighbourhood();
        }
    }

    private void neumannNeighbourhood()
    {

    }

    private void mooreNeighbourhood()
    {

    }
}