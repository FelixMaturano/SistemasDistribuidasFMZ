/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.sis258.bibliotecaapp;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
/**
 *
 * @author Felix
 */
public class ArmarioDAO {
        public void insertar(Armario armario, int bibliotecaId) {

        String sql = "INSERT INTO armario(codigo, material, biblioteca_id) VALUES (?,?,?)";

        try (Connection con = Conexion.conectar();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, armario.getCodigo());
            ps.setString(2, armario.getMaterial());
            ps.setInt(3, bibliotecaId);
            ps.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
public List<Armario> listarPorBiblioteca(int bibliotecaId) {

    List<Armario> lista = new ArrayList<>();
    String sql = "SELECT * FROM armario WHERE biblioteca_id=?";

    try (Connection con = Conexion.conectar();
         PreparedStatement ps = con.prepareStatement(sql)) {

        ps.setInt(1, bibliotecaId);
        ResultSet rs = ps.executeQuery();

        while (rs.next()) {
            lista.add(new Armario(
                    rs.getInt("id"),
                    rs.getString("codigo"),
                    rs.getString("material")
            ));
        }

    } catch (Exception e) {
        e.printStackTrace();
    }

    return lista;
}
public void eliminar(int id) {
    String sql = "DELETE FROM armario WHERE id=?";

    try (Connection con = Conexion.conectar();
         PreparedStatement ps = con.prepareStatement(sql)) {

        ps.setInt(1, id);
        ps.executeUpdate();

    } catch (Exception e) {
        e.printStackTrace();
    }
}
}