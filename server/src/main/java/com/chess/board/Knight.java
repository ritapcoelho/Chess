package com.chess.board;

import com.chess.messages.spec.Color;

import java.util.Objects;

public class Knight extends Piece {

    private Color color;

    public Knight(Color color) {
        this.color = color;
    }

    @Override
    public Color getColor() {
        return color;
    }

    @Override
    public boolean canMove(Position start, Position end) {
        return true;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Knight knight = (Knight) o;
        return color == knight.color;
    }

    @Override
    public int hashCode() {
        return Objects.hash(color);
    }
}
