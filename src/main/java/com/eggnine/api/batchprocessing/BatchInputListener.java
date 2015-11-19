/**
 * 
 */
package com.eggnine.api.batchprocessing;

import java.util.Collection;

/**
 * accept {@link BatchInput}s and associate them with a {@link BatchInputProvider}
 * 
 * @since 0
 */
public interface BatchInputListener<I extends BatchInput> {

	public interface BatchInputProvider<I> extends Iterable<I> {
		/**
		 * provides a {@link Collection} of the {@link BatchInput}s
		 * 
		 * @return {@link Collection} of {@link BatchInput}s
		 */
		Collection<I> getBatchInputs();
	}
	
	class BatchInputListenerException extends Exception {

		/**
		 * 
		 */
		private static final long serialVersionUID = 7474591792876784959L;

		public BatchInputListenerException() {
			super("The BatchInputListener is not currently accepting inputs");
		}
	};
	
	/**
	 * associates the {@link BatchInput} with a {@link BatchInputProvider} as long as {@link #isAcceptingInputs()} is true
	 * 
	 * @param input the {@link BatchInput} to add
	 * @return {@link BatchInputProvider} that the parameter was associated with or null
	 * @throws {@link BatchInputListenerException} if the listener is not currently accepting inputs
	 */
	public BatchInputProvider<I> addBatchInput(I input) throws BatchInputListenerException;

	/**
	 * indicates if the listener will accept inputs
	 * 
	 * @return true iff the listener will accept inputs and return a provider
	 */
	public Boolean isAcceptingInputs();
}
