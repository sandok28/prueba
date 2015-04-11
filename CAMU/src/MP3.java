//Reproducir un MP3 en Java 

import java.io.BufferedInputStream; 
import java.io.FileInputStream; 

import javazoom.jl.player.Player; 

//esta el clase para reproducior MP3 (ponerla en un archivo solo) 

public class MP3 { 
private String filename; 
private Player player; 

// constructor que tom ael nombre el arhivo MP3 
public MP3(String filename) { 
this.filename = filename; 
} 

public void close() { if (player != null) player.close(); } 

// reproduce le mp3 en la tarjeta de sonido predterminada 
public void play() { 
try { 
FileInputStream fis = new FileInputStream(filename); 
BufferedInputStream bis = new BufferedInputStream(fis); 
player = new Player(bis); 
} 
catch (Exception e) { 
System.out.println("Problem playing file " + filename); 
System.out.println(e); 
} 

// correo el proceso en un nuevo hilo para deterner la ejecucion del programa 
new Thread() { 
public void run() { 
try { player.play(); } 
catch (Exception e) { System.out.println(e); } 
} 
}.start(); 


} 


//poner esto en el programa donde vamos a hacer sonar el mp3: 

// prueba 
public static void main(String[] args) { 
String filename = "prueba.mp3"; 

// nueva instancia de la clase MP3 
MP3 mp3 = new MP3(filename); 
mp3.play(); 

// establecemos el tiempo para procesar el sonido (milisegundos) 
int N = 4000; 
double sum = 0.0; 
for (int i = 0; i < N; i++) { 
for (int j = 0; j < N; j++) { 
sum += Math.sin(i + j); 
} 
} 
System.out.println(sum); 

// cuando el tiempo de proceso termina se detiene la reproduccion 
mp3.close(); 

// reproducir el mp3 desde el principio 
mp3 = new MP3(filename); 
mp3.play(); 

} 



}