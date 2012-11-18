/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package data.handler;


import connections.data.TransactionCollector;
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
    
    public SMAandTMAHandler (String serverName, int serverPort, Prices prices,
            TransactionCollector transactionCollector) {
        super.init(serverName, serverPort, prices,transactionCollector);
        this.sma = new SMA(prices);
        this.tma = new TMA(prices,sma);
    }
    
    public SMA getSMA() {
        return this.sma;
    }
    
    public TMA getTMA() {
        return this.tma;
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
            sendCommand(sma.calcMovingAverage(currentTime),0,sma.getCurrentTime());
            sendCommand(tma.calcMovingAverage(currentTime),3,tma.getCurrentTime());
            //System.out.println("Time>> " + currentTime);
        }
        this.commandConnection.close();
        //System.out.println("ST handler ends.");
        
    }
    
    
    
}
