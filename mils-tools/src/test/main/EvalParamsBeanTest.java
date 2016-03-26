package main;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by gleb on 26.03.16.
 */
public class EvalParamsBeanTest {
    private final String BLANCK_SHIT = "\n\t\n\t  \n\t\n\n \t";
    private static final String SECTION_DELIMITER = "Query parameters";
    @Test
    public void setSQLtext() throws Exception {
        EvalParamsBean bean = new EvalParamsBean();

        bean.setSQLtext("");
        assertEquals("Empty query", bean.getSQLtext());

        bean.setSQLtext("   \t\n\t");
        assertEquals("Empty query", bean.getSQLtext());

        bean.setSQLtext("    ");
        assertEquals("Empty query", bean.getSQLtext());

        bean.setSQLtext("\n\n\n\n\n");
        assertEquals("Empty query", bean.getSQLtext());


        bean.setSQLtext("tu,mrntd45y\nerg445445\n");
        assertEquals(String.format("Empty parameters or absent \"%s\" string", SECTION_DELIMITER), bean.getSQLtext());

        String q =
                String.format("SELECCV * FROM :POROPR: %s%s%s  :POROPR:  678 35 OVOVERDI%s",
                        BLANCK_SHIT,
                        SECTION_DELIMITER,
                        BLANCK_SHIT,
                        BLANCK_SHIT);
        String expected =
                "SELECCV * FROM '678 35 OVOVERDI'";
        bean.setSQLtext(q);
        assertEquals(expected, bean.getSQLtext());



    }
}