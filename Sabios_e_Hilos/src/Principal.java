
import java.awt.GridLayout; 
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Random;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Principal extends JFrame implements Runnable{
	
    JPanel[] pimagenes=new JPanel[5];
    Imagenes ima;
	JLabel[] etqs=new JLabel[5];
	Thread[] hilos=new Thread[5];
	String pensando,comiendo,esperando;
	
	public Principal(){
		pensando="pensando";
		comiendo="comiendo";
		esperando="esperando";
		addWindowListener(
				new WindowAdapter(){
				public void windowClosing(WindowEvent we){
					
					System.exit(0);// salir del programa
				}});
		setSize(800,430);
		setLocation(200,200);
		relleno();
		setVisible(true);	
	}
	
	public void relleno(){
		this.setLayout(new GridLayout(1, 1, 1, 1));
		JPanel pcentro=new JPanel();
		pcentro.setLayout(null);
		for (int i = 0; i < pimagenes.length; i++){
		pimagenes[i]=new JPanel();
		pimagenes[i].setLayout(new GridLayout(1,1,1,1));
		}
		pimagenes[1].setBounds(570, 190, 170, 100);
		pimagenes[0].setBounds(410, 50, 170, 100);
		pimagenes[2].setBounds(310, 290, 170, 100);
		pimagenes[3].setBounds(90, 190, 170, 100);
		pimagenes[4].setBounds(180, 50, 170, 100);
		JPanel fondo=new JPanel();
		
		for (int i = 0; i < pimagenes.length; i++) {ima=new Imagenes(pensando+".jpg");pimagenes[i].add(ima);pcentro.add(pimagenes[i]);}
		fondo.setLayout(new GridLayout(1,1,1,1));
		fondo.setBounds(0,0,800,430);
		ima=new Imagenes("portada.jpg");
		fondo.add(ima);
		pcentro.add(fondo);
		for (int i = 0; i < etqs.length; i++){
			etqs[i]=new JLabel(pensando);
			etqs[i].setBounds(i*100, 100, 100, 50);
			
			
			hilos[i]=new Thread(this);
			hilos[i].start();
		  
		//	pcentro.add(etqs[i]);
		}	
		
	this.add(pcentro);			
	}
	
	public void comer(int pos){
		int ant=pos-1;
		int sig=pos+1;
		if(pos==0){ant=4;sig=1;}
		if(pos==4)sig=0;
		if(etqs[ant].getText().equalsIgnoreCase(comiendo)||etqs[sig].getText().equalsIgnoreCase(comiendo)){
			etqs[pos].setText(esperando); 
		}
		
		else {etqs[pos].setText(comiendo);
		}
		return ;
	}
	
	@Override
	public void run() {
		Thread ct= Thread.currentThread();
	
			for (int i = 0; i < hilos.length; i++) {
							
		while(hilos[i]==ct){
			Random rnd=new Random();
			int prob = (int)(rnd.nextDouble() * 20.0)+5;
		
			if(!etqs[i].getText().equalsIgnoreCase(esperando))esperar(prob,i);
			comer(i);
			
			if(!etqs[i].getText().equalsIgnoreCase(esperando))esperar(prob,i);
		    if(!etqs[i].getText().equalsIgnoreCase(esperando))etqs[i].setText(pensando);
			if(!etqs[i].getText().equalsIgnoreCase(esperando))esperar(prob,i);
		}
			}
	}
	
	
	public void esperar (int segundos,int hilo) {
		try {
			Random rnd=new Random();
			int rango = (int)(rnd.nextDouble() * 1000);	
		Thread.sleep (segundos*1000+rango);
		for (int i = 0; i < etqs.length; i++) {
				ima=new Imagenes(etqs[i].getText()+".jpg");
		    	pimagenes[i].removeAll();
				pimagenes[i].add(ima);
				pimagenes[i].updateUI();  //Mira si hay cambios
				pimagenes[i].repaint();
			}
		this.repaint();
		} catch (Exception e) {
		
		}
		}
	
	public static void main(String[] args) {
		Principal obj=new Principal();
	}


}
