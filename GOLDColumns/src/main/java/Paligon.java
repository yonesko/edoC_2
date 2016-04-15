import java.math.BigDecimal;

public class Paligon {
    public static void updatedixy_dcldecf7(String blivNew, BigDecimal calc, String contrnumfim, String contrnumfou, BigDecimal creg, BigDecimal decnum, String dliv, String dmaj, String gtdnum, BigDecimal motf, BigDecimal movtype, String orgnum, BigDecimal tmvt, String util, BigDecimal vliv, String bliv, BigDecimal rowid)
            throws Exception {
        String sql =
                "UPDATE dixy_dcldecf7\n" +
                        "SET\n" +
                        "df7bliv = :BLIV_NEW:,\n" +
                        "df7calc = :CALC:,\n" +
                        "df7contrnumfim = :CONTRNUMFIM:,\n" +
                        "df7contrnumfou = :CONTRNUMFOU:,\n" +
                        "df7creg = :CREG:,\n" +
                        "df7decnum = :DECNUM:,\n" +
                        "df7dliv = :DLIV:,\n" +
                        "df7dmaj = :DMAJ:,\n" +
                        "df7gtdnum = :GTDNUM:,\n" +
                        "df7motf = :MOTF:,\n" +
                        "df7movtype = :MOVTYPE:,\n" +
                        "df7orgnum = :ORGNUM:,\n" +
                        "df7tmvt = :TMVT:,\n" +
                        "df7util = :UTIL:,\n" +
                        "df7vliv = :VLIV:\n" +
                        "WHERE\n" +
                        "df7bliv = :BLIV: AND\n" +
                        "rowid = :ROWID:";
        try {
            OISSQL.updateArray(sql, new Object[] {
                    ":BLIV_NEW:", blivNew,
                    ":CALC:", calc,
                    ":CONTRNUMFIM:", contrnumfim,
                    ":CONTRNUMFOU:", contrnumfou,
                    ":CREG:", creg,
                    ":DECNUM:", decnum,
                    ":DLIV:", dliv,
                    ":DMAJ:", dmaj,
                    ":GTDNUM:", gtdnum,
                    ":MOTF:", motf,
                    ":MOVTYPE:", movtype,
                    ":ORGNUM:", orgnum,
                    ":TMVT:", tmvt,
                    ":UTIL:", util,
                    ":VLIV:", vliv,
                    ":BLIV:", bliv,
                    ":ROWID:", rowid});
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public static void insertdixy_dcldecf7(String bliv, BigDecimal calc, String contrnumfim, String contrnumfou, BigDecimal creg, String dcre, BigDecimal decnum, String dliv, String dmaj, String gtdnum, BigDecimal motf, BigDecimal movtype, String orgnum, BigDecimal tmvt, String util, BigDecimal vliv) {
        String sql =
                "INSERT INTO dixy_dcldecf7\n" +
                        "(df7bliv, df7calc, df7contrnumfim, df7contrnumfou, df7creg, df7dcre, df7decnum, df7dliv, df7dmaj, df7gtdnum, df7motf, df7movtype, df7orgnum, df7tmvt, df7util, df7vliv)\n" +
                        "VALUES\n" +
                        "(:BLIV:, :CALC:, :CONTRNUMFIM:, :CONTRNUMFOU:, :CREG:, :DCRE:, :DECNUM:, :DLIV:, :DMAJ:, :GTDNUM:, :MOTF:, :MOVTYPE:, :ORGNUM:, :TMVT:, :UTIL:, :VLIV:)\n";
        try {
            OISSQL.updateArray(sql, new Object[] {
                    ":BLIV:", bliv,
                    ":CALC:", calc,
                    ":CONTRNUMFIM:", contrnumfim,
                    ":CONTRNUMFOU:", contrnumfou,
                    ":CREG:", creg,
                    ":DCRE:", dcre,
                    ":DECNUM:", decnum,
                    ":DLIV:", dliv,
                    ":DMAJ:", dmaj,
                    ":GTDNUM:", gtdnum,
                    ":MOTF:", motf,
                    ":MOVTYPE:", movtype,
                    ":ORGNUM:", orgnum,
                    ":TMVT:", tmvt,
                    ":UTIL:", util,
                    ":VLIV:", vliv});
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
class OISSQL {
    public static void  updateArray(String g, Object[] r) {

    }
}