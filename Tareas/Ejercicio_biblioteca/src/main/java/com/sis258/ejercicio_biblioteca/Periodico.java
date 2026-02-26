/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.sis258.ejercicio_biblioteca;

/**
 *
 * @author Felix
 */
import java.util.List;

public class Periodico extends Publicacion {

    private String fecha;
    private List<String> suplementos;

    public Periodico(String nombre, String fecha, List<String> suplementos) {
        super(nombre);
        this.fecha = fecha;
        this.suplementos = suplementos;
    }

    @Override
    public String mostrarDetalle() {
        return "Periodico: " + nombre + " - " + fecha + " - Suplementos: " + suplementos;
    }
}