/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tictactoe;

import java.awt.CardLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

/**
 *
 * @author Mantas
 */
public class TicTacToe {
    
    public static final int HEIGHT = 600;
    public static final int WIDTH = 1024;
    
    CardLayout layout;
    JPanel panels;
    GameMenu menu;
    GameLogic game;

    /**
     * @param args the command line arguments
     */
    
    public TicTacToe() {
        layout = new CardLayout();
        game = new GameLogic();
        game.executeGame();
                
        menu = new GameMenu();
        menu.setUpMenu();
        menu.getStartButton().addActionListener(startButton);
                
        panels = new JPanel(layout);
        panels.add(menu);
        panels.add(game);
        
        JFrame frame = new JFrame("Tic Tac Toe");
        frame.setLayout(new FlowLayout());
        frame.add(panels);
        frame.pack();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.setVisible(true);
    }
    
    ActionListener startButton = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            layout.next(panels);
            menu.passVariables(game);
            game.updateGameInfo();
        }
        
    };

    public static void main(String[] args) {
        // TODO code application logic here
        
        
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {                  
                TicTacToe ticTacToe = new TicTacToe();
            }
        }); 
    }
}
