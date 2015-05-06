import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

public class Top10 extends JPanel{

	private static final long serialVersionUID = 1L;
	Score[] top = new Score[10];

	public Top10(){
		super(new GridLayout(1,0));
		setPreferredSize(new Dimension(170,180));
		build();
	}
	
	public void build(){
		JTable table = new JTable(getArr(),new String[]{"Jogador","Pontuação"});
		table.setPreferredSize(new Dimension(150,200));
        table.setEnabled(false);
        JScrollPane scrollPane = new JScrollPane(table);
        add(scrollPane, BorderLayout.CENTER); 
	}
	
	public Object[][] getArr(){
		Object[][]data = new Object[10][2];
		for(int i=0;i<10;++i){
			if(top[i]!=null){
				data[i][0] = " "+top[i].getNome();
				data[i][1] = " "+top[i].getPontuacao();
			}
			else{
				data[i][0] = " ";
				data[i][1] = " ";
			}
		}
		return data;
	}
	
	public void addScore(Score s){
		Score[] aux = new Score[10];
		boolean add = false;
		for(int i=0,j=0;i<top.length&&j<top.length;++i){
			if(top[i]==null && !add){
				aux[j++] = s;
				break;
			}
			if(top[i]!=null){
				if(top[i].getPontuacao()<s.getPontuacao() && !add){
					aux[j++] = s;
					add = true;	
				}
				if(!top[i].getNome().equals(s.getNome()))aux[j++] = top[i];
				else if (top[i].getPontuacao()>s.getPontuacao()) return;
			}
		}
		top = aux;
		removeAll();
		build();
	}
	
	public void print(){
		for(int i=0;i<top.length;++i){
			if(top[i]!=null)
				System.out.println(top[i].toString());
		}
	}
}

