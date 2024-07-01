package ar.edu.unju.fi.ejercicio4.main;

import ar.edu.unju.fi.ejercicio4.constantes.Posicion;
import ar.edu.unju.fi.ejercicio4.model.Jugador;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

public class Main {
    private static List<Jugador> jugadores = new ArrayList<>();
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        int opcion;

        do {
            mostrarMenu();
            opcion = leerEntero("Ingrese la opción: ");

            try {
                switch (opcion) {
                    case 1:
                        altaJugador();
                        break;
                    case 2:
                        mostrarJugadores();
                        break;
                    case 3:
                        modificarPosicion();
                        break;
                    case 4:
                        eliminarJugador();
                        break;
                    case 5:
                        System.out.println("Fin del programa.");
                        break;
                    default:
                        System.out.println("Opción no válida.");
                        break;
                }
            } catch (Exception e) {
                System.out.println("Error: " + e.getMessage());
                scanner.nextLine(); // Limpiar buffer de entrada
            } finally {
                System.out.println();
            }

        } while (opcion != 5);

        scanner.close();
    }

    private static void mostrarMenu() {
        System.out.println("*** Menú ***");
        System.out.println("1 - Alta de jugador");
        System.out.println("2 - Mostrar todos los jugadores");
        System.out.println("3 - Modificar posición de un jugador");
        System.out.println("4 - Eliminar un jugador");
        System.out.println("5 - Salir");
    }

    private static void altaJugador() {
        try {
            scanner.nextLine(); // Limpia el buffer de entrada antes de ingresar datos

            System.out.println("Ingrese nombre del jugador:");
            String nombre = scanner.nextLine(); // Leer el nombre

            System.out.println("Ingrese apellido del jugador:");
            String apellido = scanner.nextLine();

            System.out.println("Ingrese fecha de nacimiento (YYYY-MM-DD):");
            LocalDate fechaNacimiento = LocalDate.parse(scanner.nextLine());

            System.out.println("Ingrese nacionalidad:");
            String nacionalidad = scanner.nextLine();

            System.out.println("Ingrese estatura (en metros):");
            double estatura = Double.parseDouble(scanner.nextLine());

            System.out.println("Ingrese peso (en kg):");
            double peso = Double.parseDouble(scanner.nextLine());

            System.out.println("Ingrese posición (DELANTERO, MEDIO, DEFENSA, ARQUERO):");
            String posicionStr = scanner.nextLine().toUpperCase();
            Posicion posicion = Posicion.valueOf(posicionStr);

            Jugador jugador = new Jugador(nombre, apellido, fechaNacimiento, nacionalidad, estatura, peso, posicion);
            jugadores.add(jugador);
            System.out.println("Jugador agregado correctamente.");
        } catch (DateTimeParseException e) {
            System.out.println("Error al ingresar la fecha. Formato incorrecto.");
        } catch (NumberFormatException e) {
            System.out.println("Error al ingresar el número. Formato incorrecto.");
        } catch (IllegalArgumentException e) {
            System.out.println("Posición ingresada no válida.");
        }
    }
    
    private static void mostrarJugadores() {
        if (jugadores.isEmpty()) {
            System.out.println("No hay jugadores registrados.");
        } else {
            System.out.println("Listado de jugadores:");
            for (Jugador jugador : jugadores) {
                System.out.println(jugador);
            }
        }
    }

    private static void modificarPosicion() {
        if (jugadores.isEmpty()) {
            System.out.println("No hay jugadores registrados para modificar posición.");
            return;
        }

        System.out.println("Ingrese nombre del jugador:");
        String nombre = scanner.nextLine();

        System.out.println("Ingrese apellido del jugador:");
        String apellido = scanner.nextLine();

        boolean encontrado = false;
        for (Jugador jugador : jugadores) {
            if (jugador.getNombre().equalsIgnoreCase(nombre) && jugador.getApellido().equalsIgnoreCase(apellido)) {
                System.out.println("Ingrese nueva posición (DELANTERO, MEDIO, DEFENSA, ARQUERO):");
                String posicionStr = scanner.nextLine().toUpperCase();
                Posicion nuevaPosicion = Posicion.valueOf(posicionStr);
                jugador.setPosicion(nuevaPosicion);
                encontrado = true;
                System.out.println("Posición modificada correctamente.");
                break;
            }
        }

        if (!encontrado) {
            System.out.println("No se encontró al jugador.");
        }
    }

    private static void eliminarJugador() {
        if (jugadores.isEmpty()) {
            System.out.println("No hay jugadores registrados para eliminar.");
            return;
        }

        System.out.println("Ingrese nombre del jugador:");
        String nombre = scanner.nextLine();

        System.out.println("Ingrese apellido del jugador:");
        String apellido = scanner.nextLine();

        Iterator<Jugador> iterator = jugadores.iterator();
        while (iterator.hasNext()) {
            Jugador jugador = iterator.next();
            if (jugador.getNombre().equalsIgnoreCase(nombre) && jugador.getApellido().equalsIgnoreCase(apellido)) {
                iterator.remove();
                System.out.println("Jugador eliminado correctamente.");
                return;
            }
        }

        System.out.println("No se encontró al jugador.");
    }

    private static int leerEntero(String mensaje) {
        System.out.print(mensaje);
        while (!scanner.hasNextInt()) {
            System.out.println("Error. Ingrese un número válido.");
            scanner.next(); // Limpiar buffer de entrada
        }
        return scanner.nextInt();
    }
}