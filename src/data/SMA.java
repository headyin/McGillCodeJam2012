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
    
    protected long[] smaFastSum;
    protected long[] smaSlowSum;
    
    public SMA(Prices prices) {
        super.init(prices);
        smaFastSum = new long[Prices.TOTAL_TIME];
        smaSlowSum = new long[Prices.TOTAL_TIME];
    }

    @Override
    public int calcMovingAverage() {
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
        smaFastSum[time] = smaFastSum[time - 1] + maFast[time];
        smaSlowSum[time] = smaSlowSum[time - 1] + maSlow[time];
        return isCrossOver();
    }
    
    protected long[] getSMAFastSum() {
        return this.smaFastSum;
    }
    
    protected long[] getSMASlowSum() {
        return this.smaSlowSum;
    }
    
    
  
}
