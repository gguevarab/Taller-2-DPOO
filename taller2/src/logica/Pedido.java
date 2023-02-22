package logica;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class Pedido {
	
	private int numeroPedidos;
	private int idPedido = (int) (Math.random() * 9999);
	private String nombreCliente;
	private String direccionCliente;
	private ArrayList<Producto> productosPedido = new ArrayList<>();
	private ArrayList<Ingrediente> ingredientesPedido = new ArrayList<>(); 
	private int costo = 0;
	
	public Pedido(String nombreCliente, String direccionCliente) {

		this.nombreCliente = nombreCliente;
		this.direccionCliente = direccionCliente;
		
	}
	
	public int getIdPedido() {
		return idPedido;
	}
	
	public void agregarProducto(Producto nuevoItem) {
		this.productosPedido.add(nuevoItem);
		System.out.println("\nSe ha añadido: " + nuevoItem.getNombre());
		costo += nuevoItem.getPrecio();
		System.out.println(generarTextoFactura());
	}
	
	public void agregarIngrediente(Ingrediente nuevoItem) {
		this.ingredientesPedido.add(nuevoItem);
		System.out.println("\nSe ha añadido: " + nuevoItem.getNombre());
		costo += nuevoItem.getCostoAdicional();
		System.out.println(generarTextoFactura());
	}
	
	public void agregarCombo(ArrayList<Producto> combo, float descuento) {
		for(Producto p: combo) {
			this.productosPedido.add(p);
			costo += p.getPrecio() - p.getPrecio()*descuento/100;
		}
		System.out.println("Se aplica descuento de " + descuento + "% en productos seleccionados");
		System.out.println(generarTextoFactura());
	}
	
	private int getPrecioNetoPedido() {
		return (int) (costo + (costo * 0.19));
	}
	
	private int getPrecioTotalPedido() {
		return costo;
	}
	
	private int getPrecioIVAPedido() {
		//No sabia a que se referia exactamente entonces sencillamente puse el IVA actual al preico base ;p
		return (int) (costo * 0.19);
	}
	
	private String generarTextoFactura() {
		String textoFactura = "\nProductos en la factura: \n";
		for(Producto p: productosPedido) {
			textoFactura += p.getNombre() + "\t\tPrecio: " + p.getPrecio() + "\n";
		}
		
		textoFactura += "\nIngredientes adicionales: \n";
		for(Ingrediente i: ingredientesPedido) {
			textoFactura += i.getNombre() + "\t\tPrecio Extra: " + i.getCostoAdicional() + "\n";
		}
		
		//Mis conocimientos en economia estan tan mal que no se la diferencia entre valor neto y valor total :)
		textoFactura += 
				"\nIVA: " + getPrecioIVAPedido() +
				"\nPrecio Total: " + getPrecioTotalPedido() +
				"\nPrecio Neto: " + getPrecioNetoPedido();
		
		return textoFactura;
	}
	
	public void guardarFactura() {
		
		try {
			FileWriter genFactura = new FileWriter("facturas/" + idPedido + ".txt");
			genFactura.write("ID Pedido: " + idPedido +
							"\nNombre Cliente: " + nombreCliente + 
							"\nDirección Cliente: " + direccionCliente + "\n" +
							generarTextoFactura());
			
			genFactura.close();
			System.out.println("Se creo satisfactoriamente la factura con ID " + idPedido);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}

}
