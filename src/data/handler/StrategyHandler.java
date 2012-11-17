/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package data.handler;

import connections.CommandConnection;
import connections.data.Transaction;
import connections.data.TransactionCollector;
import data.Prices;


/**
 *
 * @author headyin
 */
public abstract class StrategyHandler implements Runnable{
    
    protected static final String[] COMMANDS = {null,"B","S"};
    
    

    

    protected CommandConnection commandConnection;
    protected Prices prices;
    protected TransactionCollector transactionCollector;
    
    public void init (String serverName, int serverPort, Prices prices, 
            TransactionCollector transactionCollector) {
        this.prices = prices; 
        this.commandConnection = new CommandConnection(serverName, serverPort);
        this.transactionCollector = transactionCollector;
    }
    
    protected void sendCommand(int commandNumber,int strategy, int time) {
        if (commandNumber != 0) {
            int executedPrice = commandConnection.buyOrSell(COMMANDS[commandNumber]);
            if (executedPrice != -1) {
                Transaction t = new Transaction(time,commandNumber,executedPrice,1,strategy);
                this.transactionCollector.addTransaction(t);
            }
                
        }
            /*System.out.println("Time: " + time + ", Strategy: " + strategy + 
                    ", Sending Price: " + prices.getPrice(time) + 
                    ", excuted price: " + executedPrice);*/
    }
    
}
