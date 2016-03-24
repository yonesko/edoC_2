package server.servlets;

import org.rauschig.jarchivelib.ArchiveFormat;
import org.rauschig.jarchivelib.Archiver;
import org.rauschig.jarchivelib.ArchiverFactory;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class IziJarDownloaderServl extends HttpServlet {
    static String source[] = {
            "/home/gleb/Documents/codingGame/iZiPatch/target/iZiPatch.jar",
            "/home/gleb/Documents/codingGame/iZiPatch/iZiPatch.bash"
    };
    static String archiveName = "iZiPatch";
    static String archivePath = "/tmp";
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("sending utilita");
        //send file
        resp.setContentType("application/gzip");
        OutputStream out = resp.getOutputStream();
        FileInputStream in = new FileInputStream(makeArchive());
        byte[] buffer = new byte[4096];
        int length;
        while ((length = in.read(buffer)) > 0){
            out.write(buffer, 0, length);
        }
        in.close();
        out.flush();
    }

    private File makeArchive() throws IOException {
        List<File> src = new ArrayList<File>();
        for (String s : source)
            src.add(new File(s));

        Archiver archiver = ArchiverFactory.createArchiver(ArchiveFormat.ZIP);
        File archive = archiver.create(archiveName, new File(archivePath), src.toArray(new File[src.size()]));
        return archive;
    }
}
