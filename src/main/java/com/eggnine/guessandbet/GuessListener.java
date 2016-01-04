/**
 * 
 */
package com.eggnine.guessandbet;

import com.eggnine.api.batchprocessing.BatchInput;
import com.eggnine.api.batchprocessing.BatchInputListener;

/**
 * Intermediate interface to allow a class to implement BatchInputListener
 * multiple times with different generic types
 * 
 * @since 1
 *
 */
public interface GuessListener extends BatchInputListener<BatchInput> {
	
	public BatchInputProvider<Guess> addBatchInput(Guess guess);
}
