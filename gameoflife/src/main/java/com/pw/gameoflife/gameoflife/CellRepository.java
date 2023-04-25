package com.pw.gameoflife.gameoflife;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

@Repository
public class CellRepository {

    public static List<Cell> cells = new ArrayList<>();

    public static List<Cell> get()
    {
        return CellRepository.cells;
    }

    public static void put(List<Cell> cells)
    {
        CellRepository.cells = cells;
    }
}
