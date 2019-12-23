package com.chess.board;

import com.chess.messages.spec.Color;
import com.google.common.collect.HashBasedTable;
import com.google.common.collect.Table;

import java.util.List;

public class Board {
    private static final int SIZE = 8;

    public static final Piece WHITE_ROOK = new Rook(Color.WHITE);
    public static final Piece BLACK_ROOK = new Rook(Color.BLACK);

    public static final Piece WHITE_BISHOP = new Bishop(Color.WHITE);
    public static final Piece BLACK_BISHOP = new Bishop(Color.BLACK);

    public static final Piece WHITE_KNIGHT = new Knight(Color.WHITE);
    public static final Piece BLACK_KNIGHT = new Knight(Color.BLACK);

    public static final Piece WHITE_QUEEN = new Queen(Color.WHITE);
    public static final Piece BLACK_QUEEN = new Queen(Color.BLACK);

    public static final Piece WHITE_KING = new King(Color.WHITE);
    public static final Piece BLACK_KING = new King(Color.BLACK);

    public static final Piece WHITE_PAWN = new Pawn(Color.WHITE);
    public static final Piece BLACK_PAWN = new Pawn(Color.BLACK);

    private static final List<Piece> whitePieces =
        List.of(WHITE_ROOK, WHITE_KNIGHT, WHITE_BISHOP, WHITE_QUEEN, WHITE_KING, WHITE_BISHOP, WHITE_KNIGHT, WHITE_ROOK);
    private static final List<Piece> blackPieces =
        List.of(BLACK_ROOK, BLACK_KNIGHT, BLACK_BISHOP, BLACK_QUEEN, BLACK_KING, BLACK_BISHOP, BLACK_KNIGHT, BLACK_ROOK);
    Table<Integer, Integer, Square> chessBoard = HashBasedTable.create();

    public Board() {
        init();
    }

    private void init() {
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                if (i == 0) {
                    chessBoard.put(i, j, new Square(whitePieces.get(j)));
                }
                else if (i == 1) {
                    chessBoard.put(i, j, new Square(WHITE_PAWN));
                }
                else if (i == 7) {
                    chessBoard.put(i, j, new Square(blackPieces.get(j)));
                }
                else if (i == 6) {
                    chessBoard.put(i, j, new Square(BLACK_PAWN));
                } else {
                    chessBoard.put(i, j, new Square(null));
                }
            }
        }
    }

    public Piece piece(int x, int y) {
        return chessBoard.get(x, y).getPiece();
    }
}
