/**
 * 
 */
package com.eggnine.api.batchprocessing;

/**
 * {@link BatchInput}s are the items processed in a {@link Batch}
 * 
 * @since 0
 */
public interface BatchInput {
	/**
	 * {@link BatchInputSatus} should override the {@link #toString()} method
	 * 
     * @since 0
	 */
	interface BatchInputStatus {
		@Override
		public String toString();

	}
	
	/**
	 * Listener interface for the status reporting
	 * 
     * @since 0
	 */
	public interface BatchInputProcessStatusListener {
		/**
		 * callback method for the listener to indicate the {@link BatchInputStatus} has changed
		 * 
		 * @param Status of the processing of the BatchInput
		 */
		void updateStatus(BatchInputStatus s);
		
		//TODO: include this if a value can be shown
//		/**
//		 * enable listeners to be removed if they are no longer being utilized
//		 * @return false unless the listener is interacting with an active process
//		 */
//		Boolean isListening();
		
		 //TODO: add status filter

	}
	
	/**
	 * This is the main point of the {@link BatchInput}, What it does
	 * 
	 */
	void process(Batch<? extends BatchInput> b);
	
	/**
	 * This is the main point of the {@link BatchInput}, What it does
	 * 
	 */
	void validate(Batch<? extends BatchInput> b);

	/**
	 * Add a listener to be notified in the event of status updates
	 * @param listener
	 * @return true iff the listener was added (not if it already existed but was not added)
	 */
	Boolean addInputProcessStatusListener(BatchInputProcessStatusListener listener);
	
	/**
	 * 
	 * @param listener the listener to be removed
	 * @return true if all references to the listener were removed
	 * 
	 * TODO: change to lookup listeners rather than strong reference
	 */
	Boolean removeInputProcessStatusListener(BatchInputProcessStatusListener listener); 
}
