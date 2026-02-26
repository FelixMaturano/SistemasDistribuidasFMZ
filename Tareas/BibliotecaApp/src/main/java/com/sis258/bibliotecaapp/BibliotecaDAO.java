/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.sis258.bibliotecaapp;


/**
 *
 * @author Felix
 */
import java.sql.*;
import java.util.*;

public class BibliotecaDAO {

public void insertar(Biblioteca b) {

    String sql = "INSERT INTO biblioteca(nombre, tamanio) VALUES (?,?)";

    try (Connection con = Conexion.conectar();
         PreparedStatement ps = con.prepareStatement(sql)) {

        ps.setString(1, b.getNombre());
        ps.setDouble(2, b.getTamanio());

        int filas = ps.executeUpdate();

        System.out.println("Filas afectadas: " + filas);

    } catch (Exception e) {
        System.out.println("ERROR REAL:");
        e.printStackTrace();
    }
}
    
    public List<Biblioteca> listar() {
        List<Biblioteca> lista = new ArrayList<>();
        String sql = "SELECT * FROM biblioteca";

        try (Connection con = Conexion.conectar();
             Statement st = con.createStatement();
             ResultSet rs = st.executeQuery(sql)) {

            while (rs.next()) {
                lista.add(new Biblioteca(
                        rs.getInt("id"),
                        rs.getString("nombre"),
                        rs.getDouble("tamanio")
                ));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return lista;
    }

    public void eliminar(int id) {
        String sql = "DELETE FROM biblioteca WHERE id=?";

        try (Connection con = Conexion.conectar();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, id);
            ps.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}