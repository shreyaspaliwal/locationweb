package com.shreyas.location.util;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.data.general.DefaultPieDataset;
import org.springframework.stereotype.Component;

@Component
public class ReportUtilImpl implements ReportUtil {

	@SuppressWarnings("removal")
	@Override
	public void generatePieChart(String path, List<Object[]> data) {
		// TODO Auto-generated method stub
		DefaultPieDataset dataset = new DefaultPieDataset();
		
		for(Object[] obj : data) {
			dataset.setValue(obj[0].toString(), new Double(obj[1].toString()));
		}
		
		JFreeChart chart = ChartFactory.createPieChart3D("Location type report", dataset, false, false, false); 
		
		try {
			ChartUtilities.saveChartAsJPEG(new File(path), chart, 300, 300);
		}
		catch(IOException e) {
			e.printStackTrace();
		}
		
	}

}
