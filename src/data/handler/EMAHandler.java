/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package data.handler;

import connections.data.TransactionCollector;
import data.EMA;
import data.Prices;

/**
 *
 * @author headyin
 */
public class EMAHandler extends StrategyHandler{
     private EMA ema;
     private int currentTime;
     
     public EMAHandler(String serverName, int serverPort, Prices prices, 
             TransactionCollector transactionCollector) {
        super.init(serverName, serverPort, prices,transactionCollector);
        this.ema = new EMA(prices);        
     }
     
     public EMA getEMA() {
         return this.ema;
     }
     
      @Override
    public void run() {
        this.commandConnection.connect();
        this.currentTime = 0;
        while (currentTime < Prices.TOTAL_TIME  - 1) {
            if (currentTime >= prices.getCurrentTime()) {
                continue;
            }
            currentTime++;
            sendCommand(ema.calcMovingAverage(currentTime),2,ema.getCurrentTime());
        }
        //System.out.println("EMA Handler thread ends");
        
    }
     
     
    
}
