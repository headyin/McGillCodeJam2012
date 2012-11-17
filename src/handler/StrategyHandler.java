/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package handler;

import connections.CommandConnection;
import data.EMA;
import data.LWMA;
import data.Prices;
import data.SMA;
import data.TMA;

/**
 *
 * @author headyin
 */
public class StrategyHandler implements Runnable{
    
    private static final String[] COMMANDS = {null,"B","S"};
    
    private SMA sma;
    private LWMA lwma;
    private EMA ema;
    private TMA tma;
    private CommandConnection commandConnection;
    private Prices prices;
    private int currentTime;
    
    public StrategyHandler(CommandConnection commandConnection, Prices prices) {
        this.prices = prices;
        this.sma = new SMA(prices);
        this.lwma = new LWMA(prices);
        this.ema = new EMA(prices);
        this.tma = new TMA(prices,sma);
        this.commandConnection = commandConnection;
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
            sendCommand(sma.calcMovingAverage(currentTime),"SMA",sma.getCurrentTime());
            sendCommand(lwma.calcMovingAverage(currentTime),"LWMA",sma.getCurrentTime());
            sendCommand(ema.calcMovingAverage(currentTime),"EMA",sma.getCurrentTime());
            sendCommand(tma.calcMovingAverage(currentTime),"TMA",sma.getCurrentTime());
        }
        
    }
    
    private void sendCommand(int commandNumber,String strategy, int time) {
        if (commandNumber != 0) {
            int excutedPrice = commandConnection.buyOrSell(COMMANDS[commandNumber]);
            System.out.println("Time: " + time + ", Strategy: " + strategy + 
                    ", Sending Price: " + prices.getPrice(time) + 
                    ", excuted price: " + excutedPrice);
        }
    }
    
}
