import java.util.Vector;

import uchicago.src.sim.analysis.OpenSequenceGraph;
import uchicago.src.sim.engine.SimModel;


public class MyOpenSequenceGraph extends OpenSequenceGraph {

	private int id;
	private Vector<Opiniao> opinioes;
	private Vector<Integer> results;

	
	public MyOpenSequenceGraph(String arg0, SimModel arg1) {
		super(arg0, arg1);
		this.results = new Vector<Integer>();
		results.add(0);
		results.add(0);
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Vector<Opiniao> getOpinioes() {
		return opinioes;
	}

	public void setOpinioes(Vector<Opiniao> opinioes) {
		this.opinioes = opinioes;
	}
	public Vector<Integer>  getResult() {
		return results;
	}
	public void setSucesso() {
		results.set(0, results.get(0)+1) ;
	}


	public  void setFalha() {
		results.set(1, results.get(1)+1) ;
	}

	


}
