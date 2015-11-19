/**
 * 
 */
package com.eggnine.api.batchprocessing;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;


/**
 * test the minimum requirements for a {@link BatchFilter}
 *
 * @since 0
 */
public class BatchFilterTest<B extends Batch<?>>  {

	/**
	 * batch filter provider for tests, override to test real implementations
	 * 
	 * @return
	 */
	public BatchFilter<B> getBatchFilter() {
		return new BatchFilter<B>() {

			@Override
			public B getBatch(Collection<B> batches,
					Comparator<B> comparator, Integer index) {
				Collection<B> filteredBatches = getBatches(batches);
				if(filteredBatches.isEmpty()) {
					return null;
				}
				List<B> listBatches = new ArrayList<B>(filteredBatches);
				Collections.sort(listBatches, comparator);
				return listBatches.get(index);
			}

			@Override
			public Collection<B> getBatches(Collection<B> batches) {
				return Collections.emptyList();
			}
		};
	}

	/**
	 * batch collection provider for tests, override to test real implementations
	 * 
	 * @return a collection of batches
	 */
	@SuppressWarnings("unchecked") // must override for subclasses
	protected Collection<B> getBatches() {
		return (Collection<B>)Collections.singletonList(new BatchTest().getBatch());
	}

	/**
	 * batch comparator provider for tests, override to test real implementations
	 * 
	 * @return a basic created date comparator
	 */
	protected Comparator<B> getComparator() {
		return new Comparator<B>() {
			@Override
			public int compare(B o1, B o2) {
				return o1.getCreatedAt().compareTo(o2.getCreatedAt());
			}
		};
	}
	
	/**
	 * {@link BatchFilter#getBatch()} should be null or should be contained by the collection parameter
	 */
	public void getBatchTest() {
		Collection<B> batches = getBatches();
		BatchFilter<B> batchFilter = getBatchFilter();
		Comparator<B> comparator = getComparator();
		B filteredBatch = batchFilter.getBatch(batches, comparator, 0);
		assert filteredBatch == null || batches.contains(filteredBatch);
	}
	
	/**
	 * {@link BatchFilter#getBatches()} should never be null and should be a subset of the collection parameter
	 */
	public void getBatchesTest() {
		Collection<B> batches = getBatches();
		BatchFilter<B> batchFilter = getBatchFilter();
		Collection<B> filteredBatches = batchFilter.getBatches(batches);
		assert filteredBatches != null;
		assert batches.containsAll(filteredBatches);
	}
}
