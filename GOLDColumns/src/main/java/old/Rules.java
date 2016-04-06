package old;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

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
            val = val.replaceAll(col.getDbName().toLowerCase(), "cja.getLabel("+col.getLabel()+")");
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
            val = val.replaceAll(col.getDbName().toLowerCase(), col.OISRowVal());
        }
        sb.append(val);
        return sb.toString();
    }
    public static List<Rule> parseRules(String text) {
        System.out.println("---"+new Object(){}.getClass().getEnclosingMethod().getName()+"---");
        Scanner sc = new Scanner(text);
        List<Rule> rules = new ArrayList<Rule>();
        while (sc.hasNextLine())
            rules.add(new Rule(sc.nextLine()));
        return rules;
    }
}
