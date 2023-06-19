package com.pw.gameoflife.gameoflife.services;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.pw.gameoflife.gameoflife.repositories.CellRepository;
import com.pw.gameoflife.gameoflife.settings.GameSettings;

public class SettingsReader {
    public static void readFromFile(String filename) 
    {
        File data = new File(filename);
        String currentLine;
        try (Scanner scanner = new Scanner(data)) 
        {
            //board size
            currentLine = scanner.nextLine();
            String[] newPoint = currentLine.split(",");
            if (newPoint.length != 2) {
                throw new IllegalArgumentException("Found a mistake in data file!");
            }

            GameSettings.boardX = Integer.parseInt(newPoint[0]);
            GameSettings.boardY = Integer.parseInt(newPoint[1]);

            System.out.println("Board size: " + GameSettings.boardX + " x " + GameSettings.boardY);

            //neighbourhood
            currentLine = scanner.nextLine();
            GameSettings.neighbourhood = Integer.parseInt(currentLine);

            String neighbourhoodType = GameSettings.neighbourhood == 0 ? "Moore" : "von Neumann";
            System.out.println("Neighbourhood: " + neighbourhoodType);
            
            //number of iterations
            currentLine = scanner.nextLine();
            GameSettings.numberOfIterations = Integer.parseInt(currentLine);
            CellRepository.cells = new ArrayList[GameSettings.numberOfIterations + 1];

            System.out.println("Number of iterations: " + GameSettings.numberOfIterations);

            //number of threads
            currentLine = scanner.nextLine();
            GameSettings.threads = Integer.parseInt(currentLine);

            System.out.println("Number of threads: " + GameSettings.threads);

            //border type
            currentLine = scanner.nextLine();
            GameSettings.borderType = Integer.parseInt(currentLine);

            String borderType = GameSettings.borderType == 0 ? "Default" : "Wrapped";
            System.out.println("Border type: " + neighbourhoodType);

            scanner.close();

            GameSettings.settingsForClient = new ArrayList<Integer>();
            GameSettings.settingsForClient.add(GameSettings.boardX);
            GameSettings.settingsForClient.add(GameSettings.boardY);
            GameSettings.settingsForClient.add(GameSettings.numberOfIterations);
        }
        catch (IOException e) 
        {
            System.out.println("There is a problem with data file");
        }

    }
}
