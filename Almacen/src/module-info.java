module Almacen {
	requires javafx.controls;
	requires javafx.fxml;
	requires java.desktop;
	requires javafx.graphics;
	requires javafx.base;
	
	exports co.uniquindio.almacen.application to javafx.graphics;
	opens co.uniquindio.almacen.application to javafx.fxml;
	exports co.uniquindio.almacen.controllers to javafx.fxml;
	opens co.uniquindio.almacen.controllers to javafx.fxml;
}
