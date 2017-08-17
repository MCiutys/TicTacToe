package tictactoe.game;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.util.ArrayList;
import javax.swing.JPanel;

/**
 *
 * @author Mantas
 */
public class Board extends JPanel {
    
    private final Square[] squares;
    private final Square[] winningPoints;
    
    /**
     *
     */
    public Board() {
        squares = new Square[Constants.HORIZONTAL_SQUARES * Constants.VERTICAL_SQUARES];
        winningPoints = new Square[3];
        addButtons();
    }
    
    /**
     *
     */
    public final void addButtons() {
        for (int i = 0; i < Constants.HORIZONTAL_SQUARES * Constants.VERTICAL_SQUARES; i++) {
                Square square = new Square(i);
                this.add(square.getButton());
                squares[i] = square;
        }
    }
    
    /**
     *
     */
    public void clearBoard() {
        for (Square square : squares) {
            square.clearSquare();
        }
        resetWinningPoints();
    }
    
    /**
     *
     * @return
     */
    public Square[] getSquares() {
        return squares;
    }
    
    /**
     *
     * @param sq
     * @return
     */
    public Square getSameFromBoard(Square sq) {
        for (Square s : squares) {
            if (s.compareTo(sq) == 0) {
                return s;
            }
        }
        return null;
    }
    
    /**
     *
     * @param s
     * @return
     */
    public boolean doesInclude(Square s) {
        for (Square sq : squares) {
            if (sq == s) {
                return true;
            }
        }
        return false;
    }
    
    /**
     *
     * @return
     */
    public ArrayList<Square> getFreeSquares() {
        ArrayList<Square> freeSquares = new ArrayList<>();
        for (Square square : squares) {
            if (!square.getClicked()) {
                freeSquares.add(square);
            }

        }
        return freeSquares;
    }
    
    /**
     *
     * @return
     */
    public ArrayList<Square> getAllXs() {
        ArrayList<Square> xs = new ArrayList<>();
        for (Square square : squares) {
            if (square.isDrawnX()) {
                xs.add(square);
            }
        }
        return xs;
    }
    
    /**
     *
     * @return
     */
    public ArrayList<Square> getAllOs() {
        ArrayList<Square> os = new ArrayList<>();
        for (Square square : squares) {
            if (square.isDrawnX()) {
                os.add(square);
            }
        }
        return os;
    }
    
    public void setColor(Color c) {
        for (Square square : squares) {
            square.setColor(c);
        }
    }
    
    @Override
    public void paint(Graphics g) {
        super.paint(g);
        Graphics2D g2d = (Graphics2D) g;
        
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
        RenderingHints.VALUE_ANTIALIAS_ON);
        
        g2d.setStroke(new BasicStroke(Constants.STROKE_WIDTH));
        // Horiztontal lines
        g2d.drawLine(0, TicTacToe.HEIGHT / 3,
                Constants.BOARD_WIDTH, TicTacToe.HEIGHT / 3);
        g2d.drawLine(0, 2 * TicTacToe.HEIGHT / 3,
                Constants.BOARD_WIDTH, 2 * TicTacToe.HEIGHT / 3);
        // Vertical lines
        g2d.drawLine(Constants.BOARD_WIDTH / 3, 0, Constants.BOARD_WIDTH / 3,
                TicTacToe.HEIGHT);
        g2d.drawLine(2 * Constants.BOARD_WIDTH / 3, 0, 2 * Constants.BOARD_WIDTH / 3,
                TicTacToe.HEIGHT);
                
        drawWinningLine(g2d);
    }
    
    public void drawWinningLine(Graphics2D g2d) {
        
        g2d.setStroke(new BasicStroke(Constants.STROKE_WIDTH * 2));
        
        if (winningPoints.length == 3 && winningPoints[0] != null) {
            Square firstSq = getSameFromBoard(winningPoints[0]);
            Square secondSq = getSameFromBoard(winningPoints[1]);
            Square thirdSq = getSameFromBoard(winningPoints[2]);
            
            int offsetWidth = firstSq.getButton().getWidth() / 2;
            int offsetHeight = firstSq.getButton().getHeight() / 2;
            
            int differenceX = Math.abs(firstSq.getCenterX() - secondSq.getCenterX());
            int differenceY = Math.abs(firstSq.getCenterY() - secondSq.getCenterY());
            
            
            
            // Vertical line
            if (differenceX == 0) {
                g2d.drawLine(firstSq.getCenterX(), firstSq.getCenterY() - offsetHeight, secondSq.getCenterX(), secondSq.getCenterY()+ offsetHeight);
                g2d.drawLine(secondSq.getCenterX(), secondSq.getCenterY() - offsetHeight, thirdSq.getCenterX(), thirdSq.getCenterY() + offsetHeight);
            }
            
            // Horizontal line
            if (differenceY == 0) {
                g2d.drawLine(firstSq.getCenterX() - offsetWidth, firstSq.getCenterY(), secondSq.getCenterX()+ offsetWidth, secondSq.getCenterY());
                g2d.drawLine(secondSq.getCenterX() - offsetWidth, secondSq.getCenterY(), thirdSq.getCenterX() + offsetWidth, thirdSq.getCenterY());
            }
                        
            // Diagonal line
            if (differenceX != 0 && differenceY != 0) {
                if (firstSq.getCenterX() < secondSq.getCenterX()) {
                    g2d.drawLine(firstSq.getCenterX() - offsetWidth, firstSq.getCenterY() - offsetHeight,
                            secondSq.getCenterX(), secondSq.getCenterY());
                    g2d.drawLine(secondSq.getCenterX(), secondSq.getCenterY(),
                            thirdSq.getCenterX()+ offsetWidth, thirdSq.getCenterY() + offsetHeight);   
                } else {
                    g2d.drawLine(firstSq.getCenterX() + offsetWidth, firstSq.getCenterY() - offsetHeight,
                            secondSq.getCenterX(), secondSq.getCenterY());
                    g2d.drawLine(secondSq.getCenterX(), secondSq.getCenterY(),
                            thirdSq.getCenterX() - offsetWidth, thirdSq.getCenterY() + offsetHeight);
                }
            }
        }
    }
    
    public void setWinningPoints(Square[] points) {
        System.arraycopy(points, 0, winningPoints, 0, points.length);
    }
    
    public void resetWinningPoints() {
        for (int i = 0; i < winningPoints.length; i++) {
            winningPoints[i] = null;
        }
    }
}