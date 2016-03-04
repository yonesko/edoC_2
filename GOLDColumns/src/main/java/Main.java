//dat shit generate intermediate column names basing on names in database and client JTable names
import java.util.ArrayList;
import java.util.List;

public class Main {
    static List<Column> cols111 = new ArrayList<Column>();
    static List<Rule> rules111 = new ArrayList<Rule>();

    static List<Column> cols121 = new ArrayList<Column>();
    static List<Rule> rules121 = new ArrayList<Rule>();
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

        rules121 = Rules.parseRules("qtefin = qtedeb + entque – conque \n" +
                "enttotal = entprod + entgros\n" +
                "entque = enttotal + entret + entautre\n" +
                "conque = enttotal + conautre + conret\n" +
                "qtedeb = 0 entque = 0 conque = 0 qtefin = 0");

//        System.out.println(cols111.size());
//
//        for (Column col : cols111) {
//            System.out.println("ojtDecl.setColumnEditor(\""+col.getJTableName()+"\", "+col.getCliEditorName()+");");
//        }
//
//        System.out.println(Columns.clientJavaDeclaration(cols111));
//
//        System.out.println(Columns.servUpdateSetClause("DF111", cols111));
//
//        System.out.println(Columns.servOISSQLParams(cols111));
//
//        System.out.println(Columns.servJavaArgs(cols111));
//
//        System.out.println(Columns.servInsertClause("DF111", cols111));
//
//        System.out.println(Columns.clientReflectionInvoke(cols111));
//
//        System.out.println(Columns.clientServCallParams(cols111));
//
//        for (Rule rule : rules111) {
//            System.out.println("if (" + Rules.condition(rule, cols111) + ")");
//            System.out.println("{" + Rules.showError(rule, cols111) + "}");
//        }


        cols121 = Columns.parseColumns("QTEDEB\tOST_NACH_PERIOD\t-1582\n" +
                "ENTPROD\tPOSTUP_PROIZV\t-1583\n" +
                "ENTGROS\tPOSTUP_OPT_TORG\t-1584\n" +
                "ENTTOTAL\tPOSTUP_ITOGO\t-1534\n" +
                "ENTRET\tVOZVRAT_POKUP\t-1535\n" +
                "ENTAUTRE\tPROCH_POSTUP\t-1536\n" +
                "ENTQUE\tPOSTUP_VSEGO\t-1586\n" +
                "CONGROS\tOBEM_ROZN_PROD\t-1575\n" +
                "CONAUTRE\tPROCHII_RASHOD\t-1542\n" +
                "CONRET\tVOZVRAT_POSTAVSHIKU\t-1543\n" +
                "CONQUE\tRASHOD_VSEGO\t-1578\n" +
                "QTEFIN\tOSTATOK_OTCH_PERIOD\t-1546");

        System.out.println(cols121.size());

        for (Column col : cols121) {
            System.out.println("ojtDecl.setColumnEditor(\""+col.getJTableName()+"\", "+col.getCliEditorName()+");");
        }

        System.out.println(Columns.clientJavaDeclaration(cols121));

        System.out.println(Columns.servUpdateSetClause("DF121", cols121));

        System.out.println(Columns.servOISSQLParams(cols121));

        System.out.println(Columns.servJavaArgs(cols121));

        System.out.println(Columns.servInsertClause("DF121", cols121));

        System.out.println(Columns.clientReflectionInvoke(cols121));

        System.out.println(Columns.clientServCallParams(cols121));

        System.out.println(Columns.clientVarInit(cols121, true));

        System.out.println(Columns.clienSetColumnsEditableClause(cols121));

        for (Rule rule : rules121) {
            System.out.println("if (" + Rules.condition(rule, cols121) + ")");
            System.out.println("{" + Rules.showError(rule, cols121) + "}");
        }
    }
}
