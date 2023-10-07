package src.generalTreeImplementation;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;

import javax.swing.JOptionPane;

class TreeNodeForGeneralTreeCreation {
    
    private int parentData;
    private int nodeData;
    private int depth;
    private List<TreeNodeForGeneralTreeCreation> children;

    public TreeNodeForGeneralTreeCreation(
      int parentData,
      int insideDataForNode,
      int depth ) {
        
      this.parentData = parentData;
      this.nodeData = insideDataForNode;
      this.depth = depth;
      this.children = new ArrayList<>();
    }
    public int getDepth(){
      return depth;
    }
    public int getParentData() {
        return parentData;
    }

    public int getNodeData() {
        return nodeData;
    }

    public void addChild(TreeNodeForGeneralTreeCreation child) {
        children.add(child);
    }

    public List<TreeNodeForGeneralTreeCreation> getChildren() {
        return children;
    }
}

public class GeneralTree {


  public static void main(String[] args) {

    programStart();
  }

  public static void setGeneralTreeCreationString(String defaultContent) {

    treeStringRepresentation = "";
  }

  public static String getStringRepresentationOfTree() {
    return treeStringRepresentation;
  }
 
  
// flow chart created
public static void programStart() {

  // get String from user, instructions for creating a general tree. 
  String numbersRaw = JOptionPane.showInputDialog("enter some numbers to convert into a general tree: ");

  // construct a general tree using String from user 
  GeneralTree generalTree = treeCreation(numbersRaw);

  // create the string to represent the tree visually through text
  prepareTreeStringRepresentation(generalTree.root);

  // present the tree to the user through console
  System.out.println(treeStringRepresentation);

  //Message to Ask user for Input, present instructions for user
  String message = "GENERAL TREE VISUALIZATION\n" + treeStringRepresentation +
      "\nInput numbers in pair to find their shortest distance inside tree. " +
      "\nThe first pair of numbers will be considered as the parent root node and the child node." +
      "\nExample : 1 2 = pair( parent 1, child 2 ) ; 1 2 1 3 = pair(parent 1, child 2), pair( parent 1, child 3) ";

  String userInputRawQueries = JOptionPane
      .showInputDialog(message);

  calculateFormulaResultWithShortestDistanceFromTwoNodes(generalTree, userInputRawQueries, true);
}

  // flow chart created
  public static GeneralTree treeCreation(String treeCreationString) {

    String[] numbersRawSeparated = treeCreationString.split(" ");

    // terminate function if the numbers inside is only one number 
    if (numbersRawSeparated.length <= 1) {
      return null;
    }

    int firstNumber = Integer.parseInt(numbersRawSeparated[0]);
    GeneralTree createdGeneralTree = new GeneralTree(firstNumber);
    int secondNumber = Integer.parseInt(numbersRawSeparated[1]);

    createdGeneralTree.addNode(firstNumber, secondNumber);

    for (int index = 2; index < numbersRawSeparated.length; index = index + 2) {

      createdGeneralTree.addNode(
          Integer.parseInt(numbersRawSeparated[index]),
          Integer.parseInt(numbersRawSeparated[index + 1]));
    }

    return createdGeneralTree;
  }

  // FLOWCHART CREATED
  public static ArrayList<int[]> createUnorderedPairsOfIntNumbersFromString(String userInput) {
    
    String[] userInputRaw = userInput.split(" ");

    if (userInputRaw.length < 2) {
      
      // terminate this function because the string the user inputed is only
      // equivalent to one number, there must be 2 or more number to qualify as a query 
      return null;  
    }

    ArrayList<int[]> pairsOfQueries = new ArrayList<>();


    int arryLength = userInputRaw.length;
    boolean isConvertionFinish = false;

    int firstNumStartIndex = 0;
    int firstNumIndex = firstNumStartIndex;
    int secondNumIndex = 1;
    
    while (!isConvertionFinish) {

        // loop should end when the firstNumIndex reaches or exceeds arryLength
      if (firstNumIndex >= arryLength) { break; }

      // update the first index value
      if (secondNumIndex >= arryLength) {
        firstNumIndex++;
        secondNumIndex = firstNumIndex + 1;
        continue;
      }

      int parent = Integer.parseInt(userInputRaw[firstNumIndex]);
      int child = Integer.parseInt(userInputRaw[secondNumIndex]);
      pairsOfQueries.add(
        new int[] {
          parent,
          child
          });

      secondNumIndex++;
      
      if(arryLength <= 2){
        isConvertionFinish = true;
      }
    }
    
    return pairsOfQueries;
  }
    



  public static long[] calculateFormulaResultWithShortestDistanceFromTwoNodes(
      GeneralTree tree,
      String userInputRawQueries,
      boolean printConsoleData) {

    ArrayList<int[]> unorderedPairQuery = createUnorderedPairsOfIntNumbersFromString(userInputRawQueries);

    // program time calculation and test cases variables
     long totaltime = 0;
    int numOfTotalPairs = unorderedPairQuery.size();
    long totalResult = 0;
    long totalResultFromNodeMultiplication = 0;
    String consoleCalculationProcessMessage = "";

    for (int[] query : unorderedPairQuery) {

      long startTime = System.currentTimeMillis();

      int distance = tree.shortestDistance(query[0], query[1]);
      
      long allNodesMultiplied = query[0] * query[1] * distance;
      totalResultFromNodeMultiplication += allNodesMultiplied;

      long expressionResult =  allNodesMultiplied % 1000000007;
      
      totalResult += expressionResult;
      // concatenate output message

      if (printConsoleData) {
        consoleCalculationProcessMessage += 
         "Shortest Distance of : " + query[0] + " and " + query[1] +
          " is " + distance + " : Expression Result ( (" + query[0] + " * " + query[1] + " * " + distance
          + " ) % 1,000,000,007( or 10^9 + 7) ) = " + expressionResult + "\n";
      
      }
      long endTime = System.currentTimeMillis();
      totaltime += endTime - startTime;
      
    }
    
    String guiProcessMainDetailsMessage = " THE TOTAL VALUE FROM PROBLEM CALCULATED THROUGH FORMULA OF ( (node Start * node End * shortest distance ) % 10^9 + 7 ) : " + totalResultFromNodeMultiplication % 1000000007 + "\n" + treeStringRepresentation;
    JOptionPane.showMessageDialog(null, guiProcessMainDetailsMessage);
    
    consoleCalculationProcessMessage += 
      "total time (millis) : " + totaltime +
      "  pairs : " + numOfTotalPairs +
      " Total value : " + totalResultFromNodeMultiplication % 1000000007;
      
    System.out.println(consoleCalculationProcessMessage);
    
    
    return new long[] { totaltime, numOfTotalPairs, totalResultFromNodeMultiplication % ( 1000000000 + 7 )  };
  }



  public TreeNodeForGeneralTreeCreation root;

    public GeneralTree(int rootData) {
        root = new TreeNodeForGeneralTreeCreation(-1, rootData, 0); // Assuming -1 as the parent of the root node
    }

    public void addNode(int parentData, int childData) {

      // get reference to the node that will receive the new child node
      TreeNodeForGeneralTreeCreation parentNode = findNode(root, parentData);

      if (parentNode != null) {

        TreeNodeForGeneralTreeCreation childNode = new TreeNodeForGeneralTreeCreation(
          parentData,
          childData,
          parentNode.getDepth() + 1
        );

        parentNode.addChild(childNode);

      } else {

        System.out.println("Parent not found. Cannot add child node.");
      }
    }

    
    /**
     * Use method to find a node that is located as a children within the root node.
     * @param nodeToSearchWithin node to conduct search inside.
     * @param data data contained by the node to search for within the node to search within
     * @return Reference to the node with a data inside equal to the data param.
     */
    private TreeNodeForGeneralTreeCreation findNode(TreeNodeForGeneralTreeCreation nodeToSearchWithin, int data) {

      if (nodeToSearchWithin == null) {
        return null;
      }
      
      if (nodeToSearchWithin.getNodeData() == data) {
        return nodeToSearchWithin;
      }

      for (TreeNodeForGeneralTreeCreation child : nodeToSearchWithin.getChildren()) {
        
        TreeNodeForGeneralTreeCreation childNodeWithMatchingData = findNode(child, data);
        
        if (childNodeWithMatchingData != null) {
          return childNodeWithMatchingData;
        }
      }
      
      return null;

    }

    
      
      public List<Integer> shortestPathFromRooT(int targetData) {
        List<Integer> path = new ArrayList<>();

        // the rootnode will be used as the base for finding and searching the data
        // the starting node can be changed but I haven't tested how yet
        TreeNodeForGeneralTreeCreation refToTargetDestinationNode = findNode(root, targetData);

        // TERMINATION CASE 
        if (refToTargetDestinationNode == null) {

          return path; // Target node not found in the tree
        }

        Queue<TreeNodeForGeneralTreeCreation> queue = new LinkedList<>();

        // map reduces the code needed for look up and finding values 
        Map<TreeNodeForGeneralTreeCreation, TreeNodeForGeneralTreeCreation> parentMap = new HashMap<>();

        queue.offer(root);
        parentMap.put(root, null);

        // continue loop as long as queue has contents
        while (!queue.isEmpty()) {

          TreeNodeForGeneralTreeCreation currentNode = queue.poll();

          if (currentNode == refToTargetDestinationNode) {

            // Found the target node, construct the path
            TreeNodeForGeneralTreeCreation reassignedTargetNode = refToTargetDestinationNode;

            while (reassignedTargetNode != null) {

              path.add(reassignedTargetNode.getNodeData());
              reassignedTargetNode = parentMap.get(reassignedTargetNode);
            }

            Collections.reverse(path); // Reverse the path to get root-to-target order

            return path;
          }

          for (TreeNodeForGeneralTreeCreation child : currentNode.getChildren()) {

            if (!parentMap.containsKey(child)) {
              queue.offer(child);
              parentMap.put(child, currentNode);
            }
          }
        }

        return path; // Target node is not reachable from the root
      }


    // flowchart created
  public int shortestDistance(int nodeData1, int nodeData2) {

    List<Integer> path1 = shortestPathFromRooT(nodeData1);
    List<Integer> path2 = shortestPathFromRooT(nodeData2);

    if (path1.isEmpty() || path2.isEmpty()) {
      return -1; // One or both nodes are not reachable from the root
    }

    // Find the lowest common ancestor between the two nodes

    int commonAncestorIndex = 0;
    for (int i = 0; i < Math.min(path1.size(), path2.size()); i++) {

      Integer path1num = path1.get(i);
      Integer path2num = path2.get(i);

      // stop the loop increasing index of the common ancestor closest to 
      // both nodes in the function for trying to find the shortest distance between them 
      if (!path1num.equals(path2num)) {
        break;
      }

      commonAncestorIndex++;
    }
  
    // Calculate the distance by summing the lengths of both paths minus the index of their common ancestor
    int distance = (path1.size() - commonAncestorIndex) + (path2.size() - commonAncestorIndex);

    return distance;
  }

    
  public static String treeStringRepresentation = "";

  /**
   * This method cannot return a String that represents the 
   * tree. To access the string representation of the tree. 
   * Access the variable String tree representation after calling 
   * this method. 
   * <br></br>
   * The method uses recursion which is why the return statement 
   * cannot be called to return a string value that represents the 
   * string representation of the tree or the node.
   * 
   * @param node Starting node to create a string visualization for the tree 
  */ 
  public static void prepareTreeStringRepresentation(TreeNodeForGeneralTreeCreation node) {


    String padding = "  ".repeat(node.getDepth()) + "|__"; // Create padding based on depth
    treeStringRepresentation += padding + node.getNodeData() + "\n";

    for (TreeNodeForGeneralTreeCreation child : node.getChildren()) {

      prepareTreeStringRepresentation(child); // Recursively concatenate message with the print data of the children of the node.
    }
    // BASE CASE - Recursion stops when there are no more child nodes to access and call upon this method 
  }
  

  // this fucntion does not work
  public static int[][] convertToUnorderedPairs(int[] numbers) {
    List<int[]> pairsList = new ArrayList<>();

    for (int firstNumIndex = 0; firstNumIndex < numbers.length; firstNumIndex = firstNumIndex + 2) {

      int[] pair = { numbers[firstNumIndex], numbers[firstNumIndex + 1] };
      pairsList.add(pair);

    }

    int[][] pairsArray = new int[pairsList.size()][2];
    pairsList.toArray(pairsArray);

    return pairsArray;
  }
}