/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package handler;

import connections.CommandConnection;
import connections.PriceConnection;
import data.EMA;
import data.LWMA;
import data.Prices;
import data.SMA;
import data.TMA;

/**
 *
 * @author headyin
 */
public class PriceHandler implements Runnable {
    
    private static final String[] COMMANDS = {null,"B","S"};
    
    private PriceConnection priceConnection;
    private Prices prices;
    private SMA sma;
    private LWMA lwma;
    private EMA ema;
    private TMA tma;
    private int currentTime;
    private CommandConnection commandConnection;
    
    
    public PriceHandler (PriceConnection priceConnection, CommandConnection commandConnection) {
        this.priceConnection = priceConnection;
        this.prices = new Prices();
        this.sma = new SMA(prices);
        this.lwma = new LWMA(prices);
        this.ema = new EMA(prices);
        this.tma = new TMA(prices,sma);
        this.commandConnection = commandConnection;
    }
    
    public Prices getPrices() {
        return this.prices;
    }

    @Override
    public void run() {
        priceConnection.connect();
        commandConnection.connect();
        priceConnection.startStockExchange();
        currentTime = 0;
        int priceValue = priceConnection.receivePrice();
        while (priceValue > 0) {
            currentTime++;
            prices.addPrice(currentTime, priceValue);
            sendCommand(sma.calcMovingAverage(),"SMA",sma.getCurrentTime());
            sendCommand(lwma.calcMovingAverage(),"LWMA",sma.getCurrentTime());
            sendCommand(ema.calcMovingAverage(),"EMA",sma.getCurrentTime());
            sendCommand(tma.calcMovingAverage(),"TMA",sma.getCurrentTime());
            priceValue = priceConnection.receivePrice();
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
