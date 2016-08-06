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

import org.junit.Test;
import java.util.Collections;

/**
 *
 * @since 0
 */
public class BatchProcessorTest<B extends Batch<BatchInput>> {

	public BatchProcessor<B> getBatchProcessor() {
		return (BatchProcessor<B>) new BatchProcessor<Batch<BatchInput>>() {
			Collection<Batch<BatchInput>> batches = Collections.singletonList(new BatchTest().getBatch());

			@Override
			public Map<Batch<BatchInput>, Collection<TriggerValidationFailure>> triggerBatchProcessing(
					BatchFilter<Batch<BatchInput>> filter) {
				Collection<Batch<BatchInput>> filteredBatches = filter.getBatches(getBatches());
				Map<Batch<BatchInput>, Collection<TriggerValidationFailure>> results = new HashMap<>();
				for(Batch<BatchInput> batch : filteredBatches) {
					results.put(batch, null);
				}
				return results;
			}

			@Override
			public Collection<Batch<BatchInput>> getBatches() {
				return batches;
			}
			
		};
	}

	@Test
	public void returnBatchesTest() {
		BatchProcessor<B> batchProcessor = getBatchProcessor();
		if(batchProcessor.getBatches() == null) {
			throw new AssertionError("getBatches returned null");
		}
		if(batchProcessor.getBatches().isEmpty()) {
			throw new AssertionError("getBatches returned empty collection");
		}
	}
	
	@Test
	public void successBatchProcessingTest() {
		BatchProcessor<B> batchProcessor = getBatchProcessor();
		BatchFilter<B> noReturnsFilter = new BatchFilterTest<B>().getBatchFilter();
		Map<B, Collection<TriggerValidationFailure>> results = batchProcessor.triggerBatchProcessing(noReturnsFilter);
		List<Collection<TriggerValidationFailure>> invalidResults = new ArrayList<>();
		invalidResults.addAll(results.values());
		invalidResults.removeAll(null);
		assert invalidResults.isEmpty();
	}

}
