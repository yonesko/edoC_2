import org.rauschig.jarchivelib.*;

import java.io.*;
/**
dat shit extracts jars frim gzip archive to the corresponding dirictories
 */
public class IziPatch {
    static final String PROBLEM_ARCH_READING = "Cant read archive";
    static final String HOME_UNSET = "HOME environment vriable is not set";
    static final String HOME = "HOME";
    static final String USAGE = "Usage: " + IziPatch.class.getName() + " PATCH_ARCHIVE_FILE";

    public static void main(String...args) {
        String sHome = new String();
        File fTar, fHome;
        //check errors
        if (args.length == 0) {
            System.err.println(USAGE);
            System.exit(1);
        }
        if ((sHome = System.getenv(HOME)) == null || sHome.isEmpty()) {
            System.err.println(HOME_UNSET);
            System.exit(1);
        }
        fHome = new File(sHome);
        //open archive
        fTar = new File(args[0]);
        System.out.println(fTar.getAbsoluteFile() + ":");
        Archiver archiver = ArchiverFactory.createArchiver(ArchiveFormat.TAR, CompressionType.GZIP);
        ArchiveStream stream = null;
        try {
            stream = archiver.stream(fTar);
        } catch (IOException e) {
            System.err.println(PROBLEM_ARCH_READING);
            System.exit(1);
        }
        ArchiveEntry entry;
        String name;
        File destJar;
        //read archive
        //extract .jar to the corresponding directory
        System.out.println();
        try {
            while((entry = stream.getNextEntry()) != null) {
                name = entry.getName();
                if (name.endsWith(".jar")) {
                    destJar = entry.extract(fHome);
                    System.out.println(name + " ->");
                    System.out.println(destJar.getAbsolutePath());
                    System.out.println();
                }
            }
            stream.close();
        } catch (IOException e) {
            System.err.println(PROBLEM_ARCH_READING);
            System.exit(1);
        }
    }
}
