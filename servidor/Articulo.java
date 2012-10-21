class Articulo{
	private String nombre=null;
	private int id=0;
	private String descripcion=null;
	private double precio=0;
	public Articulo(){
		
	}
	public Articulo(String nombre,String descripcion,int id,double precio){
		nombre=this.nombre;
		id=this.id;
		descripcion=this.descripcion;
		precio=this.precio;
		
	}
	public String getDescripcion(){
		return this.descripcion;
	}
	public void setDescripcion(String descripcion){
		this.descripcion=descripcion;
	}
	public int getId(){
		return this.id;
	}
	public void setId(int id){
		this.id=id;
	}
	public String getNombre(){
		return this.nombre;
	}
	
	public void setNombre(String nombre){
		this.nombre = nombre;
	}
	public double getPrecio(){
		return this.precio;
	}
	public void setPrecio(double precio){
		this.precio=precio;
	}

}