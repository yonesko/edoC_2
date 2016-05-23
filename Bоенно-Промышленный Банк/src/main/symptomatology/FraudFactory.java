package main.symptomatology;

import java.util.Arrays;
import java.util.List;

/**
 * Created by gleb on 21.05.16.
 */
public class FraudFactory {
    public List<PaymentSymptom> getAllFrauds() {
        return Arrays.asList(new One(),
                new Three(),
                new Four(),
                new Five(),
                new Six(),
                new Seven()
        );
    }
}
