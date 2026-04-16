/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package com.sis258.v2cuentajuezrmisockets;

import java.rmi.Naming;
import java.util.ArrayList;
public class Juez {
    public static void main(String[] args) throws Exception {
        try{
        IJusticia j = (IJusticia) Naming.lookup("rmi://localhost/Justicia");
        RespuestaCuenta r = j.consultar("123");
        System.out.println(r.getMensaje());
        for (Cuenta c : r.getCuentas()) {
            System.out.println(c);
        }
        double total = j.totalSaldo("123");
        System.out.println("TOTAL: " + total);
        }catch (Exception e) {
            System.out.println("Error de conexion en el Juez: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
