package sudoku_solver.sudoku_solver.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import sudoku_solver.sudoku_solver.model.SudokuBoard;
import sudoku_solver.sudoku_solver.service.SudokuService;

@RestController
@RequestMapping("/api/sudoku")
public class SudokuController {

    @Autowired
    private SudokuService sudokuService;

    @PostMapping("/solve")
    public SudokuBoard solveSudoku(@RequestBody SudokuBoard board) {
        if (sudokuService.solve(board)) {
            return board;
        } else {
            throw new RuntimeException("No solution exists");
        }
    }
}