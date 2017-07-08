package tictactoe;


import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import javax.swing.Icon;
import javax.swing.ImageIcon;

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
    
    private static final int IMAGE_SIZE = 150;
    private static final int STROKE_WIDTH = 10;
    private static final int CIRCLE_SIZE = IMAGE_SIZE - STROKE_WIDTH;
    private static final int CIRCLE_POS = (IMAGE_SIZE - CIRCLE_SIZE) / 2;
    
    
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
    
    public Icon draw() {
        BufferedImage img = new BufferedImage(IMAGE_SIZE, IMAGE_SIZE, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2d = img.createGraphics();
         g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
            RenderingHints.VALUE_ANTIALIAS_ON); 
        g2d.setStroke(new BasicStroke(STROKE_WIDTH));
        g2d.setColor(color);
        if (drawX) {
            drawX(g2d);
        } else {
            drawO(g2d);
        }
        g2d.dispose();
        return new ImageIcon(img);
    }
    
    private void drawO(Graphics2D g2d) {
        g2d.drawOval(CIRCLE_POS, CIRCLE_POS, CIRCLE_SIZE, CIRCLE_SIZE);
    }
    
    private void drawX(Graphics2D g2d) {
        g2d.drawLine(0 , 0, IMAGE_SIZE, IMAGE_SIZE);
        g2d.drawLine(0, IMAGE_SIZE, IMAGE_SIZE, 0);
    }
    
}
