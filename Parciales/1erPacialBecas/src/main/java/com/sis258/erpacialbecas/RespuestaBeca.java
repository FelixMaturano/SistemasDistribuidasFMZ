/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.sis258.erpacialbecas;
import java.io.Serializable;
import java.util.ArrayList;
/**
 *
 * @author PC
 */
public class RespuestaBeca implements Serializable{
    private boolean aprobado;
    private String motivo;
    private double promedio;
    private ArrayList<Nota> notas;

    public RespuestaBeca() {
        notas = new ArrayList<>();
    }

    public RespuestaBeca(boolean aprobado, String motivo, double promedio, ArrayList<Nota> notas) {
        this.aprobado = aprobado;
        this.motivo = motivo;
        this.promedio = promedio;
        this.notas = notas;
    }
    

    public boolean isAprobado() {
        return aprobado;
    }

    public void setAprobado(boolean aprobado) {
        this.aprobado = aprobado;
    }

    public String getMotivo() {
        return motivo;
    }

    public void setMotivo(String motivo) {
        this.motivo = motivo;
    }

    public double getPromedio() {
        return promedio;
    }

    public void setPromedio(double promedio) {
        this.promedio = promedio;
    }
    
}
