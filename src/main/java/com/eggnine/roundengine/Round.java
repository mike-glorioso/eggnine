/**
 * 
 */
package com.eggnine.roundengine;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.Date;
import java.util.Iterator;

import com.eggnine.api.batchprocessing.BatchInput;
import com.eggnine.api.batchprocessing.BatchInputListener;
import com.eggnine.api.batchprocessing.BatchInputListener.BatchInputProvider;
import com.eggnine.api.batchprocessing.SequentialBatch;

/**
 * Represents one Round of play
 * 
 * @since 0
 *
 */
public class Round implements SequentialBatch<BatchInput>, BatchInputListener<BatchInput>, BatchInputProvider<BatchInput> {

	private Date createdAt;
	private Date processingStartedAt = null;
	private Date processingEndedAt = null;
	private Boolean successful = false;
	private Collection<BatchInput> inputs;
	
	protected Round() {
		createdAt = new Date();
		inputs = new ArrayList<BatchInput>();
	}

	@Override
	public Date getCreatedAt() {
		return createdAt;
	}

	@Override
	public Date getProcessingStartedAt() {
		return processingStartedAt;
	}

	@Override
	public Date getProcessingEndedAt() {
		return processingEndedAt;
	}

	@Override
	public Boolean isSuccessful() {
		return successful;
	}

	@Override
	public Iterator<BatchInput> iterator() {
		final Iterator<BatchInput> iterator = inputs.iterator();
		return new Iterator<BatchInput>() {

			@Override
			public boolean hasNext() {
				return iterator.hasNext();
			}

			@Override
			public BatchInput next() {
				return iterator.next();
			}

			@Override
			public void remove() {
				new UnsupportedOperationException("The Round inputs list is immutable");
			}
			
		};
	}

	@Override
	public Collection<BatchInput> getBatchInputs() {
		Collection<BatchInput> newList = new ArrayList<BatchInput>(inputs.size());
		newList.addAll(inputs);
		return newList;
	}

	@Override
	public com.eggnine.api.batchprocessing.BatchInputListener.BatchInputProvider<BatchInput> addBatchInput(
			BatchInput input) throws com.eggnine.api.batchprocessing.BatchInputListener.BatchInputListenerException {
		if(!isAcceptingInputs()) {
			throw new BatchInputListenerException();
		}
		input.hashCode(); //test not-null
		inputs.add(input);
		return this;
	}

	@Override
	public Boolean isAcceptingInputs() {
		return processingStartedAt == null;
	}

	@Override
	public final void process() {
		processingStartedAt = new Date();
		processRound();
		processingEndedAt = new Date();
	}

	public void processRound() {
	}

	@Override
	public int compareTo(SequentialBatch<BatchInput> arg0) {
		return createdAt.compareTo(arg0.getCreatedAt());
	}

	@Override
	public Comparator<Round> getComparator() {
		return new Comparator<Round>() {

			@Override
			public int compare(Round arg0, Round arg1) {
				return arg0.createdAt.compareTo(arg1.createdAt);
			}
			
		};
	}
}
