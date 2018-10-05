package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JComboBox;

import model.SimplePlayer;
import model.interfaces.GameEngine;
import model.interfaces.Player;
import view.GameGUI;

public class AddPlayerListener implements ActionListener {
	private static GameEngine gameEngine;
	private static List<String> Splayers;
	private static JComboBox<Object> Cplayers;
	private static GameGUI method;
	
	public AddPlayerListener(GameEngine gameEngine,List<String> Splayers,JComboBox<Object> Cplayers,GameGUI method) {
		this.gameEngine = gameEngine;
		this.Splayers = Splayers;
		this.Cplayers = Cplayers;
		this.method = method;
	}
	

	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		System.out.println("add one!");
		Player player=new SimplePlayer("4","tsy",2000);
		gameEngine.addPlayer(player);
        gameEngine.placeBet(player, 100);
        gameEngine.dealPlayer(player, 1);
        Splayers.add("player "+player.getPlayerId());
        Cplayers=new JComboBox<>(Splayers.toArray());
        method.refresh();
//        gridLayout.setVisible(false);
//        gridLayout.myshow();
	}

}
