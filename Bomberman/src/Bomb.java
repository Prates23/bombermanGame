import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Iterator;
import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.Timer;

public class Bomb extends Texture implements Vars {
	
	private static final long serialVersionUID = 1L;
	private int position;
	private JPanel p;
	private boolean exploded = false;
	private ArrayList<Integer> flameList = new ArrayList<Integer>();
	private ArrayList<Enemy> enemy_list;
	public static int flame_lvl = Vars.flame_lvl1;
		
	public Bomb(int position, int x, int y, JPanel p, ArrayList<Enemy> arr) {
		super(Vars.bomb,x,y);
		setWalkStat(false);
		this.position = position;
		this.p = p;
		enemy_list = arr;
		setBorder(null);
	}
	
	public Timer boom(int time) {
		ActionListener action = new ActionListener() {  
			public void actionPerformed(java.awt.event.ActionEvent e) {  
				if(exploded) return;
				exploded = true;
				Tabuleiro.removeBombList(position);
				extinguish();//Invocado anteriormente para começar logo a contagem para apagar
				flameSet(position,Vars.flame_center);
				int i=1;
				for(;i<flame_lvl && !((Texture) p.getComponent(position-i) instanceof Wall);++i){
					if(!((Texture) p.getComponent(position-i)).powerUp)
						flameSet(position-i,Vars.flame_horiz);
				}
				if(i!=1 && !((Texture) p.getComponent(position-i+1)).powerUp) flameSet(position-i+1,Vars.flame_left);
				for(i=1;i<flame_lvl && !((Texture) p.getComponent(position+i) instanceof Wall);++i){
					if(!((Texture) p.getComponent(position+i)).powerUp)
						flameSet(position+i,Vars.flame_horiz);
				}
				if(i!=1 && !((Texture) p.getComponent(position+i-1)).powerUp) flameSet(position+i-1,Vars.flame_right);
				for(i=1;i<flame_lvl && !((Texture) p.getComponent(position-Vars.tab_length*i) instanceof Wall);++i){
					if(!((Texture) p.getComponent(position-Vars.tab_length*i)).powerUp)
						flameSet(position-Vars.tab_length*i,Vars.flame_vert);
				}
				if(i!=1 && !((Texture) p.getComponent(position-Vars.tab_length*(i-1))).powerUp) 
					flameSet(position-Vars.tab_length*(i-1),Vars.flame_top);
				for(i=1;i<flame_lvl && !((Texture) p.getComponent(position+Vars.tab_length*i) instanceof Wall);++i){
					if(!((Texture) p.getComponent(position+Vars.tab_length*i)).powerUp)	
						flameSet(position+Vars.tab_length*i,Vars.flame_vert);
				}
				if(i!=1 && !((Texture) p.getComponent(position+Vars.tab_length*(i-1))).powerUp) 
					flameSet(position+Vars.tab_length*(i-1),Vars.flame_down);
			}  
		};
		Timer t = new Timer(time,action);
		t.setRepeats(false);
		t.start();
		return t;
	}
	
	private void extinguish() {
		ActionListener action = new ActionListener() {  
			public void actionPerformed(java.awt.event.ActionEvent e) {  
				Tabuleiro.decreaseBombCount();
				Iterator<Integer> it = flameList.iterator();
				while(it.hasNext()){
					int currentPos = it.next();
					((Texture) p.getComponent(currentPos)).setIcon(null);
					((Texture) p.getComponent(currentPos)).setWalkStat(true);
					if(((Texture) p.getComponent(currentPos)) instanceof Box && !((Box) p.getComponent(currentPos)).isDestroyed()){
						((Box) p.getComponent(currentPos)).destroy(currentPos,p);
					}
					it.remove();
				}
			}  
		};
		Timer t = new Timer(1000,action);
		t.setRepeats(false);
		t.start();
	}
	
	private void flameSet(int pos, ImageIcon i) {
		if(pos == Tabuleiro.bombermanPos()) Bomberman.dead(p);
		Iterator<Enemy> it = enemy_list.iterator();
		while(it.hasNext()){
			Enemy e = it.next();
			if(e.enemy_ID == ((Texture) p.getComponent(pos)).enemy_ID){
				e.dead();
				it.remove();
			}
		}
		((Texture) p.getComponent(pos)).setIcon(i);
		((Texture) p.getComponent(pos)).setWalkStat(false);
		flameList.add(pos);
	}
	
	public static void levelUp()		{ flame_lvl = Vars.flame_lvl2; }
	public static void delevelFlame()	{ flame_lvl = Vars.flame_lvl1; }
	public boolean isExploded()			{ return exploded; }
}