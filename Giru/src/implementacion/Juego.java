package implementacion;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import javax.swing.JOptionPane;

import clases.EnemigoAnimado;
import clases.EnemigoAnimado1;
import clases.EnemigoAnimado2;
import clases.Item;
import clases.ItemAnimado1;
import clases.Jugador;
import clases.Player;
import clases.Suelo;
import clases.Tile;
import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.input.KeyEvent;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class Juego extends Application{
	 //VENTANA
	private GraphicsContext graficos; 
	private Group root;
	private Scene escena;
	private Canvas lienzo;
	private Jugador jugador;
	private EnemigoAnimado enemigo; 
	private static int puntuacion = 0;
	  //PARA LOS MOVIMIENTOS
	public static boolean arriba;
	public static boolean abajo;
	public static boolean izquierda;
	public static boolean derecha;
	public static boolean espacio;
//	public boolean Verificador = true;
	
	long inicioPartida = System.nanoTime();
	public int nivel = 1;
	private Tile tile;
	public int itemsColectados = 0;
	
	int randomx,randomy,randomyy;   //ALEATORIA MOVIMIENTOS PARA LOS ENEMIGOS
	int randomxV,randomyV,randomyyV;
	
//    ARREGLOS PARA TRABAJOS MI SUPERFICIE DE JUEGO Y PERSONAJES E ITEMS
	public static HashMap<String,Image> imagenes;
	private ArrayList<Tile> tiles;
	private ArrayList<Suelo> suelos;
	private ArrayList<Item> items;
	private ArrayList<ItemAnimado1> coins;
	private ArrayList<EnemigoAnimado1> enemigos1;
	private ArrayList<EnemigoAnimado2> enemigos2;
	private static ArrayList<Player> jugadores;
		


	private int[][] itemsMap = {
			{0,0,0,0,0,0,1,0,0,0,0,0,0,0,2,0},//SON LOS ITEMS DONDE APARECE
			{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
			{0,0,0,2,0,0,0,0,0,0,0,0,0,0,0,0},
			{0,0,0,0,0,0,1,0,0,0,0,0,0,0,2,0},
			{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},  //1NIVEL
			{0,0,0,1,0,0,0,0,0,0,0,0,0,0,0,0},
			{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
			{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
			{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
        	};
	
	
	private int[][] tilemap = {
			{5,5,5,5,5,5,0,5,5,5,5,3,3,3,0,5},
			{4,0,6,0,0,0,0,0,0,0,0,0,0,0,0,4},
			{4,0,4,0,0,0,4,0,0,0,29,29,29,29,0,4},
			{4,0,4,4,4,4,0,4,4,0,0,10,0,19,0,4},
			{4,0,0,0,4,0,0,0,4,4,0,0,29,29,29,4},  //SON LOS OBTACULOS
			{4,0,0,0,4,7,0,0,0,0,0,0,0,0,0,4},   //1NIVEL
			{4,0,29,29,29,29,29,0,0,0,0,0,0,0,0,2},
			{4,0,0,0,0,0,0,0,4,4,4,4,4,0,0,2},
			{4,4,4,4,4,4,4,4,4,0,0,0,4,4,4,4}, 
        	};
	
	
	private int[][] tilemapCompletado = {
			{5,5,5,5,5,5,0,5,5,5,5,3,3,3,0,5},
			{4,0,6,0,0,0,0,0,0,0,0,0,0,0,0,4},
			{4,0,4,0,0,0,4,0,0,0,29,29,29,29,0,4},
			{4,0,4,4,4,4,0,4,4,0,0,10,0,19,0,4},
			{4,0,0,0,4,0,0,0,4,4,0,0,29,29,29,4},  //SON LOS OBTACULOS
			{4,0,0,0,4,7,0,0,0,0,0,0,0,0,0,4},   //1NIVEL  AL TENER LOS ITEMS
			{4,0,29,29,29,29,29,0,0,0,0,0,0,0,0,0},
			{4,0,0,0,0,0,0,0,27,27,27,27,27,0,0,0},
			{4,4,4,4,4,4,4,4,27,0,0,0,27,27,27,27}, 
        	};
	
			
			
	private int[][] items2Map = {
			{0,0,0,0,0,0,0,0,0,0,0,0,0,3},
			{0,0,0,0,0,0,0,0,3,0,0,0,0,0},
			{0,0,0,0,0,0,0,0,0,0,0,0,0,0},
			{0,0,0,0,0,0,0,0,0,0,0,0,0,0},
			{0,0,0,0,0,0,0,0,0,0,0,0,0,0},
			{0,0,0,0,0,0,0,0,0,0,0,0,0,0}, //2NIVEL ITEMS
			{0,0,0,0,0,0,0,0,0,0,0,0,3,0},
			{0,3,0,0,0,0,0,0,0,3,0,0,0,0},
			{0,0,0,0,0,0,0,0,0,0,0,0,0,0},
			};

	private int[][] tile2map = {
			{30,30,30,30,30,30,30,30,30,30,30,30,16,0,16,30},
			{2,0,0,0,6,0,0,23,0,0,0,0,0,0,10,2},
			{2,0,0,0,0,0,0,0,23,0,0,0,0,0,0,2},// 2NIVEL OBSTACULOS
			{23,23,23,23,23,23,0,0,0,0,0,0,0,0,0,23},
			{23,0,0,0,0,0,0,0,0,0,0,0,0,0,0,23},
			{23,0,0,0,0,0,0,23,0,0,23,0,0,0,0,23},
			{23,0,23,0,7,0,0,23,23,0,0,0,0,19,0,23},
			{23,20,23,0,0,0,0,23,0,0,23,0,0,23,0,23},
			{0,23,0,23,23,23,23,0,23,23,23,23,23,23,23,23},			
	        };
	
	
	private int[][] tile2mapCompletado = {
			{30,30,30,30,30,30,30,30,30,30,30,30,16,0,16,30},
			{2,0,0,0,6,0,0,23,0,0,0,0,0,0,0,0},
			{2,0,0,0,0,0,0,0,23,0,0,0,0,0,0,0},// 2NIVEL OBSTACULOS AL TENER LOS ITEMS
			{23,23,23,23,23,23,0,0,0,0,0,0,0,0,0,23},
			{23,0,0,0,0,0,0,0,0,0,0,0,0,0,0,23},
			{23,0,0,0,0,0,0,23,0,0,23,0,0,0,0,23},
			{23,0,23,0,7,0,0,23,23,0,0,0,0,19,0,23},
			{23,20,23,0,0,0,0,23,0,0,23,0,0,23,0,23},
			{0,23,0,23,23,23,23,0,23,23,23,23,23,23,23,23},			
	        };
			
		
	
	private int[][] items3Map = {
			{0,0,0,0,0,0,0,0,0,0,0,0,0,0},
			{0,0,0,0,0,0,0,0,4,0,0,0,0,0},
			{0,0,0,0,0,0,0,0,0,5,0,0,0,0},
			{0,0,0,0,0,0,0,0,0,0,0,0,0,0},
			{0,0,0,0,0,0,0,0,0,0,0,0,0,0},
			{0,0,0,0,0,0,0,0,0,0,0,0,0,0}, //3NIVEL ITEM
			{0,0,0,0,0,0,0,0,0,0,0,0,0,0},
			{0,5,0,0,0,0,4,0,0,0,5,0,4,0},
			{0,0,0,0,0,0,0,0,0,0,0,0,0,0},
        	};

	private int[][] tile3map = {
			{28,28,28,28,28,28,28,28,28,28,28,28,28,28,28,28},
			{2,0,0,0,6,0,0,0,0,27,0,0,0,0,0,2},
			{2,0,0,0,27,0,0,0,27,0,27,0,0,0,0,2},
			{27,0,27,27,27,27,0,0,0,0,0,18,0,0,0,27},
			{27,0,27,0,0,0,0,0,0,0,0,0,0,0,0,27},  //3NIVEL OBSTACULOS
			{27,0,27,0,0,0,0,27,0,0,0,0,0,0,0,27},
			{27,0,0,0,0,27,0,27,0,0,27,27,0,7,0,27},
			{27,0,0,0,27,0,0,27,0,0,0,27,0,0,0,27},
			{27,27,27,27,0,27,27,27,27,27,27,27,27,27,27,27},
            };
	
	
	private int[][] tile3mapCompletado = {
			{28,28,28,28,28,28,28,28,28,28,28,28,28,28,28,28},
			{2,0,0,0,6,0,0,0,0,27,0,0,0,0,0,0},
			{2,0,0,0,27,0,0,0,27,0,27,0,0,0,0,0},
			{27,0,27,27,27,27,0,0,0,0,0,18,0,0,0,27},
			{27,0,27,0,0,0,0,0,0,0,0,0,0,0,0,27},  //3NIVEL OBSTACULOS ACTUALIZADOS AL YA TENER LOS ITEMS
			{27,0,27,0,0,0,0,27,0,0,0,0,0,0,0,27},
			{27,0,0,0,0,27,0,27,0,0,27,27,0,7,0,27},
			{27,0,0,0,27,0,0,27,0,0,0,27,0,0,0,27},
			{27,27,27,27,0,27,27,27,27,27,27,27,27,27,27,27},
            };
			
			
	
	private int[][] items4Map = {
			{0,0,0,0,0,0,0,0,0,0,0,0,0,0},
			{0,0,0,0,6,0,0,0,0,0,0,0,0,0},
			{0,0,0,0,0,0,0,0,0,0,0,0,0,0},
			{0,0,0,0,0,0,6,0,0,0,6,0,0,0},
			{0,0,0,0,0,0,0,0,0,0,0,0,0,0},   //4NIVEL ITEMS
			{0,0,0,0,0,0,0,0,0,0,0,0,0,0},
			{0,0,6,0,0,0,0,0,0,0,0,0,0,0,0,0},
			{0,0,0,0,0,0,0,6,0,0,0,0,0,0},
        	};

	
	private int[][] tile4map = {
			{5,5,5,5,5,5,5,5,5,5,5,0,17,17,17,5},
			{2,0,6,4,0,4,0,0,0,0,0,0,0,0,0,2},
			{2,0,4,0,0,4,0,0,0,0,0,4,0,0,0,2},
			{4,0,4,0,0,4,0,4,4,4,0,4,0,0,0,4}, //4 NIVEL TILE 
			{4,0,4,0,29,29,29,29,29,29,29,29,29,0,0,4},
			{4,0,0,0,0,0,0,0,0,0,0,0,0,0,0,4},
			{4,0,0,0,4,0,0,0,0,4,0,0,0,0,0,4},
			{4,0,4,4,4,4,0,0,0,4,0,0,0,0,0,4},
			{4,4,0,0,0,0,4,4,4,0,4,4,4,4,4,4},
			};
	
	
	private int[][] tile4mapCompletado = {
			{5,5,5,5,5,5,5,5,5,5,5,0,17,17,17,5},
			{2,0,6,4,0,4,0,0,0,0,0,0,0,0,0,0},
			{2,0,4,0,0,4,0,0,0,0,0,4,0,0,0,0},
			{4,0,4,0,0,4,0,4,4,4,0,4,0,0,0,4}, //4 NIVEL TILE ACTUALIZADOS AL YA TENER LOS ITEMS
			{4,0,4,0,29,29,29,29,29,29,29,29,29,0,0,4},
			{4,0,0,0,0,0,0,0,0,0,0,0,0,0,0,4},
			{4,0,0,0,4,0,0,0,0,4,0,0,0,0,0,4},
			{4,0,4,4,4,4,0,0,0,4,0,0,0,0,0,4},
			{4,4,0,0,0,0,4,4,4,0,4,4,4,4,4,4},
			};
			
			
			
	
	private int[][] items5Map = {

			{0,0,0,0,0,0,0,0,0,0,0,0,0,0},
			{0,0,0,0,0,0,7,0,0,0,7,0,0,0,7,0},
			{0,0,0,0,0,0,0,0,0,0,0,0,0 ,0},   // NIVEL 5 ITEMS
			{0,0,0,0,7,0,0,0,0,0,0,0,0,0},
			{0,0,0,0,0,0,0,0,0,0,0,0,0,0},
			{0,0,0,0,0,0,0,0,0,0,0,0,0,0},
			{0,0,0,0,0,0,0,0,0,0,0,0,0,0},
			{0,0,0,0,0,0,0,0,0,0,0,7,0,0},
			{0,0,0,0,0,0,0,0,0,0,0,0,0,0},
			};

	private int[][] tile5mapA = {
			{37,37,37,37,37,37,37,37,37,37,37,37,37,37,37,37},
			{2,0,6,0,13,13,0,13,13,0,0,0,13,0,0,13},
			{2,0,0,13,0,0,0,0,13,0,0,36,0,36,0,2},
			{13,0,0,13,0,0,0,0,13,0,0,0,0,0,0,2},  //NIVEL 5 TILES OBSTACULOS
			{13,0,36,36,36,36,0,36,36,36,36,36,0,36,36,13},
			{13,0,0,0,0,0,0,0,0,0,0,0,0,0,0,13},
			{13,0,0,0,0,0,0,0,0,0,13,0,0,0,0,13},
			{13,0,0,13,13,13,0,0,0,13,0,0,0,0,0,13},
			{13,13,13,0,0,0,13,13,13,13,13,13,13,13,13,13},
        	};
	
	
	private int[][] tile5mapCompletado = {
			{37,37,37,37,37,37,37,37,37,37,37,37,37,37,37,37},
			{2,0,6,0,13,13,0,13,13,0,0,0,13,0,0,13},
			{2,0,0,13,0,0,0,0,13,0,0,36,0,36,0,0},
			{13,0,0,13,0,0,0,0,13,0,0,0,0,0,0,0},  //NIVEL 5 TILES OBSTACULOS ACTUALIZADOS AL YA TENER LOS ITEMS
			{13,0,36,36,36,36,0,36,36,36,36,36,0,36,36,13},
			{13,0,0,0,0,0,0,0,0,0,0,0,0,0,0,13},
			{13,0,0,0,0,0,0,0,0,0,13,0,0,0,0,13},
			{13,0,0,13,13,13,0,0,0,13,0,0,0,0,0,13},
			{13,13,13,0,0,0,13,13,13,13,13,13,13,13,13,13},
        	};

	
	private int[][] tilemapFinal = {
			{0,0,0,6,13,0,0,11,0,0,0,0,13,0},
			{0,0,0,0,13,0,0,0,0,0,0,0,13,0},
			{0,0,0,0,13,0,13,13,0,13,13,0,13,0},
			{13,0,0,0,13,13,13,13,0,13,13,13,13,0},
			{13,0,0,0,0,13,13,13,0,13,13,13,0,0},
			{13,0,0,0,0,0,0,0,0,0,0,0,0,0,14},
			{13,0,0,0,0,0,0,0,0,0,0,0,0,0},
			{13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13},
	        };
	
	
	private int[][] suelo = {
			{1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1},
	    	{1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1},
			{1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1},
			{1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1},
			{1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1},
			{1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1},
			{1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1},
			{1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1},
			{2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2},
			};

	
	
	public static void main(String[] args) {
		launch(args);
	}

	
	public void start(Stage ventana) {
		inicializarComponentes();
		crearObjetosJuego();
		registrarEventos();
		ventana.setScene(escena);
		ventana.setTitle("GIRU");
		ventana.show();
		cicloPrincipal();	
	}
	
	
	
                  //CICLO DE JUEGO
	public void cicloPrincipal() {
		long tiempoInicial = System.nanoTime();
		AnimationTimer animationTimer = new AnimationTimer() {
             
			//Este metodo se ejecuta aproximadamente 60 veces por segundo 60FPS
			@Override
			public void handle(long tiempoActualNanoSegundos) {
				double t = (tiempoActualNanoSegundos - tiempoInicial) / 1000000000.0;
				//System.out.println(t); 
				actualizar(t);
				pintar();
			}
		};
		animationTimer.start();//Empieza el ciclo de juego
	}
	
	
	
	//INICIALIZA MIS COMPONENTES PRINCIPALES
		public void inicializarComponentes() {
			cargarImagen();
			root = new Group();
			escena = new Scene(root,1280,690);
			lienzo = new Canvas(1280,690);
			root.getChildren().add(lienzo);
			graficos = lienzo.getGraphicsContext2D();
			jugadores=new ArrayList<Player>();
			
			// INICIALIZA MIS COINS ANIMADOS
			coins = new ArrayList<ItemAnimado1>();
			int m=((int)(Math.random()*16+25));
			for (int z=0;z<m;z++) {
				randomyy=(int)(Math.random()*3+1);
				randomx=(int)(Math.random()*15000+1000);
				if (randomyy==1)
					randomy=75;
				if(randomyy==2)
					randomy=280;
				if(randomyy==3)
					randomy=490;
				coins.add(new ItemAnimado1(randomx,randomy,0,0,"coin",4,"mover", false));
			}
			
			enemigos1=new ArrayList<EnemigoAnimado1>();
			for (int z=0;z<30;z++) {
				randomyyV=(int)(Math.random()*3+1);
				randomxV=(int)(Math.random()*26000+3000);
				if (randomyyV==1)
					randomyV=67;
				if (randomyyV==2)
					randomyV=284;
				if (randomyyV==3)
					randomyV=494;
				enemigos1.add(new EnemigoAnimado1(randomxV,randomyV,0,0,"enemigo1",4,"mover", false));
			}
			
			
			enemigos2=new ArrayList<EnemigoAnimado2>();
			for (int z=0;z<30;z++) {
				randomyyV=(int)(Math.random()*3+1);
				randomxV=(int)(Math.random()*26000+3000);
				if (randomyyV==1)
					randomyV=64;
				if (randomyyV==2)
					randomyV=284;
				if (randomyyV==3)
					randomyV=494;
				enemigos2.add(new EnemigoAnimado2(randomxV,randomyV,0,0,"enemigo2",4,"mover", false));
			}
			
			
			
			
		}

		
		// EL METODO QUE PINTA
	public void pintar () {
		graficos.fillRect(0, 0, 1280, 490);

    	for (int i=0; i<suelos.size(); i++) 
			suelos.get(i).pintar(graficos);

		for (int i=0; i<tiles.size(); i++)
			tiles.get(i).pintar(graficos);

		for (int i=0; i<items.size(); i++)
			items.get(i).pintar(graficos);
		
		for (int i=0;i<coins.size();i++)
			coins.get(i).pintar(graficos);
		
		for (int i=0;i<enemigos1.size();i++)
			enemigos1.get(i).pintar(graficos);
		
		for (int i=0;i<enemigos2.size();i++)
			enemigos2.get(i).pintar(graficos);

		graficos.setFont(new Font(18));
		puntuacion();
		jugador.pintar(graficos);
		enemigo.pintar(graficos);
		
	}
	
	public void puntuacion() {
		graficos.fillText(String.valueOf("Esferas: " + jugador.getPuntuacion()+"/7"), 217d, 112d);
	}

	
	
     //ESTE METODO SIRVE PARA ACTUALIZAR TILES E ITEMS
	public void actualizar(double time) {

		jugador.actualizarAnimacion(time);
		enemigo.actualizarAnimacion(time);
		jugador.mover();
		enemigo.mover();
		System.out.println(nivel);
		itemsColectados = jugador.getPuntuacion();
		System.out.println("Items colectados"+itemsColectados);
	
		if (itemsColectados==4) {
			actualizarTiles();
		}
		if (jugador.getX()>=1280) {
			nivel=nivel+1;
			crearObjetosJuego();
		}
		for (int i=0; i<items.size();i++) {
			if (jugador.verificarColision(items.get(i)))
				items.remove(items.get(i));
		}                                                       //ESTO HACE LA COALISION
		for (int i=0; i<tiles.size();i++) {
		 	if (jugador.verificarColision(tiles.get(i))) {
				if(jugador.getX() == tile.getAnchoImagen())
			tiles.remove(tiles.get(i));
		}
		}
		
		for(int z=0;z<coins.size();z++) {
			coins.get(z).mover();
			coins.get(z).actualizarAnimacion(time);
		}
		
		for (int i=0;i<coins.size();i++)
			jugador.verificarColision(coins.get(i));
		
	
		for(int z=0;z<enemigos1.size();z++) {
			enemigos1.get(z).mover();
			enemigos1.get(z).actualizarAnimacion(time);
		}
		
		for (int i=0;i<enemigos1.size();i++)
			jugador.verificarColision(enemigos1.get(i));
		
		
		for(int z=0;z<enemigos2.size();z++) {
			enemigos2.get(z).mover();
			enemigos2.get(z).actualizarAnimacion(time);
		}
		
		for (int i=0;i<enemigos2.size();i++)
			jugador.verificarColision(enemigos2.get(i));
	}
     
	
	
	//GESTION DE EVENTOS SE USA PARA LOS MOVIMIENTOS
	public void registrarEventos() {
		//escena.setOnKeyPressed(new Evento());
		escena.setOnKeyPressed(new EventHandler<KeyEvent>() {
 
			//El metodo handle se ejecuta cada vez que presiono una tecla.
			@Override
			public void handle(KeyEvent event) {
				switch (event.getCode().toString()) {
				case "RIGHT":
					derecha = true;
					break;
				case "LEFT":
					izquierda = true;
					break;
				case "UP":
					arriba = true;
					break;
				case "DOWN":
					abajo = true;
					break;
				case "SPACE":
					espacio = true;
					ItemAnimado1.setVelocidad(8);
					EnemigoAnimado1.setVelocidad(8);
					EnemigoAnimado2.setVelocidad(8);
					break;
				}
			}
		});

		escena.setOnKeyReleased(new EventHandler<KeyEvent>() {
            
			//Este metodo se ejecuta cuando se suelta una tecla
			@Override
			public void handle(KeyEvent event) {
				switch (event.getCode().toString()) {
				case "RIGHT":
					derecha = false;
					break;
				case "LEFT":
					izquierda = false;
					break;
				case "UP":
					arriba = false;
					break;
				case "DOWN":
					abajo = false;
					break;
				case "SPACE":
					espacio = false;
					ItemAnimado1.setVelocidad(4);
					EnemigoAnimado1.setVelocidad(4);
					EnemigoAnimado2.setVelocidad(4);
					break;
				}
			}
		});
	}

	
	
	public void cargarImagen() {
		imagenes = new HashMap<String,Image>();
		imagenes.put("ob1", new Image("obstaculosEscenarios1.png"));		
		imagenes.put("esq",  new Image("robotz.png"));
		imagenes.put("items", new Image("items1.png"));
		imagenes.put("suelos", new Image("suelos1.png"));
		imagenes.put("coin", new Image("coin.png"));
		imagenes.put("enemigo1", new Image("roca1.png"));
		imagenes.put("enemigo2", new Image("golen1.png"));
		imagenes.put("enemigo", new Image("death.png"));
	}
	
	
	public static void Puntuaciones() {
		puntuacion=4;
		jugadores.add(new Player(JOptionPane.showInputDialog("Nomber del jugador:"),puntuacion));
		
		try {
			BufferedWriter archivo=new BufferedWriter(new FileWriter("NombredeJugardores.csv",true));
			archivo.write(Player.toCSV());
			archivo.flush();
			archivo.close();
		}catch (IOException e) {
			e.printStackTrace();
		}
		System.exit(0);
	}
	
	
	//INICIALIZO MI JUDADOR Y TILES
	public void crearObjetosJuego() {
		jugador = new Jugador(82,157,"esq",2,"descanso");//Me dice donde Aparecera el Robot
		enemigo = new EnemigoAnimado(900,82,"enemigo",2,"descanso");
		tiles = new ArrayList<Tile>();
		suelos = new ArrayList<Suelo>();
		items = new ArrayList<Item>();

		for (int i=0; i<suelo.length; i++) {
			for (int j=0; j<suelo[i].length; j++) {
				if (suelo[i][j] != 0) {
					suelos.add(new Suelo(j*80,i*80,"suelos",suelo[i][j]));//ME DEJA SIN SUELO Y SIN ROBOT
				}		
			}
		}
		if (nivel==1) {
			System.out.println("pintar nivel 1");
			for (int i=0; i<itemsMap.length; i++) {
				for (int j=0; j<itemsMap[i].length; j++) {
					if (itemsMap[i][j] != 0) {
						items.add(new Item(j*80,i*80,"items",itemsMap[i][j]));
					}		
				}
			}

			for (int i=0; i<tilemap.length; i++) {
				for (int j=0; j<tilemap[i].length; j++) {
					if (tilemap[i][j] != 0) {
						tiles.add(new Tile(j*80,i*80,"ob1",tilemap[i][j]));
					}		
				}
			}
		}
		if (nivel==2) {
			System.out.println("pintar nivel 2");
			for (int i=1; i<tilemap.length; i++) {
				for (int j=1; j<tilemap[i].length; j++) {
					if (tilemap[i][j] != 0) {
						tiles.clear();
					}		
				}
			}

			for (int i=0; i<items2Map.length; i++) {
				for (int j=0; j<items2Map[i].length; j++) {
					if (items2Map[i][j] != 0) {
						items.add(new Item(j*80,i*80,"items",items2Map[i][j]));
					}		
				}
			}


			for (int i=0; i<tile2map.length; i++) {
				for (int j=0; j<tile2map[i].length; j++) {
					if (tile2map[i][j] != 0) {
						tiles.add(new Tile(j*80,i*80,"ob1",tile2map[i][j]));
					}		
				}

			}
		}
		if (nivel==3) {
			System.out.println("pintar nivel 3");
			for (int i=1; i<tilemap.length; i++) {
				for (int j=1; j<tilemap[i].length; j++) {
					if (tilemap[i][j] != 0) {
						tiles.clear();
					}		
				}
			}

			for (int i=0; i<items3Map.length; i++) {
				for (int j=0; j<items3Map[i].length; j++) {
					if (items3Map[i][j] != 0) {
						items.add(new Item(j*80,i*80,"items",items3Map[i][j]));
					}		
				}
			}


			for (int i=0; i<tile3map.length; i++) {
				for (int j=0; j<tile4map[i].length; j++) {
					if (tile3map[i][j] != 0) {
						tiles.add(new Tile(j*80,i*80,"ob1",tile3map[i][j]));
					}		
				}

			}
		}
		if (nivel==4) {
			System.out.println("pintar nivel 4");
			for (int i=1; i<tilemap.length; i++) {
				for (int j=1; j<tilemap[i].length; j++) {
					if (tilemap[i][j] != 0) {
						tiles.clear();
					}		
				}
			}

			for (int i=0; i<items4Map.length; i++) {
				for (int j=0; j<items4Map[i].length; j++) {
					if (items4Map[i][j] != 0) {
						items.add(new Item(j*80,i*80,"items",items4Map[i][j]));
					}		
				}
			}


			for (int i=0; i<tile4map.length; i++) {
				for (int j=0; j<tile4map[i].length; j++) {
					if (tile4map[i][j] != 0) {
						tiles.add(new Tile(j*80,i*80,"ob1",tile4map[i][j]));
					}		
				}

			}
		}
		if (nivel==5) {
			System.out.println("pintar nivel 5");
			for (int i=1; i<tilemap.length; i++) {
				for (int j=1; j<tilemap[i].length; j++) {
					if (tilemap[i][j] != 0) {
						tiles.clear();
					}		
				}
			}

			for (int i=0; i<items5Map.length; i++) {
				for (int j=0; j<items5Map[i].length; j++) {
					if (items5Map[i][j] != 0) {
						items.add(new Item(j*80,i*80,"items",items5Map[i][j]));
					}		
				}
			}
			for (int i=0; i<tile5mapA.length; i++) {
				for (int j=0; j<tile5mapA[i].length; j++) {
					if (tile5mapA[i][j] != 0) {
						tiles.add(new Tile(j*80,i*80,"ob1",tile5mapA[i][j]));
					}		
				}

			}
		}
		if (nivel==6) {
			System.out.println("pintar nivel 5");
			for (int i=1; i<tilemap.length; i++) {
				for (int j=1; j<tilemap[i].length; j++) {
					if (tilemap[i][j] != 0) {
						tiles.clear();
					}		
				}
			}
			for (int i=0; i<tilemapFinal.length; i++) {
				for (int j=0; j<tilemapFinal[i].length; j++) {
					if (tilemapFinal[i][j] != 0) {
						tiles.add(new Tile(j*80,i*80,"ob1",tilemapFinal[i][j]));
					}		
				}

		}
			mostrarTiempo();
			}
		}


	   //METODO PARA MI TIEMPO
	private void mostrarTiempo() {
		graficos.fillText(String.valueOf("Tiempo: " + ((System.nanoTime()) - inicioPartida) / 1000000000.0), 11d, 61d);
	}
   
	
	// ACTUALIZA MIS TILES EN CADA NIVEL
	public void actualizarTiles() {
		if (nivel==1) {
			if (itemsColectados==4) {
				System.out.println("pintar nivel 1 completado");
				for (int i=1; i<tilemap.length; i++) {
					for (int j=1; j<tilemap[i].length; j++) {
						if (tilemap[i][j] != 0) {
							tiles.clear();
						}		
					}
				}
				for (int i=0; i<tilemapCompletado.length; i++) {
					for (int j=0; j<tilemapCompletado[i].length; j++) {
						if (tilemapCompletado[i][j] != 0) {
							tiles.add(new Tile(j*80,i*80,"ob1",tilemapCompletado[i][j]));
						}		
					}
				}
			}
		}
		if (nivel==2) {
			if (itemsColectados==4) {
				System.out.println("pintar nivel 2 completado");
				for (int i=1; i<tilemap.length; i++) {
					for (int j=1; j<tilemap[i].length; j++) {
						if (tilemap[i][j] != 0) {
							tiles.clear();
						}		
					}
				}
				for (int i=0; i<tile2mapCompletado.length; i++) {
					for (int j=0; j<tile2mapCompletado[i].length; j++) {
						if (tile2mapCompletado[i][j] != 0) {
							tiles.add(new Tile(j*80,i*80,"ob1",tile2mapCompletado[i][j]));
						}		
					}
				}
			}
		}
		if (nivel==3) {
			if (itemsColectados==4) {
				System.out.println("pintar nivel 3 completado");
				for (int i=1; i<tilemap.length; i++) {
					for (int j=1; j<tilemap[i].length; j++) {
						if (tilemap[i][j] != 0) {
							tiles.clear();
						}		
					}
				}
				for (int i=0; i<tile3mapCompletado.length; i++) {
					for (int j=0; j<tile3mapCompletado[i].length; j++) {
						if (tile3mapCompletado[i][j] != 0) {
							tiles.add(new Tile(j*80,i*80,"ob1",tile3mapCompletado[i][j]));
						}		
					}
				}
			}
	}
		if (nivel==4) {
			if (itemsColectados==4) {
				System.out.println("pintar nivel 4 completado");
				for (int i=1; i<tilemap.length; i++) {
					for (int j=1; j<tilemap[i].length; j++) {
						if (tilemap[i][j] != 0) {
							tiles.clear();
						}		
					}
				}
				for (int i=0; i<tile4mapCompletado.length; i++) {
					for (int j=0; j<tile4mapCompletado[i].length; j++) {
						if (tile4mapCompletado[i][j] != 0) {
							tiles.add(new Tile(j*80,i*80,"ob1",tile4mapCompletado[i][j]));
						}		
					}
				}
			}
		}
		if (nivel==5) {
			if (itemsColectados==4) {
				System.out.println("pintar nivel 5 completado");
				for (int i=1; i<tilemap.length; i++) {
					for (int j=1; j<tilemap[i].length; j++) {
						if (tilemap[i][j] != 0) {
							tiles.clear();
						}		
					}
				}
				for (int i=0; i<tile5mapCompletado.length; i++) {
					for (int j=0; j<tile5mapCompletado[i].length; j++) {
						if (tile5mapCompletado[i][j] != 0) {
						tiles.add(new Tile(j*80,i*80,"ob1",tile5mapCompletado[i][j]));
						}		
					}
				}
			}
		}
	}
}
