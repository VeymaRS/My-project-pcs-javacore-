package ru.netology.javacore;

import java.io.*;
import java.net.Socket;
import java.util.Scanner;

public class Client {
    public static void main(String[] args) throws IOException {
        try (Socket socket = new Socket("localhost", 8989);
             Scanner in = new Scanner(System.in);
             BufferedReader ois = new BufferedReader(new InputStreamReader(socket.getInputStream()));
             PrintWriter out = new PrintWriter(socket.getOutputStream(), true)
        ) {
            String inputTask;
            while (!socket.isOutputShutdown()) {
                System.out.println("Client connected to socket");
                System.out.println("What do you want to do? (input ADD TASK or REMOVE TASK). Input E for exit");
                inputTask = in.nextLine();
                if (inputTask.startsWith("ADD") || inputTask.startsWith("REMOVE")) {
                    String[] input = inputTask.split(" ");
                    out.println("{ \"type\": \"" + input[0] + "\", \"task\": \"" + input[1] + "\" }");
                } else if(inputTask.equals("E")) {
                    out.println("{ \"type\": \"EXIT\", \"task\": \"EXIT\" }");
                    socket.close();
                } else {continue;}
                System.out.println(ois.readLine());
            }
        } catch (Exception err) {
            System.out.println("Socket closed");
        }
    }
}
