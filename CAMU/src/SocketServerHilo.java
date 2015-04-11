import java.net.*; 
import java.util.ArrayList;
import java.io.*;

public class SocketServerHilo implements Runnable {

    String recibido;
    OutputStream osalida;
	DataOutputStream dsalida;

	InputStream ientrada;
	DataInputStream dentrada;

	Socket socket;
	int numerodelhilo;
	String nomcliente;
	boolean visto=false;
	ArrayList<SocketServerHilo> hermanos;

	public SocketServerHilo(Socket lsocket,int numerodelhilo,int nomhilo,ArrayList<SocketServerHilo> hermanos){
		try{
			this.numerodelhilo=numerodelhilo;
			this.hermanos=hermanos;
			this.nomcliente=Integer.toString(nomhilo);
			socket = lsocket;			
		}
		catch (Exception excepcion) {
			System.out.println(excepcion);
		}		
	}

	public void run() {	

		try{			
			osalida = socket.getOutputStream();
			dsalida = new DataOutputStream(osalida);

			ientrada = socket.getInputStream();
			dentrada = new DataInputStream(ientrada);

			dsalida.writeUTF("Bienvenido al Chat\n");

			do{
				recibido = dentrada.readUTF();	
				
				if(recibido.equals("VISTOS"))visto=true;
				int tama=hermanos.size();
				if(recibido.equalsIgnoreCase("zuuuumbido\n")){for (int i = 0; i < tama; i++)if(i!=(numerodelhilo))hermanos.get(i).dsalida.writeUTF("zuuuumbido");}
				else{
				for (int i = 0; i < tama; i++) {
					if(visto){if(i!=(numerodelhilo))hermanos.get(i).dsalida.writeUTF("Visto por Cliente "+nomcliente+"\n");}
					else { String s="Cliente "+numerodelhilo+": "+recibido;
						char[] info=s.toCharArray();
						if(info.length==20) hermanos.get(i).dsalida.writeUTF("Cliente "+nomcliente+": "+recibido+" ");
						else hermanos.get(i).dsalida.writeUTF("Cliente "+nomcliente+": "+recibido);
						}					 
				}
				visto=false;
				}
			}while(!recibido.equals("bye"));
		}
		catch (IOException excepcion) {
			int poshilo=numerodelhilo;
			hermanos.remove(poshilo);
			int tama=hermanos.size();
		for (int i = poshilo; i < tama; i++) hermanos.get(i).setnumerohilo(i);
		
		try {
			for (int i = 0; i < tama; i++)
				
					hermanos.get(i).dsalida.writeUTF(" ha abandonado el chat Cliente "+nomcliente+" \n");
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
		//	System.out.println(excepcion.getMessage());
		}
		
		try{
			dsalida.close();
			dentrada.close();
			socket.close();			
		}
		catch (IOException excepcion21) {
			System.out.println(excepcion21);
		}			
	}
	public void setnumerohilo(int numhilo){
		this.numerodelhilo=numhilo;
		
	}
}