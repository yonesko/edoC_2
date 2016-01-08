package gleb;

import java.io.*;
import java.util.*;

public class Main {
    static BufferedReader bfNambas;
    static List<String> lNambas;
    static public List<Tree> forest;

    public static void main(String...args) throws IOException {
        bfNambas = new LineNumberReader(new FileReader("resources/fileservlet2.txt"));
        lNambas = new ArrayList<>(Integer.parseInt(bfNambas.readLine()));
        forest = new ArrayList<>();
        {
            String s;
            while ((s = bfNambas.readLine()) != null)
                lNambas.add(s);
        }
        Collections.sort(lNambas);
        for (String lNamba : lNambas) {
            System.out.println(lNamba);
        }
//        System.out.println();

        deep(null, 0, lNambas.size(), 0);

        int sum = 0;
        for (Tree tree : forest) {
//            tree.show();
            sum += tree.getElementsCount(0);
        }
//        System.out.println(sum);
    }//main

    static void deep(Tree root, int start, int end, int offset) {
//        System.out.println("deep " + root + "[ " + start + "; " + end + " ) " + offset);
        String minOverlap = lNambas.get(start).substring(offset);

        if (end <= start + 1 && root != null)
            return;

        for (int i = start + 1; i < end; i++) {
            String curName = lNambas.get(i).substring(offset);
            String curOverlap = leftOverlap(minOverlap, curName);

            if (curOverlap.length() == 0) {
                Tree t = new Tree(minOverlap);
                addTree(t, root);
                minOverlap = curName;
                deep(t, start, i, offset + t.getName().length());
                start = i;
            }
            else if (curOverlap.length() < minOverlap.length())
                minOverlap = curOverlap;
        }
        Tree t = new Tree(minOverlap);
        addTree(t, root);
        deep(t, start, end, offset + minOverlap.length());
    }

    static void addTree(Tree t, Tree root) {
        if (root == null)
            forest.add(t);
        else
            root.addChild(t);
    }
    static String leftOverlap(String a, String b) {
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < Math.min(a.length(), b.length()); i++) {
            if (a.charAt(i) == b.charAt(i))
                result.append(a.charAt(i));
            else
                break;
        }
        return new String(result);
    }
}

class Tree {
    private String name;
    private List<Tree> children;

    public List<Tree> getChildren() {
        return children;
    }

    void addChild(Tree tree) {
        children.add(tree);
    }
    public Tree(String name) {
        this.name = name;
        children = new ArrayList<>();
    }
    public String getName() {
        return name;
    }
    private void show(int indent) {
        char[] t = new char[indent];
        Arrays.fill(t, '\t');
        System.out.println(new String(t) + (name.length() == 0 ? '.' : name));
        for (Tree child : children) {
            child.show(indent + 1);
        }

    }
    void show() {
        show(0);
    }

    @Override
    public String toString() {
        return "Tree{" +
                "name='" + name + '\'' +
                '}';
    }

    int getElementsCount(int countStart) {
        countStart = name.length();
        for (Tree child : children) {
            countStart += child.getElementsCount(countStart);
        }
        return countStart;
    }
}
