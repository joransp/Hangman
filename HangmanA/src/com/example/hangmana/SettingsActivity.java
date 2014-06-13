package com.example.hangmana;

import android.os.Bundle;
import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.view.Menu;
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;
import android.widget.TextView;

public class SettingsActivity extends Activity {
	
	// Define variable types
	public int maxWrongGuesses;
	public int wordLength;
	
		
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_settings);
		
		// Find seekBars
		final SeekBar wordLength = (SeekBar) findViewById(R.id.seekBar1);
        final SeekBar wrongGuesses = (SeekBar) findViewById(R.id.seekBar2);

		
		// View progress from seekBar1
		wordLength.setOnSeekBarChangeListener(new OnSeekBarChangeListener() {
            
			@Override
			public void onProgressChanged(SeekBar arg0, int arg1, boolean arg2) {
				// TODO Auto-generated method stub
				((TextView) findViewById(R.id.length)).setText("Wordlength: " + Integer.toString(arg1));	
			}
			
			@Override
			public void onStartTrackingTouch(SeekBar arg0) {
				// TODO Auto-generated method stub
			}

			@Override
			public void onStopTrackingTouch(SeekBar arg0) {
				// TODO Auto-generated method stub
			}
        });
        
        // View progress from seekBar2
        wrongGuesses.setOnSeekBarChangeListener(new OnSeekBarChangeListener() {
            
			@Override
			public void onProgressChanged(SeekBar arg0, int arg1, boolean arg2) {
				// TODO Auto-generated method stub
				((TextView) findViewById(R.id.wrongGuesses)).setText("Wrong guesses allowed: " + Integer.toString(arg1));
			}
			
			@Override
			public void onStartTrackingTouch(SeekBar arg0) {
				// TODO Auto-generated method stub
			}

			@Override
			public void onStopTrackingTouch(SeekBar arg0) {
				// TODO Auto-generated method stub
			}
        });
        
        
        
	}
	
	// When activity is started, set all variables to last values
	@Override
	public void onStart(){
	    super.onStart();
	    final SeekBar seekbar2 = (SeekBar) findViewById(R.id.seekBar2);
	    final SeekBar seekbar1 = (SeekBar) findViewById(R.id.seekBar1);
	    SharedPreferences preferences = getSharedPreferences("MyPreferences",  Context.MODE_PRIVATE);
	    seekbar2.setProgress(preferences.getInt("max_wrong_guesses", 0)); 
	    seekbar1.setProgress(preferences.getInt("word_length", 0)); 
	}
	
	// When activity is stopped, store all values
	@Override
    protected void onStop() {
        super.onStop();
        final SeekBar seekbar2 = (SeekBar) findViewById(R.id.seekBar2);
	    maxWrongGuesses = seekbar2.getProgress();
	    final SeekBar seekbar1 = (SeekBar) findViewById(R.id.seekBar1);
	    wordLength = seekbar1.getProgress();
	    SharedPreferences preferences = getSharedPreferences("MyPreferences", Context.MODE_PRIVATE);
		SharedPreferences.Editor editor = preferences.edit();
        editor.putInt("max_wrong_guesses", maxWrongGuesses);
        editor.putInt("word_length", wordLength);
        editor.commit(); 
        
    }
	
	
}
