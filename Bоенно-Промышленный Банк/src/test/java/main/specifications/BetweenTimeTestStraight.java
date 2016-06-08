package main.specifications;

import main.data.model.Payment;
import main.specifications.core.ISpecification;
import main.specifications.payment.BetweenTime;
import org.junit.Assert;
import org.junit.Test;

import java.time.Instant;
import java.time.LocalTime;

import static main.specifications.TestUtil.*;


/**
 * Created by gleb on 31.05.16.
 */
public class BetweenTimeTestStraight {
    private static Payment paymentsIn[] = new Payment[]{
            new Payment(ranValue(), Instant.parse("2016-06-01T18:49:08.039Z"), ranProd(), ranCli()),
            new Payment(ranValue(), Instant.parse("2016-06-01T13:49:08.039Z"), ranProd(), ranCli()),
            new Payment(ranValue(), Instant.parse("2006-06-01T10:49:08.039Z"), ranProd(), ranCli()),
            new Payment(ranValue(), Instant.parse("1716-06-01T13:49:08.039Z"), ranProd(), ranCli()),
            new Payment(ranValue(), Instant.parse("1970-01-01T09:00:00Z"), ranProd(), ranCli()),
            new Payment(ranValue(), Instant.parse("1970-01-01T23:00:00Z"), ranProd(), ranCli()),
            new Payment(ranValue(), Instant.parse("2016-06-01T22:59:59.999Z"), ranProd(), ranCli())};

    private static Payment paymentsNotIn[] = new Payment[]{
            new Payment(ranValue(), Instant.parse("1970-01-01T00:00:00Z"), ranProd(), ranCli()),
            new Payment(ranValue(), Instant.parse("9016-06-01T08:59:59.999Z"), ranProd(), ranCli()),
            new Payment(ranValue(), Instant.parse("2706-06-01T23:00:00.001Z"), ranProd(), ranCli()),
            new Payment(ranValue(), Instant.parse("1716-06-01T00:49:08.039Z"), ranProd(), ranCli()),
            new Payment(ranValue(), Instant.parse("2016-06-01T05:59:59.999Z"), ranProd(), ranCli())};
    @Test
    public void isSatisfiedBy() throws Exception {
        ISpecification spec = new BetweenTime(LocalTime.of(9, 0), LocalTime.of(23, 0));

        for (Payment payment : paymentsIn)
            Assert.assertTrue(payment.toString(), spec.isSatisfiedBy(payment));

        for (Payment payment : paymentsNotIn)
            Assert.assertFalse(payment.toString(), spec.isSatisfiedBy(payment));

    }
}