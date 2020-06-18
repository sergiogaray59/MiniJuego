package juego;
import javax.swing.*;

public class Topo extends Personaje{
	
	private String Sentido;
	
	Topo(JPanel MiJ, int x, int y){
		super(MiJ);		//Primero llamamos al constructor padre
		this.x=x;
		this.y=y;
		this.Sentido="ARRIBA";
	}
		
		public void movimientoTopo() {
			if(this.y<0) {
				this.Sentido="ARRIBA";
				this.y=this.y+10;
				this.setDireccion("src/juego/imagenes/Topo.png");
			}
			
			//Le restamos 200 a la condicion de movimiento para evitar que el personaje desaparezca debido a la altura del escenario.
			else if(this.y>this.getPanel().getWidth()-190) {
				this.Sentido="ABAJO";
				this.y=this.y-10;
				this.setDireccion("src/juego/imagenes/topo.png");
			}
			else if(this.Sentido.equals("ARRIBA")) {
				this.y=this.y+10;
				this.setDireccion("src/juego/imagenes/Topo.png");
			}
			else if(this.Sentido.equals("ABAJO")) {
				this.y=this.y-10;
				this.setDireccion("src/juego/imagenes/Topo.png");
			}
		}

}
