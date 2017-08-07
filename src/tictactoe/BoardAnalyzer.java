/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tictactoe;

import java.util.Random;

/**
 *
 * @author Mantas
 */
public class BoardAnalyzer {
    
    private Board board;
    
    public BoardAnalyzer(Board board) {
        this.board = board;
    }
    
    public Square getFreeCorner() {
        for (Square corner : Constants.CORNERS) {
            if (board.getFreeSquares().contains(corner)) {
                return corner;
            }
        }
        return null;
    }
    
    public Square getOppositeCorner(Square corner) {
        switch (corner.getIndex()) {
            case 0:
                return board.getSquares()[8];
            case 2:
                return board.getSquares()[6];
            case 6:
                return board.getSquares()[2];
            case 8:
                return board.getSquares()[0];
            default:
                break;
        }
        return null;
    }
    
    public Square createTwoXLines() {
        return null;
    }
    
    public Square getCornerOrCenter() {
        Random r = new Random();
        int rand = r.nextInt(Constants.CORNERS.length + 1);
        if (rand >= 0 && rand < 4) {
            return Constants.CORNERS[rand];
        } else {
            return Constants.CENTER;
        }
    }
    
    public boolean isSquareEdge(Square s) {
        for (Square edge : Constants.EDGES) {
            if (edge.equals(s)) {
                return true;
            }
        }
        return false;
    }
    
    public boolean isSquareCorner(Square s) {
        for (Square edge : Constants.CORNERS) {
            if (edge.equals(s)) {
                return true;
            }
        }
        return false;
    }
}
