package sudoku_solver.sudoku_solver.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
public class SudokuBoard {
    private int[][] board;

    @JsonCreator
    public SudokuBoard(@JsonProperty("board") int[][] board) {
        this.board = board;
    }
}
