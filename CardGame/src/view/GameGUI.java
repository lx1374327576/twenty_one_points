package view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;

import model.SimplePlayer;
import model.interfaces.GameEngine;
import model.interfaces.Player;

public class GameGUI implements Observer{

	protected GameEngine gameEngine;
	protected JFrame f;
	protected JButton Bstart,Badd;
	protected JComboBox<Object> Cplayers,Cmenu;
	protected JLabel Lsummary,Lplayer_information,Lcard,Lhouse,Lbet;
	protected List<String> Splayers;
	protected ActionListener test;


	public GameGUI(GameEngine gameEngine){
		this.gameEngine=gameEngine;
		//System.out.println("tsy");


		test=new ActionListener(){
			public void actionPerformed(ActionEvent e){
				System.out.println("add one!");
				Player player=new SimplePlayer("4","tsy",2000);
				gameEngine.addPlayer(player);
		        gameEngine.placeBet(player, 100);
		        gameEngine.dealPlayer(player, 1);
		        Splayers.add("player "+player.getPlayerId());
		        Cplayers=new JComboBox<>(Splayers.toArray());
//		        gridLayout.setVisible(false);
		        refresh();
//		        gridLayout.myshow();
			}
		};

		Cmenu=new JComboBox<>();
		Lsummary=new JLabel("summary:");
		Bstart=new JButton("start");
		Splayers=new ArrayList<String>();
		for (Player player:gameEngine.getAllPlayers()){
			Splayers.add("player "+player.getPlayerId());
		}
		Cplayers=new JComboBox<>(Splayers.toArray());
		Lplayer_information=new JLabel("player infomation");
		Badd=new JButton("add one player");
		Lbet=new JLabel("bet");
		Lcard=new JLabel("card");
		Badd.addActionListener(test);
		//System.out.println("tsy1");
		refresh();
	}

	public void refresh(){
		if (f!=null)f.setVisible(false);
		f=new JFrame();
		f.setLayout(null);

		Lsummary.setBounds(20, 30, 300, 400);
		f.add(Lsummary);

		Cmenu.setBounds(0, 0, 100, 20);
		f.add(Cmenu);

		Cplayers.setBounds(20, 450, 100, 50);
		f.add(Cplayers);

		Bstart.setBounds(600, 500, 100, 20);
		f.add(Bstart);

		Lplayer_information.setBounds(220, 450, 400, 200);
		f.add(Lplayer_information);

		Badd.setBounds(600, 600, 150, 20);
		f.add(Badd);

		Lcard.setBounds(600 , 300, 100, 20);
		f.add(Lcard);

		Lhouse=new JLabel("house");
		Lhouse.setBounds(400, 100, 200, 100);
		f.add(Lhouse);


		Lbet.setBounds(400, 350, 200, 100);
		f.add(Lbet);

		f.setTitle("Game Machine");
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.setSize(800,800);
		f.setVisible(true);
	}

	@Override
	public void update(Observable arg0, Object arg1) {
		// TODO Auto-generated method stub
		System.out.println("aa");
		
	}

}