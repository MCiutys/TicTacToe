/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tictactoe;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 * @author Mantas
 */
public class GameMenu extends JPanel {
    
    private static final String TITLE = "Tic Tac Toe";
    private static final String START_GAME = "START GAME";
    private static final int FONT_SIZE = 48;
    
    private JLabel title;
    private GameMenuNameField gettingName;
    private GameMenuPlayAgainst playAgainst;
    private JButton startGame;
    
    public GameMenu() {
        title = new JLabel(TITLE);
        title.setFont(new Font("Times New Roman", Font.BOLD, FONT_SIZE));
        
        gettingName = new GameMenuNameField();
        playAgainst = new GameMenuPlayAgainst();
        startGame = new JButton(START_GAME);
        
    }
    
    public void setUpMenu() {
        setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
        setPreferredSize(new Dimension(Constants.WIDTH, Constants.HEIGHT));
        add(title);
        add(gettingName);
        add(playAgainst);
        
        
        if (playAgainst.isHumanPlayerTicked()) {
            GameMenuOpponentName additional = new GameMenuOpponentName();
            add(additional);
        } else {
            GameMenuChooseLevel additional = new GameMenuChooseLevel();
            add(additional);
        }
        
        add(startGame);
    }
}
