/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tictactoe.game;

import tictactoe.game.Board;
import tictactoe.game.Constants;
import tictactoe.game.Player;
import tictactoe.game.Square;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
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
    
    public Square createTwoXLines(Player player) {
        ArrayList<Square> playerMarked = player.getMarked();
        ArrayList<Square> temp = new ArrayList<>();
        
            for (Square boardSq : board.getFreeSquares()) {
                int counter = 2;
                ArrayList<Square> oneTwoSet = new ArrayList<>();
                oneTwoSet.add(playerMarked.get(0));
                ArrayList<Square> twoTwoSet = new ArrayList<>();
                twoTwoSet.add(playerMarked.get(1));
                oneTwoSet.add(boardSq);
                twoTwoSet.add(boardSq);
                
                boardLoop : for (Square[] winningSet : Constants.WINNING_SETS) {
                    List<Square> winSet = Arrays.asList(winningSet);
                    if (winSet.containsAll(twoTwoSet) || winSet.containsAll(oneTwoSet)) {
                        counter--;
                        if (counter <= 0) {
                            temp.add(boardSq);
                            break;
                        }
                    }
                }
            }
//        }
        System.out.println(temp);
        
        Random r = new Random();
        if (!temp.isEmpty()) {
            int rand = r.nextInt(temp.size());
            System.out.println(temp.get(rand));
            return temp.get(rand);
        }
        
        return null;
    }
    
    private ArrayList<Square> removeRedudant(ArrayList<Square> p, ArrayList<Square> playerMarked) {
        ArrayList<Square> poss = p;
        for (Square square : poss) {
            ArrayList<Square> set1 = new ArrayList<>();
            set1.add(square);
            set1.add(playerMarked.get(0));
            
            ArrayList<Square> set2 = new ArrayList<>();
            set2.add(square);
            set2.add(playerMarked.get(1));
            
            for (Square[] winningSet : Constants.WINNING_SETS) {
                List<Square> winSet = Arrays.asList(winningSet);
                if (winSet.containsAll(set1)) {
                    for (Square s : winningSet) {
                        
                    }
                }
            }
        }
        return null;
    }
    
    public Square getRandomSquare() {
        Random r = new Random();
        int rand = r.nextInt(board.getFreeSquares().size());
        return board.getFreeSquares().get(rand);
    }
    
    public boolean isCornerMarked(Square square) {
        for (Square corner : Constants.CORNERS) {
            if (corner.equals(square)) {
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
    
    public Square anyExceptFor(Square square) {
        
        if (board.getFreeSquares().size() == 1) {
            return square;
        }
        
        Random r = new Random();
        int rand = r.nextInt(board.getFreeSquares().size());  
        
        while (board.getFreeSquares().get(rand).equals(square)) {
            rand =  r.nextInt(board.getFreeSquares().size()); 
        }
        return board.getFreeSquares().get(rand);
    }
     
}
