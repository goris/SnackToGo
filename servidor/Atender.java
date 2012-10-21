import java.io.*;
import java.net.*;

class Atender extends Thread {
	private BufferedReader entrada;
	private DataOutputStream salida;
	private String orden;
	private Socket cliente=null;
	DatosSocket dSocket=null;
	private String nombre;
	private String articulo;
	private String cantidad;
	private String fechaEntrega;
	private String json;
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
		do{
			orden = entrada.readLine();
			System.out.println(orden);
			if(orden.equals("articulos")){
				System.out.println("en articulos");
			}
			//nombre=json.getString("mail");
			System.out.println("es de "+dSocket.toString());
     		salida.writeInt(orden.length());
		}while(orden.length()!=0);
		
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