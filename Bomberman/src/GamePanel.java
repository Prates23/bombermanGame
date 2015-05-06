import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.LayoutManager;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

public class GamePanel extends JPanel{

	private static final long serialVersionUID = 1L;
	private BufferedImage image;

	public GamePanel(LayoutManager l, String imagePath){
		super(l);
		try { image = ImageIO.read(new File(imagePath)); }
		catch(IOException e){	e.printStackTrace();	}
	}
	
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D)g;
        g2.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
        int w = getWidth();
        int h = getHeight();
        // Colocar imagem centrada
        int x = (w - image.getWidth())/2;
        int y = (h - image.getHeight())/2;
        g2.drawImage(image, x, y, this);
    }
}
