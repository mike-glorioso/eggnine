/**
 * 
 */
package com.eggnine.guessandbet;

import org.junit.Before;
import org.junit.Test;

import com.eggnine.api.batchprocessing.BatchInputTest;

/**
 * Test {@link PlayerInput} by overriding the #getBatchInput() method
 * @since 1
 *
 */
public class PlayerInputTest<I extends PlayerInput> extends BatchInputTest<I> {
	Player player = null;
	
	@Before
	@Override
	public void setup() {
		if(player == null) {
			player = new PlayerTest().getPlayer();
		}
		super.setup();
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public I getBatchInput() {
		return (I) new PlayerInput(player) {

			@Override
			public void process(GuessRound guessRound) {
				//do nothing = success
			}

			@Override
			public void validate(GuessRound guessRound) {
				// TODO Auto-generated method stub
				
			}
			
		};
	}

	@Test
	public void submittedByTest() {
		PlayerInput input = getBatchInput();
		Player player = input.submittedBy();
		assert player != null;
		assert player == this.player;
	}
}
