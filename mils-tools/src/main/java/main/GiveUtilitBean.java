package main;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.rauschig.jarchivelib.ArchiveFormat;
import org.rauschig.jarchivelib.Archiver;
import org.rauschig.jarchivelib.ArchiverFactory;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

@ManagedBean
@RequestScoped
public class GiveUtilitBean {
    private static final Logger logger = LogManager.getLogger();
    static String source[] = {
            "/home/gleb/Documents/codingGame/iZiPatch/target/iZiPatch.jar",
            "/home/gleb/Documents/codingGame/iZiPatch/iZiPatch.bash"
    };
    static String archiveName = "iZiPatch";
    static String archivePath = "/tmp";

    public void download() throws IOException {
        FacesContext fc = FacesContext.getCurrentInstance();
        ExternalContext ec = FacesContext.getCurrentInstance().getExternalContext();
        File archive = makeArchive();

        ec.responseReset(); // Some JSF component library or some Filter might have set some headers in the buffer beforehand. We want to get rid of them, else it may collide.
        ec.setResponseContentType(ec.getMimeType(archive.getAbsolutePath())); // Check http://www.iana.org/assignments/media-types for all types. Use if necessary ExternalContext#getMimeType() for auto-detection based on filename.
        ec.setResponseContentLength((int) archive.length()); // Set it with the file size. This header is optional. It will work if it's omitted, but the download progress will be unknown.
        ec.setResponseHeader("Content-Disposition", "attachment; filename=\"" + archive.getName() + "\""); // The Save As popup magic is done here. You can give it any file name you want, this only won't work in MSIE, it will use current request URL as file name instead.

        OutputStream output = ec.getResponseOutputStream();
        FileInputStream in = new FileInputStream(archive);
        byte[] buffer = new byte[4096];
        int length;
        while ((length = in.read(buffer)) > 0){
            output.write(buffer, 0, length);
        }
        in.close();
        output.flush();

        fc.responseComplete(); // Important! Otherwise JSF will attempt to render the response which obviously will fail since it's already written with a file and closed.
        logger.info("sent {} {} bytes", archive.getName(), archive.length());
    }

    private File makeArchive() throws IOException {
        List<File> src = new ArrayList<File>();
        for (String s : source)
            src.add(new File(s));

        Archiver archiver = ArchiverFactory.createArchiver(ArchiveFormat.ZIP);
        return archiver.create(archiveName, new File(archivePath), src.toArray(new File[src.size()]));
    }
}
