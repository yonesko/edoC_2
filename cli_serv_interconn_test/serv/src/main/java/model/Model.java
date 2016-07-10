package model;

import common.ModelRemote;

import java.math.BigDecimal;
import java.rmi.RemoteException;
import java.time.Instant;
import java.util.Random;

/**
 * Created by gleb on 10 July 2016.
 */
public class Model implements ModelRemote {
    public String getTime() {
        return Instant.now().toString();
    }

    public int getNum() {
        System.out.println("common.Model.getNum");
        return new Random().nextInt();
    }

    @Override
    public BigDecimal sqr(BigDecimal d) throws RemoteException {
        return d.pow(2);
    }


}
