package routinekiller;

import freemarker.template.Configuration;
import freemarker.template.Template;

import java.io.File;
import java.io.IOException;

public class Freemaker {
    private static final Configuration cfg = new Configuration();

    static {
        try {
            Freemaker.cfg.setDirectoryForTemplateLoading(new File("/home/gleb/Documents/codingGame/GOLDColumns/src/main/resources"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static Template getTemplate(String name) throws IOException {
        return Freemaker.cfg.getTemplate(name);
    }
}
