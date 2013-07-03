
public class Opiniao {

	private int id;
	private Sinalpha sinalpha;
	private double confianca;
	private boolean activado;

	public Opiniao(int _id){

		this.id = _id;
		this.confianca = 0.5;
		this.sinalpha = new Sinalpha();
		this.activado = false;
	}

	public boolean isActivado() {
		return activado;
	}

	public void setActivado(boolean activado) {
		this.activado = activado;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Sinalpha getSinalpha() {
		return sinalpha;
	}

	public void setSinalpha(Sinalpha sinalpha) {
		this.sinalpha = sinalpha;
	}

	public double getConfianca() {
		return confianca;
	}

	public void setConfianca(double confianca) {
		this.confianca = confianca;
	}
	


	public void actualiza_confianca(int sucesso){

		this.confianca = sinalpha.calcula_confianca(sucesso);
		this.activado = true;
	}
}
