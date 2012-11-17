/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package data;

/**
 *
 * @author headyin
 */
public class SMA {
    private final static int FAST_PERIOD = 5;
    private final static int SLOW_PERIOD = 20;
    
    private int[] smaFast;
    private int[] smaSlow;
    private long[] sumPrice;
    private int currentTime;
    
    public SMA() {
        smaFast = new int[Prices.TOTAL_TIME];
        smaSlow = new int[Prices.TOTAL_TIME];
        sumPrice = new long[Prices.TOTAL_TIME];
        currentTime = 0;
    }
    
    public void clear() {
        this.currentTime = 0;
    }
    
    public void calcSMA(int time, int price) {
        sumPrice[time] = sumPrice[time - 1] + price;
        if (time < FAST_PERIOD) {
            smaFast[time] = getInt(sumPrice[time] - sumPrice[0]);
            smaFast[time] = smaFast[time] / time;
            smaSlow[time] = getInt(sumPrice[time] - sumPrice[0]);
            smaSlow[time] = smaSlow[time] / time;
        } else {
            smaFast[time] = getInt(sumPrice[time] - sumPrice[time - FAST_PERIOD]);
            smaFast[time] = smaFast[time] / FAST_PERIOD;
            if (time < SLOW_PERIOD) {
                smaSlow[time] = getInt(sumPrice[time] - sumPrice[0]);
                smaSlow[time] = smaSlow[time] / time;
            } else {
                smaSlow[time] = getInt(sumPrice[time] - sumPrice[time - SLOW_PERIOD]);
                smaSlow[time] = smaSlow[time] / SLOW_PERIOD;
            }        }
        //printForDebug(time, price, smaFast[time], smaSlow[time]);
        if ((smaFast[time - 1] < smaSlow[time - 1]) 
                && (smaFast[time] > smaSlow[time])) {
            //TODO: strategy sma buy
            /*printForDebug(time-2, price, smaFast[time-2], smaSlow[time-2]);
            printForDebug(time-1, price, smaFast[time-1], smaSlow[time-1]);
            printForDebug(time, price, smaFast[time], smaSlow[time]);
            System.out.println(": Buy!");*/
        } else if ((smaFast[time - 1] > smaSlow[time - 1]) 
                && (smaFast[time] < smaSlow[time])) {
            //TODO: startegy sma sell
            /*printForDebug(time-2, price, smaFast[time-2], smaSlow[time-2]);
            printForDebug(time-1, price, smaFast[time-1], smaSlow[time-1]);
            printForDebug(time, price, smaFast[time], smaSlow[time]);
            System.out.println(": Sell");*/
        }       
        
    }
    
    public int getFastSMA(int time) {
        return smaFast[time];
    }
    
    public int getSlowSMA(int time) {
        return smaSlow[time];
    }
    
    private int getInt(long value) {
        return (int) (value & 0x000000007fffffff);
    }
    
    private void printForDebug(int t, int p1, int p2, int p3) {
        System.out.println(t + ":" + p1 + ", " + p2 + ", " + p3);
    }
    
    
}
