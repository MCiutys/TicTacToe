
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
    private ArrayList<Integer> marked;
    private boolean drawX;
    
    public Player(String name) {
        this.name = name;
        marked = new ArrayList<>();
        drawX = false;
    }
    
    public String getName() {
        return name;
    }
    
    public void addToMarked(int number) {
        marked.add(number);
    }
    
    public ArrayList<Integer> getMarked() {
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
    
}
