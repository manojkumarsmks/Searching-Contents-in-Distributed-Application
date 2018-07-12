/*Joining Message  		
 * 	Send: length JOIN IP_address port_no
 *  
 */

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class join {
	public static void run() throws IOException	{ 

		//Get your node IP address
		String ipString = InetAddress.getLocalHost().getHostAddress();

		//Concatenate the Message to Send it 
		String Join  = " JOIN "+ipString+ " "+programMain.port_number_receive;
		
		//Calculate the length of the message 
		int legthOfMsg = Join.length();
		System.out.println("Length :"+legthOfMsg);
		
		// Concatenate the String
		StringBuilder concString = new StringBuilder();
		concString.append("00");
		legthOfMsg = legthOfMsg + 4;
		concString.append(legthOfMsg);
		concString.append(Join);
		Join = concString.toString();
		
		System.out.println(Join);
		//String converted into Bytes
		programMain.dataSent = Join.getBytes();
		
		//JOIN command to be sent
		int i=0;
		
		int sizeOfTable = routingTable.v.size();
		while(sizeOfTable!=0){
			
				// Sending the Packet
				//System.out.println("Sending the JOIN and Sending the Join");
				
				//Taking IP and Port number from Routing table and Separating it .
				String ipToSend = routingTable.v.get(i);
				
				String[] trimmedIPnPort = ipToSend.split(" ");
				System.out.println("IP: "+trimmedIPnPort[0]);
				//System.out.println("port: "+trimmedIPnPort[1]);
				
				//converting String to IP
				InetAddress ipSend = InetAddress.getByName(trimmedIPnPort[0]);

				
				//Datagram of Join to be Sent to the IP address the Routing table
				DatagramPacket joinToNet = new DatagramPacket(programMain.dataSent, programMain.dataSent.length,ipSend,programMain.port_number_receive);
				programMain.clientSocket.send(joinToNet);	
				
				//Increment the vector index to get ip n port number 
				i++;
				
				//decrement the size of the vector to check the condition
				sizeOfTable--;
		}
		System.out.println("JOIN over");
	}
}
