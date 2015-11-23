/**
 * 
 */
package com.eggnine.roundengine;

import org.junit.Test;

import com.eggnine.api.batchprocessing.BatchTest;


/**
 * @since 0
 *
 */
public class RoundTest extends BatchTest {
	public Round getBatch() {
		return new Round();
	}
	
	@Test
	public void roundComparatorTest() {
		Round round1 = getBatch();
		Round round2 = getBatch();
		Integer result = round1.getComparator().compare(round1, round2);
		assert round1.compareTo(round2) == result;
	}

}
