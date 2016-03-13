package util;

import java.lang.reflect.Field;

public class ReflectionHelper {
    public static <T> T createInstance(String clazz) {
        try {
            return (T) Class.forName(clazz).newInstance();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return null;
    }
    public static void setField(Object obj, String sField, String value) {
        boolean isAccessible;
        try {
            Field field = obj.getClass().getDeclaredField(sField);
            isAccessible = field.isAccessible();
            if (!isAccessible)
                field.setAccessible(true);

            if (field.getType() == int.class)
                field.setInt(obj, Integer.valueOf(value));
            else if (field.getType() == long.class)
                field.setLong(obj, Long.valueOf(value));
            else if (field.getType() == byte.class)
                field.setByte(obj, Byte.valueOf(value));
            else if (field.getType() == boolean.class)
                field.setBoolean(obj, Boolean.valueOf(value));
            else if (field.getType() == char.class)
                field.setChar(obj, value.charAt(0));
            else if (field.getType() == double.class)
                field.setDouble(obj, Double.valueOf(value));
            else if (field.getType() == float.class)
                field.setFloat(obj, Float.valueOf(value));
            else
                field.set(obj, value);


            if (!isAccessible)
                field.setAccessible(false);
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }
}
