package com.majeranek.sudoku.board;

import java.util.ArrayList;
import java.util.List;

public class SudokuBoard extends Prototype{

    private final List<List<SudokuElement>> sudokuBoard;

    public SudokuBoard() {
        List<List<SudokuElement>> sudokuBoard = new ArrayList<>();
        for (int i = 0; i < 9; i++) {
            sudokuBoard.add(new SudokuRow().getSudokuRow());
        }
        this.sudokuBoard = sudokuBoard;
    }

    public List<List<SudokuElement>> getSudokuBoard() {
        return sudokuBoard;
    }

    public void setValueOfBoardElemnt(int x, int y, int value){
        sudokuBoard.get(x).get(y).setValue(value);
    }

    public SudokuElement getElementOfBoard(int x, int y){
        return sudokuBoard.get(x).get(y);
    }

    public int getValueOfBoard(int x, int y){
        return sudokuBoard.get(x).get(y).getValue();
    }

    public void showBoard() {
        for (int i = 0; i < 9; i++) {
            if (i == 0 || i == 3 || i == 6) {
                System.out.println("-------------------------");
            }
            for (int j = 0; j < 9; j++) {
                if (j == 0) {
                    System.out.print("-");
                } else if (j == 3 || j == 6) {
                    System.out.print("|-");
                }
                System.out.print("|" + getValueOfBoard(i,j));
                if (j == 8) {
                    System.out.print("|-\n");
                }
            }
        }
        System.out.println("-------------------------");
    }
}
