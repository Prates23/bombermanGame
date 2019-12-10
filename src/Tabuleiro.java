import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Iterator;
import javax.imageio.ImageIO;
import javax.swing.*;

public class Tabuleiro extends JFrame implements KeyListener,Vars{
	private static final long serialVersionUID = 1L;
	
	private static GamePanel gridPanel;
	private static Container container;
	private static Bomberman b;
	private static Menu menu;
	private static MenuDown menuDown;
	private static boolean play_allow = true;
	private static int maxBombs = Vars.maxBomb_lvl1, currentBombs = 0, box_ID = 0, box_number;
	private boolean bomb;
	private int current_lvl = 1;
	private static ArrayList<Integer> bomb_list = new ArrayList<Integer>();
	private ArrayList<Timer> timer_list = new ArrayList<Timer>();
	private boolean key_control = true;
	private static boolean v_key = false;
	private static ArrayList<Enemy> enemyList = new ArrayList<Enemy>();
	
	public Tabuleiro(){
			build(Vars.lvl_1,Vars.background_lvl1);
			gridPanel.setBorder(null);
			container = getRootPane().getContentPane();
			container.add(menu=new Menu(this), BorderLayout.NORTH);
			container.add(gridPanel, BorderLayout.CENTER);
			container.add(menuDown=new MenuDown(),BorderLayout.SOUTH);
			container.setBackground(Color.black);
		    setTitle("Bomberman v2.0");
		    setResizable(false);
		    pack();
	        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
	}
	public GamePanel build(String pathNameIn, String background){
		gridPanel = new GamePanel(new GridLayout(Vars.tab_lines,Vars.tab_length),background);
		try {
			BufferedReader in = new BufferedReader(new FileReader(pathNameIn));
			int x_axis = 0;
			int y_axis = 0;
			int d;
			int enemy_ID = 0;
			while((d=in.read())!=-1){
				char c = (char) d;
				Texture add;
				switch(c){
					case '.':{
						add = new Floor(x_axis,y_axis);
						add.addKeyListener(this);
						gridPanel.add(add);
						++x_axis;
						break;
					}
					case 'B':{
						b = new Bomberman(x_axis,y_axis);
						b.addKeyListener(this);
						gridPanel.add(b);
						++x_axis;
						break;
					}
					case 'W':{
						add = new Wall(x_axis,y_axis);
						add.addKeyListener(this);
						gridPanel.add(add);
						++x_axis;
						break;
					}
					case 'c':{
						add = new Box(x_axis,y_axis);
						add.addKeyListener(this);
						gridPanel.add(add);
						++x_axis;	++box_ID;
						break;
					}
					case '1':{
						add = new Balloon(x_axis,y_axis,enemy_ID,gridPanel,b);
						enemyList.add((Enemy) add);
						gridPanel.add(add);
						++x_axis; ++enemy_ID;
						break;
					}
					case '2':{
						add = new BlackBomberman(x_axis,y_axis,enemy_ID,enemy_ID, gridPanel,b);
						enemyList.add((Enemy) add);
						gridPanel.add(add);
						++x_axis; ++enemy_ID;
						break;
					}
					case '3':{
						add = new Skull(x_axis,y_axis,enemy_ID,enemy_ID, gridPanel,b);
						enemyList.add((Enemy) add);
						gridPanel.add(add);
						++x_axis; ++enemy_ID;
						break;
					}
					case '\n':{
						++y_axis;
						x_axis = 0;
					}
				}
			}
			Box.setDoor((int)(box_ID * Math.random()));
			box_ID = 0;
			in.close();
		}
		catch (FileNotFoundException e){	e.printStackTrace();	} 
		catch (IOException e){				e.printStackTrace();	}
		return gridPanel;
	}
	public void keyPressed(KeyEvent e){
		if(!play_allow) return;
		if(((Texture) gridPanel.getComponent(b.getGridPosition()+1)).isDoor() && enemyList.size()==0){
			((Texture) gridPanel.getComponent(b.getGridPosition()+1)).setWalkStat(true);
		}	
		if(((Texture) gridPanel.getComponent(b.getGridPosition()-1)).isDoor() && enemyList.size()==0){
			((Texture) gridPanel.getComponent(b.getGridPosition()-1)).setWalkStat(true);
		}
		if(((Texture) gridPanel.getComponent(b.getGridPosition()+Vars.tab_length)).isDoor() && enemyList.size()==0){
			((Texture) gridPanel.getComponent(b.getGridPosition()+Vars.tab_length)).setWalkStat(true);
		}	
		if(((Texture) gridPanel.getComponent(b.getGridPosition()-Vars.tab_length)).isDoor() && enemyList.size()==0){
			((Texture) gridPanel.getComponent(b.getGridPosition()-Vars.tab_length)).setWalkStat(true);
		}	
		int seta = e.getKeyCode();
		int current_pos = b.getGridPosition();
		switch(seta){
			case 32:{
				if(key_control){
					if(currentBombs<maxBombs){
						bomb = true;
						++currentBombs;
					}
					key_control = false;
				}
				return;
			}
			case 37:{
				if(b.walkLeft()) key_macro(Vars.walk_left,b.x_axis+1,-1,current_pos);
				break;
			}
			case 38:{
				if(b.walkUp()) key_macro(Vars.walk_back,-1,b.y_axis+1,current_pos);	
				break;
			}
			case 39:{
				if(b.walkRight()) key_macro(Vars.walk_right,b.x_axis-1,-1,current_pos);			
				break;
			}
			case 40:{
				if(b.walkDown()) key_macro(Vars.walk_front,-1,b.y_axis-1,current_pos);
				break;
			}
			case 27:{	reset();	}
			case 86:{// Tecla 'V'
				if(!v_key) return;
				Iterator<Timer> timer_it = timer_list.iterator();
				while(timer_it.hasNext()){
					Timer aux = timer_it.next();
					aux.stop();
					timer_it.remove();
				}
				Iterator<Integer> it = bomb_list.iterator();
				while(it.hasNext()){
					int pos = it.next();
					it.remove();
					Bomb bomba = new Bomb(pos,((Texture) gridPanel.getComponent(pos)).x_axis,
							 ((Texture) gridPanel.getComponent(pos)).y_axis,gridPanel,enemyList);

					gridPanel.remove(pos);
					gridPanel.add(bomba,pos);
					bomba.boom(0);
				}
				bomb = false;
			}
		}
	}
	public void keyReleased(KeyEvent e){
		if(!play_allow) return;
		int seta = e.getKeyCode();
		switch(seta){
			case 37:{ 	((Texture) gridPanel.getComponent(b.getGridPosition())).setIcon(Vars.stand_left);	break;	}
			case 38:{	((Texture) gridPanel.getComponent(b.getGridPosition())).setIcon(Vars.stand_back);	break;	}
			case 39:{	((Texture) gridPanel.getComponent(b.getGridPosition())).setIcon(Vars.stand_right);	break;	}
			case 40:{	((Texture) gridPanel.getComponent(b.getGridPosition())).setIcon(Vars.stand_front);	break;	}
		}
		if(((Texture) gridPanel.getComponent(b.getGridPosition())).isDoor())
			((Texture) gridPanel.getComponent(b.getGridPosition())).setIcon(Vars.door);
	}
	public void keyTyped(KeyEvent e){}
	private void key_macro(ImageIcon newIcon, int x_last, int y_last, int currentLoc ){
		if(((Texture) gridPanel.getComponent(b.getGridPosition())).isDoor() && enemyList.size()==0){
			((Texture)gridPanel.getComponent(currentLoc)).setIcon(null);
			block_gameplay();
			ActionListener action = new ActionListener() {  
				public void actionPerformed(java.awt.event.ActionEvent e) {  
					++current_lvl;
					allow_gameplay();
					switch(current_lvl){
						case 2:{	restart(Vars.lvl_2,2);	break;		}
						case 3:{	restart(Vars.lvl_3,3);	break;		}
						case 4:{
							reset();
							Menu.countUp(Clock.getRemainingTime());
							StartMenu.addScore(new Score(StartMenu.getPlayerName(),Menu.getScore()));
						}
					}
				}  
			};
			Timer t = new Timer(8000,action);
			t.setRepeats(false);
			t.start();
			return;
		}
		if(!((Texture) gridPanel.getComponent(b.getGridPosition())).getWalkStat()){
			if(x_last!=-1) b.setX(x_last);
			if(y_last!=-1) b.setY(y_last);
			return;
		}
		key_control = true;
		((Texture) gridPanel.getComponent(b.getGridPosition())).setIcon(newIcon);
		if(((Texture) gridPanel.getComponent(b.getGridPosition())) instanceof Box){
			if(((Box) gridPanel.getComponent(b.getGridPosition())).powerUp){
				((Box) gridPanel.getComponent(b.getGridPosition())).usePowerUp();
			}
		}
		if(bomb){
			bomb_list.add(currentLoc);
			Bomb bomba = new Bomb(currentLoc,((Texture) gridPanel.getComponent(currentLoc)).x_axis,
					 ((Texture) gridPanel.getComponent(currentLoc)).y_axis,gridPanel,enemyList);

			gridPanel.remove(currentLoc);
			gridPanel.add(bomba,currentLoc);
			timer_list.add(bomba.boom(3000));
			bomb = false;
		}
		else ((JButton)gridPanel.getComponent(currentLoc)).setIcon(null);//Arrasto excluído
	}
	public static void decreaseBombCount(){ currentBombs--; if(currentBombs<0) currentBombs = 0; }
	public static void incMaxBombs(){	maxBombs++; }
	public static void defaultMaxBombs(){	maxBombs = Vars.maxBomb_lvl1; }
	public static int getBombCount(){ return maxBombs; }
	public static void block_gameplay(){ play_allow = false; }
	public static void allow_gameplay(){ play_allow = true;  }
	public static int bombermanPos(){ return b.getGridPosition(); }
	public static int getBoxNumber(){ return box_number; }
	public static int getBoxID(){	return box_ID; }
	public static void decBoxNumber(){ box_number--; }
	public static void removeBombList(int pos){ bomb_list.remove((Object)pos); }
	public static void activateV(){ v_key = true; }
	public static void deactivateV(){ v_key = false; }
	public JButton getBomber(){ return (JButton) gridPanel.getComponent(bombermanPos()); }
	public void simulateKey(MouseEvent e, Component c) throws SecurityException, NoSuchFieldException, IllegalArgumentException, IllegalAccessException {
		   Field f = KeyEvent.class.getField("Teste");
		   f.setAccessible(true);
		   f.set(e, Boolean.TRUE);
		   c.dispatchEvent(e);
	}
	public void reset(){
		setVisible(false);
		container.removeAll();
		String path = "src/starting_screen.png";
        BufferedImage image;
		try {
			image = ImageIO.read(new File(path));
			container.add(new StartMenu(image,this));
			setVisible(true);
		} 
		catch (IOException e) {		e.printStackTrace();	}
	}
	public void restart(String level, int music_lvl){
		setVisible(false);
		container.removeAll();
		enemyList.removeAll(enemyList);
		switch(music_lvl){
			case 1:{
				container.add(build(level,Vars.background_lvl1), BorderLayout.CENTER);
				container.add(menu=new Menu(this), BorderLayout.NORTH);
				container.add(menuDown=new MenuDown(),BorderLayout.SOUTH);
				break;
			}
			case 2:{
				container.add(build(level,Vars.background_lvl2), BorderLayout.CENTER);
				Menu.countUp(Clock.getRemainingTime());
				Clock.defaultClock();
				container.add((menu), BorderLayout.NORTH);
				container.add(menuDown,BorderLayout.SOUTH);
				break;
			}
			case 3:{
				container.add(build(level,Vars.background_lvl3), BorderLayout.CENTER);
				Menu.countUp(Clock.getRemainingTime());
				Clock.defaultClock();
				container.add((menu), BorderLayout.NORTH);
				container.add(menuDown,BorderLayout.SOUTH);
				break;
			}
		}
		setVisible(true);
		Iterator<Enemy> it = enemyList.iterator();
		while(it.hasNext())	it.next().AI_start();
	}
}

