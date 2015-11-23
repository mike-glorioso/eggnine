/**
 * 
 */
package com.eggnine.guessandbet;

import java.util.Random;

/**
 * Test {@link MinBet} by overriding the #getBatchInput(), etc.
 * @since 1
 *
 */
public class MinBetTest extends PlayerInputTest<MinBet> {
	Random random = new Random();
	Integer currentBet = getValidMinBet(PlayerTest.INITIAL_COUNT);
	public MinBet getPlayerInput() {
		return new MinBet(player, currentBet);
	}
	
	public MinBet getBatchInput() {
		return new MinBet(player, currentBet);
	}

	public Integer getValidMinBet(Integer coinCount) {
		return random.nextInt(coinCount) + 1;
	}
	
	public Integer getOutOfRangeMinBet(Integer coinCount, Integer maxCount, Boolean isTooHigh) {
		Integer value;
		if(isTooHigh) {
			value = coinCount + random.nextInt(maxCount) + 1;
		} else {
			value = random.nextInt(maxCount) * -1;
		}
		return value;
	}
	
	public void getBetTest() {
		MinBet minBet = getBatchInput();
		Integer bet = minBet.getBet();
		assert bet != null;
		assert bet > 0;
	}
}
