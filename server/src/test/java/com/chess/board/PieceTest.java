package com.chess.board;

import com.chess.messages.spec.Color;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class PieceTest {

    Piece piece = new Queen(Color.WHITE);

    @Test
    void moveToSamePositionInvalid() {
        Assertions.assertFalse(piece.isMoveValid(new Position(1, 1), new Position(1, 1)));
    }

    @Test
    void moveOutsideInvalid() {
        Assertions.assertFalse(piece.isMoveValid(new Position(1, 1), new Position(8, 1)));
        Assertions.assertFalse(piece.isMoveValid(new Position(1, 1), new Position(1, 8)));
    }
}
