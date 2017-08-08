/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tictactoe;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Random;
import java.util.Scanner;

/**
 *
 * @author Mantas
 */
public class FileReader {
    
    
    private static final String NAMES_FILE_NAME = "names.txt";
    private static final File f = new File("C:\\Users\\Mantas\\Documents\\NetBeansProjects\\TicTacToe\\src\\tictactoe\\names.txt");
    
    public static String chooseName() throws FileNotFoundException {
        String name = "";
        Random rand = new Random();
        int n = 0;
        
        for (Scanner scanner = new Scanner(f); scanner.hasNext(); ) {
            ++n;
            String line = scanner.nextLine();
            if (rand.nextInt(n) == 0) {
                name = line;
                System.out.println(line);
            }
        }
        return name;
    }
}
