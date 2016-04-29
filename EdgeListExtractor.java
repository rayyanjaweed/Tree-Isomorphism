/*
 * File Name : EdgeListExtractor.java
 * Short Description : This java code extracts all the edges from a given file and puts it into an array list
 * Version Number : 1.0
 * Created Date : 04/14/2016
 */


import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;


/**
 * This class has method to extract edges from a file
 * 
 * @author Rayyan Mohammed Jaweed
 * @author Sarfaraz Ali Khan
 * @version 1.0
 */
public class EdgeListExtractor {

	/**
	 * This method extracts all the edges from a given file and puts it into an array list.
	 * @param file
	 * @return
	 * 		An array list of string containing all edges of the tree
	 */
	public ArrayList<String> extractEdges(File file){
		
		ArrayList<String> edges = new ArrayList<String>();
		try (BufferedReader br = new BufferedReader(new FileReader(file))) {
		    String line;
		    while ((line = br.readLine()) != null) {
		    	if(!line.equals("")){
		    		edges.add(line);
		    	}
	 	    }
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return edges;
	}
	
	
}
