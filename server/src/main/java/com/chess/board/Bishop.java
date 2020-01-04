package com.chess.board;

import com.chess.messages.spec.Color;

import java.util.Objects;

public class Bishop implements Piece {

    private Color color;

    public Bishop(Color color) {
        this.color = color;
    }

    @Override
    public Color getColor() {
        return color;
    }

    @Override
    public boolean canMove(Move move) {
        return move.isDiagonal();
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
}
