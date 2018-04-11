package com.healthrecord.mobile.vm;

import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.zkoss.bind.annotation.Init;
import org.zkoss.image.AImage;
import org.zkoss.util.media.AMedia;

import com.healthrecord.mobile.model.NCDsM;
import com.healthrecord.mobile.model.UserProfileM;
import com.healthrecord.mobile.services.INCDsDataService;
import com.healthrecord.mobile.services.impl.NCDsDataServiceImpl;

public class NCDsVM implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private List<NCDsM> ncds = new ArrayList<NCDsM>();
	
	@Init
	public void init() {
		try {
			ncds = getNCDs();
		}catch(Exception ex) {
			ncds = new ArrayList<NCDsM>();
		}
	}
	public AImage getIndicatorImage(UserProfileM profile,String indicator) throws Exception {
		
		InputStream is = NCDsVM.class.getClassLoader().getResourceAsStream("/BUN");
		
		return new AImage("ima.png",is);
	}

	private List<NCDsM> getNCDs() throws Exception{
		 String ncds_endpoint   = "https://120f7ec5-945c-42e4-904b-e8cdf79f6b8e-bluemix.cloudant.com/ncds/_all_docs?include_docs=true";
			
		INCDsDataService ncdsService = new NCDsDataServiceImpl(ncds_endpoint);
		return ncdsService.getAll();
	}

	public List<NCDsM> getNcds() {
		return ncds;
	}

	public void setNcds(List<NCDsM> ncds) {
		this.ncds = ncds;
	}

}
