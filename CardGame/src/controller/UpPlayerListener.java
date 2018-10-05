package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JComboBox;

import model.interfaces.GameEngine;
import view.GameGUI;

public class UpPlayerListener implements ActionListener{

	private static GameEngine gameEngine;
	private static JComboBox<Object> Cplayers;
	private static GameGUI method;
	
	public UpPlayerListener(GameEngine gameEngine,JComboBox<Object> Cplayers,GameGUI method) {
		this.gameEngine = gameEngine;
		this.Cplayers = Cplayers;
		this.method = method;
	}
	
	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		int index = Cplayers.getSelectedIndex();
		if (index == 0 ) {
			Cplayers.setSelectedIndex(0);
		}else {
			Cplayers.setSelectedIndex(index-1);
		}
		
	}
	

}
