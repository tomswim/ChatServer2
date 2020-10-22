

import java.io.BufferedInputStream;
import java.io.DataInputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;


public class ChatServerMultiClient implements Runnable{
    private ServerSocket server = null;
    private Thread thread = null;
    private ChatServerThread client = null;

    public ChatServerMultiClient (int port) {
        try {
            System.out.println("Binding to port " + port + ", please wait  ...");
            server = new ServerSocket(port);
            System.out.println("Server started: " + server);
            start();
        } catch(IOException ioe) {
            System.out.println(ioe);
        }
    }

    public void run() {
        while (thread != null) {
            try {
                System.out.println("Waiting for client ...");
                addThread(server.accept());
            }   catch (IOException ioe) {
                System.out.println("Acceptance Error: " + ioe);
            }
        }
    }

    public void addThread(Socket socket) {
        System.out.println("Client accepted: " + socket);
        client = new ChatServerThread(this, socket);
        try {
            client.open();
            client.start();
        } catch(IOException ioe) {
            System.out.println("Error opening thread: " + ioe);
        }
    }

    public void start() {
        System.out.println("Starting ...");
        if (thread == null) {
            thread = new Thread(this);
            thread.start();
        }
    }

    public void stop() {
        if (thread != null) {
            thread.stop();
            thread = null;
        }
    }

    public static void main(String args[]) {
        ChatServerMultiClient  server = null;
        if (args.length != 1) {
            System.out.println("Usage: java ChatServerMultiClient  port");
        }
        else {
            server = new ChatServerMultiClient(Integer.parseInt(args[0]));
        }
    }
}

