package sudoku_solver.sudoku_solver.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import sudoku_solver.sudoku_solver.model.SudokuBoard;
import sudoku_solver.sudoku_solver.service.SudokuService;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class SudokuControllerTest {

    @Mock
    private SudokuService sudokuService;

    @InjectMocks
    private SudokuController sudokuController;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testSolveValidSudoku() {
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

        when(sudokuService.solve(board)).thenReturn(true);

        SudokuBoard solvedBoard = sudokuController.solveSudoku(board);

        assertNotNull(solvedBoard);
        assertArrayEquals(boardArray, solvedBoard.getBoard());
    }

    @Test
    public void testSolveUnsolvableSudoku() {
        int[][] unsolvableBoardArray = {
                {5, 5, 4, 6, 7, 8, 9, 1, 2},
                {6, 7, 2, 1, 9, 5, 3, 4, 8},
                {1, 9, 8, 3, 4, 2, 5, 6, 7},
                {8, 5, 9, 7, 6, 1, 4, 2, 3},
                {4, 2, 6, 8, 5, 3, 7, 9, 1},
                {7, 1, 3, 9, 2, 4, 8, 5, 6},
                {9, 6, 1, 5, 3, 7, 2, 8, 4},
                {2, 8, 7, 4, 1, 9, 6, 3, 5},
                {3, 4, 5, 2, 8, 6, 1, 7, 9}
        };

        SudokuBoard unsolvableBoard = new SudokuBoard(unsolvableBoardArray);

        when(sudokuService.solve(unsolvableBoard)).thenReturn(false);

        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
            sudokuController.solveSudoku(unsolvableBoard);
        });

        assertEquals("No solution exists", exception.getMessage());
    }

    @Test
    public void testSolveEmptyBoard() {
        int[][] emptyBoardArray = new int[9][9];
        SudokuBoard emptyBoard = new SudokuBoard(emptyBoardArray);

        when(sudokuService.solve(emptyBoard)).thenReturn(true);

        SudokuBoard solvedBoard = sudokuController.solveSudoku(emptyBoard);

        assertNotNull(solvedBoard);
        assertArrayEquals(emptyBoardArray, solvedBoard.getBoard());
    }
}
