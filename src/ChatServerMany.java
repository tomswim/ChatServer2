/*


import java.io.BufferedInputStream;
import java.io.DataInputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class ChatServerMany implements Runnable{
    private Socket socket = null;
    private ServerSocket server = null;
    private Thread thread = null;
    private DataInputStream streamIn = null;

    public ChatServerMany(int port) {
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
                socket = server.accept();
                System.out.println("Client accepted: " + socket);
                open();
                boolean done = false;
                while (!done) {
                    try {
                        String line = streamIn.readUTF();
                        System.out.println(line);
                        done = line.equals(".bye");
                    } catch (IOException ioe) {
                        done = true;
                    }
                }
                close();
            }   catch (IOException ioe) {
                System.out.println("Acceptance Error: " + ioe);
            }
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

    public void open() throws IOException {
        streamIn = new DataInputStream(new BufferedInputStream(socket.getInputStream()));
    }

    public void close() throws IOException {
        if (socket != null)    socket.close();
        if (streamIn != null)  streamIn.close();
    }

    public static void main(String args[]) {
        ChatServerMany server = null;
        if (args.length != 1) {
            System.out.println("Usage: java ChatServerMany port");
        }
        else {
            server = new ChatServerMany(Integer.parseInt(args[0]));
        }
    }
}
*/