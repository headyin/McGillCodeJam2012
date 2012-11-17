/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package data;

import connections.PriceConnection;

/**
 *
 * @author headyin
 */
public class PriceHandler implements Runnable {
    
    private PriceConnection priceConnection;
    private Prices prices;
    private SMA sma;
    private LWMA lwma;
    private EMA ema;
    private TMA tma;
    private int currentTime;
    
    public PriceHandler (PriceConnection priceConnection) {
        this.priceConnection = priceConnection;
        this.prices = new Prices();
        this.sma = new SMA(prices);
        this.lwma = new LWMA(prices);
        this.ema = new EMA(prices);
        this.tma = new TMA(prices,sma);
    }

    @Override
    public void run() {
        priceConnection.connect();
        priceConnection.startStockExchange();
        currentTime = 0;
        int priceValue = priceConnection.receivePrice();
        while (priceValue > 0) {
            currentTime++;
            prices.addPrice(currentTime, priceValue);
            sma.calcMovingAverage();
            lwma.calcMovingAverage();
            ema.calcMovingAverage();
            tma.calcMovingAverage();
            priceValue = priceConnection.receivePrice();
        }
    }
    
}
