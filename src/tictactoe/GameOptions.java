/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tictactoe;

import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;

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
    private JLabel turn;
    private JLabel playAgainst;
    private JLabel playerScore;
    private JLabel opponentScore;
    private JComboBox botLevels;
    private JButton restartGame;
    
    public GameOptions() {
        player1 = new JRadioButton("Player");
        player2 = new JRadioButton("Bot");
        turn = new JLabel();
        playerScore= new JLabel();
        opponentScore = new JLabel();
        playAgainst = new JLabel(PLAY_AGAINST);
        botLevels = new JComboBox(BOT_LEVELS);
        restartGame = new JButton();
        
//        setLayout(new BorderLayout());
//        add(player1, BorderLayout.PAGE_START);
//        add(player2, BorderLayout.PAGE_END);
//        add(turn, BorderLayout.CENTER);

        GroupLayout layout = new GroupLayout(this);
        setLayout(layout);
        
        layout.setAutoCreateGaps(true);
        layout.setAutoCreateContainerGaps(true);
        
        layout.setHorizontalGroup(
            layout.createSequentialGroup()
                .addComponent(playAgainst)
                .addComponent(player1)
                .addComponent(player2)
        );
        
        layout.setVerticalGroup(
            layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(playAgainst)
                    .addComponent(player1)
                    .addComponent(player2)
                )
        );
    }
    
    public void setTurnLabel(HumanPlayer player) {
        turn.setText(player.getName() + TURN);
    }
    
    
    
    
}
