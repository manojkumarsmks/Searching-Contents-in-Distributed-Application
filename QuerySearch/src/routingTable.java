import java.util.Vector;

/* Routing table */
public class routingTable {
	
	//Routing table is initialized in form of an array
	public static Vector<String> v = new Vector<String> (20,2);
	public static void update(String newIP, String newPortNumber){
		String ipandPort = newIP + " "+ newPortNumber;
		v.addElement(ipandPort);
	}
	
}
