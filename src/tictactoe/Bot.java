/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tictactoe;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

/**
 *
 * @author Mantas
 */
public class Bot extends Player {
    
    private ArrayList<Square> notClicked;
    private Board board;
    
    public Bot(Board board) {
        super();
        this.board = board;
        notClicked = new ArrayList<>();
    }
    
    public Square mark(Square lastMove) {
        notClicked = board.getFreeSquares();
        int numberOfMoves = Constants.TOTAL_SQUARES - notClicked.size();
        
        Square block = isBlockRequired();
        Square finish = canFinish();
        
        
        // In case bot can win the game
        if (finish != null) {
//            System.out.println("FINISH EXECUTED");
            return finish;
        }
        
        // In case bot has to avoid lose
        if (block != null) {
//            System.out.println("BLOCK EXECUTED");
            return block;
        }
        
        if (numberOfMoves == 1) {
//            System.out.println("FIRST MOVE");
            return board.getSameFromBoard(firstMove(lastMove));
        } else if (numberOfMoves == 3 && !board.getSameFromBoard(Constants.CENTER).getClicked()) {
//            System.out.println("SECOND MOVE");
            return board.getSameFromBoard(Constants.CENTER);
        }
        
        
//        System.out.println("RANDOM");
        int rand = (int) (Math.random() * notClicked.size());
        return notClicked.get(rand);
    }
    
    public Square firstMove(Square lastOppMove) {
        // If player marks the corner, bot marks the center
        if (isCornerMarked(lastOppMove)) {
            return Constants.CENTER;
        } else if (isEdgeMarked(lastOppMove)) {
            return cornerNextToEdge(lastOppMove);
        } else if (isCenterMarked(lastOppMove)) {
            return getAnyCorner();
        }
        
        // Should never be executed
        return null;
    }
    
    public Square secondMove(Square lastOppMove) {
        // If human marks the corner, bot marks an edge
        
        
        return null;
    }
    
    public boolean isCornerMarked(Square square) {
        for (Square corner : Constants.CORNERS) {
            if (corner.equals(square)) {
//                System.out.println("CORNER MARKED");
                return true;
            }
        }
        return false;
    }
    
    public boolean isEdgeMarked(Square square) {
        for (Square edge : Constants.EDGES) {
            if (edge.equals(square)) {
//                System.out.println("EDGE MARKED");
                return true;
            }
        }
        return false;
    }
    
    public boolean isCenterMarked(Square square) {
        if (square.equals(Constants.CENTER)) {
            return true;
        }
        return false;
    }
    
    public Square cornerNextToEdge(Square edge) {
        Random rand = new Random();
        int randNumber = rand.nextInt(2);
        // If edge is left or right
        if (edge.equals(Constants.LEFT_EDGE) || edge.equals(Constants.RIGHT_EDGE)) {
            if (randNumber == 0) {
                return new Square(edge.getIndex() - 3);
            } else {
                return new Square(edge.getIndex() + 3);
            }
        } else {
            if (randNumber == 0) {
                return new Square(edge.getIndex() - 1);
            } else {
                return new Square(edge.getIndex() + 1);
            }
        }
    }
    
    public Square getAnyCorner() {
        Random rand = new Random();
        int randomNumber = rand.nextInt(4);
        
        return Constants.CORNERS[randomNumber];
    }
    
    public ArrayList<Square> getOpponentsSquares() {
        ArrayList<Square> opponentSquares;// = new ArrayList<>();
        if (this.isDrawingX()) {
            opponentSquares = board.getAllOs();
        } else {
            opponentSquares = board.getAllXs();
        }
//        System.out.println("Opponent squares: " + opponentSquares);
        return opponentSquares;
    }
    
    public Square isBlockRequired() {
        ArrayList<Square> oppSquares = getOpponentsSquares();
        return finishOrBlock(oppSquares, getMarked());
    }
    
    public Square canFinish() {
        ArrayList<Square> oppSquares = getOpponentsSquares();
        return finishOrBlock(getMarked(), oppSquares);
    }
    
    public Square finishOrBlock(ArrayList<Square> squares1, ArrayList<Square> squares2) {
        
        for (Square[] set : Constants.WINNING_SETS) {
            ArrayList<Square> temp = new ArrayList<>(Arrays.asList(set));
            
            for (Square sq : set) {
                for (Square s : squares1) {
                    if (sq.compareTo(s) == 0) {
                       temp.remove(sq);
                    }
                }
            }
            
            if (temp.size() == 1) {
                  if (!squares2.contains(temp.get(0))) {
                      return board.getSameFromBoard(temp.get(0));
                  }
                
            }
        }
        return null;
    }
}
