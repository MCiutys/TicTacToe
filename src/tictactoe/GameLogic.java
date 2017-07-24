/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tictactoe;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionListener;
import java.util.Collections;
import javax.swing.JPanel;
import javax.swing.JToggleButton;

/**
 *
 * @author Mantas
 */
public class GameLogic extends JPanel {


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
        add(board);
        add(options);

        player1 = new HumanPlayer();
        player2 = new Bot();
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
        for (Square[] winningSet : Constants.WINNING_SETS) {
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
    
    public Player getPlayer1() {
        return player1;
    }
    
    public Player getPlayer2() {
        return player2;
    }
    
    public GameInfo getGameInfo() {
        return options;
    }
}
