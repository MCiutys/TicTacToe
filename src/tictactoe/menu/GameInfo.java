/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tictactoe.menu;

import java.awt.CardLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import javax.swing.JPanel;
import tictactoe.game.Player;

/**
 *
 * @author Mantas
 */
public class GameInfo extends JPanel {
    
    private static final String GAME_INFO = "Game Info";
    private static final String GAME_END = "Game End";
    
    private Panel panel;
    private GameEnd gameEnd;
    
    private CardLayout cardLayout;
    private JPanel cards;
    
    public GameInfo() {            
         
          setUp();
    }
    
    private void setUp() {
        panel = new Panel();
          setLayout(new GridBagLayout());
          GridBagConstraints gbc = new GridBagConstraints();
          
          gameEnd = new GameEnd();
          
          cardLayout = new CardLayout();
          cards = new JPanel(cardLayout);
          cards.add(panel, GAME_INFO);
          cards.add(gameEnd, GAME_END);
          cardLayout.show(cards, GAME_INFO);
          
          this.add(cards, gbc);
    }
    
    public void showGameEnd(Player player1, Player player2) {
        gameEnd.showScore(player1, player2);
        cardLayout.show(cards, GAME_END);
    }
    
    public void setTurnLabel(Player player) {
        panel.setTurn(player);
    }
    
    public void setScores(Player player1, Player player2) {
        panel.setScore(player1, player2);
    }
    
    public Panel getPanel() {
        return panel;
    }
    
    public GameEnd getGameEnd() {
        return gameEnd;
    }
}
