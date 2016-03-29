import org.rauschig.jarchivelib.*;

import java.io.File;
import java.io.IOException;

/**
dat shit extracts jars frim gzip archive to the corresponding dirictories
 */
public class IziPatch {
    static final ArchiveFormat ARCH_TYPE = ArchiveFormat.TAR;
    private File fArch;
    private File fHome;

    public IziPatch(File fArch, File fHome) {
        this.fArch = fArch;
        this.fHome = fHome;
    }

    public static void main(String...args) {
        String sHome;
        String sArch;
        IziPatch iziPatch;
        //check errors
        if (args.length == 0) {
            usage();
            System.exit(1);
        }
        sHome = System.getenv("HOME");
        sArch = args[0];
        if (sHome == null || sHome.length() == 0) {
            System.err.println(FatalErrs.HOME_UNSET);
            System.exit(1);
        }
        File fArch = new File(sArch);
        File fHome = new File(sHome);
        if (!fArch.exists() || !fArch.isFile()) {
            System.err.println(String.format("%s %s",FatalErrs.NO_FILE, sArch));
            System.exit(1);
        }

        iziPatch = new IziPatch(fArch, fHome);
        iziPatch.scatterJars();
    }

    private void scatterJars() {
        Archiver archiver = ArchiverFactory.createArchiver(ArchiveFormat.TAR, CompressionType.GZIP);
        ArchiveStream stream = null;
        try {
            stream = archiver.stream(fArch);
        } catch (IOException e) {
            System.err.println(FatalErrs.PROBLEM_ARCH_READING);
            System.exit(1);
        }
        ArchiveEntry entry;
        //read archive
        //extract .jar to the corresponding directory
        System.out.println(fArch);
        try {
            while((entry = stream.getNextEntry()) != null) {
                backUpExistingJar(entry);
                throwJar(entry);
            }
            stream.close();
        } catch (IOException e) {
            System.err.println(FatalErrs.PROBLEM_ARCH_READING);
            System.exit(1);
        }
    }

    private void backUpExistingJar(ArchiveEntry entry) throws IOException {
        File destJar = new File(fHome, entry.getName());
        String newName;
        if (destJar.exists() && destJar.isFile()) {
            newName = String.format("%s.before.%s", destJar.getName(), fArch.getName());
            destJar.renameTo(new File(destJar + newName));
        }
    }

    private void throwJar(ArchiveEntry entry) throws IOException {
        String name = entry.getName();
        File destJar;
        if (name.endsWith(".jar") && !entry.isDirectory()) {
            destJar = entry.extract(fHome);
            System.out.println(name + " ->");
            System.out.println(destJar);
            System.out.println();
        }

    }
    private static void usage() {
        System.out.println("Usage: " + IziPatch.class.getName() + " PATCH_ARCHIVE_FILE...");
    }
}
