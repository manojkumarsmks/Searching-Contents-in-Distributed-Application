/*
 * String analysis : length REG Your_IP Your_Port_NO
 */
public class analysisString {
	public static void start(String S){
		
		//String is trimmed for the analysis
		String[] trimmed = S.split(" ");
		
		//taking
		String ipnPort = trimmed[2] + " " + trimmed[3];
	//	System.out.println("The routing table is updated with "+ipnPort);
		routingTable.v.addElement(ipnPort);
	}
}
