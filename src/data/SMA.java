/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package data;

/**
 *
 * @author headyin
 */
public class SMA extends MovingAverage{
    
    public SMA(Prices prices) {
        super.init(prices);
    }

    @Override
    public void calcMovingAverage() {
        this.currentTime = prices.getCurrentTime();
        int time = currentTime;
        long[] sumPrice = prices.getSumPrices();
        int price = prices.getCurrentPrice();
        
        if (time < FAST_PERIOD) {
            maFast[time] = getInt(sumPrice[time] - sumPrice[0]);
            maFast[time] = Math.round(maFast[time] * 1.0f / time);
            maSlow[time] = getInt(sumPrice[time] - sumPrice[0]);
            maSlow[time] = Math.round(maSlow[time] * 1.0f / time);
        } else {
            maFast[time] = getInt(sumPrice[time] - sumPrice[time - FAST_PERIOD]);
            maFast[time] = Math.round(maFast[time] * 1.0f / FAST_PERIOD);
            if (time < SLOW_PERIOD) {
                maSlow[time] = getInt(sumPrice[time] - sumPrice[0]);
                maSlow[time] = Math.round(maSlow[time] * 1.0f / time);
            } else {
                maSlow[time] = getInt(sumPrice[time] - sumPrice[time - SLOW_PERIOD]);
                maSlow[time] = Math.round(maSlow[time] * 1.0f / SLOW_PERIOD);
            }        }
        //printForDebug(time, price, smaFast[time], smaSlow[time]);
        isCrossOver("SMA");
    }
    
    
  
}
