
import java.sql.*;

public class LineaPedido {
	
	private int cantArticulo;
	private int idArticulo;
	private double precio;
	private int idPedido;
	
	Connection conect = (new BaseDatos()).conection();
	
	public LineaPedido(){}
	public LineaPedido(int cantArticulo, int idArticulo, double precio, int idPedido){
		this.setCantArticulo(cantArticulo);
		this.setIdArticulo(idArticulo);
		this.setPrecio(precio);
		this.setIdPedido(idPedido);
	}
	
	public void create(){
		PreparedStatement pstmt=null;
		try{
			pstmt=conect.prepareStatement("INSERT INTO `lineaspedido`" +
					"(`id`, `pedido`, `articulo`, `cantidad`, `precio`)" +
					" VALUES (null,"+this.idPedido+","+this.idArticulo+"," +
					this.cantArticulo+","+this.precio+")");
			ResultSet rset = pstmt.executeQuery();
			if( !rset.next() ){
				System.out.println("NO SE GUARDO");
			}
		} catch ( SQLException e){
			System.out.println("ERROR: "+e.getMessage());
		}
	}
	
	public int getCantArticulo() {
		return cantArticulo;
	}
	public void setCantArticulo(int cantArticulo) {
		this.cantArticulo = cantArticulo;
	}
	public int getIdArticulo() {
		return idArticulo;
	}
	public void setIdArticulo(int idArticulo) {
		this.idArticulo = idArticulo;
	}
	public double getPrecio() {
		return precio;
	}
	public void setPrecio(double precio) {
		this.precio = precio;
	}
	public int getIdPedido() {
		return idPedido;
	}
	public void setIdPedido(int idPedido) {
		this.idPedido = idPedido;
	}
	
}
