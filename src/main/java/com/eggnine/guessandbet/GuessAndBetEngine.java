/**
 * 
 */
package com.eggnine.guessandbet;

import com.eggnine.api.batchprocessing.BatchInput;
import com.eggnine.roundengine.RoundBasedEngine;

/**
 * the processing engine for the guess and bet game
 * 
 * @since 1
 *
 */
public class GuessAndBetEngine extends RoundBasedEngine<GuessRound> implements GuessListener, MinBetListener, MaxBetListener {

	public static final Integer MIN_GUESS = 1;
	public static final Integer MAX_GUESS = 100;
	private Boolean acceptingInputs = false;
	static Integer pot = 0;

	@Override
	protected GuessRound getRound() {
		return new GuessRound();
	}
	
	@Override
	public com.eggnine.api.batchprocessing.BatchInputListener.BatchInputProvider<BatchInput> addBatchInput(
			BatchInput input)
			throws com.eggnine.api.batchprocessing.BatchInputListener.BatchInputListenerException {
		throw new BatchInputListenerException();
	}

	@Override
	public BatchInputProvider<MaxBet> addBatchInput(
			MaxBet maxBet) {
		return getRound().addBatchInput(maxBet);
	}

	@Override
	public BatchInputProvider<MinBet> addBatchInput(
			MinBet minBet) {
		return getRound().addBatchInput(minBet);
	}

	@Override
	public BatchInputProvider<Guess> addBatchInput(
			Guess guess) {
		return getRound().addBatchInput(guess);
	}

	@Override
	public Boolean isAcceptingInputs() {
		return this.acceptingInputs;
	}

	void setAcceptingInputs(Boolean b) {
		this.acceptingInputs = b;
	}

}
