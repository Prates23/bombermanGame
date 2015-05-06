import javax.swing.JPanel;


public class Box extends Texture implements Vars{
	
	private static final long serialVersionUID = 1L;
	private boolean isDoor = false,
					destroyed = false,
					power_flame = false, 
					power_speed = false, 
					power_bomb = false, 
					power_bombNr = false,
					power_bonus = false;
	private static int door = -1;
	private static int [] power_duration = new int[4];
	private int boxID = Tabuleiro.getBoxID();
	
	public Box(int x,int y) { super(Vars.box,x,y); setWalkStat(false); }
	
	public void destroy(int currentPos, JPanel p){
		if(boxID == door){
			isDoor = true;
			setIcon(Vars.door);
			setWalkStat(false);
			return;
		}
		
		destroyed = true;
		double n = Math.random();
		if(n<0.16) powerUp = true;
		if(n<0.03){
			((Texture) p.getComponent(currentPos)).setIcon(Vars.power_flame);
			power_flame = true;
			power_duration[2] = 2;
		}
		if(n>=0.03 && n<0.06){
			((Texture) p.getComponent(currentPos)).setIcon(Vars.power_speed);
			power_speed = true;
			power_duration[3] = 2;
		}
		if(n>=0.06 && n<0.1){
			((Texture) p.getComponent(currentPos)).setIcon(Vars.power_deton);
			power_bomb = true;
			power_duration[0] = 1;
		}
		if(n>=0.1 && n<0.13){
			((Texture) p.getComponent(currentPos)).setIcon(Vars.power_bombNr);
			power_bombNr = true;
			power_duration[1] = 2;
		}
		if(n>=0.13 && n<0.16){
			((Texture) p.getComponent(currentPos)).setIcon(Vars.power_bonus);
			power_bonus = true;
		}
	}
	
	public static void powerUpLow(){
		for(int i=0;i<power_duration.length;++i){
			if(power_duration[i]>0)--power_duration[i];
			if(power_duration[i]==0){
				switch(i){
					case 0:{	
						Tabuleiro.deactivateV();
						MenuDown.deactivateDetonator();
						break;
					}
					case 1:{
						Tabuleiro.defaultMaxBombs();
						MenuDown.defaultBombNr();
						break;
					}
					case 2:{
						Bomb.delevelFlame();
						MenuDown.deactivateFlame();
						break;
					}
					case 3: { MenuDown.deactivateSpeed(); }
				}
			}
		}
	}

	public void usePowerUp(){
		if(power_bomb){
			MenuDown.activateDetonator();
			Tabuleiro.activateV();
			power_bomb = false;
		}
		if(power_speed){
			MenuDown.activateSpeed();
			power_speed = false;
		}
		if(power_flame){
			MenuDown.activateFlame();
			Bomb.levelUp();
			power_flame = false;
		}
		if(power_bombNr){
			MenuDown.incBombNr();
			Tabuleiro.incMaxBombs();
			power_bombNr = false;
		}
		if(power_bonus){
			Menu.countUp(50);
			power_bonus = false;
		}
		powerUp = false;
	}
	
	public boolean isDestroyed()	 { return destroyed; }
	public boolean isDoor()			 { return isDoor; }
	public static void setDoor(int v){ door = v; }
}
