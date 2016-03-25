package com.codefunia.sudoku_solver.immutable;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;

import com.codefunia.sudoku_solver.Sudoku;

public class SudokuImpl implements Sudoku {
    
    private char[][] grid;
    
    private Set<Character>[][] candidateSets;
    
    // This is an array of length 10, where candidateQueue[i] contains all the entries that can take i possible values
    private Set<Integer>[] candidateQueue;
    
    private int currentMinIdx;
    
    public static void main(String[] args) {
	Set<Character> set = new HashSet<Character>();
	set.addAll(Arrays.asList('1', '2', '3', '4', '5', '6', '7', '8', '9'));
	System.out.println(set.contains('1'));
    }

    // Construct an empty Sudoku
    private SudokuImpl() {
	
	grid = new char[9][9];
	candidateSets = (Set<Character>[][]) new Set[9][9];
	for (int i = 0; i < 9; i++) {
	    for (int j = 0; j < 9; j++) {
		grid[i][j] = ' ';
		candidateSets[i][j] = new HashSet<Character>();
		candidateSets[i][j].addAll(Arrays.asList('1', '2', '3', '4', '5', '6', '7', '8', '9'));
	    }
	}
	
	candidateQueue = (Set<Integer>[]) new Set[10];
	for (int i = 0; i < 10; i++) {
	    candidateQueue[i] = new HashSet<Integer>();
	}
	for (int i = 0; i < 81; i++) {
	    candidateQueue[9].add(i);
	}
	
	currentMinIdx = 9;
    }
    
    private SudokuImpl(char[][] grid, Set<Character>[][] candidateSets, Set<Integer>[] candidateQueue, int currentMinIdx) {
	this.grid = grid;
	this.candidateSets = candidateSets;
	this.candidateQueue = candidateQueue;
	this.currentMinIdx = currentMinIdx;
    }
    
    @Override
    public Entry nextCandidate() {
	Object[] codes = candidateQueue[currentMinIdx].toArray();
	
	int code = (int) codes[new Random().nextInt(codes.length)];
	int rowNum = Entry.covertToRowNum(code);
	int colNum = Entry.convertToColNum(code);
	
	return new Entry(rowNum, colNum, candidateSets[rowNum][colNum]);
    }
    
    @Override
    public boolean isCompleted() {
	return currentMinIdx == -1;
    }
    
    public static Sudoku newInstance(char[][] grid) {
	
	Sudoku sudoku = new SudokuImpl();
	
	for (int i = 0; i < 9; i++) {
	    for (int j = 0; j < 9; j++) {
		if (grid[i][j] >= '1' && grid[i][j] <= '9') {
		    sudoku = sudoku.insert(i, j, (char) grid[i][j]);
		}
	    }
	}

	return sudoku;
    }

    @Override
    public Sudoku insert(int rowNum, int colNum, int value) {
	
	final char insertValue = (char) ('0' + value);
	return insert(rowNum, colNum, insertValue);
    }
    
    public Sudoku insert(int rowNum, int colNum, char insertValue) {

	if (candidateSets[rowNum][colNum].contains(insertValue)) {

	    // Make a copy of the object status
	    char[][] grid = new char[9][9];
	    Set<Character>[][] candidateSets = (Set<Character>[][]) new Set[9][9];	    
	    Set<Integer>[] candidateQueue = (Set<Integer>[]) new Set[10];
	    int currentMinIdx = -1;
	    
	    for (int i = 0; i < 9; i++) {
		for (int j = 0; j < 9; j++) {
		    grid[i][j] = this.grid[i][j];
		    candidateSets[i][j] = new HashSet<Character>(this.candidateSets[i][j]);
		}
	    }
	    
	    for (int i = 0; i < 10; i++) {
		candidateQueue[i] = new HashSet<Integer>(this.candidateQueue[i]);
	    }
	    
	    // Insert the corresponding entry
	    final int numOfPossibleVals = candidateSets[rowNum][colNum].size();
	    final int entryCode = Entry.convertToCode(rowNum, colNum);
	    
	    grid[rowNum][colNum] = insertValue;
	    candidateSets[rowNum][colNum].clear();
	    candidateQueue[numOfPossibleVals].remove(entryCode);
	    
	    // Update neighbours in the same row
	    for (int k = 0; k < 9; k++) {
		final int code = Entry.convertToCode(rowNum, k);

		if (candidateSets[rowNum][k].contains(insertValue)) {
		    final int candidateSetSize = candidateSets[rowNum][k].size();
		    candidateSets[rowNum][k].remove(insertValue);
		    candidateQueue[candidateSetSize].remove(code);
		    candidateQueue[candidateSetSize - 1].add(code);
		}
	    }
	    
	    // Update neighbours in the same column
	    for (int k = 0; k < 9; k++) {
		final int code = Entry.convertToCode(k, colNum);
		
		if (candidateSets[k][colNum].contains(insertValue)) {
		    final int candidateSetSize = candidateSets[k][colNum].size();
		    candidateSets[k][colNum].remove(insertValue);
		    candidateQueue[candidateSetSize].remove(code);
		    candidateQueue[candidateSetSize - 1].add(code);
		}
	    }
	    
	    // Update neighbours in the same box
	    for (int i = 0; i < 3; i++) {
		for (int j = 0; j < 3; j++) {
		    final int neighborRowNum = (rowNum / 3) * 3 + i;
		    final int neighborColNum = (colNum / 3) * 3 + j;
		    final int code = Entry.convertToCode(neighborRowNum, neighborColNum);

		    if (candidateSets[neighborRowNum][neighborColNum].contains(insertValue)) {
			final int candidateSetSize = candidateSets[neighborRowNum][neighborColNum].size();
			candidateSets[neighborRowNum][neighborColNum].remove(insertValue);
			candidateQueue[candidateSetSize].remove(code);
			candidateQueue[candidateSetSize - 1].add(code);
		    }
		}
	    }
	    
	    // Update currentMinIdx
	    for (int k = 0; k < 10; k++) {
		if (!candidateQueue[k].isEmpty()) {
		    currentMinIdx = k;
		    break;
		}
	    }
	    
	    return new SudokuImpl(grid, candidateSets, candidateQueue, currentMinIdx);

	} else {
	    throw new IllegalStateException("");
	}
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
    
    @Override
    public boolean equals(Object obj) {
	if (!(obj instanceof SudokuImpl))
	    return false;
	    
	SudokuImpl that = (SudokuImpl) obj;
	
	for (int i = 0; i < 9; i++) {
	    if (!Arrays.equals(this.grid[i], that.grid[i])) {
		return false;
	    }
	}
	
	for (int i = 0; i < 9; i++) {
	    for (int j = 0; j < 9; j++) {
		if (!this.candidateSets[i][j].equals(that.candidateSets[i][j])) {
		    return false;
		};
	    }
	}
	
	for (int i = 0; i < 10; i++) {
	    if (!this.candidateQueue[i].equals(that.candidateQueue[i])) {
		return false;
	    }
	}

	return this.currentMinIdx == that.currentMinIdx;
    }

}
