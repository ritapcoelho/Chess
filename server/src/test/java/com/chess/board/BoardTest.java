package com.chess.board;

import com.chess.messages.spec.Color;

import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class BoardTest {
    public static Stream<Arguments> startPiecePosition() {
        return Stream.of(
            Arguments.of(0, 0, new Rook(Color.WHITE)),
            Arguments.of(0, 1, new Knight(Color.WHITE)),
            Arguments.of(0, 2, new Bishop(Color.WHITE)),
            Arguments.of(0, 3, new Queen(Color.WHITE)),
            Arguments.of(0, 4, new King(Color.WHITE)),
            Arguments.of(0, 5, new Bishop(Color.WHITE)),
            Arguments.of(0, 6, new Knight(Color.WHITE)),
            Arguments.of(0, 7, new Rook(Color.WHITE)),
            Arguments.of(7, 0, new Rook(Color.BLACK)),
            Arguments.of(7, 1, new Knight(Color.BLACK)),
            Arguments.of(7, 2, new Bishop(Color.BLACK)),
            Arguments.of(7, 3, new Queen(Color.BLACK)),
            Arguments.of(7, 4, new King(Color.BLACK)),
            Arguments.of(7, 5, new Bishop(Color.BLACK)),
            Arguments.of(7, 6, new Knight(Color.BLACK)),
            Arguments.of(7, 7, new Rook(Color.BLACK))
        );
    }


    @MethodSource("startPiecePosition")
    @ParameterizedTest
    void testPiecePosition(int x, int y, Piece piece) {
        Board board = new Board();
        Assertions.assertEquals(board.piece(x, y).get(), piece);
    }

    @Test
    void testPawnPosition() {
        Board board = new Board();
        Set<Piece> whitePawns = Stream.of(0, 1, 2, 3, 4, 5, 6, 7)
            .map(index -> board.piece(1, index).get())
            .collect(Collectors.toSet());
        Set<Piece> blackPawns = Stream.of(0, 1, 2, 3, 4, 5, 6, 7)
            .map(index -> board.piece(6, index).get())
        .collect(Collectors.toSet());
        Assertions.assertEquals(1, whitePawns.size());
        Assertions.assertEquals(new Pawn(Color.WHITE), whitePawns.iterator().next());
        Assertions.assertEquals(1, blackPawns.size());
        Assertions.assertEquals(new Pawn(Color.BLACK), blackPawns.iterator().next());
    }

    @Test
    void addMoves() {
        Board board = new Board();
        //same color
        Assertions.assertFalse(board.addMove(new Position(0, 0), new Position(0,1)));
        //no piece
        Assertions.assertFalse(board.addMove(new Position(3, 2), new Position(4,4)));
        //Pawn move should be valid
        Assertions.assertTrue(board.addMove(new Position(1, 1), new Position(2,1)));
    }

}
