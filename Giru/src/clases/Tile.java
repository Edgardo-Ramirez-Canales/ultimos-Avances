package clases;

import implementacion.Juego;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.shape.Rectangle;

public class Tile extends ObjetoJuego{
	
	private boolean  capturado;

	public Tile(int x, int y, int xImagen, int yImagen, int anchoImagen, int altoImagen, String indiceImagen, int tipo) {
	super(x, y, xImagen, yImagen, anchoImagen, altoImagen, indiceImagen, tipo);
}

	public Tile(int x, int y, String indiceImagen, int tipo) {
		super(x, y, indiceImagen);
		this.tipo = tipo;	
		this.x = x;
		this.y = y;
		this.indiceImagen = indiceImagen;
		
		//Funciona con Obstaculos Escenario
		switch(this.tipo) {
		case 1: subImagen(123,116,80,60); break;  //BAUL
		case 2: subImagen(1165,273,83,82); break;  //FUEGO
		case 3: subImagen(130,25,72,74); break; //CASA
		case 4: subImagen(1085,455,80,82); break; //BLOQUES VERDES
		case 5: subImagen(1169,731,80,72); break;   //BLOQUES VERDES HACIA ABAJO 
		case 6: subImagen(325,635,54,54); break;//ESFERA DE PUNTAJE
		case 7: subImagen(30,25,70,64); break;     //NAVE
		case 9: subImagen(229,166,32,43); break; //NOPAL
		case 10: subImagen(184,606,85,112); break;//MALO
		case 11: subImagen(336,387,211,161); break;//DRAGON
		case 12: subImagen(184,606,85,112); break;//
		case 13: subImagen(987,165,84,83); break;//BLOQUESLUNARES
		case 14: subImagen(94,420,160,157);break;//KAME HOUSE
		case 15: subImagen(637,80,97,85);break;//CASA CHINA
		case 16: subImagen(706,10,62,73);break;//CASA ROJA
		case 17: subImagen(773,7,67,73);break;// CASA ROJA CUADRADA
		case 18: subImagen(744,89,99,102);break;//SAO PAULO
		case 19: subImagen(302,272,79,72);break;//CARRETA
		case 20: subImagen(70,185,50,61);break;//BARRIL
		case 21: subImagen(277,17,46,41);break;//TRONCO
		case 22: subImagen(12,244,60,59);break;//CAJA
		case 23: subImagen(807,346,83,83);break;// BLOQUES CAFE
		case 24: subImagen(812,611,77,83);break;// BLOQUES CAFE HACIA ABAJO
		case 27: subImagen(633,742,81,81);break;//BLOQUES MORADOS
		case 28: subImagen(542,659,81,81);break;//BLOQUES MORADOS HACIA ABAJO
		case 29: subImagen(985,457,88,48);break;//BLOQUES VERDES PEQUEÑOS
		case 30: subImagen(802,564,93,39);break;//COMO CADENAS PEQUEÑAS REDONDAS
		case 33: subImagen(93,374,53,32);break; //MAYA
		case 34: subImagen(150,376,52,32);break; //MAYA2
		case 35: subImagen(205,375,55,31);break; //MAYA3
		case 36: subImagen(765,200,86,48);break;//BLOQUE LUNAR PEQUENO__
		case 37: subImagen(614,367,83,81);break;//BLOQUE LUNAR HACIA ABAJO
		
		}
	
	}
	
	public void subImagen(int x, int y, int ancho, int alto) {
		this.xImagen = x;
		this.yImagen = y;
		this.anchoImagen = ancho;
		this.altoImagen = alto;
	}
	public Rectangle obtenerRectangulo() {
		return new Rectangle(this.x, this.y, this.anchoImagen, this.altoImagen);
	}
	
	

	public void pintar(GraphicsContext graficos) {
		if (this.capturado)
			return;
		
		//graficos.strokeRect(this.x, this.y, this.anchoImagen, this.altoImagen);
		graficos.drawImage(Juego.imagenes.get(indiceImagen), xImagen, yImagen, anchoImagen, altoImagen, x, y, anchoImagen, altoImagen);
		
	}

	public boolean isCapturado() {
		return capturado;
	}

	public void setCapturado(boolean capturado) {
		this.capturado = capturado;
	}
	
	
}
