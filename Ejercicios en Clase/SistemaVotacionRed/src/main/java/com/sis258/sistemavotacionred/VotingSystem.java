package com.sis258.sistemavotacionred;

import org.jgroups.JChannel;
import org.jgroups.Message;
import org.jgroups.ReceiverAdapter;
import org.jgroups.View;

import java.io.*;
import java.util.List;
import java.util.LinkedList;
import java.util.Map;
import java.util.HashMap;

public class VotingSystem extends ReceiverAdapter {

    JChannel channel; // Canal para el grupo de comunicación
    private String user_name; // atributo para el usuario
    
    // Variables de estado para la votacion
    private String current_question = null;
    final List<String> options = new LinkedList<>(); 
    final Map<String, Integer> vote_count = new HashMap<>();
    private boolean has_voted = false;

    public VotingSystem(String user_name) {
        this.user_name = user_name;
    }

    public void viewAccepted(View new_view) {
        System.out.println("Vista del grupo actualizada: " + new_view);
    }

    public void receive(Message msg) {
        String line = (String) msg.getObject();

        if (line.startsWith("PREGUNTA:")) {
            String content = line.substring(9);
            String[] parts = content.split("\\|");

            current_question = parts[0];
            options.clear();
            vote_count.clear();
            has_voted = false;

            for (int i = 1; i < parts.length; i++) {
                options.add(parts[i]);
                vote_count.put(parts[i], 0);
            }

            System.out.println("\n========== NUEVA VOTACION ==========");
            System.out.println("Pregunta: " + current_question);
            for (int i = 0; i < options.size(); i++) {
                System.out.println("  " + (i + 1) + ". " + options.get(i));
            }
            System.out.println("Escribe el numero de tu opcion para votar:");
            System.out.print("> ");
            System.out.flush();

        } else if (line.startsWith("VOTO:")) {
            String voted_option = line.substring(5);
            if (vote_count.containsKey(voted_option)) {
                vote_count.put(voted_option, vote_count.get(voted_option) + 1);
                System.out.println("\nVoto recibido para: " + voted_option);
                showResults();
            }
        }
    }

    private void showResults() {
        System.out.println("--- Resultados actuales ---");
        for (Map.Entry<String, Integer> entry : vote_count.entrySet()) {
            System.out.println("  " + entry.getKey() + ": " + entry.getValue() + " voto(s)");
        }
        System.out.println("---------------------------");
        System.out.print("> ");
        System.out.flush();
    }

    public void start() throws Exception {
        // Crear el canal y conectar al grupo
        channel = new JChannel();  // Utiliza UDP por defecto
        channel.setReceiver(this); // El objeto que recibira los mensajes
        channel.connect("VotingCluster");  // Conecta al grupo VotingCluster
        channel.getState(null, 10000); // Opcional, para obtener estado compartido si lo hay
        eventLoop(); // Comienza a leer y enviar mensajes
        channel.close(); // Cierra el canal cuando se termine
    }

    private void eventLoop() {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Comandos: 'iniciar' (proponer), 'numero' (votar), 'quit' o 'exit' (salir)");
        
        while (true) {
            try {
                System.out.print("> ");
                System.out.flush();
                String line = in.readLine().toLowerCase();
                
                if (line.startsWith("quit") || line.startsWith("exit")) {
                    break; // finaliza el bucle
                }

                if (line.startsWith("iniciar")) {
                    
                    // Solo el primer nodo puede iniciar (Coordinador)
                    boolean is_coordinator = channel.getView().getMembers().get(0).equals(channel.getAddress());
                    if (!is_coordinator) {
                        System.out.println("Solo el creador del grupo puede iniciar la votacion.");
                        continue;
                    }

                    System.out.print("Escribe la pregunta: ");
                    String question = in.readLine();
                    System.out.print("Cuantas opciones? ");
                    int n = Integer.parseInt(in.readLine().trim());

                    StringBuilder sb = new StringBuilder("PREGUNTA:" + question);
                    for (int i = 0; i < n; i++) {
                        System.out.print("Opcion " + (i + 1) + ": ");
                        sb.append("|").append(in.readLine());
                    }

                    Message msg = new Message(null, sb.toString()) {}; // crea mensaje
                    channel.send(msg);  // envia al grupo

                } else if (current_question != null && !has_voted) {
                    int idx = Integer.parseInt(line) - 1;
                    if (idx >= 0 && idx < options.size()) {
                        String chosen_option = options.get(idx);
                        Message msg = new Message(null, "VOTO:" + chosen_option) {}; // crea mensaje
                        channel.send(msg);  // envia al grupo
                        has_voted = true;
                        System.out.println("Voto enviado: " + chosen_option);
                    } else {
                        System.out.println("Numero fuera de rango.");
                    }
                } else if (has_voted) {
                    System.out.println("Ya votaste en esta ronda.");
                } else {
                    System.out.println("No hay votacion activa.");
                }

            } catch (Exception e) {
                // El catch vacio para ignorar errores de formato tal como en SimpleChat
            }
        }
    }
}