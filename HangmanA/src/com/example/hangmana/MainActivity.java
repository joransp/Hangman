package com.example.hangmana;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.content.res.Resources;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

class Word {
	public static String word = "hangman";
	public static String finalString = "_______";
	static int lengthWord = word.length();
	public static boolean won = false;
	public static int wrongGuesses = 0;
	public static int maxWrongGuesses = 10;
	
	public static String guess(char letter, String finalString) {
				
		boolean guessRight = false;
		StringBuilder stringBuilder = new StringBuilder();
		
		for(int i = 0; i < lengthWord; i++) {
			
			if (finalString.charAt(i) != '_') {
				stringBuilder.append(word.charAt(i));
				continue;
			}
			
			if (word.charAt(i) == letter){
				stringBuilder.append(letter);
				guessRight = true;
			}
			else {
				stringBuilder.append("_");
			}
		} 
		
		if (!guessRight) {
			wrongGuesses++;
		}
		
		finalString = stringBuilder.toString();
		return finalString;
		
	}
	
	public static void win() {
		for(int i = 0; i < lengthWord; i++) {
			if (finalString.charAt(i) == '_') {
				return;
			}			
		}
		won = true;
	}
	
	public static void restart(TextView textview1, TextView textview2, Button button, EditText edittext) {
		Word.word = "hangman";
		Word.finalString = "_______";
		Word.lengthWord = word.length();
		Word.won = false;
		Word.wrongGuesses = 0;
		
		textview1.setText(Word.finalString);
		textview2.setVisibility(View.VISIBLE);
		textview2.setText("Wrong guesses: " + Integer.toString(Word.wrongGuesses));
		button.setVisibility(View.VISIBLE);
		edittext.setVisibility(View.VISIBLE);
	}
	
	public static void lose(TextView textview1, TextView textview2, Button button, EditText edittext) {
		if (wrongGuesses >= maxWrongGuesses) {
			textview1.setText("You lost!");
			textview2.setVisibility(View.INVISIBLE);
			button.setVisibility(View.INVISIBLE);
			edittext.setVisibility(View.INVISIBLE);
			
		}
	}
}


public class MainActivity extends Activity {
	
	TextView textElement;
	EditText mEdit;
	TextView wrong;
	TextView guessing;
	TextView winner;
	String input;
	char letterGuess;
	String wrongGuessCount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        wrong = (TextView) findViewById(R.id.textView2);
        guessing = (TextView) findViewById(R.id.textView1);
        winner = (TextView) findViewById(R.id.winnerText);
        mEdit = (EditText)findViewById(R.id.editText1);
        guessing.setText(Word.finalString);        
        
        final Button guess = (Button) findViewById(R.id.button1);
        guess.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
            	input = mEdit.getText().toString();
            	letterGuess = input.charAt(0);
            	Word.finalString = Word.guess(letterGuess, Word.finalString);
            	guessing.setText(Word.finalString);
            	wrongGuessCount = Integer.toString(Word.wrongGuesses);
            	wrong.setText("Wrong guesses: " + wrongGuessCount);
            	Word.win();
            	Word.lose(wrong, guessing,  guess, mEdit);
            	if (Word.won) {
            		setContentView(R.layout.activity_winner);
            		//winner.setText("Congratulations! You have guessed the word" + Word.word + "with" + wrongGuessCount + "wrong guesses");
            	}
                
            }
        });
        
        final Button restarter = (Button) findViewById(R.id.button2);
        restarter.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
            	Word.restart(guessing, wrong, guess, mEdit);
                
            }
        });
    }
    
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }
    
    
    /*public void textChange(String text) {
    	guessing.setText(text); 
    }*/
    
    public void goSettings(View view) {
        Intent intent = new Intent(this, SettingsActivity.class);
        startActivity(intent);
    }
    
    
}
