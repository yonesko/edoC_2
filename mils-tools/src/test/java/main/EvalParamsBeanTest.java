package main;

import org.junit.Before;
import org.junit.Test;

import java.lang.reflect.Field;

import static org.junit.Assert.assertEquals;

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
        Field fSecDelim = beanClass.getDeclaredField("SECTION_DELIMITER");
        fSecDelim.setAccessible(true);

        SECTION_DELIMITER = String.valueOf((fSecDelim.get(bean)));

        fSecDelim.setAccessible(false);

        FAIL_MSG = String.format("Cant find \"%s\" delimiter string", SECTION_DELIMITER);
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
        q += "\n" + SECTION_DELIMITER + "\n";
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
        q = "WITH SITE_VIEW AS ( SELECT DISTINCT RESPERE FROM RESEAU   WHERE TRUNC(CURRENT_DATE) BETWEEN RESDDEB AND RESDFIN   AND RESRESID = pkresrel.get_resid(1,RESPERE, :TYPERESEAUASSORTCDBLE:)   AND RESSITE = :RESEAU:     UNION       SELECT DISTINCT ROBID FROM RESOBJ   WHERE ROBRESID = pkresrel.get_resid(1,ROBID, :TYPERESEAUASSORTCDBLE:)   AND ROBID = :RESEAU:   )SELECT   aracexr  ,PKSTRUCOBJ.get_desc(1,aruclibl, :LANGUE:) , aracexvl, DECODE(pkparpostes.get_postlibl(1,:CLASSE:, 731, arutypul, :LANGUE:),'-1','',pkparpostes.get_postlibl(1,:CLASSE:, 731, arutypul, :LANGUE:)), aracexta, ararefc, foucnuf, foulibl, aranfilf, fccnum, aracinr, aracinl, aracfin, araccin, TO_CHAR(araddeb,'DD/MM/RR'), TO_CHAR(aradfin,'DD/MM/RR'), arasite, pkresobj.get_desc(1,arasite, :LANGUE:),aracean, aratfou, aracdble,pkparpostes.get_postlibl(1,:CLASSE:, 1071, aratcde, :LANGUE:), foutype ,araseqvl,  decode(PKCTRLACCES.ctrl_reseau(1,:CLASSE:, CURRENT_DATE, :USER:, pkresrel.get_resid(1,arasite, :TYPERESEAUASSORTCDBLE:), arasite), 1, 1,0) FROM  ARTRAC, ARTUL aru,  SITE_VIEW , ARTUC ara,  foudgene fou, fouccom fcc WHERE  aracexr = '2000000333' AND  1 = 1  AND  1 = 1  AND  1 = 1  AND  1 = 1  AND  1 = 1  AND  1 = 1  AND  1 = 1  AND  1 = 1  AND  aracdble=1  AND  1 = 1  AND aracinl = arucinl AND araccin = fccccin AND aracfin = foucfin AND artcinr = arucinr  AND PKCTRLACCES.ctrl_structure(1,:CLASSE:, CURRENT_DATE, :USER:, -1, aracinr) = 1   AND artlocal = 0  AND nvl(fccloca,0)=0  AND arasite IN SITE_VIEW.RESPERE UNION SELECT  /*+ index(ara artuc_i2)*/  aracexr  ,PKSTRUCOBJ.get_desc(1,aruclibl, :LANGUE:) , aracexvl, DECODE(pkparpostes.get_postlibl(1,:CLASSE:, 731, arutypul, :LANGUE:),'-1','',pkparpostes.get_postlibl(1,:CLASSE:, 731, arutypul, :LANGUE:)), aracexta, ararefc, foucnuf, foulibl, aranfilf, fccnum, aracinr, aracinl, aracfin, araccin, TO_CHAR(araddeb,'DD/MM/RR'), TO_CHAR(aradfin,'DD/MM/RR'), arasite, pkresobj.get_desc(1,arasite, :LANGUE:),aracean, aratfou, aracdble,pkparpostes.get_postlibl(1,:CLASSE:, 1071, aratcde, :LANGUE:), foutype ,araseqvl,  decode(PKCTRLACCES.ctrl_reseau(1,:CLASSE:, CURRENT_DATE, :USER:, pkresrel.get_resid(1,arasite, :TYPERESEAUASSORTCDBLE:), arasite), 1, 1,0) FROM  ARTRAC, ARTUL aru,  SITE_VIEW , ARTUC ara,  artcoca, foudgene fou, fouccom fcc ,artvl WHERE aracinl = arucinl AND araseqvl = arlseqvl AND araccin = fccccin AND aracfin = foucfin AND (pkartstock.recupcinluvc(1,aracinl) = arccinv  or arlcinv  = arccinv) AND aracinr = arccinr AND artcinr = arccinr AND arcetat = 1 AND TRUNC(CURRENT_DATE) BETWEEN arcddeb AND arcdfin AND  arccode = '2000000333' AND  1 = 1  AND  1 = 1  AND  1 = 1  AND  1 = 1  AND  1 = 1  AND  1 = 1  AND  1 = 1  AND  1 = 1  AND  aracdble=1  AND  1 = 1   AND PKCTRLACCES.ctrl_structure(1,:CLASSE:, CURRENT_DATE, :USER:, -1, aracinr) = 1   AND artlocal = 0  AND nvl(fccloca,0)=0  AND arasite IN SITE_VIEW.RESPERE UNION SELECT  /*+ index(ara artuc_i2)*/  aracexr  ,PKSTRUCOBJ.get_desc(1,aruclibl, :LANGUE:) , aracexvl, DECODE(pkparpostes.get_postlibl(1,:CLASSE:, 731, arutypul, :LANGUE:),'-1','',pkparpostes.get_postlibl(1,:CLASSE:, 731, arutypul, :LANGUE:)), aracexta, ararefc, foucnuf, foulibl, aranfilf, fccnum, aracinr, aracinl, aracfin, araccin, TO_CHAR(araddeb,'DD/MM/RR'), TO_CHAR(aradfin,'DD/MM/RR'), arasite, pkresobj.get_desc(1,arasite, :LANGUE:),aracean, aratfou, aracdble,pkparpostes.get_postlibl(1,:CLASSE:, 1071, aratcde, :LANGUE:), foutype ,araseqvl,  decode(PKCTRLACCES.ctrl_reseau(1,:CLASSE:, CURRENT_DATE, :USER:, pkresrel.get_resid(1,arasite, :TYPERESEAUASSORTCDBLE:), arasite), 1, 1,0) FROM  ARTRAC, ARTUL aru,  SITE_VIEW , ARTUC ara,  foudgene fou, fouccom fcc WHERE aracinl = arucinl AND araccin = fccccin AND aracfin = foucfin AND artcinr = aracinr AND  artcexr = '2000000333' AND  1 = 1  AND  1 = 1  AND  1 = 1  AND  1 = 1  AND  1 = 1  AND  1 = 1  AND  1 = 1  AND  1 = 1  AND  aracdble=1  AND  1 = 1   AND PKCTRLACCES.ctrl_structure(1,:CLASSE:, CURRENT_DATE, :USER:, -1, aracinr) = 1   AND artlocal = 0  AND nvl(fccloca,0)=0  AND arasite IN SITE_VIEW.RESPERE\n" +
                "   DBG.2377 Query parameters\n" +
                ":CommandableLe:  null\n" +
                ":RESEAU:        10544\n" +
                ":CommandableSurSite:  false\n" +
                ":CLASSE: \t     10\n" +
                ":TYPERESEAUASSORTCDBLE:  1\n" +
                "  \t \t:LANGUE: \t\t  RU\n" +
                ":USER:   vv\n" +
                ":SITECONNEXION:  10544";
        expected = "WITH SITE_VIEW AS ( SELECT\n" +
                "        DISTINCT RESPERE \n" +
                "    FROM\n" +
                "        RESEAU   \n" +
                "    WHERE\n" +
                "        TRUNC(CURRENT_DATE) BETWEEN RESDDEB AND RESDFIN   \n" +
                "        AND RESRESID = pkresrel.get_resid(1,RESPERE, '1')   \n" +
                "        AND RESSITE = '10544'     \n" +
                "    UNION\n" +
                "    SELECT\n" +
                "        DISTINCT ROBID \n" +
                "    FROM\n" +
                "        RESOBJ   \n" +
                "    WHERE\n" +
                "        ROBRESID = pkresrel.get_resid(1,ROBID, '1')   \n" +
                "        AND ROBID = '10544'   )SELECT\n" +
                "        aracexr  ,\n" +
                "        PKSTRUCOBJ.get_desc(1,\n" +
                "        aruclibl,\n" +
                "        'RU') ,\n" +
                "        aracexvl,\n" +
                "        DECODE(pkparpostes.get_postlibl(1,\n" +
                "        '10',\n" +
                "        731,\n" +
                "        arutypul,\n" +
                "        'RU'),\n" +
                "        '-1',\n" +
                "        '',\n" +
                "        pkparpostes.get_postlibl(1,\n" +
                "        '10',\n" +
                "        731,\n" +
                "        arutypul,\n" +
                "        'RU')),\n" +
                "        aracexta,\n" +
                "        ararefc,\n" +
                "        foucnuf,\n" +
                "        foulibl,\n" +
                "        aranfilf,\n" +
                "        fccnum,\n" +
                "        aracinr,\n" +
                "        aracinl,\n" +
                "        aracfin,\n" +
                "        araccin,\n" +
                "        TO_CHAR(araddeb,\n" +
                "        'DD/MM/RR'),\n" +
                "        TO_CHAR(aradfin,\n" +
                "        'DD/MM/RR'),\n" +
                "        arasite,\n" +
                "        pkresobj.get_desc(1,\n" +
                "        arasite,\n" +
                "        'RU'),\n" +
                "        aracean,\n" +
                "        aratfou,\n" +
                "        aracdble,\n" +
                "        pkparpostes.get_postlibl(1,\n" +
                "        '10',\n" +
                "        1071,\n" +
                "        aratcde,\n" +
                "        'RU'),\n" +
                "        foutype ,\n" +
                "        araseqvl,\n" +
                "        decode(PKCTRLACCES.ctrl_reseau(1,\n" +
                "        '10',\n" +
                "        CURRENT_DATE,\n" +
                "        'vv',\n" +
                "        pkresrel.get_resid(1,\n" +
                "        arasite,\n" +
                "        '1'),\n" +
                "        arasite),\n" +
                "        1,\n" +
                "        1,\n" +
                "        0) \n" +
                "    FROM\n" +
                "        ARTRAC,\n" +
                "        ARTUL aru,\n" +
                "        SITE_VIEW ,\n" +
                "        ARTUC ara,\n" +
                "        foudgene fou,\n" +
                "        fouccom fcc \n" +
                "    WHERE\n" +
                "        aracexr = '2000000333' \n" +
                "        AND  1 = 1  \n" +
                "        AND  1 = 1  \n" +
                "        AND  1 = 1  \n" +
                "        AND  1 = 1  \n" +
                "        AND  1 = 1  \n" +
                "        AND  1 = 1  \n" +
                "        AND  1 = 1  \n" +
                "        AND  1 = 1  \n" +
                "        AND  aracdble=1  \n" +
                "        AND  1 = 1  \n" +
                "        AND aracinl = arucinl \n" +
                "        AND araccin = fccccin \n" +
                "        AND aracfin = foucfin \n" +
                "        AND artcinr = arucinr  \n" +
                "        AND PKCTRLACCES.ctrl_structure(1,'10', CURRENT_DATE, 'vv', -1, aracinr) = 1   \n" +
                "        AND artlocal = 0  \n" +
                "        AND nvl(fccloca,0)=0  \n" +
                "        AND arasite IN SITE_VIEW.RESPERE \n" +
                "    UNION\n" +
                "    SELECT\n" +
                "        /*+ index(ara artuc_i2)*/  aracexr  ,\n" +
                "        PKSTRUCOBJ.get_desc(1,\n" +
                "        aruclibl,\n" +
                "        'RU') ,\n" +
                "        aracexvl,\n" +
                "        DECODE(pkparpostes.get_postlibl(1,\n" +
                "        '10',\n" +
                "        731,\n" +
                "        arutypul,\n" +
                "        'RU'),\n" +
                "        '-1',\n" +
                "        '',\n" +
                "        pkparpostes.get_postlibl(1,\n" +
                "        '10',\n" +
                "        731,\n" +
                "        arutypul,\n" +
                "        'RU')),\n" +
                "        aracexta,\n" +
                "        ararefc,\n" +
                "        foucnuf,\n" +
                "        foulibl,\n" +
                "        aranfilf,\n" +
                "        fccnum,\n" +
                "        aracinr,\n" +
                "        aracinl,\n" +
                "        aracfin,\n" +
                "        araccin,\n" +
                "        TO_CHAR(araddeb,\n" +
                "        'DD/MM/RR'),\n" +
                "        TO_CHAR(aradfin,\n" +
                "        'DD/MM/RR'),\n" +
                "        arasite,\n" +
                "        pkresobj.get_desc(1,\n" +
                "        arasite,\n" +
                "        'RU'),\n" +
                "        aracean,\n" +
                "        aratfou,\n" +
                "        aracdble,\n" +
                "        pkparpostes.get_postlibl(1,\n" +
                "        '10',\n" +
                "        1071,\n" +
                "        aratcde,\n" +
                "        'RU'),\n" +
                "        foutype ,\n" +
                "        araseqvl,\n" +
                "        decode(PKCTRLACCES.ctrl_reseau(1,\n" +
                "        '10',\n" +
                "        CURRENT_DATE,\n" +
                "        'vv',\n" +
                "        pkresrel.get_resid(1,\n" +
                "        arasite,\n" +
                "        '1'),\n" +
                "        arasite),\n" +
                "        1,\n" +
                "        1,\n" +
                "        0) \n" +
                "    FROM\n" +
                "        ARTRAC,\n" +
                "        ARTUL aru,\n" +
                "        SITE_VIEW ,\n" +
                "        ARTUC ara,\n" +
                "        artcoca,\n" +
                "        foudgene fou,\n" +
                "        fouccom fcc ,\n" +
                "        artvl \n" +
                "    WHERE\n" +
                "        aracinl = arucinl \n" +
                "        AND araseqvl = arlseqvl \n" +
                "        AND araccin = fccccin \n" +
                "        AND aracfin = foucfin \n" +
                "        AND (\n" +
                "            pkartstock.recupcinluvc(1,aracinl) = arccinv  \n" +
                "            or arlcinv  = arccinv\n" +
                "        ) \n" +
                "        AND aracinr = arccinr \n" +
                "        AND artcinr = arccinr \n" +
                "        AND arcetat = 1 \n" +
                "        AND TRUNC(CURRENT_DATE) BETWEEN arcddeb AND arcdfin \n" +
                "        AND  arccode = '2000000333' \n" +
                "        AND  1 = 1  \n" +
                "        AND  1 = 1  \n" +
                "        AND  1 = 1  \n" +
                "        AND  1 = 1  \n" +
                "        AND  1 = 1  \n" +
                "        AND  1 = 1  \n" +
                "        AND  1 = 1  \n" +
                "        AND  1 = 1  \n" +
                "        AND  aracdble=1  \n" +
                "        AND  1 = 1   \n" +
                "        AND PKCTRLACCES.ctrl_structure(1,'10', CURRENT_DATE, 'vv', -1, aracinr) = 1   \n" +
                "        AND artlocal = 0  \n" +
                "        AND nvl(fccloca,0)=0  \n" +
                "        AND arasite IN SITE_VIEW.RESPERE \n" +
                "    UNION\n" +
                "    SELECT\n" +
                "        /*+ index(ara artuc_i2)*/  aracexr  ,\n" +
                "        PKSTRUCOBJ.get_desc(1,\n" +
                "        aruclibl,\n" +
                "        'RU') ,\n" +
                "        aracexvl,\n" +
                "        DECODE(pkparpostes.get_postlibl(1,\n" +
                "        '10',\n" +
                "        731,\n" +
                "        arutypul,\n" +
                "        'RU'),\n" +
                "        '-1',\n" +
                "        '',\n" +
                "        pkparpostes.get_postlibl(1,\n" +
                "        '10',\n" +
                "        731,\n" +
                "        arutypul,\n" +
                "        'RU')),\n" +
                "        aracexta,\n" +
                "        ararefc,\n" +
                "        foucnuf,\n" +
                "        foulibl,\n" +
                "        aranfilf,\n" +
                "        fccnum,\n" +
                "        aracinr,\n" +
                "        aracinl,\n" +
                "        aracfin,\n" +
                "        araccin,\n" +
                "        TO_CHAR(araddeb,\n" +
                "        'DD/MM/RR'),\n" +
                "        TO_CHAR(aradfin,\n" +
                "        'DD/MM/RR'),\n" +
                "        arasite,\n" +
                "        pkresobj.get_desc(1,\n" +
                "        arasite,\n" +
                "        'RU'),\n" +
                "        aracean,\n" +
                "        aratfou,\n" +
                "        aracdble,\n" +
                "        pkparpostes.get_postlibl(1,\n" +
                "        '10',\n" +
                "        1071,\n" +
                "        aratcde,\n" +
                "        'RU'),\n" +
                "        foutype ,\n" +
                "        araseqvl,\n" +
                "        decode(PKCTRLACCES.ctrl_reseau(1,\n" +
                "        '10',\n" +
                "        CURRENT_DATE,\n" +
                "        'vv',\n" +
                "        pkresrel.get_resid(1,\n" +
                "        arasite,\n" +
                "        '1'),\n" +
                "        arasite),\n" +
                "        1,\n" +
                "        1,\n" +
                "        0) \n" +
                "    FROM\n" +
                "        ARTRAC,\n" +
                "        ARTUL aru,\n" +
                "        SITE_VIEW ,\n" +
                "        ARTUC ara,\n" +
                "        foudgene fou,\n" +
                "        fouccom fcc \n" +
                "    WHERE\n" +
                "        aracinl = arucinl \n" +
                "        AND araccin = fccccin \n" +
                "        AND aracfin = foucfin \n" +
                "        AND artcinr = aracinr \n" +
                "        AND  artcexr = '2000000333' \n" +
                "        AND  1 = 1  \n" +
                "        AND  1 = 1  \n" +
                "        AND  1 = 1  \n" +
                "        AND  1 = 1  \n" +
                "        AND  1 = 1  \n" +
                "        AND  1 = 1  \n" +
                "        AND  1 = 1  \n" +
                "        AND  1 = 1  \n" +
                "        AND  aracdble=1  \n" +
                "        AND  1 = 1   \n" +
                "        AND PKCTRLACCES.ctrl_structure(1,'10', CURRENT_DATE, 'vv', -1, aracinr) = 1   \n" +
                "        AND artlocal = 0  \n" +
                "        AND nvl(fccloca,0)=0  \n" +
                "        AND arasite IN SITE_VIEW.RESPERE";
        bean.setSQLtext(q);
        assertEquals(expected, bean.getSQLtext());
        //14
        q = "select tpar.tparpost, tpar.tparlibc   from tra_parpostes tpar  where tpar.tpartabl = 1170    and tpar.tparcmag = :CLASSE:    and langue = SUBSTR(:LANG:, -2, 2) \r\n   DBG.99      Query parameters\r\n:LANG:\t\t\tru_RU\n:CLASSE:\t\t10\n:SHIPMENT_TYPE:\t\tnull";
        expected = "select\n" +
                "        tpar.tparpost,\n" +
                "        tpar.tparlibc   \n" +
                "    from\n" +
                "        tra_parpostes tpar  \n" +
                "    where\n" +
                "        tpar.tpartabl = 1170    \n" +
                "        and tpar.tparcmag = '10'    \n" +
                "        and langue = SUBSTR('ru_RU', -2, 2)";
        bean.setSQLtext(q);
        assertEquals(expected, bean.getSQLtext());
        //15
        q = "select tpar.tparpost, tpar.tparlibc   from tra_parpostes tpar  where tpar.tpartabl = 1170    and tpar.tparcmag = :CLASSE:    and langue = SUBSTR(:LANG:, -2, 2) \n\r   DBG.99      Query parameters\n\r:LANG:\t\t\tru_RU\n:CLASSE:\t\t10\n:SHIPMENT_TYPE:\t\tnull";
        expected = "select\n" +
                "        tpar.tparpost,\n" +
                "        tpar.tparlibc   \n" +
                "    from\n" +
                "        tra_parpostes tpar  \n" +
                "    where\n" +
                "        tpar.tpartabl = 1170    \n" +
                "        and tpar.tparcmag = '10'    \n" +
                "        and langue = SUBSTR('ru_RU', -2, 2)";
        bean.setSQLtext(q);
        assertEquals(expected, bean.getSQLtext());
        //16
        q = "select tpar.tparpost, tpar.tparlibc   from tra_parpostes tpar  where tpar.tpartabl = 1170    and tpar.tparcmag = :CLASSE:    and langue = SUBSTR(:LANG:, -2, 2) \n   DBG.99      Query parameters\n:LANG:\t\t\tru_RU\n:CLASSE:\t\t10\n:SHIPMENT_TYPE:\t\tnull";
        expected = "select\n" +
                "        tpar.tparpost,\n" +
                "        tpar.tparlibc   \n" +
                "    from\n" +
                "        tra_parpostes tpar  \n" +
                "    where\n" +
                "        tpar.tpartabl = 1170    \n" +
                "        and tpar.tparcmag = '10'    \n" +
                "        and langue = SUBSTR('ru_RU', -2, 2)";
        bean.setSQLtext(q);
        assertEquals(expected, bean.getSQLtext());

    }
}