package com.majeranek.sudoku;

import com.majeranek.sudoku.board.SudokuBoard;
import java.util.Scanner;

public class SudokuGame {
    public static void main(String[] args) {

        boolean gameFinished = false;
        while(!gameFinished) {
            SudokuGame theGame = new SudokuGame();
            gameFinished = theGame.sudokuProcess();
        }
    }

    private boolean sudokuProcess() {
        Scanner scanner = new Scanner(System.in);
        SudokuService service = new SudokuService();
        SudokuBoard sudokuBoard = new SudokuBoard();

        fulfilBoard(sudokuBoard, scanner);
        resolveSudoku(service, sudokuBoard);
        return ifNextGame(scanner);
    }

    private void fulfilBoard(SudokuBoard sudokuBoard, Scanner scanner){
        sudokuBoard.showBoard();

        System.out.println("If you want to fulfil board you type in \"123\" what mean value 3 will be insert in 1 row and 2 column");
        System.out.println("If you want to finish fulfill board, type in \"sudoku\"");

        boolean endFilling = false;
        while(!endFilling) {
            String valueToInsert = scanner.nextLine();
            if(valueToInsert.equals("sudoku")){
                break;
            }
            char[] chars = valueToInsert.toCharArray();
            if(chars.length==3) {
                int x = (int) chars[0] - 48;
                int y = (int) chars[1] - 48;
                int value = (int) chars[2] - 48;
                if(x>0 && x<10 && y>0 && y<10 && value>0 && value<10 ) {
                    sudokuBoard.setValueOfBoardElemnt(x - 1, y - 1, value);
                    sudokuBoard.showBoard();
                } else {
                    System.out.println("row and column and value must be value between 1 and 9");
                }
            } else {
                System.out.println("you can type in only 3 numbers without any additional letter and signs");
            }
        }
    }

    private void resolveSudoku(SudokuService service, SudokuBoard sudokuBoard) {
        int howManyElemtntsIsNotEmpty = 0;
        int amountOfIteration= 0;
        while (howManyElemtntsIsNotEmpty != 81) {
            amountOfIteration++;
            howManyElemtntsIsNotEmpty = 0;
            service.removingPossibleValues(sudokuBoard);
            Integer amountOfInsertedValue = service.insertLastPossibleValue(sudokuBoard);
            howManyElemtntsIsNotEmpty = getHowManyElementIsNotEmpty(sudokuBoard, howManyElemtntsIsNotEmpty);

            if(service.makeError(sudokuBoard)){
                break;
            }
            if(amountOfInsertedValue.equals(0)){
                System.out.println("This sudoku is impossible to solve without guessing value");
                break;
            }
        }
        System.out.println("amount of iteration: " + amountOfIteration);
        sudokuBoard.showBoard();
    }



    private int getHowManyElementIsNotEmpty(SudokuBoard sudokuBoard, int licznik) {
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if (sudokuBoard.getValueOfBoard(i,j) != 0) {
                    licznik++;
                }
            }
        }
        return licznik;
    }

    private boolean ifNextGame(Scanner scanner) {
        System.out.println("Do you want to resolve next sudoku y/n");
        String nextSudoku = scanner.nextLine();
        if(nextSudoku.equals("y")) return false;
        else return true;
    }
}
