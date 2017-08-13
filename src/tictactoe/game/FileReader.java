/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tictactoe.game;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Random;
import java.util.Scanner;

/**
 *
 * @author Mantas
 */
public class FileReader {
    
    
    private static final File FILE = new File("C:\\Users\\Mantas\\Documents\\NetBeansProjects\\TicTacToe\\src\\tictactoe\\names.txt");
    
    public static final String chooseName() throws FileNotFoundException {
        String name = "";
        Random rand = new Random();
        int n = 0;
        
        for (Scanner scanner = new Scanner(FILE); scanner.hasNext(); ) {
            ++n;
            String line = scanner.nextLine();
            if (rand.nextInt(n) == 0) {
                name = line;
            }
        }
        return name;
    }
}
