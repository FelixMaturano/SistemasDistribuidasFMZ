/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.sis258.sockets_rmi_cuenta;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

/**
 *
 * @author Ruta Binar
 */
public class Juez {

    public static void main(String[] args) {
        try {
            IJusticia justicia;
            IJusticia servidorJusticia = (IJusticia) Naming.lookup("rmi://localhost/Justicia");
        
        } catch (NotBoundException ex) {
            System.getLogger(Juez.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
        } catch (MalformedURLException ex) {
            System.getLogger(Juez.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
        } catch (RemoteException ex) {
            System.getLogger(Juez.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
        }
    }
}
