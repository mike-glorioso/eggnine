/**
 * 
 */
package com.eggnine.guessandbet;

import java.util.Random;


/**
 * Test {@link Guess} by overriding the #getBatchInput(), etc.
 * @since 1
 *
 */
public class GuessTest extends PlayerInputTest {
	Random random = new Random();
	Integer currentGuess = getValidGuess();
	public Guess getPlayerInput() {
		return new Guess(player, currentGuess);
	}
	
	public Integer getValidGuess() {
		return GuessAndBetEngine.MIN_GUESS + random.nextInt(GuessAndBetEngine.MAX_GUESS);
	}
	

	public Integer getOutOfRangeGuess(Boolean isTooHigh) {
		Integer value;
		if(isTooHigh) {
			value = GuessAndBetEngine.MAX_GUESS + random.nextInt(Integer.MAX_VALUE);
		} else {
			value = GuessAndBetEngine.MIN_GUESS - 1 - random.nextInt(Integer.MAX_VALUE - 1);
		}
		return value;
	}
}
