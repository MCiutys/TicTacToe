/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tictactoe;

import java.awt.CardLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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
    private final Font NAME = new Font("Times New Roman", Font.BOLD, 32);

    
    private JLabel title;
    private GameMenuNameField gettingName;
    private GameMenuPlayAgainst playAgainst;
    private JButton startGame;
    private GameMenuOpponentName humanOpponent;
    private GameMenuChooseLevel botOpponent;
    CardLayout cardLayout;
    
    private JPanel cards;
    
    public GameMenu() {
        title = new JLabel(TITLE);
        title.setFont(new Font("Times New Roman", Font.BOLD, FONT_SIZE));
        
        gettingName = new GameMenuNameField();
        playAgainst = new GameMenuPlayAgainst();
        startGame = new JButton(START_GAME);
        startGame.setFont(NAME);
        humanOpponent = new GameMenuOpponentName();
        botOpponent = new GameMenuChooseLevel();
        
        cardLayout = new CardLayout();
        cards = new JPanel(cardLayout);
        cards.add(humanOpponent);
        cards.add(botOpponent);
                
        playAgainst.getHumanButton().addActionListener(choosePlayer);
        playAgainst.getBotButton().addActionListener(choosePlayer);
    }
    
    ActionListener choosePlayer = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            if (playAgainst.isHumanPlayerTicked()) {
                cardLayout.next(cards);
            } else {
                cardLayout.next(cards);
            }
        }
    };
    
    public void setUpMenu() {
        setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
        setPreferredSize(new Dimension(Constants.WIDTH, Constants.HEIGHT));
        add(title);
        add(gettingName);
        add(playAgainst);
        add(cards);
        add(startGame);
    }
    
    public void passVariables(GameLogic logic) {
        // Passing first player's name
        logic.getPlayer1().setName(gettingName.getEnteredName());
        
        // Passing second player's name
        logic.getPlayer2().setName(humanOpponent.getOpponentName());
    }
    
    public JButton getStartButton() {
        return startGame;
    }
}
