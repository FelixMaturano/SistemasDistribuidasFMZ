/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.sis258.practicasocketsrmi;

import java.util.*;

import java.io.Serializable;

/**
 *
 * @author Ruta Binar
 */
public class RespuestaCuenta implements Serializable {

    private boolean error;
    private String mensaje;
    private List<Cuenta> listaCuentas;

    public RespuestaCuenta() {
        this.listaCuentas = new ArrayList<>();
    }

    public RespuestaCuenta(boolean error, String mensaje, List<Cuenta> listaCuentas) {
        this.error = error;
        this.mensaje = mensaje;
        this.listaCuentas = listaCuentas;
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

    public List<Cuenta> getListaCuentas() {
        return listaCuentas;
    }

    public void setListaCuentas(List<Cuenta> listaCuentas) {
        this.listaCuentas = listaCuentas;
    }

}
