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
    private int currentTime;
    
    public PriceHandler (PriceConnection priceConnection) {
        this.priceConnection = priceConnection;
        this.prices = new Prices();
        this.sma = new SMA();
    }

    @Override
    public void run() {
        priceConnection.startStockExchange();
        currentTime = 0;
        int price = priceConnection.receivePrice();
        while (price > 0) {
            currentTime++;
            prices.addPrice(currentTime, price);
            sma.calcSMA(currentTime, price);
            price = priceConnection.receivePrice();
        }
    }
    
}
