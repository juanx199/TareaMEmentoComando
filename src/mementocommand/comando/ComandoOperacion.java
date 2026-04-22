package mementocommand.comando;

import mementocommand.modelo.Calculadora;
import mementocommand.modelo.Memento;

//Comando concreto que realiza una operación aritmética y guarda un Memento del estado previo para poder deshacer
// 

public class ComandoOperacion implements Comando {
    private final Calculadora calculadora;
    private final String operacion;
    private final double valor;
    private Memento mementoPrevio;

    public ComandoOperacion(Calculadora calculadora, String operacion, double valor) {
        this.calculadora = calculadora;
        this.operacion = operacion;
        this.valor = valor;
    }

    @Override
    public void ejecutar() {
        // Antes de ejecutar, guardamos el estado actual (Memento)
        mementoPrevio = calculadora.guardar();

        switch (operacion) {
            case "+":
                calculadora.sumar(valor);
                break;
            case "-":
                calculadora.restar(valor);
                break;
            case "*":
                calculadora.multiplicar(valor);
                break;
            case "/":
                calculadora.dividir(valor);
                break;
            case "C":
                calculadora.limpiar();
                break;
            case "SET":
                calculadora.setResultadoActual(valor);
                break;
        }
    }

    @Override
    public void deshacer() {
        if (mementoPrevio != null) {
            calculadora.restaurar(mementoPrevio);
        }
    }

    @Override
    public String getDescripcion() {
        return operacion + " " + valor;
    }
}
