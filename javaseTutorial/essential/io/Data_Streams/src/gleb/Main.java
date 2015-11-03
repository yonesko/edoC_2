package gleb;

import java.io.DataOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        DataOutputStream out = new DataOutputStream(new FileOutputStream("res/n"));
        out.writeUTF("privet");
    }
}
