package tictactoe.game;


import tictactoe.game.Square;
import java.awt.Font;
import tictactoe.game.TicTacToe;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Mantas
 */
public final class Constants {
    // For frames and panels
    public static final int HEIGHT = 600;
    public static final int WIDTH = 1024;
    public static final float PART_OF_BOARD = 0.7f;
    public static final int HORIZONTAL_SQUARES = 3;
    public static final int VERTICAL_SQUARES = 3;
    public static final int TOTAL_SQUARES = HORIZONTAL_SQUARES * VERTICAL_SQUARES;
    public static final int WINNING_SQUARES = 3;
    public static final int HORIZONTAL_GAP = 30;
    public static final int VERTICAL_GAP = 30;
    
    // For Board
    public static final int BOARD_WIDTH = (int) (WIDTH * PART_OF_BOARD);
    public static final int VERTICAL_LINE_LENGTH = (int) (WIDTH * 0.5);
    public static final int HORIZONTAL_LINE_LENGTH = (int) (HEIGHT * 0.8);
    public static final int STROKE_WIDTH = 5;
    
    // Winning sets
    public static final Square[] TOP_HORIZONTAL = {new Square(0), new Square(1), new Square(2)};
    public static final Square[] MIDDLE_HORIZONTAL = {new Square(3), new Square(4), new Square(5)};
    public static final Square[] BOTTOM_HORIZONTAL = {new Square(6), new Square(7), new Square(8)};
    public static final Square[] LEFT_VERTICAL = {new Square(0), new Square(3), new Square(6)};
    public static final Square[] MIDDLE_VERTICAL = {new Square(1), new Square(4), new Square(7)};
    public static final Square[] RIGHT_VERTICAL = {new Square(2), new Square(5), new Square(8)};
    public static final Square[] L_TO_R_DIAGONAL = {new Square(0), new Square(4), new Square(8)};
    public static final Square[] R_TO_L_DIAGONAL = {new Square(2), new Square(4), new Square(6)};

    public static final Square[][] WINNING_SETS = {TOP_HORIZONTAL, MIDDLE_HORIZONTAL
            , BOTTOM_HORIZONTAL, LEFT_VERTICAL, MIDDLE_VERTICAL, RIGHT_VERTICAL, L_TO_R_DIAGONAL
            , R_TO_L_DIAGONAL};
    
    // For Bot
    public static final Square CENTER = new Square(4);
    public static final Square TOP_EDGE = new Square(1);
    public static final Square LEFT_EDGE = new Square(3);
    public static final Square RIGHT_EDGE = new Square(5);
    public static final Square BOTTOM_EDGE = new Square(7);

    public static final Square TOP_LEFT_CORNER = new Square(0);
    public static final Square TOP_RIGHT_CORNER = new Square(2);
    public static final Square BOTTOM_LEFT_CORNER = new Square(6);
    public static final Square BOTTOM_RIGHT_CORNER = new Square(8);

    public static final Square[] EDGES = {TOP_EDGE, LEFT_EDGE, RIGHT_EDGE, BOTTOM_EDGE};
    public static final Square[] CORNERS = {TOP_LEFT_CORNER, TOP_RIGHT_CORNER, BOTTOM_LEFT_CORNER, 
    BOTTOM_RIGHT_CORNER};
    
    // Combo box choices
    public static final String RANDOM = "Random";
    public static final String EASY = "Easy";
    public static final String IMPOSSIBLE = "Impossible";
    
    // For Game Menu
    public static final Font MENU_FONT = new Font("Times New Roman", Font.BOLD, 32);
    public static final Font INFO_FONT = new Font("Times New Roman", Font.BOLD, 20);


}
