package com.chess.board;

import com.chess.messages.spec.Color;

import java.util.Objects;

public class Queen implements Piece {

    private Color color;

    public Queen(Color color) {
        this.color = color;
    }

    @Override
    public Color getColor() {
        return color;
    }

    @Override
    public boolean canMove(Move move) {
        return true;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Queen queen = (Queen) o;
        return color == queen.color;
    }

    @Override
    public int hashCode() {
        return Objects.hash(color);
    }
}
