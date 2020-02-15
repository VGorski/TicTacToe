/**
 * TicTacToe class
 * @author Victoria Gorski
 * @date 2 / 14 / 20
 * This class is used to run the entire game and connects the logic class to its board in order to make a playable game.
 */

package edu.quinnipiac.ser210.tictactoe;

// Imports
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

// Main constructor
public class TicTacToe extends AppCompatActivity {
    // Instance variables
    private TicTacToeLogic game;
    private boolean playerTurn = true;
    private Button[] buttons;
    private boolean gameOver = false;
    TextView username;
    String name;

    // Where the game is created
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tic_tac_toe);
        // Get the name from the splash - screeen and put it near the top
        username = findViewById(R.id.playerGreeting);
        name = getIntent().getExtras().getString("name");
        username.setText("Let's play " + name + "!");

        // Initialize all the buttons
        buttons = new Button[]{findViewById(R.id.location_0), findViewById(R.id.location_1), findViewById(R.id.location_2), findViewById(R.id.location_3), findViewById(R.id.location_4), findViewById(R.id.location_5), findViewById(R.id.location_6), findViewById(R.id.location_7), findViewById(R.id.location_8)};

        // If there is a saved state, print it out onto the current board
        if (savedInstanceState != null) {
            int[][] flippedBoard = new int[3][3];
            flippedBoard[0] = savedInstanceState.getIntArray("CurrentBoardRow0");
            flippedBoard[1] = savedInstanceState.getIntArray("CurrentBoardRow1");
            flippedBoard[2] = savedInstanceState.getIntArray("CurrentBoardRow2");
            // Keep the status of the player and the game
            this.playerTurn = savedInstanceState.getBoolean("Turn");
            this.gameOver = savedInstanceState.getBoolean("Game");
            game = new TicTacToeLogic(flippedBoard);
            updateIcons();
        } else {
            // Start a new game
            game = new TicTacToeLogic();
        }

        // For every button, give it a click listener
        for (int i = 0; i < buttons.length; i++) {
            buttons[i].setOnClickListener(new clickListener(i));
        }
        // Tells if the game is over
        if (this.gameOver) {
            gameOver();
        }
    }

    // If player flips the screen, save the current progress
    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {
        super.onSaveInstanceState(savedInstanceState);
        savedInstanceState.putIntArray("CurrentBoardRow0", game.getBoard()[0]);
        savedInstanceState.putIntArray("CurrentBoardRow1", game.getBoard()[1]);
        savedInstanceState.putIntArray("CurrentBoardRow2", game.getBoard()[2]);
        savedInstanceState.putBoolean("Turn", this.playerTurn);
        savedInstanceState.putBoolean("Game", this.gameOver);
    }

    // Used to get the button's click and call a method
    class clickListener implements View.OnClickListener {
        private int buttonNum;

        public clickListener(int buttonNumber) {
            buttonNum = buttonNumber;
        }
        // If the player clicks, set that button to a symbol
        @Override
        public void onClick(View view) {
            if (game.checkLocation(buttonNum) && playerTurn) {
                game.setMove(0, buttonNum);
                // Switch turns
                turnOver();
            }
        }
    }

    // Switches the turn of either player
    private void turnOver() {
        // Update the screen each time
        updateIcons();
        this.playerTurn = false;

        // Check if the game is over
        if (game.checkForWinner() != ITicTacToe.PLAYING) {
            gameOver();
            gameOver = true;
            return;
        }

        // Computer makes a move
        game.setMove(1, game.getComputerMove());
        // Update the screen each time
        updateIcons();
        // Check if game is over
        if (game.checkForWinner() != ITicTacToe.PLAYING) {
            gameOver();
            gameOver = true;
            return;
        }

        // Repeat the cycle once the other player has gone
        this.playerTurn = true;
    }

    // Controls the icons on the screen
    private void updateIcons() {
        // For as long as the game has empty buttons, print out the appropriate symbol
        for (int i = 0; i < buttons.length; i++) {
            switch (game.getLocation(i)) {
                case (ITicTacToe.CROSS):
                    buttons[i].setText("X");
                    break;
                case (ITicTacToe.NOUGHT):
                    buttons[i].setText("O");
                    break;
                default:
                    buttons[i].setText("");
            }
        }
    }

    // If the game is over, print a message
    private void gameOver() {
        this.playerTurn = false; //disable player moves
        switch (game.checkForWinner()) {
            case (ITicTacToe.CROSS_WON):
                gameOverAlert("You win!");
                break;
            case (ITicTacToe.NOUGHT_WON):
                gameOverAlert("I win!");
                break;
            case (ITicTacToe.TIE):
                gameOverAlert("It was a tie! ");
                break;
            default:
                break;
        }
    }

    // Pop - up to tell the player the game is over
    private void gameOverAlert(String popup) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        // Sets the message with the appropriate game over and allow the game to reset
        builder.setMessage(popup + " Let's play again!").setPositiveButton("Restart", new DialogInterface.OnClickListener() {
                    // Restart the game
                    public void onClick(DialogInterface dialog, int id) {
                        game.clearBoard();
                        gameOver = false;
                        playerTurn = true;
                        // Update the board
                        updateIcons();
                    }
                });
        // Print out this pop - up at the end of the game
        AlertDialog alert = builder.create();
        alert.show();
    }
}
