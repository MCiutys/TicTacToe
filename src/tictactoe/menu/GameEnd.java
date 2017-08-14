/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tictactoe.menu;

import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import tictactoe.game.Constants;
import tictactoe.game.ExitListener;
import tictactoe.game.GameLogic;
import tictactoe.game.Player;
import tictactoe.game.RestartButtonListener;

/**
 *
 * @author Mantas
 */
public class GameEnd extends JPanel {
    
    private static final String GAME_FINISHED = "Game is finished";
    private static final String SCORE_INFO = "Score is: ";
    private static final String PLAY_AGAIN = "PLAY AGAIN";
    private static final String EXIT_GAME = "EXIT GAME";
    
    
    private final JLabel gameFinished;
    private final JLabel scoreInfo;
    private final Button playAgain;
    private final Button exitGame;
    
    public GameEnd() {
        
        gameFinished = new JLabel();
        scoreInfo = new JLabel();
        playAgain = new Button(PLAY_AGAIN);
        exitGame = new Button(EXIT_GAME);
        setUp();
    }
    
    private void setUp() {
        setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
        add(gameFinished);
        gameFinished.setFont(Constants.INFO_FONT);
        add(scoreInfo);
        scoreInfo.setFont(Constants.INFO_FONT);
        add(playAgain);
        add(exitGame);
    }
    
    public void showScore(Player player1, Player player2) {
        gameFinished.setText(GAME_FINISHED);
        String finalScoreInfo = SCORE_INFO + player1.getName() + ": " + 
                player1.getScore() + ", " + player2.getName() + ": " +
                player2.getScore();
        scoreInfo.setText(finalScoreInfo);
    }
    
    public void addRestartListener(GameLogic logic) {
        playAgain.addActionListener(new RestartButtonListener(logic));
    }
    
    public void addExitListener() {
        exitGame.addActionListener(new ExitListener());
    }
    
}
