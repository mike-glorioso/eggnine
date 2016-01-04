/**
 * 
 */
package com.eggnine.roundengine;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.eggnine.api.batchprocessing.BatchFilter;
import com.eggnine.api.batchprocessing.BatchProcessor;
import com.eggnine.api.batchprocessing.TriggerValidationFailure;

/**
 * @since 0
 *
 */
public abstract class RoundBasedEngine<R extends Round> implements BatchProcessor<R> {
	
	private List<R> rounds;
	
	public RoundBasedEngine() {
		rounds = new ArrayList<R>();
		rounds.add(getRound());
	}
	
	abstract protected R getRound();

	@Override
	public Collection<R> getBatches() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Map<R, Collection<TriggerValidationFailure>> triggerBatchProcessing(BatchFilter<R> filter) {
		if(filter == null) {
			filter = new BatchFilter<R>() {

				@Override
				public R getBatch(Collection<R> batches,
						Comparator<R> comparator, Integer index) {
					List<R> filteredBatches = getBatches(batches);
					Collections.sort(filteredBatches, comparator);
					return filteredBatches.get(index);
				}

				@Override
				public List<R> getBatches(Collection<R> batches) {
					return new ArrayList<R>(batches);
				}
				
			};
		}
		Collection<R> filteredRounds = filter.getBatches(rounds);
		List<R> sortedRounds = new ArrayList<>(rounds.size());
		sortedRounds.addAll(filteredRounds);
		Collections.sort(sortedRounds);
		Map<R, Collection<TriggerValidationFailure>> validatedRounds = new HashMap<>(filteredRounds.size());
		Boolean noRoundSkipped = true;
		for(R round: sortedRounds) {
			if(noRoundSkipped && round.isAcceptingInputs()) {
				round.process();
				validatedRounds.put(round, new ArrayList<TriggerValidationFailure>());
			} else {
				Collection<TriggerValidationFailure> failures = new ArrayList<TriggerValidationFailure>();
				if(noRoundSkipped) {
					noRoundSkipped = false;
					failures.add(new ProcessingStartedFailure());
				} else if(round.isAcceptingInputs()) {
					failures.add(new RoundOutOfSequenceFailure());
				} else {
					failures.add(new ProcessingStartedFailure());
				}
				validatedRounds.put(round, failures);
			}
		}
		return validatedRounds;
	}
	
	private class ProcessingStartedFailure implements TriggerValidationFailure {

		@Override
		public <E extends Exception> E getCause() {
			return null;
		}

		@Override
		public String getMessage() {
			return "Processing has alread started";
		}
		
	}
	
	private class RoundOutOfSequenceFailure implements TriggerValidationFailure {

		@Override
		public <E extends Exception> E getCause() {
			return null;
		}

		@Override
		public String getMessage() {
			return "Processing of the round was attempted out of order";
		}
		
	}

}
