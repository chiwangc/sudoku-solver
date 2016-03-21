package com.codefunia.sudoku_solver;

public interface Sudoku {

    public Sudoku insert(int rowNum, int colNum, int value);

    public Sudoku delete(int rowNum, int colNum);

    public String getPrintedString();

    public char[][] getGridCopy();

}
