/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tictactoe.game;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

/**
 * Object of this class is used as a helper for the bot. It has an instance of 
 * board and provided some required information about the situation of the board
 * for the bot.
 * @author Mantas
 */
public class BoardAnalyzer {
    
    private final Board board;
    
    /**
     * Constructor for board analyzer instantiates the object of Board.
     * @param board
     */
    public BoardAnalyzer(Board board) {
        this.board = board;
    }
    
    /**
     * @return free corner of the board. If none exists, return null
     */
    public Square getFreeCorner() {
        for (Square corner : Constants.CORNERS) {
            if (board.getFreeSquares().contains(corner)) {
                return corner;
            }
        }
        return null;
    }
    
    /**
     * This method provides the opposite corner of the one given in the parameters.
     * @param corner
     * @return opposite corner of the argument
     */
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
    
    /**
     * This method provides either a center of the board or any corner.
     * @return square of the board, center or any corner of the board
     */
    public Square getCornerOrCenter() {
        Random r = new Random();
        int rand = r.nextInt(Constants.CORNERS.length + 1);
        if (rand >= 0 && rand < 4) {
            return Constants.CORNERS[rand];
        } else {
            return Constants.CENTER;
        }
    }
    
    /**
     * Method checks if a given square is an edge on the board.
     * @param s
     * @return true if given square is an edge.
     * false if given square is not an edge.
     */
    public boolean isSquareEdge(Square s) {
        for (Square edge : Constants.EDGES) {
            if (edge.equals(s)) {
                return true;
            }
        }
        return false;
    }
    
    /**
     * Method checks if a given square is an corner on the board.
     * @param s
     * @return true is given square is corner on the board.
     * false if not.
     */
    public boolean isSquareCorner(Square s) {
        for (Square edge : Constants.CORNERS) {
            if (edge.equals(s)) {
                return true;
            }
        }
        return false;
    }
    
    /**
     *
     * @param player
     * @return
     */
    public Square createTwoXLines(Player player) {
        
        ArrayList<Square> list = new ArrayList<>();
        for (Square freeSquare : board.getFreeSquares()) {
            ArrayList<Square> one = new ArrayList<>();
            ArrayList<Square> two = new ArrayList<>();
            
            one.add(freeSquare);
            one.add(player.getMarked().get(0));
            
            two.add(freeSquare);
            two.add(player.getMarked().get(1));
            
            
            int counter = 2;
            
            for (Square[] winningSet : Constants.WINNING_SETS) {
                List<Square> winSet = Arrays.asList(winningSet);
                
                if (winSet.containsAll(one)) {
                    if (!isLastElementOccupied(winSet, one)) {
                        counter--;
                    }
                }
                
                if (winSet.containsAll(two)) {
                    if (!isLastElementOccupied(winSet, two)) {
                        counter--;
                    }
                }
            }
            
            if (counter == 0) {
                list.add(freeSquare);
            }
        }
        
        System.out.println(list);
        
        Random random = new Random();
        int rand = random.nextInt(list.size());
        if (!list.isEmpty()) {
            return list.get(rand);
        } else {
            return null;
        }
    }
    
    // Checks if final element from the winning set is occupied
    private boolean isLastElementOccupied(List<Square> winSet, ArrayList<Square> twoElements) {
        Square lastOne = winSet.get(0);
        
        for (Square square : winSet) {
            if (!twoElements.contains(square)) {
                lastOne = square;
            }
        }
        
        lastOne = board.getSameFromBoard(lastOne);
        return lastOne.getClicked();
    }
    
    /**
     * Method gets a random free square from the board.
     * @return free random square from the board.
     */
    public Square getRandomSquare() {
        Random r = new Random();
        int rand = r.nextInt(board.getFreeSquares().size());
        return board.getFreeSquares().get(rand);
    }
    
    /**
     * Checks if a given square (corner) is already occupied
     * @param square
     * @return true if given square occupied. Otherwise, false.
     */
    public boolean isCornerMarked(Square square) {
        for (Square corner : Constants.CORNERS) {
            if (corner.equals(square)) {
                return true;
            }
        }
        return false;
    }
    
    /**
     * Checks if a given square (edge) is already occupied
     * @param square
     * @return true if given square occupied. Otherwise, false.
     */
    public boolean isEdgeMarked(Square square) {
        for (Square edge : Constants.EDGES) {
            if (edge.equals(square)) {
                return true;
            }
        }
        return false;
    }
    
    /**
     * Checks if a given square (centre) is already occupied
     * @param square
     * @return true if given square occupied. Otherwise, false.
     */
    public boolean isCenterMarked(Square square) {
        if (square.equals(Constants.CENTER)) {
            return true;
        }
        return false;
    }
    
    /**
     * Method provides any of the two corners that are next to the given edge
     * square
     * @param edge
     * @return any of the corners of the given edge square
     */
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
    
    /**
     * Method provideds any corner
     * @return any corner
     */
    public Square getAnyCorner() {
        Random rand = new Random();
        int randomNumber = rand.nextInt(4);
        
        return Constants.CORNERS[randomNumber];
    }
    
    /**
     * Method provides any free random square, except for the given one
     * @param square
     * @return any free random square, except for the given one. If given one is
     * the only one free, return itself.
     */
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
