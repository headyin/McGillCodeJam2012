/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package data.handler;

import data.EMA;
import data.Prices;

/**
 *
 * @author headyin
 */
public class EMAHandler extends StrategyHandler{
     private EMA ema;
     private int currentTime;
     
     public EMAHandler(String serverName, int serverPort, Prices prices) {
        super.init(serverName, serverPort, prices);
        this.ema = new EMA(prices);        
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
            sendCommand(ema.calcMovingAverage(currentTime),"EMA",ema.getCurrentTime());
        }
        System.out.println("EMA Handler thread ends");
        
    }
     
     
    
}
