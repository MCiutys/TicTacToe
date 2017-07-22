/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tictactoe;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import javax.swing.JButton;
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
//                GameLogic game = new GameLogic();
//                game.executeGame();
                  GameMenu menu = new GameMenu();
                  menu.setUpMenu();
                  
                  JFrame frame = new JFrame("Tic Tac Toe");
        frame.setLayout(new FlowLayout());
        frame.add(menu);
        frame.pack();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.setVisible(true);
            }
        }); 
    }
}
