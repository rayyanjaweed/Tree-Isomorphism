/*
 * File Name : Node.java
 * Short Description : This java code contains a Node class which will form the data structure of the node of a tree.
 * Version Number : 1.0
 * Created Date : 04/01/2016
 */
import java.util.ArrayList;


/**
 * This class forms the data structure of the node of a tree.
 * 
 * @author Rayyan Mohammed Jaweed
 * @author Sarfaraz Ali Khan
 * @version 1.0
 */
public class Node
{
    String Url;
    int level;
    int parent;
    int integer;
    ArrayList<Integer> child;
    ArrayList<Integer> tuple;
    
    public Node()
    {
       Url="";
       child = new ArrayList<Integer>();
       tuple = new ArrayList<Integer>();
    }
    
    public void setUrl(String s) {
      Url=s;  
    }

  
}