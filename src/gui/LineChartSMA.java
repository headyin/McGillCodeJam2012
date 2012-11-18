/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import data.Prices;
import data.SMA;

/**
 *
 * @author headyin
 */
public class LineChartSMA extends JFrameLineChart{
    
    public LineChartSMA(Prices prices, SMA sma) {
        this.JFrameLineChartInit(prices);
        this.ma = sma;
        this.legendLabels = new String[] {"price","SMA[5]", "SMA[20]"};
        this.titleString = "Simple Moving Average";
        this.setTitle(titleString);
        this.setLocation(10, 20);
    }
    
}
