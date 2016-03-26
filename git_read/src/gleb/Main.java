package gleb;

import org.eclipse.jgit.lib.Repository;
import org.eclipse.jgit.storage.file.FileRepositoryBuilder;

import java.io.File;
import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        Repository existingRepo = new FileRepositoryBuilder()
                .setGitDir(new File("c:\\Users\\Glebushka\\Documents\\edoC\\.git\\"))
                .build();
        System.out.println(existingRepo.getBranch());
    }
}
