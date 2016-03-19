import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

/**
 * Created by gleb on 04.02.16.
 */
public class Main {
    public static void main(String...argv) throws FileNotFoundException {
        if (argv.length < 1) {
            System.out.println("specify file");
            System.exit(1);
        }
        Scanner sFile = new Scanner(new File(argv[0]));
        sFile.useDelimiter("\\W+");
        Map<String, Integer> map = new TreeMap<String, Integer>();
        String word;

        while (sFile.hasNext()) {
            word = sFile.next();
            map.put(word, map.get(word)==null ? 1 : map.get(word) + 1);
        }


        for (Map.Entry<String, Integer> e : map.entrySet()) {
            System.out.println(e.getKey() + " " + e.getValue());
        }


    }
}
