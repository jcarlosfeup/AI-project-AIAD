import java.awt.Color;
import uchicago.src.sim.gui.NetworkDrawable;
import uchicago.src.sim.network.DefaultDrawableNode;


public class MyDefaultDrawableNode extends DefaultDrawableNode {

	/*
	 *  Se classe id = 1 é um comprador
	 *  Se classe_id = 2 é um vendedor
	 */
	private int class_id;
	private NetworkDrawable node;
	private Comprador comp;
	private Vendedor vend;


	public MyDefaultDrawableNode(){
		setLabelColor(Color.white);
		setNodeLabel("xpto");
	}

	public MyDefaultDrawableNode(NetworkDrawable nDrawable,int class_id){

		super(nDrawable);
		this.node = nDrawable;
		this.class_id = class_id;

		if(class_id == 1)
			this.comp = (Comprador)nDrawable;
		else
			this.vend = (Vendedor)nDrawable;
	}



	public Comprador getComp() {
		return comp;
	}

	public void setComp(Comprador comp) {
		this.comp = comp;
	}

	public Vendedor getVend() {
		return vend;
	}

	public void setVend(Vendedor vend) {
		this.vend = vend;
	}

	public int getClass_id() {
		return class_id;
	}

	public NetworkDrawable getNetworkDrawable(){

		return node;
	}

	public void setClass_id(int class_id) {
		this.class_id = class_id;
	}

	/*@Override
	public String[] getProbedProperties() {

		if(class_id == 1){
			
			return new String[]{"id","color"};
		}
		else{
			return new String[]{"id"};
		}

	}*/
}
