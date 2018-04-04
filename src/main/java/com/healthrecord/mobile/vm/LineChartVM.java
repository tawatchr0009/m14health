package com.healthrecord.mobile.vm;
import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

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
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.select.Selectors;
import org.zkoss.zk.ui.select.annotation.Wire;

import com.healthrecord.mobile.model.UserIndicatorHistoryM;
import com.healthrecord.mobile.model.UserIndicatorM;

public class LineChartVM {
	
	@Wire("#chart")
    Charts chart;
	
	private String lineId;
	
	private Map<String, UserIndicatorM> userIndicators = new HashMap<String, UserIndicatorM>();
	
	@SuppressWarnings("unchecked")
	@AfterCompose
    public void afterCompose(@ContextParam(ContextType.VIEW) Component view){
        Selectors.wireComponents(view, this, false);
        userIndicators = (Map<String, UserIndicatorM>)Executions.getCurrent().getSession().getAttribute("USER_INDICATORS");
        initData(lineId, userIndicators);
    }
	
	@Init
    public void initVM(@QueryParam("indicator") String id){

        this.lineId = id;
 
    }
	
	private CategoryModel initModel(String line_id, Map<String, UserIndicatorM> userIndicators) {
		System.out.println("indicator:"+line_id);
		CategoryModel  model = new DefaultCategoryModel();
		
		UserIndicatorM dataM = null;
		
		if (userIndicators.containsKey(line_id)) {
			dataM = userIndicators.get(line_id);
		}
		
		if (dataM != null && dataM.getHistorys() != null && dataM.getHistorys().length > 0) {
			
			System.out.println("init data by line_id: "+line_id);
			
			for (UserIndicatorHistoryM _lineData : dataM.getHistorys()) {
				 model.setValue(line_id, getDateReport(_lineData.getDate()), new BigDecimal(_lineData.getValue()));
			}
			
		}/*else {
			 model.setValue(line_id, "10 Jan", 210);
		     model.setValue(line_id, "15 Feb", 220);
		     model.setValue(line_id, "31 Mar", 200);
		}*/
		return model;
	}
	
	private String getDateReport(String dateInString) {
		SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMdd");
		DateFormat df = new SimpleDateFormat("dd MMM");
		String convertedDate = null;
        try {
            Date date = formatter.parse(dateInString);
            
            convertedDate = df.format(date);
            
        } catch (ParseException e) {
            e.printStackTrace();
        }
        
        System.out.println("input : "+dateInString+" , output : "+convertedDate);
        
        return convertedDate;

	}
	
	private void initData(String line_id, Map<String, UserIndicatorM> userIndicators) {
		
		chart.setBackgroundColor(new Color("#FFF6F7"));
        
        chart.setModel(initModel(line_id, userIndicators));
        
        chart.getTitle().setX(-20);
    
        chart.getSubtitle().setX(-20);
    
        chart.getYAxis().setTitle("");
        PlotLine plotLine = new PlotLine();
        plotLine.setValue(0);
        plotLine.setWidth(1);
        plotLine.setColor("#808080");
        chart.getYAxis().addPlotLine(plotLine);

//        chart.getTooltip().setValueSuffix("°C");

        Legend legend = chart.getLegend();
//        legend.setItemStyle("visibility: hidden;");
//        legend.setTitle(null);
        
//        legend.setBackgroundColor(new Color("red"));
		legend.setEnabled(false);
//		legend.setItemStyle("visibility: hidden;");
//		legend.setTitle(null);
        
//        chart.setLegend(legend);
//        legend.setLayout("vertical");
//        legend.setAlign("right");
//        legend.setVerticalAlign("middle");
//        legend.setBorderWidth(0);
		Exporting exp =	chart.getExporting();
		exp.setEnabled(false);
	
}


}
