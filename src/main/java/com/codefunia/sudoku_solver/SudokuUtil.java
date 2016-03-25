package com.codefunia.sudoku_solver;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import com.codefunia.sudoku_solver.Sudoku.Entry;

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
    
    public static Set<Sudoku> solve(Sudoku sudoku) {
	
	Set<Sudoku> solutions = new HashSet<Sudoku>();
	List<Sudoku> searchList = new LinkedList<Sudoku>();
	searchList.add(sudoku);
	
	while (!searchList.isEmpty()) {
	    Sudoku currentSudoku = searchList.remove(0);
	    Entry candidateEntry = currentSudoku.nextCandidate();
	    if (candidateEntry.getCandidates().size() > 0) {	// If the size is zero then this sudoku has no possible valid filling
		for (char possibleValue : candidateEntry.getCandidates()) {
		    Sudoku newSudoku = currentSudoku.insert(candidateEntry.getRowNum(), candidateEntry.getColNum(), possibleValue);
		    if (newSudoku.isCompleted()) {
			solutions.add(newSudoku);
		    } else {
			searchList.add(newSudoku);
		    }
		}
	    }
	}
	
	return solutions;
    }

}
