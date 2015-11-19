/**
 * 
 */
package com.eggnine.guessandbet;


/**
 * Represents a guess input for a round
 * 
 * @since 1
 *
 */
public class Guess extends PlayerInput {
	private Integer guess;
	
	public Guess(Player player, Integer guess) {
		super(player);
		this.guess = guess;
	}

	@Override
	public void process(GuessRound guessRound) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void validate(GuessRound guessRound) {
		// TODO Auto-generated method stub
		
	}
}
