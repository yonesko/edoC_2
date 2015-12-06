package gleb.server.dao.entity;

import gleb.server.LoGGer;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.logging.Level;

public class AbstractEntity {
    static Class<? extends AbstractEntity> thisClass;
    static Field[] fields;

    public AbstractEntity() {
    }

    public Object getColValue(int i) {
        Field fld = fields[i];
        String fldName = String.valueOf(fld.getName().charAt(0)).toUpperCase() + fld.getName().substring(1);
        Method m = null;
        try {
            m = thisClass.getMethod("get" + fldName);
            return m.invoke(this);
        } catch (ReflectiveOperationException e) {
            LoGGer.log(Level.SEVERE, null, e);
        }
        return null;
    }
    public static String getColName(int i) {
        return fields[i].getName();
    }
    public static int getColCount() {
        return fields.length;
    }
}
