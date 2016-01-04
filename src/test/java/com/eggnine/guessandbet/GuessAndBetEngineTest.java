/**
 * 
 */
package com.eggnine.guessandbet;


import java.util.Collection;
import java.util.Random;

import org.junit.Test;

import com.eggnine.api.batchprocessing.BatchFilterTest;
import com.eggnine.api.batchprocessing.BatchInput;
import com.eggnine.api.batchprocessing.BatchInputListener.BatchInputListenerException;
import com.eggnine.api.batchprocessing.BatchInputListener.BatchInputProvider;
import com.eggnine.roundengine.RoundEngineTest;

/**
 * @since 1
 *
 */
public class GuessAndBetEngineTest extends RoundEngineTest<GuessRound> {
	Random random = new Random();
	
	public GuessAndBetEngine getBatchProcessor() {
		return new GuessAndBetEngine();
	}
	
	public Integer getValidGuess() {
		return GuessAndBetEngine.MIN_GUESS + random.nextInt(GuessAndBetEngine.MAX_GUESS);
	}

	public Integer getOutOfRangeGuess(Boolean isTooHigh) {
		Integer value;
		if(isTooHigh) {
			value = GuessAndBetEngine.MAX_GUESS + random.nextInt(Integer.MAX_VALUE);
		} else {
			value = GuessAndBetEngine.MIN_GUESS - 1 - random.nextInt(Integer.MAX_VALUE - 1);
		}
		return value;
	}

	@Test
	public void addBatchInputMaxBetTest() throws BatchInputListenerException {
		GuessAndBetEngine engine = getBatchProcessor();
		BatchInputProvider<MaxBet> maxBetProvider = null;
		maxBetProvider = engine.addBatchInput(new MaxBetTest().getBatchInput());
		assert maxBetProvider != null;
		Collection<? extends BatchInput> inputs = maxBetProvider.getBatchInputs();
		assert inputs != null;
		if(!inputs.isEmpty()) {
			for(BatchInput input: inputs) {
				assert input != null;
				assert input instanceof MaxBet;
			}
		}
	}

	@Test
	public void addBatchInputMinBetTest() throws BatchInputListenerException {
		GuessAndBetEngine engine = getBatchProcessor();
		BatchInputProvider<MinBet> minBetProvider = null;
		MinBet minBet = new MinBetTest().getBatchInput();
		minBetProvider = engine.addBatchInput(minBet);
		assert minBetProvider != null;
		Collection<? extends BatchInput> inputs = minBetProvider.getBatchInputs();
		assert inputs != null;
		if(!inputs.isEmpty()) {
			for(BatchInput input: inputs) {
				assert input != null;
				assert input instanceof MinBet;
			}
		}
	}

	@Test
	public void addBatchInputGuessTest() throws BatchInputListenerException {
		GuessAndBetEngine engine = getBatchProcessor();
		BatchInputProvider<Guess> guessProvider = null;
		Guess guess = new GuessTest().getBatchInput();
		guessProvider = engine.addBatchInput(guess);
		assert guessProvider != null;
		Collection<? extends BatchInput> inputs = guessProvider.getBatchInputs();
		assert inputs != null;
		if(!inputs.isEmpty()) {
			for(BatchInput input: inputs) {
				assert input != null;
				assert input instanceof Guess;
			}
		}
	}

	@Test
	public void isAcceptingInputsTest() {
		Boolean accept = getBatchProcessor().isAcceptingInputs();
		assert accept != null;
	}
	
	@Test
	public void processTest() {
		getBatchProcessor().triggerBatchProcessing(new BatchFilterTest<GuessRound>().getBatchFilter());
	}
}
