/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package data;

/**
 *
 * @author headyin
 */
public class EMA extends MovingAverage{
    
    private final static float alphaFast = 2.0f / (FAST_PERIOD + 1);
    private final static float alphaSlow = 2.0f / (SLOW_PERIOD + 1);
    
    public EMA(Prices prices) {
        super.init(prices);
    }
    
    @Override
    public int calcMovingAverage() {
        this.currentTime = prices.getCurrentTime();
        int time = currentTime;
        long[] sumPrice = prices.getSumPrices();
        int price = prices.getCurrentPrice();
        if (time > 1) {
           maFast[time] = maFast[time - 1] + 
                Math.round(alphaFast * (price - maFast[time - 1]));
            maSlow[time] = maSlow[time - 1] +
                Math.round(alphaSlow * (price - maSlow[time - 1]));
        } else {
            maFast[time] = price;
            maSlow[time] = price;
        }
        
        return isCrossOver();
    }
    
}
