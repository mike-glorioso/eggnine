/**
 * 
 */
package com.eggnine.roundengine;

import com.eggnine.api.batchprocessing.BatchProcessorTest;

/**
 * @since 0
 *
 */
public class RoundEngineTest<R extends Round> extends BatchProcessorTest<R> {
	public RoundBasedEngine<R> getBatchProcessor() {
		return new RoundBasedEngine<R>() {

			@SuppressWarnings("unchecked") // subclasses must override
			@Override
			protected R getRound() {
				return (R) new Round();
			}
			
		};
	}
	
	
}