/**
 * 
 */
package com.eggnine.api.batchprocessing;

import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.ArrayList;

import org.junit.Test;

import com.eggnine.api.batchprocessing.BatchInputListener.BatchInputListenerException;
import com.eggnine.api.batchprocessing.BatchInputListener.BatchInputProvider;

import com.eggnine.util.Provider;

/**
 * test the minimum requirements for a {@link BatchInputListener}
 * 
 * @since 0
 */
public class BatchInputListenerTest<I extends BatchInput,L extends BatchInputListener<I>> {
	protected I input = new BatchInputTest<I>().getBatchInput();

	private class BatchInputProviderImpl<I extends BatchInput> extends Provider<I> implements BatchInputProvider<I> {

		public BatchInputProviderImpl(Collection<I> collection) {
			super(collection);
		}

		@Override
		public Collection<I> getBatchInputs() {
			return asNewCollection();
		}
	}
	
	@SuppressWarnings("unchecked") // subclasses must override
	public L getBatchInputListener(final Boolean acceptingInputs) {
		BatchInputListener<?> listener = new BatchInputListener<I>() {
			Collection<I> inputs = new ArrayList<I>();
			BatchInputProvider<I> provider = new BatchInputProviderImpl<I>(inputs);

			@Override
			public BatchInputProvider<I> addBatchInput(I input) {
				if(acceptingInputs) {
					inputs.add(input);
					return provider;
				} else {
					return null;
				}
			}

			@Override
			public Boolean isAcceptingInputs() {
				return acceptingInputs;
			}
			
		};
		return (L) listener;
	}
	
	/**
	 * test association of the {@link BatchInput} with a {@link BatchInputProvider} when the listener is accepting
	 * @throws BatchInputListenerException 
	 * 
	 */
	@Test
	public void addBatchInputAcceptingTest() throws BatchInputListenerException {
		L listener = getBatchInputListener(true);
		if(!listener.isAcceptingInputs()) {
			throw new AssertionError("isAcceptinginputs returned false");
		}
		BatchInputProvider<I> provider = listener.addBatchInput(input);
		if(provider == null) {
			throw new AssertionError("addBatchInput returned a null provider");
		}
		Boolean found = false;
		for(BatchInput i: provider) {
			if(i.equals(input)) {
				found = true;
				break;
			}
		}
		if(!found) {
			throw new AssertionError("BatchInputProvider did not contain input");
		}
	}
	
	/**
	 * test association of the {@link BatchInput} with a {@link BatchInputProvider} when the listener is not accepting
	 * @throws BatchInputListenerException 
	 * 
	 */
	@Test
	public void addBatchInputNotAcceptingTest() throws BatchInputListenerException {
		BatchInputListener<I> listener = getBatchInputListener(false);
		if(listener.isAcceptingInputs()) {
			throw new AssertionError("isAcceptinginputs returned true");
		}
		BatchInputProvider<I> provider = listener.addBatchInput(input);
		if(provider != null) {
			throw new AssertionError("addBatchInput returned a non-null provider");
		}
	}

}
