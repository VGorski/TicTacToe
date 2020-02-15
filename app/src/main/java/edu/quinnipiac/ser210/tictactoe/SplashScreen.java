/**
 * SplashScreen class
 * @author Victoria Gorski
 * @date 2 / 14 / 20
 * This class is used to create the splash - screen for when the user first enters the game, prompting for a name.
 */

package edu.quinnipiac.ser210.tictactoe;

// Imports
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

// Main constructor
public class SplashScreen extends AppCompatActivity {

    // Instance variables
    EditText userInput;
    Button start;
    String name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.screen_splash);
        // Assign all fields to its instance variables
        start = findViewById(R.id.button);
        userInput = findViewById(R.id.editText);
        // Set a listener for the start button so it can deliver the name to the TicTacToe class
        start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SplashScreen.this, TicTacToe.class);
                name = userInput.getText().toString();
                intent.putExtra("name", name);
                // Once there is a name, start the game
                startActivity(intent);
                finish();
            }
        });
    }
}

