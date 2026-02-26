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

public class ArmarioDAO {

    public void insertar(String codigo, String tipo, int bibliotecaId) {

        String sql = "INSERT INTO armario (codigo, tipo, biblioteca_id) VALUES (?, ?, ?)";

        try (Connection conn = ConexionBD.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, codigo);
            stmt.setString(2, tipo);
            stmt.setInt(3, bibliotecaId);
            stmt.executeUpdate();

            System.out.println("Armario guardado en BD");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
