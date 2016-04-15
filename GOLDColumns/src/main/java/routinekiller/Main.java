package routinekiller;

import freemarker.template.TemplateException;
import routinekiller.server.templater.Generator;

import java.io.IOException;

/**
 * Dat projects generates routine code for futher editing by programmer
 */
public class Main {
    public static void main(String... args) throws IOException, TemplateException {
        do7();
    }

    private static void do112() throws IOException, TemplateException {
        String cols = "df112bliv, df112calc, df112contrnumfim, df112contrnumfou, df112dcre, df112decnum, df112dliv, df112dmaj, df112gtdnum, df112motf, df112movtype, df112orgnum, df112tmvt, df112util, df112vliv";
        String setted = "df112bliv, df112calc, df112contrnumfim, df112contrnumfou, df112dcre, df112decnum, df112dliv, df112dmaj, df112gtdnum, df112motf, df112movtype, df112orgnum, df112tmvt, df112util, df112vliv";
        String primary = "df112calc, df112decnum, df112orgnum, df112contrnumfou";
        String table = "dixy_dcldecf112", prefix = "df112";
        String colTypeText = "DF112UTIL\tVARCHAR2\n" +
                "DF112DMAJ\tDATE\n" +
                "DF112DCRE\tDATE\n" +
                "DF112MOTF\tNUMBER\n" +
                "DF112TMVT\tNUMBER\n" +
                "DF112MOVTYPE\tNUMBER\n" +
                "DF112VLIV\tNUMBER\n" +
                "DF112GTDNUM\tVARCHAR2\n" +
                "DF112BLIV\tVARCHAR2\n" +
                "DF112DLIV\tDATE\n" +
                "DF112CONTRNUMFOU\tVARCHAR2\n" +
                "DF112CONTRNUMFIM\tVARCHAR2\n" +
                "DF112CALC\tNUMBER\n" +
                "DF112ORGNUM\tVARCHAR2\n" +
                "DF112DECNUM\tNUMBER";

        String insert = Generator.makeInsert(table, prefix, cols.split(", "), colTypeText);
        String update = Generator.makeUpdate(table, prefix, setted.split(", "), primary.split(", "), colTypeText);


        System.out.println(update);
        System.out.println(insert);
    }

    private static void do7() throws IOException, TemplateException {
        String cols = "df7bliv, df7calc, df7contrnumfim, df7contrnumfou, df7creg, df7dcre, df7decnum, df7dliv, df7dmaj, df7gtdnum, df7motf, df7movtype, df7orgnum, df7tmvt, df7util, df7vliv";
        String setted = "df7bliv, df7calc, df7contrnumfim, df7contrnumfou, df7creg, df7decnum, df7dliv, df7dmaj, df7gtdnum, df7motf, df7movtype, df7orgnum, df7tmvt, df7util, df7vliv";
        String primary = "rowid";
        String table = "dixy_dcldecf7", prefix = "df7";
        String colTypeText = "DF7BLIV\tVARCHAR2\n" +
                "DF7CALC\tNUMBER\n" +
                "DF7CONTRNUMFIM\tVARCHAR2\n" +
                "DF7CONTRNUMFOU\tVARCHAR2\n" +
                "DF7CREG\tNUMBER\n" +
                "DF7DCRE\tDATE\n" +
                "DF7DECNUM\tNUMBER\n" +
                "DF7DLIV\tDATE\n" +
                "DF7DMAJ\tDATE\n" +
                "DF7GTDNUM\tVARCHAR2\n" +
                "DF7MOTF\tNUMBER\n" +
                "DF7MOVTYPE\tNUMBER\n" +
                "DF7ORGNUM\tVARCHAR2\n" +
                "DF7TMVT\tNUMBER\n" +
                "DF7UTIL\tVARCHAR2\n" +
                "DF7VLIV\tNUMBER";

        String insert = Generator.makeInsert(table, prefix, cols.split(", "), colTypeText);
        String update = Generator.makeUpdate(table, prefix, setted.split(", "), primary.split(", "), colTypeText);


        System.out.println(update);
        System.out.println(insert);
    }
}

/*

 */
