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

    public TreeNodeForGeneralTreeCreation(int parentData, int insideDataForNode, int depth) {
        
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
    private TreeNodeForGeneralTreeCreation root;

    public GeneralTreeCreationReference(int rootData) {
        root = new TreeNodeForGeneralTreeCreation(-1, rootData, 0); // Assuming -1 as the parent of the root node
    }

    public void addNode(int parentData, int childData) {

      TreeNodeForGeneralTreeCreation parentNode = findNode(root, parentData);

      if (parentNode != null) {

        TreeNodeForGeneralTreeCreation childNode = new TreeNodeForGeneralTreeCreation( parentData, childData, parentNode.getDepth() + 1 );
        parentNode.addChild(childNode);

      } else {

        System.out.println("Parent not found. Cannot add child node.");
      }
    }

    /**
     * Use method to find a node that is located as a children within the root node.
     * @param node
     * @param data
     * @return
     */
    private TreeNodeForGeneralTreeCreation findNode(TreeNodeForGeneralTreeCreation node, int data) {

      if (node == null) {
        return null;
      }
      if (node.getNodeData() == data) {
        return node;
      }
      for (TreeNodeForGeneralTreeCreation child : node.getChildren()) {
        TreeNodeForGeneralTreeCreation found = findNode(child, data);
        if (found != null) {
          return found;
        }
      }
      return null;
    }

    
    
  public List<Integer> shortestPath(int targetData) {
    List<Integer> path = new ArrayList<>();
    
    // the rootnode will be used as the base for finding and searching the data
    // the starting node can be changed but I haven't tested how yet
    TreeNodeForGeneralTreeCreation targetNode = findNode(root, targetData);

    // TERMINATION CASE 
    if (targetNode == null) {

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
        
        
      if (currentNode == targetNode) {
        
        // Found the target node, construct the path
        TreeNodeForGeneralTreeCreation node = targetNode;
        
        while (node != null) {

          path.add(node.getNodeData());
          node = parentMap.get(node);
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

    public static void main(String[] args) {

      testingShortestPath();
    }
    
    public static void testingShortestPath() {

      GeneralTreeCreationReference tree = new GeneralTreeCreationReference(1); // Creating a tree with root data 1

      tree.addNode(1, 2); // Adding a child node with parent 1 and data 2

      tree.addNode(1, 3); // Adding a child node with parent 1 and data 3

      tree.addNode(2, 4); // Adding a child node with parent 2 and data 4

      tree.addNode(3, 5); // Adding a child node with parent 3 and data 5

      tree.addNode(3, 6);

      tree.addNode(3, 10); 

      tree.addNode(4, 6);

      tree.addNode(3, 11); 
      
      tree.addNode(3, 6);

      tree.addNode(3, 10);

      tree.addNode(10, 43);

      tree.addNode(11, 25);

      getStringRepresentation(tree.root);
      String input = JOptionPane
          .showInputDialog("GENERAL TREE VISUALIZATION\n" + message + "\nInput number to find path inside tree: ");

      int numToFind = Integer.parseInt(input);
      
      List<Integer> shortestPath = tree.shortestPath( numToFind );

      String outPutMessage = "Shortest Path to 5: " + shortestPath;
      outPutMessage += "\n" + message;
      JOptionPane.showMessageDialog(null, outPutMessage);
    }
  
  static String message = "";
  public static void getStringRepresentation(TreeNodeForGeneralTreeCreation node) {
    
    String padding = "  ".repeat( node.getDepth() ) + "|__"; // Create padding based on depth
    message += padding + node.getNodeData() + "\n";
   
    for (TreeNodeForGeneralTreeCreation child : node.getChildren()) {

      getStringRepresentation( child ); // Recursively concatenate message with the print data of the children of the node.
    }
    
  }
}