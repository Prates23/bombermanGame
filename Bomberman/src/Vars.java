import javax.swing.ImageIcon;

public interface Vars {
	//Tabuleiro
	public final int tab_length = 20;
	public final int tab_lines = 16;
	/** Levels **/
	String lvl_1 = "src/Level 1.txt";
	String lvl_2 = "src/Level 2.txt";
	String lvl_3 = "src/Level 3.txt";
	/** Textures  **/
	public final ImageIcon wall = new ImageIcon("src/wall.png");
	public final ImageIcon box = new ImageIcon("src/box.png");
	public final ImageIcon door = new ImageIcon("src/door.gif");
	/** Bomberman **/
	public final ImageIcon stand_front = new ImageIcon("src/stand_front.gif");
	public final ImageIcon stand_back = new ImageIcon("src/stand_back.gif");
	public final ImageIcon stand_left = new ImageIcon("src/stand_left.gif");
	public final ImageIcon stand_right = new ImageIcon("src/stand_right.gif");
	
	public final ImageIcon walk_front = new ImageIcon("src/BM.gif");
	public final ImageIcon walk_back = new ImageIcon("src/BM_UP.gif");
	public final ImageIcon walk_left = new ImageIcon("src/BM_LEFT.gif");
	public final ImageIcon walk_right = new ImageIcon("src/BM_RIGHT.gif");
	
	public final ImageIcon dead = new ImageIcon("src/BM_dead.png");
	/** Enemies **/
	public final ImageIcon enemy_balloon = new ImageIcon("src/balloon.gif");
	public final ImageIcon black_bomber = new ImageIcon("src/black_bm.png");
	public final ImageIcon skull = new ImageIcon("src/skull.gif");
	/** Bombs **/
	public final int maxBomb_lvl1 = 2;
	public final int maxBomb_lvl2 = 4;
	
	public final ImageIcon bomb = new ImageIcon("src/bomb.gif");
	/** Flames */
	public final int flame_lvl1 = 3;
	public final int flame_lvl2 = 5;
	
	public final ImageIcon flame_center = new ImageIcon("src/center.gif");
	public final ImageIcon flame_left = new ImageIcon("src/left.gif");
	public final ImageIcon flame_right = new ImageIcon("src/right.gif");
	public final ImageIcon flame_down = new ImageIcon("src/down.gif");
	public final ImageIcon flame_top = new ImageIcon("src/up.gif");
	public final ImageIcon flame_vert = new ImageIcon("src/vert.gif");
	public final ImageIcon flame_horiz = new ImageIcon("src/horiz.gif");
	/** Menus **/
	public final int maxItems = 11;
	public final int maxLifes = 5;
	
	public final ImageIcon clock = new ImageIcon("src/clock.png");
	public final ImageIcon life = new ImageIcon("src/heart.png");
	public final ImageIcon life_off = new ImageIcon("src/heart_black.png");
	public final ImageIcon menu_power_speed_off = new ImageIcon("src/power_speed.png");
	public final ImageIcon menu_power_deton_off = new ImageIcon("src/power_deton.png");
	public final ImageIcon menu_power_flame_off = new ImageIcon("src/power_flame.png");
	public final ImageIcon menu_power_speed_on = new ImageIcon("src/power_speed_on.png");
	public final ImageIcon menu_power_deton_on = new ImageIcon("src/power_det_on.png");
	public final ImageIcon menu_power_flame_on = new ImageIcon("src/power_flame_on.png");
	/** Power Ups**/
	public final ImageIcon power_speed = new ImageIcon("src/power_speed.gif");
	public final ImageIcon power_deton = new ImageIcon("src/power_det.gif");
	public final ImageIcon power_flame = new ImageIcon("src/power_flame.png");
	public final ImageIcon power_bombNr = new ImageIcon("src/bomb_up.png");
	public final ImageIcon power_bonus = new ImageIcon("src/bonus.gif");
	/** Wallpapers **/
	public final String background_lvl1 = "src/level1.png";
	public final String background_lvl2 = "src/level2.png";
	public final String background_lvl3 = "src/level3.png";
}