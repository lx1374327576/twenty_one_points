package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JComboBox;

import model.interfaces.GameEngine;
import view.GameGUI;

public class DownPlayerListener implements ActionListener{

	private static GameEngine gameEngine;
	private static JComboBox<Object> Cplayers;
	private static GameGUI method;
	
	public DownPlayerListener(GameEngine gameEngine,JComboBox<Object> Cplayers,GameGUI method) {
		this.gameEngine = gameEngine;
		this.Cplayers = Cplayers;
		this.method = method;
	}
	
	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		int index = Cplayers.getSelectedIndex();
		int len = Cplayers.getItemCount();
		if (index == len-1 ) {
			Cplayers.setSelectedIndex(len-1);
		}else {
			Cplayers.setSelectedIndex(index+1);
		}
		
	}
	

}
