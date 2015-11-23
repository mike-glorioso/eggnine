/**
 * 
 */
package com.eggnine.api.batchprocessing;

import java.util.Date;

import org.junit.Test;

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
				//do nothing = success
			}
		};
		
	}

	/**
	 * {@link Batch#getCreatedAt()} should never be null and should be after the current date/time
	 */
	@Test
	public void getCreatedAtTest() {
		Batch<?> batch = getBatch();
		Date date = batch.getCreatedAt();
		assert date != null;
		assert date.after(new Date());
	}

	/**
	 * {@link Batch#getProcessingStartedAt()} should be null or should be after the created date/time
	 */
	@Test
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
	@Test
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
	@Test
	public void isSuccessfulTest() {
		Batch<?> batch = getBatch();
		Boolean success = batch.isSuccessful();
		assert success != null;
		assert success == true || success == false;
	}
	
	@Test
	public void processTest() {
		Batch<?> batch = getBatch();
		batch.process();
	}
}
