/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package data;

/**
 *
 * @author headyin
 */
public class TMA extends MovingAverage{
    
    private int[] tFastTMA;
    private int[] tSlowTMA;
    private SMA sma;
    
    public TMA(Prices prices,SMA sma) {
        super.init(prices);
        tFastTMA = new int[Prices.TOTAL_TIME];
        tSlowTMA = new int[Prices.TOTAL_TIME];
        this.sma = sma;
    }

    @Override
    public int calcMovingAverage(int time) {
        this.currentTime = time;
        long[] smaFastSum = sma.getSMAFastSum();
        long[] smaSlowSum = sma.getSMASlowSum();
        int price = prices.getPrice(time);
        
        if (time > FAST_PERIOD) {
            this.tFastTMA[time] = getInt(smaFastSum[time] - smaFastSum[time - FAST_PERIOD]);
            this.maFast[time] = Math.round(this.tFastTMA[time] * 1.0f / FAST_PERIOD);
        } else {
            this.tFastTMA[time] = getInt(smaFastSum[time]);
            this.maFast[time] = Math.round(this.tFastTMA[time] * 1.0f / time);
        }
        if (time > SLOW_PERIOD) {
            this.tSlowTMA[time] = getInt(smaSlowSum[time] - smaSlowSum[time - SLOW_PERIOD]);
            this.maSlow[time] = Math.round(this.tSlowTMA[time] * 1.0f / SLOW_PERIOD);
        } else {
            this.tSlowTMA[time] = getInt(smaSlowSum[time]);
            this.maSlow[time] = Math.round(this.tSlowTMA[time] * 1.0f / time);
        }
        return isCrossOver();
    }
    
}
