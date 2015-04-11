import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class Cliente extends JFrame  implements Runnable{
	
	
	
	/*
	
	//////////////////
	/////////////////
        

        Chat Activo Multi Usuario (CAMU)	

	LEER---

	El chat tiene 
	
	-zumbido se activa oprimiendo un boton en el ojo del personaje de fondo o con la palabra zuuuumbido como mensaje 
	
	-vistos de las conversaciones
	
	-no se cae el chat cuando se cierran algunos socket

	////////////////
	///////////////
    	
	*/
	
	
	
	
	
	JTextArea caja=new JTextArea();
	JTextField info=new JTextField(20);
	JButton boton=new JButton();
	String infoserver="desconectado";
	JLabel texto=new JLabel("INFO =");
	JScrollPane scroll = new JScrollPane();
	String recibido;
        OutputStream osalida;
	DataOutputStream dsalida;
	boolean salida=true;
	

	InputStream ientrada;
	DataInputStream dentrada;

	Socket cliente;
	Thread hilocaja;
	boolean abierto=false;
	MP3 zumbidos=new MP3("zumbido.mp3");
	MP3 msm=new MP3("mensaje.mp3");
	
	public Cliente() {
		
		setSize(550,500);
		setLocation(200,200);
		
		servidor();
		
		setVisible(true);	
		
		scroll.setViewportView(caja);
		try {	
			
			cliente = new Socket("127.0.0.1", 3000);  
			osalida = cliente.getOutputStream();
			dsalida = new DataOutputStream(osalida);

			ientrada = cliente.getInputStream();
			dentrada = new DataInputStream(ientrada);
 
			recibido = dentrada.readUTF();
	        caja.setText(caja.getText()+recibido);
		}
		catch (Exception e) {
			System.out.println("ALGO SALIO MAL CON EL RECIBIDO");
			System.err.println("Error: " + e);
		}
		
		hilocaja=new Thread(this);
		hilocaja.start();
		
	}


	public void servidor() { 
		try {
			
		} catch (Exception e) {
			
		}  
		
		this.setLayout(null);
		this.setResizable(false);
		JPanel Pfondo=new JPanel();
		Pfondo.setLayout(new GridLayout(1, 1, 1, 1));
		Pfondo.setBounds(0,0,550,600);
		JLabel ima=new JLabel(new ImageIcon("fondo.jpg"));
		Pfondo.add(ima);
	    JButton zumbido=new JButton();
	    zumbido.addActionListener(
				new ActionListener(){
					public void actionPerformed(ActionEvent evento){
					 try {
						
						dsalida.writeUTF("zuuuumbido"+"\n");
						caja.setText(caja.getText()+"Has enviado un zumbido\n");
				
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
							
					}	
				}
			);
	
	    addWindowListener(
				new WindowAdapter(){
				public void windowClosing(WindowEvent we){
					//cerrarsesion();
					System.exit(0);// salir del programa
				}});
		
		boton.addActionListener(
				new ActionListener(){
					public void actionPerformed(ActionEvent evento){
					 try {
						
						dsalida.writeUTF(info.getText()+"\n");
						if(info.getText().equalsIgnoreCase("zuuuumbido"))caja.setText(caja.getText()+"Has enviado un zumbido\n");
						info.setText("");
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
							
					}	
				}
			);
		addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowActivated(java.awt.event.WindowEvent evt) {
            	try {
					if(abierto)dsalida.writeUTF("VISTOS");
					abierto=true;
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
            }
        });
		
		info.addKeyListener(new KeyAdapter(){
			 
			public void keyPressed(KeyEvent e) {
			        if(e.getKeyCode()==KeyEvent.VK_ENTER){
			        	 try {
								
								dsalida.writeUTF(info.getText()+"\n");
								if(info.getText().equalsIgnoreCase("zuuuumbido"))caja.setText(caja.getText()+"Has enviado un zumbido\n");
								info.setText("");
							} catch (IOException e2) {
								// TODO Auto-generated catch block
								e2.printStackTrace();
							}
			}  
			}});
		JLabel titulo=new  JLabel("     Chat  Sokects");
		
		titulo.setFont(new java.awt.Font("Tahoma", 0, 30)); // NOI18N
		titulo.setForeground(Color.WHITE);
		titulo.setOpaque(false);	
		titulo.setBounds(20,20,300,50);
		
		caja.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
		
		boton.setText("Enviar");
		boton.setBounds(120,420,120 ,40);
		boton.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
		boton.setForeground(Color.WHITE);
		boton.setContentAreaFilled(false);
		boton.setFocusPainted(false);
		boton.setBorderPainted(false);
		boton.setOpaque(false);
		boton.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
		
		
		zumbido.setBounds(370,170,40 ,40);
		zumbido.setContentAreaFilled(false);
		zumbido.setFocusPainted(false);
		zumbido.setBorderPainted(false);
		zumbido.setOpaque(false);
		zumbido.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
		
		
		info.setBounds(30,380,300 ,30);
		info.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
		info.setForeground(Color.WHITE);
		info.setOpaque(false);
		
		
		caja.setEditable(false);
		//caja.setForeground(Color.red);
		scroll.setBounds(30,70,300,300);
	
		
		this.add(titulo);
		this.add(boton);
		this.add(zumbido);
		this.add(info);
		this.add(scroll);
		this.add(Pfondo);
		
		
		
			}
	public void cliente() {
		this.setLayout(new GridLayout(1, 1, 1, 1));
				
	}
	
	public void zumbido(){
	
		int x=0,y=0;
		try {
			
			 x=this.getX();y=this.getY()-28;
			
			try {
				zumbidos.close();
				
			} catch (Exception e) {
				
			}
			 zumbidos.play();
		 for (int i = 0; i < 5; i++) {
			 this.setLocation(x+10,y+30);
					Thread.sleep (80);
			 this.setLocation(x+30,y+30);
					Thread.sleep (80);
			 this.setLocation(x+30,y+10);
					Thread.sleep (80);
			 this.setLocation(x+10,y+30);
		}
		 
		} catch (Exception e) {
			
		}
		 this.setLocation(x,y);
		 
		 caja.setText(caja.getText()+"Has recibido un zumbido        \n");	
		 
		
	}
	
public void cerrarsesion(){
		
		try {
			dsalida.close();
			dentrada.close();
		    cliente.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
@Override
public void run() {
	Thread ct= Thread.currentThread();
	if(ct==hilocaja){
		try {
			
			do{
				
			recibido = dentrada.readUTF();
			     
			if(recibido.equalsIgnoreCase("zuuuumbido"))zumbido();
			else{char[] mensake=recibido.toCharArray(); 
		
				caja.setText(caja.getText()+recibido);
				if(mensake.length!=20){
			 try {
					msm.close();
					
				} catch (Exception e) {
					
				}
				 msm.play();
			}	 
			}
	}while(true);
		} catch (Exception e) {
			caja.setText(caja.getText()+"ERROR AL RECIBIR DATO \n ");
			
		}
	
	}

}		

	public static void main(String[] args) {
		Cliente obj= new Cliente();
	}
	
}
