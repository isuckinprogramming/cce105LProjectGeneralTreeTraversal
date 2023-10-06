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
    private TreeNodeForGeneralTreeCreation refToParent;
    private List<TreeNodeForGeneralTreeCreation> children;

    public TreeNodeForGeneralTreeCreation(
      int parentData,
      int insideDataForNode,
      int depth,
      TreeNodeForGeneralTreeCreation parentRef ) {
        
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

public class GeneralTreeCreationReference {


  public static void main(String[] args) {

    // testingShortestPath();
    testingUserInputConverter();
  }
  
  public static void proceedWithShortestPathBetweenTwoNodesProgramPrompt( GeneralTreeCreationReference tree) {

    // getStringRepresentation(tree.root);

    String input = JOptionPane
        .showInputDialog("GENERAL TREE VISUALIZATION\n" + stringRepresentingTree + "\nInput number to find path inside tree: ");

    String[] numbersRaw = input.split(" ");

    int[] numToFind = { Integer.parseInt(numbersRaw[0]), Integer.parseInt(numbersRaw[1]) };

    String outPutMessage = 
      "Shortest Path from : " + numToFind[0] + " and " + numToFind[1] + " is "
      + tree.shortestDistance(numToFind[0], numToFind[1])
      + "\n" + stringRepresentingTree;
      
    JOptionPane.showMessageDialog(null, outPutMessage);
  }



  private TreeNodeForGeneralTreeCreation root;

    public GeneralTreeCreationReference(int rootData) {
        root = new TreeNodeForGeneralTreeCreation(-1, rootData, 0, null); // Assuming -1 as the parent of the root node
    }

    public void addNode(int parentData, int childData) {

      // get reference to the node that will receive the new child node
      TreeNodeForGeneralTreeCreation parentNode = findNode(root, parentData);

      if (parentNode != null) {

        TreeNodeForGeneralTreeCreation childNode = new TreeNodeForGeneralTreeCreation(
          parentData,
          childData,
          parentNode.getDepth() + 1,
          parentNode
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
        TreeNodeForGeneralTreeCreation found = findNode(child, data);
        if (found != null) {
          return found;
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

    // will not work - tried to create a custom of the code generated from chat gpt but I fucked up because I don't 
    // know how the code operates under the hood
    public List<Integer> shortestPathBetweenTwoNodes(int startPoint, int endPoint) {
    
    List<Integer> path = new ArrayList<>();
    
    // the rootnode will be used as the base for finding and searching the data
    // the starting node can be changed but I haven't tested how yet
    TreeNodeForGeneralTreeCreation refToTargetDestinationNode = findNode(root, endPoint);
    
    TreeNodeForGeneralTreeCreation refToStartNode = findNode(root, startPoint);
    
    // TERMINATION CASE 
    if (refToTargetDestinationNode == null || refToStartNode == null) {

      return path; // Target node not found in the tree
    }

    Queue<TreeNodeForGeneralTreeCreation> queue = new LinkedList<>();
    
    // map reduces the code needed for look up and finding values 
    Map<TreeNodeForGeneralTreeCreation, TreeNodeForGeneralTreeCreation> parentMap = new HashMap<>();

    queue.offer(refToStartNode);
    parentMap.put(refToStartNode, null);

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
        
        if ( !parentMap.containsKey(child) ) {
          queue.offer(child);
          parentMap.put(child, currentNode);
        }
      }
    }

    return path; // Target node is not reachable from the root
  }

  public int shortestDistance(int nodeData1, int nodeData2) {

    TreeNodeForGeneralTreeCreation node1 = findNode(root, nodeData1);
    TreeNodeForGeneralTreeCreation node2 = findNode(root, nodeData2);

    if (node1 == null || node2 == null) {
      return -1; // One or both nodes not found in the tree
    }

    List<Integer> path1 = shortestPathFromRooT(nodeData1);
    List<Integer> path2 = shortestPathFromRooT(nodeData2);

    if (path1.isEmpty() || path2.isEmpty()) {
      return -1; // One or both nodes are not reachable from the root
    }

    // Find the lowest common ancestor between the two nodes
    int commonAncestor = -1;
    for (int i = 0; i < Math.min(path1.size(), path2.size()); i++) {
      
      Integer path1num = path1.get(i);
      Integer path2num = path2.get(i);
      
      if (!path1num.equals(path2num)) {
        break;
      }
      
      commonAncestor = path1.get(i);
    }

    // Calculate the distance by summing the lengths of both paths minus 2
    // (subtract 2 to remove the common ancestor and avoid double counting)
    int distance = (path1.size() - 1) + (path2.size() - 1);

    return distance;
  }



  public static void addTestNodesInsideTree(GeneralTreeCreationReference tree) {
  
      tree.addNode(1, 2); 

      tree.addNode(1, 3); 

      tree.addNode(1, 4); 

      tree.addNode(3, 5); 

      tree.addNode(3, 6);

      tree.addNode(3, 7);

  }



  
    
  static String stringRepresentingTree = "";

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
  public static void getStringRepresentation(TreeNodeForGeneralTreeCreation node) {

    // whoooppseeeieiii 
    // stringRepresentingTree = ""; WRONG CODE HERE HAHAHAHAHAHA

    String padding = "  ".repeat(node.getDepth()) + "|__"; // Create padding based on depth
    stringRepresentingTree += padding + node.getNodeData() + "\n";

    for (TreeNodeForGeneralTreeCreation child : node.getChildren()) {

      getStringRepresentation(child); // Recursively concatenate message with the print data of the children of the node.
    }
    // BASE CASE - Recursion stops when there are no more child nodes to access and call upon this method 
  }
  

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

  public static void testingUserInputConverter() {
       
      String numbersRaw = JOptionPane.showInputDialog("enter some numbers to convert into set: 1");

      String[] numbersRawSeparated = numbersRaw.split(" ");
      
      GeneralTreeCreationReference testTree;

      int firstNumber = Integer.parseInt(numbersRawSeparated[0]);
      testTree = new GeneralTreeCreationReference(firstNumber);
      int secondNumber = Integer.parseInt(numbersRawSeparated[1]);

      testTree.addNode(firstNumber, secondNumber);

      for (int index = 2; index < numbersRawSeparated.length; index = index + 2) {

        testTree.addNode(
            Integer.parseInt(numbersRawSeparated[index]),
            Integer.parseInt(numbersRawSeparated[index + 1]));
      }

      getStringRepresentation(testTree.root);
      System.out.println(stringRepresentingTree);
      
      proceedWithShortestPathBetweenTwoNodesProgramPrompt(testTree);
      
    }

}