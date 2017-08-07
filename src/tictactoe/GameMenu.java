/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tictactoe;

import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.Box;
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
    
    public static final String BOT = "Bot";
    public static final String HUMAN = "Player";

    
    private JLabel title;
    private GameMenuNameField gettingName;
    private GameMenuPlayAgainst playAgainst;
    private Button startGame;
    private GameMenuOpponentName humanOpponent;
    private GameMenuChooseLevel botOpponent;
    private GameMenuPlayTill playTill;
    private GameMenuSetFirst setFirst;
    CardLayout cardLayout;
    
    private JPanel cards;
    
    public GameMenu() {
        title = new JLabel(TITLE);
        title.setFont(new Font("Times New Roman", Font.BOLD, FONT_SIZE));
        
        gettingName = new GameMenuNameField();
        playAgainst = new GameMenuPlayAgainst();
        startGame = new Button(START_GAME);
        startGame.setFont(NAME);
        humanOpponent = new GameMenuOpponentName();
        botOpponent = new GameMenuChooseLevel();
        playTill = new GameMenuPlayTill();
        setFirst = new GameMenuSetFirst();
        
        cardLayout = new CardLayout();
        cards = new JPanel(cardLayout);
        cards.add(humanOpponent, HUMAN);
        cards.add(botOpponent, BOT);
                
        playAgainst.getHumanButton().addActionListener(choosePlayer);
        playAgainst.getBotButton().addActionListener(choosePlayer);
    }
    
    ActionListener choosePlayer = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            if (playAgainst.isHumanPlayerTicked()) {
                cardLayout.show(cards, HUMAN);
            } else {
                cardLayout.show(cards, BOT);
            }
        }
    };
    
    public Box setUpMenu() {
        Box alignPanels = new Box(BoxLayout.Y_AXIS);
        alignPanels.setPreferredSize(new Dimension(Constants.WIDTH, Constants.HEIGHT));
        alignPanels.add(title);
        alignPanels.add(gettingName);
        alignPanels.add(playAgainst);
        alignPanels.add(cards);
        alignPanels.add(playTill);
        alignPanels.add(setFirst);
        alignPanels.add(startGame);
        alignPanels.setVisible(true);
        return alignPanels;
    }
    
    public void passVariables(GameLogic logic) {
        // Passing first player's name
        logic.getPlayer1().setName(gettingName.getEnteredName());
        
        
        // Passing opponent of the player
//        if (playAgainst.isHumanPlayerTicked()) {
//            logic.setPlayer2(HUMAN, null);
//            
//            // Passing second player's name
//            logic.getPlayer2().setName(humanOpponent.getOpponentName());
//        } else {
//            logic.setPlayer2(BOT, botOpponent.giveLevel().getSelectedItem().toString());
//        }

        // Who starts the game
        logic.setFirstTurn(setFirst.isFirstPlayerTurn());
        
        // Wins limit
        logic.setMaxResult(playTill.getMaxWins());
    }
    
    public Player passPlayer(Board board) {
        Player player;
        
        // Passing opponent of the player
        if (playAgainst.isHumanPlayerTicked()) {
            player = new HumanPlayer();
            player.setName(humanOpponent.getOpponentName());
        } else {
            String botLevel = botOpponent.giveLevel().getSelectedItem().toString();
            player = new Bot(board, botLevel, !setFirst.isFirstPlayerTurn());
        }
        
        return player;
    }
    
    public JButton getStartButton() {
        return startGame;
    }
    
    public GameMenuSetFirst setFirst() {
        return setFirst;
    }
}
