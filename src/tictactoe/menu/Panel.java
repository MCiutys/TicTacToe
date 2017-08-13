/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tictactoe.menu;

import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import tictactoe.game.ContinueGameListener;
import tictactoe.game.GameLogic;
import tictactoe.game.Player;
import tictactoe.game.RestartButtonListener;

/**
 *
 * @author Mantas
 */
public class Panel extends JPanel {
    
    private static final String TURN = " turn";
    private static final String SCORE = "Score: ";
    private static final String RESTART_GAME = "RESTART GAME";
    private static final String CONTINUE_PLAYING = "CONTINUE GAME";
    private static final String WINNER_OF_GAME = "Winner is: ";
    private static final String DRAW_GAME = "Draw";
    
    private static final Font FONT = new Font("Times New Roman", Font.BOLD, 20);
    
    private JLabel turn;
    private JLabel score;
    private JLabel winner;
    private JLabel gameFinished;
    private Button restartButton;
    private Button continueButton;
    
    public Panel() {
        turn = new JLabel();
        turn.setFont(FONT);
        score = new JLabel();
        score.setFont(FONT);
        gameFinished = new JLabel();
        gameFinished.setFont(FONT);
        restartButton = new Button(RESTART_GAME);
        continueButton = new Button(CONTINUE_PLAYING);
        winner = new JLabel(WINNER_OF_GAME);
        winner.setFont(FONT);
        
        setUp();
        
        continueButton.setVisible(false);
        winner.setVisible(false);
    }
    
    private void setUp() {
        setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
        add(turn);
        add(score);
        add(restartButton);
        add(winner);
        add(continueButton);
    }
    
    public void setTurn(Player player) {
        turn.setText(player.getName() + TURN);
    }
    
    public void setScore(Player player1, Player player2) {
        score.setText(SCORE + player1.getName() + ": " + player1.getScore() + ", " +
                player2.getName() + ": " + player2.getScore());
    }
    
    public void addRestartListener(GameLogic logic) {
        restartButton.addActionListener(new RestartButtonListener(logic));
    }
    
    public void addContinueListener(GameLogic logic) {
        continueButton.addActionListener(new ContinueGameListener(logic));
    }
    
    public void displayWinner(Player player) {
        winner.setText(WINNER_OF_GAME + player.getName());
        winner.setVisible(true);
        continueButton.setVisible(true);
    }
    
    public void displayDraw() {
        winner.setText(DRAW_GAME);
        winner.setVisible(true);
        continueButton.setVisible(true);
    }
    
    public void doNotDisplayWinner() {
        winner.setVisible(false);
        continueButton.setVisible(false);
    }
    
    public void gameFinished() {
        gameFinished.setText("Game is finished");
    }
}
