package com.tfg.reusablegui.factory;

import com.tfg.reusablegui.api.GuiContext;
import com.tfg.reusablegui.api.ReusableGui;
import com.tfg.reusablegui.messaging.MessageRouter;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

/**
 * Factoría encargada de crear diálogos modales para las
 * GUIs reutilizables del framework.
 *
 * Esta clase encapsula toda la lógica necesaria para inicializar una GUI 
 * reutilizable, integrarla dentro de una ventana modal y proporcionar las 
 * operaciones estándar de validación, confirmación y cancelación.
 *
 * El diálogo generado incorpora automáticamente:
 *   - la inicialización del contexto compartido.
 *   - el sistema de mensajería.
 *   - los botones de confirmación y cancelación.
 *   - la liberación de recursos al cerrar la ventana.
 *
 * Gracias a esta factoría, las aplicaciones pueden abrir cualquier GUI 
 * reutilizable sin necesidad de implementar manualmente 
 * la infraestructura asociada.
 */

public final class DialogWrapperFactory {

    private DialogWrapperFactory() {
    }

    /**
    * Crea un diálogo modal que envuelve una GUI reutilizable.
    *
    * El método inicializa automáticamente el contexto del
    * framework, registra la GUI en el sistema de mensajería
    * y añade los controles estándar de confirmación y cancelación.
    *
    * @param owner ventana propietaria del diálogo
    * @param title título mostrado en la ventana
    * @param gui GUI reutilizable que se mostrará
    * @return diálogo completamente configurado
    * @throws IllegalArgumentException si gui es null
    */
    public static JDialog createDialog(Window owner, String title, ReusableGui gui) {
        if (gui == null) {
            throw new IllegalArgumentException("gui no puede ser null");
        }

        JDialog dialog = new JDialog(owner, title, Dialog.ModalityType.APPLICATION_MODAL);
        dialog.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        dialog.setLayout(new BorderLayout());

        // Se crea un contexto independiente para la GUI
        // junto con el sistema de mensajería asociado.
        MessageRouter router = new MessageRouter();
        GuiContext context = new GuiContext(router);

        gui.initReusable(context);

        dialog.add(gui.getView(), BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        JButton okButton = new JButton("OK");
        JButton cancelButton = new JButton("Cancel");

        buttonPanel.add(okButton);
        buttonPanel.add(cancelButton);

        dialog.add(buttonPanel, BorderLayout.SOUTH);

        // La operación de confirmación ejecuta primero la
        // validación recursiva y posteriormente aplica los cambios.
        okButton.addActionListener(e -> {
            try {
                if (gui.validateAll()) {
                    gui.applyAll();
                    dialog.dispose();
                }
            } catch (IllegalArgumentException ex) {
                JOptionPane.showMessageDialog(
                        dialog,
                        ex.getMessage(),
                        "Validación",
                        JOptionPane.WARNING_MESSAGE
                );
            }
        });

        // La cancelación descarta los cambios pendientes
        // y cierra el diálogo sin modificar el modelo.
        cancelButton.addActionListener(e -> {
            gui.cancelAll();
            dialog.dispose();
        });

        // Al cerrarse la ventana se liberan todos los recursos
        // asociados a la jerarquía de GUIs reutilizables.
        dialog.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosed(WindowEvent e) {
                gui.disposeAll();
            }
        });

        dialog.setMinimumSize(new Dimension(850, 550));
        dialog.setPreferredSize(new Dimension(1000, 650));
        dialog.pack();
        dialog.setLocationRelativeTo(owner);

        return dialog;
    }
}