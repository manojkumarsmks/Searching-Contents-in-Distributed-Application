/*
 * SearchOK message is executed based upon if the search is Found 
 * else NOT FOUND message is sent.
 */
import java.net.InetAddress;
import java.net.UnknownHostException;


public class searchOK {

	public static String run(String hops, String filename) throws UnknownHostException{
		
		//The IP of the node with which has the Query
		String ipString = InetAddress.getLocalHost().getHostAddress();
		int hop = Integer.parseInt(hops);
		String noOfHops = Integer.toString(hop);
		//String that is return to the IP that made a query is prepared here
		String S = " SEROK 1 "+ipString + " "+programMain.port_number_receive+" "+ noOfHops +" " + filename;
		int length = S.length();
		StringBuilder concString = new StringBuilder();
		concString.append("00");
		length = length + 4;
		concString.append(length);
		concString.append(S);
		S = concString.toString();
		//System.out.println("The searchOK is: "+S);
		//System.out.println("INSIDE SEARCH OK");
		//Prepared String is returned
		return S; 
		
	}
}
