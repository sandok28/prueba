import javax.swing.*;     
import java.awt.*;

public class Imagenes extends JPanel{
	// declaraciones
	private ImageIcon imagen;
	private String nombre;
//constructor 
public Imagenes(String nombre){
this.nombre=nombre;// globalizo el parametro
}
// ajuta y agrega la imagen al panel
public void paint(Graphics g){
	Dimension tamanio=getSize();
	//System.out.println("/home/sandok/Documentos/Gif/"+nombre);
	imagen=new ImageIcon(nombre);
	
	g.drawImage(imagen.getImage(),0,0,tamanio.width,tamanio.height,null);
setOpaque(false);
super.paint(g);
}
public String estado(){
	
	return nombre;
}
}
