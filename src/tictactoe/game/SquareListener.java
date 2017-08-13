/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tictactoe.game;

import tictactoe.menu.GameInfo;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 *
 * @author Mantas
 */
public class SquareListener implements ActionListener {
    
    private final Square square;
    private final Player whoseTurn;
    private final Player player1;
    private final Player player2;
    private final GameLogic gameLogic;
    private final GameInfo options;
    
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
        gameLogic.playerMove(square);
        options.setScores(player1, player2);
        if (!gameLogic.getBoard().getFreeSquares().isEmpty()) {
            gameLogic.botMove(square);   
        }
    }
}
