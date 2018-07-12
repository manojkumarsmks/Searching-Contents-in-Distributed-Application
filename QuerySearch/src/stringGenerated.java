import java.io.IOException;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Random;


public class stringGenerated {
		public static void start(String search) throws IOException{
			//Get your node IP address
			String ipString = InetAddress.getLocalHost().getHostAddress();
			
			//Search Message
			String Search  = " SER "+ipString+ " "+query.port_number_receive+ " "+search + " 0";
			int legthOfMsg = Search.length();
			
			// Concatenate the String
			StringBuilder concString = new StringBuilder();
			concString.append("00");
			legthOfMsg = legthOfMsg + 4;
			concString.append(legthOfMsg);
			concString.append(Search);
			Search = concString.toString();
			
			System.out.println(Search);
			int i=0;
			query.dataSent = Search.getBytes();
			
			
	
			
			int x =0;
			int noOfSends =5;
			while(noOfSends != 0){				
					
					
					//Taking IP and Port number from Routing table and Separating it .
					String ipToSend = randomips.random5.get(x);
					
					String[] trimmedIPnPort = ipToSend.split(" ");
				//	System.out.println("IP: "+trimmedIPnPort[0]);
					/*System.out.println("port: "+trimmedIPnPort[1]);	*/
					
					//converting String to IP
					InetAddress ipSend = InetAddress.getByName(trimmedIPnPort[0]);
					
					
					//Taking Port number from the trimmed data
					int portSend = Integer.parseInt(trimmedIPnPort[1]);
					
					//Datagram of Join to be Sent to the IP address the Routing table
					DatagramPacket joinToNet = new DatagramPacket(query.dataSent, query.dataSent.length,ipSend,query.port_number_receive);
					query.clientSocket.send(joinToNet);	
					x++;
					noOfSends--;
					
			}
		}
	}
