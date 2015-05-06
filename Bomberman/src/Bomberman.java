import java.awt.Dimension;
import java.awt.event.ActionListener;

import javax.swing.JPanel;
import javax.swing.Timer;

public class Bomberman extends Texture implements Vars{
	private static final long serialVersionUID = 1L;

	public Bomberman(int x, int y){
		super(Vars.stand_front,x,y);
		setPreferredSize(new Dimension(Vars.walk_front.getIconWidth(),Vars.walk_front.getIconHeight()));
	}
	
	public boolean walkRight(){
		if(x_axis<Vars.tab_length-1){
			x_axis++;
			return true;
		}
		return false;
	}
	
	public boolean walkLeft(){
		if(x_axis!=0){
			x_axis--;
			return true;
		}
		return false;
	}
	
	public boolean walkUp(){
		if(y_axis>0){
			y_axis--;
			return true;
		}
		return false;
	}
	
	public boolean walkDown(){
		if(y_axis<Vars.tab_lines-1){
			y_axis++;
			return true;
		}
		return false;
	}
	
	public static void dead(JPanel tab){
		((Texture) tab.getComponent(Tabuleiro.bombermanPos())).setIcon(Vars.dead);
		Tabuleiro.block_gameplay();
		Menu.dead();
		Box.powerUpLow();
		final JPanel p = tab;
		ActionListener action = new ActionListener() {  
			public void actionPerformed(java.awt.event.ActionEvent e) {  
				Tabuleiro.allow_gameplay();
				((Texture) p.getComponent(Tabuleiro.bombermanPos())).setIcon(Vars.stand_front);
			}  
		};
		Timer t = new Timer(3000,action);
		t.setRepeats(false);
		t.start();
	}
}
