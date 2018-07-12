/*out of the 20 IP's that send the routing table, 
 * we are selecting only 5 random unique number
 */


import java.util.ArrayList;
import java.util.Random;


public class randomips {
		public static ArrayList<String>random5 = new ArrayList<String>(5); 	// Random 5 ips
		public static void run(){
			
			//selecting 5 Random files and the filesInNode are considered to be present in the node
			int max = 9;
			int min = 0;
			int k=0;
			Random rand = new Random();
			{
			
			while(true){
				int randomNum = rand.nextInt((max - min) + 1) + min;
				
				if(!(random5.contains(routingTable.v.get(randomNum)))){
					random5.add(routingTable.v.get(randomNum));
					//System.out.println("print random: "+randomNum);
					k++;
				}
				if(k == 5){
					break;
				}
			}
			
			
		}
	}		
}
