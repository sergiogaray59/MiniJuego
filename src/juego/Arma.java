package juego;
import javax.swing.*;

public class Arma extends Objetos{

	private String Sentido;

	Arma(JPanel MiJ, int x, int y){
		super(MiJ);
		this.x=x;
		this.y=y;
		this.Sentido="ARRIBA";
	}

	public void posicionArma() {
			this.Sentido="ARRIBA";
			this.setDireccion("src/juego/imagenes/arma.png");
	}
	
	public void desaparecer() {
		this.Sentido="ARRIBA";
		this.setDireccion("src/juego/imagenes/Escudo.png");
	}

	public String getSentido() {
		return Sentido;
	}

	public void setSentido(String sentido) {
		Sentido = sentido;
	}
	
	



}