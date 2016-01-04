/**
 * 
 */
package com.eggnine.guessandbet;


/**
 * Represents a minimum bet input for a round
 * @since 1
 *
 */
public class MinBet extends PlayerInput {
	private static final Integer ANTE = 1;

	private Integer minBet;
	
	public MinBet(Player player, Integer minBet) {
		super(player);
		if(minBet < ANTE || minBet > player.getCoinCount()) {
			throw new IllegalArgumentException(String.format("Minimum Bet of %d is not applicable to player with CoinCount %d", minBet, player.getCoinCount()));
		}
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

	public Integer getBet() {
		return minBet;
	}

}
