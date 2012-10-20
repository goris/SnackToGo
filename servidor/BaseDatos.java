import java.io.*;
import java.sql.*;
import java.util.LinkedList;

class BaseDatos{
	Pedido pedido;
	private Connection conect;//inicio de conexion
	private PreparedStatement pstmt=null;
	private String url="jdbc:mysql://localhost:8889/snack-to-go";
	private String usuario="root";
	private String clave="root";
	public Boolean borrar(Pedido pedido){
		conect=DriverManager.getConection(url,usuario,clave);
		stmt=conect.createStatement();
		pstmt=conect.prepareStatement("DELETE FROM ")
	}	
}
