import java.util.Vector;


public class ID3 {


	int numAttributes;		
	String []attributeNames;	
	Vector []domains;

	class DataPoint {


		public int []attributes;

		public DataPoint(int numattributes) {
			attributes = new int[numattributes];
		}
	};


	 
	class TreeNode {
		public double entropy;		
		public Vector data;		
		public int decompositionAttribute;
		public int decompositionValue;		
		public TreeNode []children;		
		public TreeNode parent;			

		public TreeNode() {
			data = new Vector();
		}

	};

	
	TreeNode root = new TreeNode();


	public int getSymbolValue(int attribute, String symbol) {
		
		int index = domains[attribute].indexOf(symbol);
		
		if (index < 0) {
			domains[attribute].addElement(symbol);
			return domains[attribute].size() -1;
		}
		return index;
	}


	public int []getAllValues(Vector data, int attribute) {
		Vector values = new Vector();
		int num = data.size();
		for (int i=0; i< num; i++) {
			DataPoint point = (DataPoint)data.elementAt(i);
			String symbol = (String)domains[attribute].elementAt(point.attributes[attribute] );
			int index = values.indexOf(symbol);
			if (index < 0) {
				values.addElement(symbol);
			}
		}

		int []array = new int[values.size()];
		for (int i=0; i< array.length; i++) {
			String symbol = (String)values.elementAt(i);
			array[i] = domains[attribute].indexOf(symbol);
		}
		values = null;
		return array;
	}


	public Vector getSubset(Vector data, int attribute, int value) {
		Vector subset = new Vector();

		int num = data.size();
		for (int i=0; i< num; i++) {
			DataPoint point = (DataPoint)data.elementAt(i);
			if (point.attributes[attribute] == value) subset.addElement(point);
		}
		return subset;

	}



	public double calculateEntropy(Vector data) {

		int numdata = data.size();
		if (numdata == 0) return 0;

		int attribute = numAttributes-1;
		int numvalues = domains[attribute].size();
		double sum = 0;
		for (int i=0; i< numvalues; i++) {
			int count=0;
			for (int j=0; j< numdata; j++) {
				DataPoint point = (DataPoint)data.elementAt(j);
				if (point.attributes[attribute] == i) count++;
			}
			double probability = 1.*count/numdata;
			if (count > 0) sum += -probability*Math.log(probability);
		}
		return sum;

	}

	
	public boolean alreadyUsedToDecompose(TreeNode node, int attribute) {
		if (node.children != null) {
			if (node.decompositionAttribute == attribute )
				return true;
		}
		if (node.parent == null) return false;
		return alreadyUsedToDecompose(node.parent, attribute);
	}

	public void decomposeNode(TreeNode node) {

		double bestEntropy=0;
		boolean selected=false;
		int selectedAttribute=0;

		int numdata = node.data.size();
		int numinputattributes = numAttributes-1;
		node.entropy = calculateEntropy(node.data);
		if (node.entropy == 0) return;

		
		for (int i=0; i< numinputattributes; i++) {
			int numvalues = domains[i].size();
			if ( alreadyUsedToDecompose(node, i) ) continue;
			double averageentropy = 0;
			for (int j=0; j< numvalues; j++) {
				Vector subset = getSubset(node.data, i, j);
				if (subset.size() == 0) continue;
				double subentropy = calculateEntropy(subset);
				averageentropy += subentropy * subset.size(); 
			}

			averageentropy = averageentropy / numdata;  
			if (selected == false) {
				selected = true;
				bestEntropy = averageentropy;
				selectedAttribute = i;
			} else {
				if (averageentropy < bestEntropy) {
					selected = true;
					bestEntropy = averageentropy;
					selectedAttribute = i;
				}
			}

		}

		if (selected == false) return;


		int numvalues = domains[selectedAttribute].size();
		node.decompositionAttribute = selectedAttribute;
		node.children = new TreeNode [numvalues];
		for (int j=0; j< numvalues; j++) {
			node.children[j] = new TreeNode();
			node.children[j].parent = node;
			node.children[j].data = getSubset(node.data, selectedAttribute, j);
			node.children[j].decompositionValue = j;
		}

		
		for (int j=0; j< numvalues; j++) {
			decomposeNode(node.children[j]);
		}

		
		node.data = null;		

	}

	public int readHistorico(Vector<Encomenda> teste)  throws Exception {


		numAttributes = 4;


		domains = new Vector[numAttributes];
		for (int i=0; i < numAttributes; i++) domains[i] = new Vector();
		attributeNames = new String[numAttributes];
	
		attributeNames[0]="Nome";
		attributeNames[1]="Preco";
		attributeNames[2]="Quantidade";
		attributeNames[3]="Resultado";

		for (int i=0; i < teste.size(); i++) {
			DataPoint point = new DataPoint(numAttributes);
			point.attributes[0]  = getSymbolValue(0, teste.elementAt(i).getNome() );
			point.attributes[1]  = getSymbolValue(1, Double.toString(teste.elementAt(i).getPreco()) );
			point.attributes[2]  = getSymbolValue(2,Integer.toString(teste.elementAt(i).getQuantidade()));
			point.attributes[3]  = getSymbolValue(3,Integer.toString(teste.elementAt(i).getResultado()));
			root.data.addElement(point);
		}
		return 1;
	}




	public void printTree(TreeNode node, String tab) {

		int outputattr = numAttributes-1;

		
		
		
		
		
		if (node.children == null) {
			int []values = getAllValues(node.data, outputattr );
			if (values.length == 1) {
				System.out.println(tab + "\t" + attributeNames[outputattr] + " = \"" + domains[outputattr].elementAt(values[0]) + "\";");
				return;
			}
			System.out.print(tab + "\t" + attributeNames[outputattr] + " = {");
			for (int i=0; i < values.length; i++) {
				System.out.print("\"" + domains[outputattr].elementAt(values[i]) + "\" ");
				if ( i != values.length-1 ) System.out.print( " , " );
			}
			System.out.println( " };");
			return;
		}

		int numvalues = node.children.length;
		for (int i=0; i < numvalues; i++) {
			System.out.println(tab + "if( " + attributeNames[node.decompositionAttribute] + " == \"" +
					domains[node.decompositionAttribute].elementAt(i) + "\") {" );
			printTree(node.children[i], tab + "\t");
			if (i != numvalues-1) System.out.print(tab +  "} else ");
			else System.out.println(tab +  "}");
		}


	}
	 


	

	public Boolean ContextualFitness(TreeNode node, Encomenda enc) {

		int outputattr = numAttributes-1;

		
		
		
		
		
		if (node.children == null) {
			int []values = getAllValues(node.data, outputattr );
			if (values.length == 1) {
			if(attributeNames[outputattr].equals("Resultado") ){
				if( Integer.parseInt( domains[outputattr].elementAt(values[0]).toString())== 1){
					return true;}
				else return false;}
		}else{ return true;}}

		int numvalues = node.children.length;
		for (int i=0; i < numvalues; i++) {
			if(attributeNames[node.decompositionAttribute].equals("Quantidade") ){
				if(Integer.parseInt( domains[node.decompositionAttribute].elementAt(i).toString())- 5.0 <= enc.getQuantidade()&& enc.getQuantidade() < (Integer.parseInt(domains[node.decompositionAttribute].elementAt(i).toString())) + 5.0){
					Boolean res =ContextualFitness(node.children[i],enc);
					if(res==true)
						return true;
				}}
				
		    if(attributeNames[node.decompositionAttribute].equals("Preco") ){
		
		    	if(Double.parseDouble(domains[node.decompositionAttribute].elementAt(i).toString())- 5.0 <= enc.getPreco()&& enc.getPreco() < (Double.parseDouble( domains[node.decompositionAttribute].elementAt(i).toString())) + 5.0){
		    		Boolean res =ContextualFitness(node.children[i],enc);
				if(res==true)
					return true;}}
					
			if(attributeNames[node.decompositionAttribute].equals("Nome") ){
				if( (domains[node.decompositionAttribute].elementAt(i)).equals(enc.getNome())){
					Boolean res =ContextualFitness(node.children[i],enc);
						if(res==true)
							return true;
						
				}}
			if(attributeNames[node.decompositionAttribute].equals("Resultado") ){
				if((Integer) (domains[node.decompositionAttribute].elementAt(i))==1)
					return true;
				if((Integer) (domains[node.decompositionAttribute].elementAt(i))==2)
					return false;
				}
		

			
		}	
		return false;

	}
	 
	public boolean createDecisionTree(Encomenda enc) {
		
		decomposeNode(root);
		//printTree(root,"");
		return ContextualFitness(root, enc);
	}}
