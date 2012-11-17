/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package handler;

import connections.CommandConnection;
import data.Prices;


/**
 *
 * @author headyin
 */
public abstract class StrategyHandler implements Runnable{
    
    protected static final String[] COMMANDS = {null,"B","S"};
    
    

    

    protected CommandConnection commandConnection;
    protected Prices prices;
    
    public void init (String serverName, int serverPort, Prices prices) {
        this.prices = prices; 
        this.commandConnection = new CommandConnection(serverName, serverPort);
    }
    
    protected void sendCommand(int commandNumber,String strategy, int time) {
        if (commandNumber != 0) {
            int excutedPrice = commandConnection.buyOrSell(COMMANDS[commandNumber]);
            System.out.println("Time: " + time + ", Strategy: " + strategy + 
                    ", Sending Price: " + prices.getPrice(time) + 
                    ", excuted price: " + excutedPrice);
        }
    }
    
}
