
package monlau;

import monlau.dao.ProductoDAO;
import monlau.dao.ProductoDAOImpl;
import monlau.model.Producto;

import java.util.Scanner;

public class ProductoManager {
    public static void main(String[] args) {
        ProductoDAO producto =new ProductoDAOImpl();
        Integer idCrear = 103;
do  {
    System.out.println(" \n \n \n MENU \n 1.Registrar Producto \n 2.Actualizar Producto \n 3.Eliminar Producto \n 4.Salir" );
    Scanner sc = new Scanner(System.in);

    int option = sc.nextInt();
    if (option == 1) {

        //agregar nuevo producto
        System.out.println("Ingrese el nombre del producto");
        String nombre = sc.next();

        System.out.println("Ingrese el precio del producto");
        double precio = sc.nextDouble();
        producto.insert(new Producto(idCrear, nombre, precio));

        idCrear = idCrear + 1;

    } else if (option == 2) {
        System.out.print("Ingrese el ID del producto a actualizar: ");
        int idActualizar = sc.nextInt();

        System.out.print("Ingrese el nuevo nombre del producto: ");
        String nuevoNombre = sc.next();

        System.out.print("Ingrese el nuevo precio del producto: ");
        double nuevoPrecio = sc.nextDouble();

        producto.update(new Producto(idActualizar, nuevoNombre, nuevoPrecio));
        break;

    } else if (option == 3) {
        System.out.println("Que producto desea eliminar?");
        Integer id = sc.nextInt();
        System.out.println("Ingrese el nombre del producto");
        String nombre = sc.next();
        System.out.println("Ingrese el precio del producto");
        double precio = sc.nextDouble();

        producto.delete(new Producto(id, nombre, precio));
    }
    if (option == 4) {
        break;
    }
    } while( true );
    }
}
