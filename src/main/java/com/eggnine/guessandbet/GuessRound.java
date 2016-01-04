/**
 * 
 */
package com.eggnine.guessandbet;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Random;
import java.util.Set;

import com.eggnine.roundengine.BatchInputProviderImpl;
import com.eggnine.roundengine.Round;

/**
 * a batch specific to the needs of the guess and bet game
 * @since 1
 *
 */
public class GuessRound extends Round implements GuessListener, MinBetListener, MaxBetListener {
	private static final Integer INITIAL_COIN_COUNT = 100;
	private static final String ROUND_PROCESSING_STARTED = "Processing next round.";
	private static final String ROUND_LOW = "Lowest Coin Count is %d.";
	private static final String PLAYER_FOLDS = "%s folds!";
	private static final String ANNOUNCE_SECRET = "The secret number is %d.";
	private static final String ROUND_BET = "Round Bet is %d.";
	private static final String PLAYER_PAID = "%s was paid %d coins.";
	private static final String POT_REMAINING = "%d remains in the pot";
	private final Map<Player,Guess> guesses = new HashMap<>();
	private final Map<Player,MinBet> minBets = new HashMap<>();
	private final Map<Player,MaxBet> maxBets = new HashMap<>();
	private final BatchInputProvider<Guess> guessProvider = new BatchInputProviderImpl<Guess>(guesses.values());
	private final BatchInputProvider<MinBet> minBetProvider = new BatchInputProviderImpl<MinBet>(minBets.values());
	private final BatchInputProvider<MaxBet> maxBetProvider = new BatchInputProviderImpl<MaxBet>(maxBets.values());

	@Override
	public BatchInputProvider<Guess> addBatchInput(Guess guess) {
		guesses.put(guess.submittedBy(),guess);
		return guessProvider;
	}

	@Override
	public BatchInputProvider<MinBet> addBatchInput(
			MinBet minBet) {
		minBets.put(minBet.submittedBy(),minBet);
		return minBetProvider;
	}
	
	@Override
	public BatchInputProvider<MaxBet> addBatchInput(
			MaxBet maxBet) {
		maxBets.put(maxBet.submittedBy(),maxBet);
		return maxBetProvider;
	}
	
	@Override
	public void processRound() {
		System.out.println(ROUND_PROCESSING_STARTED);
		Set<Player> players = new HashSet<>();
		players.addAll(guesses.keySet());
		players.retainAll(minBets.keySet());
		players.retainAll(maxBets.keySet());
		
		Integer roundBet = getRoundBet(players);
		Integer minGuess = GuessAndBetEngine.MIN_GUESS;
		Integer maxGuess = GuessAndBetEngine.MAX_GUESS;
		Integer secret = new Random().nextInt(maxGuess - minGuess + 1) + minGuess;
		System.out.println(String.format(ANNOUNCE_SECRET, secret));

		Iterator<Player> playerIter = players.iterator();
		for(Player player = null; playerIter.hasNext();) {
			player = playerIter.next();
			MaxBet maxBet = maxBets.get(player);
			if(maxBet.getBet() < roundBet) {
				MinBet minBet = minBets.get(player);
				player.removeFromCount(minBet.getBet());
				GuessAndBetEngine.pot += minBet.getBet();
				System.out.println(String.format(PLAYER_FOLDS, player.getName()));
				players.remove(player);
			} else {
				player.removeFromCount(roundBet);
				GuessAndBetEngine.pot += roundBet;
			}
		}
		Collection<Player> winners = getClosestNotOverPlayers(players, secret);
		if(winners.isEmpty()) {
			winners = getClosestPlayers(players, secret);
		}
		Integer playerCount = winners.size();
		Integer payout = GuessAndBetEngine.pot / playerCount;
		for(Player player : winners) {
			player.addToCount(payout);
			GuessAndBetEngine.pot -= payout;
			System.out.println(String.format(PLAYER_PAID, player.getName(), payout));
			assert GuessAndBetEngine.pot >= 0;
		}
		System.out.println(String.format(POT_REMAINING, GuessAndBetEngine.pot));		
	}

	private Collection<Player> getClosestNotOverPlayers(Set<Player> players, Integer secret) {
		Collection<Player> closestNotOverPlayers = new ArrayList<Player>();
		Integer closestNotOverGuess = null;
		for(Player player: players) {
			Integer guess = guesses.get(player).getGuess();
			if(guess <= secret) {
				if(closestNotOverPlayers.isEmpty()) {
					closestNotOverPlayers.add(player);
					closestNotOverGuess = guess;
				} else if(guess == closestNotOverGuess) {
					closestNotOverPlayers.add(player);
				} else if(guess > closestNotOverGuess) {
					closestNotOverPlayers.removeAll(players);
					closestNotOverPlayers.add(player);
					closestNotOverGuess = guess;
				}
			}
		}
		return closestNotOverPlayers;
	}

	private Collection<Player> getClosestPlayers(Set<Player> players, Integer secret) {
		Collection<Player> closestPlayers = new ArrayList<Player>();
		Integer closestGuess = null;
		for(Player player: players) {
			Integer guess = guesses.get(player).getGuess();
			if(closestPlayers.isEmpty()) {
				closestPlayers.add(player);
				closestGuess = guess;
			} else if(guess == closestGuess) {
				closestPlayers.add(player);
			} else if(guess < closestGuess) {
				closestPlayers.removeAll(players);
				closestPlayers.add(player);
				closestGuess = guess;
			}
		}
		return closestPlayers;
	}

	private Integer getRoundBet(Collection<Player> players) {
		Integer lowestCoinCount = players.size() * INITIAL_COIN_COUNT;
		for(Player player: players) {
			Integer coinCount = player.getCoinCount();
			if(lowestCoinCount > coinCount) {
				lowestCoinCount = coinCount;
			}
		}
		System.out.println(String.format(ROUND_LOW, lowestCoinCount));

		Integer roundBet = 0;
		for(MinBet minBet: minBetProvider) {
			Integer bet = minBet.getBet();
			if(roundBet < bet ) {
				roundBet = bet;
			}
		}
		
		if(roundBet > lowestCoinCount) {
			roundBet = lowestCoinCount;
		}
		System.out.println(String.format(ROUND_BET, roundBet));
		return roundBet;
	}

}
