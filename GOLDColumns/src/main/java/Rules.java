import java.util.List;

public class Rules {
    public static String showError(Rule rule, List<Column> cols) {
        StringBuilder sb = new StringBuilder();
        String val = rule.getVal().toLowerCase();
        //beginning of the messege
        sb.append("cja.showErrorMessage(\"Condition violation \\n\"+");
        //body of the messege
        val = val.replaceAll("\\+", "\\+\" + \"\\+");
        val = val.replaceAll("-", "\\+\" - \"\\+");
        val = val.replaceAll("=", "\\+\" = \"\\+");
        for (Column col : cols) {
            val = val.replaceAll(col.getServName().toLowerCase(), "cja.getLabel("+col.getLabel()+")");
        }
        sb.append(val);
        //ending of the messege
        sb.append(");");
        return sb.toString();
    }
    public static String condition(Rule rule, List<Column> cols) {
        StringBuilder sb = new StringBuilder();
        String val = rule.getVal().toLowerCase();
        for (Column col : cols) {
            val = val.replaceAll(col.getServName().toLowerCase(), col.OISRowVal());
        }
        sb.append(val);
        return sb.toString();
    }
    /*
    if (r.toBigDecimal("RASHOD_ITOGO").compareTo(
                r.toBigDecimal("POSTAV_ITOGO")
                        .add(r.toBigDecimal("PROCH_RASHOD"))
                        .add(r.toBigDecimal("VOZVRAT_POST"))
                        .add(r.toBigDecimal("RASHOD_PEREMESH_V_ORG"))
        ) != 0)
        {
            cja.showErrorMessage("Not equal \n" + cja.getLabel(-1545)+" = "+cja.getLabel(-1541)+" + "+cja.getLabel(-1542)+" + "+cja.getLabel(-1543)+" + "+cja.getLabel(-1544));
            return false;

     */
//    public static String showError(List<Rule> rules111) {
//        StringBuilder sb = new StringBuilder();
//
//        return sb.toString();
//    }
}
