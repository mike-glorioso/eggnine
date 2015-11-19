/**
 * 
 */
package com.eggnine.guessandbet;

import com.eggnine.api.batchprocessing.BatchInput;

/**
 * Take care of some of the common work between the Inputs
 * @since 1
 *
 */
public abstract class AbstractInput implements BatchInput {

	/* (non-Javadoc)
	 * @see com.eggnine.api.batchprocessing.BatchInput#addInputProcessStatusListener(com.eggnine.api.batchprocessing.BatchInput.BatchInputProcessStatusListener)
	 */
	@Override
	public Boolean addInputProcessStatusListener(
			BatchInputProcessStatusListener listener) {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see com.eggnine.api.batchprocessing.BatchInput#removeInputProcessStatusListener(com.eggnine.api.batchprocessing.BatchInput.BatchInputProcessStatusListener)
	 */
	@Override
	public Boolean removeInputProcessStatusListener(
			BatchInputProcessStatusListener listener) {
		// TODO Auto-generated method stub
		return null;
	}

}
