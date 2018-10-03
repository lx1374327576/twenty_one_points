package view;

import java.util.logging.Level;
import java.util.logging.Logger;

import model.interfaces.GameEngine;
import model.interfaces.Player;
import model.interfaces.PlayingCard;
import view.interfaces.GameEngineCallback;


public class GameEngineCallbackImpl implements GameEngineCallback {
	private final Logger logger = Logger.getLogger(this.getClass().getName());

	public GameEngineCallbackImpl() {
		// FINE shows dealing output, INFO only shows result
		logger.setLevel(Level.FINE);
		logger.setUseParentHandlers(false);
		java.util.logging.ConsoleHandler handler = new java.util.logging.ConsoleHandler();
		handler.setLevel(Level.FINE);
		logger.addHandler(handler);
	}

	@Override
	public void nextCard(Player player, PlayingCard card, GameEngine engine) {
		//FINE: Card Dealt to The Shark .. Suit: CLUBS, Value: ACE, Score: 1
		logger.log(Level.FINE, String.format(getDealingFormat(""), player.getPlayerName(), card));
	}

	@Override
	public void result(Player player, int result, GameEngine engine) {
		//INFO: The Shark, final result=17
		logger.log(Level.INFO, String.format(getResultFormat(), player.getPlayerName(), result));
	}

	@Override
	public void bustCard(Player player, PlayingCard card, GameEngine engine) {
		//FINE: Card Dealt to The Shark .. Suit: SPADES, Value: SEVEN, Score: 7 ... YOU BUSTED!
		logger.log(Level.FINE, String.format(getDealingFormat(" ... YOU BUSTED!"), player.getPlayerName(), card));
	}

	@Override
	public void nextHouseCard(PlayingCard card, GameEngine engine) {
		//FINE: Card Dealt to House .. Suit: CLUBS, Value: KING, Score: 10
		logger.log(Level.FINE, String.format(getDealingFormat(""), "House", card));
	}

	@Override
	public void houseBustCard(PlayingCard card, GameEngine engine) {
		//FINE: Card Dealt to House .. Suit: CLUBS, Value: SEVEN, Score: 7 ... HOUSE BUSTED!
		logger.log(Level.FINE, String.format(getDealingFormat(" ... HOUSE BUSTED!"), "House", card));
	}

	@Override
	public void houseResult(int result, GameEngine engine) {
		//INFO: House, final result=20
		logger.log(Level.INFO, String.format(getResultFormat(), "House", result));
		//INFO: Final Player Results
		//Player: id=1, name=The Shark, points=900
		//Player: id=2, name=The Loser, points=500
		StringBuffer tempMsg = new StringBuffer();
		for (Player player : engine.getAllPlayers()) {
			tempMsg.append(String.format("Player: %s\n", player));
		}
		logger.log(Level.INFO, "Final Player Results\n".concat(tempMsg.toString()));
	}
	
	private String getDealingFormat(String msg) {
		//FINE: Card Dealt to The Shark .. Suit: CLUBS, Value: ACE, Score: 1
		return "Card Dealt to %s ..  %s".concat(msg);
	}
	
	private String getResultFormat() {
		//INFO: The Shark, final result=17
		return "%s, final result=%d";
	}

}
