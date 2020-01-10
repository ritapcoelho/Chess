package com.chess.board;

import com.chess.messages.spec.Color;
import org.checkerframework.checker.units.qual.C;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.wildfly.common.Assert;

import static org.junit.jupiter.api.Assertions.*;

class BishopTest {

    Bishop bishop = new Bishop(Color.WHITE);

    @Test
    void canMove() {
        Move move = new Move(new Position(0,0), new Position(4,4));
        Assert.assertTrue(bishop.canMove(move));

        move = new Move(new Position(3,3), new Position(1,1));
        Assert.assertTrue(bishop.canMove(move));

        move = new Move(new Position(0,0), new Position(1,4));
        Assert.assertFalse(bishop.canMove(move));

        move = new Move(new Position(5,0), new Position(5,1));
        Assert.assertFalse(bishop.canMove(move));
    }
}