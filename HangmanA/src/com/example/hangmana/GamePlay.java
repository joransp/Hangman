package com.example.hangmana;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class GamePlay {
	
	private String word;
	private String finalString;
	private int lengthWord;
	private boolean won = false;
	private int wrongGuesses;
	private int maxWrongGuesses;
	private StringBuilder wrongLetters = new StringBuilder();;
	
	public boolean won() {
		return won;
	}
	
	public int getWrongGuesses() {
		return wrongGuesses;
	}
	
	public void setMaxWrongGuesses(int max) {
		if (max >= 1 && max <=26) {
			maxWrongGuesses = max;
		}
	}
	
	public void setWord(String wordArgument) {
		word = wordArgument;
	}
	
	public String getWord() {
		return word;
	}
	public int getLengthWord() {
		return lengthWord;
	}
	
	public void setLengthWord(int length) {
		if (length >= 1) {
			lengthWord = length;
		}
	}
	
	public String getFinalString() {
		return finalString;
	}
	
	public void setFinalString(String string) {
		finalString = string;
	}
	
	public void setStartString(int length) {
		StringBuilder stringBuilder = new StringBuilder();
		for (int i = 0; i < length; i++) {
			stringBuilder.append('-');
			finalString = stringBuilder.toString();
		}
	}
	public String guess(char letter, String finalString, TextView textView) {
				
		boolean guessRight = false;
		if (letter >= 'a' && letter <= 'z') {
			letter = Character.toUpperCase(letter);
		}
		else if (letter <= 'A' || letter >= 'Z') {
			return finalString;
		}
		
		StringBuilder stringBuilder = new StringBuilder();
		
		for(int i = 0; i < lengthWord; i++) {
			
			if (finalString.charAt(i) != '-') {
				stringBuilder.append(word.charAt(i));
				continue;
			}
			
			if (word.charAt(i) == letter){
				stringBuilder.append(letter);
				guessRight = true;
			}
			else {
				stringBuilder.append("-");
			}
		} 
		
		if (!guessRight) {
			wrongGuesses++;
			wrongLetters.append(Character.toString(letter) + " ");
			textView.setText("Wrong guessed letters: " + wrongLetters);
		}
		
		finalString = stringBuilder.toString();
		return finalString;
		
	}
	
	public void win() {
		for(int i = 0; i < lengthWord; i++) {
			if (finalString.charAt(i) == '-') {
				return;
			}			
		}
		won = true;
	}
	
	public void restart(TextView textview1, TextView textview2, Button button, EditText edittext) {
		finalString = "";
		won = false;
		wrongGuesses = 0;
		
		textview1.setText(finalString);
		textview1.setVisibility(View.VISIBLE);
		textview2.setVisibility(View.VISIBLE);
		textview2.setText("Wrong guesses: " + Integer.toString(wrongGuesses));
		button.setVisibility(View.VISIBLE);
		edittext.setVisibility(View.VISIBLE);
	}
	
	public void lose(TextView textview1, TextView textview2, Button button, EditText edittext, String word) {
		if (wrongGuesses > maxWrongGuesses) {
			textview1.setText("You lost! The right word was " + word);
			textview2.setVisibility(View.INVISIBLE);
			button.setVisibility(View.INVISIBLE);
			edittext.setVisibility(View.INVISIBLE);
			
		}
	}
}

