package tictactoe;


import java.awt.Color;
import java.util.ArrayList;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Mantas
 */
public class Player {
    
    private String name;
    private ArrayList<Square> marked;
    private boolean drawX;
    private Color color;
    
    public Player(String name) {
        this.name = name;
        marked = new ArrayList<>();
        drawX = false;
    }
    
    public String getName() {
        return name;
    }
    
    public void addToMarked(Square number) {
        marked.add(number);
    }
    
    public ArrayList<Square> getMarked() {
        return marked;
    }
    
    public boolean isDrawingX() {
        return drawX;
    }
    
    public void setToDrawX() {
        drawX = true;
    }
    
    public void setToDrawO() {
        drawX = false;
    }
    
    public void setColor(Color c) {
        color = c;
    }
    
    public Color getColor() {
        return color;
    }
    
}
