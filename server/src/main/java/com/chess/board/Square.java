package com.chess.board;

public class Square {
    private Piece piece;

    public Square(final Piece piece) {
        this.piece = piece;
    }

    public Piece getPiece() {
        return piece;
    }
}
