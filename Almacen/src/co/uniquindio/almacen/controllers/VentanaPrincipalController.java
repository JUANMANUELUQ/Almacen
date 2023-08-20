package co.uniquindio.almacen.controllers;

import co.uniquindio.almacen.application.Aplicacion;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

/**
 * Controlador que gestiona la ventana principal de la aplicación y sus interacciones.
 */
public class VentanaPrincipalController {

    @FXML
    private ImageView fondo_1;
	@FXML
	private Button btnClientes;
	
	@FXML
	private Button btnProductos;
	
	@FXML
	private Button btnVentas;
	
	@FXML
	private Stage ventana;
	
	private ModelFactoryController mfm = ModelFactoryController.getInstance();
	private Aplicacion aplicacion=mfm.getAplicacion();
	
	/**
     * Abre la ventana de gestión de clientes al hacer clic en el botón "Clientes".
     */
	@FXML
	private void clientes() {
		aplicacion.mostrarIngresoCliente();
	}
	
	/**
     * Abre la ventana de gestión de productos al hacer clic en el botón "Productos".
     */
	@FXML
	private void productos() {
		aplicacion.mostrarIngresoProducto();
	}
	
	/**
     * Abre la ventana de ingreso de ventas al hacer clic en el botón "Ventas".
     */
	@FXML
	private void ventas() {
		aplicacion.mostrarIngresoVenta();
	}
	
	/**
     * Abre la ventana de registro de ventas al hacer clic en el botón "Registro Ventas".
     */
	@FXML
	private void registroVentas() {
		aplicacion.mostrarRegistroVentas();
	}
	
	/**
     * Establece la instancia de la ventana principal en el controlador.
     * @param primaryStage La instancia de la ventana principal.
     */
	public void setVentana(Stage primaryStage) {
		this.ventana = primaryStage;
	}
	
}
