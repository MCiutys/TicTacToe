/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tictactoe;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;

/**
 *
 * @author Mantas
 */
public class TicTacToe {
    
    public static final int HEIGHT = 600;
    public static final int WIDTH = 1024;

    /**
     * @param args the command line arguments
     */

    public static void main(String[] args) {
        // TODO code application logic here
        
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                Board board = new Board();
                board.setPreferredSize(new Dimension((int) (WIDTH * 0.7), HEIGHT));
                board.setLayout(new GridLayout(3, 3, 30, 30));
                board.addButtons();
        
                GameOptions opt = new GameOptions();
                opt.setPreferredSize(new Dimension((int) (WIDTH * 0.3), HEIGHT));
        
        
                JFrame frame = new JFrame("Tic Tac Toe");
                frame.setLayout(new FlowLayout());
                frame.add(board);
                frame.add(opt);
                frame.pack();
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.setVisible(true);
            }
        }); 
    }
}
