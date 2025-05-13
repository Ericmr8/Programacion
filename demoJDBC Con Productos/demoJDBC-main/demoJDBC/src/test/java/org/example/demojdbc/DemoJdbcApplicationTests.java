package org.example.demojdbc;

import org.example.demojdbc.model.Productos;
import org.example.demojdbc.repository.productoRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@Sql({"/schema.sql"})
class DemoJdbcApplicationTests {

    @Autowired
    productoRepository repositorio;

    @Test
    void borrarTodos() {
        repositorio.borrarTodos();
        List<Productos> lista = repositorio.buscarTodos();
        assertEquals(0, lista.size());
    }

    @Test
    void insertarProducto() {
        Productos producto = new Productos("Marmalade Boy", 10.4, 18);
        repositorio.insertar(producto);
        producto = new Productos("Lejía", 20.50, 20);
        repositorio.insertar(producto);
        List<Productos> lista = repositorio.buscarTodos();
        assertEquals(2, lista.size());
    }

    @Test
    void buscarTodos() {
        List<Productos> lista = repositorio.buscarTodos();
        assertEquals(2, lista.size());
    }

  @Test
    void borrarPersona() {
        Productos persona = new Productos("Lejía");
        repositorio.borrar(persona);
        List<Productos> lista = repositorio.buscarTodos();
        assertEquals(1, lista.size());

    }

    @Test
    void buscarUno() {
        Productos producto = repositorio.buscarUno("Marmalade Boy");
        assertEquals("Marmalade Boy", producto.getNombre());
    }
}
