package com.healthrecord.mobile.client;

import java.util.List;

import com.healthrecord.mobile.model.NCDsM;
import com.healthrecord.mobile.services.impl.NCDsDataServiceImpl;

public class NCDsClient {
	public static void main(String[] args) {
			
			try {
				String endpoint = "https://120f7ec5-945c-42e4-904b-e8cdf79f6b8e-bluemix.cloudant.com/ncds/_all_docs?include_docs=true";
				NCDsDataServiceImpl service = new NCDsDataServiceImpl(endpoint);
				List<NCDsM> datas = service.getAll();
				for(NCDsM ncd: datas) {
					System.out.println("ncds-id: "+ncd.get_id()+" name: "+ncd.getName_en());
				}
			}catch(Exception ex) {
				ex.printStackTrace();
			}
	}
}
