import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.ImageIcon;
import javax.swing.Timer;



public abstract class Enemy extends Texture implements Vars{

	private static final long serialVersionUID = 1L;
	private int points, speed;
	protected GamePanel panel;
	protected Bomberman b;
	protected Timer t1,t2;
	protected int pos = getGridPosition(), last_pos = -1;
	protected ImageIcon image;
	public int enemy_ID;

	public Enemy(ImageIcon i, int x, int y, int points, int enemy_ID, int speed, GamePanel panel, Bomberman b) {
		super(i,x,y);
		this.panel = panel;
		this.b = b;
		this.points = points;
		this.enemy_ID = enemy_ID;
		this.speed = speed;
		image = i;
		setWalkStat(false);
		radar();
	}
	
	public void AI_start(){
		ActionListener action = new ActionListener() {  
			public void actionPerformed(java.awt.event.ActionEvent e) {  
				int direction = selectPath();
				((Texture)panel.getComponent(pos)).setIcon(null);
				((Texture)panel.getComponent(pos)).setWalkStat(true);
				((Texture)panel.getComponent(pos)).enemy_ID = -1;
				switch(direction){
					case 0:{//Esquerda
						((Texture)panel.getComponent(--pos)).setIcon(image);
						last_pos = 0;
						break;
					}
					case 1:{//Direita
						((Texture)panel.getComponent(++pos)).setIcon(image);
						last_pos = 1;
						break;
					}
					case 2:{//Cima
						((Texture)panel.getComponent(pos-=Vars.tab_length)).setIcon(image);
						last_pos = 2;
						break;
					}
					case 3:{//Baixo
						((Texture)panel.getComponent(pos+=Vars.tab_length)).setIcon(image);
						last_pos = 3;
						break;
					}
				}
				((Texture)panel.getComponent(pos)).enemy_ID = enemy_ID;
			}  
		};
		t1 = new Timer(speed,action);
		t1.start();
	}
	
	protected int selectPath(){
		ArrayList<Integer> paths = new ArrayList<Integer>();
		if(((Texture) panel.getComponent(pos-1)).getWalkStat() && last_pos!=1)	paths.add(0);
		if(((Texture) panel.getComponent(pos+1)).getWalkStat() && last_pos!=0) paths.add(1);
		if(((Texture) panel.getComponent(pos-Vars.tab_length)).getWalkStat() && last_pos!=3) paths.add(2);
		if(((Texture) panel.getComponent(pos+Vars.tab_length)).getWalkStat() && last_pos!=2) paths.add(3);
		if(paths.size()==0){
			if(((Texture) panel.getComponent(pos-1)).getWalkStat())	paths.add(0);
			if(((Texture) panel.getComponent(pos+1)).getWalkStat()) paths.add(1);
			if(((Texture) panel.getComponent(pos-Vars.tab_length)).getWalkStat()) paths.add(2);
			if(((Texture) panel.getComponent(pos+Vars.tab_length)).getWalkStat()) paths.add(3);
		}
		if(paths.size()==0) return -1;
		return paths.get((int) (Math.random()*paths.size()));
	}
	
	private void radar(){
		ActionListener action = new ActionListener() {  
			public void actionPerformed(java.awt.event.ActionEvent e) {  
				if(pos == b.getGridPosition()){
					Bomberman.dead(panel);
					t2.stop();
					t1.stop();
				}
			}  
		};
		t2 = new Timer(10,action);
		t2.start();
	}
	
	public void dead(){
		t2.stop();
		t1.stop();
		Menu.countUp(points);
	}
}
