/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tictactoe;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 *
 * @author Mantas
 */
public class GameMenuNameField extends JPanel {
    
    private static final String ENTER_NAME = "Enter your name: ";
    
    private JLabel enterName;
    private JTextField nameField;
    
    public GameMenuNameField() {
        enterName = new JLabel(ENTER_NAME);
        nameField = new JTextField();
        nameField.setColumns(20);
        add(enterName);
        add(nameField);
    }
}
