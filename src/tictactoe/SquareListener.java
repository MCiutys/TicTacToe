/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tictactoe;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 *
 * @author Mantas
 */
public class SquareListener implements ActionListener {
    
    private Square square;
    private Player whoseTurn;
    private Player player1;
    private Player player2;
    private GameLogic gameLogic;
    private GameInfo options;
    
    public SquareListener(Square square, Player whoseTurn, Player player1, Player player2,
            GameLogic gameLogic, GameInfo options) {
        this.square = square;
        this.whoseTurn = whoseTurn;
        this.player1 = player1;
        this.player2 = player2;
        this.gameLogic = gameLogic;
        this.options = options;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        System.out.println("PRESSED");
        gameLogic.playerMove(square);
        options.setScores(player1, player2);
        gameLogic.botMove();
    }
    
}
