package org.good.job.currency.project.bot.service.graph;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;

import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.util.List;


public class ChartGenerator {

    public static File generateChartImage(List<Object> dates, List<Object> buyRates, List<Object> sellRates) {
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();

        for (int i = 0; i < dates.size(); i++) {
            LocalDate date = LocalDate.parse(dates.get(i).toString());
            dataset.addValue(Double.valueOf(buyRates.get(i).toString()), "Buy Rate", date);
            dataset.addValue(Double.valueOf(sellRates.get(i).toString()), "Sell Rate", date);
        }
        JFreeChart lineChart = ChartFactory.createLineChart(
                "Currency Statistics",
                "Date",
                "Rate",
                dataset,
                PlotOrientation.VERTICAL,
                true,
                true,
                false
        );
        File chartFile;
        try {
            chartFile = createChartFile(lineChart);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return chartFile;
    }

    private static File createChartFile(JFreeChart chart) throws IOException {
//        ByteArrayOutputStream out = new ByteArrayOutputStream();
//        ChartUtilities.writeChartAsPNG(out, chart, 800, 600);
//        byte[] chartData = out.toByteArray();
        File chartFile = new File("chart.png");
        ChartUtilities.saveChartAsPNG(chartFile, chart, 800, 600);
        return chartFile;
    }

}
