/*
 * Once a SER is received, the search is done.
 * Even Partially or even Fully. 
 */


import java.io.IOException;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.util.Random;

import org.omg.CORBA.portable.UnknownException;

public class search {
	/**
	 * @param args
	 * @throws IOException 
	 */
	public static void run(String S, String ipreceived) throws IOException{	
		try{
				//String format = "0014 SER localhost 98764 Dark Knight 10";
				// Search QUERY is extracted from the file
				String words[]=S.split(" ");
				int wordlength=words.length;
				String name = new String();
				for(int i =4;i<(wordlength-1);i++){
					 name = name+words[i];
					 if(i!= wordlength -2)
						 name =name + " ";
				}

				//System.out.println(name);
			
				 //IP address and port Number
				InetAddress IP = InetAddress.getByName(words[2]);	
				int port_Number = Integer.parseInt(words[3]);
				
				int flag =0;
				int hops = Integer.parseInt(words[wordlength-1]); 
				if(hops == 3 ){
					flag =1;
					String notFound = "NOTFOUND";
					byte[] dataReturned = new byte[1000];
					dataReturned = notFound.getBytes();
					DatagramPacket returnToSource = new DatagramPacket(dataReturned, dataReturned.length,IP,programMain.portReceiveSearch);
					programMain.clientSocket1.send(returnToSource);
				}
				else{
				
						//Search for Entire file
						if(name.equals(files.filesInNode[0]) || name.equals(files.filesInNode[1]) || name.equals(files.filesInNode[2]) ||name.equals(files.filesInNode[3])||name.equals(files.filesInNode[4])){
							System.out.println("Fully File Found");
							String returnS = searchOK.run(words[wordlength-1],name);
							byte[] dataReturned = new byte[1000];
							dataReturned = returnS.getBytes();
							DatagramPacket returnToSource = new DatagramPacket(dataReturned, dataReturned.length,IP,programMain.portReceiveSearch);
							programMain.clientSocket1.send(returnToSource);
							flag = 1;
							programMain.sendCount++;				// Incrementing  keep track on the no of sent Packets
						}
						
						//Searing individual words in the file name
						else{
							for(int i =0;i<5;i++){
							if((files.filesInNode[i]).contains(name)){
								System.out.println("Partailly found");
								String returnS = searchOK.run(words[wordlength-1],files.filesInNode[i]);
								System.out.println("returnS: "+returnS);
								programMain.dataSent = returnS.getBytes();
								DatagramPacket returnToSource = new DatagramPacket(programMain.dataSent,programMain.dataSent.length,IP,programMain.portReceiveSearch);
								programMain.clientSocket1.send(returnToSource);
								programMain.sendCount++;			// Incrementing  keep track on the no of sent Packets
							}
						}
					}
				}
				if(flag == 0){		
						//Flooding the Search Messages
						hops = hops+1;
						String forward = new String();
						for(int i =0;i<(wordlength -1);i++){				
							 forward = forward + words[i];
							 forward = forward + " ";
							}
						forward = forward + hops;
						floodingSearch.run(forward,ipreceived);
				}
		}
				catch(UnknownException e){
			e.printStackTrace();
	}
}
}