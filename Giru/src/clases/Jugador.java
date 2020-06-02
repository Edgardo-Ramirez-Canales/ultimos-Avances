package clases;



import java.util.HashMap;

import javax.swing.JOptionPane;

import implementacion.Juego;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.shape.Rectangle;

public class Jugador extends ObjetoJuego{
	private int velocidad;
	private double velociadadAnimacion  ;
	private double num;
	private HashMap<String, Animacion> animaciones;
	private String animacionActual ;
	private int orientacion = 1;
	private int arp =0;
	private int puntuacion = 0;
	private String ultimaAnimacion = "descanso";
	public Juego juego;

	public Jugador(int x, int y, String indiceImagen, int velocidad, String animacionActual ) {
		super(x, y,indiceImagen);
		this.velocidad = velocidad;
		this.velociadadAnimacion = num;
		this.animacionActual = animacionActual;
		animaciones = new HashMap<String, Animacion>();
		inicializarAnimaciones();
	}

	public void inicializarAnimaciones() {
		
		Rectangle[] CoorrdenadasDescanso = {
				
				new Rectangle(8,28,62,80),			
				
				//new Rectangle(8,28,62,80),
		};
		animaciones.put("descanso",new Animacion("descanso",0.20,CoorrdenadasDescanso));
		
		
		Rectangle[] CoorrdenadasCaminarAbajo = {
				new Rectangle(404,236,54,80),
			    new Rectangle(243,234,58,83), //COORDENADAS HACIA ABAJO
				new Rectangle(158,132,81,79),
				new Rectangle(235,132,81,81),
				new Rectangle(315,131,78,81),
				new Rectangle(308,24,85,81),									
		};		
		animaciones.put("caminarAbajo",new Animacion("caminarAbajo",0.08,CoorrdenadasCaminarAbajo));
		
		
		Rectangle[] CoordenadasCaminarArriba = {
				
				new Rectangle(9,131,60,82),
				new Rectangle(395,24,65,84), //COORDENADAS HACIA ARRIBA
				new Rectangle(473,29,66,79),						
				
				
				//new Rectangle(7,121,60,74),
				//new Rectangle(364,25,62,74),
				//new Rectangle(436,26,62,74),
				//new Rectangle(429,314,78,70),
				
			
		};
		animaciones.put("caminarArriba", new Animacion("caminarArriba",0.08, CoordenadasCaminarArriba));
		
	
		Rectangle[] CoorrdenadasCaminarHorizontal = {
				
				new Rectangle(5,441,67,80),
				new Rectangle(81,446,69,76),
				new Rectangle(161,444,69,77),
				new Rectangle(240,443,68,78), //CAMINAR HORIZONTAL COORDENADAS								
				new Rectangle(315,438,74,86),
				new Rectangle(395,446,73,75),
				new Rectangle(471,446,69,73),
				new Rectangle(543,443,78,78),
				
				
				
				
				
				
			//	new Rectangle(4,409,60,74),
			//	new Rectangle(76,412,60,72),
			//	new Rectangle(154,409,54,72),
		    //	new Rectangle(222,409,58,72),
			//	new Rectangle(291,410,62,70),
			//	new Rectangle(366,411,62,70),
			//	new Rectangle(440,412,62,70),
			//    new Rectangle(510,409,62,74),
			//--	new Rectangle(437,220,62,74),
			//--	new Rectangle(507,217,66,74),
		//--		new Rectangle(575,214,74,71),
				
					};
		animaciones.put("horizontal",new Animacion("horizontal",0.10,CoorrdenadasCaminarHorizontal));	
	     }
	
	
	//Se ejecuta por cada iteracion del ciclo de juego
	public void mover() {
		if (x>1280) {
			x= 20;
		}
		this.animacionActual = "descanso";
		
		if (Juego.espacio) {
			this.velociadadAnimacion = 0.01;
			this.velocidad = 15;
			this.indiceImagen = "esq";
		}else {
			this.velociadadAnimacion = 0.15;
			this.velocidad = 1;
			this.indiceImagen = "esq";
		}
			
		if (Juego.derecha) {   //Mover hacia la derecha
				
			x+= velocidad;
			this.animacionActual = "horizontal";
			this.orientacion = 1;
			arp = 0;				
		}
		
		if (Juego.izquierda) {  //Mover hacia la izquierda
			if(true)
			x-= velocidad;
			this.animacionActual = "horizontal";
			this.orientacion = -1;
			arp = 60;
			
		}
		
		if (Juego.arriba) {    //Mover hacia arriba
			y-= velocidad;
			this.animacionActual = "caminarArriba";
		}
		
		if (Juego.abajo) {     //Mover hacia abajo
			y+= velocidad;
			this.animacionActual = "caminarAbajo";
		}
		//if (x>1120) {
		//	x= 20;
		//}
	}

	
	public void actualizarAnimacion(double time) {
		Rectangle rectanguloActual = animaciones.get(animacionActual).calcularFrameActual(time);
		this.xImagen = (int)rectanguloActual.getX();
		this.yImagen = (int)rectanguloActual.getY();
		this.anchoImagen = (int)rectanguloActual.getWidth();
		this.altoImagen = (int)rectanguloActual.getHeight();
	}
	
	
	public void pintar (GraphicsContext graficos) {
		graficos.drawImage(Juego.imagenes.get(indiceImagen), xImagen, yImagen, anchoImagen, altoImagen, x+arp, y, (orientacion)*anchoImagen, altoImagen);
	}

	public void agregarPuntuacion(int puntuacion) {
		this.puntuacion+= puntuacion;
	}
	
	public Rectangle obtenerRectangulo() {
		return new Rectangle(this.x, this.y, this.anchoImagen, this.altoImagen);
	}
	
	public boolean verificarColision (Tile tiles) {
		if (obtenerRectangulo().intersects(tiles.obtenerRectangulo().getBoundsInLocal()) ) {
			//no mover parametro de X e Y
			System.out.println(" Esta colisionando con una pared");
			if (Juego.derecha) {
				
				x-=velocidad;
				this.animacionActual = "descanso";
				//arp = 0;
							
			}
			
			if (Juego.izquierda) {
				if(true)
				x+= velocidad;
				this.animacionActual = "descanso";;
				this.orientacion = -1;
				arp = 60;
				
			}
			
			if (Juego.arriba) {
				y+= velocidad;
				this.animacionActual = "descanso";;
			}
			
			if (Juego.abajo) {
				y-= velocidad;
				this.animacionActual = "descanso";;
			}
			return true;
		}
		return false;
	}
	public boolean verificarColision(Item item) {
		if (obtenerRectangulo().intersects(item.obtenerRectangulo().getBoundsInLocal()) && !item.isCapturado()) {
			item.setCapturado(true);
			agregarPuntuacion(1);
			System.out.println("Estan colisionando");
			return true;
		}
		return false;
	}

	
	
	public int getVelocidad() {
		return velocidad;
	}

	public void setVelocidad(int velocidad) {
		this.velocidad = velocidad;
	}

	public HashMap<String, Animacion> getAnimaciones() {
		return animaciones;
	}

	public void setAnimaciones(HashMap<String, Animacion> animaciones) {
		this.animaciones = animaciones;
	}

	public String getAnimacionActual() {
		return animacionActual;
	}

	public void setAnimacionActual(String animacionActual) {
		this.animacionActual = animacionActual;
	}

	public int getOrientacion() {
		return orientacion;
	}

	public void setOrientacion(int orientacion) {
		this.orientacion = orientacion;
	}

	public double getVelociadadAnimacion() {
		return velociadadAnimacion;
	}

	public void setVelociadadAnimacion(double velociadadAnimacion) {
		this.velociadadAnimacion = velociadadAnimacion;
	}

	public int getArp() {
		return arp;
	}

	public void setArp(int arp) {
		this.arp = arp;
	}

	public int getPuntuacion() {
		return puntuacion;
	}

	public void setPuntuacion(int puntuacion) {
		this.puntuacion = puntuacion;
	}

	public String getUltimaAnimacion() {
		return ultimaAnimacion;
	}

	public void setUltimaAnimacion(String ultimaAnimacion) {
		this.ultimaAnimacion = ultimaAnimacion;
	}
	
	
	public boolean verificarColision(ItemAnimado1 itemAnimado) {	
		if (obtenerRectangulo().intersects(itemAnimado.obtenerRectangulo().getBoundsInLocal()) && !itemAnimado.isCapturado()) {
			itemAnimado.setCapturado(true);
			agregarPuntuacion(1);
			System.out.println("Estan colisionando");
			return true;
		}
		return false;	
	}
	
	
	public boolean verificarColision(EnemigoAnimado1 enemigoAnimado1) {
		
		if (obtenerRectangulo().intersects(enemigoAnimado1.obtenerRectangulo().getBoundsInLocal()) && !enemigoAnimado1.isCapturado()) {
			enemigoAnimado1.setCapturado(true);
			this.puntuacion--;
			//agregarPuntuacion(1);
			System.out.println("Estan colisionando");
			//return true;
		if (puntuacion<0) {		
				JOptionPane.showMessageDialog(null, "HAS PERDIDO");
				Juego.Puntuaciones();
				enemigoAnimado1.setCapturado(true);
			}			
		}
		return false;
	}
	
	
	public boolean verificarColision(EnemigoAnimado2 enemigoAnimado2) {
		if (obtenerRectangulo().intersects(enemigoAnimado2.obtenerRectangulo().getBoundsInLocal()) && !enemigoAnimado2.isCapturado()) {
			enemigoAnimado2.setCapturado(true);
			this.puntuacion--;
			System.out.println("Estan colisionando");
		if (puntuacion<0) {		
			JOptionPane.showMessageDialog(null, "HAS PERDIDO");
			Juego.Puntuaciones();
			enemigoAnimado2.setCapturado(true);
			}
		}
		return false;
	}

}

