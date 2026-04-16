/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.sis258.v2cuentajuezrmisockets;

import java.io.Serializable;
import java.util.ArrayList;

/**
 *
 * @author Ruta Binar
 */
public class RespuestaCuenta implements Serializable {

    private boolean error;
    private String mensaje;
    private ArrayList<Cuenta> cuentas;

    public RespuestaCuenta() {
        cuentas = new ArrayList<>();
    }

    public boolean isError() {
        return error;
    }

    public void setError(boolean error) {
        this.error = error;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    public ArrayList<Cuenta> getCuentas() {
        return cuentas;
    }

    public void setCuentas(ArrayList<Cuenta> cuentas) {
        this.cuentas = cuentas;
    }
}
