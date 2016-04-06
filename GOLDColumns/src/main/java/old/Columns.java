package old;

import java.util.*;

public class Columns {
    public static String clientJavaDeclaration(List<Column> cols) {
        System.out.println("---"+new Object(){}.getClass().getEnclosingMethod().getName()+"---");
        Map<ColType, List<String>> mapTypeToNames = new HashMap<ColType, List<String>>();
        StringBuilder sb = new StringBuilder();
        for (Column col : cols) {
            if (mapTypeToNames.get(col.getType()) == null) {
                mapTypeToNames.put(col.getType(), new ArrayList<String>());
            }
            mapTypeToNames.get(col.getType()).add(col.getCliEditorName());
        }

        for (Map.Entry<ColType, List<String>> e : mapTypeToNames.entrySet()) {
            sb.append("private ");
            sb.append(e.getKey().getOISType() + " ");
            for (int i = 0; i < e.getValue().size(); i++) {
                sb.append(e.getValue().get(i));
                if (i != e.getValue().size() - 1)
                    sb.append(", ");
            }
            sb.append(";");
        }

        return sb.toString();
    }
    public static String clientReflectionInvoke(List<Column> cols) {
        System.out.println("---"+new Object(){}.getClass().getEnclosingMethod().getName()+"---");
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < cols.size(); i++) {
            sb.append(cols.get(i).getInterName());
            if (i != cols.size() - 1)
                sb.append(", ");
        }
        return  sb.toString();
    }
    public static String clientServCallParams(List<Column> cols) {
        System.out.println("---"+new Object(){}.getClass().getEnclosingMethod().getName()+"---");
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < cols.size(); i++) {
            sb.append(cols.get(i).OISRowVal());
            if (i != cols.size() - 1)
                sb.append(", ");
        }
        return sb.toString();
    }
    public static String servUpdateSetClause(String tablePrefix, List<Column> cols) {
        System.out.println("---"+new Object(){}.getClass().getEnclosingMethod().getName()+"---");
        StringBuilder sb = new StringBuilder();
        String colName;
        Column col;
        for (int i = 0; i < cols.size(); i++) {
            col = cols.get(i);
            colName = tablePrefix + col.getDbName();
            colName = colName.toLowerCase();
            if (i != 0)
                sb.append(",");
            sb.append(colName);
            sb.append(" = ");
            sb.append(col.getOISSQLName());
            sb.append("\n");
        }
        return sb.toString();
    }
    public static String servInsertClause(String tablePrefix, List<Column> cols) {
        System.out.println("---"+new Object(){}.getClass().getEnclosingMethod().getName()+"---");
        StringBuilder sbCols = new StringBuilder();
        StringBuilder sbVals = new StringBuilder();
        String colName;
        Column col;
        for (int i = 0; i < cols.size(); i++) {
            col = cols.get(i);
            colName = tablePrefix + col.getDbName();
            colName = colName.toLowerCase();

            sbCols.append(colName);
            sbVals.append(col.getOISSQLName());
            if (i != cols.size() - 1) {
                sbCols.append(", ");
                sbVals.append(", ");
            }
        }
        sbCols.append("\n");
        return sbCols.toString() + sbVals.toString();
    }
    public static String servOISSQLParams(List<Column> cols) {
        System.out.println("---"+new Object(){}.getClass().getEnclosingMethod().getName()+"---");
        StringBuilder sb = new StringBuilder();
        //":QTEFIN:", 	iQtefin,
        for (Column col : cols) {
            sb.append("\"");
            sb.append(col.getOISSQLName());
            sb.append("\",\t");
            sb.append(col.getInterName());
            sb.append(",");
            sb.append("\n");
        }
        return sb.toString();
    }
    public static String servJavaArgs(List<Column> cols) {
        StringBuilder sb = new StringBuilder();
        Column c;
        for (int i = 0; i < cols.size(); i++) {
            c = cols.get(i);
            sb.append(c.getType().getCoreType());
            sb.append(" ");
            sb.append(c.getInterName());
            if (i != cols.size() - 1)
                sb.append(", ");
        }
        return sb.toString();
    }
    public static List<Column> parseColumns(String text) {
        System.out.println("---"+new Object(){}.getClass().getEnclosingMethod().getName()+"---");
        List<Column> cols = new ArrayList<Column>();
        Scanner sc = new Scanner(text);
        String line;
        String servName;
        String JTableName;
        ColType type = ColType.NUMBER;
        int label;

        while (sc.hasNext()) {
            servName = sc.next();
            JTableName = sc.next();
            label = sc.nextInt();
            cols.add(new Column(servName, JTableName, type, label));
        }
        return cols;
    }
    public static String clientVarInit(List<Column> cols, boolean mandatory) {
        System.out.println("---"+new Object(){}.getClass().getEnclosingMethod().getName()+"---");
        StringBuilder sb = new StringBuilder();

        for (Column col : cols)
            sb.append(col.getCliEditorName() + " = new "+col.getType().getOISType()+"("+mandatory+");\n");

        return sb.toString();
    }
    public static String clienSetColumnsEditableClause(List<Column> cols) {
        System.out.println("---"+new Object(){}.getClass().getEnclosingMethod().getName()+"---");
        StringBuilder sb = new StringBuilder();
        Column c;

        for (int i = 0; i < cols.size(); i++) {
            c = cols.get(i);
            sb.append("\"" + c.getJTableName() + "\"");
            if (i != cols.size() - 1)
                sb.append(", ");
        }

        return sb.toString();
    }


}
