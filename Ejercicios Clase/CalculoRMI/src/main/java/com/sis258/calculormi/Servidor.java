/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.sis258.calculormi;

import java.net.MalformedURLException;
import java.rmi.AlreadyBoundException;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;


public class Servidor {

    public static void main(String[] args) {
        try {
            Calculo calculos = new Calculo();
            LocateRegistry.createRegistry(1099); //levantar el servidor de registro;
        
            Naming.bind("Calculo", calculos);
            System.out.println("Servidor listo, esperando clientes...");

        } catch (RemoteException ex) {
            System.getLogger(Servidor.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
        } catch (AlreadyBoundException ex) {
            System.getLogger(Servidor.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
        } catch (MalformedURLException ex) {
            System.getLogger(Servidor.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
        }
    }
}