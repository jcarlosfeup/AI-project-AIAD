import java.util.Random;
import java.util.Vector;


public class Encomenda {

	private double preco;
	private int quantidade;
	private String nome;
	private int resultado;


	public Encomenda(double p,int q,String n)
	{
		this.nome = n;
		this.preco = p;
		this.quantidade = q;
		/*
		 * variável que indica se uma encomenda foi enviada com sucesso ou não
		 * 0 - neutro
		 * 1 - sucesso
		 * 2 - falha  
		 */
		this.resultado = 0;
	}
	public int getResultado() {
		return resultado;
	}
	public Encomenda(){

		Vector<String> nomes = new Vector<String>();
		Vector<Double> precos = new Vector<Double>();
		Vector<Integer> quantidades = new Vector<Integer>();

		nomes.add("Coisa1");
		nomes.add("Coisa2");
		nomes.add("Coisa3");

		precos.add(23.0);
		precos.add(100.0);
		precos.add(75.0);

		quantidades.add(100);
		quantidades.add(200);
		quantidades.add(300);

		Random generator = new Random();
		int random_number = generator.nextInt(nomes.size());
		generator = new Random();
		int random_number2 = generator.nextInt(precos.size());
		generator = new Random();
		int random_number3 = generator.nextInt(quantidades.size());

		this.nome = nomes.get(random_number);
		this.preco = precos.get(random_number2);
		this.quantidade = quantidades.get(random_number3);
	}

	public double getPreco() {
		return preco;
	}

	public void setPreço(double preco) {
		this.preco = preco;
	}

	public int getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(int quantidade) {
		this.quantidade = quantidade;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}
	
	public void setSucesso(){
		this.resultado = 1;
	}
	
	public void setFalha(){
		this.resultado = 2;
	}

}
