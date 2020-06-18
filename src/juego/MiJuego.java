package juego;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.File;
import java.util.Random;

import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
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
	Timer reloj;
	
	


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
		
		//Creacion de nuestro segundo enemigo en la posicion x=40 y=90
		Topo topo=new Topo(panel,100,300);
		
		//Creacion de nuestro fantasma
		Fantasma fantasma=new Fantasma(panel,50,100);	
		
		//Creacion del arma
		Arma arma=new Arma(panel, 300,400);
		
		//Creacion de la vida
		Vida vida=new Vida(panel, 530, 450);
		
		//Creacion del escudo
		Escudo escudo=new Escudo(panel, 250, 350);
		
		JLabel lblNewLabel_3 = new JLabel("");
		lblNewLabel_3.setIcon(new ImageIcon(MiJuego.class.getResource("/juego/imagenes/fondo.png")));
		panel.add(lblNewLabel_3);
		
		JPanel panel_1 = new JPanel();
		contentPane.add(panel_1, BorderLayout.EAST);
		
		JLabel sal = new JLabel("Salud: "+player.getSalud());
		
		JLabel esc = new JLabel("Escudo: "+player.getEscudo());
		
		JLabel arm = new JLabel("Arma: "+player.getArma());
		
		/*****************************************************************************************/
		
		//CODIGO QUE SE HA CREADO AUTOMÁTICAMENTE CON LOS LABELS
		GroupLayout gl_panel_1 = new GroupLayout(panel_1);
		gl_panel_1.setHorizontalGroup(
			gl_panel_1.createParallelGroup(Alignment.LEADING)
				.addGroup(Alignment.TRAILING, gl_panel_1.createSequentialGroup()
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
					.addComponent(sal)
					.addContainerGap())
				.addGroup(gl_panel_1.createSequentialGroup()
					.addContainerGap()
					.addComponent(esc)
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
				.addGroup(gl_panel_1.createSequentialGroup()
					.addContainerGap()
					.addComponent(arm)
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
		);
		gl_panel_1.setVerticalGroup(
			gl_panel_1.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_1.createSequentialGroup()
					.addGap(4)
					.addComponent(arm)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(sal)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(esc)
					.addContainerGap(183, Short.MAX_VALUE))
		);
		panel_1.setLayout(gl_panel_1);
		
		
		/************************************************************************************************/
		//Funcion al pulsar una tecla
		
		addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {

				System.out.println("x--->"+player.CoordX()+",y--->"+player.CoordY());
				System.out.println(panel.getHeight());
				System.out.println(panel.getWidth());

				switch(e.getKeyCode()) {
				case KeyEvent.VK_LEFT:
					if(player.CoordX()>0) {
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
					break;
				case KeyEvent.VK_UP:
					if(player.CoordY()>0) {
						player.setCoordY(player.CoordY()-10);
						player.setDireccion("src/juego/imagenes/arriba.png");
					}
					break;
				case KeyEvent.VK_DOWN:
					//Le restamos 200 a la condicion de movimiento para evitar que el personaje desaparezca debido a la anchura del escenario
					if(player.CoordY()<(panel.getWidth()-200)) {
						player.setCoordY(player.CoordY()+10);
						player.setDireccion("src/juego/imagenes/abajo.png");
					}
					break;
				}
			}
		});
		
		/***************************************************************/
		// Parte del codigo que cada 100 milisegundos movera a los enemigos

		reloj = new Timer(100, new ActionListener() {
			int contTiempo = 0;

			public void actionPerformed(ActionEvent e) {
				contTiempo = (contTiempo + reloj.getDelay()) % 5000;

				// Actualizamos las posiciones de los personajes
				broncas.getPanel().update(panel.getGraphics());
				;
				topo.getPanel().update(panel.getGraphics());
				arma.getPanel().update(panel.getGraphics());
				player.getPanel().update(panel.getGraphics());
				;

				// Actualizamos al personaje player
				ImageIcon MiImagen = new ImageIcon(player.getDireccion());
				panel.getGraphics().drawImage(MiImagen.getImage(), player.CoordX(), player.CoordY(), panel);

				// Actualizamos al personaje Broncas
				broncas.movimientoBroncas();
				ImageIcon ImagenBroncas = new ImageIcon(broncas.getDireccion());
				panel.getGraphics().drawImage(ImagenBroncas.getImage(), broncas.CoordX(), broncas.CoordY(), panel);

				// Actualizamos al personaje Topo
				topo.movimientoTopo();
				ImageIcon ImagenTopo = new ImageIcon(topo.getDireccion());
				panel.getGraphics().drawImage(ImagenTopo.getImage(), topo.CoordX(), topo.CoordY(), panel);

				// Actualizamos al personaje Fantasma
				if (contTiempo == 0) {
					fantasma.setVisible();
					Random r = new Random();
					int valorX = r.nextInt(650);
					int valorY = r.nextInt(460);
					fantasma.setCoordX(valorX);
					fantasma.setCoordY(valorY);
					// System.out.println("Cambio");
				}

				// Actualizamos el arma
				arma.posicionArma();
				ImageIcon ImagenArma = new ImageIcon(arma.getDireccion());
				panel.getGraphics().drawImage(ImagenArma.getImage(), arma.CoordX(), arma.CoordY(), panel);

				// Actualizamos el escudo
				escudo.posicionEscudo();
				ImageIcon ImagenEscudo = new ImageIcon(escudo.getDireccion());
				panel.getGraphics().drawImage(ImagenEscudo.getImage(), escudo.CoordX(), escudo.CoordY(), panel);

				// Actualizamos la vida
				vida.posicionVida();
				ImageIcon ImagenVida = new ImageIcon(vida.getDireccion());
				panel.getGraphics().drawImage(ImagenVida.getImage(), vida.CoordX(), vida.CoordY(), panel);

				// Linea para evitar parpadeos
				panel.getGraphics().drawImage(null, 10, 80, null);

				// Colision con los personajes
				// Con broncas
				int hiddenBox = 70; // Variable creada para regular el tamaño de la caja oculta
				if (player.CoordX() >= broncas.CoordX() - hiddenBox && player.CoordX() <= broncas.CoordX() + hiddenBox
						&& ((player.CoordY() >= broncas.CoordY() - hiddenBox
								&& player.CoordY() <= broncas.CoordY() + hiddenBox))) {
					System.out.println("¡Player está tocando  a broncas!");

					if (player.getArma() == 1) {
						broncas.setCoordX(800);
						broncas.setCoordY(800);
					} else if (player.getEscudo() > 0) {
						player.setEscudo(player.getEscudo() - 1);
						esc.setText("Escudo: " + player.getEscudo());
					} else if (player.getEscudo() <= 0) {
						player.setSalud(player.getSalud() - 1);
						sal.setText("Salud: " + player.getSalud());
					}

				}

				// Con topo
				if (player.CoordX() >= topo.CoordX() - hiddenBox && player.CoordX() <= topo.CoordX() + hiddenBox
						&& ((player.CoordY() >= topo.CoordY() - hiddenBox
								&& player.CoordY() <= topo.CoordY() + hiddenBox))) {
					System.out.println("¡Player está tocando  a topo!");

					if (player.getArma() == 1) {
						topo.setCoordX(800);
						topo.setCoordY(800);
					} else if (player.getEscudo() > 0) {
						player.setEscudo(player.getEscudo() - 1);
						esc.setText("Escudo: " + player.getEscudo());
					} else if (player.getEscudo() <= 0) {
						player.setSalud(player.getSalud() - 1);
						sal.setText("Salud: " + player.getSalud());
					}

				}

				// Con fantasma
				if (fantasma.getVisible() == 1) {
					fantasma.movimientoFantasma();
					ImageIcon ImagenFantasma = new ImageIcon(fantasma.getDireccion());
					panel.getGraphics().drawImage(ImagenFantasma.getImage(), fantasma.CoordX(), fantasma.CoordY(),
							panel);

					if (player.CoordX() >= fantasma.CoordX() - hiddenBox
							&& player.CoordX() <= fantasma.CoordX() + hiddenBox
							&& ((player.CoordY() >= fantasma.CoordY() - hiddenBox
									&& player.CoordY() <= fantasma.CoordY() + hiddenBox))) {
						System.out.println("¡Player está tocando  al fantasma!");

						if (player.getArma() == 1) {
							fantasma.setCoordX(800);
							fantasma.setCoordY(800);
							contTiempo = 1111111111;
						} else if (player.getEscudo() > 0) {
							player.setEscudo(player.getEscudo() - 1);
							esc.setText("Escudo: " + player.getEscudo());
						} else if (player.getEscudo() <= 0) {
							player.setSalud(player.getSalud() - 1);
							sal.setText("Salud: " + player.getSalud());
						}

					}
				}

				// Con salud
				if (player.CoordX() >= vida.CoordX() - hiddenBox && player.CoordX() <= vida.CoordX() + hiddenBox
						&& ((player.CoordY() >= vida.CoordY() - hiddenBox
								&& player.CoordY() <= vida.CoordY() + hiddenBox))) {
					System.out.println("¡Player está tocando  la vida!");
					vida.setCoordX(800);
					vida.setCoordY(800);
					player.setSalud(player.getSalud() + 50);
					sal.setText("Salud: " + player.getSalud());
				}

				// Con escudo
				if (player.CoordX() >= escudo.CoordX() - hiddenBox && player.CoordX() <= escudo.CoordX() + hiddenBox
						&& ((player.CoordY() >= escudo.CoordY() - hiddenBox
								&& player.CoordY() <= escudo.CoordY() + hiddenBox))) {
					System.out.println("¡Player está tocando  el escudo!");
					escudo.setCoordX(800);
					escudo.setCoordY(800);
					player.setEscudo(player.getEscudo() + 50);
					esc.setText("Escudo: " + player.getEscudo());
				}

				// Con arma
				if (player.CoordX() >= arma.CoordX() - hiddenBox && player.CoordX() <= arma.CoordX() + hiddenBox
						&& ((player.CoordY() >= arma.CoordY() - hiddenBox
								&& player.CoordY() <= arma.CoordY() + hiddenBox))) {
					System.out.println("¡Player está tocando el arma!");
					arma.setCoordX(800);
					arma.setCoordY(800);
					player.setArma(player.getArma() + 1);
					arm.setText("Arma: " + player.getArma());
				}

				if (player.getSalud() <= 0) {
					JOptionPane.showMessageDialog(null, "GAME OVER", "Has perdido toda la vida", JOptionPane.ERROR_MESSAGE);
					System.exit(0);
				}

			}
		});
		reloj.start();
		
		/****************************************************************/
		
		
		
		
		
		
		
		
		
		
		

	}
}
