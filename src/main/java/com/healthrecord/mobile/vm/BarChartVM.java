package com.healthrecord.mobile.vm;



import org.zkoss.bind.annotation.AfterCompose;
import org.zkoss.bind.annotation.ContextParam;
import org.zkoss.bind.annotation.ContextType;
import org.zkoss.bind.annotation.Init;
import org.zkoss.bind.annotation.QueryParam;
import org.zkoss.chart.Axis;
import org.zkoss.chart.Charts;
import org.zkoss.chart.Color;
import org.zkoss.chart.Exporting;
import org.zkoss.chart.Legend;
import org.zkoss.chart.XAxis;
import org.zkoss.chart.YAxis;
import org.zkoss.chart.model.CategoryModel;
import org.zkoss.chart.model.DefaultCategoryModel;
import org.zkoss.chart.model.DefaultXYModel;
import org.zkoss.chart.model.XYModel;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.select.Selectors;
import org.zkoss.zk.ui.select.annotation.Wire;

public class BarChartVM {
	
	@Wire("#chart")
    Charts chart;
	
	private String lineId;
	private String topic;
	
	@AfterCompose
    public void afterCompose(@ContextParam(ContextType.VIEW) Component view){
        Selectors.wireComponents(view, this, false);
        initData(lineId);
    }
//	public CategoryModel getRoom() {
//        return initData("ค่าห้อง");
//    }
//	public CategoryModel getMedical() {
//        return initData("ค่ายา");
//    }
//	public XAxis getXaxis() {
//		XAxis x = new XAxis();
//		x.setGridLineColor(new Color("red"));
//		x.setTitle("now");
//		x.setLineColor(new Color("red"));
//		
//		return x;
//	}
//	public YAxis getYaxis() {
//		YAxis y = new YAxis();
//		
//		y.setTitle("now");
//		return y;
//	}
//	public Axis getAxis() {
//		System.out.println("sss");
//		Axis a = new Axis();
//		
//		a.setTitle("now");
//		a.getLabels().setStep(100);
//		a.getLabels().setStyle("visibility: hidden;");
//		return null;
//	}
//	public Legend getLegend() {
//		Legend legend = new Legend();
//		legend.setBackgroundColor(new Color("red"));
//		legend.setEnabled(false);
//		legend.setItemStyle("visibility: hidden;");
//		legend.setTitle(null);
//		return legend;
//	}
	public void initData(String topic) {
		 CategoryModel model = new DefaultCategoryModel();
		 if (topic.equals("room")) {
	        model.setValue("expense", "", new Integer(20));
	        model.setValue("coverage", "", new Integer(55));
		 } else {
			 model.setValue("expense", "", new Integer(80));
		        model.setValue("coverage", "", new Integer(55));
		 }
	        chart.setModel(model);
	        Legend legend = chart.getLegend();
	        legend.setEnabled(false);
	        Exporting exp =	chart.getExporting();
			exp.setEnabled(false);
			chart.setBackgroundColor(new Color("#F3F7FC"));
			chart.getYAxis().setTitle("");
			chart.getXAxis().setTitle(topic);
			chart.getSeries(0).setName("");
			
	}
	
	@Init
    public void initVM(@QueryParam("indicator") String id,@QueryParam("topic") String t_topic){

		System.out.println("indicator:"+id);
        this.lineId = id;
        this.topic = t_topic;
    }
}
