/**
 * 
 */
package com.eggnine.guessandbet;


/**
 * Represents a maximum bet input for a round
 * @since 1
 *
 */
public class MinBet extends PlayerInput {

	private Integer minBet;
	
	public MinBet(Player player, Integer minBet) {
		super(player);
		this.minBet = minBet;
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
