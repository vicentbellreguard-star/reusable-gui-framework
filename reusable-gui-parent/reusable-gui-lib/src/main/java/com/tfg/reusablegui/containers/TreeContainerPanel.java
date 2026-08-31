package com.tfg.reusablegui.containers;

import com.tfg.reusablegui.api.GuiContext;
import com.tfg.reusablegui.api.ReusableGui;
import com.tfg.reusablegui.core.AbstractReusablePanel;
import com.tfg.reusablegui.core.ChildGroup;

import javax.swing.*;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import java.awt.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Contenedor reutilizable basado en árbol.
 *
 * Permite organizar múltiples GUIs reutilizables mediante
 * una estructura jerárquica representada por un componente JTree.
 *
 * La navegación se realiza a través del árbol mostrado en la parte 
 * izquierda de la interfaz, mientras que el contenido asociado al nodo 
 * seleccionado se presenta en la parte derecha utilizando un CardLayout.
 *
 * Este contenedor resulta especialmente adecuado para representar 
 * configuraciones complejas, estructuras jerárquicas o conjuntos de GUIs 
 * agrupadas lógicamente.
 *
 * La construcción visual del árbol se realiza de forma automática a partir de 
 * las GUIs hijas y grupos registrados en la jerarquía del framework.
 */

public class TreeContainerPanel extends AbstractReusablePanel {

    private String rootTitle = "Configuración";

    // Nodo raíz del árbol de navegación.
    private final DefaultMutableTreeNode rootNode = new DefaultMutableTreeNode(rootTitle);
    // Modelo de datos asociado al árbol.
    private final DefaultTreeModel treeModel = new DefaultTreeModel(rootNode);
    // Componente visual utilizado para la navegación.
    private final JTree tree = new JTree(treeModel);
    // Panel que almacena las vistas asociadas a cada nodo.
    private final JPanel cards = new JPanel(new CardLayout());
    // Relación entre nodos del árbol y tarjetas visuales.
    private final Map<DefaultMutableTreeNode, String> nodeToCard = new HashMap<>();

    private int cardCounter = 0;

    /**
    * Construye un contenedor jerárquico vacío.
    *
    * Inicializa el árbol de navegación, el panel de tarjetas
    * y la división visual mediante un JSplitPane.
    */
    public TreeContainerPanel() {
        setLayout(new BorderLayout());

        JScrollPane treeScroll = new JScrollPane(tree);
        JScrollPane cardsScroll = new JScrollPane(cards);

        treeScroll.setMinimumSize(new Dimension(260, 300));
        treeScroll.setPreferredSize(new Dimension(300, 500));

        cardsScroll.setMinimumSize(new Dimension(500, 300));
        cardsScroll.setBorder(null);

        JSplitPane splitPane = new JSplitPane(
                JSplitPane.HORIZONTAL_SPLIT,
                treeScroll,
                cardsScroll
        );

        splitPane.setResizeWeight(0.25);
        splitPane.setDividerLocation(300);
        splitPane.setContinuousLayout(true);
        splitPane.setOneTouchExpandable(true);

        add(splitPane, BorderLayout.CENTER);

        tree.addTreeSelectionListener(e -> showSelectedCard());
    }

    /**
    * Establece el título del nodo raíz.
    *
    * @param rootTitle nuevo título de la raíz
    */
    public void setRootTitle(String rootTitle) {
        this.rootTitle = rootTitle;
        rootNode.setUserObject(rootTitle);
        treeModel.reload();
    }

    /**
    * Inicializa el contenedor y reconstruye la estructura
    * jerárquica a partir de las GUIs registradas.
    *
    * @param context contexto compartido del framework
    */
    @Override
    public void initReusable(GuiContext context) {
        super.initReusable(context);
        rebuildTree();
    }

    /**
    * No realiza ninguna acción inmediata.
    *
    * La representación visual del árbol se reconstruye
    * completamente durante la ejecución de rebuildTree().
    *
    * @param child GUI añadida
    */
    @Override
    protected void onChildAdded(ReusableGui child) {}

    /**
    * No realiza ninguna acción inmediata.
    *
    * Los grupos se procesan durante la reconstrucción
    * completa del árbol.
    *
    * @param name nombre del grupo
    * @param groupChildren GUIs pertenecientes al grupo
    */
    @Override
    protected void onChildGroupAdded(String name, List<ReusableGui> groupChildren) {}

    /**
    * Reconstruye completamente la estructura del árbol.
    *
    * El método elimina la representación visual existente y genera nuevamente 
    * todos los nodos y tarjetas a partir de las GUIs hijas y grupos registrados.
    */
    private void rebuildTree() {
        rootNode.removeAllChildren();
        cards.removeAll();
        nodeToCard.clear();
        cardCounter = 0;

        for (ReusableGui child : children()) {
            addGuiNode(rootNode, child, child.getTitle());
        }

        for (ChildGroup group : childGroups()) {
            DefaultMutableTreeNode groupNode = new DefaultMutableTreeNode(group.name());
            rootNode.add(groupNode);

            for (ReusableGui child : group.children()) {
                addGuiNode(groupNode, child, child.getTitle());
            }
        }

        treeModel.reload();
        expandAll();

        if (rootNode.getChildCount() > 0) {
            DefaultMutableTreeNode firstChild =
                    (DefaultMutableTreeNode) rootNode.getChildAt(0);
            tree.setSelectionPath(new javax.swing.tree.TreePath(firstChild.getPath()));
        }

        revalidate();
        repaint();
    }

    /**
    * Añade una GUI al árbol y crea la tarjeta visual asociada.
    *
    * @param parent nodo padre
    * @param gui GUI representada
    * @param title texto mostrado en el árbol
    */
    private void addGuiNode(DefaultMutableTreeNode parent, ReusableGui gui, String title) {
        DefaultMutableTreeNode node = new DefaultMutableTreeNode(title);
        parent.add(node);

        String cardName = "card-" + cardCounter++;
        nodeToCard.put(node, cardName);

        JPanel wrapper = new JPanel(new GridBagLayout());
        wrapper.add(gui.getView(), new GridBagConstraints());

        cards.add(wrapper, cardName);
    }

    /**
    * Muestra la tarjeta asociada al nodo actualmente seleccionado en el árbol.
    */
    private void showSelectedCard() {
        Object selected = tree.getLastSelectedPathComponent();

        if (!(selected instanceof DefaultMutableTreeNode node)) {
            return;
        }

        String cardName = nodeToCard.get(node);

        if (cardName != null) {
            CardLayout layout = (CardLayout) cards.getLayout();
            layout.show(cards, cardName);
        }
    }

    /**
    * Expande todos los nodos visibles del árbol.
    */
    private void expandAll() {
        for (int i = 0; i < tree.getRowCount(); i++) {
            tree.expandRow(i);
        }
    }

    /**
    * No realiza validaciones propias.
    *
    * La validación se delega completamente en las GUIs hijas.
    *
    * @return siempre true
    */
    @Override
    protected boolean validateSelf() {
        return true;
    }

    /**
    * No realiza ninguna acción.
    *
    * El contenedor únicamente gestiona la composición
    * visual de las GUIs hijas y no mantiene estado propio.
    */
    @Override
    protected void applySelf() {}

    /**
    * Devuelve el título asociado al nodo raíz.
    *
    * @return título del contenedor jerárquico
    */
    @Override
    public String getTitle() {
        return rootTitle;
    }
}