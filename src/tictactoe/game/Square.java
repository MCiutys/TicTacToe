/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tictactoe.game;

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
    private boolean isDrawnX;
    
    public Square(int i) {
        index = i;
        button = new JToggleButton();
        createButton(button);
        isClicked = false;
        color = Color.BLACK;
        whoseTurn = null;
        isDrawnX = false;
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
        isDrawnX = false;
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
    
    public void setDrawnX() {
        isDrawnX = true;
    }
    
    public void setDrawnO() {
        isDrawnX = false;
    }
    
    public boolean isDrawnX() {
        return isDrawnX;
    }
    
    public boolean isDrawnO() {
        return isClicked && !isDrawnX;
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

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 23 * hash + this.index;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Square other = (Square) obj;
        if (this.index != other.index) {
            return false;
        }
        return true;
    }
    
    

}
