import java.util.Date;
class Pedido{
	private String usuario=null;
	private Date fecha=null;
	private int id=0;
	private String estado=null;
	private Date entrega=null;
	public Pedido(){
		
	}
	public Pedido(Date entrega,String usuario,Date fecha,String estado,int id){
		usuario=this.usuario;
		fecha=this.fecha;
		id=this.id;
		estado=this.estado;
		entrega=this.entrega;
		
	}
	public String getEstado(){
		return this.estado;
	}
	public void setEstado(String estado){
		this.estado=estado;
	}
	public int getId(){
		return this.id;
	}
	public void setId(int id){
		this.id=id;
	}
	public Date getEntrega(){
		return this.entrega;
	}
	public void setEntrega(Date entrega){
		this.entrega=entrega;
	}
	
	public String getUsuario(){
		return this.usuario;
	}
	
	public void setUsuario(String usuario){
		this.usuario = usuario;
	}
	
	public Date getFecha(){
		return this.fecha;
	}
	
	public void setFecha(Date fecha){
		this.fecha=fecha;
	}
}