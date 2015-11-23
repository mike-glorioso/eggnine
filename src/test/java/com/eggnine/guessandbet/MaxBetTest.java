/**
 * 
 */
package com.eggnine.guessandbet;

import java.util.Random;

/**
 * Test {@link MaxBet} by overriding the #getBatchInput(), etc.
 * @since 1
 *
 */
public class MaxBetTest extends PlayerInputTest<MaxBet> {
	Random random = new Random();
	Integer currentBet = getValidMaxBet(PlayerTest.INITIAL_COUNT);	

	public MaxBet getBatchInput() {
		return new MaxBet(player, currentBet);
	}
	
	public Integer getValidMaxBet(Integer initialCount) {
		return random.nextInt(initialCount) + 1;
	}
	
	public Integer getOutOfRangeMaxBet(Integer initialCount, Integer maxCount, Boolean isTooHigh) {
		Integer value;
		if(isTooHigh) {
			value = initialCount + random.nextInt(maxCount) + 1;
		} else {
			value = random.nextInt(maxCount) * -1;
		}
		return value;
	}
	
	public void getBetTest() {
		MaxBet maxBet = getBatchInput();
		Integer bet = maxBet.getBet();
		assert bet != null;
		assert bet > 0;
	}
	
}
