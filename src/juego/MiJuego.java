package juego;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.File;

import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.LayoutStyle.ComponentPlacement;
import java.awt.Color;


/****************************************************/
//Librerias de tiempo

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.Timer;

/****************************************************/


public class MiJuego extends JFrame {

	private JPanel contentPane;
	
	//Creamos el objeto
	Link player;
	
	


	/**
	 * Create the frame.
	 */
	public MiJuego() {
		setTitle("Mini Juego");	
		
		//CONFIGURACION DE LA PANTALLA
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(135, 206, 250));
		contentPane.add(panel, BorderLayout.CENTER);
		
		//Personajes
		player = new Link(panel, 100, 0, 0);
		//Creacion del primer enemigo en la posicion x=80 y=130
		Broncas broncas= new Broncas(panel,80,130);
		
		JLabel lblNewLabel_3 = new JLabel("");
		lblNewLabel_3.setIcon(new ImageIcon(MiJuego.class.getResource("/juego/imagenes/fondo.png")));
		panel.add(lblNewLabel_3);
		
		JPanel panel_1 = new JPanel();
		contentPane.add(panel_1, BorderLayout.EAST);
		
		JLabel lblNewLabel = new JLabel("Salud: "+player.getSalud());
		
		JLabel lblNewLabel_1 = new JLabel("Escudo: "+player.getEscudo());
		
		JLabel lblNewLabel_2 = new JLabel("Arma: "+player.getArma());
		
		/*****************************************************************************************/
		
		//CODIGO QUE SE HA CREADO AUTOMÁTICAMENTE CON LOS LABELS
		GroupLayout gl_panel_1 = new GroupLayout(panel_1);
		gl_panel_1.setHorizontalGroup(
			gl_panel_1.createParallelGroup(Alignment.LEADING)
				.addGroup(Alignment.TRAILING, gl_panel_1.createSequentialGroup()
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
					.addComponent(lblNewLabel)
					.addContainerGap())
				.addGroup(gl_panel_1.createSequentialGroup()
					.addContainerGap()
					.addComponent(lblNewLabel_1)
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
				.addGroup(gl_panel_1.createSequentialGroup()
					.addContainerGap()
					.addComponent(lblNewLabel_2)
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
		);
		gl_panel_1.setVerticalGroup(
			gl_panel_1.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_1.createSequentialGroup()
					.addGap(4)
					.addComponent(lblNewLabel_2)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(lblNewLabel)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(lblNewLabel_1)
					.addContainerGap(183, Short.MAX_VALUE))
		);
		panel_1.setLayout(gl_panel_1);
		
		
		/************************************************************************************************/
		//Funcion al pulsar una tecla
		
		addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				//System.out.println("Se presiono una tecla");
				
				System.out.println("x-->"+player.CoordX()+", y-->"+player.CoordY());
				System.out.println(panel.getHeight());
				
				switch(e.getKeyCode()) {
				
				case KeyEvent.VK_LEFT:
				
					if (player.CoordX()>0) {
						player.setCoordX(player.CoordX()-10);
						player.setDireccion("src/juego/imagenes/izquierda.png");
					}
					break;
				case KeyEvent.VK_RIGHT:
					//Le restamos 200 a la condicion de movimiento para evitar que el personaje desaparezca debido a la anchura del escenario
					if(player.CoordX()<(panel.getWidth()-100)) {
						player.setCoordX(player.CoordX()+10);
						player.setDireccion("src/juego/imagenes/derecha.png");
					}
					
				case KeyEvent.VK_UP:
					if (player.CoordY()>0) {
						player.setCoordY(player.CoordY()-10);
						player.setDireccion("src/juego/imagenes/Arriba.png");
					}
					
				case KeyEvent.VK_DOWN:
					//Le restamos 200 a la condicion de movimiento para evitar que el personaje desaparezca debido a la anchura del escenario
					if(player.CoordY()<(panel.getWidth()-200)) {
						player.setCoordY(player.CoordY()+10);
						player.setDireccion("src/juego/imagenes/Abajo.png");
					}			
				}
				
				
			}
		});
		
		/***************************************************************/
		//Parte del codigo que cada 100ms mueve a los personajes enemigos
		
		Timer reloj=new Timer(100, new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				//System.out.println("Me ejecuto cada 100 milisegundos");
				
				//Actualizamos las posiciones de los personajes
				broncas.getPanel().update(panel.getGraphics());
				player.getPanel().update(panel.getGraphics());
				
				//Actualizamos al personaje player
				ImageIcon MiImagen= new ImageIcon(player.getDireccion());
				panel.getGraphics().drawImage(MiImagen.getImage(), player.CoordX(), player.CoordY(), panel);
				
				//Actualizamos al personaje Broncas
				broncas.movimientoBroncas();
				ImageIcon ImagenBroncas=new ImageIcon(broncas.getDireccion());
				panel.getGraphics().drawImage(ImagenBroncas.getImage(), broncas.CoordX(), broncas.CoordY(), panel);
				
				//Linea para evitar parpadeos
				panel.getGraphics().drawImage(null, 10, 80, null);
			}
			
		});
		
		reloj.start();
		
		/****************************************************************/
		
		
		
		
		
		
		
		
		

	}
}
