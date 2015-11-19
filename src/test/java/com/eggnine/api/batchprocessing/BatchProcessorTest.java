/**
 * 
 */
package com.eggnine.api.batchprocessing;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @since 0
 */
public class BatchProcessorTest<B extends Batch<BatchInput>> {

	 public BatchProcessor<B> getBatchProcessor() {
		return new BatchProcessor<B>() {

			@Override
			public Map<B, Collection<TriggerValidationFailure>> triggerBatchProcessing(
					BatchFilter<B> filter) {
				Collection<B> filteredBatches = filter.getBatches(getBatches());
				Map<B, Collection<TriggerValidationFailure>> results = new HashMap<>();
				for(B batch : filteredBatches) {
					results.put(batch, null);
				}
				return results;
			}

			@Override
			public Collection<B> getBatches() {
				return Collections.emptyList();
			}
			
		};
	}

	void returnBatchesTest() {
		BatchProcessor<B> batchProcessor = getBatchProcessor();
		assert batchProcessor.getBatches() != null;
		assert !batchProcessor.getBatches().isEmpty();
	}
	
	void successBatchProcessingTest() {
		BatchProcessor<B> batchProcessor = getBatchProcessor();
		BatchFilter<B> noReturnsFilter = new BatchFilterTest<B>().getBatchFilter();
		Map<B, Collection<TriggerValidationFailure>> results = batchProcessor.triggerBatchProcessing(noReturnsFilter);
		List<Collection<TriggerValidationFailure>> invalidResults = new ArrayList<>();
		invalidResults.addAll(results.values());
		invalidResults.removeAll(null);
		assert invalidResults.isEmpty();
	}

}
