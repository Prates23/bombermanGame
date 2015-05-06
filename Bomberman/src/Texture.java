import javax.swing.ImageIcon;
import javax.swing.JButton;

public abstract class Texture extends JButton{

	private static final long serialVersionUID = 1L;
	private ImageIcon standard;
	private boolean walk = true;
	public boolean powerUp = false;
	public int x_axis, y_axis, enemy_ID = -1;
	
	public Texture(ImageIcon i, int x, int y){
		standard = i;	 
		x_axis = x;
		y_axis = y;
		setIcon(standard); 
		setFocusPainted(false);
        setOpaque(false);
        setContentAreaFilled(false);
        setBorderPainted(false);
        setBorder(null);
	}
	
	public void setTexture(ImageIcon i)	{ setIcon(i); }
	public void setDefaultTexture()		{ setIcon(standard); }
	public void setWalkStat(boolean b)  { walk = b; }
	public final void setX(int x)		{ x_axis = x; }
	public final void setY(int y)		{ y_axis = y; }
	public boolean getWalkStat()		{ return walk; }
	public boolean isDoor()				{ return false;	}
	public final int getGridPosition()  { return (y_axis*Vars.tab_length)+x_axis; }
	public ImageIcon getTexture()		{ return standard; }
}

