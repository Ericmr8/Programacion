/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package monlau.dao;

import java.util.logging.Level;
import java.util.logging.Logger;
import monlau.model.Producto;
import java.sql.*;

/**
 *
 * @author user
 */
public class ProductoDAOImpl implements ProductoDAO {

    static final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
    static final String DB_URL = "jdbc:mysql://localhost:3306/inventario?useSSL=false";
    static final String DB_USR = "root";
    static final String DB_PWD = "";

    /**
     * Load the driver class
     */
    private void registerDriver() {
        try {
            Class.forName(JDBC_DRIVER);
        } catch (ClassNotFoundException ex) {
            //System.out.println("ERROR: failed to load MySQL JDBC Driver");
            System.err.println("ERROR: failed to load MySQL JDBC Driver"); // imprime en la salida standar de error
            ex.printStackTrace();
        }
    }

    public void insert(Producto producto) {
        Connection conn = null;
        try {
            registerDriver();
            // abrir la conexión
            conn = DriverManager.getConnection(DB_URL, DB_USR, DB_PWD);
            Statement stmt = conn.createStatement();
            // enviar el comando insert
            stmt.executeUpdate("insert into producto values ("
                    + producto.getId() + ",'"
                    + producto.getNombre() + "',"
                    + producto.getPrecio() + ");");
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        } finally {
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
        }
    }

    public void update(Producto producto) {
        Connection conn = null;
        PreparedStatement pstmt = null;

        try {
            registerDriver();
            // Open the connection
            conn = DriverManager.getConnection(DB_URL, DB_USR, DB_PWD);

            // Create the SQL UPDATE query with placeholders
            String sql = "UPDATE producto SET nombre = ?, precio = ? WHERE id = ?";

            // Create the PreparedStatement and set the parameters
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, producto.getNombre());   // Set the product name (String)
            pstmt.setDouble(2, producto.getPrecio());    // Set the product price (double)
            pstmt.setInt(3, producto.getId());          // Set the product ID (int)

            // Execute the update
            int rowsAffected = pstmt.executeUpdate();

            if (rowsAffected > 0) {
                System.out.println("Producto actualizado con éxito.");
            } else {
                System.out.println("No se encontró el producto con el ID proporcionado.");
            }

        } catch (SQLException ex) {
            ex.printStackTrace();  // Print the error stack trace for debugging
            throw new RuntimeException(ex);  // Rethrow the exception
        } finally {
            // Close resources safely
            try {
                if (pstmt != null) {
                    pstmt.close();
                }
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
    }


    public void delete(Producto producto) {
        Connection conn = null;
        try {
            registerDriver();
            conn = DriverManager.getConnection(DB_URL, DB_USR, DB_PWD);
            Statement stmt = conn.createStatement();
            stmt.executeUpdate("delete from producto where id = " + producto.getId());

        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        } finally {
            if (conn != null) {
                try {
                    conn.close();

                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
        }
    }

    public Producto read(Integer id) {
        Connection conn = null;
        Producto prod = null;
        try {
            registerDriver();
            // abrir la conexión
            conn = DriverManager.getConnection(DB_URL, DB_USR, DB_PWD);
            // consulta select (selecciona el producto con id especificado)
            try (PreparedStatement ps = conn.prepareStatement("select * from producto where id = ?")) {
                //indicar el id que buscamos
                ps.setInt(1, id);
                //ejecutar la consulta
                try (ResultSet rs = ps.executeQuery()) {
                    if (rs.next()) {
                        // obtener cada una de las columnas y mapearlas a la clase Product
                        prod = new Producto(id, rs.getString("nombre"), rs.getDouble("precio"));
                    }
                }
            }
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        } finally {
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
        }
        return prod;
    }

}
