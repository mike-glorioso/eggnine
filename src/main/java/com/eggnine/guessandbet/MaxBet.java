/**
 * 
 */
package com.eggnine.guessandbet;


/**
 * Represents a maximum bet input for a round
 * @since 1
 *
 */
public class MaxBet extends PlayerInput {

	private Integer maxBet;
	
	public MaxBet(Player player, Integer maxBet) {
		super(player);
		this.maxBet = maxBet;
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
