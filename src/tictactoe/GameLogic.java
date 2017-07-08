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
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Collections;
import javax.swing.JFrame;
import javax.swing.JToggleButton;
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
    private Player whoseTurn;
    
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
        player1.setToDrawX();
        player2.setToDrawO();
        whoseTurn = player1;
        options.setTurnLabel(whoseTurn);
    }
    
    public void addListeners() {
        for (int i = 0; i < 9; i++) {
            Square square = board.getSquares()[i];
            JToggleButton button = square.getButton();
            button.addActionListener(new ActionListener() {
                
                @Override
                public void actionPerformed(ActionEvent e) {
                    
                    square.setClicked();
                    square.setColor(whoseTurn.getColor());
                    
                    whoseTurn.addToMarked(square);
                    Collections.sort(whoseTurn.getMarked());
                    System.out.println(GameLogic.this.didPlayerWin(whoseTurn));
                    
                    if (square.getClicked()) {
                        button.setIcon(whoseTurn.draw());
                    } else {
                        button.setIcon(null);
                    }
                    
                    if (whoseTurn == player1) {
                        whoseTurn = player2;
                    } else {
                        whoseTurn = player1;
                    }
                    
                    options.setTurnLabel(whoseTurn);
                }
                
            });
        }
    }
    
    public boolean didPlayerWin(Player player) {
        
        Square[] set1 = {new Square(0), new Square(1), new Square(2)};   
        Square[] set2 = {new Square(3), new Square(4), new Square(5)};
        Square[] set3 = {new Square(6), new Square(7), new Square(8)};
        Square[] set4 = {new Square(0), new Square(3), new Square(6)};
        Square[] set5 = {new Square(1), new Square(4), new Square(7)};
        Square[] set6 = {new Square(2), new Square(5), new Square(8)};
        Square[] set7 = {new Square(0), new Square(4), new Square(8)};
        Square[] set8 = {new Square(2), new Square(4), new Square(6)};
        
        Square[][] winningSets = {set1, set2, set3, set4, set5, set6, set7, set8};
        
        
        int counter = 0;
        outer : for (int k = 0; k < winningSets.length; k++) {
            counter = 0;
            for (int i = 0; i < winningSets[k].length; i++) {
                for (int j = 0; j < whoseTurn.getMarked().size(); j++) {
                    if (winningSets[k][i].compareTo(whoseTurn.getMarked().get(j)) == 0) {
                        System.out.println(winningSets[k][i]);
                        counter++;
                        System.out.println(counter);
                    }
                    
                    if (counter == 3) break outer;
                }
            }    
        }
        
        
        if (counter == 3) {
            System.out.println("GOT IT!");
            return true;   
        } else {
            return false;
        }
    }
    
    public void executeGame() {
        player1.setColor(Color.RED);
        player2.setColor(Color.BLUE);
        
        board.setWhoseTurn(player1);
        addListeners();
    }
    
}
