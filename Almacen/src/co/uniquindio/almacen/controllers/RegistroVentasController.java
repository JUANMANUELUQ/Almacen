package co.uniquindio.almacen.controllers;

import co.uniquindio.almacen.application.Aplicacion;
import co.uniquindio.almacen.model.*;
import co.uniquindio.almacen.util.ClaseUtilitaria;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;

/**
 * Controlador que gestiona la interfaz gráfica para la visualización y manipulación de ventas y detalles de venta.
 */
public class RegistroVentasController {
	
	@FXML
	private TableView<Venta> tableVentas;
	@FXML
	private TableColumn<Venta,String> columnCodigo;
	@FXML
	private TableColumn<Venta,String> columnFecha;
	@FXML
	private TableColumn<Venta,String> columnTotal;
	@FXML
	private TableColumn<Venta,String> columnIva;
	@FXML
	private TableView<DetalleVenta> tableDetalles;
	@FXML
	private TableColumn<DetalleVenta,String> columnCantidad;
	@FXML
	private TableColumn<DetalleVenta,String> columnSubtotal;
	private Stage ventana;
	private ModelFactoryController mfm = ModelFactoryController.getInstance();
	private Aplicacion aplicacion=mfm.getAplicacion();
	
	/**
	 * Inicializa la clase controladora al cargar la interfaz gráfica.
	 * Actualiza la tabla de ventas.
	 */
	@FXML
	private void initialize() {
		actualizarTablaVentas();
    }
    
    /**
	 * Abre una ventana para ver los detalles del cliente asociado a la venta seleccionada.
	 */
	@FXML
	private void verCliente() {
		int selectedIndex = tableVentas.getSelectionModel().getSelectedIndex();
        if(selectedIndex >= 0){
        	Cliente clienteSeleccionado = tableVentas.getSelectionModel().getSelectedItem().getCliente();
        	ClaseUtilitaria.mostrarMensaje("Detalles del cliente", "Detalles del cliente", 
            ClaseUtilitaria.obtenerDatosCliente(clienteSeleccionado), AlertType.WARNING);
        } else {
            ClaseUtilitaria.mostrarMensaje("Advertencia", "Advertencia", "Por favor, selecciona una venta para ver la informacion del cliente", 
            AlertType.WARNING);
        }
	}
	
	/**
	 * Abre una ventana para ver los detalles del producto asociado al detalle de venta seleccionado.
	 */
	@FXML
	private void verProducto() {
		int selectedIndex = tableDetalles.getSelectionModel().getSelectedIndex();
        if(selectedIndex >= 0){
        	Producto productoSeleccionado = tableDetalles.getSelectionModel().getSelectedItem().getProducto();
        	ClaseUtilitaria.mostrarMensaje("Detalles del Producto", "Detalles del Producto", 
        	ClaseUtilitaria.obtenerDatosProducto(productoSeleccionado), AlertType.WARNING);
        } else {
            ClaseUtilitaria.mostrarMensaje("Advertencia", "Advertencia", "Por favor, selecciona un detalle para ver la informacion del producto", 
            AlertType.WARNING);
        }
	}
	
	/**
	 * Cierra la ventana actual y vuelve a la ventana principal de la aplicación.
	 */
	@FXML
	private void volver() {
		ventana.close();
		aplicacion.mostrarVentanaPrincipal();
	}
	
	/**
	 * Actualiza la tabla de detalles de venta con los detalles de la venta seleccionada en la tabla de ventas.
	 */
	@FXML
	private void ponerDatosTablaDetalles() {
		int selectedIndex = tableVentas.getSelectionModel().getSelectedIndex();
		if (selectedIndex >= 0) {
			Venta ventaSeleccionada = tableVentas.getSelectionModel().getSelectedItem();
			actualizarTablaDetalles(ventaSeleccionada);
		}
    }
	
	/**
	 * Actualiza la tabla de ventas con la lista de ventas del almacén.
	 */
	public void actualizarTablaVentas() {
		tableVentas.setItems(FXCollections.observableArrayList(mfm.getAlmacen().getListaVentas()));
		columnCodigo.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getCodigo()));
		columnFecha.setCellValueFactory(cellData -> new SimpleStringProperty(""+cellData.getValue().getFecha()));
		columnTotal.setCellValueFactory(cellData -> new SimpleStringProperty(""+cellData.getValue().getTotal()));
		columnIva.setCellValueFactory(cellData -> new SimpleStringProperty(""+cellData.getValue().getIva()));
	}
	
	/**
	 * Actualiza la tabla de detalles de venta con los detalles de la venta proporcionada.
	 * @param venta Venta de la cual se mostrarán los detalles.
	 */
	public void actualizarTablaDetalles(Venta venta) {
		tableDetalles.setItems(FXCollections.observableArrayList(venta.getListaDetallesVenta()));
		columnCantidad.setCellValueFactory(cellData -> new SimpleStringProperty(""+cellData.getValue().getCantProductosVendidos()));
		columnSubtotal.setCellValueFactory(cellData -> new SimpleStringProperty(""+cellData.getValue().getSubTotal()));
		tableVentas.refresh();
	}
	
	/**
	 * Establece la ventana actual.
	 * @param ventana Ventana actual.
	 */
	public void setVentana(Stage ventana) {
		this.ventana=ventana;
	}

}
