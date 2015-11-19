/**
 * 
 */
package com.eggnine.guessandbet;

import com.eggnine.api.batchprocessing.BatchInputTest;

/**
 * Test {@link PlayerInput} by overriding the #getBatchInput() method
 * @since 1
 *
 */
public class PlayerInputTest extends BatchInputTest {
	Player player = new PlayerTest().getPlayer();
	@Override
	public PlayerInput getBatchInput() {
		return new PlayerInput(player) {

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
	
	public void submittedByTest() {
		PlayerInput input = getBatchInput();
		Player player = input.submittedBy();
		assert player != null;
		assert player == this.player;
	}
}
