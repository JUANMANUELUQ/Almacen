package co.uniquindio.almacen.model;

import java.util.List;
import java.util.ArrayList;

public class Almacen {
	
	// variables
	String nombre;
	List<Venta> listaVentas;
	List<Cliente> listaClientes;
	List<Producto> listaProductos;
	
	//constructor
	/**
	 * Metodo constructor
	 */
	public Almacen() {
		
	}
	
	/**
	 * Metodo constructor
	 * @param nombre Nombre del almacen
	 */
	public Almacen(String nombre) {
		this.nombre=nombre;
		listaVentas=new ArrayList<Venta>();
		listaClientes=new ArrayList<Cliente>();
		listaProductos=new ArrayList<Producto>();
	}
	
	//Metodos setters y getters
	/**
	 * Metodo que obtiene el nombre del almacen
	 * @return Nombre del almacen
	 */
	public String getNombre() {
		return nombre;
	}

	/**
	 * Metodo que cambia el nombre del almacen
	 * @param nombre Nuevo nombre del almacen
	 */
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	/**
	 * Metodo que obtiene la lista de ventas
	 * @return Lista de ventas
	 */
	public List<Venta> getListaVentas() {
		return listaVentas;
	}
	
	/**
	 * metodo que cambia la lista de venta
	 * @param listaVentas Nueva lista de ventas
	 */
	public void setListaVentas(List<Venta> listaVentas) {
		this.listaVentas = listaVentas;
	}
	
	/**
	 * Metodo que obtiene la lista de clientes
	 * @return Lista de clientes
	 */
	public List<Cliente> getListaClientes() {
		return listaClientes;
	}
	
	/**
	 * Metodo que cambia la lista de clientes
	 * @param listaClientes Nueva lista de clientes
	 */
	public void setListaClientes(List<Cliente> listaClientes) {
		this.listaClientes = listaClientes;
	}
	
	/**
	 * Metodo que obtiene la lista de productos 
	 * @return Lista de productos 
	 */
	public List<Producto> getListaProductos() {
		return listaProductos;
	}
	
	/**
	 * Metodo que cambia la lista de productos
	 * @param listaProductos Nueva lista de productos 
	 */
	public void setListaProductos(List<Producto> listaProductos) {
		this.listaProductos = listaProductos;
	}
	
	
	//metodos:
	
	/**
	 * Metodo que verifica si un cliente existe
	 * @param identificacion Identificacion del cliente que se desea verificar su existencia
	 * @return boolean Respuesta de que si existe un cliente con la identificacion indicada
	 */
	public boolean existeCliente(String identificacion) {
		boolean existeCliente=false;
		for (Cliente cliente : listaClientes) {
			if (cliente.verificarIdentificacion(identificacion)) {
				existeCliente=true;
			}
		}
		return existeCliente;
	}
	
	/**
	 * Metodo que agrega un cliente
	 * @param cliente Cliente Cliente que se desea agregar
	 */
	public void agregarCliente(Cliente cliente) {
		listaClientes.add(cliente);
		cliente.setOwnedByAlmacen(this);
	}
	
	/**
	 * Metodo que obtiene un cliente
	 * @param identificacion Identificacion del cliente que se desea 
	 * @return Cliente con la identificacion indicada
	 */
	public Cliente obtenerCliente(String identificacion) {
		Cliente clienteObtenido=null;
		for (Cliente cliente : listaClientes) {
			if (cliente.verificarIdentificacion(identificacion)) {
				clienteObtenido=cliente;
			}
		}
		return clienteObtenido;
	}
	
	/**
	 * Metodo que elimina un cliente 
	 * @param identificacion Identificacion del cliente que se desea eliminar
	 */
	public void eliminarCliente(String identificacion) {
		int posicion=-1;
		for (Cliente cliente : listaClientes) {
			posicion++;
		}
		listaClientes.remove(posicion);
	}
	
	/**
	 * Metodo que verifica si un producto existe
	 * @param codigo Codigo del producto que se desea verificar su existencia
	 * @return Respuesta de que si existe o no el producto
	 */
	public boolean existeProducto(String codigo) {
		boolean existeProducto=false;
		for (Producto producto : listaProductos) {
			if (producto.verificarCodigo(codigo)) {
				existeProducto=true;
			}
		}
		return existeProducto;
	}
	
	/**
	 * Metodo que agrega un producto
	 * @param producto Producto que se desea agregar
	 */
	public void agregarProducto(Producto producto) {
		listaProductos.add(producto);
		producto.setOwnedByAlmacen(this);
	}
	
	/**
	 * Metodo que obtiene un producto
	 * @param codigo Codido del producto que se desea obtener
	 * @return Producto obtenido
	 */
	public Producto obtenerProducto(String codigo) {
		Producto productoObtenido=null;
		for (Producto producto : listaProductos) {
			if (producto.getCodigo().equals(codigo)) {
				productoObtenido=producto;
			}
		}
		return productoObtenido;
	}
	
	/**
	 * Metodo que elimina un producto de la lista de productos
	 * @param codigo  odido del producto que se desea eliminar
	 */
	public void eliminarProducto(String codigo) {
		int posicion=-1;
		for (Producto producto : listaProductos) {
			posicion++;
		}
		listaProductos.remove(posicion);
	}
	
	/**
	 * Metodo que agrega una venta a la lista de ventas
	 * @param venta Venta que se desea agregar
	 */
	public void realizarVenta(Venta venta) {
		listaVentas.add(venta);
		venta.setOwnedByAlmacen(this);
		venta.guardarDetallesEnProductos();
	}

}
