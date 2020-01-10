package com.chess.board;

import java.util.List;
import java.util.ArrayList;

public final class Path {

    private Path() {
    }

    public static List<Position> points(Move move) {
        if (move.isVertical()) {
            return pointListVertical(move);
        } else if (move.isHorizontal()) {
            return pointListHorizontal(move);
        } else {
            return pointListDiagonal(move);
        }
    }

    private static List<Position> pointListVertical(Move move) {
        List<Position> pointList = new ArrayList<>();
        for (int y = move.minY() + 1; y < move.maxY(); y++) {
            pointList.add(new Position(move.getStart().getX(), y));
        }
        return pointList;
    }

    private static List<Position> pointListHorizontal(Move move) {
        List<Position> pointList = new ArrayList<>();
        for (int x = move.minX() + 1; x < move.maxX(); x++) {
            pointList.add(new Position(x, move.getStart().getY()));
        }
        return pointList;
    }

    private static List<Position> pointListDiagonal(Move move){
        List<Position> pointList = new ArrayList<>();
        double m = ((double) move.yDiff()) / ((double) move.xDiff());
        double b = move.getStart().getY() - (m * move.getStart().getX());
        for (int x = move.minX() + 1; x < move.maxX(); x++) {
            Double y = (m * x) + b;
            int yy = y.intValue();
            pointList.add(new Position(x, yy));
        }
        return pointList;
    }

}
