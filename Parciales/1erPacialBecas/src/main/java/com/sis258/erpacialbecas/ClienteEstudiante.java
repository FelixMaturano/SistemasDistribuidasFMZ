/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package com.sis258.erpacialbecas;
import java.rmi.Naming;
import java.util.Scanner;
/**
 *
 * @author Ruta Binar
 */
public class ClienteEstudiante {
    public static void main(String[] args) {
        try{
           IBeca servidorBeca = (IBeca) Naming.lookup("rmi://localhost/Beca");
           Scanner sc = new Scanner(System.in);
           
            System.out.println("---Solicitud de beca universitaria ---");
            System.out.println("Ingrece su CI: ");
            String ci = sc.nextLine();
            System.out.println("Ingrese su nombre");
            String nombre = sc.nextLine();
            System.out.println("Ingrese su apellido");
            String apellido = sc.nextLine();
            
            RespuestaBeca respuesta = servidorBeca.SolicitarBeca(ci, nombre, apellido);
            
            System.out.println("Resultado de la solicitud---");
            System.out.println("Aprobado: " + (respuesta.isAprobado()? "SI ": "NO"));
            System.out.println("Motivo" + respuesta.getMotivo());
            System.out.println("Promedio Academico: "+ respuesta.getPromedio());
        }catch (Exception e){
            System.out.println("Error en el cliente" + e.getMessage());
        }
    }
    
}
