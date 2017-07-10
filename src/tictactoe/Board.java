/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tictactoe;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
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
        
    }
    
    public void addButtons() {
        squares = new Square[9];
        
        for (int i = 0; i < 9   ; i++) {
                Square square = new Square(i);
                this.add(square.getButton());
                squares[i] = square;
        }
    }
    
    public Square[] getSquares() {
        return squares;
    }
    
    public void setWhoseTurn(HumanPlayer p) {
        for (int i = 0; i < squares.length; i++) {
            squares[i].setWhoseTurn(p);
        }
    }
    
    public void setNotWhoseTurn(HumanPlayer p) {
        for (int i = 0; i < squares.length; i++) {
            squares[i].setNotWhoseTurn(p);
        }
    }
    
    public void setColor(Color c) {
        for (int i = 0; i < squares.length; i++) {
            squares[i].setColor(c);
        }
    }
    
//    public void addListener() {
//        for (Square square : squares) {
//            square.getButton().addActionListener(new ActionListener() {
//                @Override
//                public void actionPerformed(ActionEvent e) {
//                    
//                }
//                
//            });
//        }
//    }
    
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