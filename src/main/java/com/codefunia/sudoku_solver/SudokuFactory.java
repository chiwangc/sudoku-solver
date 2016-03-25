package com.codefunia.sudoku_solver;

public class SudokuFactory {
    
    public static Sudoku createSudoku(char[][] grid) {
	return createSudoku(grid, "IMMUTABLE");
    }
    
    public static Sudoku createSudoku(char[][] grid, String type) {
	if (type.equals("IMMUTABLE")) {
	    return com.codefunia.sudoku_solver.immutable.SudokuImpl.newInstance(grid);
	} else {
	    return null;
	}
    }
}
