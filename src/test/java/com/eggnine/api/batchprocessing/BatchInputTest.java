/**
 * 
 */
package com.eggnine.api.batchprocessing;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.junit.Test;

import com.eggnine.api.batchprocessing.BatchInput.BatchInputProcessStatusListener;

/**
 * test {@link BatchInput} the basic contract of {@link BatchInput}
 * 
 * @since 0
 */
public class BatchInputTest<I extends BatchInput> {
	
	protected I input = getBatchInput();
	protected List<BatchInputListener<I>> inputListeners = new ArrayList<>();
	protected List<BatchInputProcessStatusListener> statusListeners = new ArrayList<>();
	
	{
		BatchInputListenerTest<I,? extends BatchInputListener<I>> inputListenerTest = new BatchInputListenerTest<>();
		BatchInputListener<I> inputListener = inputListenerTest.getBatchInputListener(true);
		inputListeners.add(inputListener);
		inputListener = inputListenerTest.getBatchInputListener(true);
		inputListeners.add(inputListener);
		BatchInputProcessStatusListenerTest processListenerTest = new BatchInputProcessStatusListenerTest();
		BatchInputProcessStatusListener statusListener = processListenerTest.getStatusListener();
		statusListeners.add(statusListener);
		statusListener = processListenerTest.getStatusListener();
		statusListeners.add(statusListener);
	}
	
	void setup() {
		
	}
	
	@SuppressWarnings("unchecked") // subclasses must override this
	public I getBatchInput() {
		return (I) new BatchInput() {
			Collection<BatchInputProcessStatusListener> listeners = new ArrayList<>();

			@Override
			public void process(Batch<? extends BatchInput> batch) {
				// do nothing = success
			}

			@Override
			public Boolean addInputProcessStatusListener(
					BatchInputProcessStatusListener listener) {
				if(listeners.contains(listener)) {
					return false;
				} else {
					listeners.add(listener);
					return true;
				}
			}

			@Override
			public Boolean removeInputProcessStatusListener(
					BatchInputProcessStatusListener listener) {
				listeners.remove(listener);
				return listeners.contains(listener);
			}

			@Override
			public void validate(Batch<? extends BatchInput> b) {
				// do nothing = success
			}
			
		};
	}
	
	/**
	 * This tests the main point of the {@link BatchInput}
	 * 
	 */
	@Test
	public void processSuccessTest() {
		Batch<BatchInput> batch = new BatchTest().getBatch();
		input.process(batch);
	}
	
	/**
	 * This tests the validation prior to processing
	 * 
	 */
	@Test
	public void validateSuccessTest() {
		Batch<BatchInput> batch = new BatchTest().getBatch();
		input.validate(batch);
	}

	/**
	 * Add a listener to be notified in the event of status updates
	 * @param listener
	 * @return true iff the listener was added
	 */
	@Test
	public void addInputProcessStatusListenerTest() {
		BatchInputProcessStatusListener listener = statusListeners.get(0);
		input.addInputProcessStatusListener(listener);
	}
	
	/**
	 * Add a listener to be notified in the event of status updates
	 * @param listener
	 * @return true iff the listener was added
	 */
	@Test
	public void addMultipleInputProcessStatusListenersTest() {
		for(BatchInputProcessStatusListener listener: statusListeners) {
			input.addInputProcessStatusListener(listener);
		}
	}
	
	/**
	 * 
	 * @param listener the listener to be removed
	 * @return true if all references to the listener were removed
	 * 
	 */
	@Test
	public void removeInputProcessStatusListenerTest() {
		input.removeInputProcessStatusListener(statusListeners.get(0));
	}
	
	/**
	 * 
	 * @param listener the listener to be removed
	 * @return true if all references to the listener were removed
	 * 
	 */
	@Test
	public void removeMultipleInputProcessStatusListenerTest() {
		for(BatchInputProcessStatusListener listener: statusListeners) {
			input.removeInputProcessStatusListener(listener);
		}
	}
	
	/**
	 * 
	 * @param listener the listener to be removed
	 * @return true if all references to the listener were removed
	 * 
	 */
	@Test
	public void addThenRemoveInputProcessStatusListenerTest() {
		BatchInputProcessStatusListener listener = statusListeners.get(0);
		input.addInputProcessStatusListener(listener);
		input.removeInputProcessStatusListener(listener);
	}
	
	/**
	 * 
	 * @param listener the listener to be removed
	 * @return true if all references to the listener were removed
	 * 
	 */
	@Test
	public void addSameInputProcessStatusListenerTest() {
		BatchInputProcessStatusListener listener = statusListeners.get(0);
		input.removeInputProcessStatusListener(listener);
		input.removeInputProcessStatusListener(listener);
	}
	
	/**
	 * 
	 * @param listener the listener to be removed
	 * @return true if all references to the listener were removed
	 * 
	 */
	@Test
	public void removeSameInputProcessStatusListenerTest() {
		BatchInputProcessStatusListener listener = statusListeners.get(0);
		input.addInputProcessStatusListener(listener);
		input.addInputProcessStatusListener(listener);
	}

	@Test
	public void updateStatusTest() {
		BatchInputProcessStatusListener listener = statusListeners.get(0);
		listener.updateStatus(new BatchInputStatusTest().getBatchInputStatus());
	}
}
