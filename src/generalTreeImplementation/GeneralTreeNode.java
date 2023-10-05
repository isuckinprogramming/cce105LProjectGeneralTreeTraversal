package src.generalTreeImplementation;

public class GeneralTreeNode {
  
  public GeneralTreeNode(int data, int height) {
    dataInside = data;
    this.height = height; 
  }

  public int height = 0;
  public int dataInside = -1;
  
  public GeneralTreeNode[] children = {
    null,null,null
  };

}
