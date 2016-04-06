package old;//dat shit generate intermediate column names basing on names in database and client JTable names
//dat shit dont have any logic, only text proccessing for convenience and speed
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String...args) {
        m6();
        
    }
    private static void m6() {
        List<Column> cols = new ArrayList<Column>();
        List<Rule> rules = new ArrayList<Rule>();

        cols = Columns.parseColumns("DLIV\tDATA_POSTAVKI\t-1561\n" +
                "BLIV\tNOMER_TTN\t-1562\n" +
                "GTDNUM\tNOMER_GTD\t-1563\n" +
                "VLIV\tOBEM_POST_PROD\t-1564\n" +
                "CALC\tKOD_VIDA_PROD\t-1528\n");

        System.out.println(Columns.clientJavaDeclaration(cols));

        System.out.println(Columns.servUpdateSetClause("DF6", cols));

        System.out.println(Columns.servOISSQLParams(cols));

        System.out.println(Columns.servJavaArgs(cols));

        System.out.println(Columns.servInsertClause("DF6", cols));

        System.out.println(Columns.clientReflectionInvoke(cols));

        System.out.println(Columns.clientServCallParams(cols));

        System.out.println(Columns.clientVarInit(cols, true));

        System.out.println(Columns.clienSetColumnsEditableClause(cols));
    }
}
