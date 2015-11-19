/**
 * 
 */
package com.eggnine.api.batchprocessing;

/**
 * Provides information about why a trigger did not result in processing
 *
 * @since 0
 */
public interface TriggerValidationFailure {
	
	/**
	 * provides the exception if any that prevented the processing
	 * @return cause or null
	 */
	<E extends Exception> E getCause();
	
	/**
	 * provides a message indicating why processing was prevented
	 * @return message
	 */
	String getMessage();

}
