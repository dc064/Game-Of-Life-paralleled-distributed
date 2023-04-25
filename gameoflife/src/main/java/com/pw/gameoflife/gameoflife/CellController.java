package com.pw.gameoflife.gameoflife;

import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/cells")
public class CellController {

    @GetMapping("/c")
    public List<Cell> getAll() {
        return CellRepository.get();
    }

    @CrossOrigin
    @RequestMapping(path = "/abcde")
    public String retrieve() {
        return null;
    }
}
