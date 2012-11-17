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
    
    private PriceConnection priceConnection;
    private Prices prices;    
    private int currentTime;    
    
    public PriceHandler (PriceConnection priceConnection, Prices prices) {
        this.priceConnection = priceConnection;
        this.prices = prices;        
    }
    
    public Prices getPrices() {
        return this.prices;
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
            priceValue = priceConnection.receivePrice();
        }
        
    }
    
    
    
}
