/**
 * 
 */
package com.eggnine.guessandbet;

import java.util.Date;
import java.util.Random;

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
	private Random random = new Random(new Date().getTime());
	
	public Player(Integer coinCount, GuessListener guess, MinBetListener minBet, MaxBetListener maxBet) {
		
	}
	
	public void addToCount(Integer count) {
		
	}
	
	public void removeFromCount(Integer count) {
		
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
}
