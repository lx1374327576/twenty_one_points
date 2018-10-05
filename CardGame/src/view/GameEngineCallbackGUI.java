package view;

import java.util.Observable;

import javafx.beans.InvalidationListener;
import model.interfaces.GameEngine;
import model.interfaces.Player;
import model.interfaces.PlayingCard;
import view.interfaces.GameEngineCallback;
import view.GameGUI;

public class GameEngineCallbackGUI extends Observable implements GameEngineCallback{

	public GameEngineCallbackGUI(GameEngine gameEngine){
		GameGUI gameUI = new GameGUI(gameEngine);
		this.addObserver(gameUI);
	}
	
	public void test() {
		setChanged();
		notifyObservers(this);
	}

	@Override
	public void nextCard(Player player, PlayingCard card, GameEngine engine) {
		// TODO Auto-generated method stub

	}

	@Override
	public void bustCard(Player player, PlayingCard card, GameEngine engine) {
		// TODO Auto-generated method stub

	}

	@Override
	public void result(Player player, int result, GameEngine engine) {
		// TODO Auto-generated method stub

	}

	@Override
	public void nextHouseCard(PlayingCard card, GameEngine engine) {
		// TODO Auto-generated method stub

	}

	@Override
	public void houseBustCard(PlayingCard card, GameEngine engine) {
		// TODO Auto-generated method stub

	}

	@Override
	public void houseResult(int result, GameEngine engine) {
		// TODO Auto-generated method stub

	}

	

}
