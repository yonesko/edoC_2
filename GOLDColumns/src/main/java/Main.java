//dat shit generate intermediate column names basing on names in database and client JTable names
import java.util.ArrayList;
import java.util.List;

public class Main {
    static List<Column> cols111 = new ArrayList<Column>();
    static List<Rule> rules111 = new ArrayList<Rule>();
    static ColType type = ColType.NUMBER;

    public static void main(String...args) {
        cols111.add(new Column("QTEDEB", "OST_NACH_PERIOD", type, -1573));
        cols111.add(new Column("ENTPROD", "POSTUP_PROIZV", type, -1532));
        cols111.add(new Column("ENTGROS", "POSTUP_OPT_TORG", type, -1533));
        cols111.add(new Column("ENTTOTAL", "POSTUP_ITOGO", type, -1574));
        cols111.add(new Column("ENTRET", "VOZVRAT_POKUP", type, -1535));
        cols111.add(new Column("ENTAUTRE", "PROCH_POSTUP", type, -1536));
        cols111.add(new Column("ENTMOV", "POSTUP_PEREMESH_V_ORG", type, -1537));
        cols111.add(new Column("ENTQUE", "POSTUP_VSEGO", type, -1538));
        cols111.add(new Column("CONGROS", "OBEM_ROZN_PROD", type, -1575));
        cols111.add(new Column("CONAUTRE", "PROCHII_RASHOD", type, -1542));
        cols111.add(new Column("CONRET", "VOZVRAT_POSTAVSHIKU", type, -1543));
        cols111.add(new Column("CONMOV", "RASHOD_PEREMESH_V_ORG", type, -1577));
        cols111.add(new Column("CONQUE", "RASHOD_ITOGO", type, -1578));
        cols111.add(new Column("QTEFIN", "OSTATOK_OTCH_PERIOD", type, -1546));

        rules111.add(new Rule("qtefin = qtedeb + entque - conque "));
        rules111.add(new Rule("enttotal = entprod + entgros "));
        rules111.add(new Rule("entque = enttotal + entret + entautre + entmov"));
        rules111.add(new Rule("conque = congros + conautre + conret + conmov"));
        rules111.add(new Rule("qtedeb = 0 and entque = 0 and conque = 0 and qtefin = 0"));

        System.out.println(cols111.size());

        for (Column col : cols111) {
            System.out.println("ojtDecl.setColumnEditor(\""+col.getJTableName()+"\", "+col.getCliEditorName()+");");
        }

        System.out.println(Columns.clientJavaDeclaration(cols111));

        System.out.println(Columns.servUpdateSetClause("DF111", cols111));

        System.out.println(Columns.servOISSQLParams(cols111));

        System.out.println(Columns.servServJavaArgs(cols111));

        System.out.println(Columns.servInsertClause("DF111", cols111));

        System.out.println(Columns.clientReflectionInvoke(cols111));

        System.out.println(Columns.clientServCallParams(cols111));

        for (Rule rule : rules111) {
            System.out.println("if (" + Rules.condition(rule, cols111) + ")");
            System.out.println("{" + Rules.showError(rule, cols111) + "}");
        }
    }
}
