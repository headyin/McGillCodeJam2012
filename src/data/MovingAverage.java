/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package data;

/**
 *
 * @author headyin
 */
public abstract class MovingAverage {
    protected final static int FAST_PERIOD = 5;
    protected final static int SLOW_PERIOD = 20;
    
    protected int[] maFast;
    protected int[] maSlow;
    protected Prices prices;
   
    protected int currentTime;
    
    protected void init(Prices prices) {
        maFast = new int[Prices.TOTAL_TIME];
        maSlow = new int[Prices.TOTAL_TIME];
        this.prices = prices;
        currentTime = 0;
    }
    
    public void clear() {
        this.currentTime = 0;
    }
    
    abstract public void calcMovingAverage();
    
    public int getFastMA(int time) {
        return maFast[time];
    }
    
    public int getSlowMA(int time) {
        return maSlow[time];
    }
    
    public int getCurrentTime() {
        return this.currentTime;
    }
    
    protected int getInt(long value) {
        return (int) (value & 0x000000007fffffff);
    }
    
    protected void printForDebug(int t, int p1, int p2, int p3) {
        System.out.println(t + ":" + p1 + ", " + p2 + ", " + p3);
    }
    protected int isCrossOver(String strategy) {
        int time = currentTime;
         //printForDebug(time, price, smaFast[time], smaSlow[time]);
        if ((maFast[time - 1] < maSlow[time - 1]) 
                && (maFast[time] > maSlow[time])) {
            return 1;
            //TODO: strategy sma buy
            /*printForDebug(time-2, price, maFast[time-2], maSlow[time-2]);
            printForDebug(time-1, price, maFast[time-1], maSlow[time-1]);
            printForDebug(time, price, maFast[time], maSlow[time]);
            System.out.println(": Buy!");*/
        } else if ((maFast[time - 1] > maSlow[time - 1]) 
                && (maFast[time] < maSlow[time])) {
            //TODO: startegy sma sell
            /*printForDebug(time-2, price, maFast[time-2], maSlow[time-2]);
            printForDebug(time-1, price, maFast[time-1], maSlow[time-1]);
            printForDebug(time, price, maFast[time], maSlow[time]);
            System.out.println(": Sell");*/
            return -1;
        } else return 0;  
    }
    
}
