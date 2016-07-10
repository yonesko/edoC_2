package main;

import common.Model;
import common.ModelRemote;

import java.net.MalformedURLException;
import java.rmi.AlreadyBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

public class Main {

    public static void main(String[] args) throws RemoteException, MalformedURLException, AlreadyBoundException {

        Model obj = new Model();

        ModelRemote stub = (ModelRemote) UnicastRemoteObject.exportObject(obj, 0);

        Registry registry = LocateRegistry.getRegistry();
        registry.rebind("Hello", stub);

    }
}