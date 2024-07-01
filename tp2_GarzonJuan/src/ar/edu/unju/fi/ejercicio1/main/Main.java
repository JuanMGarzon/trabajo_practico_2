package ar.edu.unju.fi.ejercicio1.main;

import ar.edu.unju.fi.ejercicio1.model.Producto;
import ar.edu.unju.fi.ejercicio1.model.Producto.Categoria;
import ar.edu.unju.fi.ejercicio1.model.Producto.OrigenFabricacion;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    private static List<Producto> listado = new ArrayList<>();
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        int opcion;
        do {
            mostrarMenu();
            opcion = leerEntero("Ingrese Opcion: ");

            switch (opcion) {
                case 1:
                    crearProducto();
                    break;
                case 2:
                    mostrarProductos();
                    break;
                case 3:
                    modificarProducto();
                    break;
                case 4:
                    System.out.println("Fin de Programa");
                    break;
                default:
                    System.out.println("Opcion no disponible");
                    break;
            }
        } while (opcion != 4);

        scanner.close();
    }

    private static void mostrarMenu() {
        System.out.println("***Menu***");
        System.out.println("1 – Crear Producto");
        System.out.println("2 – Mostrar productos");
        System.out.println("3 – Modificar producto");
        System.out.println("4 – Salir");
    }

    private static void crearProducto() {
        Producto producto = new Producto();

        System.out.println("Ingrese el código: ");
        producto.setCodigo(scanner.next());

        System.out.println("Ingrese la descripción: ");
        scanner.nextLine(); // Limpiar el buffer
        producto.setDescripcion(scanner.nextLine());

        producto.setPrecioU(leerDouble("Ingrese el precio: "));

        producto.setOrigenFabricacion(seleccionarOrigenFabricacion());

        producto.setCategoria(seleccionarCategoria());

        listado.add(producto);
        System.out.println("Producto creado exitosamente.");
    }

    private static OrigenFabricacion seleccionarOrigenFabricacion() {
        int opcion;
        do {
            System.out.println("---- Origen de fabricación ------");
            System.out.println("1 - Argentina");
            System.out.println("2 - China");
            System.out.println("3 - Brasil");
            System.out.println("4 - Uruguay");
            opcion = leerEntero("Ingrese una opción: ");
        } while (opcion < 1 || opcion > 4);

        switch (opcion) {
            case 1:
                return OrigenFabricacion.ARGENTINA;
            case 2:
                return OrigenFabricacion.CHINA;
            case 3:
                return OrigenFabricacion.BRASIL;
            case 4:
                return OrigenFabricacion.URUGUAY;
            default:
                return null;
        }
    }

    private static Categoria seleccionarCategoria() {
        int opcion;
        do {
            System.out.println("---- Categoria ------");
            System.out.println("1 - Telefonia");
            System.out.println("2 - Informatica");
            System.out.println("3 - Electro Hogar");
            System.out.println("4 - Herramientas");
            opcion = leerEntero("Ingrese una opción: ");
        } while (opcion < 1 || opcion > 4);

        switch (opcion) {
            case 1:
                return Categoria.TELEFONIA;
            case 2:
                return Categoria.INFORMATICA;
            case 3:
                return Categoria.ELECTROHOGAR;
            case 4:
                return Categoria.HERRAMIENTAS;
            default:
                return null;
        }
    }

    private static void mostrarProductos() {
        if (listado.isEmpty()) {
            System.out.println("No hay productos para mostrar.");
        } else {
            int contador = 1;
            for (Producto producto : listado) {
                System.out.println("Datos del producto " + contador++);
                System.out.println(producto.toString());
            }
        }
    }

    private static void modificarProducto() {
        if (listado.isEmpty()) {
            System.out.println("No hay productos para modificar.");
            return;
        }

        System.out.println("Ingrese el código del producto a modificar: ");
        String codigo = scanner.next();

        boolean encontrado = false;
        for (Producto producto : listado) {
            if (producto.getCodigo().equals(codigo)) {
                encontrado = true;
                modificarDetalleProducto(producto);
                break;
            }
        }

        if (!encontrado) {
            System.out.println("Producto no encontrado.");
        }
    }

    private static void modificarDetalleProducto(Producto producto) {
        int opcion;
        do {
            System.out.println("*** Modificar Producto ***");
            System.out.println("1 – Descripcion");
            System.out.println("2 – Precio");
            System.out.println("3 – Origen de fabricacion");
            System.out.println("4 - Categoria");
            System.out.println("5 - Salir");
            opcion = leerEntero("Ingrese Opcion: ");

            switch (opcion) {
                case 1:
                    System.out.println("Ingrese la nueva descripción: ");
                    scanner.nextLine(); // Limpiar el buffer
                    producto.setDescripcion(scanner.nextLine());
                    break;
                case 2:
                    producto.setPrecioU(leerDouble("Ingrese el nuevo precio: "));
                    break;
                case 3:
                    producto.setOrigenFabricacion(seleccionarOrigenFabricacion());
                    break;
                case 4:
                    producto.setCategoria(seleccionarCategoria());
                    break;
                case 5:
                    break;
                default:
                    System.out.println("Opcion no válida.");
                    break;
            }
        } while (opcion != 5);
    }

    private static int leerEntero(String mensaje) {
        System.out.print(mensaje);
        while (!scanner.hasNextInt()) {
            System.out.println("Error. Ingrese un número válido.");
            scanner.next();
        }
        return scanner.nextInt();
    }

    private static double leerDouble(String mensaje) {
        System.out.print(mensaje);
        while (!scanner.hasNextDouble()) {
            System.out.println("Error. Ingrese un número válido.");
            scanner.next();
        }
        return scanner.nextDouble();
    }
}
