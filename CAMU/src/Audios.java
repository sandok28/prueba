import java.io.File;  
import javax.sound.sampled.AudioFileFormat;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

public class Audios  {
// declaraciones
      Clip sonido;
      String nombre;
   public Audios(String nombre){
	   //glbaliso parametros
	  this.nombre=nombre;
	   try {
		   sonido = AudioSystem.getClip(); //obj de la clase Clip 
       System.out.println(cambiarnombre());
           sonido.open(AudioSystem.getAudioInputStream(new File(cambiarnombre())));// habre y deja listo para usar el audio
           
       } catch (Exception e) {
           System.out.println("" + e);
       }
   }
   //detiene la reproduccion
   public void Detener() {   
	
	   sonido.close();  
   }
   // reprduce la cancion 50 veces
public void Continuo(){
	sonido.loop(50);
	}
//reproduce la cancion una ves
public void Iniciar(){
	sonido.start();
}
// autocompleto la direccion para solo enviar el nombre de la cancion y no la direccion completa y el formato
public String cambiarnombre(){
	String	direccion="";
direccion+=nombre+".wav";
	return direccion;
}


public static void main(String[] args) {
	Audios zumbidos=new Audios("devil");
	zumbidos.Continuo();
}

}
     
           
   
    
  