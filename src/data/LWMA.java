/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package data;

/**
 *
 * @author headyin
 */
public class LWMA extends MovingAverage{

    public LWMA(Prices prices) {
        super.init(prices);
    }

    @Override
    public void calcMovingAverage() {
        this.currentTime = prices.getCurrentTime();
        int time = currentTime;
        long[] sumPrice = prices.getSumPrices();
        int price = prices.getCurrentTime();
        
        
    }
    
}
