/*
 * Leave Message is being sent to all the IP's in the routing table. 
length LEAVE IP_address port_no
 */
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.InetAddress;



public class leave {
	public static void run() throws IOException{
		//Get your node IP address
		String ipString = InetAddress.getLocalHost().getHostAddress();

		//Concatenate the Message to Send it 
		String Leave  = " Leave "+ipString+ " "+programMain.port_number;
				
		//Calculate the length of the message 
		int legthOfMsg = Leave.length();
				
		// Concatenate the String
		StringBuilder concString = new StringBuilder();
		concString.append("00");
		concString.append(legthOfMsg);
		concString.append(Leave);
		Leave = concString.toString();
	
		System.err.println("msg Send is: "+ Leave);
		//String converted into Bytes
		programMain.dataSent = Leave.getBytes();
		
		//LEAVE command to be sent
		int i=0;
		int sizeOfTable = routingTable.v.size();
		while(sizeOfTable!=0){
				//Taking IP and Port number from Routing table and Separating it .
				String ipToSend = routingTable.v.get(i);
				String[] trimmedIPnPort = ipToSend.split(" ");
				
				//converting String to IP
				InetAddress ipSend = InetAddress.getByName(trimmedIPnPort[0]);
				
				
				//Taking Port number from the trimmed data
				int portSend = Integer.parseInt(trimmedIPnPort[1]);
				
				//Datagram of Join to be Sent to the IP address the Routing table
				DatagramPacket leaveToNet = new DatagramPacket(programMain.dataSent, programMain.dataSent.length,ipSend,programMain.port_number_receive);
				programMain.clientSocket.send(leaveToNet);	
				
				//Increment the vector index to get ip n port number 
				i++;
				
				//decrement the size of the vector to check the condition
				sizeOfTable--;
		}
		
	}
}

