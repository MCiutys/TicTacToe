/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tictactoe;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import javax.swing.JFrame;
import static tictactoe.TicTacToe.HEIGHT;
import static tictactoe.TicTacToe.WIDTH;

/**
 *
 * @author Mantas
 */
public class GameLogic {
    
    private Board board;
    private GameOptions options;
    private Player player1;
    private Player player2;
    
    public GameLogic() {
        board = new Board();
        board.setPreferredSize(new Dimension((int) (WIDTH * 0.7), HEIGHT));
        board.setLayout(new GridLayout(3, 3, 30, 30));
        board.addButtons();
        
        options = new GameOptions();
        options.setPreferredSize(new Dimension((int) (WIDTH * 0.3), HEIGHT));
        
        JFrame frame = new JFrame("Tic Tac Toe");
        frame.setLayout(new FlowLayout());
        frame.add(board);
        frame.add(options);
        frame.pack();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
        
        player1 = new Player("Mantas");
        player2 = new Player("Jonas");
    }
    
    public void executeGame() {
        Player whoseTurn = player1;
        player1.setColor(Color.RED);
        player2.setColor(Color.BLUE);
        
        board.setWhoseTurn(player1);
        board.setNotWhoseTurn(player2);
        int i = 0;
        while(i < 1000000) {
            board.setColor(whoseTurn.getColor());
            i++;
        }
    }
    
}
