package com.chess.board;

import com.chess.messages.spec.Color;
import io.quarkus.test.Mock;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class QueenTest {

    Queen queen = new Queen(Color.WHITE);

    @Test
    void canMove() {
        //diagonal
        Move move = new Move(new Position(0,0), new Position(2,2));
        Assertions.assertTrue(queen.canMove(move));

        //vertical
        move = new Move(new Position(0,0), new Position(0,2));
        assertTrue(queen.canMove(move));

        //horizontal
        move = new Move(new Position(0,0), new Position(2,0));
        assertTrue(queen.canMove(move));

        move = new Move(new Position(1,1), new Position(2,3));
        assertFalse(queen.canMove(move));

        move = new Move(new Position(4,4), new Position(3,2));
        assertFalse(queen.canMove(move));

    }
}