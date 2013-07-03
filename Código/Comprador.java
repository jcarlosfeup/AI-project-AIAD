import java.awt.Color;
import java.util.Random;
import java.util.Vector;

import uchicago.src.sim.engine.CustomProbeable;
import uchicago.src.sim.gui.OvalNetworkItem;
import uchicago.src.sim.gui.SimGraphics;


public class Comprador extends OvalNetworkItem implements CustomProbeable{

	private int id;
	private Color color;
	private Vector<Opiniao> conf_vendedores;
	private Vector<Encomenda> lista_compras;

	public Vector<Encomenda> getLista_compras() {
		return lista_compras;
	}


	public void setLista_compras(Vector<Encomenda> lista_compras) {
		this.lista_compras = lista_compras;
	}


	public Comprador(int id,int num_agentes)
	{
		super(0,0);
		this.id = id;
		this.color = Color.GREEN;
		this.lista_compras = new Vector<Encomenda>();
		conf_vendedores = new Vector<Opiniao>(num_agentes);
		for(int i =0; i< num_agentes; i++)
			conf_vendedores.add(new Opiniao(i));
	
	}




	public int getid(){ return id; }

	public Vector<Opiniao> getConf_vendedores() {
		return conf_vendedores;
	}

	
	public void setConf_vendedores(Vector<Opiniao> conf_vendedores) {
		this.conf_vendedores = conf_vendedores;
	}

	public void setColor(Color color) { this.color = color; }

	public Color getColor(){ return color; }

	public void draw(SimGraphics g) 
	{
		g.drawFastCircle(color);
	}

	public double get_confiança_vendedor(int ind){

		return conf_vendedores.get(ind).getConfianca();
	}
	public Opiniao get_opiniao(int ind){

		return conf_vendedores.get(ind);
	}
	public void actualiza_opiniao(int pos, Opiniao opinion) {
		conf_vendedores.setElementAt(opinion, pos);
	}
	public void addEncomendaLista(Encomenda e1){

		lista_compras.add(e1);
	}
	public Encomenda SelectRandEncomenda( ){
		Random randomGenerator = new Random();
		int random_number = randomGenerator.nextInt(lista_compras.size());
		return lista_compras.elementAt(random_number);
	}

	public void geraEncomendas(){


		String[] nomes = {"Brinquedo","Relogio SWATCH","Carro","Camisola MANGO"};
		int[] quantidades = {10,20,30,40};
		double[] precos = {10.0,25.0,100.0};

	

		Random randomGenerator = new Random();
	
		int pos_aleat = randomGenerator.nextInt(nomes.length);
		int pos_aleat2 = randomGenerator.nextInt(quantidades.length);
		int pos_aleat3 = randomGenerator.nextInt(precos.length);
	


		Encomenda tipo1 = new Encomenda(precos[pos_aleat3],quantidades[pos_aleat2],nomes[pos_aleat]);
	

		addEncomendaLista(tipo1);

		}
		
	@Override
	public String[] getProbedProperties() {
		
		return new String[]{"id","color","conf_vendedores"};
	}
}

