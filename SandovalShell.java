import javax.swing.*;
import java.awt.Color;
import java.awt.event.*;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

public class SandovalShell extends JFrame{
	
	//elementos gráficos
	JTextField tComando;
	JButton bEjecutar;
	JTextArea tResultado;
	JScrollPane sPane;

	//oyente de click de botón
	ActionListener alEjecutar;

	public SandovalShell(){
		setSize(700,600);
		setTitle(System.getProperty("os.name"));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	private void graficos(){
		getContentPane().setLayout(null);
		//cuadro de texto
		tComando = new JTextField();
		tComando.setBounds(50,50,250,30);
		add(tComando);
		//botón para ejecutar comando
		bEjecutar = new JButton("Ejecutar");
		bEjecutar.setBounds(350,50,150,30);
		add(bEjecutar);
		bEjecutar.addActionListener(alEjecutar);

		//área de texto
		tResultado = new JTextArea();
		tResultado.setBounds(50,130,600,370);
		tResultado.setBackground(Color.BLACK);
		tResultado.setForeground(Color.GREEN);
		//scroll pane
		sPane = new JScrollPane(tResultado);
		sPane.setBounds(50,120,500,400);
		add(sPane);
		//
		setVisible(true);
	}

	private void acciones(){
		alEjecutar = new ActionListener(){
			public void actionPerformed(ActionEvent e){
				ejecutar();
			}
		};
	}

	private void ejecutar(){

		Process proc; 
		InputStream is_in;
		String s_aux;
		BufferedReader br;

		try
		{
			
			
			
			Process p =  new  ProcessBuilder ( "cmd.exe" ,  "/C" , tComando.getText() ).redirectErrorStream ( true).start();
      InputStream es = p . getInputStream ();
      BufferedReader brr =  new  BufferedReader (new  InputStreamReader ( es ));
      String in;
      while(( in = brr . readLine ()) !=  null )  {
     
            	tResultado.setText(tResultado.getText()+in+"\n");
             
            } 
		}
		catch(Exception e)
		{
			System.out.println(e);
		}


	}

	public static void main(String args[]){
		SandovalShell ventana = new SandovalShell();
		ventana.acciones();	
		ventana.graficos();	
	}

}
