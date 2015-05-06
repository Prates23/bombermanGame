public class BlackBomberman extends ComplexEnemy implements Vars{
	
	private static final long serialVersionUID = 1L;
	
	public BlackBomberman(int x, int y, int points, int enemyID, GamePanel panel, Bomberman b) {
		super(Vars.black_bomber,x,y,200,enemyID,1000,panel, b);
	}
}
