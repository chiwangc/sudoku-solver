package com.codefunia.sudoku_solver;

import java.util.HashSet;
import java.util.Set;

public interface Sudoku {

    public Sudoku insert(int rowNum, int colNum, int value);
    
    public Sudoku insert(int rowNum, int colNum, char value);

    public Sudoku delete(int rowNum, int colNum);
    
    public Entry nextCandidate();
    
    public boolean isCompleted();

    public String getPrintedString();

    public char[][] getGridCopy();
    
    class Entry {
	
	private int rowNum;
	private int colNum;
	private Set<Character> candidates;
	
	public Entry(int rowNum, int colNum, Set<Character> candidateSets) {
	    this.rowNum = rowNum;
	    this.colNum = colNum;
	    this.candidates = new HashSet<Character>(candidateSets);
	}
	
	public int getRowNum() {
	    return rowNum;
	}
	
	public int getColNum() {
	    return colNum;
	}
		
	public int getCode() {
	    return convertToCode(rowNum, colNum);
	}
	
	public Set<Character> getCandidates() {
	    return candidates;
	}
	
	public static int convertToCode(int rowNum, int colNum) {
	    return rowNum * 9 + colNum;
	}
	
	public static int covertToRowNum(int code) {
	    return code / 9;
	}
	
	public static int convertToColNum(int code) {
	    return code % 9;
	}
	
    }

}