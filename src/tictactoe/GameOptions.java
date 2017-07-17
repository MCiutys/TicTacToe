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
public class GameOptions extends JPanel {
    
    private static final String TURN = " turn";
    private static final String PLAY_AGAINST = "Play against: ";
    private static final String[] BOT_LEVELS = {"Easy", "Random", "Impossible to beat"};

    
    private JRadioButton player1;
    private JRadioButton player2;
    private JLabel playAgainst;
    private JLabel playerScore;
    private JLabel opponentScore;
    private JComboBox botLevels;
    
    private Panel panel;
    
    public GameOptions() {
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
          

//        setLayout(new BorderLayout());
//        add(turn, BorderLayout.CENTER);
//        setPreferredSize(new Dimension((int) (WIDTH * 0.3), HEIGHT));
//        setLayout(new BorderLayout());
                

//        GroupLayout layout = new GroupLayout(this);
//        setLayout(layout);
//        
//        layout.setAutoCreateGaps(true);
//        layout.setAutoCreateContainerGaps(true);
//        
//        layout.setHorizontalGroup(
//            layout.createSequentialGroup()
//                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
//                    .addComponent(playAgainst)
////                    .addComponent(botLevels)
//                    .addComponent(restartGame)
//                )
//                .addComponent(player1)
//                .addComponent(player2)
//        );
//        
//        layout.setVerticalGroup(
//            layout.createSequentialGroup()
//                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
//                        .addComponent(playAgainst)
//                        .addComponent(player1)
//                        .addComponent(player2)
//                )
////                .addComponent(botLevels)
//                .addComponent(restartGame)
//        );
    }
    
    public void setTurnLabel(Player player) {
        panel.setTurn(player);
    }
    
    public void setScores(Player player1, Player player2) {
        panel.setScore(player1, player2);
    }
}
