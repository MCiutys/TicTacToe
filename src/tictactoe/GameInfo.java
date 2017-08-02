/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tictactoe;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import javax.swing.JPanel;

/**
 *
 * @author Mantas
 */
public class GameInfo extends JPanel {
    
    private static final String TURN = " turn";
    private static final String PLAY_AGAINST = "Play against: ";
    private static final String[] BOT_LEVELS = {"Easy", "Random", "Impossible to beat"};
    
    private Panel panel;
    
    public GameInfo() {
        
          this.setPreferredSize(new Dimension((int) (Constants.WIDTH * 0.3), Constants.HEIGHT));
          this.setLayout(new GridBagLayout());
          GridBagConstraints gbc = new GridBagConstraints();
            
          panel = new Panel();
          this.add(panel, gbc);

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
}
