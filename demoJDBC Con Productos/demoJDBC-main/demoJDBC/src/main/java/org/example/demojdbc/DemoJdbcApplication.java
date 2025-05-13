package org.example.demojdbc;

import org.example.demojdbc.model.Productos;
import org.example.demojdbc.repository.productoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Scanner;

@SpringBootApplication
public class DemoJdbcApplication implements CommandLineRunner {

    @Autowired
    private productoRepository productoRepository;

    public static void main(String[] args) {
        SpringApplication.run(DemoJdbcApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        Scanner scanner = new Scanner(System.in);
        boolean running = true;

        while (running) {
            mostrarMenu();
            int opcion = leerEntero(scanner, "Seleccione una opcion: ");

            if (opcion == 1) {
                insertarProducto(scanner);
            } else if (opcion == 2) {
                buscarTodas();
            } else if (opcion == 3) {
                buscarUna(scanner);
            } else if (opcion == 4) {
                borrarUna(scanner);
            } else if (opcion == 5) {
                borrarTodas();
            } else if (opcion == 0) {
                System.out.println("Saliendo del programa...");
               break;
            } else {
                System.out.println("Opcion no valida. Intente de nuevo.");
            }
        }
    }

    private void mostrarMenu() {
        System.out.println("\n Menú:");
        System.out.println("1. Insertar producto");
        System.out.println("2. Buscar todos los productos");
        System.out.println("3. Buscar un producto por nombre");
        System.out.println("4. Borrar un producto por nombre");
        System.out.println("5. Borrar todos los producto");
        System.out.println("0. Salir");
    }

    private int leerEntero(Scanner scanner, String mensaje) {
        System.out.print(mensaje);
        while (!scanner.hasNextInt()) {
            System.out.print("Por favor, ingrese un numero valido: ");
            scanner.next();
        }
        return scanner.nextInt();
    }

    private void insertarProducto(Scanner scanner) {
        scanner.nextLine();
        System.out.print("Ingrese el nombre: ");
        String nombre = scanner.nextLine();
        System.out.print("Ingrese el precio: ");
        double precio = scanner.nextDouble();
        int stock = leerEntero(scanner, "Ingrese cuanto stock: ");
        scanner.nextLine();
        System.out.print("Ingrese el precio: ");
        Productos producto = new Productos(nombre, precio, stock);
        productoRepository.insertar(producto);
        System.out.println("Producto insertado correctamente");
    }

    private void buscarTodas() {
        System.out.println("Listado de todos los productos:");
        productoRepository.buscarTodos().forEach(System.out::println);
    }

    private void buscarUna(Scanner scanner) {
        scanner.nextLine();
        System.out.print("Ingrese el nombre del producto a buscar: ");
        String nombre = scanner.nextLine();
        Productos producto = productoRepository.buscarUno(nombre);

        if (producto != null) {
            System.out.println("Productos encontrada: " + producto);
        } else {
            System.out.println("No se encontro ningun producto con ese nombre.");
        }
    }

    private void borrarUna(Scanner scanner) {
        scanner.nextLine();
        System.out.print("Ingrese el nombre del producto a borrar: ");
        String nombre = scanner.nextLine();

        Productos producto = productoRepository.buscarUno(nombre);
        if (producto != null) {
            productoRepository.borrar(producto);
            System.out.println("Productos borrada correctamente.");
        } else {
            System.out.println("No se encontro ninguna persona con ese nombre.");
        }
    }

    private void borrarTodas() {
        productoRepository.borrarTodos();
        System.out.println("Todas las personas han sido borradas.");
    }
}

