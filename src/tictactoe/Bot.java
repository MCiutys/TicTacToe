/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tictactoe;

import java.util.ArrayList;

/**
 *
 * @author Mantas
 */
public class Bot extends Player {
    
    private ArrayList<Square> notClicked;
    
    public Bot(String name) {
        super(name);
        notClicked = new ArrayList<>();
    }
    
    public Square mark(Board board) {
        notClicked = board.getFreeSquares();
        int rand = (int) (Math.random() * notClicked.size());
        return notClicked.get(rand);
    }
}
