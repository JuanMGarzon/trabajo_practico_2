package ar.edu.unju.fi.ejercicio7.main;

import ar.edu.unju.fi.ejercicio5.model.Producto;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {
        List<Producto> productos = new ArrayList<>();
        precargarProductos(productos);

        Scanner scanner = new Scanner(System.in);
        boolean salir = false;

        while (!salir) {
            System.out.println("Menú de opciones:");
            System.out.println("1 – Mostrar productos disponibles");
            System.out.println("2 – Mostrar los productos faltantes");
            System.out.println("3 – Incrementar los precios de los productos en un 20%");
            System.out.println("4 – Mostrar los productos de categoría Electrohogar disponibles");
            System.out.println("5 – Ordenar los productos por precio de forma descendente");
            System.out.println("6 – Mostrar los productos con los nombres en mayúsculas");
            System.out.println("7 – Salir");

            int opcion = scanner.nextInt();
            scanner.nextLine(); // Consumir el salto de línea

            switch (opcion) {
                case 1:
                    mostrarProductos(productos, p -> p.isEstado());
                    break;
                case 2:
                    mostrarProductosFaltantes(productos);
                    break;
                case 3:
                    incrementarPrecios(productos);
                    break;
                case 4:
                    mostrarElectrohogarDisponibles(productos);
                    break;
                case 5:
                    ordenarPorPrecioDescendente(productos);
                    break;
                case 6:
                    mostrarNombresMayusculas(productos);
                    break;
                case 7:
                    salir = true;
                    break;
                default:
                    System.out.println("Opción no válida. Por favor, intente de nuevo.");
            }
        }

        scanner.close();
    }

    private static void precargarProductos(List<Producto> productos) {
        productos.add(new Producto("001", "Teléfono Samsung", 35000, Producto.OrigenFabricacion.CHINA, Producto.Categoria.TELEFONIA, true));
        productos.add(new Producto("002", "Laptop Dell", 85000, Producto.OrigenFabricacion.ARGENTINA, Producto.Categoria.INFORMATICA, true));
        productos.add(new Producto("003", "Televisor LG", 120000, Producto.OrigenFabricacion.BRASIL, Producto.Categoria.ELECTROHOGAR, true));
        productos.add(new Producto("004", "Aspiradora Philips", 25000, Producto.OrigenFabricacion.URUGUAY, Producto.Categoria.ELECTROHOGAR, false));
        productos.add(new Producto("005", "Taladro Bosch", 15000, Producto.OrigenFabricacion.URUGUAY, Producto.Categoria.HERRAMIENTAS, true));
        productos.add(new Producto("006", "Impresora HP", 40000, Producto.OrigenFabricacion.BRASIL, Producto.Categoria.INFORMATICA, false));
        productos.add(new Producto("007", "Mouse Logitech", 3000, Producto.OrigenFabricacion.CHINA, Producto.Categoria.INFORMATICA, true));
        productos.add(new Producto("008", "Refrigerador Samsung", 130000, Producto.OrigenFabricacion.BRASIL, Producto.Categoria.ELECTROHOGAR, true));
        productos.add(new Producto("009", "Smartwatch Apple", 50000, Producto.OrigenFabricacion.CHINA, Producto.Categoria.TELEFONIA, true));
        productos.add(new Producto("010", "Lavadora Whirlpool", 70000, Producto.OrigenFabricacion.BRASIL, Producto.Categoria.ELECTROHOGAR, false));
        productos.add(new Producto("011", "Horno Eléctrico Atma", 15000, Producto.OrigenFabricacion.ARGENTINA, Producto.Categoria.ELECTROHOGAR, true));
        productos.add(new Producto("012", "Tablet Huawei", 25000, Producto.OrigenFabricacion.CHINA, Producto.Categoria.TELEFONIA, true));
        productos.add(new Producto("013", "Cámara Canon", 90000, Producto.OrigenFabricacion.URUGUAY, Producto.Categoria.TELEFONIA, false));
        productos.add(new Producto("014", "Parlante JBL", 12000, Producto.OrigenFabricacion.CHINA, Producto.Categoria.TELEFONIA, true));
        productos.add(new Producto("015", "Consola PlayStation", 150000, Producto.OrigenFabricacion.ARGENTINA, Producto.Categoria.INFORMATICA, false));
    }


    private static void mostrarProductos(List<Producto> productos, Predicate<Producto> predicado) {
        Consumer<Producto> consumidor = p -> System.out.println(p);
        productos.stream()
                .filter(predicado)
                .forEach(consumidor);
    }

    private static void mostrarProductosFaltantes(List<Producto> productos) {
        Predicate<Producto> predicado = p -> !p.isEstado();
        productos.stream()
                .filter(predicado)
                .forEach(System.out::println);
    }

    private static void incrementarPrecios(List<Producto> productos) {
        Function<Producto, Producto> incrementar = p -> {
            p.setPrecioU(p.getPrecioU() * 1.20);
            return p;
        };
        List<Producto> productosIncrementados = productos.stream()
                .map(incrementar)
                .collect(Collectors.toList());

        System.out.println("Precios incrementados:");
        productosIncrementados.forEach(System.out::println);
    }

    private static void mostrarElectrohogarDisponibles(List<Producto> productos) {
        Predicate<Producto> predicado = p -> p.getCategoria() == Producto.Categoria.ELECTROHOGAR && p.isEstado();
        productos.stream()
                .filter(predicado)
                .forEach(System.out::println);
    }

    private static void ordenarPorPrecioDescendente(List<Producto> productos) {
        productos.sort(Comparator.comparing(Producto::getPrecioU).reversed());
        productos.forEach(System.out::println);
    }

    private static void mostrarNombresMayusculas(List<Producto> productos) {
        Function<Producto, String> aMayusculas = p -> p.getDescripcion().toUpperCase();
        productos.stream()
                .map(aMayusculas)
                .forEach(System.out::println);
    }
}
