/**
 * 
 */
package com.eggnine.api.batchprocessing;

import java.util.Collection;
import java.util.Iterator;

import com.eggnine.api.batchprocessing.BatchInputListener.BatchInputListenerException;
import com.eggnine.api.batchprocessing.BatchInputListener.BatchInputProvider;

/**
 * test the minimum requirements for a {@link BatchInputListener}
 * 
 * @since 0
 */
public class BatchInputListenerTest {
	BatchInput input = new BatchInputTest().getBatchInput();
	
	<I extends BatchInput> BatchInputListener<I> getBatchInputListener(final Boolean acceptingInputs) {
		return new BatchInputListener<I>() {
			BatchInputProvider<I> provider = new BatchInputProvider<I>() {

				@Override
				public Iterator<I> iterator() {
					// TODO Auto-generated method stub
					return null;
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
			};
		};
	}
	
	/**
	 * test association of the {@link BatchInput} with a {@link BatchInputProvider} when the listener is accepting
	 * @throws BatchInputListenerException 
	 * 
	 */
	public void addBatchInputAcceptingTest() throws BatchInputListenerException {
		BatchInputListener<BatchInput> listener = getBatchInputListener(true);
		assert listener.isAcceptingInputs();
		BatchInputProvider<BatchInput> provider = listener.addBatchInput(input);
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
	public void addBatchInputNotAcceptingTest() throws BatchInputListenerException {
		BatchInputListener<BatchInput> listener = getBatchInputListener(false);
		assert !listener.isAcceptingInputs();
		BatchInput input = new BatchInputTest().getBatchInput();
		BatchInputProvider<BatchInput> provider = listener.addBatchInput(input);
		assert provider == null;
	}

}
