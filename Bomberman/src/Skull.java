public class Skull extends ComplexEnemy implements Vars{

	private static final long serialVersionUID = 1L;

	public Skull(int x, int y, int points, int enemyID, GamePanel panel, Bomberman b) {
		super(Vars.skull,x,y,400,enemyID,500,panel, b);
	}
}

