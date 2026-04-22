package mementocommand.gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import mementocommand.comando.ComandoOperacion;
import mementocommand.controlador.GestorHistorial;
import mementocommand.modelo.Calculadora;

public class CalculadoraGUI extends JFrame {
    private final Calculadora calculadora;
    private final GestorHistorial gestor;

    private final JTextField pantalla;
    private String operacionPendiente = "";
    private double primerValor = 0;
    private boolean nuevaEntrada = true;

    public CalculadoraGUI() {
        this.calculadora = new Calculadora();
        this.gestor = new GestorHistorial();

        setTitle("Calculadora con Historial (Memento + Command)");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout(10, 10));
        setResizable(false);

        // Pantalla
        pantalla = new JTextField("0");
        pantalla.setEditable(false);
        pantalla.setHorizontalAlignment(JTextField.RIGHT);
        pantalla.setFont(new Font("Arial", Font.BOLD, 24));
        pantalla.setPreferredSize(new Dimension(300, 50));
        add(pantalla, BorderLayout.NORTH);

        // Panel de botones
        JPanel panelBotones = new JPanel(new GridLayout(5, 4, 5, 5));
        panelBotones.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        String[] etiquetas = {
                "7", "8", "9", "/",
                "4", "5", "6", "*",
                "1", "2", "3", "-",
                "0", "C", "=", "+",
                "Deshacer", "Rehacer"
        };

        for (String texto : etiquetas) {
            JButton boton = new JButton(texto);
            boton.setFont(new Font("Arial", Font.PLAIN, 16));
            boton.addActionListener(new ManejadorBotones());

            if (texto.equals("Deshacer") || texto.equals("Rehacer")) {

            }

            panelBotones.add(boton);
        }

        add(panelBotones, BorderLayout.CENTER);

        pack();
        setLocationRelativeTo(null);
    }

    private void actualizarPantalla() {
        double valor = calculadora.getResultadoActual();
        if (valor == (long) valor) {
            pantalla.setText(String.format("%d", (long) valor));
        } else {
            pantalla.setText(String.format("%s", valor));
        }
    }

    private class ManejadorBotones implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String comando = e.getActionCommand();

            try {
                if (comando.matches("[0-9]")) {
                    if (nuevaEntrada) {
                        pantalla.setText(comando);
                        nuevaEntrada = false;
                    } else {
                        pantalla.setText(pantalla.getText() + comando);
                    }
                } else if (comando.matches("[/\\*\\-\\+]")) {
                    primerValor = Double.parseDouble(pantalla.getText());
                    operacionPendiente = comando;
                    nuevaEntrada = true;
                } else if (comando.equals("=")) {
                    if (!operacionPendiente.isEmpty()) {
                        double segundoValor = Double.parseDouble(pantalla.getText());

                        // Inicializamos el resultado con el primer valor si es la primera operación
                        // para que el comando sea atómico, podemos hacer un SET del primer valor si el
                        // historial está vacío
                        // asumimos que 'primerValor' es el estado actual de la calculadora
                        // y aplicamos la operación sobre ella.

                        // sincronizamos el modelo con el primer valor antes de la operación
                        calculadora.setResultadoActual(primerValor);

                        ComandoOperacion op = new ComandoOperacion(calculadora, operacionPendiente, segundoValor);
                        gestor.ejecutarComando(op);

                        actualizarPantalla();
                        operacionPendiente = "";
                        nuevaEntrada = true;
                    }
                } else if (comando.equals("C")) {
                    ComandoOperacion op = new ComandoOperacion(calculadora, "C", 0);
                    gestor.ejecutarComando(op);
                    actualizarPantalla();
                    nuevaEntrada = true;
                } else if (comando.equals("Deshacer")) {
                    gestor.deshacer();
                    actualizarPantalla();
                    nuevaEntrada = true;
                } else if (comando.equals("Rehacer")) {
                    gestor.rehacer();
                    actualizarPantalla();
                    nuevaEntrada = true;
                }
            } catch (ArithmeticException ex) {
                JOptionPane.showMessageDialog(null, "Error: " + ex.getMessage());
                pantalla.setText("0");
                nuevaEntrada = true;
            } catch (Exception ex) {
                pantalla.setText("Error");
                nuevaEntrada = true;
            }
        }
    }
}
