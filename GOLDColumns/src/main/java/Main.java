//dat shit generate intermediate column names basing on names in database and client JTable names
import java.util.ArrayList;
import java.util.List;

public class Main {
    static List<Column> cols = new ArrayList<Column>();
    static List<Rule> rules = new ArrayList<Rule>();
    static ColType type = ColType.NUMBER;

    public static void main(String...args) {
        cols.add(new Column("QTEDEB", "OST_NACH_PERIOD", type, -1573));
        cols.add(new Column("ENTPROD", "POSTUP_PROIZV", type, -1532));
        cols.add(new Column("ENTGROS", "POSTUP_OPT_TORG", type, -1533));
        cols.add(new Column("ENTTOTAL", "POSTUP_ITOGO", type, -1574));
        cols.add(new Column("ENTRET", "VOZVRAT_POKUP", type, -1535));
        cols.add(new Column("ENTAUTRE", "PROCH_POSTUP", type, -1536));
        cols.add(new Column("ENTMOV", "POSTUP_PEREMESH_V_ORG", type, -1537));
        cols.add(new Column("ENTQUE", "POSTUP_VSEGO", type, -1538));
        cols.add(new Column("CONGROS", "OBEM_ROZN_PROD", type, -1575));
        cols.add(new Column("CONAUTRE", "PROCHII_RASHOD", type, -1542));
        cols.add(new Column("CONRET", "VOZVRAT_POSTAVSHIKU", type, -1543));
        cols.add(new Column("CONMOV", "RASHOD_PEREMESH_V_ORG", type, -1577));
        cols.add(new Column("CONQUE", "RASHOD_ITOGO", type, -1578));
        cols.add(new Column("QTEFIN", "OSTATOK_OTCH_PERIOD", type, -1546));

        rules.add(new Rule("qtefin = qtedeb + entque - conque "));
        rules.add(new Rule("enttotal = entprod + entgros "));
        rules.add(new Rule("entque = enttotal + entret + entautre + entmov"));
        rules.add(new Rule("conque = congros + conautre + conret + conmov"));
        rules.add(new Rule("qtedeb = 0 and entque = 0 and conque = 0 and qtefin = 0"));

        System.out.println(cols.size());

        for (Column col : cols) {
            System.out.println("ojtDecl.setColumnEditor(\""+col.getCliName()+"\", "+col.getCliEditorName()+");");
        }

        System.out.println(Columns.clientJavaDeclaration(cols));

        System.out.println(Columns.servUpdateSetClause("DF111", cols));

        System.out.println(Columns.servOISSQLParams(cols));

        System.out.println(Columns.servServJavaArgs(cols));

        System.out.println(Columns.servInsertClause("DF111", cols));

        System.out.println(Columns.clientReflectionInvoke(cols));

        System.out.println(Rules.showError(rules.get(0), cols));
    }
}
