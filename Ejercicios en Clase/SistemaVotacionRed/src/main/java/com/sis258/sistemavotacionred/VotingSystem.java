package com.sis258.sistemavotacionred;

import org.jgroups.JChannel;
import org.jgroups.Message;
import org.jgroups.ReceiverAdapter;
import org.jgroups.View;
import java.io.*;
import java.util.*;

/**
 * @author Ruta Binar
 */
public class VotingSystem extends ReceiverAdapter {
    
    private JChannel channel;
    private String nombreNodo;

    private String preguntaActual = null;
    private List<String> opciones = new ArrayList<>();
    private Map<String, Integer> conteoVotos = new HashMap<>();
    private boolean yaVote = false;

    public VotingSystem(String nombreNodo) {
        this.nombreNodo = nombreNodo;
    }

    @Override
    public void receive(Message msg) {
        String texto = (String) msg.getObject();

        if (texto.startsWith("PREGUNTA:")) {
            String contenido = texto.substring(9);
            String[] partes = contenido.split("\\|");

            preguntaActual = partes[0];
            opciones.clear();
            conteoVotos.clear();
            yaVote = false;

            for (int i = 1; i < partes.length; i++) {
                opciones.add(partes[i]);
                conteoVotos.put(partes[i], 0);
            }

            System.out.println("\n========== NUEVA VOTACION ==========");
            System.out.println("Pregunta: " + preguntaActual);
            for (int i = 0; i < opciones.size(); i++) {
                System.out.println("  " + (i + 1) + ". " + opciones.get(i));
            }
            System.out.println("Escribe el numero de tu opcion para votar:");
            System.out.print("> ");

        } else if (texto.startsWith("VOTO:")) {
            String opcionVotada = texto.substring(5);
            if (conteoVotos.containsKey(opcionVotada)) {
                conteoVotos.put(opcionVotada, conteoVotos.get(opcionVotada) + 1);
                System.out.println("\nVoto recibido para: " + opcionVotada);
                mostrarResultados();
            }
        }
    }

    @Override
    public void viewAccepted(View view) {
        System.out.println("Miembros del grupo: " + view.getMembers());
    }

    private void mostrarResultados() {
        System.out.println("--- Resultados actuales ---");
        for (Map.Entry<String, Integer> entrada : conteoVotos.entrySet()) {
            System.out.println("  " + entrada.getKey() + ": " + entrada.getValue() + " voto(s)");
        }
        System.out.println("---------------------------");
        System.out.print("> ");
    }

    public void start() throws Exception {
        channel = new JChannel();
        channel.setReceiver(this);
        channel.connect("VotingCluster");
        eventLoop();
        channel.close();
    }

    private void eventLoop() throws Exception {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Comandos disponibles:");
        System.out.println("  'iniciar' proponer una nueva votacion");
        System.out.println("  numero   votar por una opcion (cuando haya votacion activa)");
        System.out.println("  'salir'  desconectarse");

        while (true) {
            System.out.print("> ");
            String linea = in.readLine();
            if (linea == null || linea.equalsIgnoreCase("salir")) break;
            linea = linea.trim();

            if (linea.equalsIgnoreCase("iniciar")) {
                System.out.print("Escribe la pregunta: ");
                String pregunta = in.readLine();
                System.out.print("Cuantas opciones? ");
                int n = Integer.parseInt(in.readLine().trim());

                StringBuilder sb = new StringBuilder("PREGUNTA:" + pregunta);
                for (int i = 0; i < n; i++) {
                    System.out.print("Opcion " + (i + 1) + ": ");
                    sb.append("|").append(in.readLine());
                }

                Message msg = new Message(null, sb.toString()) {};
                channel.send(msg);

            } else if (preguntaActual != null && !yaVote) {
                try {
                    int idx = Integer.parseInt(linea) - 1;
                    if (idx >= 0 && idx < opciones.size()) {
                        String opcionElegida = opciones.get(idx);
                        Message msg = new Message(null, "VOTO:" + opcionElegida) {};
                        channel.send(msg);
                        yaVote = true;
                        System.out.println("Voto enviado: " + opcionElegida);
                    } else {
                        System.out.println("Numero fuera de rango. Opciones: 1 a " + opciones.size());
                    }
                } catch (NumberFormatException e) {
                    System.out.println("Escribe 'iniciar', un numero para votar, o 'salir'.");
                }
            } else if (yaVote) {
                System.out.println("Ya votaste en esta ronda.");
            } else {
                System.out.println("No hay votacion activa. Escribe 'iniciar' para proponer una.");
            }
        }
    }
}