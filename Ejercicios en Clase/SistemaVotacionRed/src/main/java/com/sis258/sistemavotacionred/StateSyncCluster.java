/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.sis258.sistemavotacionred;

import org.jgroups.JChannel;
import org.jgroups.Message;
import org.jgroups.ReceiverAdapter;
import org.jgroups.View;
import org.jgroups.util.Util;
import java.io.*;
/**
 *
 * @author Ruta Binar
 */
public class StateSyncCluster extends ReceiverAdapter {

    private JChannel channel;
    private int state = 0; // Estado compartido: contador que inicia en 0

    @Override
    public void receive(Message msg) {
        // JGroups nos entrega el mensaje a todos (incluyendo al que lo envió).
        // Al recibirlo, todos sumamos 1 a nuestro estado local de forma sincronizada.
        state++;
        System.out.println("Estado actualizado: " + state);
    }

    @Override
    public void viewAccepted(View view) {
        System.out.println("Miembros del grupo: " + view.getMembers());
    }

    // El nodo existente serializa su contador para enviárselo al nodo nuevo
    @Override
    public void getState(OutputStream output) throws Exception {
        synchronized (this) {
            Util.objectToStream(state, new DataOutputStream(output));
        }
    }

    // El nodo nuevo recibe y deserializa el contador del grupo
    @Override
    public void setState(InputStream input) throws Exception {
        int estadoRecibido = (int) Util.objectFromStream(new DataInputStream(input));
        synchronized (this) {
            state = estadoRecibido;
        }
        System.out.println("Estado sincronizado al unirse: " + state);
    }

    public void start() throws Exception {
        channel = new JChannel();
        channel.setReceiver(this);
        channel.connect("StateSyncCluster");
        channel.getState(null, 10000); // Solicita el estado al grupo si ya hay miembros
        eventLoop();
        channel.close();
    }

    private void eventLoop() throws Exception {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Presiona Enter para incrementar el contador. Escribe 'exit' para salir.");
        
        while (true) {
            System.out.print("> ");
            String linea = in.readLine().trim();
            if (linea.equalsIgnoreCase("exit")) break;

            // En lugar de incrementar el estado aquí, enviamos el mensaje al grupo.
            // El método receive() atrapará este mensaje y sumará 1 para TODOS al mismo tiempo.
            Message msg = new Message(null, "INCREMENT") {};
            channel.send(msg);
        }
    }
}