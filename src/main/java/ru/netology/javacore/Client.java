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
            System.out.println("Client connected to socket");
            System.out.println("What do you want to do? (input ADD or REMOVE)");
            inputTask = in.nextLine();
            if (inputTask.startsWith("ADD") || inputTask.startsWith("REMOVE")) {
                String[] input = inputTask.split(" ");
                StringBuilder sb = new StringBuilder();
                for (int i = 1; i < input.length; i++) {
                    if (i != input.length - 1) {
                        sb.append(input[i])
                                .append(" ");
                    } else {
                        sb.append(input[i]);
                    }
                }
                out.println("{ \"type\": \"" + input[0] + "\", \"task\": \"" + sb + "\" }");
            } else {
                System.out.println("Incorrect input");
            }
            System.out.println(ois.readLine());
        } catch (Exception err) {
            System.out.println("Socket closed");
        }
    }
}
