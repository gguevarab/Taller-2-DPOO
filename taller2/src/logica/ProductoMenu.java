package logica;

public class ProductoMenu implements Producto{
	
	private final String nombre;
	private final int precioBase;
	
	public ProductoMenu(String nombre, int precioBase) {
		this.nombre = nombre;
		this.precioBase = precioBase;
	}

	@Override
	public int getPrecio() {
		// TODO Auto-generated method stub
		return this.precioBase;
	}

	@Override
	public String getNombre() {
		// TODO Auto-generated method stub
		return this.nombre;
	}

}
