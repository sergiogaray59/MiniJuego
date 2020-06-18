package juego;
import javax.swing.*;

public class Escudo extends Objetos{
	
	private String Sentido;

	Escudo(JPanel MiJ, int x, int y){
		super(MiJ);
		this.x=x;
		this.y=y;
		this.Sentido="ARRIBA";
	}

	public void posicionEscudo() {
			this.Sentido="ARRIBA";
			this.setDireccion("src/juego/imagenes/escudo.png");
	}

	public String getSentido() {
		return Sentido;
	}

	public void setSentido(String sentido) {
		Sentido = sentido;
	}
	

}