package ar.edu.unju.fi.ejercicio5.main;

import ar.edu.unju.fi.ejercicio5.model.Producto;
import ar.edu.unju.fi.ejercicio5.model.PagoEfectivo;
import ar.edu.unju.fi.ejercicio5.model.PagoTarjeta;
import ar.edu.unju.fi.ejercicio5.interfaces.Pago;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        List<Producto> productos = new ArrayList<>();
        precargarProductos(productos);

        Scanner scanner = new Scanner(System.in);
        boolean salir = false;

        while (!salir) {
            System.out.println("Menú de opciones:");
            System.out.println("1 – Mostrar productos");
            System.out.println("2 – Realizar compra");
            System.out.println("3 – Salir");

            int opcion = scanner.nextInt();
            scanner.nextLine(); // Consumir el salto de línea

            switch (opcion) {
                case 1:
                    mostrarProductos(productos);
                    break;
                case 2:
                    realizarCompra(productos, scanner);
                    break;
                case 3:
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

    private static void mostrarProductos(List<Producto> productos) {
        for (Producto producto : productos) {
            System.out.println(producto);
        }
    }

    private static void realizarCompra(List<Producto> productos, Scanner scanner) {
        List<Producto> productosSeleccionados = new ArrayList<>();

        System.out.println("Seleccione los productos (Ingrese el código):");
        String codigo;
        do {
            System.out.print("Código (0 para finalizar): ");
            codigo = scanner.nextLine();
            if (!codigo.equals("0")) {
                Producto producto = buscarProductoPorCodigo(productos, codigo);
                if (producto != null && producto.isEstado()) {
                    productosSeleccionados.add(producto);
                } else if (producto != null && !producto.isEstado()) {
                    System.out.println("Producto no disponible.");
                } else {
                    System.out.println("Producto no encontrado.");
                }
            }
        } while (!codigo.equals("0"));

        double montoTotal = productosSeleccionados.stream().mapToDouble(Producto::getPrecioU).sum();

        if (productosSeleccionados.isEmpty()) {
            System.out.println("No se seleccionaron productos disponibles para la compra.");
            return;
        }

        System.out.println("Seleccione el método de pago:");
        System.out.println("1 – Pago efectivo");
        System.out.println("2 – Pago con tarjeta");

        int metodoPago = scanner.nextInt();
        scanner.nextLine(); // Consumir el salto de línea

        Pago pago;
        if (metodoPago == 1) {
            pago = new PagoEfectivo(LocalDate.now());
        } else {
            System.out.print("Ingrese el número de tarjeta: ");
            String numeroTarjeta = scanner.nextLine();
            pago = new PagoTarjeta(numeroTarjeta, LocalDate.now());
        }

        pago.realizarPago(montoTotal);
        pago.imprimirRecibo();
    }

    private static Producto buscarProductoPorCodigo(List<Producto> productos, String codigo) {
        for (Producto producto : productos) {
            if (producto.getCodigo().equals(codigo)) {
                return producto;
            }
        }
        return null;
    }
}

