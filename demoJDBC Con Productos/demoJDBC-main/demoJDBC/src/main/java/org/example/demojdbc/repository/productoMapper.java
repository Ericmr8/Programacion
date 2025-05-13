package org.example.demojdbc.repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import org.example.demojdbc.model.Productos;

import org.springframework.jdbc.core.RowMapper;

public class productoMapper implements RowMapper<Productos> {

    @Override
    public Productos mapRow(ResultSet rs, int rowNum) throws SQLException {
        // Crear un nuevo objeto Productos y mapear los campos del ResultSet
        Productos producto = new Productos();
        producto.setId(rs.getInt("id"));
        producto.setNombre(rs.getString("nombre"));
        producto.setPrecio(rs.getDouble("precio"));
        producto.setStock(rs.getInt("stock"));
        return producto;
    }
}

