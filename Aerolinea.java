import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Aerolinea {
    private LinkedList<Vuelo> listaVuelos;

    public Aerolinea() {
        this.listaVuelos = new LinkedList<>();
    }

    public boolean agregarVuelo(Vuelo vuelo) {
        return listaVuelos.add(vuelo);
    }

    public Vuelo buscarVuelo(String nombre) {
        for (Vuelo v : listaVuelos) {
            if (v.getNombre().equals(nombre)) {
                return v;
            }
        }
        return null;
    }

    public boolean eliminarVuelo(String nombre) {
        Vuelo vuelo = buscarVuelo(nombre);
        if (vuelo != null && vuelo.getListaPasajeros().isEmpty()) {
            listaVuelos.remove(vuelo);
            return true;
        }
        return false;
    }

    public void mostrarVuelos() {
        for (Vuelo v : listaVuelos) {
            System.out.println(v);
        }
    }

    public void menu() {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("Menu Principal:");
            System.out.println("1. Gestión de Vuelos");
            System.out.println("2. Gestión de Pasajeros");
            System.out.println("3. Proceso de Abordaje");
            System.out.println("4. Salir");
            int opcion = scanner.nextInt();
            scanner.nextLine();

            switch (opcion) {
                case 1:
                    menuVuelos(scanner);
                    break;
                case 2:
                    menuPasajeros(scanner);
                    break;
                case 3:
                    procesoAbordaje(scanner);
                    break;
                case 4:
                    System.out.println("Saliendo...");
                    return;
                default:
                    System.out.println("Opción no válida");
            }
        }
    }

    private void menuVuelos(Scanner scanner) {
        System.out.println("Gestión de Vuelos:");
        System.out.println("1. Crear Vuelo");
        System.out.println("2. Editar Vuelo");
        System.out.println("3. Eliminar Vuelo");
        System.out.println("4. Mostrar Vuelos");
        int opcion = scanner.nextInt();
        scanner.nextLine();

        switch (opcion) {
            case 1:
                crearVuelo(scanner);
                break;
            case 2:
                editarVuelo(scanner);
                break;
            case 3:
                eliminarVuelo(scanner);
                break;
            case 4:
                mostrarVuelos();
                break;
            default:
                System.out.println("Opción no válida");
        }
    }

    private void crearVuelo(Scanner scanner) {
        System.out.println("Crear Vuelo:");
        System.out.print("Destino: ");
        String destino = scanner.nextLine();
        System.out.print("Horario: ");
        String horario = scanner.nextLine();
        System.out.print("Nombre: ");
        String nombre = scanner.nextLine();
        System.out.print("Capacidad: ");
        int capacidad = scanner.nextInt();
        scanner.nextLine();
        Vuelo vuelo = new Vuelo(destino, horario, nombre, capacidad);
        agregarVuelo(vuelo);
    }

    private void editarVuelo(Scanner scanner) {
        System.out.println("Editar Vuelo:");
        System.out.print("Nombre del vuelo a editar: ");
        String nombre = scanner.nextLine();
        Vuelo vuelo = buscarVuelo(nombre);
        if (vuelo != null) {
            System.out.print("Nuevo Destino: ");
            vuelo.setDestino(scanner.nextLine());
            System.out.print("Nuevo Horario: ");
            vuelo.setHorario(scanner.nextLine());
            System.out.print("Nuevo Nombre: ");
            vuelo.setNombre(scanner.nextLine());
            System.out.print("Nueva Capacidad: ");
            vuelo.setCapacidad(scanner.nextInt());
            scanner.nextLine();
        } else {
            System.out.println("Vuelo no encontrado");
        }
    }

    private void eliminarVuelo(Scanner scanner) {
        System.out.println("Eliminar Vuelo:");
        System.out.print("Nombre del vuelo a eliminar: ");
        String nombre = scanner.nextLine();
        if (eliminarVuelo(nombre)) {
            System.out.println("Vuelo eliminado");
        } else {
            System.out.println("Vuelo no encontrado o tiene pasajeros asociados");
        }
    }

    private void menuPasajeros(Scanner scanner) {
        System.out.println("Gestión de Pasajeros:");
        System.out.println("1. Crear Pasajero");
        System.out.println("2. Editar Pasajero");
        System.out.println("3. Eliminar Pasajero");
        System.out.println("4. Asocia Pasajero a Vuelo");
        System.out.println("5. Mostrar Pasajeros de un Vuelo");
        int opcion = scanner.nextInt();
        scanner.nextLine();

        switch (opcion) {
            case 1:
                crearPasajero(scanner);
                break;
            case 2:
                editarPasajero(scanner);
                break;
            case 3:
                eliminarPasajero(scanner);
                break;
            case 4:
                asociarPasajero(scanner);
                break;
            case 5:
                mostrarPasajerosVuelo(scanner);
                break;
            default:
                System.out.println("Opción no válida");
        }
    }

    private void crearPasajero(Scanner scanner) {
        System.out.println("Crear Pasajero:");
        System.out.print("ID: ");
        String id = scanner.nextLine();
        System.out.print("Nombre: ");
        String nombre = scanner.nextLine();
        System.out.print("Nacionalidad: ");
        String nacionalidad = scanner.nextLine();
        System.out.print("Asiento: ");
        int asiento = scanner.nextInt();
        scanner.nextLine();

        Pasajero pasajero = new Pasajero(id, nombre, nacionalidad, asiento);
        System.out.print("Nombre del vuelo al que desea asociar el pasajero: ");
        String nombreVuelo = scanner.nextLine();
        Vuelo vuelo = buscarVuelo(nombreVuelo);
        if (vuelo != null) {
            if (vuelo.agregarPasajero(pasajero)) {
                System.out.println("Pasajero agregado correctamente");
            } else {
                System.out.println("Error: ID o Asiento duplicado o capacidad del vuelo alcanzada");
            }
        } else {
            System.out.println("Vuelo no encontrado");
        }
    }

    private void editarPasajero(Scanner scanner) {
        System.out.println("Editar Pasajero:");
        System.out.print("ID del pasajero a editar: ");
        String id = scanner.nextLine();
        System.out.print("Nombre del vuelo donde se encuentra el pasajero: ");
        String nombreVuelo = scanner.nextLine();
        Vuelo vuelo = buscarVuelo(nombreVuelo);
        if (vuelo != null) {
            Pasajero pasajero = vuelo.buscarPasajero(id);
            if (pasajero != null) {
                System.out.print("Nuevo Nombre: ");
                pasajero.setNombre(scanner.nextLine());
                System.out.print("Nueva Nacionalidad: ");
                pasajero.setNacionalidad(scanner.nextLine());
                System.out.print("Nuevo Asiento: ");
                int nuevoAsiento = scanner.nextInt();
                scanner.nextLine();
                pasajero.setAsiento(nuevoAsiento);
            } else {
                System.out.println("Pasajero no encontrado");
            }
        } else {
            System.out.println("Vuelo no encontrado");
        }
    }

    private void eliminarPasajero(Scanner scanner) {
        System.out.println("Eliminar Pasajero:");
        System.out.print("ID del pasajero a eliminar: ");
        String id = scanner.nextLine();
        System.out.print("Nombre del vuelo donde se encuentra el pasajero: ");
        String nombreVuelo = scanner.nextLine();
        Vuelo vuelo = buscarVuelo(nombreVuelo);
        if (vuelo != null) {
            if (vuelo.eliminarPasajero(id)) {
                System.out.println("Pasajero eliminado");
            } else {
                System.out.println("Pasajero no encontrado");
            }
        } else {
            System.out.println("Vuelo no encontrado");
        }
    }

    private void asociarPasajero(Scanner scanner) {
        System.out.println("Asociar Pasajero a Vuelo:");
        System.out.print("ID del pasajero: ");
        String id = scanner.nextLine();
        System.out.print("Nombre del vuelo: ");
        String nombreVuelo = scanner.nextLine();
        Vuelo vuelo = buscarVuelo(nombreVuelo);
        if (vuelo != null) {
            Pasajero pasajero = vuelo.buscarPasajero(id);
            if (pasajero != null) {
                System.out.println("Pasajero ya está asociado a este vuelo");
            } else {
                crearPasajero(scanner);
            }
        } else {
            System.out.println("Vuelo no encontrado");
        }
    }

    private void mostrarPasajerosVuelo(Scanner scanner) {
        System.out.println("Mostrar Pasajeros de un Vuelo:");
        System.out.print("Nombre del vuelo: ");
        String nombreVuelo = scanner.nextLine();
        Vuelo vuelo = buscarVuelo(nombreVuelo);
        if (vuelo != null) {
            for (Pasajero p : vuelo.getListaPasajeros()) {
                System.out.println(p);
            }
        } else {
            System.out.println("Vuelo no encontrado");
        }
    }

    private void procesoAbordaje(Scanner scanner) {
        System.out.println("Proceso de Abordaje:");
        System.out.print("Nombre del vuelo: ");
        String nombreVuelo = scanner.nextLine();
        Vuelo vuelo = buscarVuelo(nombreVuelo);
        if (vuelo != null) {
            Queue<Pasajero> colaPar = new LinkedList<>();
            Queue<Pasajero> colaImpar = new LinkedList<>();
            for (Pasajero p : vuelo.getListaPasajeros()) {
                if (p.getAsiento() % 2 == 0) {
                    colaPar.add(p);
                } else {
                    colaImpar.add(p);
                }
            }

            if (vuelo.getListaPasajeros().isEmpty()) {
                System.out.println("El vuelo no tiene pasajeros");
            } else {
                System.out.println("Abordaje de pasajeros con asientos pares:");
                while (!colaPar.isEmpty()) {
                    System.out.println(colaPar.poll());
                }

                System.out.println("Abordaje de pasajeros con asientos impares:");
                while (!colaImpar.isEmpty()) {
                    System.out.println(colaImpar.poll());
                }
            }
        } else {
            System.out.println("Vuelo no encontrado");
        }
    }
}

