/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.sis258.ejercicio_biblioteca;

/**
 *
 * @author Felix
 */
import com.sis258.ejercicio_biblioteca.ConexionBD;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class PublicacionDAO {

    public void insertar(String nombre, String tipo, String detalle, int armarioId) {

        String sql = "INSERT INTO publicacion (nombre, tipo, detalle, armario_id) VALUES (?, ?, ?, ?)";

        try (Connection conn = ConexionBD.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, nombre);
            stmt.setString(2, tipo);
            stmt.setString(3, detalle);
            stmt.setInt(4, armarioId);
            stmt.executeUpdate();

            System.out.println("Publicación guardada en BD");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
