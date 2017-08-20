package tictactoe.game;

import java.awt.BasicStroke;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.util.ArrayList;
import javax.swing.JPanel;

/**
 * This class represents the board of the game. It contains 9 squares, 3x3.
 * Furthermore, it has an array of squares that represents the winning set of
 * squares.
 *
 * @author Mantas
 */
public class Board extends JPanel {

    private final Square[] squares;
    private final Square[] winningSquares;

    /**
     * Constructor for creating the board of the game. Creates an array of
     * squares, array of winning squares and adds buttons to 9 squares.
     */
    public Board() {
        squares = new Square[Constants.HORIZONTAL_SQUARES * Constants.VERTICAL_SQUARES];
        winningSquares = new Square[Constants.WINNING_SQUARES];
        addButtons();
    }

    /**
     * Method creates new squares and adds buttons to this Board panel
     */
    private void addButtons() {
        for (int i = 0; i < squares.length; i++) {
            Square square = new Square(i);
            add(square.getButton());
            squares[i] = square;
        }
    }

    /**
     * Method clears the board, so that players could start a new game
     */
    protected void clearBoard() {
        for (Square square : squares) {
            square.clearSquare();
        }
        resetWinningSquares();
    }

    /**
     *
     * @return the squares of the board
     */
    protected Square[] getSquares() {
        return squares;
    }

    /**
     * Method takes a square as a parameter and returns exactly the same square
     * (with the same index) from the board.If no such square exists, return null.
     * @param sq of the class Square
     * @return the square from the board that is same as passed square.
     * If such square does not exist, return null.
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
     * Method that gives an arraylist of squares that are still not occupied by
     * any of the players
     * @return squares that are not clicked.
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
     * Method that provides an arraylist of squares that are marked X
     * @return squares marked with X
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
     * Method that provides an arraylist of squares that are marked O
     * @return squares marked with O
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

    /**
     * Method that draws a line whenever the game is finished and a winner exists.
     * The line simply crosses three squares, either horizontally, vertically, or
     * diagonally.
     * @param g2d
     */
    public void drawWinningLine(Graphics2D g2d) {

        g2d.setStroke(new BasicStroke(Constants.STROKE_WIDTH * 2));

        if (winningSquares.length == 3 && winningSquares[0] != null) {
            Square firstSq = getSameFromBoard(winningSquares[0]);
            Square secondSq = getSameFromBoard(winningSquares[1]);
            Square thirdSq = getSameFromBoard(winningSquares[2]);

            int offsetWidth = firstSq.getButton().getWidth() / 2;
            int offsetHeight = firstSq.getButton().getHeight() / 2;

            int differenceX = Math.abs(firstSq.getCenterX() - secondSq.getCenterX());
            int differenceY = Math.abs(firstSq.getCenterY() - secondSq.getCenterY());

            // Vertical line
            if (differenceX == 0) {
                g2d.drawLine(firstSq.getCenterX(), firstSq.getCenterY() - offsetHeight, secondSq.getCenterX(), secondSq.getCenterY() + offsetHeight);
                g2d.drawLine(secondSq.getCenterX(), secondSq.getCenterY() - offsetHeight, thirdSq.getCenterX(), thirdSq.getCenterY() + offsetHeight);
            }

            // Horizontal line
            if (differenceY == 0) {
                g2d.drawLine(firstSq.getCenterX() - offsetWidth, firstSq.getCenterY(), secondSq.getCenterX() + offsetWidth, secondSq.getCenterY());
                g2d.drawLine(secondSq.getCenterX() - offsetWidth, secondSq.getCenterY(), thirdSq.getCenterX() + offsetWidth, thirdSq.getCenterY());
            }

            // Diagonal line
            if (differenceX != 0 && differenceY != 0) {
                if (firstSq.getCenterX() < secondSq.getCenterX()) {
                    g2d.drawLine(firstSq.getCenterX() - offsetWidth, firstSq.getCenterY() - offsetHeight,
                            secondSq.getCenterX(), secondSq.getCenterY());
                    g2d.drawLine(secondSq.getCenterX(), secondSq.getCenterY(),
                            thirdSq.getCenterX() + offsetWidth, thirdSq.getCenterY() + offsetHeight);
                } else {
                    g2d.drawLine(firstSq.getCenterX() + offsetWidth, firstSq.getCenterY() - offsetHeight,
                            secondSq.getCenterX(), secondSq.getCenterY());
                    g2d.drawLine(secondSq.getCenterX(), secondSq.getCenterY(),
                            thirdSq.getCenterX() - offsetWidth, thirdSq.getCenterY() + offsetHeight);
                }
            }
        }
    }

    /**
     * Method that sets the array of winning squares from null to given squares
     * @param squares
     */
    protected void setWinningSquares(Square[] squares) {
        System.arraycopy(squares, 0, winningSquares, 0, squares.length);
    }

    /**
     * Method that resets the winning squares and makes them equal to null.
     */
    private void resetWinningSquares() {
        for (int i = 0; i < winningSquares.length; i++) {
            winningSquares[i] = null;
        }
    }
}
