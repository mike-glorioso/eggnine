/**
 * 
 */
package com.eggnine.api.batchprocessing;

import java.util.Collection;
import java.util.Comparator;

/**
 * 
 *
 * @since 0
 */
public interface BatchFilter<B extends Batch<?>> {
	
	public B getBatch(Collection<B> batches, Comparator<B> comparator, Integer index);
	
	public Collection<B> getBatches(Collection<B> batches);
}
