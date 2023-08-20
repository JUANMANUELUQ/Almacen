package co.uniquindio.almacen.controllers;

import javafx.stage.Stage;
import co.uniquindio.almacen.application.Aplicacion;
import co.uniquindio.almacen.model.*;
import co.uniquindio.almacen.util.ClaseUtilitaria;
import java.util.List;
import java.time.LocalDate;
import java.util.ArrayList;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.text.Text;

/**
 * Controlador para la ventana de ingreso de ventas en un sistema de gestión de ventas.
 */
public class IngresoVentaController {

    @FXML
    private Button btnAgregarDetalle;

    @FXML
    private Text gestionVentasField;

    @FXML
    private TableColumn<DetalleVenta, String> columnProducto;

    @FXML
    private TableColumn<Cliente, String> columnNombre;

    @FXML
    private Button btnSeleccionarCliente;

    @FXML
    private TextField txtCodigo;

    @FXML
    private DatePicker fechaPicker;

    @FXML
    private Label lblTotal;

    @FXML
    private Button btnEliminarDetalle;

    @FXML
    private Label lblFecha;

    @FXML
    private TableView<Cliente> tableClientes;

    @FXML
    private Button btnGuardar;

    @FXML
    private Button btnVolver;

    @FXML
    private TableColumn<DetalleVenta, String> columnCantidad;

    @FXML
    private TableColumn<Cliente, String> columnIdentificacion;

    @FXML
    private TableView<DetalleVenta> tableDetallesVenta;

    @FXML
    private Label lblIva;

    @FXML
    private Label lblCodigo;
    
    @FXML
    private Label lblNombre;
    
    @FXML
    private Label lblDocumento;

    @FXML
    private TableColumn<DetalleVenta, String> columnSubtotal;
    
    private Stage ventana;
    private ModelFactoryController mfm = ModelFactoryController.getInstance();
	private Aplicacion aplicacion=mfm.getAplicacion();
	private Cliente cliente;
	private List<DetalleVenta> listaTemporalDetalles=new ArrayList<DetalleVenta>();
	private List<Producto> listaProductosTemporales=mfm.obtenerCopiaProductos();

    @FXML
    private Button btnEditarDetalle;
    
    /**
     * Inicializa la clase controlador. Carga datos iniciales en la tabla de clientes y establece el valor del IVA.
     */
	@FXML
	private void initialize() {
		ponerClientesTabla();
		fechaPicker.setEditable(false);
		fechaPicker.getEditor().setStyle("-fx-background-color: #cccccc;");
		lblIva.setText("19");
	}

	/**
     * Guarda la venta actual en el sistema si los datos ingresados son válidos.
     */
    @FXML
    void guardar() {
    	if (sonDatosValidos()) {
    		Venta venta=new Venta(txtCodigo.getText(),fechaPicker.getValue(),Float.parseFloat(lblTotal.getText()),
    		Double.parseDouble(lblIva.getText()),listaTemporalDetalles,cliente);
    		mfm.agregarVenta(venta);
    		mfm.actualizarListaProductos(listaProductosTemporales);
    		ventana.close();
    		aplicacion.mostrarVentanaPrincipal();
    	}
    }
	
	/**
     * Verifica si los datos ingresados en la venta son válidos.
     * @return true si los datos son válidos, false si no lo son.
     */
    private boolean sonDatosValidos() {
    	boolean sonValidos=false;
        String msj="";
        if (txtCodigo.getText()==null || txtCodigo.getText().trim().equals("")) {
            msj+="El codigo no debe estar vacio";
        }
        if (fechaPicker.getValue()==null) {
            msj+="\n\nLa fecha no debe estar vacia";
        }
		if (cliente==null) {
			msj+="\n\nDebe elejir un cliente";
		}
		if (listaTemporalDetalles.size()==0) {
			msj+="\n\nDebe elejir un detalle de venta";
		}
		if (msj.equals("")) {
			sonValidos=true;
		} else {
			ClaseUtilitaria.mostrarMensaje("Entradas no validas", "Entradas no validas", msj, AlertType.ERROR);
		}
		return sonValidos;
	}

	/**
     * Permite al usuario editar un detalle de venta seleccionado en la tabla.
     */
	@FXML
    void editarDetalle() {
    	int selectedIndex = tableDetallesVenta.getSelectionModel().getSelectedIndex();
		if (selectedIndex >= 0) {
			DetalleVenta detalleObtenido = tableDetallesVenta.getSelectionModel().getSelectedItem();
			DetalleVenta detalleObtenidoEdicion=aplicacion.mostrarDetalleVenta(ventana, listaProductosTemporales,detalleObtenido);
			if (detalleObtenido!=detalleObtenidoEdicion) {
				listaTemporalDetalles.set(listaTemporalDetalles.indexOf(detalleObtenido), detalleObtenidoEdicion);
				actualizarTabla();
			}
		} else {
			ClaseUtilitaria.mostrarMensaje("No hay un detalle seleccionado", "No hay un detalle seleccionado", "Elija un detalle", AlertType.ERROR);
		}
    }

	/**
     * Agrega un nuevo detalle de venta a la lista temporal.
     */
    @FXML
    void agregarDetalle() {
			DetalleVenta detalleObtenido;
			detalleObtenido=aplicacion.mostrarDetalleVenta(ventana, listaProductosTemporales,null);
			if (detalleObtenido!=null) {
				listaTemporalDetalles.add(detalleObtenido);
				actualizarTabla();
				double total = ClaseUtilitaria.obtenerTotal(listaTemporalDetalles);
				String totalFormateado = String.format("%.0f", total); // Formato completo sin decimales
				lblTotal.setText(totalFormateado);
				//listaProductosTemporales= mfm.retornarPosiblesProductos();
			}
    }

	/**
     * Vuelve a la ventana principal, cerrando la ventana actual.
     * @param event El evento de acción que desencadenó este método.
     */
    @FXML
    void volver() {
    	ventana.close();
		aplicacion.mostrarVentanaPrincipal();
    }

	/**
     * Asigna un cliente seleccionado de la tabla a la venta actual.
     */
    @FXML
    void seleccionarCliente() {
    	int selectedIndex = tableClientes.getSelectionModel().getSelectedIndex();
		if (selectedIndex >= 0) {
			Cliente clienteSeleccionado = tableClientes.getSelectionModel().getSelectedItem();
			lblNombre.setText(clienteSeleccionado.getNombre());
			lblDocumento.setText(clienteSeleccionado.getIdentificacion());
			cliente=clienteSeleccionado;
		} else {
			ClaseUtilitaria.mostrarMensaje("No hay un cliente seleccionado", "No hay un cliente seleccionado", "Elija un cliente", AlertType.ERROR);
		}
    }
	
	/**
     * Elimina un detalle de venta seleccionado de la lista temporal.
     */
    @FXML
    void eliminarDetalle() {
    	int selectedIndex = tableDetallesVenta.getSelectionModel().getSelectedIndex();
		if (selectedIndex >= 0) {
			DetalleVenta detalleVenta = tableDetallesVenta.getSelectionModel().getSelectedItem();
			Producto producto=detalleVenta.getProducto();
			ClaseUtilitaria.aumentarCantidadProductos(listaProductosTemporales, producto, detalleVenta.getCantProductosVendidos());
			listaTemporalDetalles.remove(detalleVenta);
			tableDetallesVenta.getItems().remove(selectedIndex);
		} else {
			ClaseUtilitaria.mostrarMensaje("No hay un detalle seleccionado", "No hay un detalle seleccionado", "Elija un detalle", AlertType.ERROR);
		}
    }

	/**
     * Establece la ventana actual para el controlador.
     * @param ventana La ventana actual.
     */
	public void setVentana(Stage ventana) {
		this.ventana=ventana;
	}
		
	/**
     * Rellena la tabla de clientes con los clientes disponibles en el sistema.
     */
	public void ponerClientesTabla() {
		tableClientes.setItems(FXCollections.observableArrayList(mfm.getAlmacen().getListaClientes()));
		columnNombre.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getNombre()));
		columnIdentificacion.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getIdentificacion()));
		tableClientes.refresh();
	}

	/**
     * Actualiza la tabla de detalles de venta con los datos de la lista temporal.
     */
	public void actualizarTabla() {
		tableDetallesVenta.setItems(FXCollections.observableArrayList(listaTemporalDetalles));
		columnProducto.setCellValueFactory(cellData -> new SimpleStringProperty(""+cellData.getValue().getProducto().getNombre()));
		columnCantidad.setCellValueFactory(cellData -> new SimpleStringProperty(""+cellData.getValue().getCantProductosVendidos()));
		columnSubtotal.setCellValueFactory(cellData -> new SimpleStringProperty(""+cellData.getValue().getSubTotal()));
		tableClientes.refresh();
	}

}
