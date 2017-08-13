package tictactoe.game;

import tictactoe.game.Constants;
import tictactoe.game.Square;
import tictactoe.game.TicTacToe;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.ArrayList;
import javax.swing.JPanel;

/**
 *
 * @author Mantas
 */
public class Board extends JPanel {
    
    private final Square[] squares;
    
    /**
     *
     */
    public Board() {
        squares = new Square[Constants.HORIZONTAL_SQUARES * Constants.VERTICAL_SQUARES];
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
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
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
        
    }
}