import org.rauschig.jarchivelib.*;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

/**
dat shit extracts jars frim gzip archive to the corresponding dirictories
 */
public class IziPatch {
    static final ArchiveFormat ARCH_TYPE = ArchiveFormat.TAR;
    private Path pArch;
    private Path pHome;

    public IziPatch(Path pArch, Path pHome) {
        this.pArch = pArch;
        this.pHome = pHome;
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
        if (Files.notExists(Paths.get(sArch))) {
            System.err.println(String.format("%s %s",FatalErrs.NO_FILE, sArch));
            System.exit(1);
        }

        iziPatch = new IziPatch(Paths.get(sArch), Paths.get(sHome));
        iziPatch.scatterJars();
    }

    private void scatterJars() {
        Archiver archiver = ArchiverFactory.createArchiver(ArchiveFormat.TAR, CompressionType.GZIP);
        ArchiveStream stream = null;
        try {
            stream = archiver.stream(pArch.toFile());
        } catch (IOException e) {
            System.err.println(FatalErrs.PROBLEM_ARCH_READING);
            System.exit(1);
        }
        ArchiveEntry entry;
        //read archive
        //extract .jar to the corresponding directory
        System.out.println(pArch);
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
        Path destJar = pHome.resolve(entry.getName());
        String newName;
        if (Files.exists(destJar) && Files.isRegularFile(destJar)) {
            newName = String.format("%s.before.%s", destJar.getFileName(), pArch.getFileName());
            Files.move(destJar, Paths.get(destJar.getParent().toString(), newName), StandardCopyOption.REPLACE_EXISTING);
        }
    }

    private void throwJar(ArchiveEntry entry) throws IOException {
        String name = entry.getName();
        Path destJar;
        if (name.endsWith(".jar") && !entry.isDirectory()) {
            destJar = Paths.get(entry.extract(pHome.toFile()).getAbsolutePath());
            System.out.println(name + " ->");
            System.out.println(destJar);
            System.out.println();
        }

    }
    private static void usage() {
        System.out.println("Usage: " + IziPatch.class.getName() + " PATCH_ARCHIVE_FILE...");
    }
}
