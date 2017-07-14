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
    private HumanPlayer player1;
    private Bot player2;
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
        
        player1 = new HumanPlayer("Mantas");
        player2 = new Bot("Jonas");
        player1.setToDrawX();
        player2.setToDrawO();
        whoseTurn = player1;
        options.setTurnLabel(whoseTurn);
    }
    
    public void addListeners() {
        for (int i = 0; i < Constants.HORIZONTAL_SQUARES * Constants.VERTICAL_SQUARES; i++) {
            Square square = board.getSquares()[i];
            JToggleButton button = square.getButton();
            button.addActionListener(new ActionListener() {
                
                @Override
                public void actionPerformed(ActionEvent e) {
                    playerMove(whoseTurn, square);
                    options.setTurnLabel(whoseTurn);
                    botMove(whoseTurn);
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
        outer : for (Square[] winningSet : winningSets) {
            counter = 0;
            for (Square square : winningSet) {
                for (int j = 0; j < whoseTurn.getMarked().size(); j++) {
                    if (square.compareTo(whoseTurn.getMarked().get(j)) == 0) {
                        counter++;
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
    
    public void botMove(Player b) {
        Square mark;
        if (b.getClass().getName().equals(Bot.class.getName())) {
            Bot bot = (Bot) b;
            mark = bot.mark(board);
            playerMove(bot, mark);
        }
    }
    
    public void playerMove(Player player, Square mark) {
        
        mark.setClicked();
        mark.setColor(player.getColor());
                    
        player.addToMarked(mark);
        Collections.sort(player.getMarked());
        didPlayerWin(player);
                    
                    
        if (mark.getClicked()) {
            mark.getButton().setIcon(player.draw());
        } 
        
        if (isWinner()) {
            System.out.println("WINNER IS HERE!");
        }
        
        if (player == player1) {
            whoseTurn = player2;
        } else {
            whoseTurn = player1;
        }
    }
    
    public boolean isWinner() {
        boolean isWinner = false;
        if (didPlayerWin(player1) || didPlayerWin(player2)) {
            isWinner = true;
        }
        return isWinner;
    }
    
}
