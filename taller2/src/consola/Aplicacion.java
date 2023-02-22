package consola;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Scanner;

import logica.Combo;
import logica.Ingrediente;
import logica.Pedido;
import logica.Producto;
import logica.Restaurante;

public class Aplicacion {

	
	
	public static void main(String[] args) {
		
		System.out.println("\tBienvenido a Los Pollos Hermanos\t");
		System.out.println("¿Cual sera el dia de hoy? ;)");
		System.out.println("1)Nuevo Pedido\n2)Buscar pedido por id\n3)Salir");
		BufferedReader inp = new BufferedReader (new InputStreamReader(System.in));
		String opt = "0";
		try {
			opt = inp.readLine();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		switch(opt) {
			case "1": try {
				mostrarMenu();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} break;
			case "2": try {
				ejecutarBusqueda();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} break;
			case "3": System.exit(0); break;
			default: System.out.println("Ingrese un valor valido en la proxima sesión"); System.exit(0);
		}
	}

	
	
	public static void mostrarMenu() throws IOException {
		
		Restaurante restaurante = new Restaurante();
		
		ArrayList<Producto> productos = restaurante.getMenuBase();
		ArrayList<Producto> bebidas = restaurante.getBebidas();
		ArrayList<Ingrediente> ingredientes = restaurante.getIngredientes();
		ArrayList<Combo> combos = restaurante.getCombos();
		ArrayList<ArrayList<Producto>> combosItems = restaurante.getCombosProductos();
		
		System.out.print("Ingrese el nombre del cliente: ");
		BufferedReader nomCli = new BufferedReader (new InputStreamReader(System.in));
		String nombreCliente = nomCli.readLine();
		System.out.print("Ingrese la dirección del cliente: ");
		BufferedReader dirCli = new BufferedReader (new InputStreamReader(System.in));
		String direccionCliente = dirCli.readLine();
		restaurante.iniciarPedido(nombreCliente, direccionCliente);
		Pedido pedido = restaurante.getPedidoEnCurso();
		
		while(true) {
			System.out.println("1)Comida\n2)Bebidas\n3)Ingredientes Extra\n4)Combos");
			
			BufferedReader inp = new BufferedReader (new InputStreamReader(System.in));
			String opt = "0";
			opt = inp.readLine();

			
			System.out.println("Escoja el elemento a añadir: ");
		
			switch(opt) {
				//Comida
				case "1": for(Producto p: productos) {
					System.out.println((productos.indexOf(p) + 1) + ") " + p.getNombre() + "\t" + p.getPrecio());
				}; break;
				
				//Bebidas
				case "2": for(Producto b: bebidas) {
					System.out.println((bebidas.indexOf(b) + 1) + ") " + b.getNombre() + "\t" + b.getPrecio());
				}; break;
				
				//Ingredientes extra
				case "3": for(Ingrediente i: ingredientes) {
					System.out.println((ingredientes.indexOf(i) + 1) + ") " + i.getNombre() + "\t" + i.getCostoAdicional());
				}; break;
				
				//Combos
				case "4": for(Combo c: combos) {
					System.out.println((combos.indexOf(c) + 1) + ")" + c.getNombre());
				};
				break;
				default: ;
			}
			
			BufferedReader inp1 = new BufferedReader (new InputStreamReader(System.in));
			int productNum = 0;
			productNum = Integer.parseInt(inp1.readLine());
			
			switch (opt) {
			case "1": pedido.agregarProducto(productos.get(productNum - 1)); break;
			case "2": pedido.agregarProducto(bebidas.get(productNum - 1)); break;
			case "3": pedido.agregarIngrediente(ingredientes.get(productNum - 1)); break;
			case "4": pedido.agregarCombo(combosItems.get(productNum - 1), combos.get(productNum - 1).getDescuento()); break;
			default: 
			}
			
			System.out.println("\n¿Desea agregar algo al pedido? (y/n)");
			
			BufferedReader inp2 = new BufferedReader (new InputStreamReader(System.in));
			String addP = "n";
			addP = inp2.readLine();
			
			if(!addP.equals("y")) {
					break;
			}
		}
		
		System.out.println("Gracias por su compra! :D");
		restaurante.cerrarYGuardarPedido();
	}
	
	
	
	public static void ejecutarBusqueda() throws IOException {
		
		System.out.println("Ingrese por favor el ID de la factura a buscar; ");
		BufferedReader inp = new BufferedReader (new InputStreamReader(System.in));
		String codigoID = inp.readLine();
		
		try {
		      File file = new File("facturas/" + codigoID + ".txt");
		      Scanner reader = new Scanner(file);
		      while (reader.hasNextLine()) {
		        String data = reader.nextLine();
		        System.out.println(data);
		      }
		      reader.close();
		    } catch (FileNotFoundException e) {
		      System.out.println("An error occurred.");
		      e.printStackTrace();
		    }
		
	}

}
