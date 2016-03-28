package main;

import org.hibernate.engine.jdbc.internal.BasicFormatterImpl;
import org.junit.Before;
import org.junit.Test;

import java.lang.reflect.Field;

import static org.junit.Assert.*;

/**
 * Created by gleb on 26.03.16.
 */
public class EvalParamsBeanTest {
    private final String BLANCK_SHIT = "\n\t\n\t  \n\t\n\n \t";
    private static String SECTION_DELIMITER;
    private static String FAIL_MSG;
    private EvalParamsBean bean;

    @Before
    public void setUp() throws Exception {
        bean = new EvalParamsBean();
        Class<EvalParamsBean> beanClass = EvalParamsBean.class;
        Field secDelim = beanClass.getDeclaredField("SECTION_DELIMITER");
        secDelim.setAccessible(true);

        SECTION_DELIMITER = String.valueOf((secDelim.get(bean)));
        FAIL_MSG = String.format("Cant find \"%s\" delimiter string", SECTION_DELIMITER);
        secDelim.setAccessible(false);
    }

    @Test
    public void setSQLtext() throws Exception {
        String q, expected;
        //1
        bean.setSQLtext("");
        assertEquals(FAIL_MSG, bean.getSQLtext());
        //2
        bean.setSQLtext(null);
        assertEquals(FAIL_MSG, bean.getSQLtext());
        //3
        bean.setSQLtext("   \t\n\t");
        assertEquals(FAIL_MSG, bean.getSQLtext());
        //4
        bean.setSQLtext("    ");
        assertEquals(FAIL_MSG, bean.getSQLtext());
        //5
        bean.setSQLtext("\n\n\n\n\n");
        assertEquals(FAIL_MSG, bean.getSQLtext());
        //6
        bean.setSQLtext("tu,mrntd45y\nerg445445\n");
        assertEquals(FAIL_MSG, bean.getSQLtext());
        //7
        q = String.format("SELECCV * FROM :POROPR:  , :POROPR: %s%s%s  :POROPR:  678 35 OVOVERDI%s",
                        BLANCK_SHIT,
                        SECTION_DELIMITER,
                        BLANCK_SHIT,
                        BLANCK_SHIT);
        expected = "SELECCV * \n" +
                "FROM\n" +
                "    '678 35 OVOVERDI'  ,\n" +
                "    '678 35 OVOVERDI'";
        bean.setSQLtext(q);
        assertEquals(expected, bean.getSQLtext());
        //8
        q = String.format("%sSELECCV * %sFROM :POROPR:  , :POROPR: %s%s%s  :POROPR:  678 35 OVOVERDI%s",
                        BLANCK_SHIT,
                        BLANCK_SHIT,
                        BLANCK_SHIT,
                        SECTION_DELIMITER,
                        BLANCK_SHIT,
                        BLANCK_SHIT);
        expected = "SELECCV *             \n" +
                "FROM\n" +
                "    '678 35 OVOVERDI'  ,\n" +
                "    '678 35 OVOVERDI'";
        bean.setSQLtext(q);
        assertEquals(expected, bean.getSQLtext());
        //9
        q = "select * from jopa, tab where x(+) = y(+) and :p1: = :P222: and :olobo: is not NuLl";
        q += SECTION_DELIMITER;
        q += "  :p1:  3456789\n\n\n\n";
        q += "  :P222:  NE, NU A CHO\n\n\n\n";
        q += "  \t \t:olobo:  null\n\n\n\n";
        q += "  \t \t:excess:  null35\n\n\n\n";
        expected = "select\n" +
                "        * \n" +
                "    from\n" +
                "        jopa,\n" +
                "        tab \n" +
                "    where\n" +
                "        x(+) = y(+) \n" +
                "        and '3456789' = 'NE, NU A CHO' \n" +
                "        and null is not NuLl";

        bean.setSQLtext(q);
        assertEquals(expected, bean.getSQLtext());
        //10
        bean.setSQLtext(SECTION_DELIMITER);
        assertEquals("", bean.getSQLtext());
        //11
        bean.setSQLtext(BLANCK_SHIT + SECTION_DELIMITER + BLANCK_SHIT);
        assertEquals("", bean.getSQLtext());
        //12
        q = BLANCK_SHIT + SECTION_DELIMITER + BLANCK_SHIT;
        q += ":olol:";
        q += "rhrhrh PORTUMEYA";
        bean.setSQLtext(q);
        assertEquals("", bean.getSQLtext());
        //13
        q = "hz cho";
        q += BLANCK_SHIT + SECTION_DELIMITER + BLANCK_SHIT;
        q += ":olol:";
        q += "rhrhrh PORTUMEYA";
        expected = "hz cho";
        bean.setSQLtext(q);
        assertEquals(expected, bean.getSQLtext());
    }
}