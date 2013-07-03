import java.awt.Color;
import java.util.Random;
import java.util.Vector;

import uchicago.src.sim.engine.CustomProbeable;
import uchicago.src.sim.gui.OvalNetworkItem;

public class Vendedor extends OvalNetworkItem implements CustomProbeable{

	private int id;
	private Vector<Encomenda> stock;
	private Vector<Integer> odds;
	private Vector<Encomenda> historico;
	int numero_sucesso;
	int numero_falhas;

	public Vector<Encomenda> getHistorico() {
		return historico;
	}

	public void setHistorico(Vector<Encomenda> historico) {
		this.historico = historico;
	}

	public Vendedor(int id)
	{
		super(0,0);
		this.color = Color.BLUE;
		this.id = id;
		this.odds=new Vector<Integer>();
		this.historico = new Vector<Encomenda>();
		this.stock = new Vector<Encomenda>();
		this.numero_sucesso=0;
		this.numero_falhas=0;
	}
	public int getNumero_sucesso() {
		return numero_sucesso;
	}

	public int getNumero_falhas() {
		return numero_falhas;
	}
	public void addFalha() {
		numero_falhas++;
	}
	public void addSucesso() {
		numero_sucesso++;
	}

	public Vector<Encomenda> getStock() {
		return stock;
	}
	public int getSucesso(int pos) {
		return odds.elementAt(pos);
	}

	public void setStock(Vector<Encomenda> stock) {
		this.stock = stock;
	}

	public int getid(){ return id; }

	public Color getColor(){ 
		return color; 
	}


	public void addEncomendaStock(Encomenda e1,int odd){
		stock.add(e1);
		odds.add(odd);
	}

	public void addEncomendaHist(Encomenda e1){
		historico.add(e1);
	}
	
	public void actualizaStock(int nova_quant,int pos){
		
		stock.get(pos).setQuantidade(nova_quant);
	}
	public int generate_odds( ){
		Random randomGenerator = new Random();
		int random_number = randomGenerator.nextInt(100)+1;
		return random_number;
	}
	

	
	@Override
	public String[] getProbedProperties() {
		
		return new String[]{"id","stock","odds"};
	}
}
