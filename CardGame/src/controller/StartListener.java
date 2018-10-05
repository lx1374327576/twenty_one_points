package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;

import model.interfaces.GameEngine;
import model.interfaces.Player;
import view.GameGUI;

public class StartListener implements ActionListener{
	
	private static GameEngine gameEngine;
	private static List<Player> Splayers;
	private static JComboBox<Object> Cplayers;
	private static JTextArea Tbet;
	private static GameGUI method;
	
	public  StartListener(GameEngine gameEngine,List<Player> Splayers,JComboBox<Object> Cplayers,
			JTextArea Tbet,GameGUI method){
		this.gameEngine = gameEngine;
		this.Splayers = Splayers;
		this.Cplayers = Cplayers;
		this.Tbet = Tbet;
		this.method = method;
		method.house_ans = 0;
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		if(Splayers.isEmpty()) {
			return;
		}
		for(Player player:Splayers) {
			if(player.getBet()==0) {
				JOptionPane.showMessageDialog(null, String.format("%s : No bet,Please click betButton add ", player.getPlayerName()), "ERROR", JOptionPane.ERROR_MESSAGE);
				return;
			}
		}
		Player thePlayer = Splayers.get(Cplayers.getSelectedIndex());
		gameEngine.dealPlayer(thePlayer, 1);
		for(Player player:Splayers) {
			if(player.getResult()==0) {
				return;
			}
		}
		gameEngine.dealHouse(1);
		
	}

}
