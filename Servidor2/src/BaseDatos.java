import java.io.*;
import java.sql.*;
import java.util.LinkedList;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;


class BaseDatos{
	private static String url="jdbc:mysql://localhost:8889/snack-to-go";
	private static String usuario="root";
	private static String clave="root";
	
	public Connection conection(){
		Connection conect = null;
		try{
			conect=DriverManager.getConnection(url,usuario,clave);
		}catch(SQLException e){
			System.out.println("ERROR: "+e.getMessage());
		}
		return conect;
	}
	
	/*----------------------------------------------
	 * ---------------------------------------------
	 * -------NO SE MODIFICA ESTO PERRAS------------
	 * ---------------------------------------------
	 * ---------------------------------------------
	 */
	public LinkedList<Articulo> pedir(){
		LinkedList<Articulo> listaArticulo=new LinkedList<Articulo>();
		PreparedStatement pstmt=null;
		try{
		Connection conect=DriverManager.getConnection(url,usuario,clave);
		pstmt=conect.prepareStatement("SELECT * FROM articulos");
		ResultSet rset=pstmt.executeQuery();
		while(rset.next()){
			Articulo articulo=new Articulo();
			articulo.setId(rset.getInt("id"));
			articulo.setNombre(rset.getString("nombre"));
			articulo.setPrecio(rset.getDouble("precio"));
			articulo.setDescripcion(rset.getString("descripcion"));
			listaArticulo.add(articulo);
		}
		
		}catch(SQLException e){
			System.out.println("se te frego por:"+e.getMessage());
		}finally{
			try{
			pstmt.close();
		}catch(java.sql.SQLException sql){
			System.out.println("se te frego por:"+sql.getMessage());
		}
			return listaArticulo;
		}
	}
	/*----------------------------------------------
	 * ---------------------------------------------
	 * ---------------------------------------------
	 * ---------------------------------------------
	 * ---------------------------------------------
	 */
}
