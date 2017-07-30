/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tictactoe;

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
    
    private static final int BOARD_WIDTH = (int) (TicTacToe.WIDTH * 0.7);
    private static final int VERTICAL_LINE_LENGTH = (int) (TicTacToe.WIDTH * 0.5);
    private static final int HORIZONTAL_LINE_LENGTH = (int) (TicTacToe.HEIGHT * 0.8);
    private static final int STROKE_WIDTH = 5;
    
    private Square[] squares;
    
    public Board() {
        squares = new Square[Constants.HORIZONTAL_SQUARES * Constants.VERTICAL_SQUARES];
        addButtons();
    }
    
    public void addButtons() {
        for (int i = 0; i < Constants.HORIZONTAL_SQUARES * Constants.VERTICAL_SQUARES; i++) {
                Square square = new Square(i);
                this.add(square.getButton());
                squares[i] = square;
        }
    }
    
    public void clearBoard() {
        for (Square square : squares) {
            square.clearSquare();
        }
    }
    
    public Square[] getSquares() {
        return squares;
    }
    
    public Square getSameFromBoard(Square sq) {
        for (Square s : squares) {
            if (s.compareTo(sq) == 0) {
                return s;
            }
        }
        return null;
    }
    
    public boolean doesInclude(Square s) {
        for (Square sq : squares) {
            if (sq == s) {
                return true;
            }
        }
        return false;
    }
    
    public ArrayList<Square> getFreeSquares() {
        ArrayList<Square> freeSquares = new ArrayList<>();
        for (Square square : squares) {
            if (!square.getClicked()) {
                freeSquares.add(square);
            }

        }
        return freeSquares;
    }
    
    public ArrayList<Square> getAllXs() {
        ArrayList<Square> xs = new ArrayList<>();
        for (Square square : squares) {
            if (square.isDrawnX()) {
                xs.add(square);
            }
        }
        return xs;
    }
    
    public ArrayList<Square> getAllOs() {
        ArrayList<Square> os = new ArrayList<>();
        for (Square square : squares) {
            if (square.isDrawnX()) {
                os.add(square);
            }
        }
        return os;
    }
    
//    public void setWhoseTurn(HumanPlayer p) {
//        for (Square square : squares) {
//            square.setWhoseTurn(p);
//        }
//    }
    
    public void setColor(Color c) {
        for (Square square : squares) {
            square.setColor(c);
        }
    }
    
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        g2d.setStroke(new BasicStroke(STROKE_WIDTH));
        // Horiztontal lines
        g2d.drawLine(0, TicTacToe.HEIGHT / 3,
                BOARD_WIDTH, TicTacToe.HEIGHT / 3);
        g2d.drawLine(0, 2 * TicTacToe.HEIGHT / 3,
                BOARD_WIDTH, 2 * TicTacToe.HEIGHT / 3);
        // Vertical lines
        g2d.drawLine(BOARD_WIDTH / 3, 0, BOARD_WIDTH / 3,
                TicTacToe.HEIGHT);
        g2d.drawLine(2 * BOARD_WIDTH / 3, 0, 2 * BOARD_WIDTH / 3,
                TicTacToe.HEIGHT);
        
    }
}