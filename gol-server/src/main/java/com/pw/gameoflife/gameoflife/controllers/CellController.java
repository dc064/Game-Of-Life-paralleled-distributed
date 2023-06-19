package com.pw.gameoflife.gameoflife.controllers;

import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pw.gameoflife.gameoflife.repositories.CellRepository;
import com.pw.gameoflife.gameoflife.settings.GameSettings;
import com.pw.gameoflife.gameoflife.simulation.Cell;

@RestController
public class CellController {

    @GetMapping("/cells/{id}")
    public List<Cell> getAll(@PathVariable("id") int id) {
        return CellRepository.cells[id];
    }

    @GetMapping("/settings")
    public List<Integer> getSettings() {
        return GameSettings.settingsForClient;
    }
}
