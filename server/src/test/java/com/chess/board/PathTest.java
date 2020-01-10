package com.chess.board;

import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class PathTest {

    @Test
    void points() {
        //x=y
        List<Position> pointList = Path.points(move(0, 0, 2, 2));
        Assertions.assertTrue(pointList.contains(new Position(1, 1)));
        Assertions.assertFalse(pointList.contains(new Position(0, 0)));
        Assertions.assertFalse(pointList.contains(new Position(2, 2)));

        //y1=y2
        pointList = Path.points(move(1, 0, 5, 0));
        Assertions.assertTrue(pointList.contains(new Position(2, 0)));
        Assertions.assertTrue(pointList.contains(new Position(3, 0)));
        Assertions.assertTrue(pointList.contains(new Position(4, 0)));
        Assertions.assertFalse(pointList.contains(new Position(1, 0)));
        Assertions.assertFalse(pointList.contains(new Position(5, 0)));

        //x1=x2
        pointList = Path.points(move(1, 0, 1, 4));
        Assertions.assertTrue(pointList.contains(new Position(1, 1)));
        Assertions.assertTrue(pointList.contains(new Position(1, 2)));
        Assertions.assertTrue(pointList.contains(new Position(1, 3)));
        Assertions.assertFalse(pointList.contains(new Position(1, 0)));
        Assertions.assertFalse(pointList.contains(new Position(1, 4)));

        pointList = Path.points(move(1, 4, 1, 0));
        Assertions.assertTrue(pointList.contains(new Position(1, 1)));
        Assertions.assertTrue(pointList.contains(new Position(1, 2)));
        Assertions.assertTrue(pointList.contains(new Position(1, 3)));
        Assertions.assertFalse(pointList.contains(new Position(1, 0)));
        Assertions.assertFalse(pointList.contains(new Position(1, 4)));

        pointList = Path.points(move(5, 0, 1, 0));
        Assertions.assertTrue(pointList.contains(new Position(2, 0)));
        Assertions.assertTrue(pointList.contains(new Position(3, 0)));
        Assertions.assertTrue(pointList.contains(new Position(4, 0)));
        Assertions.assertFalse(pointList.contains(new Position(1, 0)));
        Assertions.assertFalse(pointList.contains(new Position(5, 0)));

    }

    private Move move(int x1, int y1, int x2, int y2) {
        return new Move(new Position(x1, y1), new Position(x2, y2));
    }
}
