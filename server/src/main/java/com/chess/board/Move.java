package com.chess.board;

public class Move {

    private final Position start;
    private final Position end;
    private final int minX;
    private final int minY;
    private final int maxX;
    private final int maxY;
    private final int numberOfMovesY;
    private final int numberOfMovesX;
    private final int xDiff;
    private final int yDiff;
    private final boolean vertical;
    private final boolean horizontal;
    private final boolean diagonal;

    public Move(Position start, Position end) {
        this.start = start;
        this.end = end;
        this.minX = Integer.min(start.getX(), end.getX());
        this.maxX = Integer.max(start.getX(), end.getX());
        this.minY = Integer.min(start.getY(), end.getY());
        this.maxY = Integer.max(start.getY(), end.getY());
        this.numberOfMovesY = Math.abs(start.getY() - end.getY());
        this.numberOfMovesX = Math.abs(start.getX() - end.getX());
        this.xDiff = end.getX() - start.getX();
        this.yDiff = end.getY() - start.getY();
        this.vertical = start.getX() == end.getX();
        this.horizontal = start.getY() == end.getY();
        this.diagonal = numberOfMovesY == numberOfMovesX;
    }

    public Position getStart() {
        return start;
    }

    public Position getEnd() {
        return end;
    }

    public int minX() {
        return minX;
    }

    public int minY() {
        return minY;
    }

    public int maxX() {
        return maxX;
    }

    public int maxY() {
        return maxY;
    }

    public int numberOfMovesY() {
        return numberOfMovesY;
    }

    public int numberOfMovesX() {
        return numberOfMovesX;
    }

    public int xDiff() {
        return xDiff;
    }

    public int yDiff() {
        return yDiff;
    }

    public boolean isVertical() {
        return vertical;
    }

    public boolean isHorizontal() {
        return horizontal;
    }

    public boolean isDiagonal() {
        return horizontal;
    }
}
