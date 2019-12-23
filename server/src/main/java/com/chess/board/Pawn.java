package com.chess.board;

import com.chess.messages.spec.Color;

import java.util.Objects;

public class Pawn extends Piece {

    private Color color;

    public Pawn(Color color) {
        this.color = color;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Pawn pawn = (Pawn) o;
        return color == pawn.color;
    }

    @Override
    public int hashCode() {
        return Objects.hash(color);
    }
}
