package org.example.demojdbc.repository;

import java.util.List;

import org.example.demojdbc.model.Productos;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public class productoRepository {

    private JdbcTemplate plantilla;

    public productoRepository(JdbcTemplate plantilla) {
        this.plantilla = plantilla;
    }
    @Transactional
    public void insertar(Productos producto) {
        plantilla.update("insert into Productos (nombre, precio, stock) values (?,?,?)",
                producto.getNombre(), producto.getPrecio(), producto.getStock());
    }

    public List<Productos> buscarTodos() {
        return plantilla.query("select * from productos ",new productoMapper());
    }

    public Productos buscarUno(String nombre) {
        List<Productos> resultados = plantilla.query("select * from productos where nombre=?",new productoMapper(),nombre);
        if (resultados.isEmpty()) {
            System.out.println("No se encontró ningún resultado para: " + nombre);
            return null;
        } else {
            System.out.println("Encontrado: " + resultados.get(0));
            return resultados.get(0);
        }
    }
    @Transactional
    public void borrar(Productos persona) {
        plantilla.update("delete from productos where nombre=?",persona.getNombre());
    }
    @Transactional
    public void borrarTodos() {
        plantilla.update("delete from productos");
    }
}
