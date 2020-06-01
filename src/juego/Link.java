package juego;

import javax.swing.JPanel;

public class Link extends Personaje {
	
	//Atributos de Link
	private int salud;
	private int escudo;
	private int arma;
	
	//Constructor de Link
	Link(JPanel MiJ, int s, int e, int a){
		super(MiJ);
		this.salud=s;
		this.escudo=e;
		this.arma=a;
	}

	
	//Getters y setters
	
	public int getSalud() {
		return salud;
	}

	public void setSalud(int salud) {
		this.salud = salud;
	}

	public int getEscudo() {
		return escudo;
	}

	public void setEscudo(int escudo) {
		this.escudo = escudo;
	}

	public int getArma() {
		return arma;
	}

	public void setArma(int arma) {
		this.arma = arma;
	}
	

	
	
}
