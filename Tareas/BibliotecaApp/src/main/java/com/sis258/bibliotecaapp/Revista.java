/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.sis258.bibliotecaapp;

/**
 *
 * @author Felix
 */
public class Revista extends Publicacion {

    private String mes;
    private int anio;
    private String tipo; // tecnica, moda, variedades

    public Revista(int id, String nombre, String mes, int anio, String tipo) {
        super(id, nombre);
        this.mes = mes;
        this.anio = anio;
        this.tipo = tipo;
    }

    public String getMes() { return mes; }
    public int getAnio() { return anio; }
    public String getTipo() { return tipo; }
}
