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
    private int currentTime;
    
    public PriceHandler (PriceConnection priceConnection) {
        this.priceConnection = priceConnection;
        this.prices = new Prices();
    }

    @Override
    public void run() {
        priceConnection.startStockExchange();
        currentTime = 0;
        int price = priceConnection.receivePrice();
        while (price > 0) {
            currentTime++;
            prices.addPrice(currentTime, price);
            System.out.println(currentTime + ": " + prices.getCurrentPriceString());
            price = priceConnection.receivePrice();
        }
    }
    
}
