package com.tfg.demo.app;

import com.tfg.demo.gui.A1ExtraPanel;
import com.tfg.demo.gui.A2ExtraPanel;
import com.tfg.demo.gui.APanel;
import com.tfg.demo.gui.BPanel;
import com.tfg.demo.gui.CPanel;
import com.tfg.demo.gui.DPanel;
import com.tfg.demo.model.A1;
import com.tfg.demo.model.A2;
import com.tfg.demo.model.B;
import com.tfg.demo.model.C;
import com.tfg.demo.model.D;
import com.tfg.reusablegui.api.ReusableGui;
import com.tfg.reusablegui.containers.SimpleContainerPanel;
import com.tfg.reusablegui.containers.TabbedContainerPanel;
import com.tfg.reusablegui.containers.TreeContainerPanel;
import com.tfg.demo.gui.DatosPersonalesPanel;
import com.tfg.reusablegui.factory.DialogWrapperFactory;
import com.tfg.demo.gui.PruebaJFrame;

import javax.swing.JDialog;
import javax.swing.*;
import java.awt.*;
import java.util.List;

/**
 * Aplicación de demostración del framework de GUIs reutilizables.
 *
 * Esta aplicación permite mostrar las principales funcionalidades
 * implementadas en el framework, incluyendo la composición simple,
 * la composición mediante pestañas, la composición jerárquica y
 * la integración con Apache NetBeans mediante plantillas y componentes
 * de la paleta.
 */
public class DemoApp {

    private static final A1 a1DemoModel =
            new A1("Ana García", 32, "12345678A", true,
                    "Analista", 32000.0);

    private static final A2 a2DemoModel =
            new A2("Luis Pérez", 28, "87654321B", true,
                    3, true, false);

    private static final A1 a1InB =
            new A1("María López", 41, "11223344C", true,
                    "Responsable", 45000.0);

    private static final A2 a2InB =
            new A2("Carlos Ruiz", 35, "44332211D", true,
                    5, true, true);

    private static final D dInB =
            new D("Administrador", 50, 0, 100, true);

    private static final C cInB =
            new C("Desarrollo de software", 5, 8.75, true,
                    "Sin observaciones", dInB);

    private static final B bDemoModel =
            new B("Perfil administrativo", 5, 7.3, false,
                    "Administración", 0, a1InB, a2InB, cInB);

    public static void main(String[] args) {
        DemoApp.createAndShowGui();
    }

    /**
    * Construye y muestra la ventana principal de la aplicación.
    *
    * La interfaz proporciona acceso a los distintos ejemplos de
    * composición y reutilización implementados en el framework.
    */
    private static void createAndShowGui() {
        JFrame frame = new JFrame("Demo TFG - GUIs Reutilizables");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1000, 225);
        frame.setMinimumSize(new Dimension(600, 260));

        // Botones que permiten acceder a los distintos
        // escenarios de demostración del framework.
        JButton a1Button = new JButton("Editar A1 (Simple)");
        JButton a2Button = new JButton("Editar A2 (Pestañas)");
        JButton bButton = new JButton("Editar B (Árbol)");
        JButton plantillaButton = new JButton("Probar GUI generada");
        JButton paletteButton = new JButton("Probar GUI creada con Paleta");

        // Demostración de composición simple.
        a1Button.addActionListener(e -> {
            ReusableGui gui = createA1Demo();
            JDialog dialog = DialogWrapperFactory.createDialog(frame, "Editar A1", gui);
            dialog.setLocationRelativeTo(frame);
            dialog.setVisible(true);
        });

        // Demostración de composición mediante pestañas.
        a2Button.addActionListener(e -> {
            ReusableGui gui = createA2Demo();
            JDialog dialog = DialogWrapperFactory.createDialog(frame, "Editar A2", gui);
            dialog.setLocationRelativeTo(frame);
            dialog.setVisible(true);
        });

        // Demostración de composición jerárquica.
        bButton.addActionListener(e -> {
            ReusableGui gui = createBDemo();
            JDialog dialog = DialogWrapperFactory.createDialog(frame, "Editar B", gui);
            dialog.setMinimumSize(new Dimension(850, 550));
            dialog.setPreferredSize(new Dimension(1050, 650));
            dialog.pack();
            dialog.setLocationRelativeTo(frame);
            dialog.setVisible(true);
        });
        
        // Demostración de GUI generada mediante plantilla.
        plantillaButton.addActionListener(e -> {
            DatosPersonalesPanel panel = new DatosPersonalesPanel();
            JDialog dialog = DialogWrapperFactory.createDialog(frame, "GUI creada con la plantilla", panel);
            dialog.setVisible(true);
        });
        
        // Demostración de GUI creada utilizando la paleta.
        paletteButton.addActionListener(e -> {
            PruebaJFrame ventana = new PruebaJFrame();
            ventana.setLocationRelativeTo(frame);
            ventana.setVisible(true);
        });
        
        JPanel mainPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 30));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        mainPanel.add(a1Button);
        mainPanel.add(a2Button);
        mainPanel.add(bButton);
        mainPanel.add(plantillaButton);
        mainPanel.add(paletteButton);

        frame.setContentPane(mainPanel);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    /**
    * Construye el escenario de demostración basado en
    * SimpleContainerPanel.
    *
    * @return composición simple formada por dos GUIs reutilizables
    */
    private static ReusableGui createA1Demo() {
        SimpleContainerPanel simplePanel = new SimpleContainerPanel();
        simplePanel.addChild(new APanel(a1DemoModel));
        simplePanel.addChild(new A1ExtraPanel(a1DemoModel));
        return simplePanel;
    }

    /**
    * Construye el escenario de demostración basado en
    * TabbedContainerPanel.
    *
    * @return composición mediante pestañas
    */
    private static ReusableGui createA2Demo() {
        TabbedContainerPanel tabbedPanel = new TabbedContainerPanel();
        tabbedPanel.addChild(new APanel(a2DemoModel));
        tabbedPanel.addChild(new A2ExtraPanel(a2DemoModel));
        return tabbedPanel;
    }

    /**
    * Construye el escenario de demostración basado en
    * TreeContainerPanel.
    *
    * La composición generada contiene varios grupos de GUIs
    * organizados jerárquicamente para demostrar las capacidades
    * de navegación y composición compleja del framework.
    *
    * @return composición jerárquica completa
    */
    private static ReusableGui createBDemo() {
        TreeContainerPanel treePanel = new TreeContainerPanel();
        treePanel.setRootTitle("Configuración del empleado");

        treePanel.addChild(new BPanel(bDemoModel));

        treePanel.addChildGroup("Empleado A1", List.of(
                new APanel(a1InB),
                new A1ExtraPanel(a1InB)
        ));

        treePanel.addChildGroup("Empleado A2", List.of(
                new APanel(a2InB),
                new A2ExtraPanel(a2InB)
        ));

        treePanel.addChildGroup("Configuración corporativa", List.of(
                new CPanel(cInB),
                new DPanel(dInB)
        ));

        return treePanel;
    }
}