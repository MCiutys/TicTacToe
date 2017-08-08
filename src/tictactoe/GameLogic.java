/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tictactoe;

import java.awt.Color;
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
    private Player player2;
    private Player whoseTurn;
    private Player firstPlayer;

    private Panel panel;
    private int numberOfMoves;
    private int maxResult;

    public GameLogic(Player opponent, Board board) {
        player2 = opponent;
        player2.setToDrawO();
        this.board = board;
        createGame();
    }
    
    public void createGame() {
//        board = new Board();
//        board.setPreferredSize(new Dimension((int) (Constants.WIDTH * 0.7), Constants.HEIGHT));
//        board.setLayout(new GridLayout(3, 3, 30, 30));

        info = new GameInfo();
        add(board);
        add(info);

        player1 = new HumanPlayer();
//        player2 = new Bot(board, Constants.IMPOSSIBLE);
        player1.setToDrawX();
//        player2.setToDrawO();
//        whoseTurn = player1;
//                System.out.println(player1.getName());

        numberOfMoves = 0;
        maxResult = 0;

//        info.setTurnLabel(whoseTurn);
//        info.setScores(player1, player2);
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
                
        // If bot has first turn, execute it
        if (whoseTurn == player2/* && player2.getClass().getName().equals(Bot.class.getName())*/) {
            botMove(null);
        }
    }

    public void botMove(Square lastMove) {
        if (whoseTurn instanceof Bot) {
            Bot bot = (Bot) whoseTurn;
            Square mark = bot.mark(lastMove);
//            System.out.println("mark: " + mark);
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
//        System.out.println(whoseTurn.getName());
        
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
        numberOfMoves++;
        swapTurns();
        info.setTurnLabel(whoseTurn);
        removeSquareListener(mark);
        
        System.out.println(maxResult);
        
        if (maxResult == maxResult()) {
            System.out.println("DONEEE");
        }
    }
    
    private void swapTurns() {
//        if (numberOfMoves != Constants.TOTAL_SQUARES) {
            if (whoseTurn == player1) {
                whoseTurn = player2;
            } else {
                whoseTurn = player1;
            }
//        }
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
    
    public int maxResult() {
        int max = 0;
        if (player1.getScore() > player2.getScore()) {
            max = player1.getScore();
        } else {
            max = player2.getScore();
        }
                System.out.println("mAX FOR TWO: " + max);
        return max;
    }
    
    public void setMaxResult(int max) {
        maxResult = max;
    }

    public void updateGameInfo() {
        info.setTurnLabel(whoseTurn);
        info.setScores(player1, player2);
    }

    public void restartGame() {
        whoseTurn = firstPlayer;
        board.clearBoard();
        player1.resetPlayer();
        player2.resetPlayer();
        updateGameInfo();
        info.getPanel().doNotDisplayWinner();
        addSquareListeners();
        if (whoseTurn instanceof Bot) {
            botMove(null);
        }
        numberOfMoves = 0;
        
    }

    public void continueGame() {
        board.clearBoard();
        updateGameInfo();
        player1.resetMarked();
        player2.resetMarked();
        addSquareListeners();
        info.getPanel().doNotDisplayWinner();
        whoseTurn = firstPlayer;
//        if (whoseTurn.getClass().getName().equals(Bot.class.getName())) {
//            botMove(null);
//        }
        if (whoseTurn instanceof Bot) {
            botMove(null);
        }
        numberOfMoves = 0;
    }
    
    public void maxResult(int max) {
        
    }
    
    public Player getPlayer1() {
        return player1;
    }
    
    public Player getPlayer2() {
        return player2;
    }
    
    public void setPlayer2(String opponent, String level) {
        if (opponent.equals(GameMenu.HUMAN)) {
            player2 = new HumanPlayer();
        } else {
//            player2 = new Bot(board, level);
        }
    }
    
//    public void setPlayer2Level(String level) {
//        player2.setLevel(level);
//    }
    
    public GameInfo getGameInfo() {
        return info;
    }
    
    public void setFirstTurn(boolean isItPlayer1) {
        if (isItPlayer1) {
            whoseTurn = player1;
        } else {
            whoseTurn = player2;
        }
        firstPlayer = whoseTurn;
    }
}
