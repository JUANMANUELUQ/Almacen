package co.uniquindio.almacen.controllers;


import java.awt.event.ActionEvent;
import java.text.DecimalFormat;

import co.uniquindio.almacen.application.Aplicacion;
import co.uniquindio.almacen.model.*;
import co.uniquindio.almacen.util.ClaseUtilitaria;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import java.util.List;
import javafx.scene.control.Alert.AlertType;

/**
 * Controlador para la ventana de detalles de una venta en un almacén de alimentos.
 */
public class DetallesVentaController {

    @FXML
    private TableColumn<Producto, String> columnCantidadDisponible;

    @FXML
    private Button btnVerDetalles;

    @FXML
    private Label lblCantidad;

    @FXML
    private Button btnGuardar;

    @FXML
    private Button btnVolver;
    
    @FXML
    private Button btnSeleccionarProducto;

    @FXML
    private TextField txtCantidad;

    @FXML
    private Label lblSubtotalCalculado;

    @FXML
    private TableColumn<Producto, String> columnProducto;

    @FXML
    private Label lblSubtotal;

    @FXML
    private TableView<Producto> tableProductos;
    
    private ModelFactoryController mfm = ModelFactoryController.getInstance();
	private Aplicacion aplicacion=mfm.getAplicacion();
	private List<Producto> productosTemporales;
	private DetalleVenta detalleObtenido=null;
	private Stage ventana;
    
    /**
     * Inicializa el controlador despues de que se haya cargado el FXML
     */
    @FXML
	private void initialize() {
    	
    }
	
	/**
	 * Guarda los datos de la venta en el detalle 
	 */
    @FXML
    void guardar() {
        if (sonDatosValidos()) {
			Producto productoSeleccionado = tableProductos.getSelectionModel().getSelectedItem();
			DetalleVenta detalle=new DetalleVenta(Integer.parseInt(txtCantidad.getText()),Float.parseFloat(lblSubtotalCalculado.getText()),
			productoSeleccionado);
			ClaseUtilitaria.reducirCantidadProductos(productosTemporales,productoSeleccionado,Integer.parseInt(txtCantidad.getText()));
			detalleObtenido=detalle;
			ventana.close();
		}

    }
    
    /**
     * Verifica si los datos ingresados son válidos.
     * @return true si los datos son válidos, false en caso contrario.
     */
    private boolean sonDatosValidos(){
        boolean sonValidos=false;
        String msj="";
		try {
			if (Integer.parseInt(txtCantidad.getText())<=0) {
				throw new Exception("Valor invalido");
			}
		} catch (Exception e) {
			if (txtCantidad.getText().trim().equals("")) {
				msj+="\n\nLa cantidad no debe estar vacia";
			} else {
				msj+="\n\nCantidad no valido";
			}
		}
		if (tableProductos.getSelectionModel().getSelectedIndex() < 0) {
			msj+="\n\nDebe elejir un producto";
		} else {
			Producto producto=tableProductos.getSelectionModel().getSelectedItem();
			if (msj.equals("") && Integer.parseInt(txtCantidad.getText())>
			ClaseUtilitaria.obtenerCantidadProducto(producto.getCodigo(), productosTemporales)) {
				msj+="\n\nLa cantidad indicada supera a la cantidad del vehiculo seleccionado";
			}
		}
		
		if (msj.equals("")) {
			sonValidos=true;
		} else {
			ClaseUtilitaria.mostrarMensaje("Entradas no validas", "Entradas no validas", msj, AlertType.ERROR);
		}
		return sonValidos;
    }
    
	/**
     * Muestra los detalles extra de un producto en un cuadro de diálogo.
     */
    @FXML
    void verDetallesProducto() {
    	int selectedIndex = tableProductos.getSelectionModel().getSelectedIndex();
        if(selectedIndex >= 0){
        	Producto productoSeleccionado = tableProductos.getSelectionModel().getSelectedItem();
        	ClaseUtilitaria.mostrarMensaje("Detalles del Producto", "Detalles del Producto", 
            ClaseUtilitaria.obtenerDatosProducto(productoSeleccionado), AlertType.WARNING);
        } else {
            ClaseUtilitaria.mostrarMensaje("Advertencia", "Advertencia", "Por favor, selecciona un producto para ver los detalles", AlertType.WARNING);
        }
    }
	
    /**
     * Calcula el subtotal deacuerdo al producto y la cantidad indicada
     */
    @FXML
    public void calcularSubtotal() {
    	boolean esCantidadValida=true;
    	boolean estaProductoElegido=true;
    	try {
			if (Integer.parseInt(txtCantidad.getText())<=0) {
				throw new Exception("Valor invalido");
			}
		} catch (Exception e) {
			if (txtCantidad.getText().trim().equals("")) {
				esCantidadValida=false;
			} else {
				esCantidadValida=false;
			}
		}
    	if (tableProductos.getSelectionModel().getSelectedIndex() < 0) {
    		estaProductoElegido=false;
		}
    	if (esCantidadValida && estaProductoElegido) {
    		DecimalFormat decimalFormat = new DecimalFormat("#");
    		Producto productoSeleccionado=tableProductos.getSelectionModel().getSelectedItem();
    		lblSubtotalCalculado.setText(decimalFormat.format(Integer.parseInt(txtCantidad.getText())*productoSeleccionado.getValorUnitario()));
    	} else {
    		lblSubtotalCalculado.setText("0.0");
    	}
    }
    
	/**
     * Vuelve a la ventana anterior.
     */
    @FXML
    void volver() {
		ventana.close();
    }
    
    /**
     * Establece los datos correspondientes en la tanle de productos
     */
    public void ponerDatosTablaProductos() {
    	tableProductos.setItems(FXCollections.observableArrayList(productosTemporales));
		columnProducto.setCellValueFactory(cellData -> new SimpleStringProperty(""+cellData.getValue().getNombre()));
		columnCantidadDisponible.setCellValueFactory(cellData -> new SimpleStringProperty(""+cellData.getValue().getCantidadExistencia()));
    }
	
	/**
     * Establece la ventana actual para este controlador 
     * @param ventana La ventana actual
     */
	public void setVentana(Stage ventana) {
		this.ventana=ventana;
	}
	
	public void establecerProductosTemporales (List<Producto> productosTemporales){
        this.productosTemporales=productosTemporales;
    }

	/**
     * Organiza los datos previos de una venta en caso de ser necesaria su edición.
     * @param detalle El detalle de la venta.
     */
	public void organizarDetalle(DetalleVenta detalle) {
		detalleObtenido=detalle;
		if (detalleObtenido!=null) {
			txtCantidad.setText(""+detalleObtenido.getCantProductosVendidos());
			lblSubtotalCalculado.setText(""+detalleObtenido.getSubTotal());
		}
	}
	
	/**
     * Obtiene el detalle de la venta actual.
     * @return El detalle de la venta actual.
     */
	public DetalleVenta getDetalleObtenido() {
		return detalleObtenido;
	}
	
	
	
    
    

}
