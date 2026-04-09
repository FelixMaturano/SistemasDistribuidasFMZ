package com.sis258.sistemajuezrmisockets;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;
public interface IJusticia extends Remote{
    ArrayList<Cuenta> consultar(String ci)throws RemoteException;
    boolean congelar(Cuenta c, double monto)throws RemoteException;
}
