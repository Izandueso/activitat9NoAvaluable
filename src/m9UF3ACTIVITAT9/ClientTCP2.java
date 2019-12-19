package m9UF3ACTIVITAT9;

import java.net.*;
import java.io.*;

public class ClientTCP2 implements Runnable{
	
	private static Socket cliente;
	
	public ClientTCP2(Socket cliente){
		this.cliente = cliente;
	}
	
	public static void main (String[] args) throws Exception {
		
		
		// **********SEGONA PART**********
		String host = "localhost";
		int port = 60000;//Port remot
		Socket client = new Socket(host, port);
		
		//FLUX DE SORTIDA AL SERVIDOR
		PrintWriter fsortida = new PrintWriter(client.getOutputStream(), true);
		
		//FLUX D'ENTRADA AL SERVIDOR
		BufferedReader fentrada = new BufferedReader(new InputStreamReader(client.getInputStream()));
		
		//FLUX PER A ENTRADA ESTÀNDARD
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		
		String cadena, eco = "";
		String nom , eco2= "";
		System.out.println("Introdueix nom:");
		nom = in.readLine();
		
		Runnable run = new ClientTCP2(cliente);
		Thread enviaralServidor = new Thread(run);
		
		
		
		System.out.println("Introdueix la cadena: ");
		//Lectura teclat
		cadena = in.readLine();
		
		while (cadena != null && !cadena.equals("") && !nom.equals("")) {
			
			//enviament del nom al servidor
			fsortida.println(nom);
			//Enviament cadena al servidor
			fsortida.println(cadena);
			//rebuda del nom al servidor;
			eco2 = fentrada.readLine();
			//Rebuda cadena del servidor
			eco = fentrada.readLine();
			System.out.println("  =>ECO: "+eco2);
			//Lectura del teclat
			nom = in.readLine();	
			System.out.println("  =>ECO: "+eco);
			//Lectura del teclat
			cadena = in.readLine();	
			
		}
		fsortida.close();
		fentrada.close();
		System.out.println("Finalització de l'enviament...");
		in.close();
		client.close();
	}
	
	@Override
	public void run() {

		try {
			
			BufferedReader fentrada = new BufferedReader(new InputStreamReader(cliente.getInputStream()));
			
			

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
