package com.chess.board;

import java.awt.*;
import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;



class PathTest {

    @Test
    void points() {
        //x=y
        Path pointsInPath = new Path();
        List<Position> pointList = pointsInPath.points(0, 0, 2, 2);
        Assertions.assertTrue(pointList.contains(new Position(1, 1)));
        Assertions.assertFalse(pointList.contains(new Position(0, 0)));
        Assertions.assertFalse(pointList.contains(new Position(2, 2)));

        //y1=y2
        pointList = pointsInPath.points(1, 0, 5, 0);
        Assertions.assertTrue(pointList.contains(new Position(2, 0)));
        Assertions.assertTrue(pointList.contains(new Position(3, 0)));
        Assertions.assertTrue(pointList.contains(new Position(4, 0)));
        Assertions.assertFalse(pointList.contains(new Position(1, 0)));
        Assertions.assertFalse(pointList.contains(new Position(5, 0)));

        //x1=x2
        pointList = pointsInPath.points(1, 0, 1, 4);
        Assertions.assertTrue(pointList.contains(new Position(1, 1)));
        Assertions.assertTrue(pointList.contains(new Position(1, 2)));
        Assertions.assertTrue(pointList.contains(new Position(1, 3)));
        Assertions.assertFalse(pointList.contains(new Position(1, 0)));
        Assertions.assertFalse(pointList.contains(new Position(1, 4)));

    }
}