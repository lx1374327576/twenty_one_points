package view;

import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.UIManager;

import model.SimplePlayer;
import model.interfaces.GameEngine;
import model.interfaces.Player;
import model.interfaces.PlayingCard;
import controller.AddPlayerListener;
import controller.UpPlayerListener;
import controller.DownPlayerListener;
import controller.StartListener;
import controller.BetListener;

import view.GameEngineCallbackGUI;

public class GameGUI implements Observer{

	public GameEngine gameEngine;
	public JFrame f;
	public JButton Bstart,Badd,Bup,Bdown,Bbet;
	public JComboBox<Object> Cplayers;
	public JLabel Lsummary,Lplayer_information,Lcard,Lhouse,Lresult,Lstatus;
	public JTextField Tsummary,Tcard,Thouse,Tbet;
	public List<Player> Splayers;
	public ActionListener BaddListener,BstartListener;
	public JMenu Mmenu,Mtools,Mstatus;
	public JMenuItem Mrestart,Mexit;
	public JMenuBar MMenuBar;
	public JPanel panel,Tplayer_information;
	public GridLayout playerGridLayout;
	public int ans,house_ans;



	public GameGUI(GameEngine gameEngine){
		this.gameEngine=gameEngine;
		System.out.println("Hello \n world!");
		refresh();
	}

	public void refresh(){

		if (f!=null) f.setVisible(false);
		f=new JFrame();
		f.setTitle("Game Machine");
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.setSize(800,800);
		f.setLayout(null);

		set_font();
		MMenuBar=new JMenuBar();
		Mmenu=new JMenu("Menu");
		Mrestart=new JMenuItem("restart");
		Mexit=new JMenuItem("exit");
		Mtools=new JMenu("tools");
		Mstatus=new JMenu("status");
		panel=new JPanel();
		Lsummary=new JLabel("summary:");
		Tsummary=new JTextField("summary information");
		Tsummary.setEditable(false);
		Bstart=new JButton("start");
		Splayers=new ArrayList<Player>();
		for (Player player:gameEngine.getAllPlayers()){
			gameEngine.removePlayer(player);
		}
		Cplayers=new JComboBox<>();
		Cplayers.firePopupMenuWillBecomeInvisible();
		Cplayers.setEditable(false);
//		Cplayers.setEnabled(false);
		Lplayer_information=new JLabel("player infomation:");
		Badd=new JButton("add one player");
		Bup=new JButton("next");
		Bdown=new JButton("last");
		Bbet=new JButton("bet");
		Lcard=new JLabel("card");
		Tcard=new JTextField("card information");
		Tcard.setEditable(false);
		Lhouse=new JLabel("house");
		Thouse=new JTextField("Thouse information");
		Tbet=new JTextField("Bet information");
		Tbet.setColumns(1);
		Thouse.setEditable(false);
		Tbet.setEditable(false);
		//Tplayer_information.setEditable(false);



		AddPlayerListener addListener = new AddPlayerListener(gameEngine, Splayers, Cplayers, this);
		Badd.addActionListener(addListener);

		UpPlayerListener upListener = new UpPlayerListener(gameEngine, Cplayers, this);
		Bup.addActionListener(upListener);

		DownPlayerListener downListener = new DownPlayerListener(gameEngine, Cplayers, this);
		Bdown.addActionListener(downListener);
		
		StartListener startListener = new StartListener();
		Bstart.addActionListener(startListener);
		
		BetListener betListener = new BetListener(gameEngine, Splayers, Cplayers,Tbet, this);
		Bbet.addActionListener(betListener);

		Lsummary.setBounds(20, 80, 300, 50);
		Tsummary.setBounds(20, 130,300, 300);
		//Mmenu.setBounds(0, 0, 200, 50);
		Cplayers.setBounds(20, 520, 200, 50);
		Bstart.setBounds(550, 400, 100, 50);
		Lplayer_information.setBounds(20, 450, 200, 50);
		//Tplayer_information.setBounds(230, 450, 300, 250);
		Bup.setBounds(20, 600, 80, 60);
		Bdown.setBounds(120, 600, 80, 60);
		Badd.setBounds(550, 500, 200, 200);
		Lcard.setBounds(550 , 100, 100, 50);
		Tcard.setBounds(550,150,200,200);
		Lhouse.setBounds(330, 30, 200, 50);
		Thouse.setBounds(330,80,200,150);
		Bbet.setBounds(330, 240, 200, 50);
		Tbet.setBounds(330,290,200,150);
		panel.setBounds(0, 0, 200, 30);

		Cplayers.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				System.out.println("Cplayers listener added!");
				Tplayer_information=new JPanel();
				Tplayer_information.setBounds(230, 450, 300, 250);
				playerGridLayout=new GridLayout(4,4);
				Tplayer_information.setLayout(playerGridLayout);
				Lstatus=new JLabel("no action");
				Lresult=new JLabel("total=0");
				Tplayer_information.add(Lstatus);
				Tplayer_information.add(Lresult);
				f.add(Tplayer_information);
				ans=0;
			}
		});

		Mexit.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {//只能检测到mousePressed事件
					super.mouseClicked(e);
					System.exit(0);
				}
		});

		Mmenu.add(Mrestart);
		Mmenu.add(Mexit);
		MMenuBar.add(Mmenu);
		MMenuBar.add(Mtools);
		MMenuBar.add(Mstatus);
		panel.add(MMenuBar);
		f.add(panel);
		f.add(Bdown);
		f.add(Bup);
		f.add(Lsummary);
		f.add(Tsummary);
		f.add(Cplayers);
		f.add(Bstart);
		f.add(Lplayer_information);
		//f.add(Tplayer_information);
		f.add(Badd);
		f.add(Lcard);
		f.add(Tcard);
		f.add(Lhouse);
		f.add(Thouse);
		f.add(Bbet);
		f.add(Tbet);
		f.add(panel);
		f.setVisible(true);
	}

	@Override
	public void update(Observable server, Object obj) {
		// TODO Auto-generated method stub
		System.out.println("Observer get!");
		switch (((GameEngineCallbackGUI) server).getFlag()){
			case 1:
				Lstatus.setText("gaming");
				ans=ans+((PlayingCard)obj).getScore();
				Lresult.setText("result="+String.valueOf(ans));
				Tplayer_information.add(new JLabel(((PlayingCard)obj).getSuit().toString()+((PlayingCard)obj).getValue().toString()));
			break;
			case 2:
				Lstatus.setText("boom!");
				Tplayer_information.add(new JLabel(((PlayingCard)obj).getSuit().toString()+((PlayingCard)obj).getValue().toString()));
			break;
			case 3:
				Lstatus.setText("finished");
			break;
			case 4:
			break;
			case 5:
			break;
			case 6:
			break;
		}

	}

	public void set_font(){
		Font font = new Font("宋体",Font.PLAIN,20);
        UIManager.put("Button.font", font);
        UIManager.put("CheckBox.font", font);
        UIManager.put("CheckBoxMenuItem.acceleratorFont", font);
        UIManager.put("CheckBoxMenuItem.font", font);
        UIManager.put("ColorChooser.font", font);
        UIManager.put("ComboBox.font", font);
        UIManager.put("DesktopIcon.font", font);
        UIManager.put("EditorPane.font", font);
        UIManager.put("FormattedTextField.font", font);
        UIManager.put("InternalFrame.titleFont", font);
        UIManager.put("Label.font", font);
        UIManager.put("List.font", font);
        UIManager.put("Menu.acceleratorFont", font);
        UIManager.put("Menu.font", font);
        UIManager.put("MenuBar.font", font);
        UIManager.put("MenuItem.acceleratorFont", font);
        UIManager.put("MenuItem.font", font);
        UIManager.put("OptionPane.font", font);
        UIManager.put("Panel.font", font);
        UIManager.put("PasswordField.font", font);
        UIManager.put("PopupMenu.font", font);
        UIManager.put("ProgressBar.font", font);
        UIManager.put("RadioButton.font", font);
        UIManager.put("RadioButtonMenuItem.acceleratorFont", font);
        UIManager.put("RadioButtonMenuItem.font", font);
        UIManager.put("ScrollPane.font", font);
        UIManager.put("Spinner.font", font);
        UIManager.put("TabbedPane.font", font);
        UIManager.put("Table.font", font);
        UIManager.put("TableHeader.font", font);
        UIManager.put("TextArea.font", font);
        UIManager.put("TextField.font", font);
        UIManager.put("TextPane.font", font);
        UIManager.put("TitledBorder.font", font);
        UIManager.put("ToggleButton.font", font);
        UIManager.put("ToolBar.font", font);
        UIManager.put("ToolTip.font", font);
        UIManager.put("Tree.font", font);
        UIManager.put("Viewport.font", font);
	}

	public int getPlayerNumber(){
		int ans=0;
		for (Player player:gameEngine.getAllPlayers()){
			ans++;
		}
		return ans;
	}

}