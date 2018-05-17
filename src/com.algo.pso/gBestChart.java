/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package algo_scratch_1;

/**
 *
 * @author Ranadeep Guha
 */
import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JPanel;
import javax.swing.Timer;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.ValueAxis;
import org.jfree.chart.plot.XYPlot;
import org.jfree.data.time.DynamicTimeSeriesCollection;
import org.jfree.data.time.Second;
import org.jfree.data.xy.XYDataset;
import org.jfree.ui.ApplicationFrame;
import org.jfree.ui.RefineryUtilities;

public class gBestChart extends ApplicationFrame {

    private static final String TITLE = "Particle Travelling Distance";
    private static final String START = "Start";
    private static final String STOP = "Stop";
    private static final float MINMAX = 5000000;
    private static final int COUNT = 50;
    private static final int FAST = 100;
    private static final int SLOW = FAST * 100;
    private static final Random random = new Random();
    private Timer timer;
    private static PSOMain pso = new PSOMain();
    float[] fitnessValueList;

    public gBestChart(final String title) {
        super(title);

        //Call the main function
        pso.initializeSwarm();
//        pso.updateFitnessList();
//        fitnessValueList=pso.getFitnessValueList();
//        

        final DynamicTimeSeriesCollection dataset
                = new DynamicTimeSeriesCollection(1, COUNT, new Second());
        dataset.setTimeBase(new Second(0, 0, 0, 1, 1, 2011));
        dataset.addSeries(gaussianData(), 0, "PSO Data");
        JFreeChart chart = createChart(dataset);
        final JButton run = new JButton(STOP);
        run.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String cmd = e.getActionCommand();
                if (STOP.equals(cmd)) {
                    timer.stop();
                    run.setText(START);
                } else {
                    timer.start();
                    run.setText(STOP);
                }
            }
        });
        final JComboBox combo = new JComboBox();
        combo.addItem("Fast");
        combo.addItem("Slow");
        combo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if ("Fast".equals(combo.getSelectedItem())) {
                    timer.setDelay(FAST);
                } else {
                    timer.setDelay(SLOW);
                }
            }
        });
        this.add(new ChartPanel(chart), BorderLayout.CENTER);
        JPanel btnPanel = new JPanel(new FlowLayout());
        btnPanel.add(run);
        btnPanel.add(combo);
        this.add(btnPanel, BorderLayout.SOUTH);
        timer = new Timer(FAST, new ActionListener() {
            float[] newData = new float[1];

            @Override
            public void actionPerformed(ActionEvent e) {
                // newData[0] = randomValue();
                // newData[0] = 10;
                pso.execute();

                //  for(int i=0;i<pso.getFitnessValueList().length;i++)
//                {
//                    float newData[]=new float[pso.getFitnessValueList().length];
//                    newData[i]=pso.getFitnessValueList()[i];
                dataset.advanceTime();
                dataset.appendData(pso.getNewLocX());
            }
            //}
        });
    }

    private float randomValue() {
        return (float) (random.nextGaussian() * MINMAX / 3);
    }

    private float[] gaussianData() {
        float[] a = new float[50];
        for (int i = 0; i < a.length; i++) {
//            System.out.println("Value of fitness for swarm"+i+"is "+fitnessValueList[i]);
            //a[i] = (float) fitnessValueList[i];

            //      a[i] = 10;
            a[i] = -3000;
        }
        return a;
    }

    private JFreeChart createChart(final XYDataset dataset) {
        final JFreeChart result = ChartFactory.createTimeSeriesChart(
                TITLE, "Time", "gBest", dataset, true, true, false);
        final XYPlot plot = result.getXYPlot();
        ValueAxis domain = plot.getDomainAxis();
        domain.setAutoRange(true);
        ValueAxis range = plot.getRangeAxis();
        range.setRange(-MINMAX, MINMAX);
        return result;
    }

    public void start() {
        timer.start();
    }

    public static void main(final String[] args) {
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {

                gBestChart demo = new gBestChart(TITLE);
                demo.pack();
                RefineryUtilities.centerFrameOnScreen(demo);
                demo.setVisible(true);
                demo.start();
            }
        });
    }
}
