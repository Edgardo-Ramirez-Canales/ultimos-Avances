package clases;


import implementacion.Juego;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.shape.Rectangle;

public class Item extends ObjetoJuego {
	private boolean capturado;
	

	 public Item(int x, int y, int xImagen, int yImagen, int anchoImagen, int altoImagen, String indiceImagen,
			 int tipo, boolean capturado) {
		super(x, y, xImagen, yImagen, anchoImagen, altoImagen, indiceImagen, tipo);
		this.capturado = capturado;
		this.anchoImagen = (int)Juego.imagenes.get(this.indiceImagen).getWidth();
		this.altoImagen = (int)Juego.imagenes.get(this.indiceImagen).getHeight();
	 }

	 
	 public Item(int x, int y,String indiceImagen, int tipo) {
		super(x, y,indiceImagen);
		this.tipo = tipo;
		this.x = x;
		this.y = y;
		this.indiceImagen = indiceImagen;
		this.anchoImagen = (int)Juego.imagenes.get(this.indiceImagen).getWidth();
		this.altoImagen = (int)Juego.imagenes.get(this.indiceImagen).getHeight();
		
		
		//SON LOS ITEMS
		switch(this.tipo) {
		
		case 1: subImagen(7,23,52,48); break; //1 ESFERA
		case 2: subImagen(64,23,48,46); break;//2 ESFERAS
		case 3: subImagen(114,26,48,44); break;//3 ESFERAS
		case 4: subImagen(167,24,50,54); break;//4 ESFERAS
		case 5: subImagen(228,22,48,54); break;//5 ESFERAS
		case 6: subImagen(289,26,52,52); break;//6 ESFERAS
		case 7: subImagen(355,21,50,58); break;//7 ESFERAS
		case 8: subImagen(198,96,48,40); break;//CORAZON
		case 9: subImagen(116,27,50,42); break;//MONEDA
		}
	}
	 
	 
	 
	 public void pintar(GraphicsContext graficos) {
			if (this.capturado)
				return;
			//graficos.setStroke(Color.RED);
		   //graficos.strokeRect(this.x, this.y, this.anchoImagen, this.altoImagen);
			graficos.drawImage(Juego.imagenes.get(indiceImagen), xImagen, yImagen, anchoImagen, altoImagen, x, y, anchoImagen, altoImagen);
		}
	 
	 
	 public void mover() {
			
		}
	 
	public Rectangle obtenerRectangulo() {
		return new Rectangle(this.x, this.y, this.anchoImagen, this.altoImagen);
	}
	
	
	
	public void subImagen(int x, int y, int ancho, int alto) {
		this.xImagen = x;
		this.yImagen = y;
		this.anchoImagen = ancho;
		this.altoImagen = alto;
	}
		

	public boolean isCapturado() {
		return capturado;
	}
	public void setCapturado(boolean capturado) {
		this.capturado = capturado;
	}
	
}

