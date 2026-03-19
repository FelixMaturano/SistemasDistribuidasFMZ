/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.sis258.tresraya.rmi;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;

public class ClienteRaya extends JFrame {

    private IRaya raya;
    private int miJugador;
    private TableroPanel panel;
    private boolean miTurno;

    public ClienteRaya() {
        setTitle("Tres en Raya");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(500, 550);
        setLocationRelativeTo(null);
        setResizable(false);

        panel = new TableroPanel();
        panel.setPreferredSize(new Dimension(500, 550));
        add(panel, BorderLayout.CENTER);

        // clic en el tablero
        panel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (!miTurno) return;
                manejarClic(e.getX(), e.getY());
            }
        });
    }

    public void iniciar() {
        try {
            raya = (IRaya) Naming.lookup("rmi://localhost/Raya");
            miJugador = raya.conectar();

            if (miJugador == 0) {
                JOptionPane.showMessageDialog(this, "Juego lleno.");
                System.exit(0);
            }

            setTitle("Tres en Raya - Jugador " + miJugador);
            setVisible(true);

            // hilo separado para no bloquear la ventana
            new Thread(() -> bucleJuego()).start();

        } catch (NotBoundException | MalformedURLException | RemoteException ex) {
            JOptionPane.showMessageDialog(this, "Error al conectar con el servidor.");
            System.exit(0);
        }
    }

    private void bucleJuego() {
        try {
            // esperar al otro jugador
            panel.actualizarTablero(raya.getTablero(), "Esperando al otro jugador...");
            while (raya.getTurno() == 1 && miJugador == 2) {
                Thread.sleep(1000);
            }

            while (true) {
                int estado = raya.getEstado();
                if (estado != 0) {
                    panel.actualizarTablero(raya.getTablero(), obtenerMensajeFinal(estado));
                    break;
                }

                if (raya.getTurno() == miJugador) {
                    miTurno = true;
                    panel.actualizarTablero(raya.getTablero(), "Tu turno jugador " + miJugador);
                } else {
                    miTurno = false;
                    panel.actualizarTablero(raya.getTablero(), "Esperando jugada del otro jugador...");
                }

                Thread.sleep(500);
            }

        } catch (RemoteException | InterruptedException ex) {
            System.getLogger(ClienteRaya.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
        }
    }

    private void manejarClic(int x, int y) {
        int tamCelda = 120;
        int inicioX = (panel.getWidth()  - tamCelda * 3) / 2;
        int inicioY = (panel.getHeight() - tamCelda * 3) / 2;

        // verificar que el clic fue dentro del tablero
        if (x < inicioX || x > inicioX + tamCelda * 3) return;
        if (y < inicioY || y > inicioY + tamCelda * 3) return;

        int columna = (x - inicioX) / tamCelda;
        int fila    = (y - inicioY) / tamCelda;

        try {
            boolean valida = raya.hacerJugada(fila, columna, miJugador);
            if (!valida) {
                panel.actualizarTablero(raya.getTablero(), "Casilla ocupada, intenta otra.");
            } else {
                miTurno = false;
            }
        } catch (RemoteException ex) {
            System.getLogger(ClienteRaya.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
        }
    }

    private String obtenerMensajeFinal(int estado) {
        if (estado == 3)            return "Empate.";
        if (estado == miJugador)    return "Ganaste jugador " + miJugador + "!";
        return "Gano el jugador " + estado + ". Perdiste.";
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new ClienteRaya().iniciar();
        });
    }
}