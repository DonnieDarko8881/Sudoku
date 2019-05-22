package com.majeranek.sudoku.board;

import java.util.ArrayList;
import java.util.List;

public class SudokuElement {
    public static int EMPTY = -1;
    private int value;
    public List<Integer> possibleValue;

    public SudokuElement() {
        this.possibleValue = new ArrayList<>();
        this.possibleValue= setPosibleValue(possibleValue);
    }

    private List<Integer> setPosibleValue(List<Integer> posibleValue) {
        for (int i = 0; i < 9; i++) {
            posibleValue.add(i + 1);
        }
        return this.possibleValue = posibleValue;
    }

    public List<Integer> removePossibleValue(int valueToRemove) {
        if(possibleValue.contains(valueToRemove)) {
            possibleValue.remove(possibleValue.indexOf(valueToRemove));
        }
        return possibleValue;
    }


    @Override
    public String toString() {
        return Integer.toString(value);
    }

    public int getValue() {
        return this.value;
    }

    public List<Integer> getPossibleValue() {
        return possibleValue;
    }

    public void setValue(int value) {
        this.value = value;
    }
}
