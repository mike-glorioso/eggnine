/**
 * 
 */
package com.eggnine.guessandbet;

import com.eggnine.api.batchprocessing.Batch;
import com.eggnine.api.batchprocessing.BatchInput;


/**
 * Allow consumers to track Players
 * 
 * @since 1
 *
 */
public abstract class PlayerInput extends AbstractInput {
	protected final Player player;
	
	public PlayerInput(Player player) {
		this.player = player;
	}
	
	public Player submittedBy() {
		return player;
	}

	@Override
	public void process(Batch<? extends BatchInput> batch) {
		if(batch instanceof GuessRound) {
			GuessRound guessRound = (GuessRound) batch;
			process(guessRound);
		}
	}
	
	public abstract void process(GuessRound guessRound);
	
	@Override
	public void validate(Batch<? extends BatchInput> batch) {
		if(batch instanceof GuessRound) {
			GuessRound guessRound = (GuessRound) batch;
			process(guessRound);
		}
	}
	
	public abstract void validate(GuessRound guessRound);

}
