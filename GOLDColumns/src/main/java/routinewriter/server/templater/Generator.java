package routinewriter.server.templater;

import freemarker.template.Template;
import freemarker.template.TemplateException;
import routinewriter.Freemaker;
import routinewriter.server.templater.model.InsertMethod;

import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.Arrays;

/**
 * fils tempalte and print it out
 */
public class Generator {
    public static String makeInsert(String table, String[] cols) throws IOException, TemplateException {
        String result = null;
        Template temp = null;
        InsertMethod insertStatement = new InsertMethod(Arrays.asList(cols), table);

        temp = Freemaker.getTemplate("insertMethodTemplate");

        temp.process(insertStatement, new OutputStreamWriter(System.out));


        return result;
    }
}