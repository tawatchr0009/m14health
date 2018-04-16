package com.healthrecord.mobile.services.impl;

import com.healthrecord.mobile.model.NCDsM;
import com.healthrecord.mobile.model.data.results.NCDsResultSetM;
import com.healthrecord.mobile.services.INCDsDataService;

public class NCDsDataServiceImpl extends DataServiceImpl<NCDsM,NCDsResultSetM> implements INCDsDataService{

	public NCDsDataServiceImpl(String endpoint) {
		super(endpoint);
		
	}

	@Override
	protected Class<NCDsM> getDataClass() throws Exception {
		return NCDsM.class;
	}

	@Override
	protected Class<NCDsResultSetM> getDataResultSet() throws Exception {
		return NCDsResultSetM.class;
	}

	public NCDsM getById(String id) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

}
