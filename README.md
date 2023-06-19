# Game-Of-Life-paralleled-distributed

This repository contains paralelled and distributed Conway's Game of Life.

The project contains two applications: for server (for computing all iterations) and client (for fetching list of cells of each iteration and displaying the whole board).

The server application is using threads for computing each iteration of the game. It also requires two txt files for reading settings:

1) settings.txt, the file that contains the following settings (each in separate line):
-the board's size in notation: x,y;
-type of neighbourhood- 0 for Moore, 1 for von Neumann;
-number of iterations;
-number of threads used for computing;
-type of board's borders: 0 for default (closed), 1 for wrapped.
2) startCells.txt, the file that contains all alive cells for the first iteration in notation: x,y (each cell in separate line).

The client application uses HTTP requests for fetching settings and alive cells and swing components for displaying.
