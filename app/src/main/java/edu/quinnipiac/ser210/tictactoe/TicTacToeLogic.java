/**
 * TicTacToeLogic class
 * @author Victoria Gorski
 * @date 2 / 14 / 20
 * This class is used to control the rules of the game and determine the move set of the player, the computer, as well as resetting the board
 * and checking for wins.
 */

package edu.quinnipiac.ser210.tictactoe;

// Main constructor
public class TicTacToeLogic implements ITicTacToe {

    // Instance variables
    // Creates the board
    private static final int ROWS = 3, COLS = 3;
    private int[][] board;

    // Initialize the board
    public TicTacToeLogic(int[][] board) {
        this.board = board;
    }

    // Give the board its parameters
    public TicTacToeLogic() {
        board = new int[ROWS][COLS];
    }

    // Create another board to start the game over
    @Override
    public void clearBoard() {
        board = new int[ROWS][COLS];
    }

    // Set the move of the player or the computer
    @Override
    public void setMove(int player, int location) {
        int row = location / 3;
        int col = location % 3;
        // If this location is taken, do not put another symbol there
        if (!checkLocation(location)) {
            return;
            // If it's the player's turn, put a cross there
        } else if (player == 0) {
            board[row][col] = ITicTacToe.CROSS;
            // If it's the computer's turn, put a nought there
        } else {
            board[row][col] = ITicTacToe.NOUGHT;
        }
    }

    // Determine the move of the computer based on the status of the board
    @Override
    public int getComputerMove() {

        // Center if it is open
        if (board[1][1] == EMPTY)
            return board[1][1] = NOUGHT;

        // Each corner if it is open
        if (board[0][0] == EMPTY)
            return board[0][0] = NOUGHT;
        if (board[0][2] == EMPTY)
            return board[0][2] = NOUGHT;
        if (board[2][0] == EMPTY)
            return board[2][0] = NOUGHT;
        if (board[2][2] == EMPTY)
            return board[2][2] = NOUGHT;

        // Each edge if it is open
        if (board[0][1] == EMPTY)
            return board[0][1] = NOUGHT;
        if (board[1][0] == EMPTY)
            return board[1][0] = NOUGHT;
        if (board[1][2] == EMPTY)
            return board[1][2] = NOUGHT;
        if (board[2][1] == EMPTY)
            return board[2][1] = NOUGHT;
        return -1;
    }

    // Check for a winner
    @Override
    public int checkForWinner() {

        // If three of the same symbol is placed in a certain pattern and the space is not empty, display the winner
        // Checks spots 0, 1, and 2
        if (board[0][0] == board[0][1] && board[0][1] == board[0][2] && board[0][2] == board[0][0] && board[0][0] != EMPTY) {
            if (board[0][0] == CROSS) {
                return CROSS_WON;
            } else {
                return NOUGHT_WON;
            }
        }

        // Checks spots 4,5, and 6
        else if (board[1][0] == board[1][1] && board[1][1] == board[1][2] && board[1][2] == board[1][0] && board[1][0] != EMPTY) {
            if (board[1][0] == CROSS) {
                return CROSS_WON;
            } else {
                return NOUGHT_WON;
            }
        }

        // Checks spots 7, 8, and 9
        else if (board[2][0] == board[2][1] && board[2][1] == board[2][2] && board[2][2] == board[2][0] && board[2][0] != EMPTY) {
            if (board[2][0] == CROSS) {
                return CROSS_WON;
            } else {
                return NOUGHT_WON;
            }
        }

        // Checks spots 1, 4, and 7
        else if (board[0][0] == board[1][0] && board[1][0] == board[2][0] && board[2][0] == board[0][0] && board[0][0] != EMPTY) {
            if (board[0][0] == CROSS) {
                return CROSS_WON;
            } else {
                return NOUGHT_WON;
            }
        }

        // Checks spots 2, 5, and 8
        else if (board[0][1] == board[1][1] && board[1][1] == board[2][1] && board[2][1] == board[0][1] && board[0][1] != EMPTY) {
            if (board[0][1] == CROSS) {
                return CROSS_WON;
            } else {
                return NOUGHT_WON;
            }
        }

        // Checks spots 3, 6, and 9
        else if (board[0][2] == board[1][2] && board[1][2] == board[2][2] && board[2][2] == board[0][2] && board[0][2] != EMPTY) {
            if (board[0][2] == CROSS) {
                return CROSS_WON;
            } else {
                return NOUGHT_WON;
            }
        }

        // Checks spots 1, 5, and 9
        else if (board[0][0] == board[1][2] && board[1][2] == board[2][2] && board[2][2] == board[0][0] && board[0][0] != EMPTY) {
            if (board[0][0] == CROSS) {
                return CROSS_WON;
            } else {
                return NOUGHT_WON;
            }
        }

        // Checks spots 3, 5, and 7
        if (board[0][2] == board[1][2] && board[1][2] == board[2][0] && board[2][0] == board[0][2] && board[0][2] != EMPTY) {
            if (board[0][2] == CROSS) {
                return CROSS_WON;
            } else {
                return NOUGHT_WON;
            }
        }

        // If the board is filled up and there is no winner, say it's a tie
        else if (board[0][0] != EMPTY && board[0][1] != EMPTY && board[0][2] != EMPTY && board[1][0] != EMPTY && board[1][1] != EMPTY && board[1][2] != EMPTY && board[2][0] != EMPTY && board[2][1] != EMPTY && board[2][2] != EMPTY) {
            return TIE;
        }
        return 0;
    }

    // Check if a spot is empty
    public boolean checkLocation(int location) {
        int row = location / 3;
        int col = location % 3;
        // If a spot is empty, allow player to place symbol there
        if (board[row][col] == ITicTacToe.EMPTY) {
            return true;
            // If a spot is taken, do not put a symbol there
        } else {
            return false;
        }
    }

    // Get the basic outline of the board
    public int[][] getBoard() {
        return board;
    }

    // Get the location the player wants to place their symbol in
    public int getLocation(int location) {
        int row = location / 3;
        int col = location % 3;

        return board[row][col];
    }
}
