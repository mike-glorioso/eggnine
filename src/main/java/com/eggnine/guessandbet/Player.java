/**
 * 
 */
package com.eggnine.guessandbet;


/**
 * Represents a player in the guess and bet game
 * 
 * @since 1
 *
 */
public class Player {
	private Integer coinCount;
	private GuessListener guess;
	private MinBetListener minBet;
	private MaxBetListener maxBet;
	private final String name;
	
	public Player(String name, Integer coinCount, GuessListener guess, MinBetListener minBet, MaxBetListener maxBet) {
		this.name = name;
		this.coinCount = coinCount;
		this.guess = guess;
		this.minBet = minBet;
		this.maxBet = maxBet;
	}
	
	/**
	 * Adds the count to the player's coinCount if the count is in range
	 * 
	 * @param count the amount to add
	 */
	public void addToCount(Integer count) {
		synchronized(coinCount) {
			if(count <= 0 || count > Integer.MAX_VALUE - this.coinCount) {
				throw new IllegalArgumentException(String.format("CoinCount %d cannot be added to player CoinCount %d", count, this.coinCount));
			}
			this.coinCount += count;
		}
	}
	
	/**
	 * Removes the count from the player's coinCount if the count is in range 
	 * @param count
	 */
	public void removeFromCount(Integer count) {
		synchronized(coinCount) {
			if(count <= 0 || count > this.coinCount) {
				throw new IllegalArgumentException(String.format("CoinCount %d cannot be removed from player CoinCount %d", count, this.coinCount));
			}
		}
	}

	/**
	 * Provides the player's CoinCount
	 * @return CoinCount
	 */
	public Integer getCoinCount() {
		return coinCount;
	}
	
	public void provideGuess(Integer guessValue) {
		guess.addBatchInput(new Guess(this, guessValue));
	}
	
	public void provideMinBet(Integer minBetValue) {
		minBet.addBatchInput(new MinBet(this, minBetValue));
	}
	
	public void provideMaxBet(Integer maxBetValue) {
		maxBet.addBatchInput(new MaxBet(this, maxBetValue));
	}

	public String getName() {
		return this.name;
	}

}
