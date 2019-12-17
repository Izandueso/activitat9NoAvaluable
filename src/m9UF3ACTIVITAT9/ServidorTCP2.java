package m9UF3ACTIVITAT9;

import java.net.*;
import java.util.Scanner;
import java.io.*;

public class ServidorTCP2 implements Runnable{
	String cadena;
	ServerSocket server;
	int numClient;
	Socket client;
	public ServidorTCP2(Socket cliente, ServerSocket server){
		this.server = server;
		this.numClient++;
		this.client = cliente;
	}

	public static void main (String[] args) throws Exception {

		// **********SEGONA PART**********
		Scanner teclado = new Scanner(System.in);


		int numPort = 60000;
		ServerSocket servidor = new ServerSocket(numPort);
		String cadena = "";


		// Demanem el numClients per a indicar 
		// el max de clients que rebra el servidor
		System.out.println("Introdueix el num de clients que podra rebre el servidor: ");
		int numClients = teclado.nextInt();

		// Determinem les vegades que es conectaran els clients
		for (int i = 0; i < numClients; i++) {
			
			Runnable[] arrayRunnable = new Runnable[10];
			Thread[]arrayThread = new Thread[10];
			
			Socket clientConnectat = null;
			PrintWriter fsortida = null;
			BufferedReader fentrada = null;

			System.out.println("Esperant connexió... ");
			clientConnectat = servidor.accept();
			System.out.println("Client " + (i+1) + " connectat... ");

			//FLUX DE SORTIDA AL CLIENT
			fsortida = new PrintWriter(clientConnectat.getOutputStream(), true);


			//FLUX D'ENTRADA DEL CLIENT
			fentrada = new BufferedReader(new InputStreamReader(clientConnectat.getInputStream()));

			
			while ((cadena = fentrada.readLine()) != null) {

				fsortida.println(cadena);
				System.out.println("Rebent: "+cadena);
				if (cadena.equals("*")) break;

			}
			fentrada.close();
			fsortida.close();
			clientConnectat.close();
		}


		//TANCAR STREAMS I SOCKETS
		System.out.println("Tancant connexió... ");
		//		fentrada.close();
		//		fsortida.close();
		//		clientConnectat.close();
		servidor.close();

	}

	@Override
	public void run() {
		try {
			
			//FLUX DE SORTIDA AL CLIENT
			
			PrintWriter fsortida = new PrintWriter(this.client.getOutputStream(), true);
			
			//FLUX D'ENTRADA DEL CLIENT
			
			BufferedReader fentrada = new BufferedReader(new InputStreamReader(this.client.getInputStream()));

			fsortida.println("Client: " + this.numClient);
			
			while ((cadena = fentrada.readLine()) != null) {
				
				fsortida.println(cadena);
				System.out.println("Rebent: "+cadena);
				if (cadena.equals("*"))
					
				break;
				
			}

			//TANCAR STREAMS I SOCKETS
			System.out.println("Tancant connexió... ");
			
			fsortida.close();
			fentrada.close();
			this.server.close();
			this.client.close();

		} catch (IOException e) {

			e.printStackTrace();
			
		}

	}

}