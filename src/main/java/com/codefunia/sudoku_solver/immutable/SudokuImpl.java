package com.codefunia.sudoku_solver.immutable;

import com.codefunia.sudoku_solver.Sudoku;

public class SudokuImpl implements Sudoku {
    
    private char[][] grid;
    
    private void init() {
	
	grid = new char[9][9];
    }
               
    private SudokuImpl(char[][] grid) {
	
	init();
	
	for (int i = 0; i < 9; i++) {
	    for (int j = 0; j < 9; j++) {
		insert(i, j, grid[i][j]);
	    }
	}
    }
    
    public static Sudoku newInstance(char[][] grid) {
	
	Sudoku sudoku = new SudokuImpl(grid);
	return sudoku;
    }

    @Override
    public Sudoku insert(int rowNum, int colNum, int value) {
	// TODO Auto-generated method stub
	return null;
    }

    @Override
    public Sudoku delete(int rowNum, int colNum) {
	// TODO Auto-generated method stub
	return null;
    }

    @Override
    public String getPrintedString() {
	
	StringBuilder sb = new StringBuilder();
	sb.append("/---+---+---\\\n");
	sb.append(String.format("|%c%c%c|%c%c%c|%c%c%c|\n", grid[0][0], grid[0][1], grid[0][2], grid[0][3], grid[0][4], grid[0][5], grid[0][6], grid[0][7], grid[0][8]));
	sb.append(String.format("|%c%c%c|%c%c%c|%c%c%c|\n", grid[1][0], grid[1][1], grid[1][2], grid[1][3], grid[1][4], grid[1][5], grid[1][6], grid[1][7], grid[1][8]));
	sb.append(String.format("|%c%c%c|%c%c%c|%c%c%c|\n", grid[2][0], grid[2][1], grid[2][2], grid[2][3], grid[2][4], grid[2][5], grid[2][6], grid[2][7], grid[2][8]));
	sb.append("+---+---+---+\n");
	sb.append(String.format("|%c%c%c|%c%c%c|%c%c%c|\n", grid[3][0], grid[3][1], grid[3][2], grid[3][3], grid[3][4], grid[3][5], grid[3][6], grid[3][7], grid[3][8]));
	sb.append(String.format("|%c%c%c|%c%c%c|%c%c%c|\n", grid[4][0], grid[4][1], grid[4][2], grid[4][3], grid[4][4], grid[4][5], grid[4][6], grid[4][7], grid[4][8]));
	sb.append(String.format("|%c%c%c|%c%c%c|%c%c%c|\n", grid[5][0], grid[5][1], grid[5][2], grid[5][3], grid[5][4], grid[5][5], grid[5][6], grid[5][7], grid[5][8]));
	sb.append("+---+---+---+\n");
	sb.append(String.format("|%c%c%c|%c%c%c|%c%c%c|\n", grid[6][0], grid[6][1], grid[6][2], grid[6][3], grid[6][4], grid[6][5], grid[6][6], grid[6][7], grid[6][8]));
	sb.append(String.format("|%c%c%c|%c%c%c|%c%c%c|\n", grid[7][0], grid[7][1], grid[7][2], grid[7][3], grid[7][4], grid[7][5], grid[7][6], grid[7][7], grid[7][8]));
	sb.append(String.format("|%c%c%c|%c%c%c|%c%c%c|\n", grid[8][0], grid[8][1], grid[8][2], grid[8][3], grid[8][4], grid[8][5], grid[8][6], grid[8][7], grid[8][8]));
	sb.append("\\---+---+---/\n");
	
	return sb.toString();
    }

    @Override
    public char[][] getGridCopy() {
	
	char[][] gridCopy = new char[9][9];
	
	for (int i = 0; i < 9; i++) {
	    for (int j = 0; j < 9; j++) {
		gridCopy[i][j] = grid[i][j];
	    }
	}

	return gridCopy;
    }

}
