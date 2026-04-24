# 🧮 Calculadora con Historial — Patrones Memento + Command

## 📋 Descripción del Proyecto

Este proyecto implementa una **calculadora de escritorio** desarrollada en **Java con interfaz gráfica Swing**, que combina dos patrones de diseño clásicos del Gang of Four:

- **Patrón Command**: cada operación aritmética (suma, resta, multiplicación, división, borrado) se encapsula como un objeto `Comando`, lo que permite ejecutarlas, deshacerlas y rehacerlas de forma uniforme.
- **Patrón Memento**: antes de ejecutar cada comando, se captura el estado interno de la calculadora en un objeto `Memento` (valor numérico actual), garantizando que cualquier deshacer restaure el estado exacto previo a la operación.

### ✨ Funcionalidades

| Funcionalidad | Descripción |
|---|---|
| Operaciones básicas | Suma, Resta, Multiplicación, División |
| Borrado (`C`) | Restablece el resultado a 0 |
| **Deshacer** | Revierte la última operación realizada |
| **Rehacer** | Vuelve a aplicar una operación deshecha |
| Historial ilimitado | Sin límite de pasos por sesión |
| Encadenamiento | Permite operaciones consecutivas tipo `3 + 5 * 2` |

### 🏗️ Arquitectura

El proyecto está organizado en cuatro paquetes principales:

```
mementocommand/
├── MementoCommand.java        # Punto de entrada (main)
├── modelo/
│   ├── Calculadora.java       # Originador (lógica y estado)
│   └── Memento.java           # Snapshot del estado
├── comando/
│   ├── Comando.java           # Interfaz Command
│   └── ComandoOperacion.java  # Comando concreto
├── controlador/
│   └── GestorHistorial.java   # Caretaker (pilas deshacer/rehacer)
└── gui/
    └── CalculadoraGUI.java    # Interfaz gráfica Swing
```

---

## 👥 Integrantes del Equipo

| Nombre | Código |
|---|---|
| Juan Daniel Palomino García | 20232020065 |
| Juan Camilo Carvajal Camargo | 20232020026 |
| Josep Emmanuel Leon Joya | 20231020160 |

---

## 📊 Diagrama de Clases UML

> ⚠️ **El diagrama UML fue elaborado en Papyrus.**

### Vista del Diagrama

<!-- Inserta aquí la imagen del diagrama UML exportada desde Papyrus -->

![Diagrama de Clases UML](./diagrama_uml.png)


