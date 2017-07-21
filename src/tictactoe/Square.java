/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tictactoe;

import java.awt.Color;
import javax.swing.JToggleButton;

/**
 *
 * @author Mantas
 */

public class Square implements Comparable<Square> {
    
    private final int index;
    private JToggleButton button;
    private boolean isClicked;
    private Color color;
    private HumanPlayer whoseTurn;
    
    public Square(int i) {
        index = i;
        button = new JToggleButton();
        createButton(button);
        isClicked = false;
        color = Color.BLACK;
        whoseTurn = null;   
    }
    
    private void createButton(JToggleButton button) {
        button.setOpaque(false);
        button.setContentAreaFilled(false);
        button.setBorderPainted(false);
        button.setBorder(null);
        button.setFocusPainted(false);
    }
    
    public void clearSquare() {
        isClicked = false;
        button.setIcon(null);
    }
    
    public int getIndex() {
        return index;
    }
    
    public void setClicked() {
        isClicked = true;
    }
    
    public boolean getClicked() {
        return isClicked;
    }
    
    public JToggleButton getButton() {
        return button;
    }
    
    public void setColor(Color c) {
        color = c;
    }
    
//    public void setWhoseTurn(HumanPlayer p) {
//        whoseTurn = p;
//    }

    @Override
    public String toString() {
        return "Square{" + "index=" + index + '}';
    }

    @Override
    public int compareTo(Square o) {
        if (index > o.index) {
            return 1;
        } else if (index == o.index) {
            return 0;
        } else {
            return -1;
        }
    }

}
