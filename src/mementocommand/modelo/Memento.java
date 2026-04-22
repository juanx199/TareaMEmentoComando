package mementocommand.modelo;

//Representa el estado interno de la calculadora en un momento dado
// Es una clase inmutable para garantizar la integridad del historial

public class Memento {
    private final double estado;

    public Memento(double estado) {
        this.estado = estado;
    }

    public double getEstado() {
        return estado;
    }
}
