/**
 * 
 */
package com.eggnine.api.batchprocessing;

import java.util.ArrayList;
import java.util.Collection;

import com.eggnine.api.batchprocessing.BatchInput.BatchInputProcessStatusListener;
import com.eggnine.api.batchprocessing.BatchInput.BatchInputStatus;

/**
 * test {@link BatchInput} the basic contract of {@link BatchInput}
 * 
 * @since 0
 */
public class BatchInputTest {
	
	BatchInput input = getBatchInput();
	
	void setup() {
		
	}
	
	public BatchInput getBatchInput() {
		return new BatchInput() {
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
				// TODO Auto-generated method stub
				
			}
			
		};
	}
	
	/**
	 * This tests the main point of the {@link BatchInput}
	 * 
	 */
	void processSuccessTest() {
		Batch<BatchInput> batch = new BatchTest().getBatch();
		input.process(batch);
	}

	/**
	 * Add a listener to be notified in the event of status updates
	 * @param listener
	 * @return true iff the listener was added
	 */
	void addInputProcessStatusListenerTest(BatchInputProcessStatusListener listener) {
		input.addInputProcessStatusListener(listener);
	}
	
	/**
	 * Add a listener to be notified in the event of status updates
	 * @param listener
	 * @return true iff the listener was added
	 */
	void addMultipleInputProcessStatusListenersTest(BatchInputProcessStatusListener ... listeners) {
		for(BatchInputProcessStatusListener listener: listeners) {
			input.addInputProcessStatusListener(listener);
		}
	}
	
	/**
	 * 
	 * @param listener the listener to be removed
	 * @return true if all references to the listener were removed
	 * 
	 */
	void removeInputProcessStatusListenerTest(BatchInputProcessStatusListener listener) {
		input.removeInputProcessStatusListener(listener);
	}
	
	/**
	 * 
	 * @param listener the listener to be removed
	 * @return true if all references to the listener were removed
	 * 
	 */
	void removeMultipleInputProcessStatusListenerTest(BatchInputProcessStatusListener ... listeners) {
		for(BatchInputProcessStatusListener listener: listeners) {
			input.removeInputProcessStatusListener(listener);
		}
	}
	
	/**
	 * 
	 * @param listener the listener to be removed
	 * @return true if all references to the listener were removed
	 * 
	 */
	void addThenRemoveInputProcessStatusListenerTest(BatchInputProcessStatusListener listener) {
		input.addInputProcessStatusListener(listener);
		input.removeInputProcessStatusListener(listener);
	}
	
	/**
	 * 
	 * @param listener the listener to be removed
	 * @return true if all references to the listener were removed
	 * 
	 */
	void addSameInputProcessStatusListenerTest(BatchInputProcessStatusListener listener) {
		input.removeInputProcessStatusListener(listener);
		input.removeInputProcessStatusListener(listener);
	}
	
	/**
	 * 
	 * @param listener the listener to be removed
	 * @return true if all references to the listener were removed
	 * 
	 */
	void removeSameInputProcessStatusListenerTest(BatchInputProcessStatusListener listener) {
		input.addInputProcessStatusListener(listener);
		input.addInputProcessStatusListener(listener);
	}
	
	void updateStatusTest(BatchInputProcessStatusListener listener, BatchInputStatus s) {
		listener.updateStatus(s);
	}
	
	void getStatusTest(BatchInputStatus status) {
		status.toString();
	}
}
