package com.majeranek.sudoku;

import com.majeranek.sudoku.board.SudokuBoard;

public class SudokuService {
    public void removingPossibleValues(SudokuBoard sudokuBoard) {
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                int valueToRemove = sudokuBoard.getValueOfBoard(i, j);
                if (sudokuBoard.getElementOfBoard(i, j).getPossibleValue().contains(valueToRemove)) {
                    for (int k = 0; k < 9; k++) {
                        sudokuBoard.getElementOfBoard(i, k).removePossibleValue(valueToRemove);
                        sudokuBoard.getElementOfBoard(k, j).removePossibleValue(valueToRemove);
                        sudokuBoard.getElementOfBoard((i / 3) * 3 + k / 3, (j / 3) * 3 + k % 3).removePossibleValue(valueToRemove);
                    }
                }
            }
        }
    }

    public int insertLastPossibleValue(SudokuBoard sudokuBoard) {
        int countInsertedValue = 0;
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if (sudokuBoard.getElementOfBoard(i, j).getPossibleValue().size() == 1 && sudokuBoard.getValueOfBoard(i, j) == 0
                ) {
                    sudokuBoard.setValueOfBoardElemnt(i, j, sudokuBoard.getElementOfBoard(i, j).getPossibleValue().get(0));
                    countInsertedValue++;
                }
            }
        }
        return countInsertedValue;
    }

    public boolean makeError(SudokuBoard sudokuBoard) {
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                for (int k = 0; k < 9; k++) {
                    if(checkErrorInRow(sudokuBoard, i, j, k)){ return true;}
                    if(checkErrorInColumn(sudokuBoard, i, j, k)){return true;}
                    if(checkErrorInSquare(sudokuBoard, i, j, k)){return true;}
                }
            }
        }
        return false;
    }

    private boolean checkErrorInSquare(SudokuBoard sudokuBoard, int i, int j, int k) {
        if (sudokuBoard.getValueOfBoard(i, j) == sudokuBoard.getValueOfBoard((i / 3) * 3 + k / 3,
                (j / 3) * 3 + k % 3) &&
                j != ((j / 3) * 3 + k % 3) && i!=((i / 3) * 3 + k / 3)
                && sudokuBoard.getValueOfBoard(i, j) != 0) {
            System.out.println(sudokuBoard.getValueOfBoard(i, j) + " " +  sudokuBoard.getValueOfBoard((i / 3) * 3 + k / 3,
                    (j / 3) * 3 + k % 3) );
            System.out.println("Number repeats in Squrare: "+ sudokuBoard.getValueOfBoard(i, j));
            return true;
        } else {
            return false;
        }
    }

    private boolean checkErrorInColumn(SudokuBoard sudokuBoard, int i, int j, int k) {
        if (sudokuBoard.getValueOfBoard(i, j) == sudokuBoard.getValueOfBoard(k, j) && i != k
                && sudokuBoard.getValueOfBoard(i, j) != 0) {

            System.out.println("Number repeats in Column: " + sudokuBoard.getValueOfBoard(i, j));
            return true;
        } else {
            return false;
        }
    }

    private boolean checkErrorInRow(SudokuBoard sudokuBoard, int i, int j, int k) {
        if (sudokuBoard.getValueOfBoard(i, j) == sudokuBoard.getValueOfBoard(i, k) && j != k
                && sudokuBoard.getValueOfBoard(i, j) != 0) {

            System.out.println("Number repeats in Row: " + sudokuBoard.getValueOfBoard(i, j));
            return true;
        } else {
            return false;
        }
    }
}
