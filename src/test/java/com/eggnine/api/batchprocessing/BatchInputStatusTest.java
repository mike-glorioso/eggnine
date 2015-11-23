/**
 * 
 */
package com.eggnine.api.batchprocessing;

import org.junit.Test;

import com.eggnine.api.batchprocessing.BatchInput.BatchInputStatus;

/**
 * @since 1
 *
 */
public class BatchInputStatusTest {

	public BatchInputStatus getBatchInputStatus() {
		return new BatchInputStatus() {
			//do nothing yet
		};
	}

	@Test
	public void getStatusTest() {
		String s = getBatchInputStatus().toString();
		assert s != null;
	}

}
