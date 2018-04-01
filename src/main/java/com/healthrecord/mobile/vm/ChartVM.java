package com.healthrecord.mobile.vm;

import org.zkoss.chart.Axis;
import org.zkoss.chart.Charts;
import org.zkoss.chart.Color;
import org.zkoss.chart.Legend;
import org.zkoss.chart.XAxis;
import org.zkoss.chart.YAxis;
import org.zkoss.chart.model.CategoryModel;
import org.zkoss.chart.model.DefaultCategoryModel;
import org.zkoss.chart.model.DefaultXYModel;
import org.zkoss.chart.model.XYModel;
import org.zkoss.zk.ui.select.annotation.Wire;

public class ChartVM {
	

	public CategoryModel getRoom() {
        return initData("ค่าห้อง");
    }
	public CategoryModel getMedical() {
        return initData("ค่ายา");
    }
	public XAxis getXaxis() {
		XAxis x = new XAxis();
		x.setGridLineColor(new Color("red"));
		x.setTitle("now");
		x.setLineColor(new Color("red"));
		
		return x;
	}
	public YAxis getYaxis() {
		YAxis y = new YAxis();
		
		y.setTitle("now");
		return y;
	}
	public Axis getAxis() {
		System.out.println("sss");
		Axis a = new Axis();
		
		a.setTitle("now");
		a.getLabels().setStep(100);
		a.getLabels().setStyle("visibility: hidden;");
		return null;
	}
	public Legend getLegend() {
		Legend legend = new Legend();
		legend.setBackgroundColor(new Color("red"));
		legend.setEnabled(false);
		legend.setItemStyle("visibility: hidden;");
		legend.setTitle(null);
		return legend;
	}
	public CategoryModel initData(String topic) {
		 CategoryModel model = new DefaultCategoryModel();
	        model.setValue("1900", topic, new Integer(20));
	        model.setValue("1901", topic, new Integer(55));
	        return model;
	}
}
