package model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Deque;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;

import model.interfaces.GameEngine;
import model.interfaces.Player;
import model.interfaces.PlayingCard;
import model.interfaces.PlayingCard.Suit;
import model.interfaces.PlayingCard.Value;
import view.interfaces.GameEngineCallback;


public class GameEngineImpl implements GameEngine {
	private HashSet<GameEngineCallback> gameEngineCallbacks = new HashSet<GameEngineCallback>();
	private HashMap<String, Player> allPlayers = new HashMap<String, Player>();
	Deque<PlayingCard> cards = null;

	//Cards initialization
	public GameEngineImpl() {
		cards = getShuffledDeck();
	}

	@Override
	public void dealPlayer(Player player, int delay) {
		int cardScore = 0;
		for(GameEngineCallback gameEngineCallback : gameEngineCallbacks) {
			for(int i=0; i < PlayingCard.DECK_SIZE; i++) {
				try {
					//Deal Card to player
					PlayingCard card = dealCard(delay);
					//Calculate player's score
					cardScore = calculatePlayerCardScore(player.getResult(), card);
					//Determine whether the current card points + player's last points exceed 21 points.
					if(cardScore <= this.BUST_LEVEL) {
						//If no more than 21 points, set the player's result according to the current score.
						player.setResult(cardScore);
						gameEngineCallback.nextCard(player, card, this);
					}else {
						//More than 21 points. Display burst information and the result of players on the console.
						gameEngineCallback.bustCard(player, card, this);
						gameEngineCallback.result(player, player.getResult(), this);
						break;
					}
				}catch(java.util.NoSuchElementException e) {
					//If the number of cards is not enough, re-shuffle for deal again
					cards = getShuffledDeck();
				}
			}
		}
	}

	@Override
	public void dealHouse(int delay) {
		for(GameEngineCallback gameEngineCallback : gameEngineCallbacks) {
			int houseResult = 0;
			int cardScore = 0;
			for(int i=0; i < PlayingCard.DECK_SIZE; i++) {
				try {
					//Deal Card to House
					PlayingCard card = dealCard(delay);
					//Calculate House's score.
					cardScore = calculatePlayerCardScore(houseResult, card);
					if(cardScore <= this.BUST_LEVEL) {
						//Determine whether the current card points + House's last points exceed 21 points.
						houseResult = cardScore;
						gameEngineCallback.nextHouseCard(card, this);
					}else {
						//More than 21 points. Display burst information and the result of House on the console.
						gameEngineCallback.houseBustCard(card, this);
						for(Player player : getAllPlayers()) {
							//Player win£¬player get bets
							if(player.getResult() > houseResult)
								player.setPoints(player.getPoints() + player.getBet());
							//Player lose£¬player lost bets
							else if(player.getResult() < houseResult)
								player.setPoints(player.getPoints() - player.getBet());
							//Draw game£¬return bets to the player
							else
								player.setPoints(player.getPoints());
							//game over and reset the bet
							player.resetBet();
						}
						gameEngineCallback.houseResult(houseResult, this);
						break;
					}
				}catch(java.util.NoSuchElementException e) {
					//If the number of cards is not enough, re-shuffle for deal again
					cards = getShuffledDeck();
				}
			}
		}
	}

	@Override
	public void addPlayer(Player player) {
		String playerId = player.getPlayerId();
		allPlayers.put(playerId, player);
	}

	@Override
	public Player getPlayer(String id) {
		if (!allPlayers.containsKey(id)) {
			return null;
		}
		return allPlayers.get(id);
	}

	@Override
	public boolean removePlayer(Player player) {
		String playerId = player.getPlayerId();
		if (allPlayers.containsKey(playerId)) {
			if (allPlayers.remove(playerId) != null) {
				for (GameEngineCallback gameEngineCallback : gameEngineCallbacks) {
					removeGameEngineCallback(gameEngineCallback);
				}
			}
			return true;
		}
		return false;
	}

	@Override
	public void addGameEngineCallback(GameEngineCallback gameEngineCallback) {
		gameEngineCallbacks.add(gameEngineCallback);
	}

	@Override
	public boolean removeGameEngineCallback(GameEngineCallback gameEngineCallback) {
		if (gameEngineCallbacks.contains(gameEngineCallback)) {
			gameEngineCallbacks.remove(gameEngineCallback);
			return true;
		}
		return false;
	}

	@Override
	public Collection<Player> getAllPlayers() {
		Collection<Player> players = new ArrayList<>(allPlayers.values());
		return players;
	}

	@Override
	public boolean placeBet(Player player, int bet) {
		return player.placeBet(bet);
	}

	@Override
	public Deque<PlayingCard> getShuffledDeck() {
		LinkedList<PlayingCard> tempCards = new LinkedList<>();
		for(Suit suit : Suit.values()) {
			for(Value value : Value.values()) {
				int t = 0;
				switch(value.ordinal()) {
					case 0: t=1; break;
					case 10:
					case 11:
					case 12: t=10; break;
					default: t=value.ordinal()+1;
				}
				PlayingCard card = new PlayingCardImpl(suit,value,t);
				tempCards.add(card);
			}
		}
		Collections.shuffle(tempCards);
		return tempCards;
	}

	/**
	 * Interval for each deal
	 * @param delay
	 */
	private void delayDeal(int delay) {
		try {
			System.out.println("time");
			Thread.sleep(delay*1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Deal Card to player
	 * @return card
	 */
	private PlayingCard dealCard(int delay) {
		//Interval for each deal
		delayDeal(delay);
		//Receives a card from the dealer
		PlayingCard card = cards.getFirst();
		//Remove the card from deck after player got a card.
		cards.removeFirst();
		return card;
	}

	/**
	 * Calculate player's score
	 * @param preResult
	 * @param card
	 * @return
	 */
	private int calculatePlayerCardScore(int preResult, PlayingCard card) {
		return preResult + card.getScore();
	}

}
