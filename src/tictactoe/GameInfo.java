/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tictactoe;

import java.awt.BorderLayout;
import java.awt.Dimension;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import static tictactoe.TicTacToe.HEIGHT;
import static tictactoe.TicTacToe.WIDTH;

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
//        player1 = new JRadioButton("Player");
//        player2 = new JRadioButton("Bot");
//        playerScore= new JLabel("Score: ");
//        opponentScore = new JLabel();
//        playAgainst = new JLabel(PLAY_AGAINST);
//        botLevels = new JComboBox(BOT_LEVELS);
        
          this.setPreferredSize(new Dimension((int) (Constants.WIDTH * 0.3), Constants.HEIGHT));
          this.setLayout(new BorderLayout());
            
          panel = new Panel();
          panel.setPreferredSize(new Dimension((int) (Constants.WIDTH * 0.3), 100));
          this.add(panel, BorderLayout.CENTER);

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
