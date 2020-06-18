package juego;
import javax.swing.*;

public class Vida extends Objetos{
	
	private String Sentido;

	Vida(JPanel MiJ, int x, int y){
		super(MiJ);
		this.x=x;
		this.y=y;
	}

	public void posicionVida() {
			this.Sentido="ARRIBA";
			this.setDireccion("src/juego/imagenes/vida.png");
	}

	public String getSentido() {
		return Sentido;
	}

	public void setSentido(String sentido) {
		Sentido = sentido;
	}
	

}