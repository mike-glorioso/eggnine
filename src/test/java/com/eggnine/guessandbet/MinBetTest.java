/**
 * 
 */
package com.eggnine.guessandbet;

import java.util.Random;

import org.junit.Before;

/**
 * Test {@link MinBet} by overriding the #getBatchInput(), etc.
 * @since 1
 *
 */
public class MinBetTest extends PlayerInputTest<MinBet> {
	Random random = new Random();
	Integer currentBet = 1;
	
	@Before
	@Override
	public void setup() {
		if(currentBet == null) {
			currentBet = 1;
		}
		super.setup();
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
			Integer ante = MinBet.ANTE;
			value = random.nextInt(ante);
		}
		return value;
	}
	
	public void getValidBetTest() {
		currentBet = getValidMinBet(player.getCoinCount());
		MinBet minBet = getBatchInput();
		Integer bet = minBet.getBet();
		assert bet != null;
		assert bet > 0;
	}
}
