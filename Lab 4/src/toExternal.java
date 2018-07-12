/*programMain to Register your node to the External Code
 * length REG Your_IP Your_Port_NO
 * */

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;

import org.omg.CORBA.portable.UnknownException;

		public class toExternal {
			public static void run() throws IOException{
				try{
					programMain.clientSocket = new DatagramSocket();
					//Get the IP address
					String ipString = InetAddress.getLocalHost().getHostAddress();
					String port =Integer.toString(programMain.port_number);
					String Reg  = " REG "+ipString+" "+port+ " dhara";
					//This REG will be sent to external code
					int length = Reg.length();
					StringBuilder concString = new StringBuilder();
					concString.append("00");
					length = length + 4;
					concString.append(length);
					concString.append(Reg);
					Reg = concString.toString();
					System.out.println(Reg);
					InetAddress IPAddress = InetAddress.getByName("192.91.235.232");// IP address to be sent
						
					System.out.println();
			
					//Send the Registering Data
					programMain.dataSent = Reg.getBytes();
						
					//Datagram to be sent to BS
					DatagramPacket regToBoot = new DatagramPacket(programMain.dataSent, programMain.dataSent.length,IPAddress,programMain.port_number_receive);
			
					// Reg data is send to Boot StrapServer
					programMain.clientSocket.send(regToBoot);
					System.out.println("Sent to External node");
						
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
