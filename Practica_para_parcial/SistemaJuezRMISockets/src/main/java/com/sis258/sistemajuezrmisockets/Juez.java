/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.sis258.sistemajuezrmisockets;

import java.rmi.Naming;
import java.util.ArrayList;

/**
 *
 * @author Ruta Binar
 */
public class Juez {

    public static void main(String[] args) throws Exception {
        // Lo convierte (cast> al tipo IJusticia para poder invocar sus meto
        IJusticia justicia = (IJusticia) Naming.lookup("rmi://localhost/Justicia");

        // Invoca al método remoto consultar() pasando la cédula "123"
        // El servidor devuelve un ArrayList con las cuentas encontradas
        ArrayList<Cuenta> cuentas = justicia.consultar("123");

        System.out.println("RESULTADO:");
        // Recorre la lista de cuentas usando un for-each
        for (Cuenta c : cuentas) {
            // Imprime cada cuenta (usa el método toString() de Cuenta)
            System.out.println(c);
        }
        //PRUEBA DEL METODO CONGELAR
                // Verifica si la lista no está vacía (hay al menos una cuenta)
        if (!cuentas.isEmpty()) {
            // Toma la primera cuenta de la lista
            Cuenta c = cuentas.get(0);

            // Invoca al método remoto congelar() con la cuenta y monto 1000
            // Retorna true si el saldo era suficiente, false si no
            boolean ok = justicia.congelar(c, 1000);

            // Muestra si se pudo congelar
            System.out.println("Congelado: " + ok);
            // Muestra el nuevo saldo de la cuenta (modificado en el servidor)
            System.out.println("Nuevo saldo: " + c.getSaldo());
        }
    }

}
