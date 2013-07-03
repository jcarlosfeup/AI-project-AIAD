import uchicago.src.sim.analysis.OpenSequenceGraph;
import uchicago.src.sim.analysis.Sequence;
import uchicago.src.sim.engine.BasicAction;
import uchicago.src.sim.engine.Schedule;
import uchicago.src.sim.engine.SimModelImpl;
import uchicago.src.sim.engine.SimInit;
import uchicago.src.sim.gui.DisplaySurface;
import uchicago.src.sim.gui.GraphLayout;
import uchicago.src.sim.gui.Network2DDisplay;
import uchicago.src.sim.gui.RandomGraphLayout;
import uchicago.src.sim.network.DefaultDrawableEdge;
import uchicago.src.sim.network.Node;
import uchicago.src.sim.util.Random;
import java.util.*;
import java.awt.Color;


public class Model extends SimModelImpl {

	private ArrayList<Node> agentList;
	private Schedule schedule;
	private DisplaySurface dsurf;
	private GraphLayout space;
	private int spaceSize, TypeTest, TypeGraph,Selected_test;
	private ArrayList<MyOpenSequenceGraph> graphsTrust;
	private ArrayList<MyOpenSequenceGraph> graphsSucess;

	public Model() {
		//Tipo de teste
		this.TypeTest = 0;
		//Tipo de teste
		this.TypeGraph = 0;
		//Qual dos testes pre-feitos está selecionado
		this.Selected_test=0; 
		this.spaceSize = 100;
		this.agentList = new ArrayList(); 
		this.graphsTrust = new ArrayList<MyOpenSequenceGraph>();
		this.graphsSucess = new ArrayList<MyOpenSequenceGraph>();

	}

	public int getTypeTest() {
		return TypeTest;
	}

	public void setTypeTest(int typeTest) {
		TypeTest = typeTest;
	}
	public int getTypeGraph() {
		return TypeGraph;
	}

	public void setTypeGraph(int typeGraph) {
		TypeGraph = typeGraph;
	}


	public String getName() {
		return "Color Picking Model";
	}

	public String[] getInitParam() {
		return new String[] { "TypeTest","TypeGraph","Selected_test"};
	}

	public Schedule getSchedule() {
		return schedule;
	}


	public int getSpaceSize() {
		return spaceSize;
	}

	public void setSpaceSize(int spaceSize) {
		this.spaceSize = spaceSize;
	}

	public int getSelected_test() {
		return Selected_test;
	}

	public void setSelected_test(int selected_test) {
		Selected_test = selected_test;
	}

	public void setup() {
		schedule = new Schedule();
		if (dsurf != null) dsurf.dispose();
		dsurf = new DisplaySurface(this, "Shopping Trust Network");
		registerDisplaySurface("Shopping Trust Network", dsurf);

	}

	public void begin() {
		buildModel();
		buildDisplay();
		buildSchedule();
	}

	private void buildModel() {
		//Testes pre-feitos
		if(Selected_test>5){Selected_test=0;}
		if(Selected_test==0){
		
			Vendedor v1 = new Vendedor(0);
			Vendedor v2 = new Vendedor(1);
			v2.addEncomendaStock(new Encomenda(10,70000,"Meias"),0);
			v1.addEncomendaStock(new Encomenda(20,60000,"Livros"),100);
			Comprador c2 = new Comprador(1,2);
			Comprador c1 = new Comprador(0,2);
			for(int g=0;g<10;g++)
			c1.addEncomendaLista(new Encomenda(50,50,"Livros"));
			for(int g=0;g<10;g++)
			c2.addEncomendaLista(new Encomenda(50,50,"Meias"));
			

			MyDefaultDrawableNode node1 = new MyDefaultDrawableNode(v1,2);
			MyDefaultDrawableNode node2 = new MyDefaultDrawableNode(v2,2);
			MyDefaultDrawableNode node3 = new MyDefaultDrawableNode(c1,1);
			MyDefaultDrawableNode node4 = new MyDefaultDrawableNode(c2,1);


			agentList.add(node1);
			agentList.add(node2);
			agentList.add(node3);
			agentList.add(node4);
			
			MyOpenSequenceGraph g1 = new MyOpenSequenceGraph("Confianca nos vendedores do comprador "+c1.getid(), this);
			g1.setId(c1.getid());
			g1.setOpinioes(c1.getConf_vendedores());
			graphsTrust.add(g1);

			MyOpenSequenceGraph g2 = new MyOpenSequenceGraph("Confianca nos vendedores do comprador "+c2.getid(), this);
			g2.setId(c2.getid());
			g2.setOpinioes(c2.getConf_vendedores());
			graphsTrust.add(g2);
			MyOpenSequenceGraph g1s =new MyOpenSequenceGraph("Sucessos de "+c1.getid(), this);
			MyOpenSequenceGraph g2s=new MyOpenSequenceGraph("Sucessos de "+c2.getid(), this);
			graphsSucess.add(g1s);
			graphsSucess.add(g2s);
		
		}
		if(Selected_test==1){
			Vendedor v1 = new Vendedor(0);
	
			v1.addEncomendaStock(new Encomenda(20,6000,"Livros"),100);
			v1.addEncomendaStock(new Encomenda(20,6000,"Lapis"),0);
			Comprador c2 = new Comprador(1,1);
			Comprador c1 = new Comprador(0,1);
			for(int g=0;g<10;g++)
			c1.addEncomendaLista(new Encomenda(50,50,"Livros"));
			for(int g=0;g<10;g++)
			c2.addEncomendaLista(new Encomenda(50,50,"Lapis"));
			

			MyDefaultDrawableNode node1 = new MyDefaultDrawableNode(v1,2);
		
			MyDefaultDrawableNode node2= new MyDefaultDrawableNode(c1,1);
			MyDefaultDrawableNode node3 = new MyDefaultDrawableNode(c2,1);


			agentList.add(node1);
		
			agentList.add(node2);
			agentList.add(node3);
			
			MyOpenSequenceGraph g1 = new MyOpenSequenceGraph("Confianca nos vendedores do comprador "+c1.getid(), this);
			g1.setId(c1.getid());
			g1.setOpinioes(c1.getConf_vendedores());
			graphsTrust.add(g1);

			MyOpenSequenceGraph g2 = new MyOpenSequenceGraph("Confianca nos vendedores do comprador "+c2.getid(), this);
			g2.setId(c2.getid());
			g2.setOpinioes(c2.getConf_vendedores());
			graphsTrust.add(g2);
			MyOpenSequenceGraph g1s =new MyOpenSequenceGraph("Sucessos de "+c1.getid(), this);
			MyOpenSequenceGraph g2s=new MyOpenSequenceGraph("Sucessos de "+c2.getid(), this);
			graphsSucess.add(g1s);
			graphsSucess.add(g2s);
		
		}
		if(Selected_test==2){
			Vendedor v1 = new Vendedor(0);
			Vendedor v2 = new Vendedor(1);
	
			v1.addEncomendaStock(new Encomenda(20,60000,"Livros"),100);
			v2.addEncomendaStock(new Encomenda(20,60000,"Livros"),50);
		
			Comprador c1 = new Comprador(0,2);
			for(int g=0;g<100;g++)
			c1.addEncomendaLista(new Encomenda(50,50,"Livros"));
			
			

			MyDefaultDrawableNode node1 = new MyDefaultDrawableNode(v2,2);
			MyDefaultDrawableNode node2 = new MyDefaultDrawableNode(c1,1);
			MyDefaultDrawableNode node3 = new MyDefaultDrawableNode(v1,2);


			agentList.add(node1);
			agentList.add(node2);
			agentList.add(node3);
			
			MyOpenSequenceGraph g1 = new MyOpenSequenceGraph("Confianca nos vendedores do comprador "+c1.getid(), this);
			g1.setId(c1.getid());
			g1.setOpinioes(c1.getConf_vendedores());
			graphsTrust.add(g1);

			
	
			MyOpenSequenceGraph g1s =new MyOpenSequenceGraph("Sucessos de "+c1.getid(), this);
	
			graphsSucess.add(g1s);
		
		
		}
		
		if(Selected_test==3){
			Vendedor v1 = new Vendedor(0);
			Vendedor v2 = new Vendedor(1);
	
			v1.addEncomendaStock(new Encomenda(50,60000,"Livros"),100);
			v2.addEncomendaStock(new Encomenda(40,60000,"Livros"),0);
			for(int g=0;g<8;g++){
			Encomenda enc=	new Encomenda(50,50,"Livros");
			enc.setFalha();
			v2.addEncomendaHist(enc);}
			for(int g=0;g<8;g++){
				Encomenda enc=	new Encomenda(50,50,"Livros");
				enc.setSucesso();
				v1.addEncomendaHist(enc);}
			Comprador c1 = new Comprador(0,2);
			for(int g=0;g<10;g++){
			c1.addEncomendaLista(new Encomenda(50,50,"Livros"));
			}
			
			

			MyDefaultDrawableNode node1 = new MyDefaultDrawableNode(v2,2);	
			MyDefaultDrawableNode node2 = new MyDefaultDrawableNode(c1,1);
			MyDefaultDrawableNode node3 = new MyDefaultDrawableNode(v1,2);


			agentList.add(node1);
		
			agentList.add(node2);
			agentList.add(node3);
			
			MyOpenSequenceGraph g1 = new MyOpenSequenceGraph("Confianca nos vendedores do comprador "+c1.getid(), this);
			g1.setId(c1.getid());
			g1.setOpinioes(c1.getConf_vendedores());
			graphsTrust.add(g1);

			
	
			MyOpenSequenceGraph g1s =new MyOpenSequenceGraph("Sucessos de "+c1.getid(), this);
	
			graphsSucess.add(g1s);
		
		
		}
		if(Selected_test==4){
		Vendedor v1 = new Vendedor(0);
		Vendedor v2 = new Vendedor(1);

		v2.addEncomendaStock(new Encomenda(20,600000000,"Livros"),100);
		v2.addEncomendaStock(new Encomenda(10,600000000,"Meias"),50);
		v1.addEncomendaStock(new Encomenda(20,600000000,"Livros"),0);
	

		Comprador c1 = new Comprador(0,2);
		for(int g=0;g<1000;g++)
			c1.addEncomendaLista(new Encomenda(50,50,"Livros"));

		for(int g=0;g<1000;g++)
			c1.addEncomendaLista(new Encomenda(50,50,"Meias"));


		Comprador c2 = new Comprador(1,2);
		c2.addEncomendaLista(new Encomenda(50,50,"Meias"));


		MyDefaultDrawableNode node1 = new MyDefaultDrawableNode(v1,2);
		MyDefaultDrawableNode node2 = new MyDefaultDrawableNode(v2,2);
		MyDefaultDrawableNode node3 = new MyDefaultDrawableNode(c1,1);
		MyDefaultDrawableNode node4 = new MyDefaultDrawableNode(c2,1);


		agentList.add(node1);
		agentList.add(node2);
		agentList.add(node3);
		agentList.add(node4);


		MyOpenSequenceGraph g1 = new MyOpenSequenceGraph("Confianca nos vendedores do comprador "+c1.getid(), this);
		g1.setId(c1.getid());
		g1.setOpinioes(c1.getConf_vendedores());
		graphsTrust.add(g1);

		MyOpenSequenceGraph g2 = new MyOpenSequenceGraph("Confianca nos vendedores do comprador "+c2.getid(), this);
		g2.setId(c2.getid());
		g2.setOpinioes(c2.getConf_vendedores());
		graphsTrust.add(g2);
		MyOpenSequenceGraph g1s =new MyOpenSequenceGraph("Sucessos de "+c1.getid(), this);
		MyOpenSequenceGraph g2s=new MyOpenSequenceGraph("Sucessos de "+c2.getid(), this);
		graphsSucess.add(g1s);
		graphsSucess.add(g2s);
		
		}
		if(Selected_test==5){
			
			Vendedor v1 = new Vendedor(0);
			Vendedor v2 = new Vendedor(1);
			Vendedor v3 = new Vendedor(2);
			Vendedor v4 = new Vendedor(3);
			Vendedor v5 = new Vendedor(4);

			v1.addEncomendaStock(new Encomenda(10,600000,"Brinquedo"),100);
			v1.addEncomendaStock(new Encomenda(10,600000,"Relogio SWATCH"),50);
			v2.addEncomendaStock(new Encomenda(10,600000000,"Relogio SWATCH"),100);
			v2.addEncomendaStock(new Encomenda(10,600000000,"Carro"),0);
			v3.addEncomendaStock(new Encomenda(10,600000000,"Carro"),100);
			v4.addEncomendaStock(new Encomenda(10,600000000,"Camisola MANGO"),60);
			v5.addEncomendaStock(new Encomenda(10,600000000,"Camisola MANGO"),80);
		

			Comprador c1 = new Comprador(0,5);
			for(int g=0;g<4;g++)
				c1.geraEncomendas();




			MyDefaultDrawableNode node1 = new MyDefaultDrawableNode(v1,2);
			MyDefaultDrawableNode node2 = new MyDefaultDrawableNode(v2,2);
			MyDefaultDrawableNode node3 = new MyDefaultDrawableNode(c1,1);
			MyDefaultDrawableNode node4 = new MyDefaultDrawableNode(v3,2);
			MyDefaultDrawableNode node5 = new MyDefaultDrawableNode(v4,2);
			MyDefaultDrawableNode node6 = new MyDefaultDrawableNode(v5,2);


			agentList.add(node1);
			agentList.add(node2);
			agentList.add(node3);
			agentList.add(node4);
			agentList.add(node5);
			agentList.add(node6);


			MyOpenSequenceGraph g1 = new MyOpenSequenceGraph("Confianca nos vendedores do comprador "+c1.getid(), this);
			g1.setId(c1.getid());
			g1.setOpinioes(c1.getConf_vendedores());
			graphsTrust.add(g1);

			
			MyOpenSequenceGraph g1s =new MyOpenSequenceGraph("Sucessos de "+c1.getid(), this);
		
			graphsSucess.add(g1s);
		
			
			
		}
	}


	private void buildDisplay() {


		space = new RandomGraphLayout(agentList,spaceSize, spaceSize);
		space.updateLayout();

		Network2DDisplay display = new Network2DDisplay(space);

		dsurf.addDisplayableProbeable(display, "Agents Space");
		dsurf.display();
//Qual será o tipo de grafico apresentado
		//Grafo de Confianca
		if(TypeGraph==0){
			for(int i = 0;i < graphsTrust.size();i++){

				MyOpenSequenceGraph graf = graphsTrust.get(i);

				if(graf != null) graf.dispose();

				graf.setAxisTitles("Time","Trust");

				for(int j = 0; j < graf.getOpinioes().size();j++){



					graf.addSequence("Confianca no vendedor "+graf.getOpinioes().get(j).getId(),new MySeq(j,graf));

				}
				graf.display();

			}}else 		//Grafo de Sucesso
				for(int i = 0;i < graphsSucess.size();i++){

					MyOpenSequenceGraph graf = graphsSucess.get(i);

					if(graf != null) graf.dispose();

					graf.setAxisTitles("Time","Trust");

					graf.addSequence("Sucesso ",new MySeq(0,graf));
					graf.addSequence("Falhanco ",new MySeq(1,graf));


					graf.display();




				}


	}

	class MySeq implements Sequence {
		int j;
		MyOpenSequenceGraph graph1;
		MySeq(int j, MyOpenSequenceGraph graph1) {
			this.j = j;
			this.graph1=graph1;
		}


		public double getSValue() {

			double val = 0.0;
			//Grafo de Confianca
			if(TypeGraph==0){


				val = graph1.getOpinioes().get(j).getConfianca();

			}else {//Grafo de Sucesso
				val = graph1.getResult().get(j);

			}

			return val;

		}

	}
	//Devolve o node de um vendedor apartir do seu id
	public MyDefaultDrawableNode pesquisa_vendedor(int id){

		int tam = agentList.size();

		MyDefaultDrawableNode n1 = null;


		for(int i = 0; i < tam;i++){
			MyDefaultDrawableNode vend1 = (MyDefaultDrawableNode) agentList.get(i);

			if(vend1.getClass_id()==2)
			{
				if( ((Vendedor)((MyDefaultDrawableNode)agentList.get(i)).getNetworkDrawable()).getid() == id)
				{		
					n1 = (MyDefaultDrawableNode)agentList.get(i);
				}
			}
		}	

		return n1;
	}

	@SuppressWarnings("unused")
	private void buildSchedule() {
		schedule.scheduleActionBeginning(0, new MainAction());
		schedule.scheduleActionAtInterval(1, dsurf, "updateDisplay", Schedule.LAST);
		//Grafo de Sucesso
		if(TypeGraph==0){
			for(int i = 0; i < graphsTrust.size();i++){

				schedule.scheduleActionAtInterval(1, graphsTrust.get(i),"step", Schedule.LAST);
			}}else{//Grafo de Confianca
				for(int i = 0; i < graphsSucess.size();i++){

					schedule.scheduleActionAtInterval(1, graphsSucess.get(i),"step", Schedule.LAST);
				}}

	}

	class MainAction extends BasicAction {

		public void execute() {
			Boolean doChangesExist = false;
			if (TypeTest>2)TypeTest=0;
			if (TypeGraph>1)TypeGraph=0;

			// Percorre todos os agentes
			for(int i = 0; i < agentList.size(); i++) {
				MyDefaultDrawableNode comp1 = (MyDefaultDrawableNode) agentList.get(i);
				int tipo_classe = comp1.getClass_id();

				// Se o agente for um comprador
				if( tipo_classe == 1)
				{	
					Comprador comp =((Comprador)((MyDefaultDrawableNode)agentList.get(i)).getNetworkDrawable());
					System.out.println(comp.getLista_compras().size());
					Vector<Encomenda> lista_compras=comp.getLista_compras();
					if(!lista_compras.isEmpty()){

						// Seleciona uma encomenda aleatoria do Comprador
						Encomenda order= comp.SelectRandEncomenda();
						



						// Escolhe um vendedor com que irá realizar uma iteração
						Vector<Integer> id_stock= escolhe_vendedor(comp,order);
						if(id_stock.get(0)!=-1){
							Vendedor vendedor = ((Vendedor)((MyDefaultDrawableNode)agentList.get(id_stock.get(0))).getNetworkDrawable());
							
							Opiniao op = comp.get_opiniao(vendedor.getid());
							System.out.println("ID do Vendedor : "+vendedor.getid());
							System.out.println("ID do Comprador : "+comp.getid());
							System.out.println("Encomenda  : "+order.getNome());
							int sucesso=1;
							//Probablidade de sucesso
							int odd = vendedor.generate_odds();
							//indice do material no stock
							int ind_stock = id_stock.get(1);
							//Verifica se o valor gerado esta dentro da probabilidade de suceder
							if(odd<=vendedor.getSucesso(ind_stock)){
								//Indica que a ordem teve sucesso
								order.setSucesso();
								sucesso=0;
								System.out.println("Sucesso");
								//Remove a ordem da lista de compras do comprador
								lista_compras.remove(order);
								((Comprador)((MyDefaultDrawableNode)agentList.get(i)).getNetworkDrawable()).setLista_compras(lista_compras);
								//Retira a quantidade da encomenda do stock
									if(Selected_test!=5){
								int novo_stock = vendedor.getStock().get(ind_stock).getQuantidade() - order.getQuantidade();
								((Vendedor)((MyDefaultDrawableNode)agentList.get(id_stock.get(0))).getNetworkDrawable()).actualizaStock(novo_stock,ind_stock);}
									else
										((Comprador)((MyDefaultDrawableNode)agentList.get(i)).getNetworkDrawable()).geraEncomendas();
								//Incrementa o numero de transações com sucesso do comprador
								((Vendedor)((MyDefaultDrawableNode)agentList.get(id_stock.get(0))).getNetworkDrawable()).addSucesso();
							}else {order.setFalha();System.out.println("Falha");
							((Vendedor)((MyDefaultDrawableNode)agentList.get(id_stock.get(0))).getNetworkDrawable()).addFalha();
							}
							doChangesExist=true;
							System.out.println("--------------- ");
							//Actualiaza a confianca do comprador referente ao vendedor
							op.actualiza_confianca(sucesso);					
							int indice_vend=	((Vendedor)((MyDefaultDrawableNode)agentList.get(id_stock.get(0))).getNetworkDrawable()).getid();
							((Comprador)((MyDefaultDrawableNode)agentList.get(i)).getNetworkDrawable()).actualiza_opiniao(indice_vend,op);
							
							//Adiciona o resultado da transacao ao historico do vendedor
							((Vendedor)((MyDefaultDrawableNode)agentList.get(id_stock.get(0))).getNetworkDrawable()).addEncomendaHist(order);
							
							
							//Actualiza os graficos
							int id_comprador = ((Comprador)((MyDefaultDrawableNode)agentList.get(i)).getNetworkDrawable()).getid();
					
							for(int k = 0; k < graphsTrust.size();k++){

								if(graphsTrust.get(k).getId() == id_comprador){
									graphsTrust.get(k).setOpinioes(((Comprador)((MyDefaultDrawableNode)agentList.get(i)).getNetworkDrawable()).getConf_vendedores());
									if(sucesso==0)
										graphsSucess.get(k).setSucesso();
									else
										graphsSucess.get(k).setFalha();
								}
							}

						}


					}
				}

			}
			if(doChangesExist)
				DrawEdges();
		}

		public void DrawEdges(){

//Percorre dos os agentes
			for(int i = 0; i < agentList.size();i++)
			{ 
				MyDefaultDrawableNode comp1 = (MyDefaultDrawableNode) agentList.get(i);
				int tipo_classe = comp1.getClass_id();

				//É um comprador
				if( tipo_classe == 1)
				{	


					Vector<Opiniao> opinioes = ((Comprador)((MyDefaultDrawableNode)agentList.get(i)).getNetworkDrawable()).getConf_vendedores();
					//Percorre as opinioes do comprador e cria uma aresta com os compradores com que interagiu
					for(int j = 0; j < opinioes.size();j++)
					{  
						if( opinioes.get(j).isActivado() )
						{  
							double confianca= opinioes.get(j).getConfianca();

							MyDefaultDrawableNode vend1 = pesquisa_vendedor(opinioes.get(j).getId());
						
							DefaultDrawableEdge edge = new DefaultDrawableEdge(comp1,vend1);
//Cor varia de acordo com o nivel de confianca
							if(confianca > 0.6)
								edge.setColor(Color.green);
							else if(confianca > 0.4)
								edge.setColor(Color.yellow);
							else
								edge.setColor(Color.red);


							edge.setTo(vend1);
							edge.setFrom(comp1);
							vend1.addInEdge(edge);
							comp1.addOutEdge(edge);

						}
					}		


				}	
			}System.out.println(" ");
		}
	}

//Verifica se o Vendedor tem o material que o vendedor deseja, se tem a quantidade necessaria e se o preco é menor ou igual ao que o comprador está disposto a pagar
	public Map<Boolean,Integer> HasMaterial(Encomenda order, Vendedor vend){

		Map<Boolean,Integer> result = new HashMap<Boolean,Integer>();
		for(int i = 0; i < vend.getStock().size(); i++) {

			if(order.getNome().equals(vend.getStock().elementAt(i).getNome())){
				if(order.getPreco()>=vend.getStock().elementAt(i).getPreco() && order.getQuantidade()<=vend.getStock().elementAt(i).getQuantidade()){

					result.put(true,i);
					return result;
				}
			}
		}

		result.put(false,0);
		return result;
	}
//Escolhe o vendedor com que vai interagir
	public Vector<Integer> escolhe_vendedor(Comprador c1,Encomenda e1){

		int id_vendedor = -1;
		int stock_val=0;
		double preco_min = 999999999.0;
		double melhor_preco = 99999999.0;
		double melhor_confianca = -1.0;
		Vector<Opiniao> op_vendedores =  c1.getConf_vendedores();
		// Nivel de preferencia
		int nivel_pref = 5;
		
		for(int x = 0; x < agentList.size(); x++) {

			MyDefaultDrawableNode vend1 = (MyDefaultDrawableNode) agentList.get(x);
			int tipo_classe2 = vend1.getClass_id();

			//É um vendedor
			if( tipo_classe2 == 2){
				Vendedor vendedor = ((Vendedor)((MyDefaultDrawableNode)agentList.get(x)).getNetworkDrawable());
				ID3 tree =new ID3();
				Boolean ID3res=true;
				//Cria uma arvore ID3 para o vendedor e verifica se o vendedor consegue satisfazer o estereotipo criado
				if(TypeTest==0){
					try {
						tree.readHistorico(vendedor.getHistorico());
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					ID3res= tree.createDecisionTree(e1);
					}

				
				Map<Boolean,Integer> vend_hasMaterial = HasMaterial(e1,vendedor);
				System.out.println("Encomenda  : "+e1.getNome());
				//Se o vendedor possui a encomenda que o comprador quer
				if(vend_hasMaterial.containsKey(true)){
					

					double preco_enc_vend = vendedor.getStock().get(vend_hasMaterial.get(true)).getPreco();
					//Verifica o tipo de teste (o vendedor irá ignorar os resultados do teste id3 enquanto nao houver um minimo de 7 encomendas no historico)
					if(( ID3res==true && TypeTest==0)||TypeTest==1||(vendedor.getHistorico().size()<=7 &&TypeTest==0 )){
						


						Opiniao op1 = op_vendedores.get(vendedor.getid());




						//escolher logo o vendedor com esta confiança
						if(op1.getConfianca() > 0.9 && op1.isActivado()  && nivel_pref >= 1){

							if(nivel_pref > 1){
								melhor_preco = preco_enc_vend;
								nivel_pref = 1;
								id_vendedor = x;
								stock_val= vend_hasMaterial.get(true);
							}else if(preco_enc_vend < melhor_preco){
								melhor_preco = preco_enc_vend;
								id_vendedor = x;
								stock_val= vend_hasMaterial.get(true);
							}

						}else if(op1.getConfianca() > 0.6 && op1.getConfianca() <= 0.9 && op1.isActivado()  && nivel_pref >= 2){


							if(nivel_pref > 2){

								nivel_pref = 2;
								melhor_preco = preco_enc_vend;
								id_vendedor = x;
								stock_val= vend_hasMaterial.get(true);

							}else if(preco_enc_vend < melhor_preco){
								melhor_preco = preco_enc_vend;
								id_vendedor = x;
								stock_val= vend_hasMaterial.get(true);
							}

						}else if(! op1.isActivado()  && nivel_pref >= 3){

							if(nivel_pref > 3){

								nivel_pref = 3;
								preco_min = preco_enc_vend;
								id_vendedor = x;
								stock_val= vend_hasMaterial.get(true);

							}else if(preco_enc_vend < preco_min ){
								preco_min = preco_enc_vend;
								id_vendedor =x;
								stock_val= vend_hasMaterial.get(true);
							}

						}
						else if(op1.getConfianca() > 0.4 && op1.getConfianca() <= 0.6 && op1.isActivado() && nivel_pref >= 4  ){

							if(nivel_pref > 4){

								nivel_pref = 4;
								melhor_preco = preco_enc_vend;
								id_vendedor = x;
								stock_val= vend_hasMaterial.get(true);

							}else if(preco_enc_vend < melhor_preco){
								melhor_preco = preco_enc_vend;
								id_vendedor = x;
								stock_val= vend_hasMaterial.get(true);
							}
						}

						else if(op1.getConfianca() <= 0.4 && op1.isActivado() && nivel_pref == 5 ){

							if(op1.getConfianca() == melhor_confianca && melhor_confianca==0){
								int odd= vendedor.generate_odds();
								if(odd>=50){
									melhor_confianca = op1.getConfianca();
									id_vendedor = x;
									stock_val= vend_hasMaterial.get(true);}

							}else
								if(op1.getConfianca() > melhor_confianca){
									melhor_confianca = op1.getConfianca();
									id_vendedor = x;
									stock_val= vend_hasMaterial.get(true);
								}


						}
					}else if(TypeTest==2){


						if(preco_enc_vend < melhor_preco){
							melhor_preco = preco_enc_vend;
							id_vendedor = x;
							stock_val= vend_hasMaterial.get(true);}
					}
				}
			}
		}
		Vector <Integer> id_stock =new Vector<Integer>();
		id_stock.add(id_vendedor);
		
		id_stock.add (stock_val);
		return id_stock;
	}
	
	
	

	public static void main(String[] args) {
		SimInit init = new SimInit();
		init.loadModel(new Model(), null, false);
	}
}
