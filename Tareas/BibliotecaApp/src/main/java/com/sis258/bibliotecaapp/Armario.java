/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.sis258.bibliotecaapp;

/**
 *
 * @author Felix
 */
public class Armario {
    private int id;
    private String codigo;
    private String material;
    
    public Armario(int id, String codigo, String material){
        this.id = id;
        this.codigo = codigo;
        this.material = material;
    }

    public int getId() {
        return id;
    }

    public String getCodigo() {
        return codigo;
    }

    public String getMaterial() {
        return material;
    }
    
    
}