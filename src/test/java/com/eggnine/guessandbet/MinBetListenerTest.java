/**
 * 
 */
package com.eggnine.guessandbet;

import com.eggnine.api.batchprocessing.BatchInputListenerTest;

/**
 * Test {@link MinBetListener} by overriding the #getBatchInputListener()
 * @since 1
 *
 */
public class MinBetListenerTest extends BatchInputListenerTest {
	public MinBetListener getBatchListener() {
		return new GuessAndBetEngine();
	}
}
