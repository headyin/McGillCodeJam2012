/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package connections.data;

import connections.FileConnection;
import connections.HttpPostConnection;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author headyin
 */
public class TransactionCollector {
    private final static String EMAIL = "\"headyin@gmail.com\"";
    
    public ArrayList<Transaction> transactionQueue;
    FileConnection fileConnection;
    HttpPostConnection httpPostConnection;
    
    public TransactionCollector() {
        transactionQueue = new ArrayList<>();
    }
    
    public void addTransaction(Transaction transaction) {
        this.transactionQueue.add(transaction);
    }
    
    public void addTransactions(TransactionCollector tc) {
        int i;
        int l = tc.transactionQueue.size();
        for (i = 0; i < l; i++) {
            Transaction t = tc.transactionQueue.get(i);
            this.addTransaction(t);
        }
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
        this.writeOutTransactions(new URLEncodedBufferedWriter(
                this.fileConnection.getBufferedWriter()));
        try {
            this.fileConnection.getBufferedWriter().close();
        } catch (IOException ex) {
            //Logger.getLogger(TransactionCollector.class.getName()).log(Level.SEVERE, null, ex);
        }
        this.fileConnection.closeFile();
    }
    
    public String postToServer() {
        this.httpPostConnection = new HttpPostConnection();
        this.writeOutTransactions(new URLEncodedBufferedWriter(
                this.httpPostConnection.getBufferedWriter()));
        BufferedReader br = this.httpPostConnection.getBufferedReader();
        String respone = null;
        try {
            respone = br.readLine();
            br.close();
            this.httpPostConnection.getBufferedWriter().close();
        } catch (IOException ex) {
//            Logger.getLogger(TransactionCollector.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        this.httpPostConnection.disconnect();
        return respone;
    }
    
    private void writeOutTransactions(URLEncodedBufferedWriter bw) {
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
                bw.write("    }");
                if (i != l-1) {
                    bw.write(",\n");
                } else {
                    bw.write("\n");
                }
            }
            bw.write("  ]\n");
            bw.write("}\n");
            bw.flush();
        } catch (IOException e) {
            
        }
        
    }
    
    
    
}
