import java.io.*;
import java.net.*;

class Atender extends Thread {
	private BufferedReader entrada;
	private DataOutputStream salida;
	private Socket cliente=null;
	DatosSocket dSocket=null;
	public Atender(Socket cliente){
		this.cliente=cliente;//creo un nuevo cliente 
		dSocket=new DatosSocket(cliente);//obtengo todos los datos del Cliente
		System.out.println("ahuevo se conecto este cabron >"+dSocket.toString());
	}
	public void run(){
	try{
		entrada=new BufferedReader(new InputStreamReader(cliente.getInputStream()));
		salida=new DataOutputStream(cliente.getOutputStream());
		/*
			aqui es donde ocurrira la magia de platicar entre cliente y servidor 
		*/
		Pedido pedido=null;
		boolean actualizar=BaseDatos.actualizar(pedido);
	}catch(SocketException se)	{
		//evito que lo truenen con ctr-c
		System.out.println("cabron checa que te quieren tronar");
	}catch(IOException e){
		System.out.println("no se recibio bien el packetongo"+e.getMessage());
		e.printStackTrace();
	}
	try{
		entrada.close();
		salida.close();
		cliente.close();
	}catch(IOException e){
		System.out.println("fallo al cerrar:"+e.getMessage());
	}
	System.out.println("se termino el pedido"+dSocket.toString());
	}
}