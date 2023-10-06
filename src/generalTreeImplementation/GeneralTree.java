package src.generalTreeImplementation;

import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

public class GeneralTree {

  public static void main(String[] args) {
    GeneralTree test = new GeneralTree();

    // checkNodeDistanceOfTwoNodes(test.rootNode, 3, 6);

    // checkNodeDistanceOfTwoNodes(test.rootNode, 1, 6);

    // checkNodeDistanceOfTwoNodes(test.rootNode, 2, 4);
  
    testingUserInputConverter();
  }

  GeneralTreeNode rootNode;
  public GeneralTree() {

    long timeStart = System.currentTimeMillis();

    // NODE CREATION - RECREATION OF THE GENERAL TREE FROM THE PROBLEM 
    rootNode = new GeneralTreeNode(1, 0);

    rootNode.children[0] = new GeneralTreeNode(2, 1);
    rootNode.children[1] = new GeneralTreeNode(3, 1);
    rootNode.children[2] = new GeneralTreeNode(4, 1);

    rootNode.children[1].children[0] = new GeneralTreeNode(5, 2);
    rootNode.children[1].children[1] = new GeneralTreeNode(6, 2);
    rootNode.children[1].children[2] = new GeneralTreeNode(7, 2);

    long timeEnd = System.currentTimeMillis();
    long creationTime = timeEnd - timeStart;

    System.out.println("General tree creation time: (Millis) " + creationTime);

  }
  
  // this function will not work. kind of sucks
  private static void printOutTree(GeneralTree tree) {

    printNode(tree.rootNode, 0);

    printNode(tree.rootNode.children[0], 0);
    printNode(tree.rootNode.children[1], 0);

    printNode(tree.rootNode.children[1].children[0], 1);
    printNode(tree.rootNode.children[1].children[1], 1);
    printNode(tree.rootNode.children[1].children[2], 1);

    printNode(tree.rootNode.children[2], 1);

  }
  

  public void printAllData(TreeNodeForGeneralTreeCreation node) {
    String padding = "  ".repeat(node.depth) + "|__"; // Create padding based on depth
    System.out.println(padding + " " + node.data);
    for (TreeNodeForGeneralTreeCreation child : node.children) {
      printAllData(child); // Recursively print children
    }
  }
  
  private static void printNode(GeneralTreeNode node, int heightStartPadding) {
    String padding = "";
    String prePadding = "|___";
    for (int i = 1; i < node.height; i++) {
      padding += (i < heightStartPadding) ? "   " : "___";
    }

    System.out.println(padding + node.dataInside);
  }

  // variables used in checking distance of two nodes 
  private static int nodesTraversed = 0;
  private static boolean isStartFound = false;
  private static long traverseStartTime = 0;
  private static long traverseEndTime = 0;
  private static int traversalLevel = 0;
 /*  
 TODO : identify what is the current shortest distance between two nodes,
 1. if those nodes are parent-child - can be achieved by subtracting the height of the parent with the child
 2. if those nodes belong to the same or 
    same/different level with similar/different
    parents and/or sharing ancestors - I Don't Know how to do this part
 */
    
  /***
   * Traversing the tree in an in-order fashion, starting from 
   * the first child of the children node.
   * 
   * @param rootNode node to check for value, children of this node will be traversed for 
   * finding the values of the start and the end.
   * @param a number of the starting node.
   * @param b number of the end node.
  */ 
  private static void checkNodeDistanceOfTwoNodes(GeneralTreeNode rootNode, int a, int b) {

    if (rootNode == null) {
      traversalLevel--;
      return;
    }
    
    nodesTraversed += (isStartFound) ? 1: 0; 
    String message = "VISITED : " + rootNode.dataInside + " traversal level : " + traversalLevel;
    
    if (rootNode.dataInside == a) {
      isStartFound = true;
      message += " <-- : found start";
      traverseStartTime = System.currentTimeMillis();
    } 
    else if (rootNode.dataInside == b) {
      
      traverseEndTime = System.currentTimeMillis();
      message += " <-- found end : nodes traversed = " + nodesTraversed +
      " time in millis : " + (traverseEndTime - traverseStartTime); 
      
      // return the default values of the function variables
      
      nodesTraversed = 0;
      System.out.println(message);
      
      traverseEndTime = 0;
      traverseStartTime = 0;
      isStartFound = false;
      traversalLevel = 0;
      // end function -- RECURSION BASE CASEs
      return;
    }
    System.out.println( message );
    // traverse children if found no match between data
    for (GeneralTreeNode childNode : rootNode.children) {
      traversalLevel++;
      checkNodeDistanceOfTwoNodes(childNode, a, b);
    }
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
      ArrayList<Integer> numbersOutput = new ArrayList<>(); 
      for (int index = 0; index < numbersRawSeparated.length; index = index + 2) {
        
         ( Integer.parseInt(numbersRawSeparated[0]) , Integer.parseInt(numbersRawSeparated[1]) )


      }


      int[] inputArray = { 1, 2, 2, 3 ,3 ,5 };
        int[][] pairs = convertToUnorderedPairs(inputArray);

        for (int[] pair : pairs) {
            System.out.println("{" + pair[0] + ", " + pair[1] + "}");
        }
    }

}
