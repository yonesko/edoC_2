package routinewriter.server.templater;

import freemarker.template.Template;
import freemarker.template.TemplateException;
import routinewriter.Freemaker;
import routinewriter.server.templater.model.InsertMethod;
import routinewriter.server.templater.model.UpdateMethod;

import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.StringWriter;
import java.util.Arrays;

/**
 * fils tempalte and print it out
 */
public class Generator {
    public static String makeInsert(String table, String[] cols) throws IOException, TemplateException {
        StringWriter stringWriter = new StringWriter();
        InsertMethod insertMethod = new InsertMethod(Arrays.asList(cols), table);

        Template temp = Freemaker.getTemplate("insertMethodTemplate");
        temp.process(insertMethod, stringWriter);

        return stringWriter.toString();
    }
    public static String makeUpdate(String table, String[] setted, String[] primary) throws IOException, TemplateException {
        StringWriter stringWriter = new StringWriter();
        UpdateMethod updateMethod = new UpdateMethod(Arrays.asList(setted), Arrays.asList(primary), table);

        Template temp = Freemaker.getTemplate("updateMethodTemplate");
        temp.process(updateMethod, stringWriter);

        return stringWriter.toString();
    }
}