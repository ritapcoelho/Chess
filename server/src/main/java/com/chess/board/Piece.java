package com.chess.board;

import com.chess.messages.spec.Color;

import java.util.Collection;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static com.chess.board.Board.BOARD_SIZE;

public abstract class Piece {

    public abstract Color getColor();

    public abstract boolean canMove(final Position start, final Position end);

    public boolean hasObstacles(final Board board, final Position start, final Position end){
        Path path = new Path();
        Collection<Position> positions = path.points(start.getX(), start.getY(), end.getX(), end.getY());
        for (Position pos: positions){
            if (!board.piece(pos).isEmpty()) return true;
        }
        return false;
    }


    public boolean isMoveValid(final Position start, final Position end, final Board board) {
        return !start.equals(end) && isMoveInsideBoard(end) && canMove(start, end)
                && !hasObstacles(board, start, end);
    }

    private boolean isMoveInsideBoard(final Position end) {
        return end.allLessThan(BOARD_SIZE);
    }


}
