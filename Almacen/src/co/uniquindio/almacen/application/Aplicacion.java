package co.uniquindio.almacen.application;

import java.io.IOException;
import co.uniquindio.almacen.controllers.*;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import co.uniquindio.almacen.model.*;
import co.uniquindio.almacen.*;
import java.util.List;

public class Aplicacion extends Application {
	
	//atributos
	private Stage primaryStage;
	private AnchorPane rootLayout;
	
	/**
	 * Metodo que inica la ejecucion de la aplicacion
	 */
	@Override
	public void start(Stage primaryStage) {
		ModelFactoryController mfm = ModelFactoryController.getInstance();
		mfm.setAplicacion(this);
		this.primaryStage = primaryStage;
		this.primaryStage.setTitle("Almacen");
		primaryStage.setResizable(false);
		mostrarVentanaPrincipal();
	}
	
	/**
	 * Inicia la ventana principal
	 */
	public void mostrarVentanaPrincipal() {
		try {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(Aplicacion.class.getResource("/co/uniquindio/almacen/view/VentanaPrincipal.fxml"));
			rootLayout = (AnchorPane) loader.load();
			Scene scene = new Scene(rootLayout);
			primaryStage.setScene(scene);
			primaryStage.centerOnScreen();
			primaryStage.show();
			VentanaPrincipalController controlador = loader.getController();
			controlador.setVentana(primaryStage);
		} catch (IOException e) {
			e.printStackTrace();
			
		}
	}
	
	/**
	 * Muestra la ventana de IngresoCliente
	 */
	public void mostrarIngresoCliente() {
		try {
			primaryStage.close();
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(Aplicacion.class.getResource("/co/uniquindio/almacen/view/IngresoCliente.fxml"));
			AnchorPane rootLayout = (AnchorPane) loader.load();
			Scene scene = new Scene(rootLayout);
			Stage primaryStage=new Stage();
			primaryStage.setScene(scene);
			primaryStage.show();
			IngresoClienteController controlador = loader.getController();
			controlador.setVentana(primaryStage);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Muestra la ventana de IngresoVenta
	 */
	public void mostrarIngresoVenta() {
		try {
			primaryStage.close();
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(Aplicacion.class.getResource("/co/uniquindio/almacen/view/IngresoVenta.fxml"));
			AnchorPane rootLayout = (AnchorPane) loader.load();
			Scene scene = new Scene(rootLayout);
			Stage primaryStage=new Stage();
			primaryStage.setScene(scene);
			primaryStage.show();
			IngresoVentaController controlador = loader.getController();
			controlador.setVentana(primaryStage);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Muestra la ventana de IngresoProducto
	 */
	public void mostrarIngresoProducto() {
		try {
			primaryStage.close();
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(Aplicacion.class.getResource("/co/uniquindio/almacen/view/IngresoProducto.fxml"));
			AnchorPane rootLayout = (AnchorPane) loader.load();
			Scene scene = new Scene(rootLayout);
			Stage primaryStage=new Stage();
			primaryStage.setScene(scene);
			primaryStage.setTitle("Ingreso Productos");
			primaryStage.show();
			IngresoProductoController controlador = loader.getController();
			controlador.setVentana(primaryStage);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Muestra la ventana de DetalleVenta
	 * @param ventanaAnterior Ventana anterior
	 * @param productosTemporales Lista de productos que son temporales
	 * @param detalleVenta Detalle que se va a editar
	 * @return Respuesta del detalle resultante
	 */
	public DetalleVenta mostrarDetalleVenta(Stage ventanaAnterior, List<Producto> productosTemporales,DetalleVenta detalleVenta) { //Para que le entra el detalle por parámetro? No se supone que tiene que salir?{
		try {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(Aplicacion.class.getResource("/co/uniquindio/almacen/view/DetallesVenta.fxml"));
			AnchorPane page = (AnchorPane) loader.load();
			Stage ventana = new Stage();
			ventana.setTitle("Detalle venta");
			ventana.initModality(Modality.WINDOW_MODAL);
			ventana.initOwner(ventanaAnterior);
			Scene scene = new Scene(page);
			ventana.setScene(scene);
			ventana.setResizable(false); 
			DetallesVentaController controller = loader.getController();
			controller.setVentana(ventana);
			controller.establecerProductosTemporales(productosTemporales);
			controller.ponerDatosTablaProductos();
			controller.organizarDetalle(detalleVenta);
			ventana.showAndWait();
			return controller.getDetalleObtenido();
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	/**
	 * Muestra la ventana de DetalleVenta
	 */
	public void mostrarRegistroVentas() {
		try {
			primaryStage.close();
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(Aplicacion.class.getResource("/co/uniquindio/almacen/view/RegistroVentas.fxml"));
			AnchorPane rootLayout = (AnchorPane) loader.load();
			Scene scene = new Scene(rootLayout);
			Stage primaryStage=new Stage();
			primaryStage.setScene(scene);
			primaryStage.setTitle("Registro ventas");
			primaryStage.show();
			RegistroVentasController controlador = loader.getController();
			controlador.setVentana(primaryStage);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
