/**
 * 
 */
package com.eggnine.guessandbet;

import com.eggnine.api.batchprocessing.BatchInputListener;
import com.eggnine.api.batchprocessing.BatchInputListenerTest;

/**
 * Test {@link MinBetListener} by overriding the #getBatchInputListener()
 * @since 1
 *
 */
public class MaxBetListenerTest extends BatchInputListenerTest<MaxBet, BatchInputListener<MaxBet>> {
	public MaxBetListener getBatchInputListener() {
		return new GuessAndBetEngine();
	}
}
