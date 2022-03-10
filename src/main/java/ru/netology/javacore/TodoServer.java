package ru.netology.javacore;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class TodoServer {
    private Todos todos;
    private int port;
    GsonBuilder builder = new GsonBuilder();
    Gson gson = builder.create();

    public TodoServer(int port, Todos todos) {
        this.port = port;
        this.todos = todos;
    }

    public void start() throws IOException {
        try (
                ServerSocket serverSocket = new ServerSocket(port);
                Socket clientSocket = serverSocket.accept();
                PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
                BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()))) {

            System.out.println("Starting server at " + port + "...");

            String input;

            while (true) {
                input = in.readLine();
                Task task = gson.fromJson(input, Task.class);
                switch (task.getType()) {
                    case "ADD":
                        todos.addTask(task.getTask());
                        break;
                    case "REMOVE":
                        if (todos.isTaskStatus()) {
                            todos.removeTask(task.getTask());
                            break;
                        } else {
                            break;
                        }
                    case "EXIT":
                        out.println(todos.getAllTasks());
                        in.close();
                        out.close();
                        clientSocket.close();
                        break;
                }
                out.println(todos.getAllTasks());
            }
        } catch (Error err) {
            System.out.println("Stream closed");
        }
    }
}

