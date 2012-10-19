class Atender extends Thread {
	private BufferedReader entrada;
	private BufferedReader salida;
	private Socket cliente=null;
	DatosSocket dSocket=null;
	public Atiende(Socket cliente){
		this.cliente=cliente;//creo un nuevo cliente 
		dSocket=new DatosSocket(cliente);//obtengo todos los datos del Cliente
		System.out.println("ahuevo se conecto este cabron >"+dSocket.toString());
	}
	public void run(){
		
	}
}