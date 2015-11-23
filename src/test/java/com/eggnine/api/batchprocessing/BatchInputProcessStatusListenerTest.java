/**
 * 
 */
package com.eggnine.api.batchprocessing;

import com.eggnine.api.batchprocessing.BatchInput.BatchInputProcessStatusListener;
import com.eggnine.api.batchprocessing.BatchInput.BatchInputStatus;

/**
 * @since 1
 *
 */
public class BatchInputProcessStatusListenerTest {

	public BatchInputProcessStatusListener getStatusListener() {
		return new BatchInputProcessStatusListener() {

			@Override
			public void updateStatus(BatchInputStatus s) {
				//do nothing = success
			}
			
		};
	}

}
