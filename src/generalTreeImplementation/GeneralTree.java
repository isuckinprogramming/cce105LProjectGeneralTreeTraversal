package src.generalTreeImplementation;


public class GeneralTree {
  
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

    printNode(tree.rootNode,0);
    
    printNode(tree.rootNode.children[0],0);
    printNode(tree.rootNode.children[1],0);
    
    printNode(tree.rootNode.children[1].children[0],1);
    printNode(tree.rootNode.children[1].children[1],1);
    printNode(tree.rootNode.children[1].children[2],1);

    printNode(tree.rootNode.children[2],1);

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
      return;
    }
    
    nodesTraversed += (isStartFound) ? 1: 0; 
    String message = "VISITED : " + rootNode.dataInside;
    
    if (rootNode.dataInside == a) {
      isStartFound = true;
      message += " <-- : found start";
      traverseStartTime = System.currentTimeMillis();
    } 
    else if (rootNode.dataInside == b) {
      
      traverseEndTime = System.currentTimeMillis();
      message += " <-- found end : nodes traversed = " + nodesTraversed + " time in millis : " + (traverseEndTime - traverseStartTime); 
      
      // return the default values of the function variables
      
      nodesTraversed = 0;
      System.out.println(message);
      
      traverseEndTime = 0;
      traverseStartTime = 0;
      isStartFound = false;

      // end function -- RECURSION BASE CASEs
      return;
    }
    System.out.println( message );
    // traverse children if found no match between data
    for (GeneralTreeNode childNode : rootNode.children) {
      
      checkNodeDistanceOfTwoNodes(childNode, a, b);
    }
  }

  public static void main(String[] args) {
    GeneralTree test = new GeneralTree();

    checkNodeDistanceOfTwoNodes(test.rootNode, 3, 6);
    
    checkNodeDistanceOfTwoNodes(test.rootNode, 1, 6);

    checkNodeDistanceOfTwoNodes(test.rootNode, 2, 4);
  }
}
