package mementocommand.comando;

// Interfaz base para todas las operaciones de la calculadora

public interface Comando {
    // Ejecuta la operación y guarda el estado necesario para deshacer
    void ejecutar();

    // Revierte la operación restaurando el estado previo

    void deshacer();

    // Devuelve una descripción del comando para trazabilidad
    String getDescripcion();
}
