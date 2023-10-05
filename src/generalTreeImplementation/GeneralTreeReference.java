package src.generalTreeImplementation;

import java.util.ArrayList;
import java.util.List;

class TreeNode {
    int data;
    int depth;
    List<TreeNode> children;

    public TreeNode(int data, int depth) {
      this.data = data;
      this.depth = depth;
      this.children = new ArrayList<>();
    }
    
}

public class GeneralTreeReference {
    private TreeNode root;

    public GeneralTreeReference(int rootData) {
        this.root = new TreeNode(rootData, 0);
    }

    public TreeNode getRoot() {
        return root;
    }

    public void addChild(TreeNode parent, int childData) {
      TreeNode child = new TreeNode(childData, parent.depth + 1);
      parent.children.add(child);
    }

    
    public void printAllData(TreeNode node) {
      String padding = "  ".repeat(node.depth) + "|__" ; // Create padding based on depth
      System.out.println(padding + " " + node.data);
      for (TreeNode child : node.children) {
        printAllData(child); // Recursively print children
      }
    }

    
    public static void main(String[] args) {
      GeneralTreeReference tree = new GeneralTreeReference(1);
      TreeNode root = tree.getRoot();

      tree.addChild(root, 1);
      tree.addChild(root, 3);

      TreeNode node2 = root.children.get(0);
      tree.addChild(node2, 4);
      tree.addChild(node2, 5);

      TreeNode node3 = root.children.get(1);
      tree.addChild(node3, 6);

      tree.printAllData(root);

      // testingTreeGenerationFunction();
    }
    

    // public static TreeNode generateTreeFromPairs(List<int[]> pairs) {
    //   TreeNode root = new TreeNode(pairs.get(0)[0]);

    //   // Create a mapping of nodes to their corresponding TreeNode objects
    //   List<TreeNode> nodes = new ArrayList<>();
    //   nodes.add(root);

    //   for (int i = 1; i < pairs.size(); i++) {
    //     int parentValue = pairs.get(i)[0];
    //     int childValue = pairs.get(i)[1];

    //     TreeNode parentNode = nodes.get(parentValue - 1); // -1 to account for 1-based indexing
    //     TreeNode childNode = new TreeNode(childValue);

    //     parentNode.children.add(childNode);
    //     nodes.add(childNode);
    //   }

    //   return root;
    // }

    // public static void printTree(TreeNode node, String prefix, boolean isTail) {
      
    //   System.out.println(prefix + (isTail ? "└── " : "├── ") + node.data);
    //   List<TreeNode> children = node.children;
     
    //   for (int i = 0; i < children.size() - 1; i++) {
    //     printTree(children.get(i), prefix + (isTail ? "    " : "│   "), false);
    //   }
     
    //   if (!children.isEmpty()) {
    //     printTree(children.get(children.size() - 1), prefix + (isTail ? "    " : "│   "), true);
    //   }

    // }

    // public static void testingTreeGenerationFunction() {
 
    //   List<int[]> pairs = new ArrayList<>();
    //   pairs.add(new int[] { 1, 2 });
    //   pairs.add(new int[] { 1, 3 });
    //   pairs.add(new int[] { 2, 4 });
    //   pairs.add(new int[] { 2, 5 });
    //   pairs.add(new int[] { 3, 6 });

    //   TreeNode root = generateTreeFromPairs(pairs);
    //   printTree(root, "", true);

    // }

}