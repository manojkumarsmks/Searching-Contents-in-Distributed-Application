/* Receive the RegOK 
 * length REGOK no_nodes IP_1 Port_1 IP_2 Port_2c
 * Based up on the REGOK message the Routing table is updated 
 * 
 */

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;

public class registerOK {

		public static  int run(String S){
			String[] trimmed = S.split(" ");
			
			int noOfNodes = Integer.parseInt(trimmed[2]);
			
			if(noOfNodes == 0){
				//System.out.println("No IP, Hence Routing table Need no Be updated");
				return 0;
			}
			else if(noOfNodes == 1){
				String ipnPort = trimmed[3] + " " + trimmed[4];
				//System.out.println("The routing table is updated with "+ipnPort);
				routingTable.v.addElement(ipnPort);
				return 1;
			}
			else if(noOfNodes == 2){
				String ipnPort1 = trimmed[3] + " " + trimmed[4];
				//System.out.println("The routing table updated with "+ipnPort1);
				routingTable.v.addElement(ipnPort1);
				String ipnPort2 = trimmed[5] + " " + trimmed[6];
				//System.out.println("The routing table updated with "+ipnPort2);
				routingTable.v.addElement(ipnPort2);
				return 2;
			} 
			else if (noOfNodes == 9998) {
				System.out.println("Already Registered");
			}
			else {
				System.err.println("Error: "+noOfNodes);
			}
			return 3;
		}
	} 
