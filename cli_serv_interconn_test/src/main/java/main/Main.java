package main;

import common.ModelRemote;
import model.Model;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

/**
 * Created by gleb on 10 July 2016.
 */
public class Main {
    public static void main(String[] args) throws RemoteException {
        Model obj = new Model();

        ModelRemote stub = (ModelRemote) UnicastRemoteObject.exportObject(obj, 0);

        Registry registry = LocateRegistry.getRegistry();
        registry.rebind("Hello", stub);
    }
}
