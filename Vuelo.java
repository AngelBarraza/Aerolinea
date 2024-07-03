import java.util.LinkedList;

public class Vuelo {
    private String destino;
    private String horario;
    private String nombre;
    private int capacidad;
    private LinkedList<Pasajero> listaPasajeros;

    public Vuelo(String destino, String horario, String nombre, int capacidad) {
        this.destino = destino;
        this.horario = horario;
        this.nombre = nombre;
        this.capacidad = capacidad;
        this.listaPasajeros = new LinkedList<>();
    }

    public String getDestino() {
        return destino;
    }

    public String getHorario() {
        return horario;
    }

    public String getNombre() {
        return nombre;
    }

    public int getCapacidad() {
        return capacidad;
    }

    public LinkedList<Pasajero> getListaPasajeros() {
        return listaPasajeros;
    }

    public void setDestino(String destino) {
        this.destino = destino;
    }

    public void setHorario(String horario) {
        this.horario = horario;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setCapacidad(int capacidad) {
        this.capacidad = capacidad;
    }

    public boolean agregarPasajero(Pasajero pasajero) {
        if (listaPasajeros.size() >= capacidad) {
            return false;
        }
        for (Pasajero p : listaPasajeros) {
            if (p.getId().equals(pasajero.getId()) || p.getAsiento() == pasajero.getAsiento()) {
                return false;
            }
        }
        listaPasajeros.add(pasajero);
        return true;
    }

    public Pasajero buscarPasajero(String id) {
        for (Pasajero p : listaPasajeros) {
            if (p.getId().equals(id)) {
                return p;
            }
        }
        return null;
    }

    public boolean eliminarPasajero(String id) {
        for (Pasajero p : listaPasajeros) {
            if (p.getId().equals(id)) {
                listaPasajeros.remove(p);
                return true;
            }
        }
        return false;
    }

    @Override
    public String toString() {
        return "Vuelo{" +
                "destino='" + destino + '\'' +
                ", horario='" + horario + '\'' +
                ", nombre='" + nombre + '\'' +
                ", capacidad=" + capacidad +
                ", listaPasajeros=" + listaPasajeros +
                '}';
    }
}


