package main;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by gleb on 26.03.16.
 */
public class EvalParamsBeanTest {
    private final String BLANCK_SHIT = "\n\t\n\t  \n\t\n\n \t";
    private static final String SECTION_DELIMITER = "Query parameters";
    private static final String FAIL_MSG = String.format("Cant find \"%s\" delimiter string", SECTION_DELIMITER);
    @Test
    public void setSQLtext() throws Exception {
        EvalParamsBean bean = new EvalParamsBean();
        String q, expected;

        bean.setSQLtext("");
        assertEquals(FAIL_MSG, bean.getSQLtext());

        bean.setSQLtext(null);
        assertEquals(FAIL_MSG, bean.getSQLtext());

        bean.setSQLtext("   \t\n\t");
        assertEquals(FAIL_MSG, bean.getSQLtext());

        bean.setSQLtext("    ");
        assertEquals(FAIL_MSG, bean.getSQLtext());

        bean.setSQLtext("\n\n\n\n\n");
        assertEquals(FAIL_MSG, bean.getSQLtext());


        bean.setSQLtext("tu,mrntd45y\nerg445445\n");
        assertEquals(FAIL_MSG, bean.getSQLtext());

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
    }
}