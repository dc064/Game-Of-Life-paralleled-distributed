package com.pw.gameoflife.gameoflife.services;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import com.pw.gameoflife.gameoflife.repositories.CellRepository;
import com.pw.gameoflife.gameoflife.settings.ChangeableGameSettings;
import com.pw.gameoflife.gameoflife.settings.ConstantGameSettings;

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

            ConstantGameSettings.boardX = Integer.parseInt(newPoint[0]);
            ConstantGameSettings.boardY = Integer.parseInt(newPoint[1]);

            //neighbourhood
            currentLine = scanner.nextLine();
            ChangeableGameSettings.neighbourhood = Integer.parseInt(currentLine);
            
             //number of iterations
             currentLine = scanner.nextLine();
             ConstantGameSettings.numberOfIterations = Integer.parseInt(currentLine);
             CellRepository.cells = new ArrayList[ConstantGameSettings.numberOfIterations + 1];

             //number of threads
             currentLine = scanner.nextLine();
             ConstantGameSettings.threads = Integer.parseInt(currentLine);

             //border type
             currentLine = scanner.nextLine();
             ChangeableGameSettings.borderType = Integer.parseInt(currentLine);

            scanner.close();
        }
        catch (IOException e) 
        {
            System.out.println("There is a problem with data file");
        }

    }
}
