package com.chess.board;

import com.chess.messages.spec.Color;

import java.util.Objects;

public class Rook extends Piece {

    private Color color;

    public Rook(Color color) {
        this.color = color;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Rook rook = (Rook) o;
        return color == rook.color;
    }

    @Override
    public int hashCode() {
        return Objects.hash(color);
    }
}
