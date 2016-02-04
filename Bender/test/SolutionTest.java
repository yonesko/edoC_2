import org.junit.Test;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.PrintStream;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by gleb on 16.12.15.
 */
public class SolutionTest {
    static BufferedReader ansFile;
    static List<String> ans = new ArrayList<>();
    static final int TEST_COUNT = 12;

    @Test
    public void testMain() throws Exception {
        for (int i = 1; i <= TEST_COUNT; i++) {
            ans.clear();

            System.out.println("Testing " + i);

            ansFile = new BufferedReader(new FileReader(String.format("res/Test_%d_output.txt", i)));
            String t;
            while ( (t = ansFile.readLine()) != null )
                ans.add(t);


            Foresee foresee = new Foresee(String.format("res/Test_%d_input.txt", i));
            foresee.start();

            assertArrayEquals(ans.toArray(), foresee.steps.toArray());


        }
    }

}