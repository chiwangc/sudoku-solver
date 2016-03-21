package com.codefunia.sudoku_solver;

public final class SudokuUtil {

    private SudokuUtil() {}
    
    public static char[][] toGrid(String... args) {
	if (args == null || args.length != 9) {
	    throw new IllegalArgumentException("The input does not represent a Sudoku of size 9x9");
	}
	
	char[][] grid = new char[9][9];
	
	for (int i = 0; i < 9; i++) {
	    if (args[i] == null || args[i].length() != 9) {
		throw new IllegalArgumentException("The input does not represent a Sudoku of size 9x9");
	    }
	    
	    for (int j = 0; j < 9; j++) {
		char entry = args[i].charAt(j);
		if (entry < '1' || entry > '9') {
		    grid[i][j] = ' ';
		} else {
		    grid[i][j] = entry;
		}
	    }
	}
	
	return grid;
    }

}
