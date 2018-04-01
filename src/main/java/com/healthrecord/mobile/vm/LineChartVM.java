package com.healthrecord.mobile.vm;
import org.zkoss.bind.annotation.AfterCompose;
import org.zkoss.bind.annotation.ContextParam;
import org.zkoss.bind.annotation.ContextType;
import org.zkoss.bind.annotation.Init;
import org.zkoss.bind.annotation.QueryParam;
import org.zkoss.chart.Charts;
import org.zkoss.chart.Color;
import org.zkoss.chart.Exporting;
import org.zkoss.chart.Legend;
import org.zkoss.chart.PlotLine;
import org.zkoss.chart.model.CategoryModel;
import org.zkoss.chart.model.DefaultCategoryModel;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.select.Selectors;
import org.zkoss.zk.ui.select.annotation.Wire;

public class LineChartVM {
	
	@Wire("#chart")
    Charts chart;
	
	private String lineId;
	
	@AfterCompose
    public void afterCompose(@ContextParam(ContextType.VIEW) Component view){
        Selectors.wireComponents(view, this, false);
        initData(lineId);
    }
	
	@Init
    public void initVM(@QueryParam("indicator") String id){

		System.out.println("indicator:"+id);
        this.lineId = id;
 
    }
	
	private CategoryModel initModel(String line_id) {
		System.out.println("indicator:"+line_id);
		CategoryModel  model = new DefaultCategoryModel();
		if (line_id.equals("a")) {
			System.out.println("init data by line_id: "+line_id);
	        model.setValue(line_id, "10 Jan", 200);
	        model.setValue(line_id, "15 Feb", 200);
	        model.setValue(line_id, "31 Mar", 210);
		}else {
			 model.setValue(line_id, "10 Jan", 210);
		     model.setValue(line_id, "15 Feb", 220);
		     model.setValue(line_id, "31 Mar", 200);
		}
		return model;
	}

	private void initData(String line_id) {
		
			chart.setBackgroundColor(new Color("#FFF6F7"));
	        
	        chart.setModel(initModel(line_id));
	        
	        chart.getTitle().setX(-20);
	    
	        chart.getSubtitle().setX(-20);
	    
	        chart.getYAxis().setTitle("");
	        PlotLine plotLine = new PlotLine();
	        plotLine.setValue(0);
	        plotLine.setWidth(1);
	        plotLine.setColor("#808080");
	        chart.getYAxis().addPlotLine(plotLine);

//	        chart.getTooltip().setValueSuffix("°C");

	        Legend legend = chart.getLegend();
//	        legend.setItemStyle("visibility: hidden;");
//	        legend.setTitle(null);
	        
//	        legend.setBackgroundColor(new Color("red"));
			legend.setEnabled(false);
//			legend.setItemStyle("visibility: hidden;");
//			legend.setTitle(null);
	        
//	        chart.setLegend(legend);
//	        legend.setLayout("vertical");
//	        legend.setAlign("right");
//	        legend.setVerticalAlign("middle");
//	        legend.setBorderWidth(0);
			Exporting exp =	chart.getExporting();
			exp.setEnabled(false);
		
	}


}
