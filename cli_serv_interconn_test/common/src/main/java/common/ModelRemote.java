package common;

import java.math.BigDecimal;
import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 * Created by gleb on 10 July 2016.
 */
public interface ModelRemote extends Remote {
    String getTime() throws RemoteException;

    int getNum() throws RemoteException;

    BigDecimal sqr(BigDecimal d) throws RemoteException;
}
