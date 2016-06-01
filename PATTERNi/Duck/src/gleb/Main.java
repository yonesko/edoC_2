package gleb;

import java.util.Enumeration;
import java.util.Hashtable;
import java.util.Iterator;

public class Main {

    public static void main(String[] args) {

        Hashtable<Character, String> hashtable = new Hashtable<>();

        hashtable.<String>put('c', "Тцэ");
        hashtable.put('x', "Xа");

        Enumeration<String> elements = hashtable.<String>elements();

        Main main = new Main();

        main.newSuperTechMethod(new <String>EnumerationAdapter(elements));

    }


    void newSuperTechMethod(Iterator iterator) {
        while (iterator.hasNext())
            System.out.println(String.format("!%s!", iterator.next()));
    }
}
