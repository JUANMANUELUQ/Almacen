package co.uniquindio.almacen.util;

import co.uniquindio.almacen.model.*;
import java.util.List;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

/**
 * Clase de utilidad que proporciona métodos para realizar operaciones comunes en la aplicación.
 */
public class ClaseUtilitaria {
	
	/**
	 * Muestra un mensaje de alerta.
	 *
	 * @param titulo El título del mensaje.
	 * @param encabezado El encabezado del mensaje.
	 * @param texto El texto principal del mensaje.
	 * @param alerta El tipo de alerta.
	 */
	public static void mostrarMensaje(String titulo,String encabezado,String texto,AlertType alerta) {
		Alert alert = new Alert(alerta);
		alert.setTitle(titulo);
		alert.setHeaderText(encabezado);
		alert.setContentText(texto);
		alert.showAndWait();
	}
	
	/**
	 * Obtiene el enum PaisOrigen a partir de una cadena de texto.
	 *
	 * @param cadena La cadena de texto que representa el país de origen.
	 * @return El valor enum correspondiente al país de origen.
	 */
	public static PaisOrigen obtenerPaisOrigen(String cadena) {
		PaisOrigen paisOrigen=null;
		switch(cadena) {
			case "Colombia": paisOrigen=PaisOrigen.COLOMBIA; break;
			case "Argentina": paisOrigen=PaisOrigen.ARGENTINA; break;
			case "Chile": paisOrigen=PaisOrigen.CHILE; break;
			case "Ecuador": paisOrigen=PaisOrigen.ECUADOR; break;
			case "Peru": paisOrigen=PaisOrigen.PERU; break;
		}
		return paisOrigen;
	}
	
	/**
	 * Obtiene la cantidad de un producto en base a su código.
	 *
	 * @param productoBuscar El código del producto a buscar.
	 * @param listaProductos La lista de productos en la que se buscará.
	 * @return La cantidad del producto encontrado.
	 */
	public static int obtenerCantidadProducto(String productoBuscar,List<Producto> listaProductos) {
		int cant=0;
		for (Producto producto : listaProductos) {
			if (producto.verificarCodigo(productoBuscar)) {
				cant=producto.getCantidadExistencia();
			}
		}
		return cant;
	}
	
	/**
	 * Reduce la cantidad de un producto en la lista de productos.
	 *
	 * @param listaProductos La lista de productos en la que se reducirá la cantidad.
	 * @param productoBuscar El producto cuya cantidad se reducirá.
	 * @param cantidad La cantidad a reducir.
	 */
	public static void reducirCantidadProductos(List<Producto> listaProductos,Producto productoBuscar, int cantidad) {
		for (Producto producto : listaProductos) {
			if (producto.verificarCodigo(productoBuscar.getCodigo())) {
				producto.setCantidadExistencia(producto.getCantidadExistencia()-cantidad);
				break;
			}
		}
	}
	
	/**
	 * Aumenta la cantidad de un producto en la lista de productos.
	 *
	 * @param listaProductos La lista de productos en la que se aumentará la cantidad.
	 * @param productoBuscar El producto cuya cantidad se aumentará.
	 * @param cantidad La cantidad a aumentar.
	 */
	public static void aumentarCantidadProductos(List<Producto> listaProductos,Producto productoBuscar, int cantidad) {
		for (Producto producto : listaProductos) {
			if (producto.verificarCodigo(productoBuscar.getCodigo())) {
				producto.setCantidadExistencia(producto.getCantidadExistencia()+cantidad);
				break;
			}
		}
	}
	
	/**
	 * Calcula el total de una lista de detalles de venta.
	 *
	 * @param detalles La lista de detalles de venta.
	 * @return El total calculado.
	 */
	public static Double obtenerTotal(List<DetalleVenta> detalles) {
		Double total=0.0;
		for (DetalleVenta detalleTransaccion : detalles) {
			total+=detalleTransaccion.getSubTotal();
		}
		return total;
	}
	
	/**
	 * Obtiene los datos de un producto en formato de cadena.
	 *
	 * @param producto El producto del que se obtendrán los datos.
	 * @return Los datos del producto en formato de cadena.
	 */
	public static String obtenerDatosProducto(Producto producto) {
		String msj="Codigo: "+ producto.getCodigo();
		msj+="\nNombre: " + producto.getNombre();
		msj+="\nDescripcion: " + producto.getDescripcion();
		msj+="\n" + "Valor Unitario: " + producto.getValorUnitario();
		msj+="\n" + "Cantidad existencia: " + producto.getCantidadExistencia();
		msj+="\n" + "Tipo de producto: " + producto.obtenerTipoProducto();
		if(producto instanceof ProductoPerecedero){
			msj+="\n" + "Fecha de vencimiento: " + ((ProductoPerecedero) producto).obtenerTipoProducto();
	    } else if (producto instanceof ProductoRefrigerado){
	    	msj+="\n" + "Codigo de Aprobacion: " + ((ProductoRefrigerado) producto).getCodigoAprobacion();
	    	msj+="\n" + "Temperatura de refrigeracion recomendada: " + ((ProductoRefrigerado) producto).getTempRefrigeracionRecomendada();
	    } else if (producto instanceof ProductoEnvasado){
	    	msj+="\n" + "Fecha de Envasado: " + ((ProductoEnvasado) producto).getFechaEnvasado();
	    	msj+="\n" + "Peso de envase: " + ((ProductoEnvasado) producto).getPesoEnvase();
	    	msj+="\n" + "Pais de origen: " + ((ProductoEnvasado) producto).obtenerPaisOrigenCadena();
	    } 
		return msj;
	}
	
	/**
	 * Obtiene los datos de un cliente en formato de cadena.
	 *
	 * @param cliente El cliente del que se obtendrán los datos.
	 * @return Los datos del cliente en formato de cadena.
	 */
	public static String obtenerDatosCliente(Cliente cliente) {
		String msj="Nombre: "+ cliente.getNombre();
		msj+="\nApellidos: "+ cliente.getApellidos();
		msj+="\nIdentificacion: "+ cliente.getIdentificacion();
		msj+="\nDireccion: "+ cliente.getDireccion();
		msj+="\nTelefono: "+ cliente.getTelefono();
		if(cliente instanceof ClienteNatural){
			msj+="\nEmail: "+ ((ClienteNatural) cliente).getEmail();
			msj+="\nFecha de nacimiento: "+ ((ClienteNatural) cliente).getFechaNacimiento();
        } else if (cliente instanceof ClienteJuridico){
        	msj+="\nNit: "+ ((ClienteJuridico) cliente).getNit();
        }  
		return msj;
	}

}
