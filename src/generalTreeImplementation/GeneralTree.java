package src.generalTreeImplementation;


public class GeneralTree {
  
  public GeneralTree() {

    long timeStart = System.currentTimeMillis();

    GeneralTreeNode rootNode = new GeneralTreeNode(1);
  
    rootNode.children[0] = new GeneralTreeNode(2);
    rootNode.children[1] = new GeneralTreeNode(3);
    rootNode.children[2] = new GeneralTreeNode(4);

    rootNode.children[0] = new GeneralTreeNode(5);    
    rootNode.children[0] = new GeneralTreeNode(6);
    rootNode.children[0] = new GeneralTreeNode(7);

    long timeEnd = System.currentTimeMillis();

    System.out.println( timeEnd - timeStart);

  }

  public static void main(String[] args) {
    GeneralTree test = new GeneralTree();
  }
}
