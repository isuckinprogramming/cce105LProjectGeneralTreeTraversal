package src.generalTreeImplementation;

import java.util.ArrayList;
import java.util.Vector;

import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class tableDisplayer {
  
  public static void displayAllDataFromTestToJTable( ArrayList<long[]> dataFromAllTests ) {

    JTable table = new JTable(buildTableModel(dataFromAllTests));

    table.setEnabled(true);

    JOptionPane.showMessageDialog(null, new JScrollPane(table));
  }


  public static DefaultTableModel buildTableModel(ArrayList<long[]> dataset) {

    // ResultSetMetaData metaData = rs.getMetaData();

    // names of columns
    Vector<String> columnNames = new Vector<String>();
    int columnCount = 4;

    
    columnNames.add("millis");
    
    columnNames.add("pairs");
    
    columnNames.add("result");
    
    columnNames.add("tree nodes");
    

    // data of the table
    Vector<Vector<Object>> data = new Vector<Vector<Object>>();
    
    for (long[] testData : dataset) {
      Vector<Object> rowDataFromTestData = new Vector<Object>();

      // for (int columnIndex = 1; columnIndex <= columnCount; columnIndex++) {
      //   vector.add(rs.getObject(columnIndex));
      // }

      rowDataFromTestData.add(testData[0]);
      
      rowDataFromTestData.add(testData[1]);
      
      rowDataFromTestData.add(testData[2]);

      rowDataFromTestData.add(testData[3]);
      
      data.add(rowDataFromTestData);
    }
    
    DefaultTableModel tableModel = new DefaultTableModel(data, columnNames);
    
    return tableModel;
  }

}

