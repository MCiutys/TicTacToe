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
public class RestartButtonListener implements ActionListener {
    
    private final GameLogic gameLogic;
    
    public RestartButtonListener(GameLogic logic) {
        gameLogic = logic;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        gameLogic.removeSquareListeners();
        gameLogic.restartGame();
    }
    
}
