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
    private GameInfo info;
    private HumanPlayer player1;
    private Bot player2;
    private Player whoseTurn;

    private Panel panel;

    public GameLogic() {
        createGame();
    }
    
    public void createGame() {
        board = new Board();
        board.setPreferredSize(new Dimension((int) (Constants.WIDTH * 0.7), Constants.HEIGHT));
        board.setLayout(new GridLayout(3, 3, 30, 30));

        info = new GameInfo();
        add(board);
        add(info);

        player1 = new HumanPlayer();
        player2 = new Bot(board);
        player1.setToDrawX();
        player2.setToDrawO();
        whoseTurn = player1;

        info.setTurnLabel(whoseTurn);
        info.setScores(player1, player2);
    }

    public void addSquareListeners() {

        // Square Listener
        for (int i = 0; i < Constants.TOTAL_SQUARES; i++) {
            Square square = board.getSquares()[i];
            ActionListener squareListener = new SquareListener(square, whoseTurn, player1,
                    player2, this, info);
            JToggleButton button = square.getButton();
            button.addActionListener(squareListener);
        }

    }

    public void addOtherListeners() {
        // Restart Game Button Listener
        info.getPanel().addRestartListener(this);

        // Continue Game Button Listener
        info.getPanel().addContinueListener(this);
    }

    public ActionListener[] getSquareListener(Square square) {
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

    public void botMove(Square lastMove) {
//        System.out.println("BOT EXECUTING");
        if (whoseTurn.getClass().getName().equals(Bot.class.getName()) && board.getFreeSquares().size() != Constants.TOTAL_SQUARES) {
            Bot bot = (Bot) whoseTurn;
            Square mark = bot.mark(lastMove);
            if (mark != null) {
                playerMove(mark);
            }
        }
    }
    
    public Board getBoard() {
        return board;
    }

    public void removeSquareListeners() {
        for (Square square : board.getSquares()) {
            removeSquareListener(square);
        }
    }
    
    public void removeSquareListener(Square square) {
        for (ActionListener actionListener : getSquareListener(square)) {
            square.getButton().removeActionListener(actionListener);
        }
    }

    public void playerMove(Square mark) {
//        System.out.println(board.getFreeSquares());
        
        mark.setClicked();
        mark.setColor(whoseTurn.getColor());
        
        if (whoseTurn.isDrawingX()) {
            mark.setDrawnX();
        } else {
            mark.setDrawnO();
        }

        whoseTurn.addToMarked(mark);
        Collections.sort(whoseTurn.getMarked());

        if (mark.getClicked()) {
            mark.getButton().setIcon(whoseTurn.draw());
        }

        if (isWinner()) {
            removeSquareListeners();
            info.getPanel().displayWinner(whoseTurn);
        } else {
            if (isDraw()) {
                removeSquareListeners();
                info.getPanel().displayDraw();
            }
        }
        
        swapTurns();
        info.setTurnLabel(whoseTurn);
        removeSquareListener(mark);
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
        return board.getFreeSquares().isEmpty();
    }

    public void updateGameInfo() {
        info.setTurnLabel(whoseTurn);
        info.setScores(player1, player2);
    }
    
    public int amountOfListeners() {
        Square s = board.getFreeSquares().get(4);
        int count = 0;
        for (Square sq : board.getFreeSquares()) {
            for (ActionListener a : s.getButton().getActionListeners()) {
               count++;
            }  
            System.out.println("AMOUNT OF LISTENERS for " + sq.getIndex() + ": " + count );
        }
        return count;
    }

    public void restartGame() {
        board.clearBoard();
        player1.resetPlayer();
        player2.resetPlayer();
        amountOfListeners();
        updateGameInfo();
        info.getPanel().doNotDisplayWinner();
        addSquareListeners();
    }

    public void continueGame() {
        board.clearBoard();
        updateGameInfo();
        player1.resetMarked();
        player2.resetMarked();
        addSquareListeners();
        info.getPanel().doNotDisplayWinner();
        whoseTurn = player1;
    }
    
    public Player getPlayer1() {
        return player1;
    }
    
    public Player getPlayer2() {
        return player2;
    }
    
    public GameInfo getGameInfo() {
        return info;
    }
}
