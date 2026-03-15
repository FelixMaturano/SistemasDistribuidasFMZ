/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.sis258.tresraya.rmi;

import java.rmi.Remote;
import java.rmi.RemoteException;
/**
 *
 * @author Ruta Binar
 */
public interface IRaya extends Remote{
    public int conectar() throws RemoteException;
    public int[][] getTablero() throws RemoteException;
    public boolean hacerJugada(int fila, int columna, int jugador) throws RemoteException;
    public int getTurno()throws RemoteException;
    public int getEstado()throws RemoteException;
}
