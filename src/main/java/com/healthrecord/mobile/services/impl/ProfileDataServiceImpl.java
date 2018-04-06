package com.healthrecord.mobile.services.impl;


import com.healthrecord.mobile.model.UserProfileM;
import com.healthrecord.mobile.model.data.results.CloudantResultSetM;
import com.healthrecord.mobile.model.data.results.ProfileResultSetM;
import com.healthrecord.mobile.services.IProfileDataService;

public class ProfileDataServiceImpl extends DataServiceImpl<UserProfileM,ProfileResultSetM> implements IProfileDataService{
	
	
	
	public ProfileDataServiceImpl(String endpoint) {
		super(endpoint);
		
	}

	

	

	@Override
	protected Class<UserProfileM> getDataClass() throws Exception {
		
		return UserProfileM.class;
	}





	





	@Override
	protected Class<ProfileResultSetM> getDataResultSet() {

		return ProfileResultSetM.class;
	}



	

}
