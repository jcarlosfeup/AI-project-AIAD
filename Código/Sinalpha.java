
public class Sinalpha {

	/*
	 * variável que se pode situar entre 3*pi/2 e 5*pi/2,
	 */

	private double alpha_ant;

	
	
	public Sinalpha(){
		
		this.alpha_ant = (( 3 * (Math.PI)) /2);
	}
	
	
	/*
	 * se sucesso = 0, accao positiva
	 * se sucesso = 1, accao negativa  
	 */
	public double calcula_confianca(int sucesso){

		double alpha = 0.0;

		if(sucesso == 0){
			alpha = this.alpha_ant + (1 * (Math.PI/10));
		}
		else if(sucesso == 1){
			alpha = this.alpha_ant + ((-1.5) * (Math.PI/10));
		}
		else{
			System.out.println("Erro! Valor do lambda errado");
			System.exit(0);
		}
		if(alpha < (( 3 * (Math.PI)) /2))
			alpha=(( 3 * (Math.PI)) /2);
		
		if(alpha > (( 5 * (Math.PI)) /2))
			alpha=(( 5 * (Math.PI)) /2);
		
		double confianca = 0.0;
		double omega = 0.5;

		confianca = omega * Math.sin(alpha) + omega;
		alpha_ant = alpha;


		return confianca;
	}
}
