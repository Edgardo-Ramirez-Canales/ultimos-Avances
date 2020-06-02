package clases;

import java.util.HashMap;
import implementacion.Juego;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.shape.Rectangle;

public class EnemigoAnimado2 extends ObjetoJuego {
	private int ancho;
	private int alto;
	private String indiceImagen;
	private boolean capturado;
	private static int velocidad;
	public static String animacionActual;
	private HashMap<String, Animacion> animaciones;
	
	
	public EnemigoAnimado2(int x, int y, int ancho, int alto, String indiceImagen, int velocidad, String animacionActual, boolean capturado) {
		super(velocidad, velocidad, velocidad, velocidad, velocidad, velocidad, animacionActual, velocidad);
		this.x = x;
		this.y = y;
		this.ancho = ancho;
		this.alto = alto;
		this.indiceImagen = indiceImagen;
		this.capturado = capturado;
		EnemigoAnimado2.velocidad = velocidad;
		EnemigoAnimado2.animacionActual=animacionActual;
		inicializarAnimaciones();
	}
	
	public void inicializarAnimaciones() {
		animaciones = new HashMap<String, Animacion>();
		Rectangle coordenadasMover[]= {
				
				new Rectangle(0, 7, 86, 88),
				new Rectangle(76, 6, 86, 88),
				new Rectangle(173, 6, 86, 90),
				new Rectangle(257, 6, 86, 90),
				new Rectangle(349, 5, 86, 90),		
				new Rectangle(19, 108, 86, 90),
				new Rectangle(122, 109, 86, 90),
				new Rectangle(228, 108, 86, 90),
				new Rectangle(322, 109, 86, 90),
				new Rectangle(17, 225, 86, 90),
				new Rectangle(119, 225, 86, 90),
	    		new Rectangle(220, 226, 86, 90),
	 		
		};
		
		Animacion animacionMover = new Animacion("mover",0.1,coordenadasMover);
		animaciones.put("mover",animacionMover);
     }
	
	
	public void mover(){
		if (Juego.derecha)
			this.x-=velocidad;
	}
	
	
	public void actualizarAnimacion(double time) {
		Rectangle coordenadasActuales = this.animaciones.get(animacionActual).calcularFrameActual(time);
		this.xImagen = (int)coordenadasActuales.getX();
		this.yImagen = (int)coordenadasActuales.getY();
		this.anchoImagen = (int)coordenadasActuales.getWidth();
		this.altoImagen = (int)coordenadasActuales.getHeight();
	
	}
	
	
	public void pintar(GraphicsContext graficos) {
		graficos.drawImage(
				Juego.imagenes.get(this.indiceImagen), 
				this.xImagen, this.yImagen, 
				this.anchoImagen, this.altoImagen,
				this.x-=2, this.y,
				this.anchoImagen, this.altoImagen
		);
    }
	
	
	public Rectangle obtenerRectangulo() {
		return new Rectangle(this.x, this.y, this.anchoImagen, this.altoImagen);
	}
	
	public boolean isCapturado() {
		return capturado;
	}

	public int getAncho() {
		return ancho;
	}

	public void setAncho(int ancho) {
		this.ancho = ancho;
	}

	public int getAlto() {
		return alto;
	}

	public void setAlto(int alto) {
		this.alto = alto;
	}

	public String getIndiceImagen() {
		return indiceImagen;
	}

	public void setIndiceImagen(String indiceImagen) {
		this.indiceImagen = indiceImagen;
	}

	public static int getVelocidad() {
		return velocidad;
	}

	public static void setVelocidad(int velocidad) {
		EnemigoAnimado2.velocidad = velocidad;
	}

	public static String getAnimacionActual() {
		return animacionActual;
	}

	public static void setAnimacionActual(String animacionActual) {
		EnemigoAnimado2.animacionActual = animacionActual;
	}

	public HashMap<String, Animacion> getAnimaciones() {
		return animaciones;
	}

	public void setAnimaciones(HashMap<String, Animacion> animaciones) {
		this.animaciones = animaciones;
	}

	public void setCapturado(boolean capturado) {
		this.capturado = capturado;
	}
	
	
}
