import java.net.*;
class DatosSocket{
	InetAddres ipLocal=null;
	InetAddres ipCliente=null;
	int puertoLocal=0;
	int puertoCliente=0;
	public DatosSocket(Socket socket){
		ipLocal=socket.getLocalAddres();
		ipCliente=socket.getInetAddress();
		puertoLocal=socket.getLocalPort();
		puertoCliente=socket.getPort();
	}
	public String toString(){
		String s="Cliente: "+ipCliente.getHostAddress()+"/"+puertoCliente;
		s=s+"Local: "+ipLocal.getHostAddress()+"/"+puertoLocal;
		return s;
	}
	
}