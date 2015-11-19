/**
 * 
 */
package com.eggnine.api.batchprocessing;

import java.util.Date;

/**
 * the minimum requirements for a batch
 *
 * @since 0
 */
public interface Batch<B extends BatchInput> {

	/**
	 * provide the date and time when the {@link Batch} was created
	 * @return {@link Date} (and time) when the {@link Batch} was created
	 */
	Date getCreatedAt();
	
	/**
	 * provide the date and time when processing began
	 * @return {@link Date} (and time) of processing began
	 */
	Date getProcessingStartedAt();
	
	/**
	 * provide the date and time when processing ended
	 * @return {@link Date} (and time) of processing ended
	 */
	Date getProcessingEndedAt();
	
	/**
	 * indicate if the {@link Batch} has completed processing successfully
	 * @return false unless processing has started, ended, and is not flagged as being unsuccessful
	 */
	Boolean isSuccessful();
	
	/**
	 * process the batch
	 */
	void process();
}
