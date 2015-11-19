/**
 * 
 */
package com.eggnine.api.batchprocessing;

import java.util.Comparator;

/**
 * SequentialBatches may be processed in order
 * @since 0
 */
public interface SequentialBatch<B extends BatchInput> extends Batch<B>, Comparable<SequentialBatch<B>>{
	/**
	 * the comparator used to order {@link SequentialBatch}es
	 * @return
	 */
	public Comparator<? extends SequentialBatch<B>> getComparator();
	
}
