/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import data.EMA;
import data.Prices;

/**
 *
 * @author headyin
 */
public class LineChartEMA extends JFrameLineChart{
    public LineChartEMA(Prices prices, EMA ema) {
        this.JFrameLineChartInit(prices);
        this.ma = ema;
        this.legendLabels = new String[] {"price","EMA[5]", "EMA[20]"};
        this.titleString = "Exponential Moving Average";
        this.setTitle(titleString);
        this.setLocation(600, 20);
    }
    
}
