package main;

import models.DBCred;
import sax.LoadXML;

public class Main {
    public static void main(String...args) {
        DBCred o = LoadXML.readXML("data/MySqlResource.xdb");
        System.out.println(o);
    }
}
