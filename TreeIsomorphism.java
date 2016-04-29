/*
 * File Name : TreeIsomorphism.java
 * Short Description : This java code compares two trees and tells if they are isomorphic or not
 * Version Number : 1.0
 * Created Date : 04/12/2016
 */


import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;


/**
 * This class compares two trees and tells if they are isomorphic or not
 * 
 * @author Rayyan Mohammed Jaweed
 * @author Sarfaraz Ali Khan
 * @version 1.0
 */
public class TreeIsomorphism {
	
	ArrayList<Integer> L1 = new ArrayList<Integer>();
	ArrayList<Integer> L2 = new ArrayList<Integer>();
	
	/**
	 * This methods checks for isomorphism between two trees
	 * @param tree1
	 * 			This is the first tree
	 * @param tree2
	 * 			This is the second tree
	 * @return
	 * 		True if the two trees are isomorphic.
	 * 		False if the two trees are not isomorphic.
	 */
	public boolean checkIsomorphism(TreeGen tree1, TreeGen tree2){
		boolean isIsomorphic = false;
		int tree1Height = tree1.nodeListByLevel.size();
		int tree2Height = tree2.nodeListByLevel.size();
		
		if(tree1Height == tree2Height){
			L1 = assignZeroToLeaves(tree1);
			L2 = assignZeroToLeaves(tree2);
			for(int i = tree1Height - 2; i > 0; i--){
				isIsomorphic = assignIntegersToNodes(tree1,tree2,i);
				if(!isIsomorphic){
					return isIsomorphic;
				}
			}
			// Comparing Roots of Tree1 and Tree2
			if(tree1.treeNodes[0].integer == tree2.treeNodes[0].integer){
				isIsomorphic = true;
			}
		}
		return isIsomorphic;
	}
	
	/**
	 * This method assigns integers to the nodes of the two trees depending on the no of children it has in accordance to other nodes on that level
	 * @param tree1
	 * 			This is the first tree
	 * @param tree2
	 * 			This is the second tree
	 * @param level
	 * 			The level at which the nodes are to be assigned the integers
	 * @return
	 * 			True if the two trees are isomorphic at the given level.
	 * 			False if the two trees are not isomorphic at the given level.
	 */
	public boolean assignIntegersToNodes(TreeGen tree1, TreeGen tree2, int level) {
		boolean isIsomorphic = false;
		ArrayList<ArrayList<Integer>> S1 = new ArrayList<ArrayList<Integer>>();
		ArrayList<ArrayList<Integer>> S2 = new ArrayList<ArrayList<Integer>>();
		for(int i=0;i<L1.size();i++){
			int integer = tree1.treeNodes[L1.get(i)].integer;
			int parentNode  = tree1.treeNodes[L1.get(i)].parent;
			tree1.treeNodes[parentNode].tuple.add(integer);
		}
		for(int i=0;i<L2.size();i++){
			int integer = tree2.treeNodes[L2.get(i)].integer;
			int parentNode = tree2.treeNodes[L2.get(i)].parent;
			tree2.treeNodes[parentNode].tuple.add(integer);
		}
		int noOfNodes = tree1.nodeListByLevel.get(level).size();
		for(int j=0;j<noOfNodes;j++){
			int nodeNumber = tree1.nodeListByLevel.get(level).get(j);
			if(!(tree1.treeNodes[nodeNumber].child.isEmpty())){
				ArrayList<Integer> temp = tree1.treeNodes[nodeNumber].tuple;
				Collections.sort(temp);
				S1.add(temp);
			}
		}
		noOfNodes = tree2.nodeListByLevel.get(level).size();
		for(int j=0;j<noOfNodes;j++){
			int nodeNumber = tree2.nodeListByLevel.get(level).get(j);
			if(!(tree2.treeNodes[nodeNumber].child.isEmpty())){
				ArrayList<Integer> temp = tree2.treeNodes[nodeNumber].tuple;
				Collections.sort(temp);
				S2.add(temp);
			}
		}
		boolean tempTest1 = false;
		boolean tempTest2 = false;
		
		//To check if S1 is equal to S2
		if(S1.size() == S2.size()){
			for(int i=0;i<S1.size();i++){
				if(!S2.contains(S1.get(i))){
					tempTest1 = false;
				}else{
					tempTest1 = true;
				}
			}
			for(int i=0;i<S2.size();i++){
				if(!S1.contains(S2.get(i))){
					tempTest2 = false;
				}else{
					tempTest2 = true;
				}
			}
			if(S1.isEmpty() && S2.isEmpty())
				tempTest1 = tempTest2 = true;
			
			if(tempTest1 && tempTest2){
				L1.clear();
				L2.clear();
				int distinctTuples = 0;
				Set<ArrayList<Integer>> S1Set = new HashSet<ArrayList<Integer>>(S1);
				distinctTuples = S1Set.size();
				ArrayList<ArrayList<Integer>> newS1 = new ArrayList<ArrayList<Integer>>(S1Set);
				noOfNodes = tree1.nodeListByLevel.get(level).size();
				//For tree1
				int k=1;
				for(int i=0; i<distinctTuples; i++){
					for(int j= 0;j<noOfNodes;j++){
						int nodeNumber = tree1.nodeListByLevel.get(level).get(j);
						if(!tree1.treeNodes[nodeNumber].child.isEmpty()){
							if((newS1.get(i).equals(tree1.treeNodes[nodeNumber].tuple))){
								tree1.treeNodes[nodeNumber].integer = k;
								L1.add(nodeNumber);
							}
						}
					}
					k++;
				}
				for(int i=0;i<noOfNodes;i++){
					int nodeNumber = tree1.nodeListByLevel.get(level).get(i);
					if(tree1.treeNodes[nodeNumber].child.isEmpty()){
						L1.add(nodeNumber);
					}
				}
				noOfNodes = tree2.nodeListByLevel.get(level).size();
				//For tree2
				k=1;
				for(int i=0; i<distinctTuples; i++){
					for(int j= 0;j<noOfNodes;j++){
						int nodeNumber = tree2.nodeListByLevel.get(level).get(j);
						if(!tree2.treeNodes[nodeNumber].child.isEmpty()){
							if(newS1.get(i).equals(tree2.treeNodes[nodeNumber].tuple)){
								tree2.treeNodes[nodeNumber].integer = k;
								L2.add(nodeNumber);
							}
						}
					}
					k++;
				}
				for(int i=0;i<noOfNodes;i++){
					int nodeNumber = tree2.nodeListByLevel.get(level).get(i);
					if(tree2.treeNodes[nodeNumber].child.isEmpty()){
						L2.add(nodeNumber);
					}
				}
				isIsomorphic = true;
			}else{
				isIsomorphic = false;
			}
		}else{
			isIsomorphic = false;
		}
		return isIsomorphic;
	}

	/**
	 * This method assigns 0 as an integer to all of the leaf nodes of the tree
	 * @param tree
	 * @return
	 * 		Returns an array list of all the leaf nodes at the last level only
	 */
	public ArrayList<Integer> assignZeroToLeaves(TreeGen tree){
		ArrayList<Integer> leavesAtLastLevel = new ArrayList<Integer>();
		int treeHeight = tree.nodeListByLevel.size();
		for(int i=treeHeight-1;i>0;i--){
			int noOfNodes = tree.nodeListByLevel.get(i).size();
			for(int j=0;j<noOfNodes;j++){
				int nodeNumber = tree.nodeListByLevel.get(i).get(j);
				if(tree.treeNodes[nodeNumber].child.isEmpty()){
					tree.treeNodes[nodeNumber].integer = 0;
					if(i == treeHeight-1){
						leavesAtLastLevel.add(nodeNumber);
					}
				}
			}
		}
		return leavesAtLastLevel;
	}

	public static void main(String[] args) {

		EdgeListExtractor edgeListExtractor = new EdgeListExtractor();
		ArrayList<String> edges = new ArrayList<String>();
		File file = new File("C:/Users/Rayyan/workspace/A and C 460 - HW7/src/Tree3.txt");
		edges = edgeListExtractor.extractEdges(file);
		TreeGen tree1 = new TreeGen(edges.size()+1);
		for(int i=0;i<edges.size();i++){
			tree1.tree.add(edges.get(i));
		}
		tree1.createNodes();
		edges.clear();
		file = new File("C:/Users/Rayyan/workspace/A and C 460 - HW7/src/Tree4.txt");
		edges = edgeListExtractor.extractEdges(file);
		TreeGen tree2 = new TreeGen(edges.size()+1);
		for(int i=0;i<edges.size();i++){
			tree2.tree.add(edges.get(i));
		}
		tree2.createNodes();
		TreeIsomorphism testIsomorphism = new TreeIsomorphism();
		boolean result = testIsomorphism.checkIsomorphism(tree1, tree2);
		if(result){
			System.out.println("Trees are isomorphic");
		}else{
			System.out.println("Trees are NOT isomorphic");
		}
	}
}
