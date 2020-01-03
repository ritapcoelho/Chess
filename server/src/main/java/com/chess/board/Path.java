package com.chess.board;

import java.util.List;
import java.util.ArrayList;

public class Path {

    public List<Position> points(int x1, int y1, int x2, int y2) {
        List<Position> pointList = new ArrayList<>();
        //horizontal
        if (x1 == x2 ) {
            if(y1 < y2) {
                for (int y = y1 + 1; y < y2; y++){
                    pointList.add(new Position(x1, y));
                }
            }
            else{
                for (int y = x1 - 1; y > x2; y++){
                    pointList.add(new Position(x1, y));
                }
            }
        }
        //vertical
        else if (y1 == y2){
            if(x1 < x2) {
                for (int x = x1 + 1; x < x2; x++){
                    pointList.add(new Position(x, y1));
                }
            }
            else{
                for (int x = x1 - 1; x > x2; x++){
                    pointList.add(new Position(x, y1));
                }
            }

        } else {
            int xDiff = x2 - x1;
            int yDiff = y2 - y1;
            double m = ((double) yDiff) / ((double) xDiff);
            double b = y1 - (m * x1);
            int startX = Integer.min(x1,x2);
            int endX = Integer.max(x1,x2);
            for (int x = startX + 1; x < endX; x++) {
                Double y = (m * x) + b;
                int yy = y.intValue();
                pointList.add(new Position(x, yy));
            }
        }
        return pointList;
    }

}
