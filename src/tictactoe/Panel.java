/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tictactoe;

import java.awt.Dimension;
import java.awt.FlowLayout;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import static tictactoe.TicTacToe.HEIGHT;
import static tictactoe.TicTacToe.WIDTH;

/**
 *
 * @author Mantas
 */
public class Panel extends JPanel {
    
    private static final String TURN = " turn";
    private static final String SCORE = "Score: ";
    private static final String RESTART_GAME = "RESTART GAME";
    
    private JLabel turn;
    private JLabel score;
    private JButton restartButton;
    
    public Panel() {
        turn = new JLabel();
        score = new JLabel();
        restartButton = new JButton(RESTART_GAME);
        setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
        add(turn);
        add(score);
        add(restartButton);
    }
    
    public void setTurn(Player player) {
        turn.setText(player.getName() + TURN);
    }
    
    public void setScore(Player player1, Player player2) {
        score.setText(SCORE + player1.getName() + ": " + player1.getScore() + ", " +
                player2.getName() + ": " + player2.getScore());
    }
}
