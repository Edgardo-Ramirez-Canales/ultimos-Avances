package clases;
import implementacion.Juego;
import javafx.scene.canvas.GraphicsContext;

public class Suelo extends ObjetoJuego{

	public Suelo(int x, int y, int xImagen, int yImagen, int anchoImagen, int altoImagen, String indiceImagen,
			int tipo) {
		super(x, y, xImagen, yImagen, anchoImagen, altoImagen, indiceImagen, tipo);
		
	}
	
	
	public Suelo(int x, int y, String indiceImagen, int tipo) {
		super(x, y, indiceImagen);
		this.tipo = tipo;	
		this.x = x;
		this.y = y;
		this.indiceImagen = indiceImagen;
	
		//FUNCIONA CON IMAGEN DE SUELO
		switch (this.tipo) {
		
		case 1: subImagen(206, 268, 108,88); break;	
		case 2: subImagen(677, 10, 106,112); break;
//		case 3: subImagen(51, 523, 80,90); break;
		
		}
	}

	public void subImagen(int x, int y, int ancho, int alto) {
		this.xImagen = x;
		this.yImagen = y;
		this.anchoImagen = ancho;
		this.altoImagen = alto;
	}
	
	
	public void pintar(GraphicsContext graficos) {
		graficos.drawImage(Juego.imagenes.get(indiceImagen), xImagen, yImagen, anchoImagen, altoImagen, x, y, anchoImagen, altoImagen);
	}
	
}
