package clases;

import java.util.HashMap;
import implementacion.Juego;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.shape.Rectangle;

public class ItemAnimado1 extends ObjetoJuego {
	private int ancho; 
	private int alto;  
	private boolean capturado;
	private static int velocidad;
	public static String animacionActual;
	private HashMap<String, Animacion> animaciones;
	
	
	public ItemAnimado1(int x, int y, int ancho, int alto, String indiceImagen, int velocidad, String animacionActual, boolean capturado) {
		super(x, y,indiceImagen);
		this.x = x;
		this.y = y;
		this.setAncho(ancho);
		this.setAlto(alto);
		this.indiceImagen = indiceImagen;
		this.capturado = capturado;
		ItemAnimado1.velocidad = velocidad;
		ItemAnimado1.animacionActual=animacionActual;
		inicializarAnimaciones();	
		}

	public void actualizarAnimacion(double time) {
		Rectangle coordenadasActuales = this.animaciones.get(animacionActual).calcularFrameActual(time);
		this.xImagen = (int)coordenadasActuales.getX();
		this.yImagen = (int)coordenadasActuales.getY();
		this.anchoImagen = (int)coordenadasActuales.getWidth();
		this.altoImagen = (int)coordenadasActuales.getHeight();		
	}
	
	
	public void inicializarAnimaciones() {
		animaciones = new HashMap<String, Animacion>();
		Rectangle coordenadasMover[]= {
				new Rectangle(11, 12, 37, 37),
				new Rectangle(50, 12, 37, 37),
				new Rectangle(92, 12, 37, 37),
				new Rectangle(7, 56, 37, 37),
				new Rectangle(43, 56, 37, 37),
				new Rectangle(94, 56, 37, 37)
		};
		
		Animacion animacionMover = new Animacion("mover",0.1,coordenadasMover);
		animaciones.put("mover",animacionMover);
       }
	
    	public void mover(){					//NO USADO
	    	if (Juego.derecha)
		    	this.x-=velocidad;
	    }

    	public void pintar(GraphicsContext graficos) {
    		if (!capturado)
    			graficos.drawImage(
    					Juego.imagenes.get(this.indiceImagen), 
    					this.xImagen, this.yImagen, 
    					this.anchoImagen, this.altoImagen,
    					this.x, this.y,
    					this.anchoImagen, this.altoImagen
    			);   		
    	}
	
    	public Rectangle obtenerRectangulo() {
    		return new Rectangle(this.x, this.y, this.anchoImagen, this.altoImagen);
    	}

		public boolean isCapturado() {
			return capturado;
		}

		public void setCapturado(boolean capturado) {
			this.capturado = capturado;
		}

		public static int getVelocidad() {
			return velocidad;
		}

		public static void setVelocidad(int velocidad) {
			ItemAnimado1.velocidad = velocidad;
		}

		public static String getAnimacionActual() {
			return animacionActual;
		}
		
		public static void setAnimacionActual(String animacionActual) {
			ItemAnimado1.animacionActual = animacionActual;
		}

		public HashMap<String, Animacion> getAnimaciones() {
			return animaciones;
		}

		public void setAnimaciones(HashMap<String, Animacion> animaciones) {
			this.animaciones = animaciones;
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
	
	
}
