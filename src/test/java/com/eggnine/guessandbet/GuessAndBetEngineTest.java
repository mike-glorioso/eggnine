/**
 * 
 */
package com.eggnine.guessandbet;


import java.util.Collection;
import java.util.Random;

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

	public void addBatchInputMaxBetTest() throws BatchInputListenerException {
		GuessAndBetEngine engine = getBatchProcessor();
		BatchInputProvider<BatchInput> maxBetProvider = null;
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

	public void addBatchInputMinBetTest() throws BatchInputListenerException {
		GuessAndBetEngine engine = getBatchProcessor();
		BatchInputProvider<BatchInput> minBetProvider = null;
		minBetProvider = engine.addBatchInput(new MinBetTest().getBatchInput());
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

	public void addBatchInputGuessTest() throws BatchInputListenerException {
		GuessAndBetEngine engine = getBatchProcessor();
		BatchInputProvider<BatchInput> guessProvider = null;
		guessProvider = engine.addBatchInput(new GuessTest().getBatchInput());
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

	public void isAcceptingInputsTest() {
		Boolean accept = getBatchProcessor().isAcceptingInputs();
		assert accept != null;
	}
	
	public void processTest() {
		getBatchProcessor().triggerBatchProcessing(new BatchFilterTest<GuessRound>().getBatchFilter());
	}
}
