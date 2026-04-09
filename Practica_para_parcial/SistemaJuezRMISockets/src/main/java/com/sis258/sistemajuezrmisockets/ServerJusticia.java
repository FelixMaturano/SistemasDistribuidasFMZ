/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.sis258.sistemajuezrmisockets;
import java.net.MalformedURLException;
import java.rmi.AlreadyBoundException;
import java.rmi.RemoteException;
import java.rmi.Naming;
import java.rmi.registry.LocateRegistry;
/**
 *
 * @author Ruta Binar
 */
public class ServerJusticia {
    public static void main(String[] args) throws Exception{
        try{
        LocateRegistry.createRegistry(1099);
        Naming.bind("Justicia", new Justicia());
        System.out.println("Servidor RMI listo...");
        }catch (RemoteException ex) {
                System.getLogger(ServerJusticia.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
            } catch (AlreadyBoundException ex) {
                System.getLogger(ServerJusticia.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
            } catch (MalformedURLException ex) {
                System.getLogger(ServerJusticia.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
            }
    }
}
