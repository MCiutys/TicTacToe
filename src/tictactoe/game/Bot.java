/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tictactoe.game;

import tictactoe.game.BoardAnalyzer;
import tictactoe.game.Board;
import tictactoe.game.Constants;
import tictactoe.game.FileReader;
import tictactoe.game.Player;
import tictactoe.game.Square;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

/**
 *
 * @author Mantas
 */
public class Bot extends Player {
    
//    private ArrayList<Square> notClicked;
    private Board board;
    private String level;
    private boolean startsFirst;
    private final BoardAnalyzer analyzer;
    
    public Bot(Board board, String level, boolean startsFirst) throws FileNotFoundException {
        super();
        this.board = board;
//        notClicked = board.getFreeSquares();
        this.level = level;
        this.startsFirst = startsFirst;
        analyzer = new BoardAnalyzer(board);
        setName(FileReader.chooseName());
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
                    Square easy = easyLevel();
                    System.out.println(easy);
                    return board.getSameFromBoard(easy);
                case Constants.RANDOM:
                    return board.getSameFromBoard(randomLevel());
                case Constants.IMPOSSIBLE:
                    return board.getSameFromBoard(impossibleLevel(lastMove));
                default:
                    break;
            }
            System.out.println("null is returned");
        return null;
    }
    
    public Square randomLevel() {
        return analyzer.getRandomSquare();
    }
    
    public Square easyLevel() {
        Square block = isBlockRequired();
        Square finish = canFinish();
        
        System.out.println("not stuck");
                
        if (finish != null) {
            return board.getSameFromBoard(analyzer.anyExceptFor(finish));
        }
        
        if (block != null) {
            return board.getSameFromBoard(analyzer.anyExceptFor(block));
        }
        
        
        return analyzer.getRandomSquare();
    }
    
    public Square impossibleLevel(Square lastOppMove) {
        int numberOfMoves = Constants.TOTAL_SQUARES - board.getFreeSquares().size();
                
        Square block = isBlockRequired();
        Square finish = canFinish();
        Square trapOpponent = null;
        
        if (getMarked().size() == 2) {
            trapOpponent = analyzer.createTwoXLines(this);
        }
        
        // In case bot can win the game
        if (finish != null) {
            return finish;
        }
        
        // In case bot has to avoid lose
        if (block != null) {
            return block;
        }
        
       if (trapOpponent != null) {
           return trapOpponent;
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
                        return analyzer.getRandomSquare();
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
        return analyzer.getRandomSquare();
    }
    
    public Square firstMove(Square lastOppMove) {
        // If player marks the corner, bot marks the center
        if (analyzer.isCornerMarked(lastOppMove)) {
            return Constants.CENTER;
        } else if (analyzer.isEdgeMarked(lastOppMove)) {
            return analyzer.cornerNextToEdge(lastOppMove);
        } else if (analyzer.isCenterMarked(lastOppMove)) {
            return analyzer.getAnyCorner();
        }
        
        // Should never be executed
        return null;
    }
    
    public ArrayList<Square> getOpponentsSquares() {
        ArrayList<Square> opponentSquares;
        if (isDrawingX()) {
            opponentSquares = board.getAllOs();
        } else {
            opponentSquares = board.getAllXs();
        }
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
