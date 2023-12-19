/* Sakina Ghafoor 
 * Lab 5 Assignment Fall 2023
 * Techniques in Programming 
 */

package Lab5Client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

/**
 * The ClientRunnable class implements the Runnable interface and represents
 * a separate thread that continuously reads messages from the server.
 * It is designed to be used in conjunction with the client-side of a simple
 * chat application.
 */

public class ClientRunnable implements Runnable {

    private Socket socket;
    private BufferedReader input;
    // private PrintWriter output;

    /**
     * Constructs a new ClientRunnable with the specified socket.
     * @param s The Socket used for communication with the server.
     * @throws IOException If an I/O error occurs when creating the BufferedReader.
     */
    public ClientRunnable(Socket s) throws IOException {
        this.socket = s;
        this.input = new BufferedReader( new InputStreamReader(socket.getInputStream()));
        // this.output = new PrintWriter(socket.getOutputStream(),true);
    }
    
    /**
     * The run method of the thread. It continuously reads messages from the server
     * and prints them to the console.
     */
    @Override
    public void run() {
        
            try {
                while(true) {
                    String response = input.readLine();
                    System.out.println(response);
                }
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                try {
                    input.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
    }
    
}
