public class Score{
	private String nome;
	private int pontuacao;
	public Score(String n, int p){nome = n; pontuacao = p; }
	public String getNome(){	return nome; }
	public int getPontuacao(){	return pontuacao; }
	public String toString(){	return nome+" "+pontuacao; }
}
