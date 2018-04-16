package com.healthrecord.mobile.vm;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.zkoss.bind.annotation.AfterCompose;
import org.zkoss.bind.annotation.BindingParam;
import org.zkoss.bind.annotation.ContextParam;
import org.zkoss.bind.annotation.ContextType;
import org.zkoss.bind.annotation.Init;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.select.Selectors;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.healthrecord.mobile.model.NCDsM;
import com.healthrecord.mobile.model.UserIndicatorM;
import com.healthrecord.mobile.model.UserProfileM;
import com.healthrecord.mobile.services.INCDsDataService;
import com.healthrecord.mobile.services.INDICATORS;
import com.healthrecord.mobile.services.IService;
import com.healthrecord.mobile.services.NCDS;
import com.healthrecord.mobile.services.USERPROFILE;
import com.healthrecord.mobile.services.impl.NCDsDataServiceImpl;
import com.healthrecord.mobile.services.impl.NCDsJsonNodeDataServiceImpl;
import com.healthrecord.mobile.model.IndicatorID;
import com.healthrecord.mobile.model.IndicatorM;
import com.healthrecord.mobile.model.NCDsM;

public class NCDByIndicatorVM extends BaseVM{
	
	
	private UserProfileM profile;
	private NCDsM ncd;
	private List<IndicatorM> indicators = new ArrayList<IndicatorM>();
	private Map<String, UserIndicatorM> userIndicators = new HashMap<String, UserIndicatorM>();

	@AfterCompose
    public void afterCompose(@ContextParam(ContextType.VIEW) Component view){
        Selectors.wireComponents(view, this, false);
    }
	
	@Init
    public void initVM(@BindingParam("ncds") String ncd_name){
		try {
//			String ncd_name = "dyslipidemia";
			System.out.println("initVM >>> ");
			this.profile = callService(USERPROFILE.U0001, UserProfileM.class);
	//		this.ncd = callService(NCDS.DYSLIPIDEMIA, NCDsM.class);
			this.ncd = getNCDByIndicator(ncd_name);
			this.indicators.clear();
			
	        if (null != ncd && ncd.getIndicators() != null &&
	        		ncd.getIndicators().length > 0) {
	        	
	//        	for (IndicatorID _indicatorID : ncd.getIndicators()) {
	//        		System.out.println("Indicator ID : " + _indicatorID);
	//        		INDICATORS _indicator = INDICATORS.valueOf(_indicatorID.getId().toUpperCase());
	//        		System.out.println("indicator : "+_indicator.url());
	//        		IndicatorM indicatorM = callService(_indicator, IndicatorM.class);
	//        		indicators.add(indicatorM);
	//        		
	//        	}        	
	        }
	        
	        userIndicators.clear();
	        if (null != profile && 
	        		null != profile.getIndicators() && profile.getIndicators().length > 0) {
	        	for (UserIndicatorM _userIndicators : profile.getIndicators()) {
	        		userIndicators.put(_userIndicators.getId(), _userIndicators);
	        	}
	        }
	
	        System.out.println("User Profile : "+profile);
	        
	        Executions.getCurrent().getSession().setAttribute("USER_INDICATORS", userIndicators);
		} catch(Exception ex) {
			ex.printStackTrace();
		}
    }
	
	/*public NcdM initDyslipidemiaM() {
		InputStream is = null;
		try {
	
			String port = ( Executions.getCurrent().getServerPort() == 80 ) ? "" : (":" + Executions.getCurrent().getServerPort());
	        String endpoint_url = Executions.getCurrent().getScheme() + "://" + Executions.getCurrent().getServerName() + port + Executions.getCurrent().getContextPath();
	        endpoint_url += "/services/ncds/dysipidemia.json";
	        
	        System.out.println("endpoint_url : "+endpoint_url);

	        is = new URL(endpoint_url).openStream();
	        BufferedReader rd = new BufferedReader(new InputStreamReader(is, Charset.forName("UTF-8")));
	       
	        NcdM dyM = getModel(rd, NcdM.class);
			System.out.println("Print JSON "+dyM);
			
			return dyM;
		} catch (Exception e) {
			System.err.println("error.initVM : " +e.getMessage());
		} finally {
			if (is != null)
				try {
					is.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		}
		return null;
	}*/

	
	
	
	
	



	private NCDsM getNCDByIndicator(String id) throws Exception {
		System.out.println("id:"+id);
		String endpoint = "https://120f7ec5-945c-42e4-904b-e8cdf79f6b8e-bluemix.cloudant.com/ncds";
		INCDsDataService dataService = new NCDsJsonNodeDataServiceImpl(endpoint);
		return dataService.getById(id);
	
	}

	public List<IndicatorM> getIndicators() {
		return indicators;
	}

	public void setIndicators(List<IndicatorM> indicators) {
		this.indicators = indicators;
	}
	

	public NCDsM getNcd() {
		return ncd;
	}

	public void setNcd(NCDsM ncd) {
		this.ncd = ncd;
	}

	public UserProfileM getProfile() {
		return profile;
	}

	public void setProfile(UserProfileM profile) {
		this.profile = profile;
	}

	public Map<String, UserIndicatorM> getUserIndicators() {
		return userIndicators;
	}

	public void setUserIndicators(Map<String, UserIndicatorM> userIndicators) {
		this.userIndicators = userIndicators;
	}


}
