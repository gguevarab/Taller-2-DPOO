package logica;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Restaurante {
	
	private ArrayList<ArrayList<Producto>> comboItemsMayor = new ArrayList<>(); 
	private ArrayList<Ingrediente> ingredientes;
	private ArrayList<Producto> productos;
	private ArrayList<Producto> bebidas;
	private ArrayList<Combo> combos;
	private Pedido pedido;
	
	
	public Restaurante() {
		cargarInformacionRestaurante();
	}
	
	
	
	public void iniciarPedido(String nombre, String direccionCliente) {
		
		Pedido pedido = new Pedido(nombre, direccionCliente);
		this.pedido = pedido;
		
	}
	
	

	public void cerrarYGuardarPedido() {
		this.pedido.guardarFactura();
	}
	
	
	
	public Pedido getPedidoEnCurso() { 
		return pedido;
	}
	
	
	
	public ArrayList<Producto> getMenuBase(){
		return this.productos;
	}
	
	
	
	public ArrayList<Ingrediente> getIngredientes(){
		return this.ingredientes;
	}
	
	
	
	public ArrayList<Combo> getCombos(){
		return this.combos;
	}
	
	
	public ArrayList<ArrayList<Producto>> getCombosProductos(){
		return this.comboItemsMayor;
	}
	
	
	
	public ArrayList<Producto> getBebidas(){
		return this.bebidas;
	}
	
	
	//Abrir archivos y carga de datos general
	private void cargarInformacionRestaurante() {
		
		File archivoIngredientes = new File("./data/ingredientes.txt");
		cargarIngredientes(archivoIngredientes);
		
		File archivoBebidas = new File("./data/bebidas.txt");
		cargarBebidas(archivoBebidas);
		
		File archivoMenu = new File("./data/menu.txt");
		cargarMenu(archivoMenu);
		
		File archivoCombos = new File("./data/combos.txt");
		cargarCombos(archivoCombos);
		
	}
	
	
	
	private void cargarIngredientes(File archivoIngredientes) {
		
		ArrayList<Ingrediente> ingredientes = new ArrayList<>();
		
		try(Scanner sc = new Scanner(archivoIngredientes)){
			while(sc.hasNextLine()) {
				String[] item = sc.nextLine().split(";");
				ingredientes.add(new Ingrediente(item[0], Integer.parseInt(item[1])));
			}
			this.ingredientes = ingredientes;
		} catch (FileNotFoundException e) {
			System.out.println("No se econtro el archivo especificado");
		}
		
	}
	
	
	
	private void cargarMenu(File archivoMenu) {
		
		ArrayList<Producto> productos = new ArrayList<>();
		
		try(Scanner sc = new Scanner(archivoMenu)){
			while(sc.hasNextLine()) {
				String[] item = sc.nextLine().split(";");
				productos.add(new ProductoMenu(item[0], Integer.parseInt(item[1])));
			}
			this.productos = productos;
		} catch (FileNotFoundException e) {
			System.out.println("No se econtro el archivo especificado");
		}
		
	}
	
	
	private void cargarBebidas(File archivoBebidas) {
		
		ArrayList<Producto> bebidas = new ArrayList<>();
		
		try(Scanner sc = new Scanner(archivoBebidas)){
			while(sc.hasNextLine()) {
				String[] item = sc.nextLine().split(";");
				bebidas.add(new ProductoMenu(item[0], Integer.parseInt(item[1])));
			}
			this.bebidas = bebidas;
		} catch (FileNotFoundException e) {
			System.out.println("No se econtro el archivo especificado");
		}
		
	}
	
	
	
	private void cargarCombos(File archivoCombos) {

		ArrayList<Combo> combos = new ArrayList<>();
		
		try(Scanner sc = new Scanner(archivoCombos)){
			while(sc.hasNextLine()) {
				ArrayList<Producto> comboItems = new ArrayList<>();
				String[] item = sc.nextLine().split(";");
				item[1] = item[1].substring(0, item[1].length()- 1); //Eliminar el '%' al final
				
				//Se agrega cada elemento de cada combo a una lista la cual se almacena en otra lista. Basicamente una lista 2D con los combos en el eje x y sus ingredientes en el y.
				for(int x=0; x < item.length - 2; x++) {
					for(Producto p: productos) {
						if (p.getNombre().equals(item[x + 2])) {
							comboItems.add(p);
							break;
						}
					}
					for(Producto b: bebidas) {
						if (b.getNombre().equals(item[x + 2])) {
							comboItems.add(b);
							break;
						}
					}
				}
				comboItemsMayor.add(comboItems);
				combos.add(new Combo(item[0], Float.parseFloat(item[1])));
			}
			this.combos = combos;
		} catch (FileNotFoundException e) {
			System.out.println("No se econtro el archivo especificado");
		}
		
	}
	
	
	
}
