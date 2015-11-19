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

	@Override
	protected GuessRound getRound() {
		return new GuessRound();
	}
	
	@Override
	public com.eggnine.api.batchprocessing.BatchInputListener.BatchInputProvider<BatchInput> addBatchInput(
			BatchInput input)
			throws com.eggnine.api.batchprocessing.BatchInputListener.BatchInputListenerException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public com.eggnine.api.batchprocessing.BatchInputListener.BatchInputProvider<MaxBet> addBatchInput(
			MaxBet maxBet) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public com.eggnine.api.batchprocessing.BatchInputListener.BatchInputProvider<MinBet> addBatchInput(
			MinBet minBet) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public com.eggnine.api.batchprocessing.BatchInputListener.BatchInputProvider<Guess> addBatchInput(
			Guess guess) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Boolean isAcceptingInputs() {
		// TODO Auto-generated method stub
		return null;
	}

}
