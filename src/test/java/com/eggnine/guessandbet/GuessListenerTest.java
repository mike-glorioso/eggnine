/**
 * 
 */
package com.eggnine.guessandbet;

import com.eggnine.api.batchprocessing.BatchInputListenerTest;

/**
 * Test {@link GuessListener} by overriding the #getBatchInputListener()
 * @since 1
 *
 */
public class GuessListenerTest extends BatchInputListenerTest {
	public GuessListener getBatchListener() {
		return new GuessAndBetEngine();
	}

}
