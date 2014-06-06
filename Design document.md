Design document
===============

Database tables
---------------
To save the high-scores of the user a database table will be implemented. The database will save for each found word the word itself, the length of the word, the number of wrong guesses and the score. 

Classes
-------
Several classes will be implemented. The classes are defined as below.

*Gameplay*, containing:
*	*string word*
	This string contains the word to be guessed.
*	*int wrongGuesses*
	This integer stores the number of wrong guesses made.
* 	*string visibleLetters*
	This string stores all guessed letters and leaves the not guessed letters as underscores.
*	*public void guess(letter)*
	The function *guess(letter)* checks if the inputted letter is in the word to be guessed. If so, the letter will be added to *visibleLetters*. If not, *wrongGuesses* will add up by one and a part of the gallows will be drawn.
*	*int score*
	This integer stores the current score calculated by the length of the word minus the number of wrong guesses.
*	*public void end()*
	The function *end()* checks whether the game is lost, won or still in progress. 

Settings, containing:
*	*int wordLength*
	This integer stores the number of letters chosen for the word to be guessed.
*	*public void wordLengthPlus()*
	This function adds *wordLength* up by one.
*	*public void wordLengthMinus()*
	This function lowers wordLength by one.
*	*int guesses*
	This integer stores the number of guesses allowed.
*	*public void guessesPlus()*
	This function adds *guesses* up by one.
*	*public void guessesMinus()*
	This function lowers *guesses* by one.


