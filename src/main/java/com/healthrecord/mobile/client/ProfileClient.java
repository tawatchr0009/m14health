package com.healthrecord.mobile.client;

import java.util.List;

import com.healthrecord.mobile.model.IndicatorM;
import com.healthrecord.mobile.model.UserIndicatorM;
import com.healthrecord.mobile.model.UserProfileM;
import com.healthrecord.mobile.model.data.results.CloudantResultM;
import com.healthrecord.mobile.model.data.results.CloudantResultSetM;
import com.healthrecord.mobile.model.data.results.ProfileResultSetM;
import com.healthrecord.mobile.services.impl.ProfileDataServiceImpl;

public class ProfileClient {

	public static void main(String[] args) {
		
		try {
			String endpoint_url = "https://120f7ec5-945c-42e4-904b-e8cdf79f6b8e-bluemix.cloudant.com/profiles/_all_docs?include_docs=true&keys=[\"9a9f3e2377fa0cbe4ac985955a7ee8cf\"]";
			ProfileDataServiceImpl service = new ProfileDataServiceImpl(endpoint_url);
			
//			CloudantResultSetM<UserProfileM> rs =	service.get();
//			List<CloudantResultM<UserProfileM>> items = rs.getRows();
//			for(CloudantResultM<UserProfileM> item: items) {
//				UserProfileM user = item.getDoc();
//					for(UserIndicatorM i : user.getIndicators()) {
//					
//					System.out.println("indicator name: "+i.getId()+" value: "+i.getValue());
//				}
//			}
			
			List<UserProfileM> profiles = service.getAll();
			for(UserProfileM p : profiles) {
				for(UserIndicatorM i : p.getIndicators()) {
					
					System.out.println("indicator name: "+i.getId()+" value: "+i.getValue());
				}
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
