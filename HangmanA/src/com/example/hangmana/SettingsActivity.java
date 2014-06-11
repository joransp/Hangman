package com.example.hangmana;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;
import android.widget.TextView;

public class SettingsActivity extends Activity {
	
	TextView textElement;
	public static int maxWrongGuesses;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_settings);
		
		textElement = (TextView) findViewById(R.id.textView1);
		
		final SeekBar wordLength = (SeekBar) findViewById(R.id.seekBar1);
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
        
        final SeekBar wrongGuesses = (SeekBar) findViewById(R.id.seekBar2);
        wrongGuesses.setOnSeekBarChangeListener(new OnSeekBarChangeListener() {
            
			@Override
			public void onProgressChanged(SeekBar arg0, int arg1, boolean arg2) {
				// TODO Auto-generated method stub
				maxWrongGuesses = arg1;
				((TextView) findViewById(R.id.wrongGuesses)).setText("Wrong guesses allowed: " + Integer.toString(maxWrongGuesses));
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
	

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.settings, menu);
		return true;
	}
	
	

}
