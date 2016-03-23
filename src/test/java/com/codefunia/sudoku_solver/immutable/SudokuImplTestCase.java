package com.codefunia.sudoku_solver.immutable;

import static org.junit.Assert.*;

import java.util.Arrays;

import org.junit.Test;

import com.codefunia.sudoku_solver.Sudoku;
import com.codefunia.sudoku_solver.SudokuTestObject;

public class SudokuImplTestCase {
    
    @Test
    public void getPrintedStringShouldBeEqual() {
	Sudoku sudoku1 = SudokuImpl.newInstance(SudokuTestObject.validGrid1);
	assertEquals( "/---+---+---\\\n" 
		    + "|435|269|781|\n"
		    + "|682|571|493|\n"
		    + "|197|834|562|\n"
		    + "+---+---+---+\n"
		    + "|826|195|347|\n"
		    + "|374|682|915|\n"
		    + "|951|743|628|\n"
		    + "+---+---+---+\n"
		    + "|519|326|874|\n"
		    + "|248|957|136|\n"
		    + "|763|418|259|\n"
		    + "\\---+---+---/\n", sudoku1.getPrintedString());
    }
    
    @Test
    public void getGridCopyShouldGiveNewCopy() {
	char[][] grid1 = SudokuTestObject.validGrid1;
	Sudoku sudoku = SudokuImpl.newInstance(grid1);
	char[][] grid2 = sudoku.getGridCopy();
	
	assertTrue(grid1 != grid2);
	
	for (int i = 0; i < 9; i++) {
	    assertTrue(Arrays.equals(grid1[i], grid2[i]));
	}
    }

}
