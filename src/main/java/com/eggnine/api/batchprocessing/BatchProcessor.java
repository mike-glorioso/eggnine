/**
 * 
 */
package com.eggnine.api.batchprocessing;

import java.util.Collection;
import java.util.Map;

/**
 * Processes Batches returned by a filter
 * @since 0
 */
public interface BatchProcessor<B extends Batch<?>> {
	/**
	 * attempts to trigger processing of zero or more batches 
	 * 
	 * @param filter to determine which batch(es) to process
	 * @return map of batch(es) that may have been triggered with validation failures for any that were not triggered
	 */
	Map<B,Collection<TriggerValidationFailure>> triggerBatchProcessing(BatchFilter<B> filter);
	
	/**
	 * provides all batches known to the processor
	 * 
	 * @return a collection of one or more batches
	 */
	Collection<B> getBatches();
	
}
