/* Sakina Ghafoor 
 * Lab 5 Assignment Fall 2023
 * Techniques in Programming
 */

package Lab5Client;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.TextArea;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

/**
 * The View class represents the graphical user interface (GUI) for a simple chat client.
 * It includes components such as text fields, buttons, and checkboxes for sending messages and configuring the chat.
 * The class also handles user input, message encryption, and the display of incoming messages.
 */

public class View extends JFrame implements ActionListener{

	protected JTextArea result;
    protected JTextField messageField;
    protected JButton sendButton;
    protected Scanner scanner;
    protected String clientName;
    protected PrintWriter output;
    protected JFrame frame = new JFrame("Chat Console");
	protected JLabel senderLabel;
	protected JLabel recipientLabel;
	protected JTextField senderName;
	protected JTextField recipientsList;
	protected JCheckBox encryptB;

	/**
     * Constructs a new View instance, creating the graphical user interface (GUI) for the chat client.
     */
    public View() {
        
    	JPanel panel = new JPanel(new BorderLayout());
        
        senderLabel = new JLabel("Sender: ");
        recipientLabel = new JLabel("Recipient: ");
        senderName = new JTextField();
        recipientsList = new JTextField();
        encryptB = new JCheckBox("encrypt");

        
        JPanel p2 = new JPanel(new GridLayout(3, 2));
        p2.add(senderLabel);
        p2.add(senderName);
        p2.add(recipientLabel);
        p2.add(recipientsList);
        p2.add(encryptB);
        
        panel.add(p2, BorderLayout.NORTH);
        
        JPanel p3 = new JPanel(new BorderLayout());
        result = new JTextArea();
        result.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(result);
        p3.add(scrollPane);
        panel.add(p3, BorderLayout.CENTER);

        JPanel p4 = new JPanel(new BorderLayout());
        messageField = new JTextField();
        p4.add(messageField, BorderLayout.CENTER);
        
        sendButton = new JButton("Send");
        sendButton.addActionListener(new SendButtonListener());
        p4.add(sendButton, BorderLayout.EAST);
        panel.add(p4, BorderLayout.SOUTH);
        

        frame.getContentPane().add(panel);

        //scanner = new Scanner(System.in);
        sendButton.addActionListener(new SendButtonListener());
        encryptB.addActionListener(this);

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 300);
        frame.setVisible(true);
    }
    
    /**
     * ActionListener implementation for the sendButton.
     * Sends the entered message to the server, optionally encrypting it based on user choice.
     */
    public class SendButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String message = messageField.getText();
            if (!message.isEmpty() && output != null) {
                
                if (encryptB.isSelected()) {
                    message = encryptMessage(message);
                }
                
                output.println("(" + senderName.getText() + ") " + "message: " + message + "\nTo: "+recipientsList.getText()+ "\n");
                messageField.setText("");  // Clear the input field after sending
            }
        }
    }
    
    /**
     * Encrypts the given message using a simple Caesar cipher with a shift of 1.
     * @param message The message to be encrypted.
     * @return The encrypted message.
     */
    public String encryptMessage(String message) {
        // Implement your encryption logic here
        // For demonstration, let's use a simple Caesar cipher with a shift of 1
        StringBuilder encryptedMessage = new StringBuilder();
        for (char c : message.toCharArray()) {
            if (Character.isLetter(c)) {
                char encryptedChar = (char) (((c - 'A' + 1) % 26) + 'A');
                encryptedMessage.append(encryptedChar);
            } else {
                encryptedMessage.append(c);
            }
        }
        return encryptedMessage.toString();
    }
    
   /* 
    public void setSenderName(String name) {
        senderName.setText(name);
    }

    public String getSenderName() {
        return senderName.getText();
    }

    public void setClientName(String name) {
        this.clientName = name;
    }
*/
    /**
     * Retrieves user input from the scanner.
     * @return The user input as a String.
     */
    public String getUserInput() {
        return scanner.nextLine();
    }

    /**
     * Appends a message to the chat result area.
     * @param message The message to be appended.
     */
    public void appendMessage(String message) {
        result.append(message + "\n");
    }

    /**
     * Sets the output PrintWriter for sending messages to the server.
     * @param output The PrintWriter instance.
     */
    public void setOutput(PrintWriter output) {
        this.output = output;
    }

    /**
     * The main method to run the chat client.
     * Connects to the server, sets up input and output streams, and starts the GUI and message listening threads.
     * @param args The command-line arguments.
     */
    public static void main(String[] args) {
        try (Socket socket = new Socket("localhost", 5000)) {
            BufferedReader input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            PrintWriter output = new PrintWriter(socket.getOutputStream(), true);

            View view = new View();
            //view.setClientName(view.senderName.getText());
            view.setOutput(output);

            new Thread(() -> {
                try {
                    while (true) {
                        String response = input.readLine();
                        if (response == null) {
                            break;
                        }
                        view.appendMessage(response);
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }).start();

            // Keep the GUI running
            while (true) {
                // You can add some logic here if needed
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}
}
