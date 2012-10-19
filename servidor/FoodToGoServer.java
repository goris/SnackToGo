import java.io.*;
import java.net.*;

class FoodToGoServer{
	public static void main(String[] args) {
		ServerSocket servidor=null;
		Socket cliente=null;
		boolean escuchando=true;
		final int PUERTO=6666;
		//el servidor empesara a escuchar por el puerto 6666
		try{
			servidor=new ServerSocket(PUERTO);
			System.out.println("ahuevo estoy escuchando en el puerto"+PUERTO);
		}catch(IOException e){
			System.out.println("error al intenar abrir el socket.. dio el error"+e.getMessage());
			System.exit(1);
		}
		//un ciclo infinito donde mandare llamar a atender y hay creare un hilo para cada una de las peticiones papawn
		while(escuchando){
			try{
				cliente=servidor.accept();
			}catch(IOException e){
				System.out.println("sas trono al llegar una peticion con este mensaje dude:"+e.getMessage());
				cliente=null;
			}
			if(cliente!=null){
				new Atender(cliente).start();
			}
		}try{
			servidor.close();
		}catch(IOException e){
			System.out.println("arregla el desmadre que causaste al cerrar pinche juve:"+e.getMessage());
		}
	}
}