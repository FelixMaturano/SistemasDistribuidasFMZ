/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.sis258.tresraya.rmi;

import java.net.MalformedURLException;
import java.rmi.AlreadyBoundException;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;

public class ServidorRaya{

    public static void main(String[] args) {

        try {
            //Naming.bind("Raya", servidor);
            Raya raya = new Raya();
            LocateRegistry.createRegistry(1099);
            Naming.bind("Raya", raya);
            System.out.println("Servidor listo...");
        } catch (RemoteException ex) {
            System.getLogger(ServidorRaya.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
        } catch (AlreadyBoundException ex) {
            System.getLogger(ServidorRaya.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
        } catch (MalformedURLException ex) {
            System.getLogger(ServidorRaya.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
        }
    }
}
