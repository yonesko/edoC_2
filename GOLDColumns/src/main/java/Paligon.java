public class Paligon {
    public static void updatedixy_dcldecf6(Integer bliv, Integer calc, Integer contrnumfim, Integer contrnumfou, Integer creg, Integer dcre, Integer decnum, Integer dliv, Integer dmaj, Integer gtdnum, Integer motf, Integer movtype, Integer orgnum, Integer tmvt, Integer util, Integer vliv, Integer calcnew, Integer decnumnew, Integer orgnumnew, Integer contrnumfounew)
            throws Exception {
        String sql =
                "UPDATE dixy_dcldecf6\n" +
                        "SET\n" +
                        "df6contrnumfou = :CONTRNUMFOU:,\n" +
                        "df6vliv = :VLIV:,\n" +
                        "df6util = :UTIL:,\n" +
                        "df6creg = :CREG:,\n" +
                        "df6calc = :CALC:,\n" +
                        "df6decnum = :DECNUM:,\n" +
                        "df6dliv = :DLIV:,\n" +
                        "df6movtype = :MOVTYPE:,\n" +
                        "df6motf = :MOTF:,\n" +
                        "df6dmaj = :DMAJ:,\n" +
                        "df6contrnumfim = :CONTRNUMFIM:,\n" +
                        "df6decnumnew = :DECNUMNEW:,\n" +
                        "df6orgnumnew = :ORGNUMNEW:,\n" +
                        "df6orgnum = :ORGNUM:,\n" +
                        "df6tmvt = :TMVT:,\n" +
                        "df6dcre = :DCRE:,\n" +
                        "df6calcnew = :CALCNEW:,\n" +
                        "df6gtdnum = :GTDNUM:,\n" +
                        "df6bliv = :BLIV:,\n" +
                        "df6contrnumfounew = :CONTRNUMFOUNEW:" +
                        "WHERE\n" +
                        "df6calc = :CALC: AND\n" +
                        "df6decnum = :DECNUM: AND\n" +
                        "df6orgnum = :ORGNUM: AND\n" +
                        "df6contrnumfou = :CONTRNUMFOU:";
        OISSQL.updateArray(sql, new Object[] {
                ":DECNUM:", decnum,
                ":CONTRNUMFOU:", contrnumfou,
                ":MOTF:", motf,
                ":UTIL:", util,
                ":ORGNUMNEW:", orgnumnew,
                ":CONTRNUMFIM:", contrnumfim,
                ":VLIV:", vliv,
                ":DLIV:", dliv,
                ":DCRE:", dcre,
                ":CREG:", creg,
                ":TMVT:", tmvt,
                ":MOVTYPE:", movtype,
                ":CALCNEW:", calcnew,
                ":CONTRNUMFOUNEW:", contrnumfounew,
                ":BLIV:", bliv,
                ":ORGNUM:", orgnum,
                ":GTDNUM:", gtdnum,
                ":DECNUMNEW:", decnumnew,
                ":DMAJ:", dmaj,
                ":CALC:", calc    });
    }

}
class OISSQL {
    public static void  updateArray(String g, Object[] r) {

    }
}