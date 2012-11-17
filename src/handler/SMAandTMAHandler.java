/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package handler;


import data.Prices;
import data.SMA;
import data.TMA;


/**
 *
 * @author headyin
 */
public class SMAandTMAHandler extends StrategyHandler {
    private SMA sma;
    private TMA tma;
    private int currentTime;
    
    public SMAandTMAHandler (String serverName, int serverPort, Prices prices) {
        super.init(serverName, serverPort, prices);
        this.sma = new SMA(prices);
        this.tma = new TMA(prices,sma);
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
            sendCommand(sma.calcMovingAverage(currentTime),"SMA",sma.getCurrentTime());
            sendCommand(tma.calcMovingAverage(currentTime),"TMA",tma.getCurrentTime());
            //System.out.println("Time>> " + currentTime);
        }
        System.out.println("ST handler ends.");
        
    }
    
    
    
}
