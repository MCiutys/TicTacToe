/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tictactoe.menu;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 *
 * @author Mantas
 */
public class GameMenuOpponentName extends JPanel {
    
    private static final String ENTER_NAME = "Enter opponent's name: ";
    private final Font NAME = new Font("Times New Roman", Font.BOLD, 32);

    
    private JLabel enterName;
    private JTextField nameField;
    
    public GameMenuOpponentName() {
        enterName = new JLabel(ENTER_NAME);
        nameField = new JTextField(20);
        enterName.setFont(NAME);
        nameField.setFont(NAME);
        setLayout(new BorderLayout());
        add(enterName, BorderLayout.WEST);
        add(nameField, BorderLayout.EAST);
    }
    
    public String getOpponentName() {
        return nameField.getText();
    }
    
    public boolean isFieldEmpty() {
        return nameField.getText().isEmpty();
    }
    
}
