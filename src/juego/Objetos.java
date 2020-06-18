package juego;
import javax.swing.*;

public class Objetos {
	//Atribustos
	
	protected int x;
	protected int y;
	private String Direccion;
	
	protected JPanel MiJP;
	
	//Constructor
	Objetos(JPanel MiJ){
		this.x=0;
		this.y=0;
		this.MiJP=MiJ;
	}	
	
	public int CoordX() {
		return this.x;
	}
	public int CoordY() {
		return this.y;
	}
	public String getDireccion() {
		return this.Direccion;
	}
	public void setDireccion(String Direccion) {
		this.Direccion=Direccion;
	}
	public void setCoordX(int x) {
		this.x=x;
	}
	public void setCoordY(int y) {
		this.y=y;
	}
	public JPanel getPanel() {
		return this.MiJP;
	}

}