/* Sakina Ghafoor 
 * Lab 5 Assignment Fall 2023
 * Techniques in Programming
 */

package Lab5Client;

import java.awt.event.ActionEvent;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

// all rights reserved to relevant owners 

/**
 * The ModelClient class represents the client-side of a simple chat application.
 * It connects to a server, reads input from the user, and sends messages to the server.
 * The class uses a separate thread to continuously read messages from the server.
 */

public class ModelClient {
	
    private Socket socket;
    private BufferedReader input;
    private PrintWriter output;
	protected View view = new View();

	/**
     * The main method of the ModelClient class. It establishes a connection with
     * the server, initializes input and output streams, and starts a thread for
     * continuous message reception.
     * @param args The command-line arguments (not used in this implementation).
     */
	public void main(String[] args) {
        try (Socket socket = new Socket("localhost", 5000)){
            //reading the input from server
            BufferedReader input = new BufferedReader( new InputStreamReader(socket.getInputStream()));
            
            //returning the output to the server : true statement is to flush the buffer otherwise
            //we have to do it manuallyy
            PrintWriter output = new PrintWriter(socket.getOutputStream(),true);

            //taking the user input
            Scanner scanner = new Scanner(System.in);
            String userInput;
            String response;
            //String clientName = "empty";
            String clientName = view.senderName.getText();
            
            ClientRunnable clientRun = new ClientRunnable(socket);


            new Thread(clientRun).start();
           //loop closes when user enters exit command
           
           do {
               
               if (!clientName.equals(null)) {
                   System.out.println("Enter your name ");
                    view.result.append("Enter your name");
                    userInput = view.messageField.getText();
                    clientName = view.senderName.getText();
                    output.println(clientName + userInput);
                    if (userInput.equals("exit")) {
                        break;
                    }
               } 
               else {
                    String message = ( "(" + view.senderName.getText() + ")" + " message : " );
                    System.out.println(message);
                    
                    view.result.append(message + "\n");
                    userInput = scanner.nextLine();
                    output.println(message + " " + userInput);
                    view.result.append(clientName + " " + message + " " + userInput);
                    if (userInput.equals("exit")) {
                        //reading the input from server
                        break;
                    }
                }

           } while (!userInput.equals("exit"));
           


            
        } catch (Exception e) {
            e.printStackTrace();
    }
    }
    
    //public static void main(String[] args) {
    	
   // }
    
}
