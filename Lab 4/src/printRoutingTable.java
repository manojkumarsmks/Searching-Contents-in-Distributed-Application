/*
 * The routing table is printed from the vector table 
 * This class is executed just before leave is executed.
 */

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;


public class printRoutingTable {
public static void run(){
	int sizeOfTable = routingTable.v.size();		//Size of the Routing table
	int i =0;					
	System.out.println("In the Function");
	while(sizeOfTable != 0){						//Printing the rouinging table till the size of the table is zero
		
		String ipNport = routingTable.v.get(i);		
		System.out.println(ipNport);
		i++;
		sizeOfTable--;
		}
	}
}

