package server.templater;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;

import java.io.File;
import java.io.IOException;
import java.io.StringWriter;
import java.io.Writer;
import java.util.Map;

public class MyGenerator {
    private static final String HTML_DIR = "templates";
    private final Configuration cfg;
    private static final MyGenerator ourInstance = new MyGenerator();

    private MyGenerator() {
        this.cfg = new Configuration();
    }

    public static MyGenerator getInstance() {
        return MyGenerator.ourInstance;
    }
    public String getPage(String filename, Map<String, Object> data) {
        Writer stream = new StringWriter();

        try {
            Template template = this.cfg.getTemplate(MyGenerator.HTML_DIR + File.separator + filename);
            template.process(data, stream);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (TemplateException e) {
            e.printStackTrace();
        }

        return stream.toString();
    }
}
