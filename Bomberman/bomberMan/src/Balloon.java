public class Balloon extends Enemy implements Vars{
	
	private static final long serialVersionUID = 1L;
	
	public Balloon(int x,int y,int ID,GamePanel panel,Bomberman b) {
		super(Vars.enemy_balloon,x,y,100,ID,1000,panel,b);
	}
}

