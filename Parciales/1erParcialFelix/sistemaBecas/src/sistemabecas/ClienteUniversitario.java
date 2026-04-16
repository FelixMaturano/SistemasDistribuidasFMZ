/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sistemabecas;
import java.rmi.Naming;

/**
 *
 * @author PC
 */
public class ClienteUniversitario {
    public static void main(String[] args) throws Exception{
        try{
            IBeca b = (IBeca)Naming.lookup("rmi://localhost/");
            //RespuestaBeca r 
        }catch(Exception e){
            System.out.println("Error en la conexion en el cliente uni..: "+e.getMessage());
            e.printStackTrace();
        }
    }
}
