package com.chess.board;

import com.chess.messages.spec.Color;

import static com.chess.board.Board.BOARD_SIZE;

public abstract class Piece {

    public abstract Color getColor();
    public abstract boolean canMove(final Position start, final Position end);

    public boolean isMoveValid(final Position start, final Position end) {
        return !start.equals(end) && isMoveInsideBoard(end);
    }

    private boolean isMoveInsideBoard(final Position end) {
        return end.allLessThan(BOARD_SIZE);
    }

}
