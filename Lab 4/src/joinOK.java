/*
 * Received: length JOIN IP_address port_no
 * Send: length JOINOK value 
 */

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.InetAddress;

public class joinOK {
	public static void Start(String S) throws IOException{
		
		//System.out.println("Inside the JOINOK");
		//Analyze the received message 
		String[] trimmedMsg = S.split(" ");
		String ipnPort = new String();
		ipnPort = trimmedMsg[2] + " " + trimmedMsg[3];
		InetAddress IP = InetAddress.getByName(trimmedMsg[2]);
		int port_Number = Integer.parseInt(trimmedMsg[3]);
		
		//Routing table size is recorded
		int routingTableSize = routingTable.v.size();
		
		//Routing table is updated
		routingTable.v.addElement(ipnPort);
			
		//Routing table size is re-recorded
		int updatedroutingTableSize = routingTable.v.size();

		
		//If Routing table is updated successfully, This msg is send
		if(updatedroutingTableSize == (routingTableSize +1)){
			String msg = "0014 JOINOK 0";
			programMain.dataSent = msg.getBytes();
			DatagramPacket send = new DatagramPacket(programMain.dataSent, programMain.dataSent.length,IP,programMain.port_number_receive);
			programMain.clientSocket.send(send);
		//	System.out.println("JOIN Successful");
		}
		
		//Unsuccessful update will send this message
		else{
			String msg = "0014 JOINOK 9998";
			programMain.dataSent = msg.getBytes();
			DatagramPacket send = new DatagramPacket(programMain.dataSent, programMain.dataSent.length,IP,programMain.port_number_receive);
			programMain.clientSocket.send(send);
		//	System.out.println("JOIN Not successful");
		}
	}
}
