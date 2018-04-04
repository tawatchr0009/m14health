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
import org.zkoss.bind.annotation.ContextParam;
import org.zkoss.bind.annotation.ContextType;
import org.zkoss.bind.annotation.Init;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.select.Selectors;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.healthrecord.mobile.model.NcdM;
import com.healthrecord.mobile.model.UserIndicatorM;
import com.healthrecord.mobile.model.UserProfileM;
import com.healthrecord.mobile.services.INDICATORS;
import com.healthrecord.mobile.services.IService;
import com.healthrecord.mobile.services.NCDS;
import com.healthrecord.mobile.services.USERPROFILE;
import com.healthrecord.mobile.model.IndicatorID;
import com.healthrecord.mobile.model.IndicatorM;

public class DyslipidemiaVM {
	
	
	private UserProfileM profile;
	private NcdM ncd;
	private List<IndicatorM> indicators = new ArrayList<IndicatorM>();
	private Map<String, UserIndicatorM> userIndicators = new HashMap<String, UserIndicatorM>();

	@AfterCompose
    public void afterCompose(@ContextParam(ContextType.VIEW) Component view){
        Selectors.wireComponents(view, this, false);
    }
	
	@Init
    public void initVM(){
		System.out.println("initVM >>> ");
		this.profile = callService(USERPROFILE.U0001, UserProfileM.class);
		this.ncd = callService(NCDS.DYSLIPIDEMIA, NcdM.class);
		this.indicators.clear();
		
        if (null != ncd && ncd.getIndicators() != null &&
        		ncd.getIndicators().length > 0) {
        	
        	for (IndicatorID _indicatorID : ncd.getIndicators()) {
        		System.out.println("Indicator ID : " + _indicatorID);
        		INDICATORS _indicator = INDICATORS.valueOf(_indicatorID.getId().toUpperCase());
        		System.out.println("indicator : "+_indicator.url());
        		IndicatorM indicatorM = callService(_indicator, IndicatorM.class);
        		indicators.add(indicatorM);
        		
        	}        	
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

	public String getContextRootURL() throws Exception {
		String port = ( Executions.getCurrent().getServerPort() == 80 ) ? "" : (":" + Executions.getCurrent().getServerPort());
        String endpoint_url = Executions.getCurrent().getScheme() + "://" + Executions.getCurrent().getServerName() + port + Executions.getCurrent().getContextPath();
        return endpoint_url;
	}
	
	public <T> T getModel(Reader r, Class<T> c) throws Exception {
		ObjectMapper objMapper = new ObjectMapper();
		T model =	objMapper.readValue(r, c);
		return model;
	}
	
	public <T> T callService (IService s, Class<T> c) {
		InputStream is = null;
		BufferedReader rd = null;
		try {
	        String endpoint_url = getContextRootURL();
	        endpoint_url += s.url();
	        
	        System.out.println("endpoint_url : "+endpoint_url);

	        is = new URL(endpoint_url).openStream();
	        rd = new BufferedReader(new InputStreamReader(is, Charset.forName("UTF-8")));
	       
	        T item = getModel(rd, c);
			System.out.println("Print JSON "+item);
			
			return item;
		} catch (Exception e) {
			System.err.println("error.callService : " +e.getMessage());
		} finally {
			
			if (rd != null)
				try {
					rd.close();
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			
			
			if (is != null)
				try {
					is.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			
			
		}
		return null;
	}



	public List<IndicatorM> getIndicators() {
		return indicators;
	}

	public void setIndicators(List<IndicatorM> indicators) {
		this.indicators = indicators;
	}
	

	public NcdM getNcd() {
		return ncd;
	}

	public void setNcd(NcdM ncd) {
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
