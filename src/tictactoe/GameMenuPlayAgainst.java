/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tictactoe;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Font;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;

/**
 *
 * @author Mantas
 */
public class GameMenuPlayAgainst extends JPanel {
    
    private static final String PLAY_AGAINST = "Play against: ";
    private static final String HUMAN = "Human";
    private static final String BOT = "Computer";
    private final Font NAME = new Font("Times New Roman", Font.BOLD, 32);

    
    private JLabel playAgainst;
    private JRadioButton humanPlayer;
    private JRadioButton bot;
    
    public GameMenuPlayAgainst() {
        playAgainst = new JLabel(PLAY_AGAINST);
        humanPlayer = new JRadioButton(HUMAN, true);
        bot = new JRadioButton(BOT, false);
        playAgainst.setFont(NAME);
        humanPlayer.setFont(NAME);
        bot.setFont(NAME);
        setLayout(new BorderLayout());
        
        add(playAgainst, BorderLayout.WEST);
        
        JPanel choices = new JPanel(new BorderLayout());
        choices.add(humanPlayer, BorderLayout.WEST);
        choices.add(bot, BorderLayout.EAST);
        add(choices, BorderLayout.EAST);
//        add(humanPlayer, BorderLayout.EAST);
//        add(bot, BorderLayout.EAST);
        
        // Creating button group
        ButtonGroup group = new ButtonGroup();
        group.add(humanPlayer);
        group.add(bot);
    }
    
    public boolean isHumanPlayerTicked() {
        return humanPlayer.isSelected();
    }
    
    public JRadioButton getHumanButton() {
        return humanPlayer;
    }
    
    public JRadioButton getBotButton() {
        return bot;
    }
}