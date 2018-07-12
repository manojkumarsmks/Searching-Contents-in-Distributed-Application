/*
 * The List of the files's that can be present in the routing table. 
 * 5 random files's are taken from it. 
 */

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Random;

import org.omg.CORBA.portable.UnknownException;


public class files {
  public static	String[] filesInNode = new String[5];

		public static void start(){
			  try{
				//Movies from the filesname.txt
				String[] movies = new String[]{"Life of Pi", "Prometheus", "The Hunger Games", "Hotel Transylvania","Brave", "The Hobbit","The Amazing Spider-Man","Men in Black 3","The Dark Knight Rises","Skyfall","Adventures of Tintin","Jack and Jill","Glee","The Vampire Diarie", "King Arthur", "Windows XP", "Harry Potter", "Kung Fu Panda", "Lady Gaga", "Twilight","Windows 8","Mission Impossible","Turn Up The Music","Super Mario","American Pickers","Microsoft Office 2010","Happy Feet","Modern Family","American Idol","Hacking for Dummies"};
				Arrays.fill(filesInNode, "a");
			
				//selecting 5 Random files and the filesInNode are considered to be present in the node
				int max = 29;
				int min = 0;
				int k =0;
				int i =0;
				while(true){
					Random rand = new Random(); 		// Rand is a new object created
			
						int randomNum = rand.nextInt((max - min) + 1) + min; 		// Random number is developed within a that max and minimum number defined
						if((!files.filesInNode[0].equals(movies[randomNum]))&(!files.filesInNode[1].equals(movies[randomNum]))&(!files.filesInNode[2].equals(movies[randomNum]))&(!files.filesInNode[3].equals(movies[randomNum]))&(!files.filesInNode[4].equals(movies[randomNum]))){
						files.filesInNode[i] = movies[randomNum];	
						k++;
						i++;
						}
				if(k == 5){
					break;
				}
				//Take the Search Query
				}
				for(int j =0;j<5;j++){
					System.out.println(files.filesInNode[j]);
				}
  		}
	
 
		catch (UnknownException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
}
