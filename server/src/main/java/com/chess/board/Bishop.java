package com.chess.board;

import com.chess.messages.spec.Color;

import java.util.Objects;

public class Bishop extends Piece {

    private Color color;

    public Bishop(Color color) {
        this.color = color;
    }

    @Override
    public Color getColor() {
        return color;
    }

    @Override
    public boolean canMove(Position start, Position end) {
        return checkXEqualsToY(start,end);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Bishop bishop = (Bishop) o;
        return color == bishop.color;
    }

    @Override
    public int hashCode() {
        return Objects.hash(color);
    }

    private boolean checkXEqualsToY(Position start, Position end){
        return Math.abs(start.getX()-end.getX()) == Math.abs(start.getY()-end.getY());

    }
}
