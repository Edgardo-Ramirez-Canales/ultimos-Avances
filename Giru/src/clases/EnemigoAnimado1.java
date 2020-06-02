package clases;

import java.util.HashMap;
import implementacion.Juego;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.shape.Rectangle;

public class EnemigoAnimado1 extends ObjetoJuego {
	private int ancho;
	private int alto;
	private String indiceImagen;
	private boolean capturado;
	private static int velocidad;
	public static String animacionActual;
	private HashMap<String, Animacion> animaciones;
	
	
	public EnemigoAnimado1(int x, int y, int ancho, int alto, String indiceImagen, int velocidad, String animacionActual, boolean capturado) {
		super(velocidad, velocidad, velocidad, velocidad, velocidad, velocidad, animacionActual, velocidad);
		this.x = x;
		this.y = y;
		this.ancho = ancho;
		this.alto = alto;
		this.indiceImagen = indiceImagen;
		this.capturado = capturado;
		EnemigoAnimado1.velocidad = velocidad;
		EnemigoAnimado1.animacionActual=animacionActual;
		inicializarAnimaciones();
	}
	
	public void inicializarAnimaciones() {
		animaciones = new HashMap<String, Animacion>();
		Rectangle coordenadasMover[]= {
	
				new Rectangle(0,1,88,86),
				new Rectangle(77,1,88,86),
				new Rectangle(168,1,88,86),
				new Rectangle(252,4,88,86),
				new Rectangle(348,0,88,86),
				new Rectangle(10,95,88,86),
				new Rectangle(105,93,88,86),
				new Rectangle(201,95,88,86),
				new Rectangle(308,95,88,86),
				new Rectangle(8,195,88,86),
				new Rectangle(101,196,88,86),
				new Rectangle(199,194,88,86)
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
		EnemigoAnimado1.velocidad = velocidad;
	}

	public static String getAnimacionActual() {
		return animacionActual;
	}

	public static void setAnimacionActual(String animacionActual) {
		EnemigoAnimado1.animacionActual = animacionActual;
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
