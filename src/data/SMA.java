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
            maFast[time] = maFast[time] / time;
            maSlow[time] = getInt(sumPrice[time] - sumPrice[0]);
            maSlow[time] = maSlow[time] / time;
        } else {
            maFast[time] = getInt(sumPrice[time] - sumPrice[time - FAST_PERIOD]);
            maFast[time] = maFast[time] / FAST_PERIOD;
            if (time < SLOW_PERIOD) {
                maSlow[time] = getInt(sumPrice[time] - sumPrice[0]);
                maSlow[time] = maSlow[time] / time;
            } else {
                maSlow[time] = getInt(sumPrice[time] - sumPrice[time - SLOW_PERIOD]);
                maSlow[time] = maSlow[time] / SLOW_PERIOD;
            }        }
        //printForDebug(time, price, smaFast[time], smaSlow[time]);
        isCrossOver("SMA");
    }
    
    
  
}
