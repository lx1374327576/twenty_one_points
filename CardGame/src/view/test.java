package view;


import view.GameEngineCallbackGUI;
import model.GameEngineImpl;

public class test {
	public static void main(String [] args) {
		GameEngineImpl b = new GameEngineImpl();
		GameEngineCallbackGUI a = new GameEngineCallbackGUI(b);
		a.test();
	}
}
