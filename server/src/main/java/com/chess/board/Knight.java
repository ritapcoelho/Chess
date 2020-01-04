package com.chess.board;

import com.chess.messages.spec.Color;

import java.util.Objects;

public class Knight implements Piece {

    private Color color;

    public Knight(Color color) {
        this.color = color;
    }

    @Override
    public Color getColor() {
        return color;
    }

    @Override
    public boolean canMove(Move move) {
        return move.numberOfMovesX() == 2 && move.numberOfMovesY() == 1
            || move.numberOfMovesY() == 2 && move.numberOfMovesX() == 1;
    }

    @Override
    public boolean hasObstacles(Board board, Move move) {
        return false;
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
