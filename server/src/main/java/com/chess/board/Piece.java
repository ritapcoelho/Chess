package com.chess.board;

import com.chess.messages.spec.Color;

import java.util.Collection;

import static com.chess.board.Board.BOARD_SIZE;

public interface Piece {

    Color getColor();

    boolean canMove(final Position start, final Position end);

    default boolean hasObstacles(final Board board, final Position start, final Position end){
        Collection<Position> positions = Path.points(start.getX(), start.getY(), end.getX(), end.getY());
        for (Position pos: positions){
            if (board.piece(pos).isPresent()) return true;
        }
        return false;
    }

    default boolean isMoveValid(final Position start, final Position end, final Board board) {
        return !start.equals(end) && isMoveInsideBoard(end) && canMove(start, end)
                && !hasObstacles(board, start, end);
    }

    private boolean isMoveInsideBoard(final Position end) {
        return end.allLessThan(BOARD_SIZE);
    }
}
