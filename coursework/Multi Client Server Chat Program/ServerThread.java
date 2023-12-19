/* Sakina Ghafoor 
 * Lab 5 Assignment Fall 2023
 * Techniques in Programming 
 */

package Lab5Server;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;

/**
 * The ServerThread class represents a thread for handling communication with a client in a simple chat server.
 * Each instance of this class is responsible for managing the input and output streams of a connected client.
 * It reads messages from the client, processes them, and either broadcasts the message to all clients or sends
 * it to a specific recipient based on the specified format.
 */

public class ServerThread extends Thread {
    private Socket socket;
    private ArrayList<ServerThread> threadList;
    private PrintWriter output;
    
    private String senderName;

    /**
     * Constructs a new ServerThread instance.
     * @param socket The Socket representing the connection to the client.
     * @param threads The list of all active ServerThread instances.
     */
    public ServerThread(Socket socket, ArrayList<ServerThread> threads) {
        this.socket = socket;
        this.threadList = threads;
    }

    /**
     * The main execution logic of the ServerThread.
     * Reads messages from the client, processes them, and broadcasts or sends them to the appropriate recipient.
     */
    @Override
    public void run() {
        try {
            //Reading the input from Client
            BufferedReader input = new BufferedReader( new InputStreamReader(socket.getInputStream()));
            
            //returning the output to the client : true statement is to flush the buffer otherwise
            //we have to do it manually
             output = new PrintWriter(socket.getOutputStream(),true);
             this.senderName = input.readLine();
             System.out.println("Sender's name: " + senderName);

            //inifite loop for server
            while(true) {
                String outputString = input.readLine();
                //String senderLine = input.readLine();
                //if user types exit command
                if(outputString.equals("exit")) {
                    break;
                }
                String[] messageParts = outputString.split("\nTo: ");
                String messageContent = messageParts[0];
                String recipientName = messageParts.length > 1 ? messageParts[1] : null;

                if (recipientName != null) {
                    // Send the message to the specified recipient
                    printToSpecificClient("(" + senderName + ") " + messageContent, recipientName);
                } else {
                    // Broadcast the message to all clients
                    printToAllClients("(" + senderName + ") " + messageContent);
                }
                //printToAllClients(outputString);
                //output.println("Server says " + outputString);
                System.out.println("Server received " + outputString);
                
                //System.out.println("Sent by " + senderLine);

            }


        } catch (Exception e) {
            System.out.println("Error occured " +e.getStackTrace());
        }
    }

    /**
     * Broadcasts the given message to all connected clients.
     * @param outputString The message to be broadcasted.
     */
    public void printToAllClients(String outputString) {
        for( ServerThread sT: threadList) {
            sT.output.println(outputString);
        }

    }
    
    /**
     * Sends the given message to a specific client (recipient) based on the recipient's name.
     * @param outputString The message to be sent.
     * @param recipientName The name of the recipient client.
     */
    public void printToSpecificClient(String outputString, String recipientName) {
        for (ServerThread sT : threadList) {
            if (sT.senderName.equals(recipientName)) {
                sT.output.println(outputString);
                break; // Assuming there is only one matching recipient
            }
        }
    }
    
    
    
}
