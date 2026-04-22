package mementocommand;

import mementocommand.gui.CalculadoraGUI;
import javax.swing.UIManager;

public class MementoCommand {

    public static void main(String[] args) {
        try {
            // Intentar establecer el look and feel del sistema para una mejor apariencia
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            e.printStackTrace();
        }

        java.awt.EventQueue.invokeLater(() -> {
            new CalculadoraGUI().setVisible(true);
        });
    }
}
