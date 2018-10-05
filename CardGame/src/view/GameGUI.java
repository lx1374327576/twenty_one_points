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
import javax.swing.UIManager;

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
	protected ActionListener BaddListener,BstartListener;


	public GameGUI(GameEngine gameEngine){
		this.gameEngine=gameEngine;
		//System.out.println("tsy");


		BaddListener=new ActionListener(){
			public void actionPerformed(ActionEvent e){
				System.out.println("add one!");
				int tmp=getPlayerNumber();
				Player player=new SimplePlayer(Integer.toString(tmp+1),"tsy"+Integer.toString(tmp+1),(int)(1+Math.random()*10)*100);
				gameEngine.addPlayer(player);
		        Splayers.add("player "+player.getPlayerId());
		        Cplayers=new JComboBox<>(Splayers.toArray());
//		        gridLayout.setVisible(false);
		        refresh();
//		        gridLayout.myshow();
			}
		};

		set_font();
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
		Badd.addActionListener(BaddListener);
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

		Cplayers.setBounds(20, 450, 200, 50);
		f.add(Cplayers);

		Bstart.setBounds(550, 500, 100, 20);
		f.add(Bstart);

		Lplayer_information.setBounds(220, 450, 400, 200);
		f.add(Lplayer_information);

		Badd.setBounds(550, 600, 200, 20);
		f.add(Badd);

		Lcard.setBounds(550 , 300, 100, 20);
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

	public void set_font(){
		Font font = new Font("ËÎÌו",Font.PLAIN,20);
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