/* Sakina Ghafoor 
 * Lab 5 Assignment Fall 2023
 * Techniques in Programming 
 */

package Lab5Server;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.Label;
import java.awt.Panel;
import java.awt.TextArea;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Calendar;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;

/**
 * The ControlServer class represents the main server control class for a simple chat application.
 * It manages the graphical user interface (GUI) for the server and handles incoming client connections.
 * The server displays the current date and time, as well as the messages received from clients.
 */
public class ControlServer implements ActionListener {
	
	protected TextArea result = new TextArea(40,40);
	protected Label timeLabel = new Label("Date and Time ", Label.RIGHT);
	protected TextField timeField = new TextField("");
	protected Panel displayTime = new Panel(new GridLayout(1,2));
	//protected JButton connect = new JButton("connect");
	protected JFrame serverF = new JFrame("Server");
		
	int year,month,day,hour,min,sec;
    String todayS, timeS, minS, secS;
    Calendar now;
    
    /**
     * Constructs a new ControlServer instance.
     * Initializes the server GUI and starts listening for client connections.
     */
    public ControlServer() {
    	SwingUtilities.invokeLater(() -> {
    	serverF.setLayout(new BorderLayout());
		serverF.setSize(400, 400);
		displayTime.add(timeLabel);
		displayTime.add(timeField);
		serverF.add(displayTime,BorderLayout.NORTH);
		serverF.add(result,BorderLayout.CENTER);
		//serverF.add(connect, BorderLayout.SOUTH);
		serverF.setBackground(Color.orange);
		//connect.addActionListener(this);
		serverF.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		serverF.setVisible(true);
		result.append(processTime(2) + "\n"); 
		
    	});
    }

    /**
     * The main method to start the server.
     * Initializes the server GUI and begins listening for client connections.
     * @param args Command-line arguments (not used).
     */
    public static void main(String[] args) {
        //using serversocket as argument to automatically close the socket
        //the port number is unique for each server

        //list to add all the clients thread
    	SwingUtilities.invokeLater(ControlServer::new);
    	
        ArrayList<ServerThread> threadList = new ArrayList<>();
        try (ServerSocket serversocket = new ServerSocket(5000)){
            while(true) {
                Socket socket = serversocket.accept();
                ServerThread serverThread = new ServerThread(socket, threadList);
                //starting the thread
                threadList.add(serverThread); 
                serverThread.start();

                //get all the list of currently running thread

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    /**
     * Processes the current time based on the specified option.
     * @param option An integer representing the time processing option.
     *               0: Returns the current date.
     *               1: Returns the current time.
     *               2: Returns the current date and time.
     * @return A formatted string representing the processed time.
     */
    public String processTime(int option)
	   {    now = Calendar.getInstance();
	        year = now.get(Calendar.YEAR); 
       month = now.get(Calendar.MONTH)+1; 
       day = now.get(Calendar.DAY_OF_MONTH);
       hour = now.get(Calendar.HOUR);
       min =  now.get(Calendar.MINUTE);	  
       sec =  now.get(Calendar.SECOND);
       if (min < 10 )  minS =  "0" + min ;  else  minS = "" + min;
       if (sec < 10 )  secS =  "0" + sec ;  else  secS = "" + sec;
       todayS =  month + " / " + day + " / " + year;  
       timeS  = hour + " : " + minS + " : " + secS; 
       switch(option) {
       case (0):  return todayS  ; 
       case (1):  return timeS;
       case (2):  return todayS + " @ " + timeS ; 
      } 
      return null;  // should not get here
   }

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}
}
