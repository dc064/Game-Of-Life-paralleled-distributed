package com.pw.gameoflife.gameoflife;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class CellFileReader {
    public static void readFromFile(String filename) throws IOException
    {
        List<Cell> newCells = new ArrayList<>();

        File data = new File(filename);
        String currentLine;
        try (Scanner scanner = new Scanner(data)) {

            while (scanner.hasNextLine()) {
                currentLine = scanner.nextLine();

                String[] newPoint = currentLine.split(",");
                if (newPoint.length != 2) {
                    throw new IllegalArgumentException("Found a mistake in data file!");
                }

                int newX = Integer.parseInt(newPoint[0]);
                int newY = Integer.parseInt(newPoint[1]);

                newCells.add(new Cell(newX, newY));
            }
            scanner.close();

        } catch (IOException e) {
            System.out.println("There is a problem with data file");
        }

        CellRepository.put(newCells);
    }
}
