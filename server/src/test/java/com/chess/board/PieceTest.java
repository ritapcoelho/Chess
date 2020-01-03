package com.chess.board;

import com.chess.messages.spec.Color;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

class PieceTest {

    Piece piece = new Queen(Color.WHITE);

    @Test
    void moveToSamePositionInvalid() {
        Assertions.assertFalse(piece.isMoveValid(new Position(1, 1), new Position(1, 1), new Board()));
    }

    @Test
    void moveOutsideInvalid() {
        Assertions.assertFalse(piece.isMoveValid(new Position(1, 1), new Position(8, 1), new Board()));
        Assertions.assertFalse(piece.isMoveValid(new Position(1, 1), new Position(1, 8), new Board()));
    }
}
