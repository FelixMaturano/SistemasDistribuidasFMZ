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

public class BibliotecaDAO {

    public void insertar(String nombre, double tamanio) {

        String sql = "INSERT INTO biblioteca (nombre, tamanio) VALUES (?, ?)";

        try (Connection conn = ConexionBD.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, nombre);
            stmt.setDouble(2, tamanio);
            stmt.executeUpdate();

            System.out.println("Biblioteca guardada en BD");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
