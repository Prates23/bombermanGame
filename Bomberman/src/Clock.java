import java.awt.Color;
import java.awt.Font;
import java.util.Timer;
import java.util.TimerTask;
import javax.swing.JLabel;

public class Clock extends JLabel{
	
	private static final long serialVersionUID = 1L;
	private Timer timer = new Timer();
	private static int seconds;
	private static int default_secs;

	public Clock(int seconds){
		Clock.seconds = seconds;
		default_secs = seconds;
		setText(""+seconds);
		setIcon(Vars.clock);
		setFocusable(false);
		setBorder(null);
		setForeground(Color.white);
		setFont(new Font("Tahoma",Font.BOLD, 15));
	}
	
	public Clock start(){
		timer.schedule(new Task(this),0,1000);
		return this;
	}

	public class Task extends TimerTask {
		JLabel l;
		public Task(JLabel l){	this.l=l;	}
		public void run() {
			if(seconds<0){
				timer.cancel();
				return;
			}
			l.setText(seconds/60+":"+seconds%60);
			if(seconds%60<10) l.setText(seconds/60+":0"+seconds%60);
			if(seconds%60==0) l.setText(seconds/60+":00");
			--seconds;
		}
	}
	
	public static int getRemainingTime()	{ return seconds; }
	public static void defaultClock()		{ seconds = default_secs; }
}
