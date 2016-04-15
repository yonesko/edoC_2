package routinekiller.server.templater;

import freemarker.template.Template;
import freemarker.template.TemplateException;
import routinekiller.Freemaker;
import routinekiller.server.templater.model.InsertMethod;
import routinekiller.server.templater.model.UpdateMethod;

import java.io.IOException;
import java.io.StringWriter;
import java.util.Arrays;

/**
 * fils tempalte and print it out
 */
public class Generator {
    public static String makeInsert(String table, String prefix, String[] cols, String colTypeText) throws IOException, TemplateException {
        StringWriter stringWriter = new StringWriter();
        InsertMethod insertMethod = new InsertMethod(Arrays.asList(cols), table, prefix, colTypeText);

        Template temp = Freemaker.getTemplate("insertMethodTemplate");
        temp.process(insertMethod, stringWriter);

        return stringWriter.toString();
    }
        public static String makeUpdate(String table, String prefix, String[] setted, String[] primary, String colTypeText) throws IOException, TemplateException {
        StringWriter stringWriter = new StringWriter();
        UpdateMethod updateMethod = new UpdateMethod(Arrays.asList(setted), Arrays.asList(primary), table, prefix, colTypeText);

        Template temp = Freemaker.getTemplate("updateMethodTemplate");
        temp.process(updateMethod, stringWriter);

        return stringWriter.toString();
    }
}