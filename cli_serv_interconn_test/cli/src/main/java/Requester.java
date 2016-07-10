import common.ModelRemote;

import java.math.BigDecimal;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

/**
 * Created by gleb on 10 July 2016.
 */
public class Requester {
    public static void main(String[] args) throws Exception {

        Registry registry = LocateRegistry.getRegistry(null);
        ModelRemote stub = (ModelRemote) registry.lookup("Hello");
        BigDecimal response = stub.sqr(new BigDecimal("13"));
        System.out.println("response: " + response);

    }

}
