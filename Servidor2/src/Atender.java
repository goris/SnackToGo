import java.io.*;
import java.net.*;
import java.util.LinkedList;
import com.google.gson.Gson;
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
	private Gson gson =new Gson();
	LinkedList<Articulo> listaArticulo=new LinkedList<Articulo>();
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
		orden = entrada.readLine();
		while(orden!=null&&orden.length()!=0){
			System.out.println("mensajes de entrada"+orden);
			if(orden.equals("articulos")){
				listaArticulo=(new BaseDatos()).pedir();
				json=gson.toJson(listaArticulo)+"\n"; 
				salida.writeBytes(json);
				System.out.println(json);
				System.out.println("mensajes de entrada="+orden);
			}else{
		     	salida.writeBytes("looool");
			}
			System.out.println("es de "+dSocket.toString());
			orden = entrada.readLine();
		}
		
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