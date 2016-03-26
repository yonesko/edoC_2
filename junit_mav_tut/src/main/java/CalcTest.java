import static org.junit.Assert.assertEquals;
import org.junit.Test;

public class CalcTest {
    @Test
    public void testPlus() throws Exception {
        Calc c = new Calc();
        double sum = c.plus(5.6, 7.8);
        assertEquals(13.2, sum);
    }
}