package src.generalTreeImplementation;

public class ProgramTester {
  
  public static void main(String[] args) {
    

    // NOTE : DO NOT INCLUDE IN CODE PRESENTATION
    // test node data from problem
    // 1 2 1 3 1 4 3 5 3 6 3 7 nodes = 7

    // 1 2 1 3 1 4 3 5 3 6 3 7 3 8 nodes = 8
    
    // 1 2 1 3 1 4 3 5 3 6 3 7 3 8 2 9 nodes = 9 input data : 1 2 3 4 5 6 7 8 9
    
    // 1 2 1 3 1 4 3 5 3 6 3 7 3 8 2 9 2 10 nodes = 10 input data : 1 2 3 4 5 5 7 8
    
    // 9 10 result : total time (millis) : 4706 pairs : 45
    
    // 1 2 1 3 1 4 3 5 3 6 3 7 3 8 2 9 2 10 4 11 nodes = 11 input data : 1 2 3 4 5 6
    
    // 7 8 9 10 11 result : total time (millis) : 7005 pairs : 55
    
    // 1 2 1 3 1 4 3 5 3 6 3 7 3 8 2 9 2 10 4 11 5 12 nodes = 12 input data : 1 2 3
    // 4 5 6 7 8 9 10 11 12 

    // 1 2 1 3 1 4 3 5 3 6 3 7 3 8 2 9 2 10 4 11 5 12 2 13 nodes = 13 input data : 1
    // 2 3 4 5 6 7 8 9 10 11 12 13 
    // result:
    
    // 1 2 1 3 1 4 3 5 3 6 3 7 3 8 2 9 2 10 4 11 5 12 2 13 9 14 nodes = 14 input
    // data : 1 2 3 4 5 6 7 8 9 10 11 12 13 14 
    // result:
    
    
    // 1 2 1 3 1 4 3 5 3 6 3 7 3 8 2 9 2 10 4 11 5 12 2 13 9 14 7 15 nodes = 15
    // input data : 1 2 3 4 5 6 7 8 9 10 11 12 13 14 15 
    // result:
    
    // 1 2 1 3 1 4 3 5 3 6 3 7 3 8 2 9 2 10 4 11 5 12 2 13 9 14 7 15 8 16 nodes = 16
    // input data : 1 2 3 4 5 6 7 8 9 10 11 12 13 14 15 16 
    // result:

    // : TEST UPDATED
    // 1 2 1 3 1 4 3 5 3 6 3 7 3 8 2 9 2 10 4 11 5 12 2 13 9 14 7 15 8 16 9 17 nodes
    // = 17
    // input data : 1 2 3 4 5 6 7 8 9 10 11 12 13 14 15 16 17
    // result: total time (millis) : 17 pairs : 136 Total value : 51808

    // 1 2 1 3 1 4 3 5 3 6 3 7 3 8 2 9 2 10 4 11 5 12 2 13 9 14 7 15 8 16 9 17 10 18
    // nodes = 18
    // input data : 1 2 3 4 5 6 7 8 9 10 11 12 13 14 15 16 17 18
    // result: total time (millis) : 24 pairs : 153Total value : 66712

    String[][] allTestData = {
        new String[] {
          "1 2 1 3 1 4 3 5 3 6 3 7", 
          "1 2 3 4 5 6 7" 
        },
          new String[] {
          "1 2 1 3 1 4 3 5 3 6 3 7 3 8", 
          "1 2 3 4 5 6 7 8" 
        },
        new String[] {
          "1 2 1 3 1 4 3 5 3 6 3 7 3 8 4 9",
          "1 2 3 4 5 6 7 8 9" 
        },
        new String[] {
          "1 2 1 3 1 4 3 5 3 6 3 7 3 8 4 9 9 10",
          "1 2 3 4 5 6 7 8 9 10" 

        } 
    };

    for (String[] treeNodesAndQueryPair  :  allTestData){
      programTestCases(treeNodesAndQueryPair[0], treeNodesAndQueryPair[1]);
    }
  }

  public static void programTestCases(
      String treeNodeData,
      String query) {

    // construct a general tree using String from user
    GeneralTree generalTree = GeneralTree.treeCreation(treeNodeData);

    // create the string to represent the tree visually through text
    GeneralTree.prepareTreeStringRepresentation(generalTree.root);

    // present the tree to the user through console
    System.out.println( GeneralTree.getStringRepresentationOfTree() );
    ;

    GeneralTree.calculateFormulaResultWithShortestDistanceFromTwoNodes(generalTree, query, false);
    // treeStringRepresentation = "";
    GeneralTree.setGeneralTreeCreationString("");
  }

}
