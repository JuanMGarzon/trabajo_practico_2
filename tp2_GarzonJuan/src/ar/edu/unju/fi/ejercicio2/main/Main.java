package ar.edu.unju.fi.ejercicio2.main;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

import ar.edu.unju.fi.ejercicio2.constantes.Mes;
import ar.edu.unju.fi.ejercicio2.model.Efemeride;

public class Main {
    private static Scanner scanner = new Scanner(System.in);
    private static List<Efemeride> listado = new ArrayList<>();

    public static void main(String[] args) {
        int opcion;

        do {
            mostrarMenu();
            opcion = leerEntero("Ingrese la opción: ");

            switch (opcion) {
                case 1:
                    crearEfemeride();
                    break;
                case 2:
                    mostrarEfemerides();
                    break;
                case 3:
                    eliminarEfemeride();
                    break;
                case 4:
                    modificarEfemeride();
                    break;
                case 5:
                    System.out.println("Fin del programa.");
                    break;
                default:
                    System.out.println("Opción no válida.");
                    break;
            }
        } while (opcion != 5);

        scanner.close();
    }

    private static void mostrarMenu() {
        System.out.println("*** Menu ***");
        System.out.println("1 - Crear efeméride");
        System.out.println("2 - Mostrar efemérides");
        System.out.println("3 - Eliminar efeméride");
        System.out.println("4 - Modificar efeméride");
        System.out.println("5 - Salir");
    }

    private static void crearEfemeride() {
        Efemeride efemeride = new Efemeride();

        efemeride = ingresarCodigo(efemeride);
        efemeride = ingresarMes(efemeride);
        efemeride = ingresarDia(efemeride);
        efemeride = ingresarDetalle(efemeride);

        listado.add(efemeride);
        System.out.println("Efeméride creada correctamente.");
    }

    private static Efemeride ingresarCodigo(Efemeride efemeride) {
        boolean valido = false;
        do {
            try {
                System.out.println("Ingrese el código: ");
                String codigo = scanner.next();
                efemeride.setCodigo(codigo);
                valido = true;
            } catch (Exception e) {
                System.out.println("Error en el ingreso de datos.");
                scanner.nextLine(); // Limpiar el buffer de entrada
            }
        } while (!valido);
        return efemeride;
    }

    private static Efemeride ingresarMes(Efemeride efemeride) {
        boolean valido = false;
        do {
            try {
                System.out.println("Ingrese el número de mes (1-12): ");
                int mesNum = scanner.nextInt();
                scanner.nextLine();

                if (mesNum >= 1 && mesNum <= 12) {
                    Mes mes = Mes.values()[mesNum - 1];
                    efemeride.setMes(mes);
                    valido = true;
                } else {
                    System.out.println("Número de mes fuera de rango.");
                }
            } catch (Exception e) {
                System.out.println("Error en el ingreso de datos.");
                scanner.nextLine(); // Limpiar el buffer de entrada
            }
        } while (!valido);
        return efemeride;
    }

    private static Efemeride ingresarDia(Efemeride efemeride) {
        boolean valido = false;
        do {
            try {
                System.out.println("Ingrese el día: ");
                int dia = scanner.nextInt();
                if (dia >= 1 && dia <= 31) {
                    efemeride.setDia(dia);
                    valido = true;
                } else {
                    System.out.println("Día fuera de rango.");
                }
            } catch (Exception e) {
                System.out.println("Error en el ingreso de datos.");
                scanner.nextLine(); // Limpiar el buffer de entrada
            }
        } while (!valido);
        return efemeride;
    }

    private static Efemeride ingresarDetalle(Efemeride efemeride) {
        boolean valido = false;
        scanner.nextLine(); // Limpia el búfer antes de leer el detalle
        do {
            try {
                System.out.println("Ingrese el detalle: ");
                String detalle = scanner.nextLine(); // Leer la línea completa
                efemeride.setDetalle(detalle);
                valido = true;
            } catch (Exception e) {
                System.out.println("Error en el ingreso de datos.");
            }
        } while (!valido);
        return efemeride;
    }


    private static void mostrarEfemerides() {
        if (listado.isEmpty()) {
            System.out.println("No hay efemérides para mostrar.");
        } else {
            int p = 1;
            for (Efemeride efemeride : listado) {
                System.out.println("Datos de la efemeride " + p++);
                System.out.println(efemeride);
            }
        }
    }

    private static void eliminarEfemeride() {
        if (listado.isEmpty()) {
            System.out.println("No hay efemérides para eliminar.");
            return;
        }

        System.out.println("Ingrese el código de la efeméride a eliminar: ");
        String codigo = scanner.next();

        Iterator<Efemeride> iterator = listado.iterator();
        while (iterator.hasNext()) {
            Efemeride efemeride = iterator.next();
            if (efemeride.getCodigo().equals(codigo)) {
                iterator.remove();
                System.out.println("Efeméride eliminada correctamente.");
                return;
            }
        }

        System.out.println("Efeméride no encontrada.");
    }

    private static void modificarEfemeride() {
        if (listado.isEmpty()) {
            System.out.println("No hay efemérides para modificar.");
            return;
        }

        System.out.println("Ingrese el código de la efeméride a modificar: ");
        String codigo = scanner.next();

        for (Efemeride efemeride : listado) {
            if (efemeride.getCodigo().equals(codigo)) {
                modificarDetalleEfemeride(efemeride);
                System.out.println("Efeméride modificada correctamente.");
                return;
            }
        }

        System.out.println("Efeméride no encontrada.");
    }

    private static void modificarDetalleEfemeride(Efemeride efemeride) {
        int opcion;
        do {
            mostrarMenuModificacion();
            opcion = leerEntero("Ingrese opción: ");

            switch (opcion) {
                case 1:
                    efemeride = ingresarMes(efemeride);
                    break;
                case 2:
                    efemeride = ingresarDia(efemeride);
                    break;
                case 3:
                    efemeride = ingresarDetalle(efemeride);
                    break;
                case 4:
                    break;
                default:
                    System.out.println("Opción no válida.");
                    break;
            }
        } while (opcion != 4);
    }

    private static void mostrarMenuModificacion() {
        System.out.println("*** Modificar efeméride ***");
        System.out.println("1 - Modificar mes");
        System.out.println("2 - Modificar día");
        System.out.println("3 - Modificar detalle");
        System.out.println("4 - Salir");
    }

    private static int leerEntero(String mensaje) {
        System.out.print(mensaje);
        while (!scanner.hasNextInt()) {
            System.out.println("Error. Ingrese un número válido.");
            scanner.next(); // Limpiar el buffer
        }
        return scanner.nextInt();
    }
}
