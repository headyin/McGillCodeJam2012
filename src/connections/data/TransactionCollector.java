/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package connections.data;

import java.util.ArrayList;

/**
 *
 * @author headyin
 */
public class TransactionCollector {
    
    private ArrayList<Transaction> transactionQueue;
    
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
    
    
}
