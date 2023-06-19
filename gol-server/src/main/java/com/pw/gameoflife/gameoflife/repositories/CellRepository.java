package com.pw.gameoflife.gameoflife.repositories;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.pw.gameoflife.gameoflife.simulation.Cell;

@Repository
public class CellRepository {

    public static List<Cell>[] cells;

    public static void put(List<Cell> cells, int iteration)
    {
        CellRepository.cells[iteration] = cells;
    }
}
