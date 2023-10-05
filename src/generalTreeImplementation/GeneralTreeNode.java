package src.generalTreeImplementation;

public class GeneralTreeNode {
  
  public GeneralTreeNode(int data) {
    dataInside = data;
  }

  
  public int dataInside = -1;
  
  public GeneralTreeNode[] children = {
    null,null,null
  };

}
