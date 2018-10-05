package view;

import java.awt.Font;
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
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.UIManager;

import model.SimplePlayer;
import model.interfaces.GameEngine;
import model.interfaces.Player;

public class GameGUI implements Observer{

	protected GameEngine gameEngine;
	protected JFrame f;
	protected JButton Bstart,Badd,Bup,Bdown;
	protected JComboBox<Object> Cplayers;
	protected JLabel Lsummary,Lplayer_information,Lcard,Lhouse,Lbet;
	protected JTextField Tplayer_information,Tsummary,Tcard,Thouse,Tbet;
	protected List<String> Splayers;
	protected ActionListener BaddListener,BstartListener;
	protected JMenu Mmenu;
	protected JMenuItem Mhelp;
	protected JMenuBar MMenuBar;
	protected JPanel panel;


	public GameGUI(GameEngine gameEngine){
		this.gameEngine=gameEngine;
		//System.out.println("tsy");
		f=new JFrame();
		f.setTitle("Game Machine");
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.setSize(800,800);
		f.setLayout(null);

		BaddListener=new ActionListener(){
			public void actionPerformed(ActionEvent e){
				System.out.println("add one!");
				int tmp=getPlayerNumber();
				Player player=new SimplePlayer(Integer.toString(tmp+1),"tsy"+Integer.toString(tmp+1),(int)(1+Math.random()*10)*100);
				gameEngine.addPlayer(player);
		        Splayers.add("player "+player.getPlayerId());
		        Cplayers.addItem("player "+player.getPlayerId());
//		        gridLayout.setVisible(false);
		        //refresh();
//		        gridLayout.myshow();
			}
		};

		set_font();
		MMenuBar=new JMenuBar();
		Mmenu=new JMenu("Menu");
		Mhelp=new JMenuItem("help");
		panel=new JPanel();
		Lsummary=new JLabel("summary:");
		Tsummary=new JTextField("summary information");
		Tsummary.setEditable(false);
		Bstart=new JButton("start");
		Splayers=new ArrayList<String>();
		for (Player player:gameEngine.getAllPlayers()){
			Splayers.add("player "+player.getPlayerId());
		}
		Cplayers=new JComboBox<>(Splayers.toArray());
		Lplayer_information=new JLabel("player infomation:");
		Badd=new JButton("add one player");
		Lbet=new JLabel("bet");
		Lcard=new JLabel("card");
		Tcard=new JTextField("card information");
		Tcard.setEditable(false);
		Lhouse=new JLabel("house");
		Thouse=new JTextField("Thouse information");
		Tbet=new JTextField("Bet information");
		Thouse.setEditable(false);
		Tbet.setEditable(false);
		Badd.addActionListener(BaddListener);
		Tplayer_information=new JTextField("specific information");
		Tplayer_information.setEditable(false);
		Bup=new JButton("next");
		Bdown=new JButton("last");


		Lsummary.setBounds(20, 80, 300, 50);
		Tsummary.setBounds(20, 130,300, 300);
		//Mmenu.setBounds(0, 0, 200, 50);
		Cplayers.setBounds(20, 520, 200, 50);
		Bstart.setBounds(550, 400, 100, 50);
		Lplayer_information.setBounds(20, 450, 200, 50);
		Tplayer_information.setBounds(230, 450, 300, 250);
		Bup.setBounds(20, 600, 80, 60);
		Bdown.setBounds(120, 600, 80, 60);
		Badd.setBounds(550, 500, 200, 200);
		Lcard.setBounds(550 , 100, 100, 50);
		Tcard.setBounds(550,150,200,200);
		Lhouse.setBounds(330, 30, 200, 50);
		Thouse.setBounds(330,80,200,150);
		Lbet.setBounds(330, 240, 200, 50);
		Tbet.setBounds(330,290,200,150);
		panel.setBounds(0, 0, 50, 30);

		Mmenu.add(Mhelp);
		MMenuBar.add(Mmenu);
		panel.add(MMenuBar);

		//panel.setVisible(true);

		//System.out.println("tsy1");
		refresh();
	}

	public void refresh(){

		f.add(panel);
		f.add(Bdown);
		f.add(Bup);
		f.add(Lsummary);
		f.add(Tsummary);
		f.add(Cplayers);
		f.add(Bstart);
		f.add(Lplayer_information);
		f.add(Tplayer_information);
		f.add(Badd);
		f.add(Lcard);
		f.add(Tcard);
		f.add(Lhouse);
		f.add(Thouse);
		f.add(Lbet);
		f.add(Tbet);
		f.add(panel);
		f.setVisible(true);
	}

	@Override
	public void update(Observable arg0, Object arg1) {
		// TODO Auto-generated method stub
		System.out.println("aa");

	}

	public void set_font(){
		Font font = new Font("����",Font.PLAIN,20);
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