package org.example.almacen;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import javafx.stage.Stage;
import javafx.scene.control.CheckBox;

public class agregar {

    @FXML
    private TextField lbID;

    @FXML
    private TextField lbNombre;

    @FXML
    private TextField lbCantidad;

    @FXML
    private TextField lbDescripcion;

    @FXML
    private CheckBox cbExistencia;

    @FXML
    private Label texExito;

    @FXML
    public void btnguardar() {
        String id = lbID.getText();
        String nombre = lbNombre.getText();
        String cantidad = lbCantidad.getText();
        String descripcion = lbDescripcion.getText();
        boolean existencia = cbExistencia.isSelected();  // true o false

        String ubicacionWallet = "src/Wallet/";
        System.setProperty("oracle.net.tns_admin", ubicacionWallet);
        String jdbcurl = "jdbc:oracle:thin:@icl8aqfau8e0bzlc_high";
        String userName = "ADMIN";
        String password = "Biblioteca01";

        String sql = "INSERT INTO peliculas (producto_id, nombre_producto, cantidad, descripcion, estado) VALUES (?, ?, ?, ?, ?)";

        try {
            Class.forName("oracle.jdbc.OracleDriver");
            Connection con = DriverManager.getConnection(jdbcurl, userName, password);
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setInt(1, Integer.parseInt(id));
            stmt.setString(2, nombre);
            stmt.setInt(3, Integer.parseInt(cantidad));
            stmt.setString(4, descripcion);
            stmt.setBoolean(5, existencia);

            stmt.executeUpdate();

            texExito.setText("Registro exitoso.");
            stmt.close();
            con.close();
        } catch (Exception e) {
            texExito.setText("Error al registrar.");
            e.printStackTrace();
        }
    }

    @FXML
    protected void volverMenu() {
        Stage stage = (Stage) lbNombre.getScene().getWindow();
        stage.close();
    }
}
