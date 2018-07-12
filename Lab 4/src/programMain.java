import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.rmi.dgc.Lease;
import java.util.Scanner;

import org.omg.CORBA.portable.UnknownException;
public class programMain{

	public static byte dataSent[] = new byte[10000];
	public static byte dataReceived[] = new byte[10000];
	public static DatagramSocket clientSocket;
	public static int portNumber = 10001;
	public static int port_number = 9010;
	public static int port_number_receive = 9012;
	public static int portReceiveSearch = 5656;
	public static int sendCount = 0;
	public static int receiveCount = 0;
	
	public static DatagramSocket clientSocket1;
	public static void main(String[] args) throws IOException{
		clientSocket1=new DatagramSocket(port_number_receive);
		programMain.clientSocket = new DatagramSocket(port_number);
		boolean flag = true;
		try{
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
																		//Unregistering is executed//
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

				unregister.run();
				DatagramPacket receiveFromBS = new DatagramPacket(programMain.dataReceived,programMain.dataReceived.length);
				programMain.clientSocket.receive(receiveFromBS);
				String S = new String(receiveFromBS.getData(),0,receiveFromBS.getLength());
				System.out.println(S);
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
																	//Registering is executed//
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
				System.out.println("Registering to BS");
				register.run();
				DatagramPacket receiveFromBS1 = new DatagramPacket(programMain.dataReceived,programMain.dataReceived.length);
				programMain.clientSocket.receive(receiveFromBS1);
				String S1 = new String(receiveFromBS1.getData(),0,receiveFromBS1.getLength());
				System.out.println(S1);
				int reg = registerOK.run(S1);
				toExternal.run();					//Sending messages to External Node
			
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
																//JOINING is executed//
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////			
			
				join.run();
				DatagramPacket receiveFromBS2 = new DatagramPacket(programMain.dataReceived,programMain.dataReceived.length);
				clientSocket1.receive(receiveFromBS2);
				S = new String(receiveFromBS2.getData(),0,receiveFromBS2.getLength());
				System.out.println(S);
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
																//Files in the Nodes//
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////			
			
				files.start();
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
																//Listening is executed//
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////			
				System.out.println("Listen Mode is Activated");	
				int receivePackets = 0;
				while(true){							// Listen Mode is Activated.
					
					DatagramPacket receiveFromNodes = new DatagramPacket(programMain.dataReceived,programMain.dataReceived.length);
					clientSocket1.receive(receiveFromNodes);
					receivePackets++;											// Increment the Receive Count to keep track of received packets
					String ipReceived = receiveFromNodes.getAddress().getHostAddress();
				
					//System.out.println("packet recieved");
					S = new String(receiveFromNodes.getData(),0,receiveFromNodes.getLength());
					System.out.println(S);
						
					//Analyse the Packets
					String trimmedString[] = S.split(" ");
								
					//JOIN called if the String code was to JOIN
					if(trimmedString[1].equals("JOIN")){
						joinOK.Start(S);	
					}
					else if(trimmedString[1].equals("SER")){
						search.run(S,ipReceived);
					}
					else if(trimmedString[1].equals("STOP")){
						System.err.println("Printing Routing table");
						printRoutingTable.run();
					//	System.out.println("Leave and Unregister");
						leave.run();
						DatagramPacket receiveFromBS3 = new DatagramPacket(programMain.dataReceived,programMain.dataReceived.length);
						clientSocket1.receive(receiveFromBS3);
						System.out.println("RP : "+receivePackets);
						System.out.println("SP : "+sendCount+2);
						System.out.println("Routing Table Size: "+routingTable.v.size());
						S = new String(receiveFromBS3.getData(),0,receiveFromBS3.getLength());
						unregister.run();
						DatagramPacket receiveFromBS4= new DatagramPacket(programMain.dataReceived,programMain.dataReceived.length);
						clientSocket.receive(receiveFromBS4);
						S = new String(receiveFromBS4.getData(),0,receiveFromBS4.getLength());
						System.out.println(S);
						System.exit(0);
					}
				}
	}
		catch (UnknownException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
}
				