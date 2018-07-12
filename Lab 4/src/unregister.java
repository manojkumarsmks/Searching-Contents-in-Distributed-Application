/* Unregister Message sent to Boot Strap Server*/  

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;

import org.omg.CORBA.portable.UnknownException;


public class unregister {
	public static void run() throws IOException{
		try{
			programMain.clientSocket = new DatagramSocket();
			//Get the IP address
			String ipString = InetAddress.getLocalHost().getHostAddress();
			String port = Integer.toString(programMain.port_number);
			String unReg  = " UNREG "+ipString+" "+port+" dhara";
			int length = unReg.length();
			StringBuilder concString = new StringBuilder();
			concString.append("00");
			length = length + 4;
			concString.append(length);
			concString.append(unReg);
			unReg = concString.toString();
			
			System.out.println(unReg);
			InetAddress IPAddress = InetAddress.getByName("129.82.12.187");// IP address to be sent
			
			System.out.println();
			//Send the Registering Data
			programMain.dataSent = unReg.getBytes();
			
			//Datagram to be sent to BS
			DatagramPacket regToBoot = new DatagramPacket(programMain.dataSent, programMain.dataSent.length,IPAddress,programMain.portNumber);
			
			// Reg data is send to Boot StrapServer
			programMain.clientSocket.send(regToBoot);	

		}
		//Catch the exceptions here
		catch(UnknownException e){
				e.printStackTrace();
		}
		
		catch (SocketException e) {
				e.printStackTrace();
		}
	}	
}