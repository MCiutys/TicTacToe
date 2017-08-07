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
    private String level;
    private boolean startsFirst;
    private BoardAnalyzer analyzer;
    
    public Bot(Board board, String level, boolean startsFirst) {
        super();
        this.board = board;
        notClicked = board.getFreeSquares();
        this.level = level;
        this.startsFirst = startsFirst;
        analyzer = new BoardAnalyzer(board);
    }
    
    public void setLevel(String level) {
        this.level = level;
    }
    
    public void setBoard(Board board) {
        this.board = board;
    }
    
    public void setStartsFirst(boolean ans) {
        startsFirst = ans;
    }
    
    public Square mark(Square lastMove) {
       
            if (null == level) {
                return null;
            } else switch (level) {
                case Constants.EASY:
                    return board.getSameFromBoard(easyLevel());
                case Constants.RANDOM:
                    return board.getSameFromBoard(randomLevel());
                case Constants.IMPOSSIBLE:
                    return board.getSameFromBoard(impossibleLevel(lastMove));
                default:
                    break;
            }
        return null;
    }
    
    public Square randomLevel() {
        return getRandomSquare();
    }
    
    public Square easyLevel() {
        Square block = isBlockRequired();
        Square finish = canFinish();
        
        if (finish != null) {
            return board.getSameFromBoard(anyExceptFor(finish));
        }
        
        if (block != null) {
            return board.getSameFromBoard(anyExceptFor(block));
        }
        
        return getRandomSquare();
    }
    
    public Square impossibleLevel(Square lastOppMove) {
        int numberOfMoves = Constants.TOTAL_SQUARES - board.getFreeSquares().size();
        
        Square block = isBlockRequired();
        Square finish = canFinish();
        
        // In case bot can win the game
        if (finish != null) {
            return finish;
        }
        
        // In case bot has to avoid lose
        if (block != null) {
            return block;
        }
        
        if (startsFirst) {
            if (numberOfMoves == 0) {
                return analyzer.getCornerOrCenter();
            } else if (numberOfMoves == 2) {
                Square first = getMarked().get(0);
                
                if (first.equals(Constants.CENTER)) {
                    if (analyzer.isSquareEdge(lastOppMove)) {
                        return analyzer.getFreeCorner();
                    } else {
                        return getRandomSquare();
                    }
                } else {
                    if (analyzer.isSquareCorner(lastOppMove)) {
                        return analyzer.getFreeCorner();
                    } else if (analyzer.isSquareEdge(lastOppMove)) {
                        return Constants.CENTER;
                    } else {
                        return analyzer.getOppositeCorner(first);
                    }
                }
            }
            
        } else {
            if (numberOfMoves == 1) {
                return firstMove(lastOppMove);
            } else if (numberOfMoves == 3 && !board.getSameFromBoard(Constants.CENTER).getClicked()) {
                return Constants.CENTER;
            }
        }
        
        
        // It does not matter where you mark right now
        return getRandomSquare();
    }
    
    public Square getRandomSquare() {
//        notClicked = board.getFreeSquares();
        Random r = new Random();
        int rand = r.nextInt(board.getFreeSquares().size());
        return board.getFreeSquares().get(rand);
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
    
    public Square anyExceptFor(Square square) {
        Random r = new Random();
        int rand = r.nextInt(board.getFreeSquares().size());  
        
        while (board.getFreeSquares().get(rand).equals(square)) {
            rand =  r.nextInt(board.getFreeSquares().size()); 
        }
        
        System.out.println(board.getFreeSquares().get(rand));
        return board.getFreeSquares().get(rand);
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
