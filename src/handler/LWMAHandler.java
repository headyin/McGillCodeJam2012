/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package handler;


import data.LWMA;
import data.Prices;

/**
 *
 * @author headyin
 */
public class LWMAHandler extends StrategyHandler{

    private LWMA lwma;
    private int currentTime;
    
    public LWMAHandler(String serverName, int serverPort, Prices prices) {
        super.init(serverName, serverPort, prices);
        this.lwma = new LWMA(prices);        
     }
    
     @Override
    public void run() {
        this.commandConnection.connect();
        this.currentTime = 0;
        while (currentTime < Prices.TOTAL_TIME) {
            if (currentTime >= prices.getCurrentTime()) {
                continue;
            }
            currentTime++;
            sendCommand(lwma.calcMovingAverage(currentTime),"LWMA",lwma.getCurrentTime());
       }
        
    }
    

}
