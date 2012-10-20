import java.io.*;
import java.sql.*;
import java.util.LinkedList;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;


class BaseDatos{
	private static Connection conect;//inicio de conexion
	private static PreparedStatement pstmt=null;
	private static String url="jdbc:mysql://localhost:8889/snack-to-go";
	private static String usuario="root";
	private static String clave="root";
	private static boolean actualiza=true; 
	public static boolean actualizar(Pedido pedido){
		try{
		conect=DriverManager.getConnection(url,usuario,clave);
		pstmt=conect.prepareStatement("INSERT INTO LineasPedido VALUES(NULL,) ");
		pstmt=conect.prepareStatement("INSERT INTO Pedidos VALUES(NULL,) ");
		
		}catch(SQLException e){
			System.out.println("se te frego por:"+e.getMessage());
			actualiza=false;
		}finally{
			try{
			pstmt.close();
		}catch(java.sql.SQLException sql){
			System.out.println("se te frego por:"+sql.getMessage());
		}
			return actualiza;
		}
	}	
}
