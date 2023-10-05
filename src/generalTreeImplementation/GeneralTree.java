package src.generalTreeImplementation;


public class GeneralTree {
  
  GeneralTreeNode rootNode;
  public GeneralTree() {

    long timeStart = System.currentTimeMillis();

    rootNode = new GeneralTreeNode(1);

    rootNode.children[0] = new GeneralTreeNode(2);
    rootNode.children[1] = new GeneralTreeNode(3);
    rootNode.children[2] = new GeneralTreeNode(4);

    rootNode.children[1].children[0] = new GeneralTreeNode(5);
    rootNode.children[1].children[1] = new GeneralTreeNode(6);
    rootNode.children[1].children[2] = new GeneralTreeNode(7);

    long timeEnd = System.currentTimeMillis();

    System.out.println(timeEnd - timeStart);

  }
  
  private static void checkNodeDistanceOfTwoNodes(GeneralTreeNode rootNode, int a, int b,boolean isProcessFinished) {

    if(rootNode == null || !isProcessFinished ){
      return;
    }
    System.out.println("VISITED : " + rootNode.dataInside);
    for (GeneralTreeNode childNode : rootNode.children) {
      
      if (childNode == null) { continue; }
      System.out.println("VISITED : " + childNode.dataInside);


      if (childNode.dataInside == a) {
        System.out.println("found start");
      } else if (childNode.dataInside == b) {
        System.out.println("found end ");
        checkNodeDistanceOfTwoNodes(childNode, a, b, false);
      }
      checkNodeDistanceOfTwoNodes(childNode, a, b, true);
    }
  }

  public static void main(String[] args) {
    GeneralTree test = new GeneralTree();
    checkNodeDistanceOfTwoNodes(test.rootNode, 3, 6,true);
  }
}
