/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import data.Prices;
import data.SMA;
import data.TMA;

/**
 *
 * @author headyin
 */
public class LineChartTMA extends JFrameLineChart {
    public LineChartTMA(Prices prices, TMA tma) {
        this.JFrameLineChartInit(prices);
        this.ma = tma;
        this.legendLabels = new String[] {"price","TMA[5]", "TMA[20]"};
        this.titleString = "Triangular Moving Average";
        this.setTitle(titleString);
        this.setLocation(600, 300);
    }
    
}
