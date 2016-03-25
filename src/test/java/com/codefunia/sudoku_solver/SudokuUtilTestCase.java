package com.codefunia.sudoku_solver;

import static com.codefunia.sudoku_solver.SudokuUtil.toGrid;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.Arrays;
import java.util.Set;

import org.junit.Test;

import com.codefunia.sudoku_solver.immutable.SudokuImpl;

public class SudokuUtilTestCase {

    @Test(expected=IllegalArgumentException.class)
    public void toGridShouldThrowIllegalArgumentException() {
	toGrid("123456789", null, "987654321");
    }
    
    @Test
    public void toGridShouldBeEqual() {
	char[][] array1 = SudokuUtil.toGrid("435269781", "682571493", "197834562", "826195347", "374682915", "951743628", "519326874", "248957136", "763418259");
	char[][] array2 = SudokuUtil.toGrid("   5 7   ", "  24 63  ", " 9  1  2 ", "27     68", "  3   1  ", "14     93", " 6  4  5 ", "  92 56  ", "   9 3   ");
	
	for (int i = 0; i < 9; i++) {
	    assertTrue(Arrays.equals(SudokuTestObject.validGrid1[i], array1[i]));
	    assertTrue(Arrays.equals(SudokuTestObject.validGrid2[i], array2[i]));
	}
    }
        
    @Test
    public void solveShouldGiveTwoSolutions() {
	Set<Sudoku> solutions = SudokuUtil.solve(SudokuFactory.createSudoku(SudokuTestObject.validGrid5));
	assertEquals(2, solutions.size());
    }
    
}
