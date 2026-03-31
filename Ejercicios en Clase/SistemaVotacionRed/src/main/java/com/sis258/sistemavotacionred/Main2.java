/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.sis258.sistemavotacionred;

/**
 *
 * @author Ruta Binar
 */

public class Main {
    public static void main(String[] args) {
        try {
            System.setProperty("java.net.preferIPv4Stack", "true");
            new StateSyncCluster().start();
        } catch (Exception ex) {
            System.getLogger(Main.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
        }
    }
}