package routinewriter;

import freemarker.template.TemplateException;
import routinewriter.server.templater.Generator;

import java.io.IOException;

public class Main {
    public static void main(String...args) throws IOException, TemplateException {
        String ins = "df6bliv, df6calc, df6contrnumfim, df6contrnumfou, df6creg, df6dcre, df6decnum, df6dliv, df6dmaj, df6gtdnum, df6motf, df6movtype, df6orgnum, df6tmvt, df6util, df6vliv";

        Generator.makeInsert("dixy_dcldecf6", ins.split(", "));

    }
}
