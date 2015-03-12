import javax.swing.*;
import java.awt.Color;
import java.awt.event.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class Shell extends JFrame{
	
	//elementos gráficos
	JTextField tComando;
	JButton bEjecutar;
	JTextArea tResultado;
	JScrollPane sPane;

	//oyente de click de botón
	ActionListener alEjecutar;

	public Shell(){
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

		Process proc = null; 
		InputStream is_in;
		String s_aux;
		BufferedReader br;
		 if(tComando.getText().equalsIgnoreCase("clear")||tComando.getText().equalsIgnoreCase("cls")){tResultado.setText("");}
	      else{
		try
		{
			
			try {
				proc = Runtime.getRuntime().exec(tComando.getText());
			} catch (IOException e1) {
				proc = Runtime.getRuntime().exec("cmd.exe /C"+tComando.getText());
			}
			
			is_in=proc.getInputStream();
			br=new BufferedReader (new InputStreamReader (is_in));
			s_aux = br.readLine();
	        while (s_aux!=null)
	        {
	        	tResultado.setText(tResultado.getText()+s_aux+"\n");
	            s_aux = br.readLine();
	        } 
      } catch (Exception e) {
	
        }
	      }
	 }

	public static void main(String args[]){
		Shell ventana = new Shell();
		ventana.acciones();	
		ventana.graficos();	
	}

}