/**
 * 
 */
package com.eggnine.api.batchprocessing;

import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import org.junit.Test;

import com.eggnine.api.batchprocessing.BatchInputListener.BatchInputListenerException;
import com.eggnine.api.batchprocessing.BatchInputListener.BatchInputProvider;

/**
 * test the minimum requirements for a {@link BatchInputListener}
 * 
 * @since 0
 */
public class BatchInputListenerTest<I extends BatchInput,L extends BatchInputListener<I>> {
	protected I input = new BatchInputTest<I>().getBatchInput();
	
	@SuppressWarnings("unchecked") // subclasses must override
	public L getBatchInputListener(final Boolean acceptingInputs) {
		BatchInputListener<?> listener = new BatchInputListener<I>() {
			BatchInputProvider<I> provider = new BatchInputProvider<I>() {

				@Override
				public Iterator<I> iterator() {
					List<I> emptyList = Collections.emptyList(); 
					return emptyList.iterator();
				}

				@Override
				public Collection<I> getBatchInputs() {
					// TODO Auto-generated method stub
					return null;
				}
			};

			@Override
			public BatchInputProvider<I> addBatchInput(I input) {
				return provider;
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
		assert listener.isAcceptingInputs();
		BatchInputProvider<I> provider = listener.addBatchInput(input);
		assert provider != null;
		Boolean found = false;
		for(BatchInput i: provider) {
			if(i.equals(input)) {
				found = true;
				break;
			}
		}
		assert found == true;
	}
	
	/**
	 * test association of the {@link BatchInput} with a {@link BatchInputProvider} when the listener is not accepting
	 * @throws BatchInputListenerException 
	 * 
	 */
	@Test
	public void addBatchInputNotAcceptingTest() throws BatchInputListenerException {
		BatchInputListener<I> listener = getBatchInputListener(false);
		assert !listener.isAcceptingInputs();
		BatchInputProvider<I> provider = listener.addBatchInput(input);
		assert provider == null;
	}

}
