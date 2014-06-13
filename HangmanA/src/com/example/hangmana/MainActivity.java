package com.example.hangmana;

import java.util.Random;

import android.os.Bundle;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.app.ActionBar;

public class MainActivity extends Activity {
	// Define variable types
	EditText mEdit;
	TextView wrong;
	TextView guessing;
	TextView winner;
	TextView letters;
	String input;
	char letterGuess;
	String wrongGuessCount;
	GamePlay gameplay;
	String[] words;
	Random rand;
	Random rand2;
	int int1;
	int int2;
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        // Define new GamePlay class
        gameplay = new GamePlay();
        
        // Find all textviews and textfields
        wrong = (TextView) findViewById(R.id.textView2);
        guessing = (TextView) findViewById(R.id.textView1);
        winner = (TextView) findViewById(R.id.winnerText);
        letters = (TextView) findViewById(R.id.textView3);
        mEdit = (EditText)findViewById(R.id.editText1);
        
        // Finish building screen using SharedPreferences values
        guessing.setText(gameplay.getFinalString());
        words = getResources().getStringArray(R.array.words_small);
        final int arrayLength = words.length;
        final SharedPreferences preferences = getSharedPreferences("MyPreferences",  Context.MODE_PRIVATE);
        gameplay.setMaxWrongGuesses(preferences.getInt("max_wrong_guesses", 0));
        
        // Pick random word of given length
        rand = new Random();
		int1 = rand.nextInt(arrayLength);
		gameplay.setWord(words[int1]);
		gameplay.setLengthWord(preferences.getInt("word_length", 0)); 
		gameplay.setStartString(gameplay.getLengthWord()); 
		while(gameplay.getWord().length() != gameplay.getLengthWord()) { 
			rand2 = new Random();
    		int2 = rand2.nextInt(arrayLength);
    		gameplay.setWord( words[int2]);
		}
		guessing.setText(gameplay.getFinalString());
        
		// Define onClick actions for pressing GUESS
		final Button guess = (Button) findViewById(R.id.button1);
        guess.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
            	// Get text from TextField
            	input = mEdit.getText().toString();
            	mEdit.setText("");
            	if (input == null) {
            		letterGuess = '0';
            	}
            	else {
            		letterGuess = input.charAt(0);
            	}
            	// Gues word
            	gameplay.setFinalString(gameplay.guess(letterGuess, gameplay.getFinalString(), letters));
            	guessing.setText(gameplay.getFinalString());
            	wrongGuessCount = Integer.toString(gameplay.getWrongGuesses());
            	wrong.setText("Wrong guesses: " + wrongGuessCount);
            	// Check if game is won or lost
            	gameplay.win();
            	gameplay.lose(wrong, guessing,  guess, mEdit, gameplay.getWord());
            	if (gameplay.won()) {
            		wrong.setText("You won!");
        			guessing.setVisibility(View.INVISIBLE);
        			guess.setVisibility(View.INVISIBLE);
        			mEdit.setVisibility(View.INVISIBLE);            	}
                
            }
        });
        
        // Define onClick actions for pressing RESTART
        final Button restarter = (Button) findViewById(R.id.button2);
        restarter.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
            	// Set all values back 
            	gameplay.restart(guessing, wrong, guess, mEdit);
            	Random rand = new Random();
        		int1 = rand.nextInt(arrayLength);
        		gameplay.setWord(words[int1]);
        		gameplay.setLengthWord(preferences.getInt("word_length", 0));
        		gameplay.setStartString(gameplay.getLengthWord());
        		while(gameplay.getWord().length() != gameplay.getLengthWord()) {
        			rand2 = new Random();
            		int2 = rand2.nextInt(arrayLength);
            		gameplay.setWord(words[int2]);
        		}
                
            }
        });
    }
    
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }
    
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
    	// Add settings button to the action bar
    	int id = item.getItemId(); 
    	if (id == R.id.action_settings) {
    		goSettings();
    		return true; 
    	}
    	return super.onOptionsItemSelected(item);
    }
       
    public void goSettings() {
    	// Define intent to go to settings
        Intent intent = new Intent(this, SettingsActivity.class);
        startActivity(intent);
    }
    
    
}
