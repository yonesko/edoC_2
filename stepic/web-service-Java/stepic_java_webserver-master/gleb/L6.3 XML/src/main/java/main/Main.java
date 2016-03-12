package main;

import sax.LoadXML;

public class Main {
    public static void main(String...args) {
        Object o = LoadXML.readXML("data/MySqlResource.xdb");
        System.out.println(o);
    }
}
