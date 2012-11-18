/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package connections.data;

import connections.FileConnection;
import java.io.BufferedWriter;
import java.io.IOException;
import java.util.ArrayList;

/**
 *
 * @author headyin
 */
public class TransactionCollector {
    private final static String EMAIL = "\"headyin@gmail.com\"";
    
    private ArrayList<Transaction> transactionQueue;
    FileConnection fileConnection;
    
    public TransactionCollector() {
        transactionQueue = new ArrayList<Transaction>();
    }
    
    public void addTransaction(Transaction transaction) {
        this.transactionQueue.add(transaction);
    }
    
    public void printTransactions() {
        int i;
        int l = this.transactionQueue.size();
        System.out.println("size: " + l);
        for (i = 0; i < l; i++) {
            Transaction t = this.transactionQueue.get(i);
            System.out.print(i + "->");
            System.out.print("time:" + t.getTime());
            System.out.print(", type: " + t.getType());
            System.out.print(", price: " + t.getPrice());
            System.out.print(", manager: " + t.getManager());
            System.out.println(", strategy: " + t.getStrategy());
        }       
    }
    
    public void saveToFile() {
        this.fileConnection = new FileConnection();
        this.writeOutTransactions(this.fileConnection.getBufferedWriter());
        
    }
    
    private void writeOutTransactions(BufferedWriter bw) {
        try {
            bw.write("{\n");
            bw.write("  \"team\" : " + "\"QDOIer\",\n");
            bw.write("  \"destination\" : " + EMAIL + ",\n");
            bw.write("  \"transactions\" : [ \n");
            int i;
            int l = this.transactionQueue.size();
            for (i = 0; i < l; i++) {
                Transaction t = this.transactionQueue.get(i);
                bw.write("    { \n");
                bw.write("    \"time\" : \"" + t.getTime() + "\",\n");
                bw.write("    \"type\" : \"" + t.getType() + "\",\n");
                bw.write("    \"price\" : " + t.getPrice() + ",\n");
                bw.write("    \"manager\" : \"" + t.getManager() + "\",\n");
                bw.write("    \"strategy\" : \"" + t.getStrategy() + "\",\n");
                bw.write("    {");
                if (i != l-1) {
                    bw.write(",\n");
                } else {
                    bw.write("\n");
                }
            }
            bw.write("  ]\n");
            bw.write("}\n");
            bw.flush();
            bw.close();            
        } catch (IOException e) {
            
        }
        
    }
    
    
    
}
