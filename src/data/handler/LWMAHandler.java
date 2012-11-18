/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package data.handler;


import connections.data.TransactionCollector;
import data.LWMA;
import data.Prices;

/**
 *
 * @author headyin
 */
public class LWMAHandler extends StrategyHandler{

    private LWMA lwma;
    private int currentTime;
    
    public LWMAHandler(String serverName, int serverPort, Prices prices,
            TransactionCollector transactionCollector) {
        super.init(serverName, serverPort, prices,transactionCollector);
        this.lwma = new LWMA(prices);        
     }
    
    public LWMA getLWMA() {
        return this.lwma;
    }
    
     @Override
    public void run() {
        this.commandConnection.connect();
        this.currentTime = 0;
        while (currentTime < Prices.TOTAL_TIME - 1) {
            if (currentTime >= prices.getCurrentTime()) {
                continue;
            }
            currentTime++;
            sendCommand(lwma.calcMovingAverage(currentTime),1,lwma.getCurrentTime());
        }
        this.commandConnection.close();
        //System.out.println("LWMA Handler thread ends");

    }
    

}
