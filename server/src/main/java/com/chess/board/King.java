package com.chess.board;

import com.chess.messages.spec.Color;

import java.util.Objects;

public class King extends Piece {

    private Color color;

    public King(Color color) {
        this.color = color;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        King king = (King) o;
        return color == king.color;
    }

    @Override
    public int hashCode() {
        return Objects.hash(color);
    }
}
