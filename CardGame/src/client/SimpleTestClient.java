package client;

import java.util.Deque;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import model.GameEngineImpl;
import model.SimplePlayer;
import model.interfaces.GameEngine;
import model.interfaces.Player;
import model.interfaces.PlayingCard;
import validate.Validator;
import view.GameEngineCallbackGUI;
import view.GameEngineCallbackImpl;

/**
 * Simple console client for SADI assignment 2, 2018
 * NOTE: This code will not compile until you have implemented code for the supplied interfaces!
 *
 * You must be able to compile your code WITHOUT modifying this class.
 * Additional testing should be done by copying and adding to this class while ensuring this class still works.
 *
 * The provided Validator.jar will check if your code adheres to the specified interfaces!
 *
 * @author Caspar Ryan
 *
 */
public class SimpleTestClient
{
   private static Logger logger = Logger.getLogger("assignment1");

   public static void main(String args[])
   {
      final GameEngine gameEngine = new GameEngineImpl();

      // call method in Validator.jar to test *structural* correctness
      // just passing this does not mean it actually works .. you need to test yourself!
      // pass false if you want to disable logging .. (i.e. once it passes)
      Validator.validate(false);

      // create test players
//      Player[] players = new Player[] { };
//      Player[] players = new Player[] { new SimplePlayer("1", "The Shark", 0)};
//      Player[] players = new Player[] { new SimplePlayer("1", "The Shark1", 1000), new SimplePlayer("1", "The Shark2", 500)};
//    List<Player> players = initPlayers();



      // Uncomment this to DEBUG your deck of cards creation
      //Deque<PlayingCard> shuffledDeck = gameEngine.getShuffledDeck();
      //printCards(shuffledDeck);

      // main loop to add players, place a bet and receive hand

   // add logging callback
      gameEngine.addGameEngineCallback(new GameEngineCallbackGUI(gameEngine));
//      gameEngine.addGameEngineCallback(new GameEngineCallbackImpl());

      // all players have played so now house deals
      // GameEngineCallBack.houseResult() is called to log all players (after results are calculated)
   }

	private static List<Player> initPlayers() {
		List<Player> players = new LinkedList<>();
	      for(int i=1;i<27;i++) {
	    	  players.add(new SimplePlayer(String.valueOf(i), "The ".concat(String.valueOf((char)(64+i))),i*100));
		  }
		return players;
	}

   @SuppressWarnings("unused")
   private static void printCards(Deque<PlayingCard> deck)
   {
      for (PlayingCard card : deck)
         logger.log(Level.INFO, card.toString());
   }
}
