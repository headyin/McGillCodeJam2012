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
    private int[] lwmsFast;
    private int[] lwmsSlow;
    private static final int FAST_PERIOD_SUM = FAST_PERIOD * (FAST_PERIOD + 1) / 2; 
    private static final int SLOW_PERIOD_SUM = SLOW_PERIOD * (SLOW_PERIOD + 1) / 2;
    
    public LWMA(Prices prices) {
        super.init(prices);
        lwmsFast = new int[Prices.TOTAL_TIME];
        lwmsSlow = new int[Prices.TOTAL_TIME];
    }

    @Override
    public int calcMovingAverage() {
        this.currentTime = prices.getCurrentTime();
        int time = currentTime;
        long[] sumPrice = prices.getSumPrices();
        int price = prices.getCurrentPrice();
        
        if (time <= FAST_PERIOD) {
            this.lwmsFast[time] = calcFrontLWMS(time, price, lwmsFast);
            this.maFast[time] = Math.round(this.lwmsFast[time] * 2.0f / (time + 1) / time);
        } else {
            this.lwmsFast[time] = calcLWMS(time, price, FAST_PERIOD, lwmsFast);
            this.maFast[time] = Math.round(this.lwmsFast[time] * 1.0f / FAST_PERIOD_SUM);
        }
        
        if (time <= SLOW_PERIOD) {
            this.lwmsSlow[time] = calcFrontLWMS(time, price, lwmsSlow);
            this.maSlow[time] = Math.round(lwmsSlow[time] * 2.0f / time / (time + 1));
        } else {
            this.lwmsSlow[time] = calcLWMS(time, price, SLOW_PERIOD, lwmsSlow);
            this.maSlow[time] = Math.round(lwmsSlow[time] * 1.0f / SLOW_PERIOD_SUM);
        }
        return isCrossOver();
        
    }
    
    private int calcLWMS(int time, int price, int period, int[] lwms) {
        int currentLWMS = lwms[time -1] - 
                getInt(prices.getSumPrice(time -1) - 
                        prices.getSumPrice(time -1 - period)); 
        currentLWMS += price * period;
        return currentLWMS;
    }
    
    private int calcFrontLWMS(int time, int price, int[] lwms) {
        int currentLWMS = lwms[time -1] + price * time;
        return currentLWMS;
    }
}
