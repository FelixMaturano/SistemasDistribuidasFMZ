/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.sis258.tresraya.rmi;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import javax.swing.JPanel;

/**
 *
 * @author Ruta Binar
 */
public class TableroPanel extends JPanel {

    private int[][] tablero;
    private String mensaje;

    public TableroPanel() {
        tablero = new int[3][3];
        mensaje = "Conectando.. ";
        setBackground(Color.WHITE);
    }

    public void actualizarTablero(int[][] tablero, String mensaje) {
        this.tablero = tablero;
        this.mensaje = mensaje;
        repaint();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        int ancho = getWidth();
        int alto = getHeight();
        int tamCelda = 120;
        int inicioX = (ancho - tamCelda * 3) / 2;
        int inicioY = (alto - tamCelda * 3) / 2;

        // dibujar lineas del tablero
        g2.setColor(Color.BLACK);
        g2.setStroke(new BasicStroke(3));
        for (int i = 1; i < 3; i++) {
            // lineas verticales
            g2.drawLine(inicioX + i * tamCelda, inicioY,
                    inicioX + i * tamCelda, inicioY + tamCelda * 3);
            // lineas horizontales
            g2.drawLine(inicioX, inicioY + i * tamCelda,
                    inicioX + tamCelda * 3, inicioY + i * tamCelda);
        }

        // dibujar X y O
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                int x = inicioX + j * tamCelda;
                int y = inicioY + i * tamCelda;
                int margen = 20;

                if (tablero[i][j] == 1) {
                    // X en rojo
                    g2.setColor(Color.RED);
                    g2.setStroke(new BasicStroke(5));
                    g2.drawLine(x + margen, y + margen,
                            x + tamCelda - margen, y + tamCelda - margen);
                    g2.drawLine(x + tamCelda - margen, y + margen,
                            x + margen, y + tamCelda - margen);
                } else if (tablero[i][j] == 2) {
                    // O en azul
                    g2.setColor(Color.BLUE);
                    g2.setStroke(new BasicStroke(5));
                    g2.drawOval(x + margen, y + margen,
                            tamCelda - margen * 2, tamCelda - margen * 2);
                }
            }
        }

        // dibujar mensaje abajo
        g2.setColor(Color.DARK_GRAY);
        g2.setFont(new Font("Arial", Font.BOLD, 18));
        int anchoMensaje = g2.getFontMetrics().stringWidth(mensaje);
        g2.drawString(mensaje, (ancho - anchoMensaje) / 2, inicioY + tamCelda * 3 + 40);
    }
}
