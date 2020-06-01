package juego;

import java.awt.Graphics;
import javax.swing.ImageIcon;
import javax.swing.JPanel;

public class Personaje {

	//Atributos de la clase Personaje
	protected int x;
	protected int y;
	private String Direccion;
	private JPanel MiJP;
	
	//Constructores de la clase Personaje
	
	Personaje(JPanel MiJ){
		this.x=0;
		this.y=0;
		this.MiJP=MiJ;
	}
	


	//GetX
	public int CoordX() {
		return x;
	}
	

	public void setCoordX(int x) {
		this.x = x;
	}

	//GetY
	public int CoordY() {
		return y;
	}
	
	public void setCoordY(int y) {
		this.y = y;
	}


	//GetPanel
	
	public JPanel getPanel() {
		return MiJP;
	}


	//Getters y Setters  Direccion
	
	public String getDireccion() {
		return Direccion;
	}

	public void setDireccion(String direccion) {
		Direccion = direccion;
	}


	
	
	
}
