/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.sis258.ejercicio_biblioteca;

/**
 *
 * @author Felix
 */
import java.util.ArrayList;
import java.util.List;

public abstract class Armario {

    protected String codigo;
    protected List<Publicacion> publicaciones;

    public Armario(String codigo) {
        this.codigo = codigo;
        this.publicaciones = new ArrayList<>();
    }

    public void agregarPublicacion(Publicacion p) {
        publicaciones.add(p);
    }

    public abstract String tipoArmario();

    public void listarPublicaciones() {
        for (Publicacion p : publicaciones) {
            System.out.println(p.mostrarDetalle());
        }
    }
}
