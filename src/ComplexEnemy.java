import javax.swing.ImageIcon;

public class ComplexEnemy extends Enemy implements Vars{
	
	private static final long serialVersionUID = 1L;
	
	public ComplexEnemy(ImageIcon i, int x, int y, int points, int enemyID, int speed, GamePanel panel, Bomberman b) {
		super(i,x,y,points,enemyID,speed,panel,b);
	}
	
	protected int selectPath(){//Select Path com algorítmo de evitar bombas
		int nextPath = -1;
		for(int i=1;i<4;++i){
			if(((Texture) panel.getComponent(pos-i)) instanceof Bomb){//Bomba à esquerda
				if(((Bomb) panel.getComponent(pos-i)).isExploded()) break;
				if(((Texture) panel.getComponent(pos+Vars.tab_length)).getWalkStat()) nextPath = 3;//Baixo
				if(((Texture) panel.getComponent(pos-Vars.tab_length)).getWalkStat()) nextPath = 2;//Cima
				if(((Texture) panel.getComponent(pos+1)).getWalkStat()) nextPath = 1;//Direita
			}	
			if(((Texture) panel.getComponent(pos+i)) instanceof Bomb){//Bomba à direita
				if(((Bomb) panel.getComponent(pos+i)).isExploded()) break;
				if(((Texture) panel.getComponent(pos+Vars.tab_length)).getWalkStat()) nextPath = 3;//Baixo
				if(((Texture) panel.getComponent(pos-Vars.tab_length)).getWalkStat()) nextPath = 2;//Cima
				if(((Texture) panel.getComponent(pos-1)).getWalkStat()) nextPath = 0;//Esquerda
			}
			if(pos+(Vars.tab_length*i)< panel.getComponentCount() && ((Texture) panel.getComponent(pos+(Vars.tab_length*i))) instanceof Bomb){
				if(((Bomb) panel.getComponent(pos+(Vars.tab_length*i))).isExploded()) break;
				if(((Texture) panel.getComponent(pos-1)).getWalkStat()) nextPath = 0;//Esquerda
				if(((Texture) panel.getComponent(pos+1)).getWalkStat()) nextPath = 1;//Direita
				if(((Texture) panel.getComponent(pos-Vars.tab_length)).getWalkStat()) nextPath = 2;//Cima
			}
			if(pos-(Vars.tab_length*i)>0 &&((Texture) panel.getComponent(pos-(Vars.tab_length*i))) instanceof Bomb){
				if(((Bomb) panel.getComponent(pos-(Vars.tab_length*i))).isExploded()) break;
				if(((Texture) panel.getComponent(pos-1)).getWalkStat()) nextPath = 0;//Esquerda
				if(((Texture) panel.getComponent(pos+1)).getWalkStat()) nextPath = 1;//Direita
				if(((Texture) panel.getComponent(pos+Vars.tab_length)).getWalkStat()) nextPath = 3;//Baixo
			}
		}
		System.out.println(nextPath);
		if(nextPath==-1) nextPath = super.selectPath();
		return nextPath;	
	}
}
