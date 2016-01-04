/**
 * 
 */
package com.eggnine.guessandbet;

import java.util.Random;

import org.junit.Test;

/**
 * Test the basics provided by {@link Player}
 * 
 * @since 1
 *
 */
public class PlayerTest {
	static Integer INITIAL_COUNT = 100;
	private static Integer MAX_COUNT = 1000;
	Random random = new Random();
	GuessAndBetEngine batchProcessor = new GuessAndBetEngineTest().getBatchProcessor();
	
	public Player getPlayer() {
		return new Player("Player Name", INITIAL_COUNT, batchProcessor, batchProcessor, batchProcessor);
	}

	@Test
	public void addToCountTest() {
		Player player = getPlayer();
		player.addToCount(random.nextInt(MAX_COUNT + 1));
	}

	@Test
	public void addToCountNegativeTest() {
		Player player = getPlayer();
		player.addToCount(random.nextInt(MAX_COUNT + 1) * -1);
	}

	@Test
	public void removeFromCountPassTest() {
		Player player = getPlayer();
		player.removeFromCount(random.nextInt(INITIAL_COUNT) + 1);
		
	}

	@Test
	public void removeFromCountFailTest() {
		Player player = getPlayer();
		player.removeFromCount(random.nextInt(MAX_COUNT) + INITIAL_COUNT + 1);
		
	}

	public void submitInput(InputType inputType, Player player) {
		switch(inputType) {
		case GUESS:
			player.provideGuess(new GuessTest().getValidGuess());
			break;
		case MIN_BET:
			player.provideMinBet(new MinBetTest().getValidMinBet(INITIAL_COUNT));
			break;
		case MAX_BET:
			player.provideMaxBet(new MaxBetTest().getValidMaxBet(INITIAL_COUNT));
			break;
		default:
			throw new IllegalArgumentException(String.format("%s is not listed for InputTypes in PlayerTest", inputType));	
		}
	}

	public void submitInput(InputType inputType, Player player, Boolean isOutOfRangeHigh) {
		Integer value = 0;
		switch(inputType) {
		case GUESS:
			value = new GuessTest().getOutOfRangeGuess(isOutOfRangeHigh);
			break;
		case MIN_BET:
			value = new MinBetTest().getOutOfRangeMinBet(INITIAL_COUNT, MAX_COUNT, isOutOfRangeHigh);
			break;
		case MAX_BET:
			value = new MaxBetTest().getOutOfRangeMaxBet(INITIAL_COUNT, MAX_COUNT, isOutOfRangeHigh);
			break;
		default:
			throw new IllegalArgumentException(String.format("%s is not listed for InputTypes in PlayerTest", inputType));	
		}
		submitInput(inputType, player, value);
	}

	public void submitInput(InputType inputType, Player player, Integer value) {
		switch(inputType) {
		case GUESS:
			player.provideGuess(value);
			break;
		case MIN_BET:
			player.provideMinBet(value);
			break;
		case MAX_BET:
			player.provideMaxBet(value);
			break;
		default:
			throw new IllegalArgumentException(String.format("%s is not listed for InputTypes in PlayerTest", inputType));	
		}
	}

	//TODO: make parameterized by inputType
	@Test
	public void submitInputTwice() {
		InputType inputType = InputType.GUESS;
		Player player = getPlayer();
		submitInput(inputType, player);
		submitInput(inputType, player);
	}

	@Test
	public void submitAllInputs() {
		Player player = getPlayer();
		submitInput(InputType.GUESS, player);
		submitInput(InputType.MIN_BET, player);
		submitInput(InputType.MAX_BET, player);
	}

	@Test
	public void submitMultipleInputs() {
		Player player = getPlayer();
		submitInput(InputType.GUESS, player);
		submitInput(InputType.MIN_BET, player);
		submitInput(InputType.GUESS, player);
		submitInput(InputType.MAX_BET, player);
		submitInput(InputType.MAX_BET, player);
		submitInput(InputType.MIN_BET, player);
	}

	@Test
	public void submitInputTooHigh() {
		InputType inputType = InputType.MAX_BET;
		Player player = getPlayer();
		submitInput(inputType, player, true);
		
	}

	@Test
	public void submitInputTooLow() {
		InputType inputType = InputType.MIN_BET;
		Player player = getPlayer();
		submitInput(inputType, player, false);
		
	}
}
