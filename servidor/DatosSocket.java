import java.net.*;

class DatosSocket{
	InetAddress ipLocal=null;
	InetAddress ipCliente=null;
	int puertoLocal=0;
	int puertoCliente=0;
	public DatosSocket(Socket socket){
		ipLocal=socket.getLocalAddress();
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