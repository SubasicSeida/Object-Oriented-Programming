package Week12;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

class ChatServer {
    private final List<ClientHandler> clients = Collections.synchronizedList(new ArrayList<>());

    public void start(int port) {
        try (ServerSocket serverSocket = new ServerSocket(port)) {
            System.out.println("Server is running on port: " + port);

            while (true) {
                Socket socket = serverSocket.accept();
                ClientHandler clientHandler = new ClientHandler(socket);
                clients.add(clientHandler);
                new Thread(clientHandler).start();
            }
        } catch (IOException e) {
            System.err.println("Error starting server: " + e.getMessage());
        }
    }

    private void broadcastMessage(String message, ClientHandler sender) {
        synchronized (clients) {
            for (ClientHandler client : clients) {
                if (client != sender) {
                    client.sendMessage(message);
                }
            }
        }
    }


class ClientHandler implements Runnable {
    private final Socket socket;
    private BufferedReader reader;
    private PrintWriter writer;
    private String username;

    public ClientHandler(Socket socket) {
        this.socket = socket;
        try {
            this.reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            this.writer = new PrintWriter(socket.getOutputStream(), true);
        } catch (IOException e) {
            System.err.println("Error initializing client handler: " + e.getMessage());
        }
    }

    @Override
    public void run() {
        try {
            this.username = reader.readLine();
            System.out.println(username + " has joined the chat.");
            broadcastMessage(username + " has joined the chat.", this);

            String message;
            while ((message = reader.readLine()) != null) {
                System.out.println(username + ": " + message);
                broadcastMessage(username + ": " + message, this);
            }
        } catch (IOException e) {
            System.err.println("Error in client handler: " + e.getMessage());
        } finally {
            try {
                socket.close();
            } catch (IOException e) {
                System.err.println("Error closing socket: " + e.getMessage());
            }
            clients.remove(this);
            broadcastMessage(username + " has left the chat.", this);
            System.out.println(username + " has disconnected.");
        }
    }

    public void sendMessage(String message) {
        writer.println(message);
    }
}
}

class ChatApplication {
    public static void main(String[] args) {
        ChatServer chatServer = new ChatServer();
        int port = 12345; // Change this port if needed
        chatServer.start(port);
    }
}

class ChatClient {
    public static void main(String[] args) {
        if (args.length < 2) {
            System.out.println("Usage: java ChatClient <username> <server-ip>");
            return;
        }

        String username = args[0];
        String serverIp = args[1];
        int serverPort = 12345; // Change this port if needed

        try (Socket socket = new Socket(serverIp, serverPort);
             BufferedReader serverReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
             PrintWriter serverWriter = new PrintWriter(socket.getOutputStream(), true);
             BufferedReader consoleReader = new BufferedReader(new InputStreamReader(System.in))) {

            serverWriter.println(username);

            Thread readerThread = new Thread(() -> {
                try {
                    String serverMessage;
                    while ((serverMessage = serverReader.readLine()) != null) {
                        System.out.println(serverMessage);
                    }
                } catch (IOException e) {
                    System.err.println("Error reading from server: " + e.getMessage());
                }
            });

            readerThread.start();

            String userMessage;
            while ((userMessage = consoleReader.readLine()) != null) {
                serverWriter.println(userMessage);
            }
        } catch (IOException e) {
            System.err.println("Error connecting to server: " + e.getMessage());
        }
    }
}