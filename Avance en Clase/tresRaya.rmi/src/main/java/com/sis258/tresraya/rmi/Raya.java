/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.sis258.tresraya.rmi;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

/**
 *
 * @author Ruta Binar
 */
public class Raya extends UnicastRemoteObject implements IRaya {

    private int[][] tablero;
    private int jugadoresConectados;
    private int turno;
    private int estado;

    public Raya() throws RemoteException {
        super();
        tablero = new int[3][3];
        jugadoresConectados = 0;
        turno = 1;
        estado = 0;
    }

    @Override
    public int conectar() throws RemoteException {
        if (jugadoresConectados >= 2) {
            return 0;
        }
        jugadoresConectados++;
        return jugadoresConectados;
    }

    @Override
    public int getTurno() throws RemoteException {
        return turno;
    }

    @Override
    public int getEstado() throws RemoteException {
        return estado;
    }

    // 3. ¡Este es el método que NetBeans te está pidiendo!
    @Override
    public int[][] getTablero() throws RemoteException {
        return tablero;
    }

    @Override
    public boolean hacerJugada(int fila, int columna, int jugador) throws RemoteException {
        if (tablero[fila][columna] != 0) {
            return false;
        }
        tablero[fila][columna] = jugador;
        if (hayGanador(jugador)) {
            estado = jugador;
        }else if(tableroLleno()){
            estado = 3;
        }else{
        turno = (jugador == 1) ? 2 : 1;
        
        }
        return true;
    }

    private boolean hayGanador(int jugador) {
        for (int i = 0; i < 3; i++) {
            if (tablero[i][0] == jugador && tablero[i][1] == jugador && tablero[i][2] == jugador) {
                return true;
            }
            if (tablero[0][i] == jugador && tablero[1][i] == jugador && tablero[2][i] == jugador) {
                return true;
            }
        }
        if (tablero[0][0] == jugador && tablero[1][1] == jugador && tablero[2][2] == jugador) {
            return true;
        }
        if (tablero[0][2] == jugador && tablero[1][1] == jugador && tablero[2][0] == jugador) {
            return true;
        }
        return false;
    }
    
    private boolean tableroLleno(){
        for (int i = 0; i < 3; i++) 
            for (int j = 0; j < 3; j++) 
                if(tablero[i][j]==0 )return false;
        return true;
            
            
        
    }
}
