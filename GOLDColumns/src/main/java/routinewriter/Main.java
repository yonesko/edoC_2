package routinewriter;

import freemarker.template.TemplateException;
import routinewriter.server.templater.Generator;

import java.io.IOException;

/**
 * Dat projects generates routine code for futher editing by programmer
 */
public class Main {
    public static void main(String...args) throws IOException, TemplateException {
        String cols = "df6bliv, df6calc, df6contrnumfim, df6contrnumfou, df6creg, df6dcre, df6decnum, df6dliv, df6dmaj, df6gtdnum, df6motf, df6movtype, df6orgnum, df6tmvt, df6util, df6vliv";
        String setted = "df6bliv, df6calc, df6contrnumfim, df6contrnumfou, df6creg, df6dcre, df6decnum, df6dliv, df6dmaj, df6gtdnum, df6motf, df6movtype, df6orgnum, df6tmvt, df6util, df6vliv";
        String primary = "df6calc, df6decnum, df6orgnum, df6contrnumfou";
        String table = "dixy_dcldecf6";

//        String insert = Generator.makeInsert(table, cols.split(", "));
        String update = Generator.makeUpdate(table, setted.split(", "), primary.split(", "));


        System.out.println(update);
    }
}
