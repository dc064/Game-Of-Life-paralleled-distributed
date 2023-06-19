package com.pw.gameoflife.gameoflife;

import java.io.IOException;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.pw.gameoflife.gameoflife.repositories.CellRepository;
import com.pw.gameoflife.gameoflife.services.CellFileReader;
import com.pw.gameoflife.gameoflife.services.SettingsReader;
import com.pw.gameoflife.gameoflife.settings.ChangeableGameSettings;
import com.pw.gameoflife.gameoflife.settings.ConstantGameSettings;
import com.pw.gameoflife.gameoflife.simulation.CellUpdater;

@SpringBootApplication
public class GameoflifeApplication {

	public static void main(String[] args) throws Exception {
		SpringApplication.run(GameoflifeApplication.class, args);

		SettingsReader.readFromFile("settings.txt");
		CellFileReader.readFromFile("startCells.txt");

		int iteration = 0;
		Thread.sleep(1000);
		
		long start = System.currentTimeMillis();
		while (iteration < ConstantGameSettings.numberOfIterations) {
			CellUpdater.nextStep(CellRepository.cells[iteration++], ConstantGameSettings.boardX, ConstantGameSettings.boardY, iteration);
		}
		long finish = System.currentTimeMillis();
		long timeElapsed = finish - start;

		System.out.println("For " + ConstantGameSettings.numberOfIterations + " iterations and " + ConstantGameSettings.threads +
		" threads, measured time: " + timeElapsed);
	}

}
