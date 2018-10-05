package controller;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;

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
		method.Tplayer_information=new JPanel();
		method.Tplayer_information.setBounds(230, 450, 300, 250);
		method.playerGridLayout=new GridLayout(4,4);
		method.Tplayer_information.setLayout(method.playerGridLayout);
		method.Lstatus=new JLabel("no action");
		method.Lresult=new JLabel("total=0");
		method.Tplayer_information.add(method.Lstatus);
		method.Tplayer_information.add(method.Lresult);
		method.ans=0;
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
