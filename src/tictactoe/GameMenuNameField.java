/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tictactoe;

import java.awt.Font;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 *
 * @author Mantas
 */
public class GameMenuNameField extends JPanel {
    
    private static final String ENTER_NAME = "Enter your name: ";
    private final Font NAME = new Font("Times New Roman", Font.BOLD, 32);
    
    private JLabel enterName;
    private JTextField nameField;
    
    public GameMenuNameField() {
        enterName = new JLabel(ENTER_NAME);
        nameField = new JTextField();
        enterName.setFont(NAME);
        nameField.setFont(NAME);
        nameField.setColumns(20);
        add(enterName);
        add(nameField);
    }
    
    public String getEnteredName() {
        return nameField.getText();
    }
}
