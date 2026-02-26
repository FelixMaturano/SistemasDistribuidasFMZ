/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.sis258.bibliotecaapp;
import java.util.ArrayList;
import java.util.List;
/**
 *
 * @author Felix
 */
import java.sql.*;

public class PublicacionDAO {

    public void insertarLibro(Libro libro, int armarioId) {

        String sql = "INSERT INTO publicacion(tipo,nombre,autor,editorial,anio,armario_id) VALUES (?,?,?,?,?,?)";

        try (Connection con = Conexion.conectar();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, "LIBRO");
            ps.setString(2, libro.getNombre());
            ps.setString(3, libro.getAutor());
            ps.setString(4, libro.getEditorial());
            ps.setInt(5, libro.getAnio());
            ps.setInt(6, armarioId);

            ps.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void insertarRevista(Revista revista, int armarioId) {

        String sql = "INSERT INTO publicacion(tipo,nombre,mes,anio,tipo_revista,armario_id) VALUES (?,?,?,?,?,?)";

        try (Connection con = Conexion.conectar();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, "REVISTA");
            ps.setString(2, revista.getNombre());
            ps.setString(3, revista.getMes());
            ps.setInt(4, revista.getAnio());
            ps.setString(5, revista.getTipo());
            ps.setInt(6, armarioId);

            ps.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void insertarPeriodico(Periodico periodico, int armarioId) {

        String sql = "INSERT INTO publicacion(tipo,nombre,fecha,suplementos,armario_id) VALUES (?,?,?,?,?)";

        try (Connection con = Conexion.conectar();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, "PERIODICO");
            ps.setString(2, periodico.getNombre());
            ps.setDate(3, Date.valueOf(periodico.getFecha()));
            ps.setString(4, periodico.getSuplementos());
            ps.setInt(5, armarioId);

            ps.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public List<String> listarPorArmario(int armarioId) {

    List<String> lista = new ArrayList<>();
    String sql = "SELECT * FROM publicacion WHERE armario_id=?";

    try (Connection con = Conexion.conectar();
         PreparedStatement ps = con.prepareStatement(sql)) {

        ps.setInt(1, armarioId);
        ResultSet rs = ps.executeQuery();

        while (rs.next()) {
            String tipo = rs.getString("tipo");
            String nombre = rs.getString("nombre");

            lista.add(tipo + " - " + nombre);
        }

    } catch (Exception e) {
        e.printStackTrace();
    }

    return lista;
}
    public void eliminar(int id) {
    String sql = "DELETE FROM publicacion WHERE id=?";

    try (Connection con = Conexion.conectar();
         PreparedStatement ps = con.prepareStatement(sql)) {

        ps.setInt(1, id);
        ps.executeUpdate();

    } catch (Exception e) {
        e.printStackTrace();
    }
}
}