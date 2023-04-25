package com.pw.gameoflife.gameoflife;

import java.io.IOException;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class GameoflifeApplication {

	public static void main(String[] args) throws InterruptedException, IOException {
		SpringApplication.run(GameoflifeApplication.class, args);

		CellFileReader.readFromFile("startCells.txt");
		Thread.sleep(10000);
		
		while (true) {
			Thread.sleep(5000);
			CellUpdater.nextStep(CellRepository.cells, 100, 100);
		}
	}

}
