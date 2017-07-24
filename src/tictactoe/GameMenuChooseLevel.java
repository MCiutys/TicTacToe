/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tictactoe;

import java.awt.Font;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 * @author Mantas
 */
public class GameMenuChooseLevel extends JPanel {
    
    
    private static final String CHOOSE_LEVEL = "Choose level: ";
    private static final String[] LEVELS = {"Random", "Easy", "Impossible"};
    
    private JLabel chooseLevel;
    private JComboBox levels;
    private final Font NAME = new Font("Times New Roman", Font.BOLD, 32);

    public GameMenuChooseLevel() {
        chooseLevel = new JLabel(CHOOSE_LEVEL);
        levels = new JComboBox(LEVELS);
        chooseLevel.setFont(NAME);
        levels.setFont(NAME);
        add(chooseLevel);
        add(levels);
    }
}
