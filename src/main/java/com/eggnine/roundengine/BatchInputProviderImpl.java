/**
 * 
 */
package com.eggnine.roundengine;

import java.util.Collection;

import com.eggnine.api.batchprocessing.BatchInput;
import com.eggnine.api.batchprocessing.BatchInputListener.BatchInputProvider;
import com.eggnine.util.Provider;

/**
 * @since 1
 *
 */
public class BatchInputProviderImpl<I extends BatchInput> extends Provider<I> implements BatchInputProvider<I> {

	public BatchInputProviderImpl(Collection<I> collection) {
		super(collection);
	}

	@Override
	public Collection<I> getBatchInputs() {
		return asNewCollection();
	}

}
