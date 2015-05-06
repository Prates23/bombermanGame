import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionListener;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.Timer;

public class Menu extends JPanel{
	
	private static final long serialVersionUID = 1L;
	private static JLabel[] items = new JLabel[Vars.maxItems];
	private static Tabuleiro t;
	private static int count, countPos, currentLifes;
	
	public Menu(Tabuleiro t){
		currentLifes = Vars.maxLifes;
		Menu.t = t;
		count = 0;
		build();
		for(int i=0;i<items.length;++i){
			if(items[i]!=null)
				add(items[i]);
		}
		setBackground(Color.black);
	}
	
	private void build(){
		for(int i=0;i<Vars.maxLifes;++i){
			items[i] = new JLabel();
			items[i].setBorder(null);
			items[i].setIcon(Vars.life);
		}
		int pos = Vars.maxLifes;
		items[pos++] = new JLabel("                                   ");
		items[pos++] = new Clock(500).start();
		items[pos++] = new JLabel("                                            ");
		JLabel aux = new JLabel("000000");
		aux.setFont(new Font("Tahoma",Font.BOLD, 15));
		aux.setForeground(Color.white);
		items[pos] = aux;
		countPos = pos;
	}
	
	public static void countUp(int val){
		count+=val;
		String aux = "";
		if(count>=10&&count<100) aux = "0000"+count;
		if(count>=100&&count<1000) aux = "000"+count;
		if(count>=1000&&count<10000) aux = "00"+count;
		if(count>=10000&&count<100000) aux = "0"+count;
		items[countPos].setText(""+aux);
	}
	
	public static void dead(){
		if(currentLifes==0) return;
		items[--currentLifes].setIcon(Vars.life_off);	
		if(currentLifes==0){
			ActionListener action = new ActionListener() {  
				public void actionPerformed(java.awt.event.ActionEvent e) {  
					t.reset();
					StartMenu.addScore(new Score(StartMenu.getPlayerName(),Menu.getScore()));
				}  
			};
			Timer t = new Timer(4000,action);
			t.setRepeats(false);
			t.start();
		}
	}
	
	public static int getLifes() { return currentLifes;	}
	public static int getScore() { return count; }
}
