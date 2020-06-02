package clases;

import java.util.HashMap;
import implementacion.Juego;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.shape.Rectangle;

public class EnemigoAnimado extends ObjetoJuego{
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

	public EnemigoAnimado(int x, int y, String indiceImagen, int velocidad, String animacionActual ) {
		super(x, y,indiceImagen);
		this.velocidad = velocidad;
		this.velociadadAnimacion = num;
		this.animacionActual = animacionActual;
		animaciones = new HashMap<String, Animacion>();
		inicializarAnimaciones();
	}

	public void inicializarAnimaciones() {
		
		Rectangle[] CoorrdenadasDescanso = {
				new Rectangle(97,3,45,61),
		};
		animaciones.put("descanso",new Animacion("descanso",0.20,CoorrdenadasDescanso));
		
		
		
		Rectangle[] CoorrdenadasCaminarAbajo = {
				new Rectangle(2,3,44,62),
			    new Rectangle(49,4,44,62), //COORDENADAS HACIA ABAJO
				new Rectangle(97,2,44,62),
				new Rectangle(145,4,44,62),	
	        	};		
		animaciones.put("caminarAbajo",new Animacion("caminarAbajo",0.08,CoorrdenadasCaminarAbajo));
		
		
		
		Rectangle[] CoordenadasCaminarArriba = {
				new Rectangle(2,193,44,62),
				new Rectangle(48,194,44,62), //COORDENADAS HACIA ARRIBA
				new Rectangle(97,193,44,62),						
				new Rectangle(145,194,46,62),	
				};
		animaciones.put("caminarArriba", new Animacion("caminarArriba",0.08, CoordenadasCaminarArriba));
		
	
		
		Rectangle[] CoorrdenadasCaminarHorizontal = {
				new Rectangle(0,130,44,62),
				new Rectangle(47,130,44,62),
				new Rectangle(96,130,44,62),
				new Rectangle(144,130,44,62), //CAMINAR HORIZONTAL COORDENADAS												
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
			this.indiceImagen = "enemigo";
		}else {
			this.velociadadAnimacion = 0.15;
			this.velocidad = 1;
			this.indiceImagen = "enemigo";
		}
			
		if (Juego.derecha) {   //Mover hacia la derecha
				
			x-= velocidad;
			this.animacionActual = "horizontal";
			this.orientacion = -1;
			arp = 0;				
		}
		
		if (Juego.izquierda) {  //Mover hacia la izquierda
			if(true)
			x+= velocidad;
			this.animacionActual = "horizontal";
			this.orientacion = 1;
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
	
	
	
	

	
	

	
	
}


