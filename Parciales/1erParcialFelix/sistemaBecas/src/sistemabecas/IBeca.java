/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package sistemabecas;
import java.rmi.Remote;
import java.rmi.RemoteException;
/**
 *
 * @author PC
 */
public interface IBeca extends Remote{
    RespuestaBeca SolicitarBeca(String ci,String nombres, String apellidos)throws RemoteException;
}
