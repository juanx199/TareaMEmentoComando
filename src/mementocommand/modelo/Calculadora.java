package mementocommand.modelo;

//El Originador: La lógica de la calculadora que mantiene el estado actual

public class Calculadora {
    private double resultadoActual;

    public Calculadora() {
        this.resultadoActual = 0;
    }

    public double getResultadoActual() {
        return resultadoActual;
    }

    public void setResultadoActual(double valor) {
        this.resultadoActual = valor;
    }

    public void sumar(double n) {
        resultadoActual += n;
    }

    public void restar(double n) {
        resultadoActual -= n;
    }

    public void multiplicar(double n) {
        resultadoActual *= n;
    }

    public void dividir(double n) {
        if (n != 0) {
            resultadoActual /= n;
        } else {
            throw new ArithmeticException("División por cero");
        }
    }

    public void limpiar() {
        resultadoActual = 0;
    }

    // Captura el estado actual en un Memento.
    public Memento guardar() {
        return new Memento(resultadoActual);
    }

    // Restaura el estado desde un Memento.
    public void restaurar(Memento m) {
        this.resultadoActual = m.getEstado();
    }
}
