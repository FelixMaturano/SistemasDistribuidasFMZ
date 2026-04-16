/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sistemabecas;

import java.rmi.server.UnicastRemoteObject;
import java.rmi.RemoteException;
import java.net.*;
import java.io.*;
import java.util.ArrayList;

/**
 *
 * @author PC
 */
public class ServidorUniversitario extends UnicastRemoteObject implements IBeca {

    public ServidorUniversitario() throws RemoteException {
        super();
    }

    @Override
    public RespuestaBeca SolicitarBeca(String ci, String nombres, String apellidos) throws RemoteException {
        RespuestaBeca respuesta = new RespuestaBeca();
        respuesta.setAprobado(false);
        try {
            boolean existeEnSegip = consultarSegipTCP(ci);
            boolean sinDeudas = consultarFinancieroUDP(ci);
            
            if(existeEnSegip && sinDeudas){
                ArrayList<Nota> notas = new ArrayList<>();
                
                notas.add(new Nota("SIS-258",80));
                notas.add(new Nota("SIS-350",80));
                
                for(Nota n: notas){
                    double promedio = n.getCalificacion();
                    if(promedio > 70){
                        respuesta = new RespuestaBeca(true, "Aprobado. identidad verificada buen promedio.",promedio,notas );
                    }else{
                        respuesta = new RespuestaBeca(false, "Rechazado. Su promedio no alcanza el mínimo requerido.", promedio, notas);
                    }
                }
            }else{
                String motivo = "Rechazado. ";
                if(!existeEnSegip) motivo += "CI no encontrado en Segip.";
                if(!sinDeudas) motivo += " Presenta deudas pendientes";
                respuesta.setMotivo(motivo);
            }
            
        }catch (Exception ex) {
            respuesta.setMotivo("Error interno del servidor "+ex.getMessage());
        }
        return respuesta;
    }
    
    private boolean consultarSegipTCP(String ci){
        int port = 500;
        try{
            Socket client = new Socket("localhost", 500);
            PrintStream toServer = new PrintStream(client.getOutputStream());
            BufferedReader fromServer = new BufferedReader(new InputStreamReader(client.getInputStream()));
            
            toServer.println(ci);
            String result = fromServer.readLine();
            if(result != null && result.contains("si existe"))return true;
            
        }catch(IOException e){
            System.out.println("Error TCP Segip: "+ e.getMessage());
        }
        return false;
    }
    
    private boolean consultarFinancieroUDP(String ci){
        return false;
    }
    public static String procesar(String cadena){
        String[] comando = cadena.split(":");
        String datos = comando[1];
        String[] datos_personales = datos.split("-");
        if(datos_personales[0].equals("1234567")){
        return "resultado encontrado";
        }else{
            return "no encontrado";
        }
 
    }
    
}
