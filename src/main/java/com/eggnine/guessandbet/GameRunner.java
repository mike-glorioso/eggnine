/**
 * 
 */
package com.eggnine.guessandbet;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Random;

/**
 * @since 1
 *
 */
public class GameRunner {
	private static final Integer INITIAL_COUNT = 100;
	private static final Integer MIN_PLAYERS = 2;
	private static final Integer MAX_PLAYERS = 10;
	private static final Integer ONE_SECOND = 1000;
	private static final String ANNOUNCE_WINNER = "And the winner is: %s";
	private static final String[] PLAYER_NAMES = {"Alice", "Bob", "Charlie", "David", "Ezra", "Frank", "George", "Henry", "Isabella", "Jennifer"};
	static Random random = new Random();

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		GuessAndBetEngine gabe = new GuessAndBetEngine();
		GuessListener guessListener = gabe;
		MinBetListener minBetListener = gabe;
		MaxBetListener maxBetListener = gabe;
		Collection<Player> players = new ArrayList<>();
		for(Integer i = random.nextInt(MAX_PLAYERS - MIN_PLAYERS);i < MAX_PLAYERS;i++) {
			players.add(new Player(PLAYER_NAMES[i], INITIAL_COUNT, guessListener, minBetListener, maxBetListener));
		}
		gabe.setAcceptingInputs(true);
		do {
			for(Player player: players) {
				player.provideGuess(random.nextInt(GuessAndBetEngine.MAX_GUESS - GuessAndBetEngine.MIN_GUESS + 1));
				Integer minBet = random.nextInt(player.getCoinCount()) + 1;
				player.provideMinBet(minBet);
				Integer maxBet = random.nextInt(player.getCoinCount() - minBet) + minBet + 1;
				player.provideMaxBet(maxBet);
			}
			GuessRound guessRound = gabe.getRound();
			gabe.triggerBatchProcessing(null);
			Boolean successful = true;
			while(guessRound.getProcessingEndedAt() == null) {
				try {
					Thread.sleep(ONE_SECOND);
				} catch (InterruptedException e) {
					e.printStackTrace();
					successful = false;
				}
			}
			//TODO: add handling for failure case
			if(successful) {
				Player winning = null;
				Collection<Player> losingPlayers = new ArrayList<>();
				for(Player player : players) {
					Integer playerCoinCount = player.getCoinCount();
					assert playerCoinCount >= 0;
					if(playerCoinCount == 0) {
						losingPlayers.add(player);
					} else {
						if(winning == null) {
							winning = player;
						} else if(winning.getCoinCount() < player.getCoinCount()) {
							winning = player;
						}
					}
				}
				players.removeAll(losingPlayers);
				assert players.size() > 0;
				if(players.size() == 1) {
					gabe.setAcceptingInputs(false);
					System.out.println(String.format(ANNOUNCE_WINNER, winning.getName()));
				}
			}
		} while(gabe.isAcceptingInputs());
	}

}
