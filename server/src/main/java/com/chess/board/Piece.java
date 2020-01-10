package com.chess.board;

import com.chess.messages.spec.Color;

import java.util.Collection;

import static com.chess.board.Board.BOARD_SIZE;

public interface Piece {

    Color getColor();

    boolean canMove(final Move move);

    default boolean hasObstacles(final Board board, final Move move){
        Collection<Position> positions = Path.points(move);
        for (Position pos: positions){
            if (board.piece(pos).isPresent()) return true;
        }
        return false;
    }

    default boolean isMoveValid(final Position start, final Position end, final Board board) {
        Move move = new Move(start, end);
        return !start.equals(end) && isMoveInsideBoard(end) && canMove(move)
                && !hasObstacles(board, move);
    }

    private boolean isMoveInsideBoard(final Position end) {
        return end.allLessThan(BOARD_SIZE);
    }
}
