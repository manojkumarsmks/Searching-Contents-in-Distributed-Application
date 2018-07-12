import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

/*
 * Query Search Generated here
 */
public class query {

	/**
	 * @param args
	 */
	public static byte dataSent[] = new byte[10000];
	public static byte dataReceived[] = new byte[10000];
	public static DatagramSocket clientSocket;
	public static DatagramSocket clientSocket1;
	public static int port_number = 9010;
	public static int port_number_receive = 9012;
	public static int portReceiveSearch = 5656;
	
	public static void main(String[] args) throws IOException, InterruptedException {
		
		clientSocket1=new DatagramSocket(port_number_receive);
		query.clientSocket = new DatagramSocket(port_number);
	
		// TODO Auto-generated method stub
		String[] queries  = new String[] {"Twilight","Jack","American Idol","Life of Pi","Twilight saga",
		"Life","Sky fall","Happy Feet","Prometheus","Happy Feet","Gungry","Twilight","Windows","Happy Feet",
		"Mission Impossible","Twilight","Windows 8","The","Happy","Windows 8","Happy Feet","Super Mario","Hotel Transylvania",
		"Jack and Jill","Happy Feet","The Hunger Games","Hobbit","Turn Up The Music","Adventures of Tintin","Twilight saga",
		"Happy Feet","Super Mario","American Pickers","Microsoft Office 2010","Twilight","Modern Family","Jack and Jill","Jill",
		"Spider-Man","Glee","The Vampire Diarie","King Arthur","Dark Knight","King Arthur","Windows XP","Harry Potter","Men in Black",
		"Feet","Kung Fu Panda","Lady Gaga","Gaga","Happy Feet","Twilight","Hacking","King"};
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
										//Collecting all IPs here//
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////		
		int i = 20;
		System.out.println("Listening for the IP's");
		while( i != 0){
			DatagramPacket receiveFromBS = new DatagramPacket(query.dataReceived,query.dataReceived.length);
			clientSocket1.receive(receiveFromBS);
			String S = new String(receiveFromBS.getData(),0,receiveFromBS.getLength());
			System.out.println(S);
			analysisString.start(S);
			i--;
			}
		System.out.println("Enter 1 to Seart Query");
		randomips.run();
		
		for(int k=0;k<5;k++){
			String ipToSend = randomips.random5.get(k);
			String[] trimmedIPnPort = ipToSend.split(" ");
			System.out.println("IP: "+trimmedIPnPort[0]);
		}
			
			Scanner scanIn = new Scanner(System.in);
			
		String input = scanIn.nextLine();
		//int regInt = Integer.parseInt(input);
	
		
		System.out.println("Start the query");
		for(int j =0;j<55;j++){
			DatagramSocket clientSocket2 = new DatagramSocket(portReceiveSearch);
			int startTime =  (int) System.currentTimeMillis();	// Timer - current time in milliseconds
			stringGenerated.start(queries[j]);
			DatagramPacket receiveFromBS = new DatagramPacket(query.dataReceived,query.dataReceived.length);
			clientSocket2.receive(receiveFromBS);
			int stopTime = (int) System.currentTimeMillis();	// TImer  - current time in milliseconds
			String S = new String(receiveFromBS.getData(),0,receiveFromBS.getLength());
			System.out.println(S);
			System.out.println("Latency : "+ (stopTime-startTime));
			System.out.println("-----------------------------------------------------");
			clientSocket2.close();
			TimeUnit.SECONDS.sleep(4);							// Wait for 3 Seconds to Send the next Query
			
		}
		System.out.println("Enter 1 to leave the nodes");
		Scanner scanIn1 = new Scanner(System.in);
		String input1 = scanIn.nextLine();
		
		for(int j =0;j<1;j++){
			System.out.println("Start to Leave");
			String Stop = "0000 STOP ALL";
			
			int x =0;
			int noOfSends =20;
			while(noOfSends != 0){				
					
				
					//Taking IP and Port number from Routing table and Separating it .
					String ipToSend = routingTable.v.get(x);
					
					String[] trimmedIPnPort = ipToSend.split(" ");
				/*	System.out.println("IP: "+trimmedIPnPort[0]);
					System.out.println("port: "+trimmedIPnPort[1]);*/
					
					//converting String to IP
					InetAddress ipSend = InetAddress.getByName(trimmedIPnPort[0]);
					
					
					//Taking Port number from the trimmed data
					int portSend = Integer.parseInt(trimmedIPnPort[1]);
					query.dataSent = Stop.getBytes();
					
					//Datagram of Join to be Sent to the IP address the Routing table
					DatagramPacket joinToNet = new DatagramPacket(query.dataSent, query.dataSent.length,ipSend,query.port_number_receive);
					query.clientSocket.send(joinToNet);	
					noOfSends--;
					x++;
			}
		}
			
	}
}
