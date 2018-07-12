/* Flooding of the SER packets is done in this function if we Don't find in the node and
 * the hop count is <3
 * */
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class floodingSearch {
	public static void run(String S,String ipreceived) throws IOException{
		
		int sizeOfTable = routingTable.v.size();		//Size of the Routing table
		programMain.clientSocket = new DatagramSocket();
		int i =0;					
		
		//Flooding the SER to all the IP's in the routing table.
		while(sizeOfTable != 0){						//Flood the packet to all packets in the IPs
		
			String ipNport = routingTable.v.get(i);		
			String trimmed[] = ipNport.split(" ");
			InetAddress IP = InetAddress.getByName(trimmed[0]);	// IP address
			int port = Integer.parseInt(trimmed[1]);			// Port Number
			programMain.dataSent = S.getBytes();	
			if(!(trimmed[0].equals(ipreceived))){
				DatagramPacket regToBoot = new DatagramPacket(programMain.dataSent, programMain.dataSent.length,IP,programMain.port_number_receive);
				programMain.clientSocket.send(regToBoot);
				programMain.sendCount++;
			}
			i++;
			sizeOfTable--;
		}
	}
}
