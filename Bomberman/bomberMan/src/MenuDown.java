import java.awt.Color;
import java.awt.Font;

import javax.swing.JLabel;
import javax.swing.JPanel;


public class MenuDown extends JPanel{
	
	private static final long serialVersionUID = 1L;
	private static JLabel[] items = new JLabel[4];

	public MenuDown(){
		build();
		for(int i=0;i<items.length;++i){
			if(items[i]!=null)
				add(items[i]);
		}
		setBackground(Color.black); 
	}
	
	private void build(){
		JLabel l1 = new JLabel("x"+Tabuleiro.getBombCount()+"        ");
		l1.setForeground(Color.white);
		l1.setFont(new Font("Tahoma",Font.BOLD, 13));
		l1.setIcon(Vars.menu_power_deton_off);
		items[0] = l1;
		JLabel l2 = new JLabel("           ");
		l2.setIcon(Vars.menu_power_speed_off);
		items[1] = l2;
		JLabel l3 = new JLabel("                ");
		l3.setIcon(Vars.menu_power_flame_off);
		items[2] = l3;
		JLabel l4 = new JLabel("                                                       Level 1");
		l4.setForeground(Color.white);
		l4.setFont(new Font("Tahoma",Font.BOLD, 13));
		items[3] = l4;
	}
	
	public static void incBombNr()			{ items[0].setText("x"+(Tabuleiro.getBombCount()+1)+"        "); }
	public static void activateDetonator()	{ items[0].setIcon(Vars.menu_power_deton_on); }
	public static void activateSpeed()		{ items[1].setIcon(Vars.menu_power_speed_on); }
	public static void activateFlame()		{ items[2].setIcon(Vars.menu_power_flame_on); }
	public static void deactivateDetonator(){ items[0].setIcon(Vars.menu_power_deton_off); }
	public static void defaultBombNr()		{ items[0].setText("x"+(Vars.maxBomb_lvl1)+"        "); }
	public static void deactivateFlame()	{ items[2].setIcon(Vars.menu_power_flame_off); }
	public static void deactivateSpeed()	{ items[1].setIcon(Vars.menu_power_speed_off); }
}

