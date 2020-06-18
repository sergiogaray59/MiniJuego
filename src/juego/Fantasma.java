package juego;

import javax.swing.*;

public class Fantasma extends Personaje{

	private String sentido;
	//Simularemos una variable booleana donde 0 será no visible y 1 visible
	private int visible;	

	Fantasma(JPanel MiJ, int x, int y){
		super(MiJ);
		this.x=x;
		this.y=y;
		this.sentido="ESTE";
		visible=0;
		this.setDireccion("src/juego/imagenes/fantasma.png");
	}

	public void movimientoFantasma() {
		this.setDireccion("src/juego/imagenes/fantasma.png");
	}
	public String getSentido() {
		return sentido;
	}

	public void setSentido(String sentido) {
		this.sentido = sentido;
	} 

	public int getVisible() {
		return visible;
	}

	public void setVisible() {
		if (visible==0) {
			visible=1;
		}
		else {
			visible=0;
		}
	} 


}