import java.awt.*;
import java.awt.event.InputEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import javax.swing.*;

import java.awt.Robot;

public class StartMenu extends JPanel implements MouseListener{

	private static final long serialVersionUID = 1L;
	private BufferedImage image;
    private JButton menu_start = new JButton("Start"),
    				go = new JButton("Go"),
    				resume = new JButton("Resume"),
    				top = new JButton("Top Scores"),
    				exit = new JButton("Exit"),
    				back = new JButton("Back");
    private JLabel l1 = new JLabel(" "),
    	   		   l2 = new JLabel(" "),
    	   		   l3 = new JLabel(" "),
    	   		   l4 = new JLabel(" "),
    	   		   l5 = new JLabel(" ");
    private JTextField name = new JTextField();
    private static String last_name = "default";
    private Tabuleiro t;
    private GridBagLayout gridbag;
    private GridBagConstraints c;
    private boolean nameClean = true;
    private static Top10 scores = new Top10();

    public StartMenu(BufferedImage image, Tabuleiro t) {
		gridbag = new GridBagLayout();
		c = new GridBagConstraints();
		setLayout(gridbag);
		c.gridwidth = GridBagConstraints.REMAINDER;//Fim de linha
		this.t = t;
        this.image = image;
        menu_start.addMouseListener(this);
        go.addMouseListener(this);
        name.addMouseListener(this);
        resume.addMouseListener(this);
        exit.addMouseListener(this);
        back.addMouseListener(this);
        top.addMouseListener(this);
        name.setBorder(BorderFactory.createMatteBorder(3,10,3,4,Color.black));
		name.setCaretColor(Color.BLUE);
		name.setBackground(Color.black);
		name.setForeground(Color.white);
        endLines();
        mainMenu();
    }
    
    private void mainMenu(){
        add(menu_start);
        add(l1);	
        add(resume);
        add(l2);	
        add(top);
        add(l3);	add(l4);	add(l5);
        add(exit);
    }
    
    public void topMenu(){
    	gridbag.setConstraints(scores, c);
    	add(scores);
    	add(l1);
    	add(back);
    }
    
    private void endLines(){
        gridbag.setConstraints(menu_start, c);
        gridbag.setConstraints(resume, c);
        gridbag.setConstraints(l1, c);
        gridbag.setConstraints(l2, c);
        gridbag.setConstraints(l3, c);
        gridbag.setConstraints(l4, c);
        gridbag.setConstraints(l5, c);
        gridbag.setConstraints(exit, c);
        gridbag.setConstraints(name, c);
        gridbag.setConstraints(go, c);
    }

    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D)g;
        g2.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING,
                            RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
        int w = getWidth();
        int h = getHeight();
        // Colocar imagem centrada
        int x = (w - image.getWidth())/2;
        int y = (h - image.getHeight())/2;
        g2.drawImage(image, x, y, this);
    }
    
	public void mouseClicked(MouseEvent arg0) {
		if(arg0.getSource() == menu_start){
			setVisible(false);
			removeAll();
			endLines();
			add(go);
			add(l1);
			add(name);
			name.setText("Introduzir nome ");
			name.setFont(null);
			nameClean = true;
			add(l2);	add(l3);	add(l4);	add(l5);
			add(back);
			setVisible(true);
		}
		if(arg0.getSource() == go && !nameClean && !name.getText().equals("")){
			last_name = name.getText();
			t.restart(Vars.lvl_1,1);
			Robot robot = null;
			try {
				robot = new Robot();
			} catch (AWTException e) {
				e.printStackTrace();
			}
		    //Simular um click para obter focus
		    robot.mousePress(InputEvent.BUTTON1_MASK);
		    robot.mouseRelease(InputEvent.BUTTON1_MASK);
		}
		if(arg0.getSource() == exit){
			System.exit(0);
		}
		if(arg0.getSource() == name){
			if(nameClean){
				name.setFont(new Font("Tahoma",Font.BOLD, 13));
				name.setText("");
				nameClean = false;
			}
		}
		if(arg0.getSource() == back){
			setVisible(false);
			removeAll();
			endLines();
			mainMenu();
			nameClean = true;
			setVisible(true);
		}
		if(arg0.getSource() == top){
			setVisible(false);
			removeAll();
			endLines();
			topMenu();
			setVisible(true);
		}
	}

	public void mouseEntered(MouseEvent arg0) {}
	public void mouseExited(MouseEvent arg0)  {}
	public void mousePressed(MouseEvent arg0) {}
	public void mouseReleased(MouseEvent arg0){}
	
    public static void addScore(Score s) { scores.addScore(s); }
    public static String getPlayerName() { return last_name; }
}