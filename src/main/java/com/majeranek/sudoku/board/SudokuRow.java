package com.majeranek.sudoku.board;

import java.util.ArrayList;
import java.util.List;

public class SudokuRow {

    private final List<SudokuElement> sudokuRow;

    public SudokuRow() {
        List<SudokuElement> sudokuRow = new ArrayList<>();
        for (int i = 0; i <9 ; i++) {
            sudokuRow.add(new SudokuElement());
        }
        this.sudokuRow = sudokuRow;
    }

    public List<SudokuElement> getSudokuRow() {
        return sudokuRow;
    }
}

