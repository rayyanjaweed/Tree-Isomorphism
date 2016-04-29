/*
 * File Name : TreeGen.java
 * Short Description : This java code generates a random tree and assigns URLs to each node from a URL file.
 * Version Number : 1.0
 * Created Date : 04/01/2016
 */


import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;


/**
 * This class has methods which can create a random edge list for tree, create nodes of the tree, assign URL to nodes and display info
 * 
 * @author Rayyan Mohammed Jaweed
 * @author Sarfaraz Ali Khan
 * @version 1.0
 */
public class TreeGen
{
    
    int n;
    ArrayList<String> tree;
    Node [] treeNodes;
    HashMap<Integer, ArrayList<Integer>> nodeListByLevel;
    
    /**
     * Constructor
     */
    public TreeGen(int k) {
        n = k;
        tree = new ArrayList<String>(n-1);
        treeNodes = new Node[n];
        nodeListByLevel = new HashMap<Integer, ArrayList<Integer>>();
        for(int i=0;i<n;i++){
        	treeNodes[i] = new Node();
        }
    }
    
    /**
     * This method generates random edge list that forms a tree.
     * Root is numbered 0
     */
    public void rootedTree() {
        Random p =new Random();
        tree.add("0,1");
        for (int j=2;j<n;j++) {
            int r = p.nextInt(j);
            //(parent,child)
            tree.add(r+","+j);
        }
    }
    
    /**
     * This method creates nodes according to the edge list.
     * It also maps nodes to levels for the nodeByLevel hashmap
     */
    public void createNodes(){
    	treeNodes[0].parent = -1;
    	//Since the node 0 is at 0th Level
    	nodeListByLevel.put(0, new ArrayList<Integer>());
    	nodeListByLevel.get(0).add(0);
    	for(int i=0;i<n-1;i++){
    		int r = Integer.parseInt(tree.get(i).split(",")[0]);
    		int j = Integer.parseInt(tree.get(i).split(",")[1]);
    		treeNodes[r].child.add(j);
    		treeNodes[j].parent = r;
        	treeNodes[j].level = treeNodes[r].level + 1;
        	
        	int l = treeNodes[r].level + 1;
        	if(nodeListByLevel.containsKey(l)){
        		nodeListByLevel.get(l).add(j);
        	}else{
        		nodeListByLevel.put(l, new ArrayList<Integer>());
            	nodeListByLevel.get(l).add(j);
        	}
    	}
    }
    
    /**
     * This method adds URLs to the nodes
     * @param urlArray
     * 				It contains the list of all the URLs which is to be added in the nodes
     */
    public void addUrlToNodes(ArrayList<String> urlArray){
        for (int i=0;i<n;i++)
        treeNodes[i].setUrl(urlArray.get(i));
    }
    
    /**
     * This method scans an external file for URLs and puts them in an arraylist
     * @param file
     * 			It contains the external file where all URLs are stored
     * @return
     * 		ArrayList of all the URLs from the file
     */
    public static ArrayList<String> scanURLFile(File file){
    	ArrayList<String> urlArray = new ArrayList<String>();
    	try (BufferedReader br = new BufferedReader(new FileReader(file))) {
    	    String line;
    	    while ((line = br.readLine()) != null) {
    	    	if(!line.equals("")){
    	    		urlArray.add(line);
    	    	}
     	    }
    	} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
    	return urlArray;
    }
    
    /**
     * This method displays all the tree information to the output screen.
     */
    public void displayTree(){
    	for(int i=0;i<tree.size();i++){
    		System.out.println(tree.get(i));
    	}
    	System.out.println("****************************************");
		System.out.println("****************************************");
    	for(int i=0;i<treeNodes.length;i++){
    		System.out.println(" \n ");
    		System.out.println("Node = "+i);
    		System.out.println("Level = "+treeNodes[i].level);
    		System.out.println("Parent = "+treeNodes[i].parent);
    		System.out.println("Integer = "+treeNodes[i].integer);
    		System.out.print("Tuple:  ");
    		for(int j=0;j<treeNodes[i].tuple.size();j++){
    			System.out.print(treeNodes[i].tuple.get(j)+",");
    		}
    		System.out.println();
    		System.out.print("Children = ");
    		if(treeNodes[i].child.size() == 0){
    			System.out.println("No children");
    		}else{
    			for (Iterator iterator = treeNodes[i].child.iterator(); iterator.hasNext();) {
    				Integer child = (Integer)iterator.next();
    				System.out.print(child + ",");
    			}
    		}
    		System.out.println("URL : "+treeNodes[i].Url);
    	}
    }
    
    /**
     * This method displays all the nodes of the tree listed by all the levels
     */
    public void displayNodesByLevel(){
    	for(int i=0;i<nodeListByLevel.size();i++){
    		System.out.println("Level "+i+" :");
    		for(int j=0;j<nodeListByLevel.get(i).size();j++){
    			System.out.print(nodeListByLevel.get(i).get(j)+",");
    		}
    		System.out.println();
    	}
    }
    
    /**
     * This method displays all the nodes of the tree for a specific level number
     * @param levelNo
     * 				It contains the level number
     */
    public void nodeListByLevel(int levelNo){
    	System.out.println("Level "+levelNo+" :");
    	for(int i=0;i<nodeListByLevel.get(levelNo).size();i++){
    		System.out.print(nodeListByLevel.get(levelNo).get(i)+",");
    	}
    }
    
    /**
     * This method returns all the node children for a given node
     * @param nodeNumber
     * 				It contains the node number for whose children are needed
     */
    public void getChildren(int nodeNumber){
    	System.out.print("The children of the node "+nodeNumber+" is :");
		for (Iterator iterator = treeNodes[nodeNumber].child.iterator(); iterator.hasNext();) {
			Integer child = (Integer)iterator.next();
			System.out.print(child + ",");
		}
		if(treeNodes[nodeNumber].child.size() == 0)
			System.out.println("No Children");
    }
    
    /**
     * This method return the parent for a given node
     * @param nodeNumber
     * 				It contains the node number for whose parent is needed
     */
    public void getParent(int nodeNumber){
    	System.out.println("The parent of the node "+nodeNumber+" is : "+ treeNodes[nodeNumber].parent);
    }

    public static void main(String args[]){
    	
    	ArrayList<String> urlArray = new ArrayList<String>();
    	File file = new File("C:/Users/Rayyan/workspace/A and C 460 - HW6/src/testURL");
    	urlArray = scanURLFile(file);
    	int numberOfNodes = urlArray.size();
    	TreeGen testTree = new TreeGen(numberOfNodes);
    	//Creates Edge List for the tree
    	testTree.rootedTree();
    	//Creates nodes according to the Edge List of the tree
    	testTree.createNodes();
    	//Adds URL to the nodes
    	testTree.addUrlToNodes(urlArray);
    	//Displays the edge list and the nodes of the tree
    	testTree.displayTree();
    }
}