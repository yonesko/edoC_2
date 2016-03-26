package gleb;

import javax.swing.*;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.TreePath;
import java.awt.*;
import java.io.IOException;

public class Gui extends JPanel {
    private JTree tree;

    public Gui() throws IOException {
        Main.main();
        Tree numTree = Main.forest.get(0);


        DefaultMutableTreeNode top = new DefaultMutableTreeNode(numTree.getName());
        //add nodes
        addNodes(numTree, top);
        //add tree to frame
        tree = new JTree(top);
        JScrollPane treeView = new JScrollPane(tree);

        tree.expandPath(new TreePath(top));

        treeView.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

        add(treeView);
    }

    private void addNodes(Tree root, DefaultMutableTreeNode view) {

        for (Tree c : root.getChildren()) {
            DefaultMutableTreeNode v = new DefaultMutableTreeNode(c.getName());
            view.add(v);
            addNodes(c, v);
        }

    }

    public static void main(String...args) throws IOException {



        //Create and set up the window.
        JFrame frame = new JFrame("TreeDemo");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //Add content to the window.
        frame.add(new Gui());

        //Display the window.
        frame.pack();
        frame.setVisible(true);
    }
}
