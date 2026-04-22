package mementocommand.controlador;

import java.util.Stack;
import mementocommand.comando.Comando;

//El Caretaker gestiona el historial de comandos para permitir deshacer/rehacer.

public class GestorHistorial {
    private final Stack<Comando> historialDeshacer = new Stack<>();
    private final Stack<Comando> historialRehacer = new Stack<>();

    public void ejecutarComando(Comando comando) {
        comando.ejecutar();
        historialDeshacer.push(comando);
        historialRehacer.clear(); // Al hacer una nueva acción, se pierde la posibilidad de rehacer lo anterior
    }

    public void deshacer() {
        if (!historialDeshacer.isEmpty()) {
            Comando comando = historialDeshacer.pop();
            comando.deshacer();
            historialRehacer.push(comando);
        }
    }

    public void rehacer() {
        if (!historialRehacer.isEmpty()) {
            Comando comando = historialRehacer.pop();
            comando.ejecutar();
            historialDeshacer.push(comando);
        }
    }

    public boolean puedeDeshacer() {
        return !historialDeshacer.isEmpty();
    }

    public boolean puedeRehacer() {
        return !historialRehacer.isEmpty();
    }
}
