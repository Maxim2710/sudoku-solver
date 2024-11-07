package sudoku_solver.sudoku_solver.service;

import org.junit.jupiter.api.Test;
import sudoku_solver.sudoku_solver.model.SudokuBoard;

import static org.junit.jupiter.api.Assertions.*;

class SudokuServiceTest {

    private final SudokuService sudokuService = new SudokuService();

    @Test
    void testSolve_ValidPuzzle_Success() {
        int[][] boardArray = {
                {5, 3, 0, 0, 7, 0, 0, 0, 0},
                {6, 0, 0, 1, 9, 5, 0, 0, 0},
                {0, 9, 8, 0, 0, 0, 0, 6, 0},
                {8, 0, 0, 0, 6, 0, 0, 0, 3},
                {4, 0, 0, 8, 0, 3, 0, 0, 1},
                {7, 0, 0, 0, 2, 0, 0, 0, 6},
                {0, 6, 0, 0, 0, 0, 2, 8, 0},
                {0, 0, 0, 4, 1, 9, 0, 0, 5},
                {0, 0, 0, 0, 8, 0, 0, 7, 9}
        };

        SudokuBoard board = new SudokuBoard(boardArray);
        boolean solved = sudokuService.solve(board);

        assertTrue(solved, "The Sudoku puzzle should be solved.");

        int[][] solvedBoard = board.getBoard();
        for (int row = 0; row < 9; row++) {
            for (int col = 0; col < 9; col++) {
                assertNotEquals(0, solvedBoard[row][col], "All cells should be filled.");
            }
        }
    }

    @Test
    void testSolve_UnsolvablePuzzle_Failure() {
        int[][] unsolvableBoardArray = {
                {5, 3, 0, 0, 7, 0, 0, 0, 0},
                {6, 0, 0, 1, 9, 5, 0, 0, 0},
                {0, 9, 8, 0, 0, 0, 0, 6, 0},
                {8, 0, 0, 0, 6, 0, 0, 0, 3},
                {4, 0, 0, 8, 0, 3, 0, 0, 1},
                {7, 0, 0, 0, 2, 0, 0, 0, 6},
                {0, 6, 0, 0, 0, 0, 2, 8, 0},
                {0, 0, 0, 4, 1, 9, 0, 0, 5},
                {0, 0, 0, 0, 8, 0, 0, 7, 8}
        };

        SudokuBoard board = new SudokuBoard(unsolvableBoardArray);
        boolean solved = sudokuService.solve(board);

        assertFalse(solved, "The unsolvable Sudoku puzzle should not be solved.");
    }

    @Test
    void testIsSafe() {
        int[][] boardArray = {
                {5, 3, 0, 0, 7, 0, 0, 0, 0},
                {6, 0, 0, 1, 9, 5, 0, 0, 0},
                {0, 9, 8, 0, 0, 0, 0, 6, 0},
                {8, 0, 0, 0, 6, 0, 0, 0, 3},
                {4, 0, 0, 8, 0, 3, 0, 0, 1},
                {7, 0, 0, 0, 2, 0, 0, 0, 6},
                {0, 6, 0, 0, 0, 0, 2, 8, 0},
                {0, 0, 0, 4, 1, 9, 0, 0, 5},
                {0, 0, 0, 0, 8, 0, 0, 7, 9}
        };

        assertTrue(sudokuService.isSafe(boardArray, 0, 2, 4), "4 should be safe to place at (0,2)");
        assertFalse(sudokuService.isSafe(boardArray, 0, 2, 5), "5 should not be safe to place at (0,2)");
    }
}
