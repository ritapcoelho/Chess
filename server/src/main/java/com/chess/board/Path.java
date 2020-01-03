package com.chess.board;

import java.util.List;
import java.util.ArrayList;

public final class Path {

    private Path() {
    }

    public static List<Position> points(int x1, int y1, int x2, int y2) {
        int startX = Integer.min(x1, x2);
        int endX = Integer.max(x1, x2);
        int startY = Integer.min(y1, y2);
        int endY = Integer.max(y1, y2);
        if (x1 == x2) {
            return pointListVertical(startY, endY, x1);
        } else if (y1 == y2) {
            return pointListHorizontal(startX, endX, y1);
        } else {
            return pointListDiagonal(startX, endX, x1, x2, y1, y2);
        }
    }

    private static List<Position> pointListVertical(int startY, int endY, int x) {
        List<Position> pointList = new ArrayList<>();
        for (int y = startY + 1; y < endY; y++) {
            pointList.add(new Position(x, y));
        }
        return pointList;
    }

    private static List<Position> pointListHorizontal(int startX, int endX, int y) {
        List<Position> pointList = new ArrayList<>();
        for (int x = startX + 1; x < endX; x++) {
            pointList.add(new Position(x, y));
        }
        return pointList;
    }

    private static List<Position> pointListDiagonal(int startX, int endX, int x1, int x2, int y1, int y2){
        List<Position> pointList = new ArrayList<>();
        int xDiff = x2 - x1;
        int yDiff = y2 - y1;
        double m = ((double) yDiff) / ((double) xDiff);
        double b = y1 - (m * x1);
        for (int x = startX + 1; x < endX; x++) {
            Double y = (m * x) + b;
            int yy = y.intValue();
            pointList.add(new Position(x, yy));
        }
        return pointList;
    }

}
