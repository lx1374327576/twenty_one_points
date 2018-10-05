package view;

import java.util.Observable;

import javafx.beans.InvalidationListener;
import model.interfaces.GameEngine;
import model.interfaces.Player;
import model.interfaces.PlayingCard;
import view.interfaces.GameEngineCallback;
import view.GameGUI;

public class GameEngineCallbackGUI extends Observable implements GameEngineCallback{
	
	private int flag = 0;

	public GameEngineCallbackGUI(GameEngine gameEngine){
		GameGUI gameUI = new GameGUI(gameEngine);
		this.addObserver(gameUI);
	}
	
	public  void test() {
		setChanged();
		notifyObservers("bbb");
	}

	@Override
	public void nextCard(Player player, PlayingCard card, GameEngine engine) {
		// TODO Auto-generated method stub
		setFlag(1);
		setChanged();
		notifyObservers(card);

	}

	@Override
	public void bustCard(Player player, PlayingCard card, GameEngine engine) {
		// TODO Auto-generated method stub
		setFlag(2);
		setChanged();
		notifyObservers(card);
	}

	@Override
	public void result(Player player, int result, GameEngine engine) {
		// TODO Auto-generated method stub
		setFlag(3);
		setChanged();
		notifyObservers(result);

	}

	@Override
	public void nextHouseCard(PlayingCard card, GameEngine engine) {
		// TODO Auto-generated method stub
		setFlag(4);
		setChanged();
		notifyObservers(card);
	}

	@Override
	public void houseBustCard(PlayingCard card, GameEngine engine) {
		// TODO Auto-generated method stub
		setFlag(5);
		setChanged();
		notifyObservers(card);
	}

	@Override
	public void houseResult(int result, GameEngine engine) {
		// TODO Auto-generated method stub
		setFlag(6);
		setChanged();
		notifyObservers(result);
	}

	public int getFlag() {
		return flag;
	}

	public void setFlag(int flag) {
		this.flag = flag;
	}

	

}
