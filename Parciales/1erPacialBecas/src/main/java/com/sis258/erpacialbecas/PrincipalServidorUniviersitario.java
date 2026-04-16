/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.sis258.erpacialbecas;

import java.net.MalformedURLException;
import java.rmi.AlreadyBoundException;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;

/**
 *
 * @author Ruta Binar
 */
public class PrincipalServidorUniviersitario {

    public static void main(String[] args) {
        try {
            ServidorUniversitario ServidorO = new ServidorUniversitario();
            LocateRegistry.createRegistry(1099);
            Naming.bind("Beca", ServidorO);
            System.out.println("Servidor Universitario RMI iniciado. Esperando solicitudes...");
        } catch (RemoteException ex) {
            System.getLogger(PrincipalServidorUniviersitario.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
        } catch (AlreadyBoundException ex) {
            System.getLogger(PrincipalServidorUniviersitario.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
        } catch (MalformedURLException ex) {
            System.getLogger(PrincipalServidorUniviersitario.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
        }
    }
}
