/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tictactoe;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import static java.awt.image.ImageObserver.HEIGHT;
import static java.awt.image.ImageObserver.WIDTH;
import java.util.Collections;
import javax.swing.JFrame;
import javax.swing.JToggleButton;
import static tictactoe.TicTacToe.HEIGHT;
import static tictactoe.TicTacToe.WIDTH;

/**
 *
 * @author Mantas
 */
public class GameLogic {

    // Winning sets
    private static final Square[] SET_1 = {new Square(0), new Square(1), new Square(2)};
    private static final Square[] SET_2 = {new Square(3), new Square(4), new Square(5)};
    private static final Square[] SET_3 = {new Square(6), new Square(7), new Square(8)};
    private static final Square[] SET_4 = {new Square(0), new Square(3), new Square(6)};
    private static final Square[] SET_5 = {new Square(1), new Square(4), new Square(7)};
    private static final Square[] SET_6 = {new Square(2), new Square(5), new Square(8)};
    private static final Square[] SET_7 = {new Square(0), new Square(4), new Square(8)};
    private static final Square[] SET_8 = {new Square(2), new Square(4), new Square(6)};

    private static final Square[][] WINNING_SETS = {SET_1, SET_2, SET_3, SET_4, SET_5, SET_6, SET_7, SET_8};

    private Board board;
    private final GameInfo options;
    private final HumanPlayer player1;
    private final Bot player2;
    private Player whoseTurn;

    private Panel panel;

    public GameLogic() {
        board = new Board();
        board.setPreferredSize(new Dimension((int) (Constants.WIDTH * 0.7), Constants.HEIGHT));
        board.setLayout(new GridLayout(3, 3, 30, 30));

        options = new GameInfo();

        JFrame frame = new JFrame("Tic Tac Toe");
        frame.setLayout(new FlowLayout());
        frame.add(board);
        frame.add(options);
        frame.pack();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.setVisible(true);

        player1 = new HumanPlayer("Mantas");
        player2 = new Bot("Jonas");
        player1.setToDrawX();
        player2.setToDrawO();
        whoseTurn = player1;

        options.setTurnLabel(whoseTurn);
        options.setScores(player1, player2);
    }

    public void addSquareListeners() {

        // Square Listener
        for (int i = 0; i < Constants.TOTAL_SQUARES; i++) {
            Square square = board.getSquares()[i];
            ActionListener squareListener = new SquareListener(square, whoseTurn, player1,
                    player2, this, options);
            JToggleButton button = square.getButton();
            button.addActionListener(squareListener);
        }

    }

    public void addOtherListeners() {
        // Restart Game Button Listener
        options.getPanel().addRestartListener(this);

        // Continue Game Button Listener
        options.getPanel().addContinueListener(this);
    }

    public ActionListener[] getSquareListener(Square square) {
        System.out.println("Number of listeners: " + square.getButton().getActionListeners().length);
        return square.getButton().getActionListeners();
    }

    public boolean didPlayerWin(Player player) {

        int counter = 0;
        outer:
        for (Square[] winningSet : WINNING_SETS) {
            counter = 0;
            for (Square square : winningSet) {
                for (int j = 0; j < whoseTurn.getMarked().size(); j++) {
                    if (square.compareTo(whoseTurn.getMarked().get(j)) == 0) {
                        counter++;
                    }
                    if (counter == 3) {
                        break outer;
                    }
                }
            }
        }

        if (counter == 3) {
            whoseTurn.increaseScore();
            return true;
        } else {
            return false;
        }
    }

    public void executeGame() {
        player1.setColor(Color.RED);
        player2.setColor(Color.BLUE);

//        board.setWhoseTurn(player1);
        addSquareListeners();
        addOtherListeners();
    }

    public void botMove() {
        if (whoseTurn.getClass().getName().equals(Bot.class.getName())) {
            Bot bot = (Bot) whoseTurn;
            Square mark = bot.mark(board);
            if (mark != null) {
                playerMove(mark);
            }
        }
    }

    public void removeSquareListeners() {
        for (Square square : board.getSquares()) {
            for (ActionListener actionListener : this.getSquareListener(square)) {
                square.getButton().removeActionListener(actionListener);
            }
        }
    }

    public void playerMove(Square mark) {
        mark.setClicked();
        mark.setColor(whoseTurn.getColor());

        whoseTurn.addToMarked(mark);
        Collections.sort(whoseTurn.getMarked());

        if (mark.getClicked()) {
            mark.getButton().setIcon(whoseTurn.draw());
        }

        if (isWinner()) {
            removeSquareListeners();
            options.getPanel().displayWinner(whoseTurn);
        } else {
            if (isDraw()) {
                removeSquareListeners();
                continueGame();
            }
        }
        swapTurns();
        options.setTurnLabel(whoseTurn);
    }
    
    private void swapTurns() {
        if (whoseTurn == player1) {
            whoseTurn = player2;
        } else {
            whoseTurn = player1;
        }
    }

    public boolean isWinner() {
        boolean isWinner = false;
        if (didPlayerWin(player1) || didPlayerWin(player2)) {
            isWinner = true;
        }
        return isWinner;
    }

    public boolean isDraw() {
        boolean isDraw = false;
        if (board.getFreeSquares().isEmpty()) {
            isDraw = true;
        }
        return isDraw;
    }

    public void updateGameInfo() {
        options.setTurnLabel(whoseTurn);
        options.setScores(player1, player2);
    }

    public void restartGame() {
        board.clearBoard();
        player1.resetPlayer();
        player2.resetPlayer();
        updateGameInfo();
        options.getPanel().doNotDisplayWinner();
        if (getSquareListener(board.getSquares()[2]).length == 0) {
            addSquareListeners();
        }
    }

    public void continueGame() {
        board.clearBoard();
        updateGameInfo();
        player1.resetMarked();
        player2.resetMarked();
        addSquareListeners();
        options.getPanel().doNotDisplayWinner();
    }

    @Override
    public String toString() {
        return "hellow";
    }

}
