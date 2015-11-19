/**
 * 
 */
package com.eggnine.api.batchprocessing;

import java.util.Date;

/**
 * test the minimum requirements for a {@link Batch}
 *
 * @since 0
 */
public class BatchTest {
	/**
	 * batch provider for tests, override to test real implementations
	 * 
	 * @return
	 */
	public <B extends BatchInput> Batch<B> getBatch() {
		return new Batch<B>() {

			@Override
			public Date getCreatedAt() {
				return new Date();
			}

			@Override
			public Date getProcessingStartedAt() {
				return null;
			}

			@Override
			public Date getProcessingEndedAt() {
				return null;
			}

			@Override
			public Boolean isSuccessful() {
				return false;
			}
			
			@Override
			public void process() {
				throw new UnsupportedOperationException(String.format("%s.process() is not supported. Use a proper implementation of %s", this.getClass(), this.getClass()));
			}
		};
		
	}

	/**
	 * {@link Batch#getCreatedAt()} should never be null and should be after the current date/time
	 */
	public void getCreatedAtTest() {
		Batch<?> batch = getBatch();
		Date date = batch.getCreatedAt();
		assert date != null;
		assert date.after(new Date());
	}

	/**
	 * {@link Batch#getProcessingStartedAt()} should be null or should be after the created date/time
	 */
	public void getProcessingStartedAtTest() {
		Batch<?> batch = getBatch();
		Date date = batch.getProcessingStartedAt();
		if(date != null) {
			assert date.after(batch.getCreatedAt());
		}
	}
	
	/**
	 * {@link Batch#getProcessingEndedAt()} should be null or should be after the startedAt date/time
	 */
	public void getProcessingEndedAtTest() {
		Batch<?> batch = getBatch();
		Date date = batch.getProcessingEndedAt();
		if(date != null) {
			assert date.after(batch.getProcessingStartedAt());
		}
	}
	
	/**
	 * {@link Batch#isSuccessful()} should be true or false;
	 */
	public void isSuccessfulTest() {
		Batch<?> batch = getBatch();
		Boolean success = batch.isSuccessful();
		assert success != null;
		assert success == true || success == false;
	}
}
