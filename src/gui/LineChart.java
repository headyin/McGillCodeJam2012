/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Random;
import javax.swing.JApplet;
import javax.swing.JFrame;
import net.sourceforge.chart2d.*;

/**
 *
 * @author headyin
 */
public class LineChart extends JApplet{
     private JFrame frame = null;
     private static boolean isApplet = true;
     
    @Override
    public void init() {
        
        int maxWidth = 2000;
        int maxHeight = 300;
        
        Dimension maxSize = new Dimension (maxWidth, maxHeight);
        System.out.println (maxSize);
        
        Chart2D chart2D = getChart2DDemoI();
        chart2D.setSize(maxSize);
        chart2D.setPreferredSize(maxSize);
        
        frame = new JFrame();
        frame.getContentPane().add(chart2D);
        frame.setTitle ("LBChart2DFrameDemo");
        frame.addWindowListener (
                new WindowAdapter() {
                    @Override
                    public void windowClosing (WindowEvent e) {
                        destroy();
                    } } );
        frame.pack();
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        frame.setLocation (
                (screenSize.width - frame.getSize().width) / 2,
                (screenSize.height - frame.getSize().height) / 2);
    }
    
    
    /**
     * Shows the JFrame GUI.
     */
    @Override
    public void start() {
        frame.setVisible(true);;
        //frame.show();
    }
    
    /**
     * Ends the application or applet.
     */
    @Override
    public void destroy() {
        
        if (frame != null) {
            frame.dispose();
        }
        if (!isApplet) {
            System.exit (0);
        }
    }
    
    private Chart2D getChart2DDemoI() {
        
        //<-- Begin Chart2D configuration -->
        
        //Configure object properties
        Object2DProperties object2DProps = new Object2DProperties();
        object2DProps.setObjectTitleText ("Weekly LOC Programmed");
        
        //Configure chart properties
        Chart2DProperties chart2DProps = new Chart2DProperties();
        chart2DProps.setChartDataLabelsPrecision (1);
        
        //Configure legend properties
        LegendProperties legendProps = new LegendProperties();
        String[] legendLabels = {"Price", "SMA[5]", "SMA[20]"};
        legendProps.setLegendLabelsTexts (legendLabels);
        
        //Configure graph chart properties
        GraphChart2DProperties graphChart2DProps = new GraphChart2DProperties();
        String[] labelsAxisLabels =
        {"9 am", "10 am", "11 am", "12 pm", "1 pm", "2 pm", "3 pm", "4pm", "5 pm", "6 pm"};
        graphChart2DProps.setLabelsAxisLabelsTexts (labelsAxisLabels);
        graphChart2DProps.setLabelsAxisTitleText ("Time of the Day");
        graphChart2DProps.setNumbersAxisTitleText ("Price");
        graphChart2DProps.setLabelsAxisTicksAlignment (GraphChart2DProperties.CENTERED);
        
        //Configure graph properties
        GraphProperties graphProps = new GraphProperties();
        graphProps.setGraphBarsExistence (false);
        graphProps.setGraphLinesExistence (true);
        graphProps.setGraphAllowComponentAlignment (true);
        graphProps.setGraphLinesWithinCategoryOverlapRatio (1f);
        
        //Configure dataset
        Random random = new Random();
        Dataset dataset = new Dataset (3, 10, 4);
        for (int i = 0; i < dataset.getNumSets(); ++i) {
            for (int j = 0; j < dataset.getNumCats(); ++j) {
                for (int k = 0; k < dataset.getNumItems(); ++k) {
                    int increaseMetric = dataset.getNumSets() - i - 1;
                    dataset.set (i, j, k,
                            (increaseMetric + 1) * random.nextInt (5) + (increaseMetric + 1) * 30 + j * 3);
                }
            }
        }
        
        //Configure graph component colors
        MultiColorsProperties multiColorsProps = new MultiColorsProperties();
        
        //Configure chart
        LBChart2D chart2D = new LBChart2D();
        chart2D.setObject2DProperties (object2DProps);
        chart2D.setChart2DProperties (chart2DProps);
        chart2D.setLegendProperties (legendProps);
        chart2D.setGraphChart2DProperties (graphChart2DProps);
        chart2D.addGraphProperties (graphProps);
        chart2D.addMultiColorsProperties (multiColorsProps);
        chart2D.addDataset (dataset);
        
        //Optional validation:  Prints debug messages if invalid only.
        if (!chart2D.validate (false)) {
            chart2D.validate (true);
        }
        
        //<-- End Chart2D configuration -->
        
        return chart2D;
    }
}