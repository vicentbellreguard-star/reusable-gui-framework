<#--
 Plantilla FreeMarker utilizada por el plugin de NetBeans
 para generar automáticamente una nueva GUI reutilizable
 compatible con el framework.

 La clase generada hereda de AbstractReusablePanel e incluye
 la estructura mínima necesaria para su integración dentro
 del sistema de composición de GUIs.
-->

<#if package?? && package != "">
package ${package};
</#if>

import com.tfg.reusablegui.core.AbstractReusablePanel;

import javax.swing.*;
import java.awt.*;

public class ${name} extends AbstractReusablePanel {

    private final JTextField field = new JTextField(20);

    public ${name}() {
        buildUi();
    }

    private void buildUi() {

        setLayout(new GridBagLayout());

        GridBagConstraints gbc = new GridBagConstraints();

        gbc.insets = new Insets(4, 4, 4, 4);
        gbc.anchor = GridBagConstraints.WEST;
        gbc.fill = GridBagConstraints.HORIZONTAL;

        gbc.gridx = 0;
        gbc.gridy = 0;

        add(new JLabel("Campo:"), gbc);

        gbc.gridx = 1;
        gbc.weightx = 1.0;

        add(field, gbc);
    }

    @Override
    protected boolean validateSelf() {
        return true;
    }

    @Override
    protected void applySelf() {
    }

    @Override
    public String getTitle() {
        return "${name}";
    }
}
